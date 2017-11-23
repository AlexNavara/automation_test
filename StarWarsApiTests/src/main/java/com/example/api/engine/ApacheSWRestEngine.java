package com.example.api.engine;

import com.example.api.models.HttpStarWarsResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

public final class ApacheSWRestEngine implements SWRestApiEngine
{
   @Override
   public HttpStarWarsResponse get(final String url)
   {
      HttpStarWarsResponse response = new HttpStarWarsResponse();

      CloseableHttpClient client = HttpClientBuilder.create().build();
      HttpGet request = new HttpGet(URI.create(url));

      try(CloseableHttpResponse httpResponse = client.execute(request))
      {
         response.setStatusCode(httpResponse.getStatusLine().getStatusCode());
         response.setStatusMessage(httpResponse.getStatusLine().getReasonPhrase());

         final StringBuilder bodyBuilder = new StringBuilder();

         try(InputStream in = httpResponse.getEntity().getContent())
         {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
               bodyBuilder.append(line);
            }
         }

         response.setBody(bodyBuilder.toString());

      }
      catch (IOException e)
      {
         e.printStackTrace();
      }

      return response;
   }
}
