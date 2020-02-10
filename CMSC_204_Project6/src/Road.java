/**
 * 
 * The class Road that can represent the edges of a Graph of Towns. 
 * The class must implement Comparable. The class stores references to the two vertices(Town endpoints), 
 * the distance between vertices, and a name, and the traditional methods (constructors, getters/setters, toString, etc.), 
 * and a compareTo, which compares two Road objects. Since this is a undirected graph, an edge from A to B is equal to an edge from B to A.
 * 
 * @author Derek Luong
 *
 */
public class Road implements Comparable<Road> {

	private Town source;
	private Town destination;
	private String name;
	private Integer weight;
	
	public Road(Town sourceTown, Town destinationTown, Integer weight, String name) {
		this.source = sourceTown;
		this.destination = destinationTown;
		this.name = name;
		this.weight = weight;
		sourceTown.addAdjacentTown(destinationTown);
		destinationTown.addAdjacentTown(sourceTown);
	}
	
	public Road(Town sourceTown, Town destinationTown) {
		this(sourceTown, destinationTown, 1, "");
	}
	
	@Override
	public int compareTo(Road o) {
		if ((this.source.equals(o.source) && this.destination.equals(o.destination))
				|| (this.destination.equals(o.source) && this.source.equals(o.destination))) {
			return 0;
		}
		
		return -1;
	}

	public Town getSource() {
		return source;
	}

	public void setSource(Town sourceTown) {
		this.source = sourceTown;
	}

	public Town getDestination() {
		return destination;
	}

	public void setDestination(Town destinationTown) {
		this.destination = destinationTown;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Road)) {
			return false;
		}
		Road r = (Road) obj;
		if (this.compareTo(r) == 0) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "Road name = " + name + ", weight = " + weight + ", source = " + source.toString() + ", destination = " + destination.toString();
	}
}
