package com.jam;

import com.jam.resources.MailGun;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

public class HolderApplication extends Application<HolderConfiguration> {

    public static void main(final String[] args) throws Exception {
        new HolderApplication().run(args);
    }

    @Override
    public String getName() {
        return "Holder";
    }

    @Override
    public void initialize(final Bootstrap<HolderConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final HolderConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
        environment.jersey().register(MultiPartFeature.class);
        environment.jersey().register(new MailGun());

    }

}
