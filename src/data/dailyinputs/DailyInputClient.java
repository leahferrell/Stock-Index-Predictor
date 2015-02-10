package data.dailyinputs;
import data.entities.*;

public interface DailyInputClient {
	
	public void connectToServer();
	
	public void connectToServer(String serverLocation);
	
	public InputVector getInputForIndex();
	
	public InputVector getInputForIndex(String date);
	
	public InputVector getInputForIndex(SimpleDate date);
	
	public void disconnect();
}
