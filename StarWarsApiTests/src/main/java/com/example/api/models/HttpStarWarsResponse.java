package com.example.api.models;

import lombok.Data;

/**
 * Class represents a container for HTTP response fields
 */
@Data
public final class HttpStarWarsResponse
{
        private int statusCode;
        private String statusMessage;
        private String body;
}
