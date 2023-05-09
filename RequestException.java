public class RequestException extends Exception{
	public RequestException(){
		super("Incorrect request: changer does not posess the values requested.");
	}
	public RequestException(String message){
		super(message);
	}
}