package com.swenkalski.level.level1;

import com.swenkalski.handler.TextureHandler;

import java.util.ArrayList;

public class Textures {

    public static ArrayList<TextureHandler> getTextures() {
        ArrayList<TextureHandler> textures = new ArrayList<>();
        textures.add(TextureHandler.wood);
        textures.add(TextureHandler.brick);
        textures.add(TextureHandler.bluestone);
        textures.add(TextureHandler.stone);
        return textures;
    }
}
