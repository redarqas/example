package com.jam.resources;


import com.jam.core.EmailMessage;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path("/old")
public class MailGun {

    @GET
    @Path("info")
    @Produces("application/text")
    public String info() {
        return "Mark resource";
    }


    //TO be abstracted
    public EmailMessage transform(FormDataMultiPart multiPart) {
        Map<String, FormDataBodyPart> result = multiPart.getFields().entrySet().stream()
                                              .map(e -> e.getValue().get(0))
                                              .collect(Collectors.toMap(FormDataBodyPart::getName, p -> p));

        //TODO : Builder
        EmailMessage email = new EmailMessage();
        email.recipient = result.get("recipient").getValue();
        return email;
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces("application/text")
    public String mailgun(FormDataMultiPart multiPart) {
        //EmailMessage email = transform(multiPart);



        Map<String, List<FormDataBodyPart>> parts = multiPart.getFields();

        parts.forEach((k, vs) -> {
            System.out.println("Name : " + k);
            vs.stream().filter(v -> "text/plain".equals(v.getMediaType().toString())).forEach(v -> System.out.println(" ------------------------ " + v.getValue()));
        });

        return "Mark resource";
    }





}
