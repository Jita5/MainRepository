package core;

import java.util.ArrayList;

public interface IAPI {
	public boolean IsNullOrEmpty(String param);
	public Boolean LeftIsEmpty(int r, int c);
	public Boolean RightIsEmpty(int r, int c);
	public Boolean TopIsEmpty(int r, int c);
	public Boolean BottomIsEmpty(int r, int c);
	public Boolean MatchingOrEmpty(int r, int c, String character);
	public ArrayList<Coordinate> FindMatchingCoordinates(String character);
	public String[][] GetGrid();
	public int GetPlacedWordCount();
	public Boolean CoordinateInBounds(int r, int c);
	public Boolean TestPlaceWordDown(int r, int c, String word, int offset);
	public Boolean TestPlaceWordRight(int r, int c, String word, int offset);
	public void PlaceWordDown(int r, int c, String word);
	public void PlaceWordRight(int r, int c, String word);
	public void GenerateCrosswordPuzzle();
	public String GetCrosswordGridHtml(Boolean generateSolution);
	public String GetCrosswordHtml(Boolean includeSolution, Boolean cluesByTopic);
}