package com.mymovieportal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO: Auto-generated Javadoc
/**
 * The Class MymovieportalApplication.
 */

@SpringBootApplication
@EnableAutoConfiguration
// @SuppressWarnings("deprecation")
public class MymovieportalApplication {

    static Logger logger = LoggerFactory.getLogger(MymovieportalApplication.class);

    /**
     * The main method.
     *
     * @param args the arguments
     */

    public static void main(String[] args) {
        logger.info("mymovieportal started");
        SpringApplication.run(MymovieportalApplication.class, args);
    }
}
