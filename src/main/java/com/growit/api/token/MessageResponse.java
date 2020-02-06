package com.growit.api.token;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class MessageResponse {

    private String url;
    private String message;

    MessageResponse(String message, String url) {
        this.url = url;
        this.message = message;
    }

    public static ResponseBuilder builder() {
        return new ResponseBuilder();
    }
}
