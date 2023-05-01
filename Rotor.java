public abstract class Rotor{
	private Pattern pattern;

	public Rotor(String format) throws IncorrectFormatException{
		pattern=new Pattern(format);
	}

	public Rotor(Pattern pattern){
		this.pattern=pattern;
	}

	public Rotor(Rotor rotor){
		pattern=rotor.getPattern();
	}

	public abstract void rotate();

	public abstract int getCharacter(int index) throws RotorBoundsException;

	abstract void setCharacter(int index, int value) throws RotorBoundsException;

	public Pattern getPattern(){
		return pattern;
	}

	public void setPattern(Pattern pattern){
		this.pattern=pattern;
	}
}