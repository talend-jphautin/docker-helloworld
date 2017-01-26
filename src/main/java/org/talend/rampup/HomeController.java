package org.talend.rampup;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jphautin on 25/01/17.
 */
@RestController
@RequestMapping( value = "/")
public class HomeController {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "welcome to ramp up application ! see <a href='swagger-ui.html'>swagger ui</a> or try the <a href='/api/greeting'>greeting service</a>";
    }
}
