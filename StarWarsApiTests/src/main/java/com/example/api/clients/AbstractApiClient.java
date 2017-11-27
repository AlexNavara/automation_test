package com.example.api.clients;

import java.lang.reflect.Type;

import com.example.api.engine.SWRestApiEngine;
import com.example.api.models.SimplifiedHttpResponse;
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

   public StarWarsResponse<T> getById(final int entityId)
   {
      final String url = String.format("%s/%s/%d/", ConfigProvider.INSTANCE.getServerUrl(), getResourceName(), entityId);
      return getById(url);
   }

   public StarWarsResponse<T> getById(final String url)
   {
      return getGenericResponse(url, entityType);
   }

   public StarWarsResponse<SWCollection<T>> getCollection()
   {
      final String url = String.format("%s/%s/", ConfigProvider.INSTANCE.getServerUrl(), getResourceName());
      return getCollection(url);
   }

   public StarWarsResponse<SWCollection<T>> getCollection(final String url)
   {
      return getGenericResponse(url, collectionType);
   }

   private <GT> StarWarsResponse<GT> getGenericResponse(final String url, final Type responseType)
   {
      SimplifiedHttpResponse httpResponse = engine.get(url);

      StarWarsResponse<GT> response = new StarWarsResponse<>(httpResponse);

      if (isSuccess(httpResponse.getStatusCode()))
      {
         response.setResponse(gson().fromJson(httpResponse.getBody(), responseType));
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
