import java.util.Arrays;

public class Pattern{
	private String name;
	private int startingPosition;
	private String cypher;
	private String sorted="";

	public Pattern(String format) throws IncorrectFormatException{
		String[] tokens=format.split(":");
		if(tokens.length>3){
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
		try{
			cypher=tokens[2];
		}
		catch(ArrayIndexOutOfBoundsException e) {
			throw new IncorrectFormatException("Invalid format: Incorrect number of fields in pattern (found "+tokens.length+").");
		}
		if(cypher.indexOf(startingPosition)<0){
			throw new IncorrectFormatException("Invalid format: Starting position and cypher incompatibility");
		}
		for(int i=0;i<cypher.length();i++){
			int count=0;
			for(int j=i+1;j<cypher.length();j++){
				if(cypher.charAt(j)==cypher.charAt(i)){
					if(j==i+1){
						throw new IncorrectFormatException("Invalid format: "+cypher.charAt(i)+" cannot be converted to "+cypher.charAt(i)+".");
					}
					count++;
				}
				if(count>3){
					throw new IncorrectFormatException("Invalid format: "+cypher.charAt(i)+" occurs more than twice.");
				}
			}
			if(count==2){
				int j;
				for(j=i+1;cypher.charAt(j)!=cypher.charAt(i);j++){
					for(int k=j+1;k<cypher.length();k++){
						if(cypher.charAt(j)==cypher.charAt(k)){
							throw new IncorrectFormatException("Invalid format: "+cypher.charAt(j)+" can not occur in two places.");
						}
					}
				}
				i=j;
			}
		}
		String[] sortedArray=cypher.split("");
		Arrays.sort(sortedArray);
		for(int i=0;i<sortedArray.length;i++){
			if(sortedArray[i]!=sortedArray[i-1]){
				sorted+=sortedArray[i];
			}
		}
	}

	public String getName(){
		return name;
	}

	public int getStartingPosition(){
		return startingPosition;
	}

	public String getCypher(){
		return cypher;
	}

	public final String toString() {
		return name+":"+startingPosition+":"+cypher;
	}

	public void initialize(Rotor rotor) throws RotorBoundsException{
		for(int i=0;i<sorted.length();i++){
			int j=cypher.indexOf(sorted.charAt(i)+"")+1;
			if(j>=cypher.length()){
				rotor.setCharacter(i,sorted.indexOf(cypher.charAt(0)+""));
			}
			else{
				rotor.setCharacter(i,sorted.indexOf(""+cypher.charAt(cypher.indexOf(sorted.charAt(i)+"")+1)));
			}
		}
		for(int i=0;i<startingPosition;i++){
			rotor.rotate();
		}
	}
}