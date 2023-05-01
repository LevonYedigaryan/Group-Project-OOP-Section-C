public class IncorrectFormatException extends Exception{
	public IncorrectFormatException(){
		super("Incorrect format: Incorrect number of fields in pattern (found 0.)");
	}
	public IncorrectFormatException(String message){
		super(message);
	}
}