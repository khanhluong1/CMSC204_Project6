import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Graph implements methods in GraphInterface
 * 
 * @author Derek Luong
 *
 */
public class Graph implements GraphInterface<Town, Road> {
	
	private Set<Town> towns;
	private Set<Road> roads;
	private Set<Town> open = new HashSet<Town>();
	private Set<Town> closed = new HashSet<Town>();
	
	public Graph() {
		towns = new HashSet<Town>();
		roads = new HashSet<Road>();
	}

	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		Road foundRoad = null;
		Road findingRoad = new Road(sourceVertex, destinationVertex);
		for (Road r : roads) {
			if (r.compareTo(findingRoad) == 0) {
				foundRoad = r;
				break;
			}
		}
		return foundRoad;
	}

	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight,
			String description) {
		Road newRoad = new Road(sourceVertex, destinationVertex, weight, description);
		roads.add(newRoad);
		return newRoad;
	}

	@Override
	public boolean addVertex(Town v) {
		return towns.add(v);
	}

	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		Road foundRoad = getEdge(sourceVertex, destinationVertex);
		return foundRoad != null;
	}

	@Override
	public boolean containsVertex(Town v) {
		return towns.contains(v);
	}

	@Override
	public Set<Road> edgeSet() {
		return roads;
	}

	@Override
	public Set<Road> edgesOf(Town vertex) {
		Set<Road> result = new HashSet<Road>();
		for (Road r : roads) {
			if (r.getSource().compareTo(vertex) == 0 || r.getDestination().compareTo(vertex) == 0) {
				result.add(r);
			}
		}
		return result;
	}

	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex,
			int weight, String description) {
		Road removedRoad = new Road(sourceVertex, destinationVertex, weight, description);
		roads.remove(removedRoad);
		return removedRoad;
	}

	@Override
	public boolean removeVertex(Town v) {
		Set<Road> removedRoads = edgesOf(v);
		return towns.remove(v) && roads.removeAll(removedRoads);
	}

	@Override
	public Set<Town> vertexSet() {
		return towns;
	}

	@Override
	public ArrayList<String> shortestPath(Town sourceVertex,
			Town destinationVertex) {
		dijkstraShortestPath(sourceVertex);
		ArrayList<String> result = new ArrayList<String>();
		Town town = destinationVertex;
		Town prevTown = town.getBackPath();
		while (prevTown != null) {
			Road road = getEdge(prevTown, town);
			result.add(prevTown.getName() + " via " + road.getName() + " to " + town.getName() + " " + road.getWeight() + " mi");
			town = prevTown;
			prevTown = town.getBackPath();
		}
		return result;
	}

	@Override
	public void dijkstraShortestPath(Town sourceVertex) {
		open = towns;
		open.remove(sourceVertex);
		closed = new HashSet<Town>();
		closed.add(sourceVertex);
		while(!open.isEmpty()) {
			int minWt = Integer.MAX_VALUE;
			Town minAdjTown = null;
			for (Town vertex : closed) {
				for (Town adjVertex : getAdjVerticesInSet(vertex, open)) {
					int wt = getWtToSource(adjVertex, vertex, sourceVertex);
					if (wt < minWt) {
						minWt = wt;
						minAdjTown = adjVertex;
						adjVertex.setBackPath(vertex);
					}
				}
			}
			if (minAdjTown != null) {
				minAdjTown.setWt(minWt);
				open.remove(minAdjTown);
				closed.add(minAdjTown);
			}
		}
		
	}
	
	private Set<Town> getAdjVerticesInSet(Town vertex, Set<Town> open) {
		Set<Town> result = new HashSet<Town>();
		List<Town> adjacentTowns = vertex.getAdjacentTowns();
		for (Town t : adjacentTowns) {
			if (open.contains(t)) {
				result.add(t);
			}
		}
		return result;
	}

	private int getWtToSource(Town adjVertex, Town vertex, Town sourceVertex) {
		int weight = 0;
		Road r1 = getEdge(adjVertex, vertex);
		if (r1 != null) {
			weight += r1.getWeight();
		}
		Town prevTown = vertex.getBackPath();
		while (prevTown != null) {
			Road r2 = getEdge(vertex, prevTown);
			if (r2 != null) {
				weight += r2.getWeight();
			}
			vertex = prevTown;
			prevTown = vertex.getBackPath();
		}
		return weight;
	}

}
