public class PackedReflector extends PackedRotor{
	public PackedReflector(String format) throws IncorrectFormatException, RotorBoundsException{
		super(format);
		String cypher=getPattern().getCypher();
		for(int i=0;i<=getMaximalIndex();i++){
			if(i!=getPattern().sortedIndexOf(cypher.charAt(getPattern().sortedIndexOf(cypher.charAt(i))))){
				throw new IncorrectFormatException("Incorrect format: The format does not correspond to the one of a reflector.");
			}
		}
	}

	public PackedReflector(PackedReflector reflector) throws IncorrectFormatException, RotorBoundsException{
		this(reflector.getPattern());
	}

	public PackedReflector(Pattern pattern) throws IncorrectFormatException, RotorBoundsException{
		this(pattern.toString());
	}
}