package com.mymovieportal.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.mymovieportal.dto.MovieDTO;
import com.mymovieportal.dto.MovieTheatreDTO;
import com.mymovieportal.exception.ExceptionConstants;
import com.mymovieportal.exception.MovieException;
import com.mymovieportal.exception.TheatreException;
import com.mymovieportal.model.Movie;
import com.mymovieportal.model.MovieTheatre;
import com.mymovieportal.model.Showtime;
import com.mymovieportal.model.Theatre;
import com.mymovieportal.repository.MovieRepository;
import com.mymovieportal.repository.MovieTheatreRepository;
import com.mymovieportal.repository.ShowtimeRepository;
import com.mymovieportal.repository.TheatreRepository;
import com.mymovieportal.service.MovieService;
import com.mymovieportal.service.TheatreService;

// TODO: Auto-generated Javadoc
/**
 * The Class MovieServiceImpl.
 */
@Service
public class MovieServiceImpl implements MovieService {

	/** The movie repository. */
	@Autowired
	MovieRepository movieRepository;

	/** The showtime repository. */
	@Autowired
	ShowtimeRepository showtimeRepository;

	/** The theatre repository. */
	@Autowired
	TheatreRepository theatreRepository;

	/** The movie theatre repository. */
	@Autowired
	MovieTheatreRepository movieTheatreRepository;

	@Autowired
	TheatreService theatreService;

