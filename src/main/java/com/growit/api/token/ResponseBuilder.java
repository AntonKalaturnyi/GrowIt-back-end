package com.growit.api.token;

public class ResponseBuilder {

    private String url;
    private String message;

    ResponseBuilder() {}

    public ResponseBuilder url(String url) {
        this.url = url;
        return this;
    }

    public ResponseBuilder message(String message) {
        this.message = message;
        return this;
    }

    public MessageResponse build() {
        return new MessageResponse(message, url);
    }

}
