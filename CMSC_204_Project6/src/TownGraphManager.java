import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

/**
 * TownGraphManager implements TownGraphManagerInterface
 * 
 * @author Derek Luong
 *
 */
public class TownGraphManager implements TownGraphManagerInterface {
	
	private Graph graph;
	
	public TownGraphManager() {
		graph = new Graph();
	}

	@Override
	public boolean addRoad(String town1, String town2, String roadName) {
		Town town1Obj = getTownByName(town1);
		Town town2Obj = getTownByName(town2);
		Road edge = graph.addEdge(town1Obj, town2Obj, 1, roadName);
		return edge != null;
	}
	
	@Override
	public boolean addRoad(String town1, String town2, String roadName, Integer roadWeight) {
		Town town1Obj = getTownByName(town1);
		Town town2Obj = getTownByName(town2);
		Road edge = graph.addEdge(town1Obj, town2Obj, roadWeight, roadName);
		return edge != null;
	}

	@Override
	public String getRoad(String town1, String town2) {
		Town town1Obj = getTownByName(town1);
		Town town2Obj = getTownByName(town2);
		Road road = graph.getEdge(town1Obj, town2Obj);
		return road.getName();
	}

	@Override
	public boolean addTown(String v) {
		Town town1Obj = new Town(v);
		town1Obj.setWt(1);
		return graph.addVertex(town1Obj);
	}

	@Override
	public boolean containsTown(String v) {
		Town town1Obj = getTownByName(v);
		return graph.containsVertex(town1Obj);
	}

	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		Town town1Obj = getTownByName(town1);
		Town town2Obj = getTownByName(town2);
		return graph.containsEdge(town1Obj, town2Obj);
	}

	@Override
	public ArrayList<String> allRoads() {
		ArrayList<String> result = new ArrayList<String>();
		for (Road r : graph.edgeSet()) {
			result.add(r.getName());
		}
		Collections.sort(result);
		return result;
	}

	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		Town town1Obj = getTownByName(town1);
		Town town2Obj = getTownByName(town2);
		Road removedRoad = graph.removeEdge(town1Obj, town2Obj, 1, road);
		return removedRoad != null;
	}

	@Override
	public boolean deleteTown(String v) {
		Town town1Obj = new Town(v);
		return graph.removeVertex(town1Obj);
	}

	@Override
	public ArrayList<String> allTowns() {
		ArrayList<String> result = new ArrayList<String>();
		for (Town t : graph.vertexSet()) {
			result.add(t.getName());
		}
		Collections.sort(result);
		return result;
	}

	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		
		Town beginIndex = getTownByName(town1);
		Town endIndex = getTownByName(town2);
		if(beginIndex != null && endIndex != null)
		{
			ArrayList<String> path = graph.shortestPath(beginIndex,endIndex);
			return path;
		}
		return null;
	}
	
	private Town getTownByName(String townName) {
		Set<Town> towns = graph.vertexSet();
		Iterator<Town> iterator = towns.iterator();
		while(iterator.hasNext())
		{    	
			Town town = iterator.next();
			if(town.getName().equals(townName))
				return town;
		}
		return null;
	}

}
