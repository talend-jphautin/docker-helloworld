package org.talend.rampup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by jphautin on 25/01/17.
 */
@SpringBootApplication
public class RampupApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(RampupApplication.class, args);
    }

}
