package com.example.api.engine;

import com.example.api.models.SimplifiedHttpResponse;
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
   public SimplifiedHttpResponse get(final String url)
   {
      SimplifiedHttpResponse simplifiedHttpResponse = new SimplifiedHttpResponse();

      CloseableHttpClient client = HttpClientBuilder.create().build();
      HttpGet request = new HttpGet(URI.create(url));

      try(CloseableHttpResponse httpResponse = client.execute(request))
      {
         simplifiedHttpResponse.setStatusCode(httpResponse.getStatusLine().getStatusCode());
         simplifiedHttpResponse.setStatusMessage(httpResponse.getStatusLine().getReasonPhrase());

         final StringBuilder bodyBuilder = new StringBuilder();

         try(InputStream in = httpResponse.getEntity().getContent())
         {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
               bodyBuilder.append(line);
            }
         }

         simplifiedHttpResponse.setBody(bodyBuilder.toString());

      }
      catch (IOException e)
      {
         e.printStackTrace();
      }

      return simplifiedHttpResponse;
   }
}
