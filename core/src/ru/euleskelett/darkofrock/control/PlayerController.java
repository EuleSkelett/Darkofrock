package ru.euleskelett.darkofrock.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import ru.euleskelett.darkofrock.view.GameScreen;


public class PlayerController {
    private float playerSpeed, velosity = 10f, speedMax = 10f;
    private float rotationSpeed = 30f;

    private Polygon playerBounds;
    public PlayerController(Polygon playerBounds){
        this.playerBounds = playerBounds;
    }
// все что связанно со скоростью


    public void handle(){
        // движение вперед назад
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            playerSpeed += velosity* GameScreen.deltaCff;
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            playerSpeed -= velosity* GameScreen.deltaCff;
        else
            downSpeed();

        playerSpeed = sliceSpeed();

        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            playerBounds.setPosition(playerBounds.getX(), 1);
        //

        playerBounds.setPosition(playerBounds.getX() + MathUtils.cosDeg(playerBounds.getRotation() +180) * playerSpeed * GameScreen.deltaCff,
                playerBounds.getY() + MathUtils.sinDeg(playerBounds.getRotation() + 180) * playerSpeed * GameScreen.deltaCff);
    }
    // гасим скорость
    private void downSpeed() {
        if (playerSpeed > velosity* GameScreen.deltaCff)
            playerSpeed -= velosity* GameScreen.deltaCff;
        else if (playerSpeed < -velosity* GameScreen.deltaCff)
            playerSpeed += velosity* GameScreen.deltaCff;
        else
            playerSpeed = 0f;
    }
    // Ограничеваем скорость
    private float sliceSpeed(){
        if (playerSpeed > speedMax)
            return speedMax;
        if (playerSpeed < -speedMax)
            return -speedMax;
        return playerSpeed;
    }
}
