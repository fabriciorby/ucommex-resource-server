package br.com.ucommex.resourceserver.db;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.util.StringUtils;

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
        String mongoURI = StringUtils.isEmpty(System.getenv("MONGODB_URI")) ?
                "mongodb://localhost:27017" : System.getenv("MONGODB_URI");
        mongoClient = MongoClients.create(mongoURI);
        return mongoClient;
    }

    public static MongoDatabase getDatabase() {
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        String databaseName = StringUtils.isEmpty(System.getenv("MONGODB_NAME")) ?
                "ucommex" : System.getenv("MONGODB_NAME");
        MongoDatabase database = getInstance().getDatabase(databaseName).withCodecRegistry(pojoCodecRegistry);
        return database;
    }

}
