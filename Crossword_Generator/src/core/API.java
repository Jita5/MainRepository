package core;

import java.util.*;

public class API implements IAPI {

	/**
	 * The Crossword Puzzle Grid
	 */
	private String[][] Grid;

	/**
	 * The Words List
	 */
	private ArrayList<String> Words;
	
	/**
	 * Placed Words Going Right
	 */
	private ArrayList<String> PlacedWordsRight;
	private ArrayList<PlacedWordTracker> TrackWordsRight = new ArrayList<PlacedWordTracker>();
	
	/**
	 * Placed Words Going Down
	 */
	private ArrayList<String> PlacedWordsDown;
	private ArrayList<PlacedWordTracker> TrackWordsDown = new ArrayList<PlacedWordTracker>();
	
	/**
	 * Helps assign numbers to cells for clues
	 */
	private int PlacedWordNumber = 1;
	
	private Map<Coordinate, List<PlacedWordTracker>> coordinateMap;

	/**
	 * IsNullOrEmpty - tests if a string is null or empty
	 */
	public boolean IsNullOrEmpty(String param) {
		return param == null || param.trim().length() == 0;
	}
	

	// ---------------
	// --constructor--
	// ---------------

	public API(int gridRows, int gridColumns, int inputSource,
			int minWordsPlaced, int numPuzzlesToGen) {
		Config.GridRows = gridRows;
		Config.GridColumns = gridColumns;
		Config.InputSource = inputSource;
		Config.MinWordsPlaced = minWordsPlaced;
		Config.NumPuzzlesToGenerate = numPuzzlesToGen;

		Grid = new String[Config.GridRows][Config.GridColumns];
		
		PlacedWordsRight = new ArrayList<String>();
		PlacedWordsDown = new ArrayList<String>();
		coordinateMap = new HashMap<Coordinate, List<PlacedWordTracker>>();
		
		if(inputSource == 0){
			Words = new ArrayList<String>(Config.GetFilteredRandomWordsList());
		}
		else{
			Words = new ArrayList<String>(Config.GetManualWordsList());
		}
	}

	/**
	 * LeftIsEmpty - Tests if the left cell is empty
	 */
	public Boolean LeftIsEmpty(int r, int c) {
		if (c > 0) {
			return IsNullOrEmpty(Grid[r][c - 1]);
		}
		return true;
	}

	/**
	 * RightIsEmpty - Tests if the right cell is empty
	 */
	public Boolean RightIsEmpty(int r, int c) {
		if (c < Config.GridColumns - 1) {
			return IsNullOrEmpty(Grid[r][c + 1]);
		}
		return true;
	}

	/**
	 * TopIsEmpty - Tests if the top cell is empty
	 */
	public Boolean TopIsEmpty(int r, int c) {
		if (r > 0) {
			return IsNullOrEmpty(Grid[r - 1][c]);
		}
		return true;
	}

	/**
	 * BottomIsEmpty - Tests if the bottom cell is empty
	 */
	public Boolean BottomIsEmpty(int r, int c) {
		if (r < Config.GridRows - 1) {
			return IsNullOrEmpty(Grid[r + 1][c]);
		}
		return true;
	}

