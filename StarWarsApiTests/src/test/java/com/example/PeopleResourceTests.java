package com.example;

import com.example.api.models.StarWarsResponse;
import com.example.assertions.PeopleAssertion;
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
      final int actualResult = apiClient.getCollection()
            .getResponse()
            .getCount();

      Assert.assertEquals(expectedResult, actualResult);
   }

   @Test
   public void shouldReturnNextPage()
   {
      SWCollection<People> firstPage = apiClient.getCollection()
            .getResponse();
      SWCollection<People> secondPage = apiClient.getCollection(firstPage.getNextPageUrl())
            .getResponse();

      Assert.assertNotNull(secondPage.getResults());
   }

   @Test
   public void checkHttpResponseCodeAndStatus()
   {
      StarWarsResponse<People> response = apiClient.getById(1);

      final int actualStatusCode = response.getSimplifiedHttpResponse().getStatusCode();
      final String actualHttpStatus = response.getSimplifiedHttpResponse().getStatusMessage();

      Assert.assertEquals(200, actualStatusCode);
      Assert.assertEquals("OK", actualHttpStatus);
   }

   @Test
   public void customAssertionTest()
   {
      People luke = apiClient.getById(1).getResponse();

      PeopleAssertion.assertThat(luke).hasName("Luke Skywalker");
   }

}
