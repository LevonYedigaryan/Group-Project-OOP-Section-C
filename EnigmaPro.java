public class EnigmaPro extends Enigma{
    public class ChangeBoard{
        private String changer="";
        private Pattern pattern;

        public ChangeBoard(String format) throws IncorrectFormatException{
            this.pattern=new Pattern(format);
        }

        public ChangeBoard(ChangeBoard changeboard) throws IncorrectFormatException{
            this(changeboard.getPattern());
        }

        public ChangeBoard(Pattern pattern) throws IncorrectFormatException{
            this.pattern=pattern;
        }

        public Pattern getPattern(){
            return pattern;
        }
    
        public void changeCharacters(char a, char b){
            for(int i=0;i<changer.length();i++){
                if(changer.charAt(i)==a){
                    if(i%2==0){
                        changer=changer.substring(0, i)+changer.substring(i+2)+a+b;
                        break;
                    }
                    else{
                        changer=changer.substring(0, i-1)+changer.substring(i+1)+a+b;
                        break;
                    }
                }
            }
        }

        public int getCharacter(int index){
            char a=pattern.getSorted().charAt(index);
            for(int i=0;i<changer.length();i++){
                if(changer.charAt(i)==a){
                    if(i%2==0){
                        return pattern.sortedIndexOf(changer.charAt(i+1));
                    }
                    else{
                        return pattern.sortedIndexOf(changer.charAt(i-1));
                    }
                }
            }
            return index;
        }
    }

    private int maximalIndex;
    private ChangeBoard changer;

    public EnigmaPro(String[] formatRotor, String formatReflector) throws IncorrectFormatException, RotorBoundsException, RotorIncompatibilityException{
        super(formatRotor, formatReflector);
        changer=new ChangeBoard(getPattern());
        maximalIndex=this.getPattern().getCypher().length()-1;
    }

    ChangeBoard getChanger(){
        return changer;
    }

    public void changeCharacters(char a, char b) throws RequestException{
        if(0<=getPattern().sortedIndexOf(a) && getPattern().sortedIndexOf(a)<=maximalIndex && 0<=getPattern().sortedIndexOf(b) && getPattern().sortedIndexOf(b)<=maximalIndex){
            changer.changeCharacters(a, b);
        }
        else{
            throw new RequestException();
        }
    }

    public String encode(String message){
		String code="";
		for(int i=0;i<message.length();i++){
			int index=getPattern().sortedIndexOf(message.charAt(i));
			if(index!=-1){
				index=goThroughRotors(index, getRotors());
                if(index!=changer.getCharacter(index)){
                    index=changer.getCharacter(index);
                    index=goThroughRotors(index, getRotors());
                }
                code+=getPattern().getValueOf(index);
                for(int j=0;j<getRotors().length;j++){
                    getRotors()[j].increaseAfterRotation();
                    if(getRotors()[j].getAfterRotation()==getRotors()[j].getFrequency()){
                        getRotors()[j].rotate();
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
		return code;
	}
}