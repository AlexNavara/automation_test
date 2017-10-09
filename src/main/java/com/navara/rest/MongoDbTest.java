package com.navara.rest;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BSON;
import org.bson.Document;

public class MongoDbTest {

    public static void main(String[] args) {
        MongoClientOptions options = MongoClientOptions.builder()
                .connectionsPerHost(10)
                .build();
        MongoClient mongoClient = new MongoClient(new ServerAddress("localhost", 27017), options);
        MongoDatabase db = mongoClient.getDatabase("navara");
        MongoCollection<Document> groups = db.getCollection("groups");

        for (Document doc : groups.find()) {
            System.out.println(doc);
        }
    }

}
