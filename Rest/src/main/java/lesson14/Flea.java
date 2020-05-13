package lesson14;

public class Flea {

	private String flea = "{\"id\":\"63\",\"name\":\"bee\",\"color\":\" \"}";

	private int id;
	private String name;
	private String color;

	public String getFlea() {
		return flea;
	}

	public void setFlea(String flea) {
		this.flea = flea;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Flea(int id, String name, String color) {
		this.id = id;
		this.name = name;
		this.color = color;
	}

	public Flea() {
	}

	@Override
	public String toString() {
		return "Flea [id=" + id + ", name=" + name + ", color=" + color + "]";
	}
}
