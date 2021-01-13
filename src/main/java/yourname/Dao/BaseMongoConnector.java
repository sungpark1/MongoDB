package yourname.Dao;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.jongo.Jongo;
import org.jongo.Mapper;
import org.jongo.marshall.jackson.JacksonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("mongo")
public class BaseMongoConnector {

    protected final MongoClient client;
    protected final DB db;
    protected final MongoDatabase database;
    protected final Jongo jongo;

    // Constructor
    @Autowired
    public BaseMongoConnector(
            @Value("${spring.data.mongodb.port:27017}") int port,
            @Value("${spring.data.mongodb.database}") String database,
            @Value("${spring.data.mongodb.host:#{null}}") String host
    ) {
        this.client = new MongoClient(host, port);
        System.out.println("Created client successfully!");

        this.database = client.getDatabase(database);
        System.out.println("Accessing the database!");
        this.db = client().getDB("db");
        this.jongo = new Jongo(db);
    }

    protected Mapper makeJongoMapper() { return new JacksonMapper.Builder().build(); }

    public MongoClient client() { return client; }

    public DB db() { return db; }

    public MongoDatabase database() { return database; }

    protected Jongo jongo() { return jongo; }

}

