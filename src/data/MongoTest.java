package data;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Set;

import static java.util.concurrent.TimeUnit.SECONDS;

public class MongoTest {
	public static void main(String[] args){
		try {
			MongoClient mongo = new MongoClient("localhost",27017);
			//DB db = mongo.getDB("test");
			List<String> dbs = mongo.getDatabaseNames();
			for(String db : dbs){
				System.out.println(db);
			}
		}catch (UnknownHostException e) {
			System.out.println("Database is not online.");
		}
	}
}
