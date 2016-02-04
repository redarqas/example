package com.jam.core;

import java.util.Optional;

public class Identification {

    public final ApiFamilly familly;
    public final Optional<String> clientUUID;
    public final Optional<String> apiKeyBannette;
    public final Optional<String> apiKeyManager;


    public Identification(ApiFamilly familly, Optional<String> clientUUID, Optional<String> apiKeyManager, Optional<String> apiKeyBannette) {
        this.familly = familly;
        this.clientUUID = clientUUID;
        this.apiKeyManager = apiKeyManager;
        this.apiKeyBannette = apiKeyBannette;
    }

    public Identification() {
        this.familly = ApiFamilly.NONE;
        this.clientUUID = Optional.empty();
        this.apiKeyManager = Optional.empty();
        this.apiKeyBannette = Optional.empty();
    }
}
