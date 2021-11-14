package com.mygdx.game.AI;

import com.badlogic.gdx.math.Vector2;

public class Node {

    public static int count = 0;
    private float x, y;
    private Vector2 postion = new Vector2();
    private int index;

    public Node() {



    }
    public Node(float x, float y) {

        this.x = x;
        this.y = y;
        this.postion = new Vector2(x,y);
        this.index = count;
        count++;

    }

    public Node(Vector2 vector) {

        this.x = vector.x;
        this.y = vector.y;
        this.postion = new Vector2(x,y);
        this.index = count;
        count++;

    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getIndex() {
        return index;
    }

    public Vector2 getPostion() {
        return postion;
    }

    public void clear() {

        this.x = 0;
        this.y = 0;
        this.postion.x = x;
        this.postion.y = y;

    }
}
