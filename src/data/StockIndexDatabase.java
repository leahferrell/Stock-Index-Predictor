package data;

import java.net.UnknownHostException;

import com.mongodb.MongoClient;

public class StockIndexDatabase {
	private MongoClient mongo;
	private String host = "localhost";
	private int port = 27017;
	StockIndexDatabase() throws UnknownHostException{
		mongo = new MongoClient(host, port);
	}
	public void initialCreate(){
		
	}
}
