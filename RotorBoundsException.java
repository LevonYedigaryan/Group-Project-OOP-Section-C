public class RotorBoundsException extends Exception{
	public RotorBoundsException(){
		super("Incorrect bounds: requested index out of Rotor bounds.");
	}
	public RotorBoundsException(String message){
		super(message);
	}
}