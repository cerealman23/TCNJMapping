package com.mygdx.game;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.mygdx.game.AI.Node;
import com.mygdx.game.AI.NodeConnections;

import java.util.ArrayList;

public class MathFunctions {

    public static Node edgeSnap (Vector2 start, ObjectMap<Node, Array<Connection<Node>>> nodesInMap) {

        Node returnNode = new Node();

        float min = Float.MAX_VALUE;

        for (ObjectMap.Entry<Node, Array<Connection<Node>>> nodes : nodesInMap.entries()) {

            float distance = (new Vector2(nodes.key.getPostion().x - start.x, nodes.key.getPostion().y - start.y)).len();
            

            if (distance < min) {

                returnNode = nodes.key;
                min = distance;

            }


        }



        return returnNode;


    }

}
