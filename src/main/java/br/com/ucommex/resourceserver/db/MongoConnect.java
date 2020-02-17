package br.com.ucommex.resourceserver.db;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoConnect {

    private static MongoClient mongoClient;

    //@formatter:off
    private MongoConnect() { };
    //@formatter:on

    public static MongoClient getInstance() {
        if (mongoClient != null)
            return mongoClient;
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        return mongoClient;
    }

    public static MongoDatabase getDatabase() {
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        MongoDatabase database = getInstance().getDatabase("ucommex").withCodecRegistry(pojoCodecRegistry);
        return database;
    }

}
