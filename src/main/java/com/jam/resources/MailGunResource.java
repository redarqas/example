package com.jam.resources;

import com.jam.core.EmailMessage;
import com.jam.core.MailGunTransformer;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/mailgun")
public class MailGunResource extends EmailHook<FormDataMultiPart> {

    public MailGunResource() {
        super(new MailGunTransformer("sandboxc83355d02c5148c1a9596d78119cf204.mailgun.org"));
    }

    @Override
    protected Response process(EmailMessage email) {
        System.out.println(email.recipient);
        System.out.println(email.identification);
        return Response.ok().build();
    }
}
