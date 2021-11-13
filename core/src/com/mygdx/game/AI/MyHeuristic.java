package com.mygdx.game.AI;

import com.badlogic.gdx.ai.pfa.Heuristic;

public class MyHeuristic implements Heuristic<Node> {
    @Override
    public float estimate(Node node, Node endNode) {
        return 0;
    }
}
