package com.example.api.engine;

import com.example.api.models.SimplifiedHttpResponse;

/**
 * Interface for api requests executors
 */
public interface SWRestApiEngine
{
        /**
         * @param url - resource full URL
         * @return instance of SimplifiedHttpResponse class, describing HTTP response
         */
        SimplifiedHttpResponse get(final String url);
}
