package test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

//import static java.util.concurrent.TimeUnit.SECONDS;

public class MongoTest {
	public static void insertTest(DBCollection collection){
		List<DBObject> documents = new ArrayList<DBObject>();	
		DBObject document = new BasicDBObject();
		document.put("daily_open", 10);
		document.put("daily_high", 9);
		document.put("daily_low", 8);
		document.put("daily_close", 7);
		document.put("trading_volume", 6);
		collection.insert(document);
		//collection.insert(documents);
	}
	public static void main(String[] args){
		try {
			MongoClient mongo = new MongoClient();
			DB stockDB = mongo.getDB("stocks");
			DBCollection djia = stockDB.getCollection("DJIA");
			insertTest(djia);
			DBCursor c = djia.find();
			while(c.hasNext()){
				System.out.println(c.next());
			}
			List<String> dbs = mongo.getDatabaseNames();
			for(String db : dbs){
				System.out.println(db);
			}
		}catch (UnknownHostException e) {
			System.out.println("Database is not online.");
		}
	}
}
