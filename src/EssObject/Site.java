package EssObject;

public class Site {
	String name;
	public Site(){
		this("");
	}
	public Site(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}
	public String toString(){
		return "site name = "+name;
	}
	public boolean equals(Object o){
		if(o.getClass() != this.getClass()){
			return false;
		}
		Site site = (Site)o;
		if(site.name != this.name)return false;{
			return true;
		}
	}
	public int compareTo(Site o) {
		return this.name.compareTo(o.name);
	}
	
}
