package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

public class MathFunctions {

    public static Vector2 edgeSnap (Vector2 start, ArrayList<Vector2> points) {



        Vector2 returnVector = new Vector2();
        float min = Float.MAX_VALUE;

        for (Vector2 vec : points) {

            float distance = (new Vector2(vec.x - start.x, vec.y - start.y)).len();
            System.out.println("The Length: " + distance);

            if (distance < min) {

                returnVector = vec;
                min = distance;

            }


        }

        System.out.println(returnVector);

        return returnVector;


    }

}
