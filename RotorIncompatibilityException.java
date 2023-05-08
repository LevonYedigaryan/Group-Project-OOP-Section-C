public class RotorIncompatibilityException extends Exception{
	public RotorIncompatibilityException(){
		super("Incorrect inputs: the rotors chosen must work on the same cypher.");
	}
	public RotorIncompatibilityException(String message){
		super(message);
	}
}