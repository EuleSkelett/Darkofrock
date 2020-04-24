package ru.euleskelett.darkofrock.control;
/*
* Управление персонажем
* */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import ru.euleskelett.darkofrock.view.GameScreen;

public class PlayerController {
    private float playerSpeed, velosity = 5f, speedMax = 5f;
    private float rotateSpeed = 500f;
    private Polygon playerBounds;
    public PlayerController(Polygon playerBounds){
        this.playerBounds = playerBounds;
    }
// все что связанно со скоростью
    public void handle(){
        // движение вперед назад
        if (Gdx.input.isKeyPressed(Input.Keys.UP))
            playerSpeed += velosity* GameScreen.deltaCff;
        else if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
            playerSpeed -= velosity* GameScreen.deltaCff;
        else
            quenchSpeed();
        //

        playerSpeed = sliceSpeed();

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            playerBounds.rotate(rotateSpeed * GameScreen.deltaCff);
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            playerBounds.rotate(-rotateSpeed * GameScreen.deltaCff);



        playerMovement();


    }
    private  void playerMovement(){
        playerBounds.setPosition(playerBounds.getX() + MathUtils.cosDeg(playerBounds.getRotation() +90) * playerSpeed * GameScreen.deltaCff,
                playerBounds.getY() + MathUtils.sinDeg(playerBounds.getRotation() + 90) * playerSpeed * GameScreen.deltaCff);
    }

    private void playerSideMovement(){
        playerBounds.setPosition(playerBounds.getX() + MathUtils.cosDeg(playerBounds.getRotation() ) * playerSpeed * GameScreen.deltaCff,
                playerBounds.getY() + MathUtils.sinDeg(playerBounds.getRotation() ) * playerSpeed * GameScreen.deltaCff);
    }
    // гасим скорость
    private void quenchSpeed() {
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
