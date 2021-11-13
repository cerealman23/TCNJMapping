package com.mygdx.game.AI;


import com.badlogic.gdx.ai.pfa.Connection;

public class NodeConnections implements Connection<Node> {
    @Override
    public float getCost() {
        return 0;
    }

    @Override
    public Node getFromNode() {
        return null;
    }

    @Override
    public Node getToNode() {
        return null;
    }
}
