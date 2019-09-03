package com.mymovieportal.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mymovieportal.dao.SeatBookingDAO;
import com.mymovieportal.dto.ConfirmSeatMailDTO;
import com.mymovieportal.model.SeatBooking;
import com.mymovieportal.service.MailService;
import com.mymovieportal.service.SeatBookingService;

// TODO: Auto-generated Javadoc

/**
 * The Class ShowTimeAlertScheduler.
 */
@EnableScheduling
@Component
public class ShowTimeAlertScheduler {

    /** The seat booking dao. */
    @Autowired
    private SeatBookingDAO seatBookingDao;

    /** The confirm seat mail. */
    @Autowired
    private MailService confirmSeatMail;

    /** The seat booking service. */
    @Autowired
    private SeatBookingService seatBookingService;

    /** The i. */
    static int i = 0;

    /**
     * Send alert.
     */
    @Scheduled(fixedDelay = 60000)
    // @Scheduled(cron = "0 0 1 * * *")
    public void sendAlert() {
        // System.out.println(i++ + " " + new Date().getMinutes());
        // SeatBookingServiceImpl bookingServiceImpl = new SeatBookingServiceImpl();
        DateFormat dateFormat0 = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dateFormat1 = new SimpleDateFormat("HH:mm:ss");
        Date todayDate = new Date();
        String date1 = dateFormat0.format(todayDate);
        String time = dateFormat1.format(todayDate);
        // SeatBookingDAO seatBookingDAO = new SeatBookingDAOImpl();

        List<SeatBooking> seatBooking = seatBookingDao.getAllBookedSeat(date1, time);

        if (seatBooking != null) {
            Locale aLocale = new Locale("Hindi");
            Calendar calendar = Calendar.getInstance(aLocale);
            calendar.add(Calendar.HOUR_OF_DAY, +3);
            Date date = calendar.getTime();
            // Date date2 = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            ConfirmSeatMailDTO confirmSeatMailDTO = new ConfirmSeatMailDTO();
            String mailSentUser = "";
            for (SeatBooking seatBooking2 : seatBooking) {
                if (!mailSentUser.contains("" + confirmSeatMailDTO.getUserId())) {
                    try {
                        Date date2 = dateFormat.parse(seatBooking2.getSbShowTime());
                        if (date.getHours() == date2.getHours() && (date.getMinutes() <= date2.getMinutes())) {
                            // confirmSeatMailDTO = new ConfirmSeatMailDTO();
                            confirmSeatMailDTO.setMovieId(seatBooking2.getSbMovieId());
                            confirmSeatMailDTO.setTheatreId(seatBooking2.getSbTheatreId());
                            confirmSeatMailDTO.setUserId(seatBooking2.getSbUserId());
                            confirmSeatMailDTO.setDate(seatBooking2.getSbDate());
                            confirmSeatMailDTO.setTime(seatBooking2.getSbShowTime());
                            List<String> seatNames = seatBookingService.getSeatNames(seatBooking2.getSbUserId(),
                                seatBooking2.getSbDate(), seatBooking2.getSbShowTime(),
                                seatBooking2.getSbTheatreId(), seatBooking2.getSbMovieId());

                            // System.out.println("****************" + seatNames);
                            confirmSeatMailDTO.setSeatNames(seatNames);
                            mailSentUser = confirmSeatMailDTO.getUserId() + " ";
                            confirmSeatMail.showTimeAlertMail(confirmSeatMailDTO);
                        }
                    } catch (Exception e) {
                        // TODO: handle exception
                        // e.printStackTrace();
                    }
                }
            }
        }
    }

    // public static void main(String[] args) {
    // sendAlert();
    // }

}
