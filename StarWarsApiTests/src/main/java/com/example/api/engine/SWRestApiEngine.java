package com.example.api.engine;

import com.example.api.models.HttpStarWarsResponse;

/**
 * Interface for api requests executors
 */
public interface SWRestApiEngine
{
        HttpStarWarsResponse get(final String url);
}
