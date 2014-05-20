@SuppressWarnings("serial")
public class LoadFailException extends Exception {
	//Parameterless Constructor
	public LoadFailException() {}
	
	//Constructor that accepts a message
	public LoadFailException(String message) {
		super(message);
	}
}
