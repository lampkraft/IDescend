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
        prevVel.copy(velocity);
        
        stop();
        
        if(key.up.isHeld() || key.wkey.isHeld())
            moveUp();
        if(key.down.isHeld() || key.skey.isHeld())
            moveDown();
        if(key.left.isHeld() || key.akey.isHeld())
            moveLeft();
        if(key.right.isHeld() || key.dkey.isHeld())
            moveRight();
        if(key.zkey.isHeld())
            attack(direction);
        
        move(delta);
        if(direction == 90 || direction == 270) {
        	xScaleLocal = (float)Math.sin(Math.toRadians(direction));
        }
        
        if(!velocity.compare(prevVel))
            newData = true;

        view.setPosition(xPos, yPos);
        depth = (int)-yPos;
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
