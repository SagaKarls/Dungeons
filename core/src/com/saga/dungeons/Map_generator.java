package com.saga.dungeons;

import java.util.ArrayList;
import java.util.List;

public class Map_generator {

	public static void generate(Game_map map, int width, int height) {
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				map.set_tile(x, y, new Tile(Tile_type.EMPTY, "void", x, y));
			}
		}
		rectangular_room(map, 10, 10, 60, 30);
		fill(map.plot_line(60, 45, 10, 4, true), Tile_type.WALL);
	}
	
	
	private static void rectangular_room(Game_map map,int x0,int y0,int x1,int y1) {
		fill(map.get_rectangle(x0, y0, x1, y1, false), Tile_type.WALL);
		fill(map.get_rectangle(x0 + 1, y0 + 1, x1, y1, true), Tile_type.FLOOR);
	}
	
	private static void fill(List<Tile> tiles, Tile_type type) {
		for(Tile tile : tiles) {
			tile.set_type(type);
		}
	}
	
	
}
	
