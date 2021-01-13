package yourname.Dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.jongo.*;
import org.jongo.MongoCollection;

import javax.annotation.PostConstruct;


public abstract class AbstractMongoDao {
    private String collectionName;
    private BaseMongoConnector mongoConnector;

    public AbstractMongoDao(
            String collectionName,
            BaseMongoConnector mongoConnector
    ) {
        this.collectionName = collectionName;
        this.mongoConnector = mongoConnector;
    }

    @PostConstruct
    protected void init(){
        try {
            runIndexes();

        } catch (Exception e){

        }
    }

    protected void runIndexes(){}

    protected void createIndex(DBObject keys, DBObject options) {
        collection().getDBCollection().createIndex(keys, options);
    }

    protected void createIndex(DBObject keys) {
        createIndex(keys, makeDefaultIndexOptions());
    }

    protected DBObject makeDefaultIndexOptions() {
        return new BasicDBObject("background", true);
    }

    protected void createBasicIndex(String... fields){
        DBObject keyObject = new BasicDBObject();
        for (String field : fields){
            keyObject.put(field, 1);
        }
        createIndex(keyObject);
    }

    protected void dropLegacyIndex(String name){
        try {
            collection().getDBCollection().dropIndex(name);
        } catch (Exception e){

        }
    }

    public String getCollectionName(){
        return collectionName;
    }

    protected BaseMongoConnector mongoConnector(){
        return mongoConnector;
    }

    protected MongoClient client(){
        return mongoConnector().client();
    }

    protected MongoCollection collection(){
        return jongo().getCollection(collectionName);
    }

    protected MongoDatabase database(){ return mongoConnector().database(); }

    protected DB db(){
        return mongoConnector().db();
    }

    protected Jongo jongo(){
        return mongoConnector().jongo();
    }

}