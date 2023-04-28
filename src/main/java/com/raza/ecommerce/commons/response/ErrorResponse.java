package com.raza.ecommerce.commons.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponse {
    @JsonProperty("code")
    private final String errorCode;
    @JsonProperty("message")
    private final String errorMessage;

    public ErrorResponse(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public String toString() {
        return "error-code: " + errorCode + " error message: " + errorMessage;
    }

    public static ErrorResponseBuilder builder(){
        return new ErrorResponseBuilder();
    }

    public static class ErrorResponseBuilder {
        private String errorCode;
        private String errorMessage;

        public ErrorResponseBuilder errorCode(String errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public ErrorResponseBuilder errorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        public ErrorResponse build() {
            return new ErrorResponse(errorCode, errorMessage);
        }

        @Override
        public String toString() {
            return "ErrorResponse.ErrorResponseBuilder{" +
                    "errorCode='" + errorCode + '\'' +
                    ", errorMessage='" + errorMessage + '\'' +
                    '}';
        }
    }
}
