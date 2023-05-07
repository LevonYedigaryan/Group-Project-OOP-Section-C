public class Enigma{
	private Rotor[] rotors;
	private PackedReflector reflector;
	private Pattern pattern;

	public Enigma(String[] formatRotor, String formatReflector){
		try{
			rotors=new Rotor[formatRotor.length];
			for(int i=0;i<formatRotor.length;i++){
				rotors[i]=new PackedRotor(formatRotor[i]);
			}
			reflector=new PackedReflector(formatReflector);
			for(int i=0;i<formatRotor.length-1;i++){
				if(!rotors[i].equals(rotors[i+1])){
					System.out.println("kakadu");
				}
			}
			if(!rotors[rotors.length-1].equals(reflector)){
				System.out.println("kakadu");
			}
			pattern=reflector.getPattern();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	public String encode(String message){
		String code="";
		for(int i=0;i<message.length();i++){
			int index=pattern.sortedIndexOf(message.charAt(i));
			if(index!=-1){
				for(int j=0;j<rotors.length;j++){
					index=rotors[j].getCharacter(index);
				}
				index=reflector.getCharacter(index);
				for(int j=rotors.length-1;j>=0;j--){
					index=rotors[j].getCharacter(index);
				}
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
}