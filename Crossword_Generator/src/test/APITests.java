package test;

import static org.junit.Assert.*;
import org.junit.Test;
import core.*;

/*
 * JUnit tests for API methods
 */
public class APITests {

	@Test
	public void test_PlaceWordRight_English() {
		API api = new API(10,10,0,10,1);
		
		api.TestPlaceWordRight(0, 0, "HELLO", 0);
		
		api.PlaceWordRight(0, 0, "HELLO");
		
		assertTrue(api.TestPlaceWordDown(0, 0, "HELLO", 0));
		
		assertFalse(api.TestPlaceWordDown(0, 0, "YELLOW", 0));
	}
	
	@Test
	public void test_PlaceWordRight_Telugu() {
		API api = new API(10,10,0,10,1);
		
		api.TestPlaceWordRight(0, 0, "ఇండోనేషియా", 0);
		
		api.PlaceWordRight(0, 0, "ఇండోనేషియా");
		
		assertTrue(api.TestPlaceWordDown(0, 0, "ఇండోనేషియా", 0));
		
		assertFalse(api.TestPlaceWordDown(0, 0, "క్షణము", 0));
	}
	
	@Test
	public void test_TopIsEmpty() {
		//arrange
		API api = new API(10,10,0,10,1);
		
		//test min
		assertTrue(api.TopIsEmpty(0, 0));
		
		api.PlaceWordRight(0, 0, "TEST");
		
		assertFalse(api.TopIsEmpty(1, 0));
		assertFalse(api.TopIsEmpty(1, 1));
		assertFalse(api.TopIsEmpty(1, 2));
		assertFalse(api.TopIsEmpty(1, 3));
	}
	
	@Test
	public void test_BottomIsEmpty() {
		//arrange
		API api = new API(10,10,0,10,1);
		
		//test max
		assertTrue(api.BottomIsEmpty(9, 9));
		
		api.PlaceWordRight(1, 0, "TEST");
		
		assertFalse(api.BottomIsEmpty(0, 0));
		assertFalse(api.BottomIsEmpty(0, 1));
		assertFalse(api.BottomIsEmpty(0, 2));
		assertFalse(api.BottomIsEmpty(0, 3));
	}
	
	@Test
	public void test_LeftIsEmpty() {
		//arrange
		API api = new API(10,10,0,10,1);
		
		//test min
		assertTrue(api.LeftIsEmpty(0, 0));
		
		api.PlaceWordDown(0, 0, "TEST");
		
		assertFalse(api.LeftIsEmpty(0, 1));
		assertFalse(api.LeftIsEmpty(1, 1));
		assertFalse(api.LeftIsEmpty(2, 1));
		assertFalse(api.LeftIsEmpty(3, 1));
	}
	
	@Test
	public void test_RightIsEmpty() {
		//arrange
		API api = new API(10,10,0,10,1);
		
		//test max
		assertTrue(api.RightIsEmpty(9, 9));
		
		api.PlaceWordDown(0, 1, "TEST");
		
		assertFalse(api.RightIsEmpty(0, 0));
		assertFalse(api.RightIsEmpty(1, 0));
		assertFalse(api.RightIsEmpty(2, 0));
		assertFalse(api.RightIsEmpty(3, 0));
	}
	
	@Test
	public void test_MatchingOrEmpty() {
		//arrange
		API api = new API(10,10,0,10,1);
		
		//test max
		assertTrue(api.MatchingOrEmpty(0, 0, "T"));
		
		api.PlaceWordRight(0, 0, "TEST");
		
		assertTrue(api.MatchingOrEmpty(0, 0, "T"));
		assertTrue(api.MatchingOrEmpty(0, 1, "E"));
		assertTrue(api.MatchingOrEmpty(0, 2, "S"));
		assertTrue(api.MatchingOrEmpty(0, 3, "T"));
	}
	
	@Test
	public void test_CoordinateInBounds() {
		API api = new API(10, 10, 0, 10, 1);
		assertTrue(api.CoordinateInBounds(0, 0));
		assertTrue(api.CoordinateInBounds(0, 0));
		assertTrue(api.CoordinateInBounds(0, 1));
		assertTrue(api.CoordinateInBounds(0, 2));
		assertTrue(api.CoordinateInBounds(0, 3));
	}

	@Test
	public void test_TestPlaceWordRight() {
		API api = new API(10, 10, 0, 10, 1);
		api.TestPlaceWordRight(0, 0, "TEST", 0);
		api.PlaceWordRight(0, 0, "TEST");
		assertTrue(api.TestPlaceWordDown(0, 0, "TEST", 0));
		assertFalse(api.TestPlaceWordDown(0, 0, "క్షణము", 0));
	}

	@Test
	public void test_GetGrid() {
		API api = new API(10, 10, 0, 10, 1);
		api.GetGrid();
	}
	
	@Test
	public void test_IsNullOrEmpty(){
		API api = new API(10, 10, 0, 10, 1);
		assertTrue(api.IsNullOrEmpty(""));
		assertTrue(api.IsNullOrEmpty(null));
	}
}
