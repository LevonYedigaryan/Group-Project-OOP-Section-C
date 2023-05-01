public class PackedRotor extends Rotor{
	private long rotor;

	public PackedRotor(String format) throws IncorrectFormatException{
		super(format);
		try{
			getPattern().initialize(this);
		}
		catch(RotorBoundsException e){}
	}

	public PackedRotor(PackedRotor newRotor){
		super((Rotor) newRotor);
		try{
			getPattern().initialize(this);
		}
		catch(RotorBoundsException e){}
	}

	public PackedRotor(Pattern pattern){
		super(pattern);
		try{
			pattern.initialize(this);
		}
		catch(RotorBoundsException e){}
	}

	public void rotate(){
		long firstDigit=rotor;
		firstDigit>>=36;
		rotor=((rotor-(firstDigit<<36))<<4)+firstDigit+73300775185L;
		long check=rotor;
		for(int i=0;i<36;i++){
			if((check&15L)==10L){
				rotor^=(10L<<(i*4));
				break;
			}
			check>>=4;
		}
	}

	public int getCharacter(int index) throws RotorBoundsException{
		if(index>=0 && index<10){
			return (int)((rotor>>(index*4))&15L);
		}
		throw new RotorBoundsException("Incorrect bounds: requested index out of Rotor bounds("+index+" is not supported in rotor).");
	}

	void setCharacter(int index, int value) throws RotorBoundsException{
		if(index<0 && index>=10 && value>=0 && value<10){
			throw new RotorBoundsException("Incorrect bounds: requested index out of Rotor bounds("+index+" is not supported in rotor).");
		}
		if(index<0 && index>=10 && value<0 && value>=10){
			throw new RotorBoundsException("Incorrect bounds: requested index out of Rotor bounds("+value+" is not supported in rotor).");
		}
		long remainder=rotor&((long)Math.pow(2,index*4)-1);
		rotor-=rotor&((long)Math.pow(2,(2+1)*4)-1);
		rotor+=remainder+(value<<(index*4));
	}
}