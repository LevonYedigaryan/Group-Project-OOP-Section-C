import java.util.ArrayList;

public class PackedRotor extends Rotor{
	private ArrayList<Long> rotor=new ArrayList<>(1);
	private int oneCharacterSpace=0;
	private int maximalIndex;
	private int oneSlotCharacterNumber;
	private int freeSpace;
	private int lastRowFreeSpace;
	private long fullCharacter;
	private final int ONEMEMORYSLOT=64;
	

	public PackedRotor(String format) throws IncorrectFormatException, RotorBoundsException{
		super(format);
		getPattern().initialize(this);
		maximalIndex=getPattern().getCypher().length()-1;
		while((1L<<(oneCharacterSpace))<maximalIndex){
			oneCharacterSpace++;
		}
		oneSlotCharacterNumber=ONEMEMORYSLOT/oneCharacterSpace;
		freeSpace=ONEMEMORYSLOT%oneCharacterSpace;
		fullCharacter=(1L<<oneCharacterSpace)-1;
		while(rotor.size()*oneSlotCharacterNumber<maximalIndex+1){
			rotor.add(0L);
		}
		lastRowFreeSpace=ONEMEMORYSLOT-(maximalIndex%oneSlotCharacterNumber+1)*oneCharacterSpace;
		getPattern().setSettings(this);
	}

	public PackedRotor(PackedRotor rotor) throws IncorrectFormatException, RotorBoundsException{
		this(rotor.getPattern());
	}

	public PackedRotor(Pattern pattern) throws IncorrectFormatException, RotorBoundsException{
		this(pattern.toString());
	}
	
	int getMaximalIndex(){
		return maximalIndex;
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
			long lastDigit;
			long passToNext=(rotor.get(rotor.size()-1)<<(ONEMEMORYSLOT-(lastRowFreeSpace+oneCharacterSpace)))&((fullCharacter)<<(ONEMEMORYSLOT-oneCharacterSpace));
			for(int j=0;j<rotor.size();j++){
				lastDigit=(rotor.get(j)>>>freeSpace)&(fullCharacter);
				rotor.set(j,(rotor.get(j)>>>oneCharacterSpace)+passToNext);
				passToNext=lastDigit<<(oneCharacterSpace*(oneSlotCharacterNumber-1)+freeSpace);
				int bound=freeSpace;
				if(j==rotor.size()-1){
					bound=lastRowFreeSpace;
				}
				for(int k=ONEMEMORYSLOT-oneCharacterSpace;k>=bound;k-=oneCharacterSpace){
					if(((rotor.get(j)>>>k)&(fullCharacter))==maximalIndex){
						rotor.set(j,rotor.get(j)^((long)maximalIndex)<<k);
					}
					else{
						rotor.set(j,rotor.get(j)+(1L<<k));
					}
				}
			}
		}
	}

	public int getCharacter(int index){
		if(index>=0 && index<=maximalIndex){
			long row=rotor.get(index/oneSlotCharacterNumber);
			row>>>=ONEMEMORYSLOT-(index%oneSlotCharacterNumber+1)*oneCharacterSpace;
			return (int)(row&(fullCharacter));
		}
		return -1;
	}

	public int getInverseCharacter(int index){
		for(int i=0;i<rotor.size();i++){
			int bound=freeSpace;
			if(i==rotor.size()-1){
				bound=lastRowFreeSpace;
			}
			for(int j=ONEMEMORYSLOT-oneCharacterSpace;j>=bound;j-=oneCharacterSpace){
				if((fullCharacter&(rotor.get(i)>>>j))==index){
					return i*oneSlotCharacterNumber+(ONEMEMORYSLOT-j-1)/oneCharacterSpace;
				}
			}
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
		int row=index/oneSlotCharacterNumber;
		long remainder=rotor.get(row)&((1L<<(ONEMEMORYSLOT-(index%oneSlotCharacterNumber+1)*oneCharacterSpace))-1);
		long newValue=rotor.get(row)-(rotor.get(row)&((1L<<(ONEMEMORYSLOT-(index%oneSlotCharacterNumber)*oneCharacterSpace))-1));
		rotor.set(row,newValue+remainder);
		newValue=rotor.get(row)+(((long)value)<<(ONEMEMORYSLOT-(index%oneSlotCharacterNumber+1)*oneCharacterSpace));
		rotor.set(row, newValue);
	}
}