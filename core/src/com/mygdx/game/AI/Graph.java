package com.mygdx.game.AI;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.DefaultGraphPath;
import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.ai.pfa.Heuristic;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.mygdx.game.DebugDrawer;
import com.mygdx.game.TheCollegeOfNewJersey;

import java.util.ArrayList;

public class Graph implements IndexedGraph<Node> {

    ArrayList<Node> list;

    GraphPath<Node> finalPath;

    ObjectMap<Node, Array<Connection<Node>>> college = new ObjectMap<>();
    ArrayList<NodeConnections> edges = new ArrayList<>();     // All edges in the graph

    MyHeuristic heuristic = new MyHeuristic();


    public GraphPath<Node> makePath(Node start, Node end) {

        GraphPath<Node> defPath = new DefaultGraphPath<>();
        new IndexedAStarPathFinder<Node>(this).searchNodePath(start, end, heuristic, defPath);
        return defPath;

    }

    public void setFinalPath(GraphPath<Node> finalPath) {

        this.finalPath = finalPath;

    }

    public GraphPath<Node> getFinalPath() {

        return finalPath;

    }

    public void connectBuilding(Node toCity, Node fromCity) {

        NodeConnections path1 = new NodeConnections(fromCity, toCity);
        NodeConnections path2 = new NodeConnections(toCity, fromCity);
        if (!college.containsKey(fromCity)) { // Checks if from city is in the map

            college.put(fromCity, new Array<Connection<Node>>());


        }

        if (!college.containsKey(toCity)) { // Checks if from city is in the map

            college.put(toCity, new Array<Connection<Node>>());


        }

        college.get(fromCity).add(path1);
        college.get(toCity).add(path2);

        


    }

    public void setCollege(ObjectMap<Node, Array<Connection<Node>>> college) {

        this.college = college;

    }

    @Override
    public int getIndex(Node node) {
        return node.getIndex();
    }

    public ObjectMap<Node, Array<Connection<Node>>> getCollege() {

        return college;

    }

    @Override
    public int getNodeCount() {
        return Node.count;
    }

    @Override
    public Array<Connection<Node>> getConnections(Node fromNode) {

        if (college.containsKey(fromNode)) {

            return college.get(fromNode);

        }

        System.out.println("No Connections ::Graph.java::");

        return new Array<>(0);

    }

    public int getConnectionCount() {

        int count = 0;

        for (ObjectMap.Entry<Node, Array<Connection<Node>>> nodes : college.entries()) {

            count += nodes.value.size;

        }

        return count / 2;   // Divide by two because current graph is bi directional



    }

    public void clear() {

        college.clear();
        Node.count = 0;

    }
}
