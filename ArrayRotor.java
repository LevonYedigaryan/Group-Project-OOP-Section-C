public class ArrayRotor extends Rotor{
	//instance variables 
	private String [] rotor;

	//constructors
	public ArrayRotor(String format) throws IncorrectFormatException {
		super(format);
		getPattern().initialize(this);
	}

	public ArrayRotor (ArrayRotor arrayRotor){
		super((Rotor) arrayRotor);
		getPattern().initialize(this);
	}

	public ArrayRotor(Pattern pattern){
		super(pattern);
		pattern.initialize(this);
	}

	public void rotate(){
		String firstString = rotor[0];
		for (int i = 0; i < rotor.length - 1; i++){
			rotor[i] = rotor[i+1];
		}
		rotor[rotor.length - 1] = firstString;	
	}

	public String getCharacter(int index) throws RotorBoundsException{
		if(index >= 0 && index < rotor.length){
			return rotor[index];
		}
		throw new RotorBoundsException("Incorrect bounds: requested index out of Rotor bounds(" + index + " is not supported in rotor).");
	} 

	public void setCharacter (int index, int value) throws RotorBoundsException{
		if (index < 0 || index >= rotor.length || value.length() != 1){
			throw new RotorBoundsException ("Incorrect bounds: requested index out of Rotor bounds or value is not a single character.");
		}
		rotor[index] = value;
	} 
}