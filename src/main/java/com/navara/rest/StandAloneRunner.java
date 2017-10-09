package com.navara.rest;


import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

public class StandAloneRunner {

    private static HttpServer startServer() {
        final String url = "http://localhost:1234";
        final ResourceConfig resourceConfig = new ResourceConfig().packages("com.navara.rest.links");
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(url), resourceConfig);
    }

    public static void main(String[] args) throws IOException {
        HttpServer server = startServer();
        System.out.println("Server started. Press 'Enter' to stop.");
        System.in.read();
        server.shutdown();
    }

}
