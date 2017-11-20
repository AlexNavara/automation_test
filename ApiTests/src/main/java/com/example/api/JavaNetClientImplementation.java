package com.example.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public final class JavaNetClientImplementation implements MathApiClient {
    @Override
    public String evaluateExpression(String expression) {
        final StringBuilder response = new StringBuilder();

        try {
            URL url = new URL("http://localhost:8080/Calculator?" + expression.replaceAll(" ", ""));

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            connection.connect();

            final int responseCode = connection.getResponseCode();

            if (responseCode == 200) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                }
            } else {
                throw new RuntimeException("Server returned " + responseCode + " error");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response.toString();
    }
}
