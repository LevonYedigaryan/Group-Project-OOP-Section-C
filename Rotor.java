public abstract class Rotor{
	private Pattern pattern;
	private int step;
	private int frequency;
	private int afterRotation=0;

	public Rotor(String format) throws IncorrectFormatException{
		pattern=new Pattern(format);
	}

	public Rotor(Pattern pattern) throws IncorrectFormatException{
		this.pattern=pattern;
	}

	public Rotor(Rotor rotor) throws IncorrectFormatException{
		this(rotor.getPattern());
	}

	public abstract void rotate();

	public abstract int getCharacter(int index);

	abstract void setCharacter(int index, int value) throws RotorBoundsException;

	public int getStep(){
		return step;
	}

	public void setStep(int step){
		this.step=step;
	}

	public int getFrequency(){
		return frequency;
	}

	public void setFrequency(int frequency){
		this.frequency=frequency;
	}

	public int getAfterRotation(){
		return afterRotation;
	}

	public void increaseAfterRotation(){
		afterRotation++;
	}

	public void resetAfterRotation(){
		afterRotation=0;
	}

	public Pattern getPattern(){
		return pattern;
	}

	public void setPattern(Pattern pattern){
		this.pattern=pattern;
	}
}