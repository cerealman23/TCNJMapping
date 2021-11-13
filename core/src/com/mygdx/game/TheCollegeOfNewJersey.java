package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;

public class TheCollegeOfNewJersey extends ApplicationAdapter {

	protected static ArrayList<Vector2> circles = new ArrayList<>();
	protected static ArrayList<Array<Vector2>> lines = new ArrayList<>();

	private Vector3 mouseCordinates =  new Vector3();
	protected static CreateFile filemanager = new CreateFile();



	SpriteBatch batch;
	Texture img;
	private OrthographicCamera camera;
	public static FillViewport viewport;

	float cameraWidth, cameraHeight, worldUnits;

	// Input file

	OurInputProcessor inputProcessor = new OurInputProcessor();


	Sprite sprite = new Sprite();

	static {

		float cameraWidth = 800;
		float cameraHeight = 800;
		float worldUnits = 1;

	}

	
	@Override
	public void create () {

		Gdx.input.setInputProcessor(inputProcessor);



		batch = new SpriteBatch();
		sprite = new Sprite(new Texture(Gdx.files.internal("map.png")));
		sprite.setPosition(0,0);

		//img = new Texture("map.png");

		cameraWidth = sprite.getWidth();
		cameraHeight = sprite.getHeight();

		camera = new OrthographicCamera(cameraWidth, cameraHeight);
		camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
		viewport = new FillViewport(camera.viewportWidth, camera.viewportHeight, camera);

		//camera.translate(0,0);

		viewport.apply();




	}

	private void updateMouse() {

		mouseCordinates.x = Gdx.input.getX();
		mouseCordinates.y = Gdx.input.getY();


	}

	@Override
	public void render () {


		updateMouse();
		camera.update();

		viewport.getCamera().unproject(mouseCordinates);

		ScreenUtils.clear(1, 0, 0, 1);

		batch.setProjectionMatrix(viewport.getCamera().combined);

		batch.begin();
		//batch.draw(img, 0, 0);

		sprite.draw(batch);

		batch.end();

		for (Vector2 point : circles) {


			DebugDrawer.DrawDebugCircle(point, 30, 7, Color.PURPLE, TheCollegeOfNewJersey.viewport.getCamera().combined);

		}

		for (Array<Vector2> point : lines) {


			DebugDrawer.DrawDebugLine(point.get(0), point.get(1), 7, Color.PURPLE, TheCollegeOfNewJersey.viewport.getCamera().combined);

		}

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
