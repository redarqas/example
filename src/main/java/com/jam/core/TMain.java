package com.jam.core;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TMain {

    public static void main(String[] args) {
        String domain = "workplace-mail.people-doc.com";
        Pattern sharedPattern = Pattern.compile(String.format("workplace-shared-([A-Za-z0-9+/-]+)-(.*)@%s", domain));

        Matcher matcher = sharedPattern.matcher("workplace-shared-123@workplace-mail.people-doc.com");
        if(matcher.find()) {
            System.out.println(matcher.groupCount());
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
        }


        Optional<String> s = Optional.empty();
        //s.orElse()

    }




}
