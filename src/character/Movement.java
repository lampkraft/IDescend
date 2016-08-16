package character;

import math.Vector2f;

public class Movement {
	
	public Character self;
	public float acceleration, deacceleration=0.9f, friction=0.4f;
	public Vector2f velocity = new Vector2f();
	public int direction;
	
	public Movement(Character self) {
		this.self = self;
		this.acceleration = self.getAttributes().speed;
		stop();
	}
	
	public void move(double delta) {
    	self.x += velocity.x * delta;
    	self.y -= velocity.y * delta;
    }
    
    public void stop() {
    	if(!self.isAttacking) {
    		self.getAnimate().setAnimation(2, self.spriteIdle, true, null);
    	}
    	velocity.x = velocity.x * deacceleration;
    	velocity.y = velocity.y * deacceleration;
    }

    public void moveUp() {
    	if(!self.isAttacking) {
    		self.getAnimate().setAnimation(4, self.spriteWalk, true, null);
    	}
    	
    	
    	velocity.y += (acceleration * friction);
    	
    }

    public void moveDown() {
    	if(!self.isAttacking) {
    		self.getAnimate().setAnimation(4, self.spriteWalk, true, null);
    	}
    	
    	
    	velocity.y -= (acceleration * friction);
    }

    public void moveRight() {
    	direction = 90;
    	if(!self.isAttacking) {
    		self.getAnimate().setAnimation(4, self.spriteWalk, true, null);
    	}
    	
    	
    	velocity.x += acceleration * friction;
    }

    public void moveLeft() {
    	direction = 270;
    	if(!self.isAttacking) {
    		self.getAnimate().setAnimation(4, self.spriteWalk, true, null);
    	}
    	
    	
    	velocity.x -= acceleration * friction;
    }
    
}
