import java.util.Arrays;

public class Pattern implements Comparable<Pattern>{
	private String name;
	private int startingPosition;
	private int step=1;
	private int frequency=1;
	private String cypher;
	private String sorted="";

	public Pattern(String format) throws IncorrectFormatException{
		String[] tokens=format.split(":");
		int cypherIndex=2;
		if(tokens.length>5){
			throw new IncorrectFormatException("Incorrect format: Incorrect number of fields in pattern (found "+tokens.length+").");
		}
		try{
			name=tokens[0];
		}
		catch(ArrayIndexOutOfBoundsException e){
			throw new IncorrectFormatException();
		}
		try{
			startingPosition=Integer.parseInt(tokens[1]);
		}
		catch(ArrayIndexOutOfBoundsException e){
			throw new IncorrectFormatException("Incorrect format: Incorrect number of fields in pattern (found "+tokens.length+").");
		}
		catch(NumberFormatException e){
			throw new IncorrectFormatException("Incorrect format: Must be a number on the second field (found "+tokens[1]+").");
		}
		if(tokens.length==4){
			throw new IncorrectFormatException("Incorrect format: Incorrect number of fields in pattern (found "+tokens.length+").");
		}
		if(tokens.length==5){
			try{
				step=Integer.parseInt(tokens[2]);
			}
			catch(NumberFormatException e){
				throw new IncorrectFormatException("Incorrect format: Must be a number on the third field (found "+tokens[2]+").");
			}
			try{
				frequency=Integer.parseInt(tokens[3]);
			}
			catch(NumberFormatException e){
				throw new IncorrectFormatException("Incorrect format: Must be a number on the fourth field (found "+tokens[3]+").");
			}
			cypherIndex=4;
		}
		try{
			cypher=tokens[cypherIndex];
		}
		catch(ArrayIndexOutOfBoundsException e){
			throw new IncorrectFormatException("Invalid format: Incorrect number of fields in pattern (found "+tokens.length+").");
		}
		if(startingPosition>=cypher.length()){
			throw new IncorrectFormatException("Invalid format: Starting position must be smaller than the number of characters cyphered.");
		}
		String[] sortedArray=cypher.split("");
		Arrays.sort(sortedArray);
		for(int i=0;i<sortedArray.length;i++){
			sorted+=sortedArray[i];
		}
		for(int i=0;i<cypher.length();i++){
			if(i==sortedIndexOf(cypher.charAt(i))){
				throw new IncorrectFormatException("Invalid format: "+cypher.charAt(i)+" must not go to itself.");
			}
			for(int j=i+1;j<cypher.length();j++){
				if(cypher.charAt(i)==cypher.charAt(j)){
					throw new IncorrectFormatException("Invalid format: "+cypher.charAt(i)+" can only occur once.");
				}
			}
		}
	}

	public int sortedIndexOf(char character){
		int mid=(sorted.length()-1)/2;
		int start=0, end=sorted.length()-1;
		while(sorted.charAt(mid)!=character){
			if(character>sorted.charAt(mid)){
				start=mid+1;
			}
			else{
				end=mid-1;
			}
			mid=(start+end)/2;
			if(mid==end && (sorted.charAt(mid)!=character)){
				return -1;
			}
		}
		return mid;
	}

	public char getValueOf(int index){
		return sorted.charAt(index);
	}

	public String getName(){
		return name;
	}

	public int getStartingPosition(){
		return startingPosition;
	}

	public int getStep(){
		return step;
	}

	public int getFrequency(){
		return frequency;
	}

	public String getCypher(){
		return cypher;
	}

	public String getSorted(){
		return sorted;
	}

	public void initialize(Rotor rotor) throws RotorBoundsException{
		rotor.setPattern(this);
		rotor.setStep(step);
		rotor.setFrequency(frequency);
	}

	public void setSettings(Rotor rotor) throws RotorBoundsException{
		for(int i=0;i<sorted.length();i++){
			rotor.setCharacter(i,sortedIndexOf(cypher.charAt(i)));
		}
		for(int i=0;i<startingPosition;i++){
			rotor.rotate();
		}
	}

	public String toString(){
		return name+":"+startingPosition+":"+step+":"+frequency+":"+cypher;
	}

	public int compareTo(Pattern pattern){
		return this.toString().compareTo(pattern.toString());
	}
}