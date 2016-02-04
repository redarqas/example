package com.jam.core;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

public class MailGunTransformer implements Function<FormDataMultiPart, EmailMessage> {

    private final String domain;
    private final Pattern sharedPattern;
    private final Pattern personalPattern;
    private final Pattern v1Pattern;

    public MailGunTransformer(String domain) {
        this.domain = domain;
        sharedPattern = Pattern.compile(String.format("workplace-shared-([A-Za-z0-9+/-]+)-(.*)@%s", domain));
        personalPattern = Pattern.compile(String.format("workplace-personal-([A-Za-z0-9+/-]+)-(.*)@%s", domain));
        v1Pattern = Pattern.compile(String.format("workplace-(.*)@%s", domain));
    }

    @Override
    public EmailMessage apply(FormDataMultiPart multiPart) {
        Map<String, FormDataBodyPart> result = multiPart.getFields().entrySet().stream().map(e -> e.getValue().get(0)).collect(Collectors.toMap(FormDataBodyPart::getName, p -> p));
        // TODO : Builder
        EmailMessage email = new EmailMessage();
        String recipient = result.get("recipient").getValue();
        email.recipient = recipient;
        Optional<Identification> identification = extractIdentification(recipient);
        email.identification = identification;
        return email;
    }

    private Optional<Identification> extractIdentification(String recipient) {
        Stream<Function<String, Optional<Identification>>> extractions = Stream.of(this::extractPersonalIdentity, this::extractSharedIdentity, this::extractLeagcyIdentity);
        return extractions.map(f -> f.apply(recipient)).filter(Optional::isPresent).map(Optional::get).findFirst();
    }

    protected Optional<Identification> extractPersonalIdentity(String data) {
        Matcher matcher = personalPattern.matcher(data);
        if (matcher.find()) {
            return Optional.of(new Identification(ApiFamilly.PERSONAL, Optional.of(matcher.group(1)), Optional.of(matcher.group(2)), Optional.empty()));
        }
        return Optional.empty();
    }

    protected Optional<Identification> extractSharedIdentity(String data) {
        Matcher matcher = sharedPattern.matcher(data);
        if (matcher.find()) {
            return Optional.of(new Identification(ApiFamilly.SHARED, Optional.of(matcher.group(1)), Optional.empty(), Optional.of(matcher.group(2))));
        }
        return Optional.empty();
    }

    protected Optional<Identification> extractLeagcyIdentity(String data) {
        Matcher matcher = sharedPattern.matcher(data);
        if (matcher.find()) {
            return Optional.of(new Identification(ApiFamilly.LEGACY, Optional.empty(), Optional.empty(), Optional.of(matcher.group(1))));
        }
        return Optional.empty();
    }

}
