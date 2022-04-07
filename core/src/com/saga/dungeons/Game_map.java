package com.saga.dungeons;

import java.util.ArrayList;
import java.util.List;

public class Game_map {
	
	private int x_size, y_size;
	private Tile[][] tiles;
	private Map_type type;
	
	public Game_map(Map_type type, int x_size, int y_size) {
		this.x_size = x_size;
		this.y_size = y_size;
		this.type = type;
		this.tiles = new Tile[y_size][x_size];
		Map_generator.generate(this, x_size, y_size);
	}
	
	
	public List<Tile> plot_line(int x0, int y0, int x1, int y1, boolean cast) {
		/* Takes start and end coordinates as input. Outputs a line of tiles
		 * as a list. Picks the correct algorithm based on line slope.
		 */
		List<Tile> tiles = new ArrayList<Tile>();
		if(Math.abs(y1 - y0) < Math.abs(x1 - x0)) {
			if(x0 > x1) {
				tiles = plot_low_slope(x1, y1, x0, y0, cast);
			} else {
				tiles = plot_low_slope(x0, y0, x1, y1, cast);
			}
		} else {
			if(y0 > y1) {
				tiles = plot_high_slope(x1, y1, x0, y0, cast);
			} else {
				tiles = plot_high_slope(x0, y0, x1, y1, cast);
			}
		}
		return tiles;
	}
	
	
	public List<Tile> plot_low_slope(int x0, int y0, int x1, int y1, boolean cast){
		/* Input start, end coordinates. Output list of tiles.
		 * Uses Bresenham's algorithm to plot lines with slope <= 1
		 */
		List<Tile> tiles = new ArrayList<Tile>();
		int dx = x1 - x0;
		int dy = y1 - y0;
		int yi = 1;
		if(dy < 0) {
			yi = -1;
			dy = -dy;
		}
		int D = (2 * dy) - dx;
		int y = y0;
		Tile tile;
		for(int x = x0; x < x1 + 1; x++) {
			tile = get_tile(x,y);
			if(cast && !tile.get_passable()) {
				break;
			}
			tiles.add(tile);
			if(D > 0) {
				y += yi;
				D += (2 * (dy - dx));
			} else {
				D += 2 * dy;
			}
		}
		return tiles;
	}
	
	
	public List<Tile> plot_high_slope(int x0, int y0, int x1, int y1, boolean cast){
		/* Input start, end coordinates. Output list of tiles.
		 * Uses Bresenham's algorithm to plot lines with slope >= 1
		 */
		List<Tile> tiles = new ArrayList<Tile>();
		int dx = x1 - x0;
		int dy = y1 - y0;
		int xi = 1;
		if(dx < 0) {
			xi = -1;
			dx = -dx;
		}
		int D = (2 * dx) - dy;
		int x = x0;
		Tile tile;
		for(int y = y0; y < y1 + 1; y++) {
			tile = get_tile(x,y);
			if(cast && !tile.get_passable()) {
				break;
			}
			tiles.add(tile);
			if(D > 0) {
				x += xi;
				D += (2 * (dx - dy));
			} else {
				D += 2 * dx;
			}
		}
		return tiles;
	}
	
	/* Takes start and end coordinates, and a boolean fill value.
	 * Returns a list of tiles corresponding to an outlined or filled rectangle
	 */
	public List<Tile> get_rectangle(int x0,int y0,int x1,int y1, boolean fill){
		List<Tile> tiles = new ArrayList<Tile>();
		if(fill) {
			for(int y = y0; y < y1; y++) {
				for(int x = x0; x < x1; x++) {
					tiles.add(get_tile(x, y));
				}
			}
		} else {
			tiles.addAll(plot_line(x0, y0, x1, y0, false));
			tiles.addAll(plot_line(x0, y1, x1, y1, false));
			tiles.addAll(plot_line(x0, y0, x0, y1, false));
			tiles.addAll(plot_line(x1, y0, x1, y1, false));
		}
		return tiles;
	}
	
	
	public void set_tile(int x,int y, Tile tile) {
		tiles[y][x] = tile;
	}
	
	
	public Tile[][] get_tiles(){
		return this.tiles;
	}
	
	
	public Tile get_tile(int x, int y) {
		return this.tiles[y][x];
	}
	
	public int get_width() {
		return this.x_size;
	}
	
	public int get_height() {
		return this.y_size;
	}
	

}
