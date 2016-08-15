package game;


import input.Keyboard;
import math.Vector2f;
import graphics.SpriteSheet;
import graphics.View;
import java.awt.image.BufferedImage;

import character.Character;

/**
 *
 * @author Herman Hallstedt
 * @version 2015-04-01
 */
public class Player extends Character
{
    private boolean newData;
    private Vector2f prevVel = new Vector2f();
    
    public Player(BufferedImage image)
    {
        super(0, 0, image, true);
    }

    public void update(Keyboard key, double delta, View view)
    {
        prevVel.copy(movement.velocity);
        
        movement.stop();
        
        if(key.up.isHeld() || key.wkey.isHeld())
            movement.moveUp();
        if(key.down.isHeld() || key.skey.isHeld())
        	movement.moveDown();
        if(key.left.isHeld() || key.akey.isHeld())
        	movement.moveLeft();
        if(key.right.isHeld() || key.dkey.isHeld())
        	movement.moveRight();
        if(key.zkey.isHeld())
            attack(movement.direction);
        
        movement.move(delta);
        if(movement.direction == 90 || movement.direction == 270) {
        	//xScaleLocal = (float)Math.sin(Math.toRadians(movement.direction));
        	xScaleLocal = 1;
        }
        
        if(!movement.velocity.compare(prevVel))
            newData = true;

        view.setPosition(x, y);
        depth = (int)-y;
    }
    
    public boolean hasNewData()
    {
        if(newData) {
            newData = false;
            return true;
        } else
            return false;    
    }
}
