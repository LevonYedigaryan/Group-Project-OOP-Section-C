public class EnigmaPro extends Enigma{
    public class ChangeBoard{
        private String changer="";
        private Pattern pattern;

        public ChangeBoard(String format, String configuration) throws IncorrectFormatException, RequestException{
            this.pattern=new Pattern(format);
            if(configuration.length()%2!=0){
                throw new RequestException("Wrong request: configuration must be even.");
            }
            for(int i=0;i<configuration.length();i++){
                for(int j=i+1;j<configuration.length();j++){
                    if(configuration.charAt(i)==configuration.charAt(j)){
                        throw new RequestException("Wrong request: There must be no repetative characters in a configuration.");
                    }
                }
                if(pattern.sortedIndexOf(configuration.charAt(i))<0){
                    throw new RequestException("Wrong request: COnfiguration given does not correspond to the Pattern.");
                }
            }
            changer=configuration;
        }

        public ChangeBoard(ChangeBoard changeboard, String configuration) throws IncorrectFormatException, RequestException{
            this(changeboard.getPattern(), configuration);
        }

        public ChangeBoard(Pattern pattern, String configuration) throws IncorrectFormatException, RequestException{
            this(pattern.toString(), configuration);
        }

        public Pattern getPattern(){
            return pattern;
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

    private ChangeBoard changer;

    public EnigmaPro(String[] formatRotor, String formatReflector, String configuration) throws IncorrectFormatException, RotorBoundsException, RotorIncompatibilityException, RequestException{
        super(formatRotor, formatReflector);
        changer=new ChangeBoard(getPattern(), configuration);
    }

    ChangeBoard getChanger(){
        return changer;
    }

    public String encode(String message){
		String code="";
		for(int i=0;i<message.length();i++){
			int index=getPattern().sortedIndexOf(message.charAt(i));
			if(index!=-1){
                index=changer.getCharacter(index);
				index=goThroughRotors(index, getRotors());
                index=changer.getCharacter(index);
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