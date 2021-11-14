package com.mygdx.game.AI;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.utils.ObjectMap;

import java.io.File;

public class SaveAndLoad {

    private FileHandle file;

    Json json;

    public SaveAndLoad(ObjectMap<Node, Array<Connection<Node>>> nodesInMap) {

        file = new FileHandle(new File("C:\\Users\\mango\\Desktop\\JSON\\file.json"));

        json = new Json();
        file.writeString(json.prettyPrint(json.toJson(nodesInMap)), false);

        System.out.println(json.toJson(nodesInMap));

    }


}
