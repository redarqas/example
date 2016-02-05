package com.jam.resources;

import com.jam.core.EmailMessage;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.function.Function;

public abstract class EmailHook<T> {

    protected final Function<T, EmailMessage> transform;

    public EmailHook(Function<T, EmailMessage> transform) {
        this.transform = transform;
    }

    @Path("/callback")
    @POST
    @Consumes(MediaType.WILDCARD)
    public Response callback(T msg) {
        System.out.println("Receive Message");
        EmailMessage email = transform.apply(msg);
        return process(email);
    }

    protected abstract Response process(EmailMessage email);


}
