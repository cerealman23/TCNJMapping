package com.mygdx.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;


public class OurInputProcessor implements InputProcessor {

    Array<Vector2> start = new Array<>(), end = new Array<>();

    boolean justClickedFirst = true, getJustClickedSecond = true;

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        Vector3 cords = new Vector3(screenX, screenY, 0);

        TheCollegeOfNewJersey.viewport.getCamera().unproject(cords);

        Vector2 cord2 = new Vector2(cords.x, cords.y);

        start = new Array<>();

        if (button == Input.Buttons.LEFT) {



            CreateFile.createFile();

            TheCollegeOfNewJersey.filemanager.writeCordinates(new Vector2(screenX, screenY));

            TheCollegeOfNewJersey.circles.add(cord2);

            return true;
        }

        if (button == Input.Buttons.RIGHT) {


                start.add(MathFunctions.edgeSnap(cord2, TheCollegeOfNewJersey.circles));

                justClickedFirst = false;

                System.out.println("Clicked");



            return true;
        }

        return false;

    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        Vector3 cords = new Vector3(screenX, screenY, 0);


        TheCollegeOfNewJersey.viewport.getCamera().unproject(cords);

        Vector2 cord2 = new Vector2(cords.x, cords.y);

        justClickedFirst = true;

        if (button == Input.Buttons.RIGHT) {



            end.add(MathFunctions.edgeSnap(cord2, TheCollegeOfNewJersey.circles));

            Array<Vector2> values = new Array<>();

            for (Vector2 vec : start)
                values.add(vec);

            for (Vector2 vec : end)
                values.add(vec);

            TheCollegeOfNewJersey.lines.add(values);

            end.clear();
            start.clear();

            System.out.println(values);

            return true;
        }

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
