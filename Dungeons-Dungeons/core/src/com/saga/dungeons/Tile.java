package com.saga.dungeons;


public class Tile {
	private String texture;
	private boolean is_passable;
	private Tile_type type;
	private int x;
	private int y;
	
	
	public Tile(Tile_type type, String texture, int x, int y) {
		set_type(type);
		this.texture = texture;
		this.x = x;
		this.y = y;
	}
	
	
	/* Takes a tile type as input.
	 * Changes tile type to the supplied type and updates associated attributes.
	 */
	public void set_type(Tile_type type){
		this.type = type;
		switch(this.type) {
		case WALL:
			this.is_passable = false;
			this.texture = "brick";
			break;
		case FLOOR:
			this.is_passable = true;
			this.texture = "floor_tile";
			break;
		case EMPTY:
			this.is_passable = true;
			this.texture = "void";
			break;
		}
	}
	
	
	public Tile_type get_type() {
		return this.type;
	}
	
	
	public boolean get_passable() {
		return this.is_passable;
	}
	
	
	public void set_texture(String texture) {
		this.texture = texture;
	}
	
	
	public String get_texture() {
		return this.texture;
	}


	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	}

}
