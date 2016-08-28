package character;

import graphics.SpriteSheet;
import graphics.View;
import math.Box;
import math.Vector2f;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import events.Attacking;
import events.Death;
import game.Entity;
import game.ObjectsController;

public class Character extends Entity {

    protected int isFlipped = 0;
    protected boolean isAttacking = false;
    public SpriteSheet spriteHurt, spriteWalk, spriteAttack, spriteDeath, spriteIdle, spriteCorpse;
    protected Attributes attributes;
    protected Movement movement; 
    private Ellipse2D.Double shadow = new Ellipse2D.Double(0, 0, 0, 0);
    private AffineTransform tx;

    public Character(float x, float y, BufferedImage image, boolean animatable) {
    	
        try {
        	spriteHurt = new SpriteSheet(ImageIO.read(new File("res/sprites/mage/spr_mage_hurt_strip4.png")), 0, 0, 100, 100, 4, 1, 4);
        	spriteWalk = new SpriteSheet(ImageIO.read(new File("res/sprites/mage/spr_mage_walk_strip8.png")), 0, 0, 100, 100, 8, 1, 8);
        	spriteAttack = new SpriteSheet(ImageIO.read(new File("res/sprites/mage/spr_mage_attack_melee_strip7.png")), 0, 0, 100, 100, 7, 1, 7);
        	spriteIdle = new SpriteSheet(ImageIO.read(new File("res/sprites/mage/spr_mage_idle.png")), 0, 0, 100, 100, 1, 1, 1);
        	spriteDeath =  new SpriteSheet(ImageIO.read(new File("res/sprites/mage/spr_mage_death_strip6.png")), 0, 0, 100, 100, 6, 1, 6);
        	spriteCorpse = new SpriteSheet(ImageIO.read(new File("res/sprites/mage/spr_mage_corpse.png")), 0, 0, 100, 100, 1, 1, 1);
			//animations.add(new SpriteSheet(ImageIO.read(new File("res/sprites/character_walk.png")), 0, 0, 130, 150, 7, 4, 27));
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        this.sprite = spriteIdle;
        this.image = sprite.getSubimage(0);
        this.isAnimatable = animatable;
        this.x = x;
        this.y = y;
        this.width = sprite.getSpriteWidth();
        this.height = sprite.getSpriteHeight();
        this.angle = 50;
        this.depth = (int)-y;
        this.animate = new Animate(this);
        this.attributes = new Attributes(this, 1, 2, 50, 80, 0);//host, speed, attack delay, attack range, max health, experience
        this.movement = new Movement(this);
        this.collisionBox = new Box(this, 10, 10, 5, 5);//left, top, right, bottom
    }
    
    public void attack(int attackDir) {
    	if(!isAttacking) {
    		animate.clear();
			isAttacking = true;
	    	animate.setAnimation(attributes.attackDelay, spriteAttack, false, new Attacking(this));
    	}
    }
    
    public void die() {
    	animate.setAnimation(2, spriteDeath, false, new Death(this));
    }
    
    public BufferedImage getImage() {
    	return image;
    }
    
    public Attributes getAttributes() {
    	return attributes;
    }
    
    public Movement getMovement() {
    	return movement;
    }
    
    public Animate getAnimate() {
    	return animate;
    }
    
    public void setIsAttacking(boolean b) {
    	isAttacking = b;
    }
    
    public void update() {
    	//collisionBox.update();
    	animate.update();
    }
    
    public void draw(Graphics2D g, View view, BufferedImage image) {
    	
    	if(!invisible) {
	    	Vector2f drawPosition = getDrawPosition(view);
	        Vector2f drawScale = getDrawScale(view);
	        
	        g.setColor(new Color(0f, 0f, 0f, 0.3f));
	        shadow.setFrame((int)(drawPosition.x - 35), (int)drawPosition.y + height-13, 60, 10);
	        tx = AffineTransform.getRotateInstance(Math.toRadians (0), (int)(drawPosition.x - 35)+60/2, (int)(drawPosition.y + height-13)+10/2);
	        Shape rotatedShadow = tx.createTransformedShape(shadow);
	        
	        g.fill(rotatedShadow);
	        
	        g.drawImage(image, (int)(drawPosition.x-(xScaleLocal*(width/2))), (int)drawPosition.y, (int)(drawScale.x*xScaleLocal), (int)drawScale.y, null);
	        if(collisionBox != null) collisionBox.draw(g, drawPosition, drawScale);
	        g.setColor(new Color(0f, 0f, 0f, 1f));
	        g.drawRect(
					(int)(drawPosition.x-((width/2))),
					(int)(drawPosition.y),
					(int)((drawScale.x)),
					(int)(drawScale.y)
					);
	        attributes.drawAttackRange(g, drawPosition, drawScale, xScaleLocal);
	    	
    	}
    }
}

