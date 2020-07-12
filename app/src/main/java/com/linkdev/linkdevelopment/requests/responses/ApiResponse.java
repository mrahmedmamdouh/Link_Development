package com.linkdev.linkdevelopment.requests.responses;

import java.io.IOException;
import java.util.Objects;

import retrofit2.Response;

@SuppressWarnings("ALL")
public class ApiResponse<T> {

    public ApiResponse<T> create(Throwable error) {
        return new ApiErrorResponse<>(Objects.equals(error.getMessage(), "") ? error.getMessage() : "Unknown error\nCheck network connection");
    }

    public ApiResponse<T> create(Response<T> response) {

        if (response.isSuccessful()) {
            T body = response.body();

            if (body == null || response.code() == 204) {
                return new ApiEmptyResponse<>();
            } else {
                return new ApiSuccessResponse<>(body);
            }
        } else {
            String errorMsg;
            try {
                assert response.errorBody() != null;
                errorMsg = response.errorBody().string();
            } catch (IOException e) {
                e.printStackTrace();
                errorMsg = response.message();
            }
            return new ApiErrorResponse<>(errorMsg);
        }
    }

    /**
     * Generic success response from api
     *
     * @param <T>
     */
    @SuppressWarnings("TypeParameterHidesVisibleType")
    public class ApiSuccessResponse<T> extends ApiResponse<T> {

        private final T body;

        ApiSuccessResponse(T body) {
            this.body = body;
        }

        public T getBody() {
            return body;
        }

    }

    /**
     * Generic Error response from API
     *
     * @param <T>
     */
    @SuppressWarnings("TypeParameterHidesVisibleType")
    public class ApiErrorResponse<T> extends ApiResponse<T> {

        private final String errorMessage;

        ApiErrorResponse(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

    }


    public class ApiEmptyResponse<T> extends ApiResponse<T> {
    }

}