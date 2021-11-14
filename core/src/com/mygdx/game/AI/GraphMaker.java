package com.mygdx.game.AI;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;


import java.util.ArrayList;

public class GraphMaker {

    ArrayList<Vector2> nodes;
    ArrayList<Array<Vector2>> paths;

    Graph finalGraph = new Graph();

    public GraphMaker(ArrayList<Vector2> nodes, ArrayList<Array<Vector2>> paths ) {

        this.nodes = nodes;
        this.paths = paths;
        makeConnections();

    }

    private void makeConnections() {



    }



}
