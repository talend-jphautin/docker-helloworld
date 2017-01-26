package org.talend.rampup;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by jphautin on 25/01/17.
 */
@RestController
@RequestMapping( value = "/api" )
public class GreetingController {

    private static final String template = "Hello, %s!";

    @Autowired
    private GreetingRepository greetingRepository;

    @RequestMapping(value="greeting/{name}", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Message greeting(@PathVariable("name") String name) {
        Greeting greeting = new Greeting(name);
        greetingRepository.insert(greeting);
        return new Message(String.format(template, name));
    }

    @RequestMapping(value="greeting/{name}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Greeting> greetings(@PathVariable("name") String name) {
        List<Greeting> greetings = greetingRepository.findByName(name);
        return greetings;
    }
}
