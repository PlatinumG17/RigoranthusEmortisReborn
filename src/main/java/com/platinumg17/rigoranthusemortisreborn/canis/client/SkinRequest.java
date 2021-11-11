package com.platinumg17.rigoranthusemortisreborn.canis.client;

public enum SkinRequest {
    UNREQUESTED,
    REQUESTED,
    RECEIVED,
    FAILED;

    boolean requested() {
        return this != UNREQUESTED;
    }
}
