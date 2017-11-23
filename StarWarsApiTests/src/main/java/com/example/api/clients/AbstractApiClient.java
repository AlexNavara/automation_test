package com.example.api.clients;

import java.lang.reflect.Type;

import com.example.api.engine.SWRestApiEngine;
import com.example.api.models.HttpStarWarsResponse;
import com.example.api.models.StarWarsResponse;
import com.example.config.ConfigProvider;
import com.example.models.SWCollection;
import com.example.models.SWEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public abstract class AbstractApiClient<T extends SWEntity>
{
   private final SWRestApiEngine engine;
   private final Type collectionType;
   private final Type entityType;

   AbstractApiClient(final SWRestApiEngine engine)
   {
      this.engine = engine;
      this.collectionType = TypeToken.getParameterized(SWCollection.class, getResourceClass())
            .getType();
      this.entityType = getResourceClass();
   }

   public StarWarsResponse<T> getEntityResponse(final int entityId)
   {
      final String url = String.format("%s/%s/%d/", ConfigProvider.INSTANCE.getServerUrl(), getResourceName(), entityId);
      return getEntityResponse(url);
   }

   public StarWarsResponse<T> getEntityResponse(final String url)
   {
      return getGenericResponse(url, entityType);
   }

   public StarWarsResponse<SWCollection<T>> getCollectionResponse()
   {
      final String url = String.format("%s/%s/", ConfigProvider.INSTANCE.getServerUrl(), getResourceName());
      return getCollectionResponse(url);
   }

   public StarWarsResponse<SWCollection<T>> getCollectionResponse(final String url)
   {
      return getGenericResponse(url, collectionType);
   }

   private <R> StarWarsResponse<R> getGenericResponse(final String url, final Type responseType)
   {
      HttpStarWarsResponse httpResponse = engine.get(url);

      StarWarsResponse<R> response = new StarWarsResponse<>();
      response.setHttpStatusCode(httpResponse.getStatusCode());
      response.setHttpStatusMessage(httpResponse.getStatusMessage());

      if (isSuccess(httpResponse.getStatusCode()))
      {
         response.setResponse(gson().fromJson(httpResponse.getBody(), responseType));
      }
      else
      {
         response.setErrorMessage(httpResponse.getBody());
      }

      return response;
   }

   abstract String getResourceName();

   abstract Class<T> getResourceClass();

   abstract Gson gson();

   private boolean isSuccess(final int statusCode)
   {
      return String.valueOf(statusCode)
            .matches("^[1-2]\\d{2}$");
   }
}
