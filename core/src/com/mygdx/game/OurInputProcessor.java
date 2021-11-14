package com.mygdx.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.mygdx.game.AI.Graph;
import com.mygdx.game.AI.Node;
import com.mygdx.game.AI.NodeConnections;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Stack;

public class OurInputProcessor implements InputProcessor {

    ArrayList<Node> graph = new ArrayList<>();

    ArrayList<Node> stack = new ArrayList<>();

    ObjectMap<Node, Array<Connection<Node>>> college = new ObjectMap<>();

    private ArrayList<Vector2> circles;
    private ArrayList<Array<Vector2>> lines;
    private CreateFile fileManager;
    private GraphPath<Node> finalPath;

    private OrthographicCamera camera;

    private Graph finalGraph;

    public OurInputProcessor (ArrayList<Vector2> circles, ArrayList<Array<Vector2>> lines, Graph finalGraph, GraphPath<Node> finalPath) {

        this.circles = circles;
        this.lines = lines;
        this.fileManager = fileManager;
        this.finalPath = finalPath;

        this.finalGraph = finalGraph;
        this.camera = camera;

        finalGraph.setCollege(college);

    }


    Node start = new Node(), end = new Node();

    @Override
    public boolean keyDown(int keycode) {

        if (keycode == Input.Keys.SPACE) {

            finalGraph.setFinalPath(finalGraph.makePath(stack.get(0), stack.get(stack.size() - 1)));
            fileManager = new CreateFile(finalGraph);

            fileManager.writeCordinates();

        }

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

    private boolean firstPress = true;

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        Vector3 cords = new Vector3(screenX, screenY, 0);

        TheCollegeOfNewJersey.viewport.getCamera().unproject(cords);

        Vector2 cord2 = new Vector2(cords.x, cords.y);

        if (button == Input.Buttons.LEFT) {



            CreateFile.createFile();

           // fileManager.writeCordinates(new Vector2(screenX, screenY));

            // Adds a new node to the college map
            Node delete = new Node(cord2);

            stack.add(delete);

            college.put(delete, new Array<Connection<Node>>());



           //circles.add(cord2);

            return true;
        }

        if (button == Input.Buttons.RIGHT) {

            // snaps to closest node

                start = (MathFunctions.edgeSnap(cord2, college));

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

        if (button == Input.Buttons.RIGHT) {

            end = MathFunctions.edgeSnap(cord2, college);

            finalGraph.connectBuilding(start, end);




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

    private float zoomValues = 0;

    public float zoomValue() {


        return zoomValues;

    }

    @Override
    public boolean scrolled(float amountX, float amountY) {

        zoomValues += amountX;

        return false;
    }
}
