public class EnigmaProMax extends EnigmaPro{
    private int numberOfWorkingRotors;

    public EnigmaProMax(String[] formatRotor, String formatReflector, int numberOfWorkingRotors) throws IncorrectFormatException, RotorBoundsException, RotorIncompatibilityException, RequestException{
        super(formatRotor, formatReflector);
        if(getRotors().length<numberOfWorkingRotors){
            throw new RequestException("Invalid request: Number of working rotors can not be greater than the number of rotors.");
        }
        this.numberOfWorkingRotors=numberOfWorkingRotors;
    }

    public String encode(String message){
		String code="";
        String key="";
		for(int i=0;i<message.length();i++){
			int index=getPattern().sortedIndexOf(message.charAt(i));
			if(index!=-1){
                Rotor[] workingRotors=new Rotor[numberOfWorkingRotors];
                for(int j=0;j<numberOfWorkingRotors;j++){
                    int randomIndex=(int)(Math.random()*getRotors().length);
                    workingRotors[j]=getRotors()[randomIndex];
                    key+=randomIndex;
                }
                key+=":";
				index=goThroughRotors(index, workingRotors);
                if(index!=getChanger().getCharacter(index)){
                    index=getChanger().getCharacter(index);
                    index=goThroughRotors(index, getRotors());
                }
                code+=getPattern().getValueOf(index);
                for(int j=0;j<workingRotors.length;j++){
                    workingRotors[j].increaseAfterRotation();
                    if(workingRotors[j].getAfterRotation()==workingRotors[j].getFrequency()){
                        workingRotors[j].rotate();
                    }
                }
                getReflector().increaseAfterRotation();
                if(getReflector().getAfterRotation()==getReflector().getFrequency()){
                    getReflector().rotate();
                }
			}
			else{
				code+=message.charAt(i);
			}
		}
		return "Your code is:"+code+"\nYour key is:"+key.substring(0, key.length()-1);
	}

    public String decode(String code, String key) throws RequestException{
		String message="";
        String[] rotorIndexes=key.split(":");
        int count=0;
		for(int i=0;i<code.length();i++){
			int index=getPattern().sortedIndexOf(code.charAt(i));
			if(index!=-1){
                Rotor[] workingRotors=new Rotor[numberOfWorkingRotors];
                for(int j=0;j<numberOfWorkingRotors;j++){
                    if(rotorIndexes[count].charAt(j)<'0' || rotorIndexes[count].charAt(j)>'9'){
                        throw new RequestException("Invalide request: The input is not a key.");
                    }
                    workingRotors[j]=getRotors()[rotorIndexes[count].charAt(j)-'0'];
                }
				index=goThroughRotors(index, workingRotors);
                if(index!=getChanger().getCharacter(index)){
                    index=getChanger().getCharacter(index);
                    index=goThroughRotors(index, getRotors());
                }
                message+=getPattern().getValueOf(index);
                count++;
                for(int j=0;j<workingRotors.length;j++){
                    workingRotors[j].increaseAfterRotation();
                    if(workingRotors[j].getAfterRotation()==workingRotors[j].getFrequency()){
                        workingRotors[j].rotate();
                    }
                }
                getReflector().increaseAfterRotation();
                if(getReflector().getAfterRotation()==getReflector().getFrequency()){
                    getReflector().rotate();
                }
			}
			else{
				message+=code.charAt(i);
			}
		}
		return message;  
    }
}