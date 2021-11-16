package com.mygdx.game.AI;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.utils.ObjectMap;
import com.google.gson.Gson;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SaveAndLoad {

    private FileHandle file;
    private FileHandle fileForConnections;


    public SaveAndLoad() {

        fileForConnections = new FileHandle(new File("C:\\Users\\mango\\Desktop\\JSON\\file2.json"));


    }

    public void save(ObjectMap<Node, Array<Connection<Node>>> nodesInMap) {

        // convert map to JSON string

        ArrayList<Node> theNodes = new ArrayList<>();
        ArrayList<Array<Connection<Node>>> connection = new ArrayList<>();

        for (ObjectMap.Entry<Node, Array<Connection<Node>>> nodes : nodesInMap.entries()) {

            theNodes.add(nodes.key);

            connection.add(nodes.value);

        }

        file = new FileHandle(new File("C:\\Users\\mango\\Desktop\\JSON\\file1.json"));


        Json json = new Json();
        json.setOutputType(JsonWriter.OutputType.json);




        System.out.println(json);


        System.out.println(json.prettyPrint(theNodes));


        file.writeString(json.prettyPrint(theNodes), false);
        fileForConnections.writeString(json.prettyPrint(connection), false);





    }

    private String json = "";

    public ObjectMap<Node, Array<Connection<Node>>> load() {

        //Map<String, Object> map = new Gson().fromJson(json, Map.class);

        Json json1 = new Json();

        ArrayList<Node> theNode = json1.fromJson(ArrayList.class, file);

        ArrayList<Array<Connection<Node>>> connections = json1.fromJson(ArrayList.class, fileForConnections);

        System.out.println(connections);

        ObjectMap<Node, Array<Connection<Node>>> floats = new ObjectMap<>();

        for (Node node : theNode) {

            System.out.println(node);
            floats.put(node, new Array<Connection<Node>>());


        }




;
/*

        ObjectMap<Node, Array<Connection<Node>>> floats = json1.fromJson(, ObjectMap.class);

        System.out.println(floats + "Floats");

        for (ObjectMap.Entry<Node, Array<Connection<Node>>> nodes : floats.entries()) {

         nodes.key.getPostion();

        }

        /*
        ObjectMap<Node, Array<Connection<Node>>> returnmap = json1.fromJson(ObjectMap.class, file);

        System.out.println(returnmap);
*/


        return floats;

    }

    public class ObjectMapWrapper {

        public ObjectMap<Node, Array<Connection<Node>>> data = new ObjectMap<>();

    }


}