	/** The logger. */
	Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);

	/**
	 * Gets the movies.
	 *
	 * @return the movies
	 */
	@Override
	@Transactional
	public List<Movie> getMovies() {
		// TODO Auto-generated method stub
		return movieRepository.getMovies();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.mymovieportal.service.MovieService#getMoviesForDeletion()
	 */
	@Override
	@Transactional
	public List<Movie> getMoviesForDeletion() {
		// TODO Auto-generated method stub
		List<Movie> movieList = movieRepository.getMovies();

		List<MovieTheatre> movieTheatreList = movieTheatreRepository.findAll();

		List<Movie> tempList = new ArrayList<>();

		for (int i = 0; i < movieList.size(); i++) {
			for (int j = 0; j < movieTheatreList.size(); j++) {
				if (movieList.get(i).getMovieId() == movieTheatreList.get(j).getMtMovieId()) {
					tempList.add(movieList.get(i));
				}
			}
		}
		movieList.removeAll(tempList);
		return movieList;
	}

	/**
	 * Gets the movie.
	 *
	 * @param movieId
	 *            the movie id
	 * @return the movie
	 * @throws MovieException
	 *             the movie exception
	 */
	@Override
	@Transactional
	public Movie getMovie(long movieId) throws MovieException {

		Movie movie = movieRepository.findByMovieId(movieId);
		if (movie == null) {
			throw new MovieException(ExceptionConstants.MOVIEOPERATIONFAILS, HttpStatus.NOT_FOUND);
		}
		return movie;
	}

	/**
	 * Gets the movies by theatre.
	 *
	 * @param theatreId
	 *            the theatre id
	 * @return the movies by theatre
	 * @throws TheatreException
	 *             the theatre exception
	 */
	@Override
	@Transactional
	public List<Movie> getMoviesByTheatre(long theatreId) throws TheatreException {
		// TODO Auto-generated method stub

		List<Long> theatreIds = theatreRepository.getTheatreIds();
		if (!theatreIds.contains(theatreId)) {
			throw new TheatreException(ExceptionConstants.THEATREOPERATIONFAILS, HttpStatus.NOT_FOUND);

		}
		return movieRepository.getMoviesByTheatre(theatreId);

	}

	/**
	 * Gets the show time.
	 *
	 * @param movieTheatreDTO
	 *            the movie theatre DTO
	 * @return the show time
	 * @throws MovieException
	 *             the movie exception
	 */
	@Override
	@Transactional
	public List<String> getShowTime(MovieTheatreDTO movieTheatreDTO) throws MovieException {

		long movieId = movieTheatreDTO.getMtMovieId();
		long theatreId = movieTheatreDTO.getMtTheatreId();
		List<String> str = showtimeRepository.getShowTime(movieId, theatreId);

		if (str.size() == 0) {
			throw new MovieException(ExceptionConstants.MOVIEOPERATIONFAILS, HttpStatus.NOT_FOUND);
		}

		for (int i = 0; i < str.size(); i++) {
			String[] splited = str.get(i).split("\\s+");
			str.remove(str.get(i));
			str.add(i, splited[1]);

		}

		Object[] arr = str.toArray();
		String[] stringArray = Arrays.copyOf(arr, arr.length, String[].class);
		Arrays.sort(stringArray);
		str = Arrays.asList(stringArray);
		return str;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.mymovieportal.service.MovieService#getMovieNameOnly(java.lang.String)
	 */
	@Override
	@Transactional
	public String getMovieNameOnly(long movieId) {
		return movieRepository.getMovieNameOnly(movieId);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.mymovieportal.service.MovieService#getDiscount(com.mymovieportal.dto.
	 * MovieTheatreDTO)
	 */
	@Override
	@Transactional
	public int getDiscount(MovieTheatreDTO movieTheatreDTO) throws MovieException {
		long movieId = movieTheatreDTO.getMtMovieId();
		long theatreId = movieTheatreDTO.getMtTheatreId();
		List<Integer> discount = movieRepository.getDiscount(movieId, theatreId);
		if (discount.size() == 0) {
			throw new MovieException(ExceptionConstants.MOVIEOPERATIONFAILS, HttpStatus.NOT_FOUND);
		}
		return discount.get(0);

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.mymovieportal.service.MovieService#insertMovie(com.mymovieportal.dto.
	 * MovieDTO)
	 */
	@Override
	@Transactional
	public Movie insertMovie(MovieDTO movieDTO) {
		// TODO Auto-generated method stub

		Movie movie1 = movieRepository.findByMovieName(movieDTO.getMovieName());
		if (movie1 != null) {
			movie1.setMovieStatus("active");
			return movie1;
		}

		/*
		 * List<String> movieIds = movieRepository.getMovieIds(); String id =
		 * movieIds.get(movieIds.size() - 1); String ch = "m"; id = id.substring(1); int
		 * movieId = Integer.parseInt(id); if (movieId < 9) { ch += "0"; } movieId++;
		 */

		Movie movie = new Movie();
		movie.setMovieName(movieDTO.getMovieName());
		// movie.setMovieId(ch + movieId);
		movie.setMovieStatus("active");
		movie.setMoviePrice("250");
		Movie response = movieRepository.save(movie);
		return response;

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.mymovieportal.service.MovieService#deleteMovie(com.mymovieportal.dto.
	 * MovieDTO)
	 */
	@Override
	@Transactional
	public Movie deleteMovie(MovieDTO movieDTO) throws MovieException {
		// TODO Auto-generated method stub

		Movie movie = movieRepository.findByMovieNameAndMovieStatus(movieDTO.getMovieName(), "active");
		if (movie == null) {
			throw new MovieException(ExceptionConstants.MOVIEOPERATIONFAILS, HttpStatus.NOT_FOUND);
		}
		movie.setMovieName(movie.getMovieName());
		movie.setMovieStatus("inactive");
		movie.setMovieId(movie.getMovieId());
		Movie response = movieRepository.save(movie);
		return response;

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.mymovieportal.service.MovieService#movieOperationThroughCSV(java.io.
	 * BufferedReader, java.lang.String)
	 */
	@Override
	@Transactional
	public boolean movieOperationThroughCSV(BufferedReader br, String operation) throws MovieException {
		boolean response = false;
		List<Movie> movieList = new ArrayList<>();

		String line = "";
		String cvsSplitBy = ",";

		try {

			if ("add".equalsIgnoreCase(operation)) {

				while ((line = br.readLine()) != null) {

					String[] movieArray = line.split(cvsSplitBy);
					Movie movie = new Movie();
					// movie.setMovieId(movieArray[0].substring(1));
					movie.setMovieName(movieArray[1]);
					movie.setMoviePrice(movieArray[2]);
					movie.setMovieStatus(movieArray[3].substring(0, 6));

					movieList.add(movie);
				}
				List<Movie> mList = movieRepository.getAllMovies();
				List<Movie> tempList = new ArrayList<>();

				for (int i = 0; i < movieList.size(); i++) {
					for (int j = 0; j < mList.size(); j++) {
						if (movieList.get(i).getMovieName().equals(mList.get(j).getMovieName())) {
							if ((movieList.get(i).getMovieStatus().equals(mList.get(j).getMovieStatus()))
									&& (movieList.get(i).getMoviePrice().equals(mList.get(j).getMoviePrice()))) {

								mList.get(j).setMoviePrice(movieList.get(i).getMoviePrice());

							} else {
								mList.get(j).setMovieStatus("active");
								mList.get(j).setMovieId(mList.get(j).getMovieId());
								mList.get(j).setMovieName(mList.get(j).getMovieName());
								mList.get(j).setMoviePrice(movieList.get(i).getMoviePrice());
								movieRepository.save(mList.get(j));
								// movieList.remove(movieList.get(i));
								tempList.add(movieList.get(i));
								break;
							}
						}

					}
				}

				for (int i = 0; i < tempList.size(); i++) {
					movieList.remove(tempList.get(i));
				}
				movieRepository.save(movieList);
				response = true;
			}

			if ("delete".equalsIgnoreCase(operation)) {

				while ((line = br.readLine()) != null) {

					String[] movieArray = line.split(cvsSplitBy);
					Movie movie = new Movie();
					movie.setMovieName(movieArray[0]);
					movieList.add(movie);
				}

				for (int i = 0; i < movieList.size(); i++) {
					MovieDTO movieDTO = new MovieDTO();
					movieDTO.setMovieName(movieList.get(i).getMovieName());
					Movie movie = deleteMovie(movieDTO);
					if (movie != null) {
						response = true;
					}
				}
			}

		} catch (IOException ex) {
			logger.warn(ex.getMessage());
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.mymovieportal.service.MovieService#movieInsertOperationThroughXml(java.io
	 * .File)
	 */
	@Override
	@Transactional
	public boolean movieInsertOperationThroughXml(File br) {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		boolean response = false;

		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(br);
			doc.getDocumentElement().normalize();

			NodeList nodeList = doc.getElementsByTagName("Movie");

			// now XML is loaded as Document in memory, lets convert it to Object List

			List<Movie> movieList = new ArrayList<>();
			for (int i = 0; i < nodeList.getLength(); i++) {
				movieList.add(getMovie(nodeList.item(i)));
			}

			List<Movie> mList = movieRepository.getAllMovies();
			List<Movie> tempList = new ArrayList<>();
			for (int i = 0; i < movieList.size(); i++) {
				for (int j = 0; j < mList.size(); j++) {
					if (movieList.get(i).getMovieName().equals(mList.get(j).getMovieName())) {
						if ((movieList.get(i).getMovieStatus().equals(mList.get(j).getMovieStatus()))
								&& (movieList.get(i).getMoviePrice().equals(mList.get(j).getMoviePrice()))) {

							mList.get(j).setMoviePrice(movieList.get(i).getMoviePrice());

						} else {
							mList.get(j).setMovieStatus("active");
							mList.get(j).setMovieId(mList.get(j).getMovieId());
							mList.get(j).setMovieName(mList.get(j).getMovieName());
							mList.get(j).setMoviePrice(movieList.get(i).getMoviePrice());

							movieRepository.save(mList.get(j));
							// movieList.remove(movieList.get(i));
							tempList.add(movieList.get(i));
							break;
						}
					}
				}
			}

			for (int i = 0; i < tempList.size(); i++) {
				movieList.remove(tempList.get(i));
			}
			movieRepository.save(movieList);
			response = true;

		} catch (SAXException | ParserConfigurationException | IOException e1) {
		}
		return response;
	}

	/**
	 * Gets the movie.
	 *
	 * @param node
	 *            the node
	 * @return the movie
	 */
	private static Movie getMovie(Node node) {

		// XMLReaderDOM domReader = new XMLReaderDOM();
		Movie movie = new Movie();
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			// movie.setMovieId(getTagValue("movieId", element));
			movie.setMovieName(getTagValue("movieName", element));
			movie.setMoviePrice(getTagValue("moviePrice", element));
			movie.setMovieStatus(getTagValue("movieStatus", element));
		}
		return movie;
	}

	/**
	 * Gets the tag value.
	 *
	 * @param tag
	 *            the tag
	 * @param element
	 *            the element
	 * @return the tag value
	 */
	private static String getTagValue(String tag, Element element) {
		NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = nodeList.item(0);
		return node.getNodeValue();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.mymovieportal.service.MovieService#movieDeleteOperationThroughXml(java.io
	 * .File)
	 */
	@Override
	@Transactional
	public boolean movieDeleteOperationThroughXml(File br) throws MovieException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		boolean response = false;

		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(br);
			doc.getDocumentElement().normalize();

			NodeList nodeList = doc.getElementsByTagName("Movie");

			// now XML is loaded as Document in memory, lets convert it to Object List

			List<Movie> movieList = new ArrayList<>();
			for (int i = 0; i < nodeList.getLength(); i++) {
				movieList.add(getMovieForDeletion(nodeList.item(i)));
			}

			for (int i = 0; i < movieList.size(); i++) {
				MovieDTO movieDTO = new MovieDTO();
				movieDTO.setMovieName(movieList.get(i).getMovieName());
				Movie movie = deleteMovie(movieDTO);
				response = true;
			}
		} catch (MovieException ex) {
			throw new MovieException(ExceptionConstants.MOVIEOPERATIONFAILS, HttpStatus.NOT_FOUND);
		} catch (SAXException | ParserConfigurationException | IOException ex) {
			logger.warn(ex.getMessage());
		}
		return response;
	}

	/**
	 * Gets the movie for deletion.
	 *
	 * @param node
	 *            the node
	 * @return the movie for deletion
	 */
	private static Movie getMovieForDeletion(Node node) {

		// XMLReaderDOM domReader = new XMLReaderDOM();
		Movie movie = new Movie();
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;

			movie.setMovieName(getTagValue("movieName", element));

		}
		return movie;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.mymovieportal.service.MovieService#getShowtimes()
	 */
	@Override
	@Transactional
	public List<Showtime> getShowtimes() throws ParseException {
		// TODO Auto-generated method stub
		List<Showtime> showtimes = showtimeRepository.findAll();
		return showtimes;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.mymovieportal.service.MovieService#mapMovieTheatre(com.mymovieportal.dto.
	 * MovieTheatreDTO)
	 */
	@Override
	@Transactional
	public MovieTheatreDTO mapMovieTheatre(MovieTheatreDTO movieTheatreDTO) throws MovieException {
		// MovieTheatreDTO movieTheatreDto = null;

		ModelMapper modelMapper = new ModelMapper();

		// MovieTheatre movieTheatre = modelMapper.map(movieTheatreDTO,
		// MovieTheatre.class);

		MovieTheatre movieTheatre = null;

		MovieTheatre entity = new MovieTheatre();
		entity.setDiscount(movieTheatreDTO.getDiscount());
		entity.setMtMovieId(movieTheatreDTO.getMtMovieId());
		entity.setMtShowtimeId(movieTheatreDTO.getMtShowtimeId());
		entity.setMtTheatreId(movieTheatreDTO.getMtTheatreId());

		try {
			movieTheatre = movieTheatreRepository.save(entity);
		} catch (DataIntegrityViolationException ex) {
			throw new MovieException(ExceptionConstants.MOVIETHEATREID, HttpStatus.CONFLICT);
		}
		MovieTheatreDTO movieTheatreDto = modelMapper.map(movieTheatre, MovieTheatreDTO.class);

		return movieTheatreDto;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.mymovieportal.service.MovieService#addTime(java.lang.String)
	 */
	@Override
	@Transactional
	public boolean addTime(String result) throws ParseException, MovieException {
		// TODO Auto-generated method stub
		Showtime showtime = new Showtime();
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
			Date date = formatter.parse(result);

			showtime.setShowTimeValue(date);
			showtime = showtimeRepository.save(showtime);

		} catch (DataIntegrityViolationException ex) {
			throw new MovieException(ExceptionConstants.SHOWTIMEEXISTS, HttpStatus.CONFLICT);
		} catch (Exception ex) {
			return false;
		}

		return true;

	}

	@Override
	@Transactional
	public String getMovieTheatrePdf() throws ParseException {

		List<Movie> movieList = getMovies();

		List<Theatre> theatreList = theatreService.getTheatres();

		List<Showtime> showtimeList = getShowtimes();

		List<MovieTheatre> movieTheatreList = movieTheatreRepository.findAll();

		String pdfList = "Movie          Theatre          Showtime \n\n";

		for (int i = 0; i < movieTheatreList.size(); i++) {

			for (int j = 0; j < movieList.size(); j++) {
				if (movieTheatreList.get(i).getMtMovieId() == movieList.get(j).getMovieId()) {
					pdfList = pdfList.concat(movieList.get(j).getMovieName());
					pdfList = pdfList.concat("          ");
				}
			}

			for (int k = 0; k < theatreList.size(); k++) {
				if (movieTheatreList.get(i).getMtTheatreId() == theatreList.get(k).getTheatreId()) {
					pdfList = pdfList.concat(theatreList.get(k).getTheatreName());
					pdfList = pdfList.concat("          ");
				}
			}

			for (int l = 0; l < showtimeList.size(); l++) {
				if (movieTheatreList.get(i).getMtShowtimeId() == showtimeList.get(l).getShowTimeId()) {
					pdfList = pdfList.concat(showtimeList.get(l).getShowTimeValue().toString());
					pdfList = pdfList.concat("\n");
				}
			}

		}

		System.out.println(pdfList);
		return pdfList;

	}

}
