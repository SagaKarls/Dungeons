package com.saga.dungeons;

import java.util.HashMap;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class Dungeons extends ApplicationAdapter {
	SpriteBatch batch;
	TextureAtlas texture_atlas;
	HashMap<String, Sprite> sprites = new HashMap<String, Sprite>();
	Game_map current_map = new Game_map(Map_type.DUNGEON, 95, 50);
	ExtendViewport viewport;
	OrthographicCamera camera;
	
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		texture_atlas = new TextureAtlas("tiles.txt");
		camera = new OrthographicCamera();
		viewport = new ExtendViewport(1200, 800, camera);
		load_sprites();
	}
	
	private void load_sprites() {
		/* Loads sprites from the texture atlas into a hashmap */
		Array<AtlasRegion> regions = texture_atlas.getRegions();
		for(AtlasRegion region : regions) {
			String name = region.name;
			Sprite sprite = texture_atlas.createSprite(name);
			sprites.put(name, sprite);
		}
	}
	

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		draw_map(current_map);
		batch.end();
	}
	
	private void draw_map(Game_map map) {
		Sprite sprite;
		for(int y = 0; y < map.get_height(); y++) {
			for(int x = 0; x < map.get_width(); x++) {
				sprite = sprites.get(map.get_tile(x, y).get_texture());
				sprite.setPosition(16 * x, 16 * y);
				sprite.draw(batch);
			}
		}
	}
	
	@Override
	public void resize(int width, int height) {
		viewport.update(width, height, true);
		batch.setProjectionMatrix(camera.combined);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		texture_atlas.dispose();
		sprites.clear();
	}
}
