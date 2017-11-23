package com.example.api.models;

import lombok.Data;

@Data
public final class StarWarsResponse<T>
{
        private int httpStatusCode;
        private String httpStatusMessage;
        private T response;
        private String errorMessage;
}