	/**
	 * MatchingOrEmpty - compares a given character to the contents of a given
	 * coordinate
	 */
	public Boolean MatchingOrEmpty(int r, int c, String character) {
		String cellValue = Grid[r][c];
		if (!IsNullOrEmpty(cellValue)) {
			if (!cellValue.equals(character)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * CoordinateInBounds - determines if a coordinate is in bounds within the
	 * grid
	 */
	public Boolean CoordinateInBounds(int r, int c) {
		if (r < 0 || r > Config.GridRows - 1) {
			return false;
		}
		if (c < 0 || c > Config.GridColumns - 1) {
			return false;
		}
		return true;
	}

	/**
	 * PlaceWordDown - Places a word going down on the grid
	 */
	public void PlaceWordDown(int r, int c, String word) {
		WordProcessor wp = Config.GetWordProcessor(word);

		for (int i = 0; i < wp.getLength(); i++) {
			Grid[r + i][c] = wp.logicalCharAt(i);
		}
		
		PlacedWordsDown.add(word);
		
		Coordinate cd = new Coordinate(r, c);
		PlacedWordTracker tracker = new PlacedWordTracker(cd, word);
		TrackWordsDown.add(tracker);
		List<PlacedWordTracker> existing = coordinateMap.get(cd);
		if (existing == null)
			coordinateMap.put(cd, new LinkedList<PlacedWordTracker>(Arrays.asList(tracker)));
		else
			existing.add(tracker);
			
	}

	/**
	 * PlaceWordRight - Places a word going right on the grid
	 */
	public void PlaceWordRight(int r, int c, String word) {
		WordProcessor wp = Config.GetWordProcessor(word);

		for (int i = 0; i < wp.getLength(); i++) {
			Grid[r][c + i] = wp.logicalCharAt(i);
		}
		
		PlacedWordsRight.add(word);
		
		Coordinate cd = new Coordinate(r, c);
		PlacedWordTracker tracker = new PlacedWordTracker(cd, word);
		TrackWordsRight.add(tracker);
		List<PlacedWordTracker> existing = coordinateMap.get(cd);
		if (existing == null)
			coordinateMap.put(cd, new LinkedList<PlacedWordTracker>(Arrays.asList(tracker)));
		else
			existing.add(tracker);
	}

	/**
	 * TestPlaceWordDown - Tests if a word can be placed going down
	 */
	public Boolean TestPlaceWordDown(int r, int c, String word, int offset) {
		WordProcessor wp = Config.GetWordProcessor(word);

		int R = r - offset;

		// for each letter in the word
		for (int i = 0; i < wp.getLength(); i++) {
			// test coordinate in bounds
			if (!CoordinateInBounds(R + i, c)) {
				return false;
			}

			// test first letter has nothing above it
			if (i == 0) {
				if (!TopIsEmpty(R + i, c)) {
					return false;
				}
			}

			// test coordinate is either matching or empty
			if (!MatchingOrEmpty(R + i, c, wp.logicalCharAt(i))) {
				return false;
			}

			// test left and right are empty when not at the intersection
			if (r != R + i) {

				if (!RightIsEmpty(R + i, c)) {
					return false;
				}

				if (!LeftIsEmpty(R + i, c)) {
					return false;
				}
			}

			// test that the last letter has nothing below it
			if (i == wp.getLength() - 1) {
				if (!BottomIsEmpty(R + i, c)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * TestPlaceWordRight - Tests if a word can be placed going right
	 */
	public Boolean TestPlaceWordRight(int r, int c, String word, int offset) {
		WordProcessor wp = Config.GetWordProcessor(word);

		int C = c - offset;

		// for each letter in the word
		for (int i = 0; i < wp.getLength(); i++) {
			// test coordinate in bounds
			if (!CoordinateInBounds(r, C + i)) {
				return false;
			}

			// test first letter has nothing to the left of it
			if (i == 0) {
				if (!LeftIsEmpty(r, C + i)) {
					return false;
				}
			}

			// test coordinate is either matching or empty
			if (!MatchingOrEmpty(r, C + i, wp.logicalCharAt(i))) {
				return false;
			}

			// test top and bottom are empty when not at the intersection
			if (c != C + i) {

				if (!TopIsEmpty(r, C + i)) {
					return false;
				}

				if (!BottomIsEmpty(r, C + i)) {
					return false;
				}
			}

			// test that the last letter has nothing to the right of it
			if (i == wp.getLength() - 1) {
				if (!RightIsEmpty(r, C + i)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * FindMatchingCoordinates - Finds list of matching coordinates on the grid
	 */
	public ArrayList<Coordinate> FindMatchingCoordinates(String character) {
		ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();

		for (int r = 0; r < Config.GridRows; r++) {
			for (int c = 0; c < Config.GridColumns; c++) {
				String cell = Grid[r][c];

				if (cell != null && cell.equals(character)) {
					coordinates.add(new Coordinate(r, c));
				}
			}
		}

		return coordinates;
	}

	/**	
	 * GetGrid - returns the Grid
	 */
	public String[][] GetGrid() {
		return Grid;
	}

	/**
	 * GetPlacedWordCount - Returns the number of words that have been placed on the grid
	 */
	public int GetPlacedWordCount() {
		return PlacedWordsRight.size() + PlacedWordsDown.size();
	}

	/**
	 * GenerateCrosswordPuzzle - Generates a crossword puzzle This method
	 * contains the logical steps to generate a new crossword puzzle
	 */
	public void GenerateCrosswordPuzzle() {
		// define
		int maxPasses = 6;
		int currentPass = 0;

		// init
		Collections.sort(Words, new LengthComparator());
		PlacedWordsRight = new ArrayList<String>();
		PlacedWordsDown = new ArrayList<String>();

		// place words
		while (currentPass <= maxPasses) {
			currentPass++;

			// for each word in the word list
			for (int h = 0; h < Words.size(); h++) {
				String currentWord = Words.get(h);
				WordProcessor wp = Config.GetWordProcessor(currentWord);

				// place first word on the grid
				if (PlacedWordsRight.size() == 0) {
					if (TestPlaceWordRight(0, 0, currentWord, 0)) {
						PlaceWordRight(0, 0, currentWord);
					}
				}

				else {
					// attempt to place words that haven't been placed yet
					if ((!PlacedWordsRight.contains(currentWord)) && (!PlacedWordsDown.contains(currentWord))) {
						Boolean exitCondition = false;

						// attempt to place word
						for (int i = 0; i < wp.getLength(); i++) {
							if (exitCondition)
								break;

							// find matching coordinates for each character in
							// word
							String character = wp.logicalCharAt(i);
							ArrayList<Coordinate> coordinates = FindMatchingCoordinates(character);

							// attempt to place words at coordinates
							if (coordinates.size() > 0) {
								for (Coordinate c : coordinates) {
									if (TestPlaceWordRight(c.getR(), c.getC(),
											currentWord, i)) {
										PlaceWordRight(c.getR(), c.getC() - i,
												currentWord);

										exitCondition = true;
										break;
									}

									else if (TestPlaceWordDown(c.getR(),
											c.getC(), currentWord, i)) {
										PlaceWordDown(c.getR() - i, c.getC(),
												currentWord);

										exitCondition = true;
										break;
									}
								}
							}
						}
					}
				}
			}
		}
		List<PlacedWordTracker> pwts;
		for (int r = 0; r < Config.GridRows; r++){
			for (int c = 0; c < Config.GridColumns; c++){
				if ((pwts = coordinateMap.get(new Coordinate(r,c))) != null){
					int num = PlacedWordNumber++;
					for (PlacedWordTracker pwt : pwts){
						pwt.setNumber(num);
					}
				}
			}
		}
	}
	
	private int GetNumberAtCoordinate(Coordinate coordinate){
		for(PlacedWordTracker t : TrackWordsRight){
			if((t.getCoordinate().getC() == coordinate.getC()) && (t.getCoordinate().getR() == coordinate.getR())) {
				return t.getNumber();
			}
		}
		
		for(PlacedWordTracker t : TrackWordsDown){
			if((t.getCoordinate().getC() == coordinate.getC()) && (t.getCoordinate().getR() == coordinate.getR())) {
				return t.getNumber();
			}
		}
		
		return 0;
	}
	
	public String GetCrosswordGridHtml(Boolean generateSolution){
		StringBuilder sb = new StringBuilder();
				for (int i = 0; i < Config.GridRows; i++) {
					for (int j = 0; j < Config.GridColumns; j++) {
						String cell = Grid[i][j];

						if (IsNullOrEmpty(cell)) {
							cell = Config.GetBlankCharacter();
						}

						if (cell != Config.GetBlankCharacter()) {
							int num = GetNumberAtCoordinate(new Coordinate(i,j));
							if(num > 0){
								if(generateSolution){
									sb.append("<span class='tile tileComplete'><span class='sm'>" + num + "</span><span class='ltr'>" + Grid[i][j] + "</span></span>");
								}
								else{
									sb.append("<span class='tile'><span class='sm'>" + num + "</span><span class='ltr'>" + Config.GetBlankCharacter() + "</span></span>");
								}
								
							}
							else {
								if(generateSolution){
									sb.append("<span class='tile tileComplete'>" + Grid[i][j] + "</span>");
								}
								else{
									sb.append("<span class='tile'>" + Config.GetBlankCharacter() + "</span>");
								}
								
							}
							
						} else {
							sb.append("<span class='blacktile'>" + cell + "</span>");
						}
					}

					sb.append("<br/>");
				}
		return sb.toString();
	}

	/**
	 * GetCrosswordHtml - Generates the grid as an HTML string
	 */
	public String GetCrosswordHtml(Boolean includeSolution, Boolean cluesByTopic) {
		if (cluesByTopic && Config.InputSource != 0)
			throw new UnsupportedOperationException("User-input words have no topics");
		
		StringBuilder sb = new StringBuilder();

		//build grid
		sb.append(GetCrosswordGridHtml(false));
		
		//build clues right
		sb.append("<table id='clueTable'><tr><td valign='top' style='padding-right:40px'>");
		sb.append("<p><strong>Across Clues</strong></p>");
		

		//file
		if(Config.InputSource == 0){
			PlacedWordTracker[] words = (PlacedWordTracker[])(TrackWordsRight.toArray(new PlacedWordTracker[0]));
			if (cluesByTopic)
				Arrays.sort(words, new Comparator<PlacedWordTracker>(){
					@Override
					public int compare(PlacedWordTracker o1, PlacedWordTracker o2){
						return o1.getTopic().compareToIgnoreCase(o2.getTopic());
					}
				});
			
			String topic = "";
			for (PlacedWordTracker t: Arrays.asList(words)){
				if (cluesByTopic && !t.getTopic().equals(topic)){
					topic = t.getTopic();
					sb.append("<p>Topic: " + topic + "</p>");
				}	
				
				BigWord bw = Config.FindBigWordInFilteredBigWords(t.getWord());
				if(bw.hasClue()){
					sb.append(t.getNumber() + ". "  + bw.getClue() + "<br/>");
				}
				else{
					sb.append(t.getNumber() + ". No Clue" + "<br/>");
				}
			}
			
		}
		//gui
		else{
			for(PlacedWordTracker t : TrackWordsRight){
				String clue = Config.GetClueFromManualWordsList(t.getWord());
				if(clue != null){
					sb.append(t.getNumber() + ". "  + clue + "<br/>");
				}
				else{
					sb.append(t.getNumber() + ". No Clue" + "<br/>");
				}
			}
		}
		
		
		sb.append("</td><td valign='top'>");
		
		//build clues down
		sb.append("<p><strong>Down Clues</strong></p>");
		
		//words = (PlacedWordTracker[])TrackWordsDown.toArray();
		
		
		//file
		if(Config.InputSource == 0){
			
			PlacedWordTracker[] words = (PlacedWordTracker[])(TrackWordsDown.toArray(new PlacedWordTracker[0]));
			if (cluesByTopic)
				Arrays.sort(words, new Comparator<PlacedWordTracker>(){
					@Override
					public int compare(PlacedWordTracker o1, PlacedWordTracker o2){
						return o1.getTopic().compareToIgnoreCase(o2.getTopic());
					}
				});
			String topic = "";
			for (PlacedWordTracker t: Arrays.asList(words)){
				if (cluesByTopic && !t.getTopic().equals(topic)){
					topic = t.getTopic();
					sb.append("<p>Topic: " + topic + "</p>");
				}	
				
				BigWord bw = Config.FindBigWordInFilteredBigWords(t.getWord());
				if(bw.hasClue()){
					sb.append(t.getNumber() + ". "  + bw.getClue() + "<br/>");
				}
				else{
					sb.append(t.getNumber() + ". No Clue" + "<br/>");
				}
			}
		}
		//gui
		else{
			for(PlacedWordTracker t : TrackWordsDown){
				String clue = Config.GetClueFromManualWordsList(t.getWord());
				if(clue != null){
					sb.append(t.getNumber() + ". "  + clue + "<br/>");
				}
				else{
					sb.append(t.getNumber() + ". No Clue" + "<br/>");
				}
			}
		}
		
		
		sb.append("</td></tr></table>");
		
		if(includeSolution){
			sb.append("<div class='theSolutionIsHere'>");
			sb.append(GetCrosswordGridHtml(true));
			sb.append("</div>");
		}

		return sb.toString();
	}
}