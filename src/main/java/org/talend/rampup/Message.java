package org.talend.rampup;

/**
 * Created by jphautin on 26/01/17.
 */
public class Message {

    public Message(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    private final String content;
}
