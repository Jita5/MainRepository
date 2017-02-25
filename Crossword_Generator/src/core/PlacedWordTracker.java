package core;

public class PlacedWordTracker {
	private int number;
	private Coordinate coordinate;
	private String word;
	
	public PlacedWordTracker(Coordinate coordinate, String word){
		this.coordinate = coordinate;
		this.word = word;
	}
	
	public int getNumber(){
		return number;
	}
	public void setNumber(int number){
		this.number = number;
	}
	public Coordinate getCoordinate(){
		return coordinate;
	}
	public String getWord(){
		return word;
	}
	public String getTopic(){
		if (Config.InputSource != 0)
			throw new UnsupportedOperationException("User-input words have no topics");
		
		return Config.FindBigWordInFilteredBigWords(word).getTopic();
	}
}
