package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class TheCollegeOfNewJersey extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	private OrthographicCamera camera;
	public static FillViewport viewport;

	float cameraWidth, cameraHeight, worldUnits;

	Sprite sprite = new Sprite();

	static {

		float cameraWidth = 800;
		float cameraHeight = 800;
		float worldUnits = 1;

	}

	
	@Override
	public void create () {

		batch = new SpriteBatch();
		sprite = new Sprite(new Texture(Gdx.files.internal("map.png")));
		sprite.setPosition(0,0);

		//img = new Texture("map.png");

		cameraWidth = sprite.getWidth();
		cameraHeight = sprite.getHeight();

		camera = new OrthographicCamera(cameraWidth, cameraHeight);
		camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
		viewport = new FillViewport(camera.viewportWidth, camera.viewportHeight, camera);

		System.out.println(cameraWidth);






		//camera.translate(0,0);

		viewport.apply();




	}

	@Override
	public void render () {


		camera.update();

		ScreenUtils.clear(1, 0, 0, 1);
	batch.setProjectionMatrix(viewport.getCamera().combined);

		batch.begin();
		//batch.draw(img, 0, 0);

		sprite.draw(batch);

		batch.end();

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

	@Override
	public void resize(int width, int height){

		viewport.update(width,height);
		camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
	}
}
