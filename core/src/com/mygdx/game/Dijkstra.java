package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.AI.Node;

import java.util.Scanner;
import java.io.File;
import java.io.*;
import java.util.*;


public class Dijkstra{
    private static float dijkstra(float[][] adjacencyMatrix, int endNode){
        int v = adjacencyMatrix.length;
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < adjacencyMatrix[0].length; j++) {
                //System.out.print(adjacencyMatrix[i][j] + " ");
            }
            //System.out.println();
        }
        boolean visited[] = new boolean[v];
        float distance[] = new float[v];
        distance[0]=0;
        for(int i = 1; i < v; i++){
            distance[i] = Integer.MAX_VALUE;
        }
        for(int i = 0; i < v -1; i++){
            int minVertex = findMinVertex(distance,visited);
            visited[minVertex] = true;
            //System.out.println(minVertex);

            for(int j = 0; j < v; j++){

                if(adjacencyMatrix[minVertex][j] != 0 && !visited[j] && distance[minVertex] != Integer.MAX_VALUE){

                    if (j == 5) {

                        //System.out.println(distance[minVertex] + " " + adjacencyMatrix[minVertex][j] + " " + j);
                        for (int k = 0; k < adjacencyMatrix[minVertex].length; k++) {
                        }
                    }
                    float newDist = distance[minVertex] + adjacencyMatrix[minVertex][j];

                    if(newDist<distance[j]){
                        distance[j]= newDist;
                    }
                }
            }
        }
    /*for(int i = 0; i < v; i++){
      System.out.println(i+" "+distance[i]);
    }*/
        return distance[endNode];
    }

    private static int findMinVertex(float[] distance, boolean visited[]){
        int minVertex = -1;
        for(int i = 0; i < distance.length; i++){
            if(!visited[i] && (minVertex == -1 || distance[i] < distance[minVertex])){
                minVertex = i;
            }
        }
        return minVertex;
    }

    public static float calculateDijkstra() {

        Scanner s = null;
        try {
            s = new Scanner (new File("C:\\Users\\mango\\Documents\\MLH\\android\\assets\\textFiles\\nodes.txt"));
        }
        catch (Exception e) {
            System.out.println("could not find file");
        }
        int v = s.nextInt();
        int e = s.nextInt();
        int startNode = s.nextInt();
        int endNode = s.nextInt();

        int temp = -1;
        if (startNode > endNode) {
            temp = startNode;
            startNode = endNode;
            endNode = temp;
        }

        endNode = endNode - startNode;

        float adjacencyMatrix[][] = new float[v][v];
        for(int i = 0; i < e; i++){
            int v1 = s.nextInt() - startNode;

            int v2 = s.nextInt() - startNode;

            if (v1 < 0) {
                v1 = v1 + v;
            }

            if (v2 < 0) {
                v2 = v2 + v;
            }

            float weight = s.nextFloat();

            adjacencyMatrix[v1][v2] = weight;
            adjacencyMatrix[v2][v1] = weight;
        }

        float x = dijkstra(adjacencyMatrix, endNode);
        System.out.println(x);

        return x;

    }

}