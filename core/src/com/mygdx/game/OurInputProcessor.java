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

    private Node startNode, endNode;

    ArrayList<Node> stack = new ArrayList<>();

    ObjectMap<Node, Array<Connection<Node>>> college = new ObjectMap<>();

    private ArrayList<Vector2> circles;
    private ArrayList<Array<Vector2>> lines;
    private CreateFile fileManager;
    private GraphPath<Node> finalPath;

    private OrthographicCamera camera;

    private Graph finalGraph;

    private float max = 0;

    // Must set max seperately
    public OurInputProcessor (ArrayList<Vector2> circles, ArrayList<Array<Vector2>> lines, Graph finalGraph, GraphPath<Node> finalPath) {

        this.circles = circles;
        this.lines = lines;
        this.fileManager = fileManager;
        this.finalPath = finalPath;

        this.finalGraph = finalGraph;
        this.camera = camera;

        finalGraph.setCollege(college);

    }

    public void setMax(float max) {

        this.max = max;

    }


    Node start = new Node(), end = new Node();

    public float generateTxtFile() {

        fileManager = new CreateFile(finalGraph);

        fileManager.writeCordinates(startNode, endNode);

        if (startNode != null && endNode != null)
            return Dijkstra.calculateDijkstra();

        return 0f;

    }

    public void generate() {

        if (startNode != null && endNode != null) {

            if (stack.size() >= 2)
                finalGraph.setFinalPath(finalGraph.makePath(startNode, endNode));

        }





    }

    private boolean setStartNode = false, setEndNode = false;

    public void setStartNode() {

        setStartNode = true;
        setEndNode = false;

    }

    public void setEndNode() {

        setEndNode = true;
        setStartNode = false;

    }

    public void clear() {

        college.clear();
        finalGraph.clear();
        stack.clear();
        if (finalGraph.getFinalPath() != null)
            finalGraph.getFinalPath().clear();

    }


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

    private boolean firstPress = true;

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        Vector3 cords = new Vector3(screenX, screenY, 0);

        TheCollegeOfNewJersey.viewport.getCamera().unproject(cords);

        Vector2 cord2 = new Vector2(cords.x, cords.y);

        if (cord2.x < max) {

            if (button == Input.Buttons.LEFT && setStartNode) {

                startNode = MathFunctions.edgeSnap(cord2, college);
                finalGraph.setStart(startNode);
                setStartNode = false;

            } else if (button == Input.Buttons.LEFT && setEndNode) {

                endNode = MathFunctions.edgeSnap(cord2, college);
                finalGraph.setEnd(endNode);
                setEndNode = false;

            }

            else if (button == Input.Buttons.LEFT) {


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
