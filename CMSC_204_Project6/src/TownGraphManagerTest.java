

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TownGraphManagerTest {
	private TownGraphManager graph;
	private String[] town;
	  
	@Before
	public void setUp() throws Exception {
		  graph = new TownGraphManager();
		  town = new String[12];
		  
		  for (int i = 1; i < 12; i++) {
			  town[i] = "Town_" + i;
			  graph.addTown(town[i]);
		  }
		  
		  graph.addRoad(town[1], town[2], "Road_1");
		  graph.addRoad(town[1], town[3], "Road_2");
		  graph.addRoad(town[1], town[5], "Road_3");
		  graph.addRoad(town[3], town[7], "Road_4");
		  graph.addRoad(town[3], town[8], "Road_5");
		  graph.addRoad(town[4], town[8], "Road_6");
		  graph.addRoad(town[6], town[9], "Road_7");
		  graph.addRoad(town[9], town[10], "Road_8");
		  graph.addRoad(town[8], town[10], "Road_9");
		  graph.addRoad(town[5], town[10], "Road_10");
		  graph.addRoad(town[10], town[11], "Road_11");
		  graph.addRoad(town[2], town[11], "Road_12");
		  graph.addRoad(town[7], town[6], "Road_13");
		  
	}

	@After
	public void tearDown() throws Exception {
		graph = null;
	}

	@Test
	public void testAddRoad() {
		ArrayList<String> roads = graph.allRoads();
		assertEquals("Road_1", roads.get(0));
		assertEquals("Road_10", roads.get(1));
		assertEquals("Road_11", roads.get(2));
		assertEquals("Road_12", roads.get(3));
		assertEquals("Road_13", roads.get(4));
		graph.addRoad(town[4], town[11], "Road_14");
		roads = graph.allRoads();
		assertEquals("Road_1", roads.get(0));
		assertEquals("Road_10", roads.get(1));
		assertEquals("Road_11", roads.get(2));
		assertEquals("Road_12", roads.get(3));
		assertEquals("Road_13", roads.get(4));
		assertEquals("Road_14", roads.get(5));
		
	}

	@Test
	public void testGetRoad() {
		assertEquals("Road_12", graph.getRoad(town[2], town[11]));
		assertEquals("Road_4", graph.getRoad(town[3], town[7]));
	}

	@Test
	public void testAddTown() {
		assertEquals(false, graph.containsTown("Town_12"));
		graph.addTown("Town_12");
		assertEquals(true, graph.containsTown("Town_12"));
	}

	@Test
	public void testContainsTown() {
		assertEquals(true, graph.containsTown("Town_2"));
		assertEquals(false, graph.containsTown("Town_12"));
	}

	@Test
	public void testContainsRoadConnection() {
		assertEquals(true, graph.containsRoadConnection(town[2], town[11]));
		assertEquals(false, graph.containsRoadConnection(town[3], town[5]));
	}

	@Test
	public void testAllRoads() {
		ArrayList<String> roads = graph.allRoads();
		assertEquals("Road_1", roads.get(0));
		assertEquals("Road_10", roads.get(1));
		assertEquals("Road_11", roads.get(2));
		assertEquals("Road_7", roads.get(10));
		assertEquals("Road_8", roads.get(11));
	}

	@Test
	public void testDeleteRoadConnection() {
		assertEquals(true, graph.containsRoadConnection(town[2], town[11]));
		graph.deleteRoadConnection(town[2], town[11], "Road_12");
		assertEquals(false, graph.containsRoadConnection(town[2], town[11]));
	}

	@Test
	public void testDeleteTown() {
		assertEquals(true, graph.containsTown("Town_2"));
		graph.deleteTown(town[2]);
		assertEquals(false, graph.containsTown("Town_2"));
	}

	@Test
	public void testDeleteTownSTUDENT() {
		assertEquals(true, graph.containsTown("Town_5"));
		graph.deleteTown(town[5]);
		assertEquals(false, graph.containsTown("Town_5"));
	}
	
	@Test
	public void testAllTowns() {
		ArrayList<String> roads = graph.allTowns();
		assertEquals("Town_1", roads.get(0));
		assertEquals("Town_10", roads.get(1));
		assertEquals("Town_11", roads.get(2));
		assertEquals("Town_2", roads.get(3));
		assertEquals("Town_8", roads.get(9));
	}

	@Test
	public void testGetPath() {
		ArrayList<String> path = graph.getPath(town[1],town[11]);
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  
		  assertEquals("Town_2 via Road_12 to Town_11 1 mi",path.get(0).trim());
		  assertEquals("Town_1 via Road_1 to Town_2 1 mi",path.get(1).trim());

	}
	
	@Test
	public void testGetPathA() {
		ArrayList<String> path = graph.getPath(town[1],town[10]);
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  
		  assertEquals("Town_5 via Road_10 to Town_10 1 mi",path.get(0).trim());
		  assertEquals("Town_1 via Road_3 to Town_5 1 mi",path.get(1).trim());
	}
	
	@Test
	public void testGetPathB() {
		ArrayList<String> path = graph.getPath(town[1],town[6]);
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  
		  assertEquals("Town_7 via Road_13 to Town_6 1 mi",path.get(0).trim());
		  assertEquals("Town_3 via Road_4 to Town_7 1 mi",path.get(1).trim());
		  assertEquals("Town_1 via Road_2 to Town_3 1 mi",path.get(2).trim());

	}
	
	@Test
	public void testGetPathSTUDENT() {
		ArrayList<String> path = graph.getPath(town[1],town[10]);
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  
		  assertEquals("Town_5 via Road_10 to Town_10 1 mi",path.get(0).trim());
		  assertEquals("Town_1 via Road_3 to Town_5 1 mi",path.get(1).trim());
	}

}
