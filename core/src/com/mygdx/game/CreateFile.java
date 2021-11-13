package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.io.File;
import java.io.FileWriter;

public class CreateFile {

    int count = 0;

    public CreateFile() {

        try {

            writer = new FileWriter("C:\\Users\\mango\\Documents\\MLH\\android\\assets\\textFiles\\nodes.txt");

            System.out.println("Succesfuly Wrote to a file");

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

    public final void writeCordinates(Vector2 mouse) {

        try {

            writer.append((int)mouse.x +  " " + (int)mouse.y + " " + count + "\n");
            count++;

            writer.flush();

        } catch (Exception ecx) {

            System.out.println("Couldn't write");

        }

    }

}
