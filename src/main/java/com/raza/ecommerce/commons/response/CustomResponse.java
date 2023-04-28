package com.raza.ecommerce.commons.response;

import java.util.List;

public class CustomResponse<T> {
    private final T body;
    private final List<ErrorResponse> errors;

    public CustomResponse(T body, List<ErrorResponse> errors) {
        this.body = body;
        this.errors = errors;
    }

    public T getBody() {
        return this.body;
    }

    public List<ErrorResponse> getErrors() {
        return this.errors;
    }

    public String toString() {
        return "body: " + body + "errors: " + errors;
    }

    public static <T> CustomResponseBuilder<T> builder(){
        return new CustomResponseBuilder<>();
    }

    public static class CustomResponseBuilder<T> {
        private T body;
        private List<ErrorResponse> errors;

        public CustomResponseBuilder(){}

        public CustomResponseBuilder<T> body(T body) {
            this.body = body;
            return this;
        }

        public CustomResponseBuilder<T> errors(List<ErrorResponse> errors) {
            this.errors = errors;
            return this;
        }

        public CustomResponse<T> build() {
            return new CustomResponse<>(body, errors);
        }

        @Override
        public String toString() {
            return "CustomResponse.CustomResponseBuilder{" +
                    "body=" + body +
                    ", errors=" + errors +
                    '}';
        }
    }
}
