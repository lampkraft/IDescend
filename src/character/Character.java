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

public class Character extends Entity {

    protected Vector2f velocity = new Vector2f();
    protected float acceleration, deacceleration=0.9f, friction=0.4f;
    protected int direction, isFlipped = 0;
    protected boolean isAttacking = false;
    public SpriteSheet spriteHurt, spriteWalk, spriteAttack, spriteDeath, spriteIdle, spriteCorpse;
    public Attributes attributes;
    private Ellipse2D.Double shadow = new Ellipse2D.Double(0, 0, 0, 0);
    private AffineTransform tx;

    public Character(float x, float y, BufferedImage image, boolean animatable) {
    	
        try {
        	spriteHurt = new SpriteSheet(ImageIO.read(new File("res/sprites/mage/spr_mage_hurt_strip4.png")), 0, 0, 100, 100, 4, 1, 4);
        	spriteWalk = new SpriteSheet(ImageIO.read(new File("res/sprites/mage/spr_mage_walk_strip8.png")), 0, 0, 100, 100, 8, 1, 8);
        	spriteAttack = new SpriteSheet(ImageIO.read(new File("res/sprites/mage/spr_mage_attack_melee_strip7.png")), 0, 0, 100, 100, 7, 1, 7);
        	spriteIdle =  new SpriteSheet(ImageIO.read(new File("res/sprites/mage/spr_mage_idle.png")), 0, 0, 100, 100, 1, 1, 1);
        	spriteDeath =  new SpriteSheet(ImageIO.read(new File("res/sprites/mage/spr_mage_death_strip6.png")), 0, 0, 100, 100, 6, 1, 6);
        	spriteCorpse = new SpriteSheet(ImageIO.read(new File("res/sprites/mage/spr_mage_corpse.png")), 0, 0, 100, 100, 1, 1, 1);
			//animations.add(new SpriteSheet(ImageIO.read(new File("res/sprites/character_walk.png")), 0, 0, 130, 150, 7, 4, 27));
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        this.sprite = spriteIdle;
        this.image = sprite.getSubimage(0);
        this.isAnimatable = animatable;
        this.xPos = x;
        this.yPos = y;
        this.width = sprite.getSpriteWidth();
        this.height = sprite.getSpriteHeight();
        this.angle = 50;
        this.depth = (int)-yPos;
        this.direction = 1;
        this.animate = new Animate(this);
        this.attributes = new Attributes(this, 1, 2, 10, 100, 0);
        this.acceleration = attributes.speed;
        this.collisionBox = new Box((int)20, (int)20, (int)80, (int)80);
        
        stop();
    }
    
    public Vector2f getVelocity() {
        return velocity;
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
    
    public void move(double delta) {
    	xPos += velocity.x * delta;
        yPos -= velocity.y * delta;
    }
    
    public void stop() {
    	
    	if(!isAttacking) {
    		animate.setAnimation(2, spriteIdle, true, null);
    	}
    	
    	velocity.x = velocity.x * deacceleration;
    	velocity.y = velocity.y * deacceleration;
    }

    public void moveUp() {
    	//direction = 0;
    	if(!isAttacking) {
    		animate.setAnimation(4, spriteWalk, true, null);
    	}
    	velocity.y += (acceleration * friction);
    }

    public void moveDown() {
    	//direction = 180;
    	if(!isAttacking) {
    		animate.setAnimation(4, spriteWalk, true, null);
    	}
    	velocity.y -= (acceleration * friction);
    }

    public void moveRight() {
    	direction = 90;
    	if(!isAttacking) {
    		animate.setAnimation(4, spriteWalk, true, null);
    	}
    	velocity.x += acceleration * friction;
    }

    public void moveLeft() {
    	direction = 270;
    	if(!isAttacking) {
    		animate.setAnimation(4, spriteWalk, true, null);
    	}
    	velocity.x -= acceleration * friction;
    }
    
    public int getDirection() {
    	return direction;
    }
    
    public BufferedImage getImage() {
    	return image;
    }
    
    public Attributes getAttributes() {
    	return attributes;
    }
    
    public Animate getAnimate() {
    	return animate;
    }
    
    public Box getCollisionBox() {
    	return collisionBox;
    }
    
    public void setIsAttacking(boolean b) {
    	isAttacking = b;
    }
    
    public void update() {
    	animate.update();
    }
    
    public void draw(Graphics2D g, View view, BufferedImage image) {
    	
    	if(!invisible) {
	    	Vector2f drawPosition = getDrawPosition(view);
	        Vector2f drawScale = getDrawScale(view);
	        
	        if(xScaleLocal == -1) isFlipped = 1;
	        else isFlipped = 0;
	        g.setColor(new Color(0f, 0f, 0f, 0.3f));
	        //g.drawOval((int)(drawPosition.x-width/2), (int)drawPosition.y + height-10, (int)width, 10);
	        shadow.setFrame((int)(drawPosition.x - 35), (int)drawPosition.y + height-13, 60, 10);
	        tx = AffineTransform.getRotateInstance(Math.toRadians (0), (int)(drawPosition.x - 35)+60/2, (int)(drawPosition.y + height-13)+10/2);
	        Shape rotatedShadow = tx.createTransformedShape(shadow);
	        
	        g.fill(rotatedShadow);
	        g.setColor(new Color(1f, 0f, 0f, 1f));
	        //g.drawRect((int)(drawPosition.x-(xScaleLocal*(collisionBox.right/2))), (int)drawPosition.y, (int)(drawScale.x*xScaleLocal), (int)drawScale.y);
	        g.drawRect((int)(drawPosition.x-(xScaleLocal*(width/2)) + collisionBox.left), (int)drawPosition.y + collisionBox.top, (int)(collisionBox.right*xScaleLocal), (int)(collisionBox.bottom));
	        g.drawImage(image, (int)(drawPosition.x-(xScaleLocal*(width/2))), (int)drawPosition.y, (int)(drawScale.x*xScaleLocal), (int)drawScale.y, null);
	        //if(isAttacking) {
	    	//if(direction == 90) g.drawLine(Math.round(drawPosition.x), Math.round(drawPosition.y + height/2), Math.round(drawPosition.x + attackRange), Math.round(drawPosition.y + height/2));
	    	//else if(direction == 270) g.drawLine(Math.round(drawPosition.x), Math.round(drawPosition.y + height/2), Math.round(drawPosition.x - attackRange), Math.round(drawPosition.y + height/2));
	        	//isAttacking = false;
	        //}
    	}
    }
}

