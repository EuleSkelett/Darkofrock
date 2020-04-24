package ru.euleskelett.darkofrock.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.euleskelett.darkofrock.control.PlayerController;



public class Player extends GameObject{

    private PlayerController playerController;
    public Player(TextureRegion textureRegion, float x, float y, float width, float height) {
        super(textureRegion, x, y, width, height);
        playerController = new PlayerController(bouds);
    }
    @Override
    public void draw(SpriteBatch batch){
        super.draw(batch);
        playerController.handle();
    }
}
