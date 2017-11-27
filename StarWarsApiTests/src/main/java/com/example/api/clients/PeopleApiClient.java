package com.example.api.clients;

import com.example.api.engine.SWRestApiEngine;
import com.example.models.People;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class PeopleApiClient extends AbstractApiClient<People>
{

   public PeopleApiClient(final SWRestApiEngine engine)
   {
      super(engine);
   }

   @Override
   String getResourceName()
   {
      return "people";
   }

   @Override
   Class<People> getResourceClass()
   {
      return People.class;
   }

   @Override
   Gson gson()
   {
      return new GsonBuilder().setPrettyPrinting()
            .create();
   }
}
