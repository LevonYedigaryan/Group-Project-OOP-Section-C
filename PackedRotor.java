public class PackedRotor extends Rotor{
	private long rotor;
	private long toAddRotation=1;
	private int oneCharacterSpace=1;
	private int maximalIndex;
	private final int MEMORYLENGTH=64;
	

	public PackedRotor(String format) throws IncorrectFormatException, RotorBoundsException{
		super(format);
		getPattern().initialize(this);
		maximalIndex=getPattern().getCypher().length()-1;
		while((1L<<(oneCharacterSpace-1))<maximalIndex){
			oneCharacterSpace++;
		}
		if(MEMORYLENGTH/oneCharacterSpace<oneCharacterSpace){
			throw new IncorrectFormatException("Incorrct format: No enough memory to store the cypher.");
		}
		for(int i=0;i<maximalIndex;i++){
			toAddRotation<<=(oneCharacterSpace+1);
		}
	}

	int getMaximalIndex(){
		return maximalIndex;
	}

	public PackedRotor(PackedRotor rotor) throws IncorrectFormatException, RotorBoundsException{
		this(rotor.getPattern());
	}

	public PackedRotor(Pattern pattern) throws IncorrectFormatException, RotorBoundsException{
		this(pattern.toString());
	}

	int getOneCharacterSpace(){
		return oneCharacterSpace;
	}

	void setOneCharacterSpace(int space){
		oneCharacterSpace=space;
	}

	public void rotate(){
		resetAfterRotation();
		for(int i=0;i<getStep();i++){
			long firstDigit=rotor;
			firstDigit>>=maximalIndex*oneCharacterSpace;
			rotor=(rotor<<oneCharacterSpace)+firstDigit+toAddRotation;
			for(int j=0;j<=maximalIndex*oneCharacterSpace;j+=oneCharacterSpace){
				if(((rotor>>(j*oneCharacterSpace))&((1L<<oneCharacterSpace)-1))==maximalIndex){
					rotor^=(((long)maximalIndex)<<(j*oneCharacterSpace));
					break;
				}
			}
		}
		
	}

	public int getCharacter(int index){
		if(index>=0 && index<getPattern().getCypher().length()){
			return (int)((rotor>>(index*oneCharacterSpace))&((1<<oneCharacterSpace)-1));
		}
		return -1;
	}

	public boolean equals(Object object){
		if(object==null){
			return false;
		}
		else if(!(object instanceof PackedRotor)){
			return false;
		}
		PackedRotor comparable=(PackedRotor) object;
		return this.getPattern().getSorted().equals(comparable.getPattern().getSorted());
	}

	void setCharacter(int index, int value) throws RotorBoundsException{
		if(index<0 && index>maximalIndex){
			throw new RotorBoundsException("Incorrect bounds: requested index out of Rotor bounds("+index+" is not supported in rotor).");
		}
		if(value<0 && value>maximalIndex){
			throw new RotorBoundsException("Incorrect bounds: requested index out of Rotor bounds("+value+" is not supported in rotor).");
		}
		long remainder=rotor&((1L<<(index*oneCharacterSpace))-1);
		rotor-=rotor&((1L<<((index+1)*oneCharacterSpace))-1);
		rotor+=remainder+((long)value<<(index*oneCharacterSpace));
	}
}