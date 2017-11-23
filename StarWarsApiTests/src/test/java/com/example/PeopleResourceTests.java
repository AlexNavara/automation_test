package com.example;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.example.api.clients.PeopleApiClient;
import com.example.api.engine.ApacheSWRestEngine;
import com.example.models.People;
import com.example.models.SWCollection;

public class PeopleResourceTests
{

   private static PeopleApiClient apiClient;

   @BeforeClass
   public static void setupApiClient()
   {
      apiClient = new PeopleApiClient(new ApacheSWRestEngine());
   }

   @Test
   public void collectionShouldHaveCorrectCount()
   {
      final int expectedResult = 87;
      final int actualResult = apiClient.getCollectionResponse()
            .getResponse()
            .getCount();

      Assert.assertEquals(expectedResult, actualResult);
   }

   @Test
   public void shouldReturnNextPage()
   {
      SWCollection<People> firstPage = apiClient.getCollectionResponse()
            .getResponse();
      SWCollection<People> secondPage = apiClient.getCollectionResponse(firstPage.getNextPageUrl())
            .getResponse();

      Assert.assertNotNull(secondPage.getResults());
   }

}
