package br.com.ucommex.resourceserver.pojo;

import org.bson.types.ObjectId;

public class ObjectDB {
    protected ObjectId id;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}
