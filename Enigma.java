public class Enigma{
	private Rotor[] rotors;
	private PackedReflector reflector;
	private Pattern pattern;

	public Enigma(String[] formatRotor, String formatReflector) throws IncorrectFormatException, RotorBoundsException, RotorIncompatibilityException{
		rotors=new Rotor[formatRotor.length];
		for(int i=0;i<formatRotor.length;i++){
			rotors[i]=new PackedRotor(formatRotor[i]);
		}
		reflector=new PackedReflector(formatReflector);
		for(int i=0;i<formatRotor.length-1;i++){
			if(!rotors[i].equals(rotors[i+1])){
				throw new RotorIncompatibilityException();
			}
		}
		if(!rotors[rotors.length-1].equals(reflector)){
			throw new RotorIncompatibilityException("Incorrect inputs: the rotors and the reflector chosen work on the same cypher.");
		}
		pattern=reflector.getPattern();
	}

	int goThroughRotors(int index, Rotor[] rotors){
		for(int i=0;i<rotors.length;i++){
			index=rotors[i].getCharacter(index);
		}
		index=reflector.getCharacter(index);
		for(int i=rotors.length-1;i>=0;i--){
			index=rotors[i].getInverseCharacter(index);
		}
		return index;
	}

	public String encode(String message){
		String code="";
		for(int i=0;i<message.length();i++){
			int index=pattern.sortedIndexOf(message.charAt(i));
			if(index!=-1){
				index=goThroughRotors(index, rotors);
				code+=pattern.getValueOf(index);
			}
			else{
				code+=message.charAt(i);
			}
			for(int j=0;j<rotors.length;j++){
				rotors[j].increaseAfterRotation();
				if(rotors[j].getAfterRotation()==rotors[j].getFrequency()){
					rotors[j].rotate();
				}
			}
			reflector.increaseAfterRotation();
			if(reflector.getAfterRotation()==reflector.getFrequency()){
				reflector.rotate();
			}
		}
		return code;
	}

	public Pattern getPattern(){
		return pattern;
	}

	Rotor[] getRotors(){
		return rotors;
	}

	PackedReflector getReflector(){
		return reflector;
	}
}