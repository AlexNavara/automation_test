package com.example.api;

import com.example.config.ConfigProvider;
import com.example.models.Equation;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.Collection;
import java.util.List;

public final class ApacheClientImplementation implements MathApiClient {

    private static final String BASE_URI = ConfigProvider.INSTANCE.getServerUrl() + "/Calculator";

    @Override
    public Equation postEquation(Equation equation) {

        URI uri = URI.create(BASE_URI);

        final HttpPost request = new HttpPost(uri);
        request.setEntity(new StringEntity(new Gson().toJson(equation), ContentType.APPLICATION_JSON));

        return processRequest(request, Equation.class);
    }

    @Override
    public Equation getEquationById(int id) {

        URI uri = URI.create(BASE_URI + "/" + id);

        final HttpGet request = new HttpGet(uri);

        return processRequest(request, Equation.class);
    }

    @Override
    public List<Equation> getAllEquations() {
        return processRequest(new HttpGet(BASE_URI), getCollectionType(Equation.class));
    }

    @Override
    public void deleteEquation(int id) {
        processRequest(new HttpDelete(BASE_URI + "/" + id), null);
    }

    private <T> T processRequest(final HttpRequestBase request, final Type clazz)
    {
        try (CloseableHttpResponse response = HttpClientBuilder.create().build().execute(request)){
            if (response.getStatusLine().getStatusCode() != 200) {
                return null;
            }

            if (response.getEntity().getContentLength() > 0) {
                return new Gson().fromJson(new InputStreamReader(response.getEntity().getContent()), clazz);
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static Type getCollectionType(final Class elementType)
    {
        return TypeToken.getParameterized(Collection.class, elementType).getType();
    }
}
