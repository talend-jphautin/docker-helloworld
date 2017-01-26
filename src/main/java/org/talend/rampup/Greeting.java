package org.talend.rampup;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by jphautin on 25/01/17.
 */
public class Greeting {

    private final String name;

    private final Date date;

    public Greeting(String name) {
        this.name = name;
        this.date=new Date();
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

}
