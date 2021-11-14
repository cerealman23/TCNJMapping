package com.mygdx.game;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.mygdx.game.AI.Graph;
import com.mygdx.game.AI.Node;

import java.io.File;
import java.io.FileWriter;

public class CreateFile {

    int count = 0;

    private Graph graph;

    public CreateFile(Graph graph) {

        this.graph = graph;

        try {

            writer = new FileWriter("C:\\Users\\mango\\Documents\\MLH\\android\\assets\\textFiles\\nodes.txt");

            System.out.println("Wrote");



        } catch (Exception ecx) {

            System.out.println("Couldn't write");

        }


    }

    private static FileWriter writer;

    public static final void createFile() {


        File nodeFile = new File("C:\\Users\\mango\\Documents\\MLH\\android\\assets\\textFiles\\nodes.txt");

        try {

            if (nodeFile.createNewFile()) {

                System.out.println("File created: " + nodeFile.getName());

            }  else {
                System.out.println("File already exists.");

            }

        } catch (Exception exception) {


            System.out.println("Error in file creation");

        }



    }

    public final void writeCordinates(Node startNode, Node endNode) {

        try {



            //writer.append((int)mouse.x +  " " + (int)mouse.y + " " + count + "\n");

            writer.append(graph.getNodeCount() + " " + graph.getConnectionCount() + " ");
            writer.append(startNode.getIndex() + " " + endNode.getIndex() + " ");

            String finalString = "";

            // Gets node
            for (ObjectMap.Entry<Node, Array<Connection<Node>>> nodes : graph.getCollege().entries()) {

                finalString = "";

                //finalString += nodes.key.getIndex();


                for (Connection<Node> values : nodes.value) {

                    // Calculate weight
                    float distance = new Vector2(nodes.key.getPostion().x  - values.getToNode().getPostion().x, nodes.key.getPostion().y  - values.getToNode().getPostion().y).len();

                    finalString+= nodes.key.getIndex() < values.getToNode().getIndex() ?
                            nodes.key.getIndex() + " " + values.getToNode().getIndex() + " " + (int)distance + " " : "";



                }

                writer.append(finalString);

            }


            // Start Node


            // End Node

            count++;

            writer.flush();

        } catch (Exception ecx) {

            System.out.println("Couldn't write");

        }

    }

}
