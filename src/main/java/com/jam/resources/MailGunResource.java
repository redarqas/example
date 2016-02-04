package com.jam.resources;

import com.jam.core.EmailMessage;
import com.jam.core.MailGunTransformer;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import javax.ws.rs.core.Response;

public class MailGunResource extends EmailHook<FormDataMultiPart> {

    public MailGunResource() {
        super(new MailGunTransformer(""));
    }


    @Override
    protected Response process(EmailMessage email) {

        return Response.ok().build();
    }
}
