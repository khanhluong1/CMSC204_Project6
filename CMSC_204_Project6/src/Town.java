import java.util.ArrayList;
import java.util.List;

/**
 * Represents an town as a node of a graph. 
 * The Town class holds the name of the town and a list of adjacent towns, 
 * and other fields as desired, and the traditional methods (constructors, getters/setters, toString, etc.). 
 * It will implement the Comparable interface These are the minimum methods that are needed. 
 * 
 * 
 * @author Derek Luong
 *
 */
public class Town implements Comparable<Town> {
	
	private String name;
	private List<Town> adjacentTowns;
	private Integer wt;
	private Town backPath;
	
	/**
	 * Constructor. Requires town's name.
	 * 
	 * @param name - Town name
	 */
	public Town(String name) {
		this.name = name;
		adjacentTowns = new ArrayList<Town>();
	}

	@Override
	public int compareTo(Town o) {
		return this.name.compareTo(o.name);
	}
	
	public void addAdjacentTown(Town t) {
		if (!adjacentTowns.contains(t)) {
			adjacentTowns.add(t);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Town> getAdjacentTowns() {
		return adjacentTowns;
	}

	public void setAdjacentTowns(List<Town> adjacentTowns) {
		this.adjacentTowns = adjacentTowns;
	}

	public Integer getWt() {
		return wt;
	}

	public void setWt(Integer wt) {
		this.wt = wt;
	}

	public Town getBackPath() {
		return backPath;
	}

	public void setBackPath(Town backPath) {
		this.backPath = backPath;
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
		if (!(obj instanceof Town)) {
			return false;
		}
		Town t = (Town) obj;
		if (this.compareTo(t) == 0) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		
		return "Town name = " + name + ", weight = " + wt;
	}
	
}
