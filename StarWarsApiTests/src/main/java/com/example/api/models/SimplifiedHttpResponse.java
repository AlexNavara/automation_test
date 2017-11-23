package com.example.api.models;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class represents a container for HTTP response fields
 */
@Data
@NoArgsConstructor
public class SimplifiedHttpResponse
{
        private int statusCode;
        private String statusMessage;
        private String body;
}
