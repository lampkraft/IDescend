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
    	
    	if(!self.getCollisionBox().getCollisionMeeting(0, -1)) {
    		velocity.y += (acceleration * friction);
    	} else {
    		velocity.y = 0;
    	}
    	
    }

    public void moveDown() {
    	if(!self.isAttacking) {
    		self.getAnimate().setAnimation(4, self.spriteWalk, true, null);
    	}
    	
    	if(!self.getCollisionBox().getCollisionMeeting(0, 1)) {
    		velocity.y -= (acceleration * friction);
    	} else {
    		velocity.y = 0;
    	}
    }

    public void moveRight() {
    	direction = 90;
    	if(!self.isAttacking) {
    		self.getAnimate().setAnimation(4, self.spriteWalk, true, null);
    	}
    	
    	if(!self.getCollisionBox().getCollisionMeeting(1, 0)) {
    		velocity.x += acceleration * friction;
    	} else {
    		velocity.x = 0;
    	}
    }

    public void moveLeft() {
    	direction = 270;
    	if(!self.isAttacking) {
    		self.getAnimate().setAnimation(4, self.spriteWalk, true, null);
    	}
    	
    	if(!self.getCollisionBox().getCollisionMeeting(-1, 0)) {
    		velocity.x -= acceleration * friction;
    	} else {
    		velocity.x = 0;
    	}
    }
    
}
