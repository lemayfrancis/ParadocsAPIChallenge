package com.lemayfrancis.infrastructure.Trail;

import com.lemayfrancis.domain.Trail.Trail;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import java.util.UUID;

public class TrailAdaptor {
  public static DBObject toDBObject(Trail trail) {
    return new BasicDBObject("idTrail", trail.getIdTrail().toString())
        .append("name", trail.getName())
        .append("description", trail.getDescription());
  }

  public static DBObject toDBQuery(UUID idTrail) {
    return new BasicDBObject("idTrail", idTrail.toString());
  }

  public static Trail toTrail(DBObject dbObject) {
    UUID idTrail = UUID.fromString(dbObject.get("idTrail").toString());
    String name = dbObject.get("name").toString();
    String description = dbObject.get("description").toString();
    return new Trail(idTrail, name, description);
  }
}
