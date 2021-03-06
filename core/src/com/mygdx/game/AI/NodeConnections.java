package com.mygdx.game.AI;


import com.badlogic.gdx.ai.pfa.Connection;

public class NodeConnections implements Connection<Node> {

    transient Node fromNode;
    transient Node toNode;
    float cost;

    public NodeConnections() {



    }

    public NodeConnections(Node fromNode, Node toNode) {

        this.fromNode = fromNode;
        this.toNode = toNode;

    }

    @Override
    public float getCost() {
        return cost;
    }

    @Override
    public Node getFromNode() {
        return fromNode;
    }

    @Override
    public Node getToNode() {
        return toNode;
    }
}
