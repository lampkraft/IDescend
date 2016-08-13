package events;

import character.Character;
import game.ObjectsController;

public class Attacking extends Event {
	
	Character self;
	
	public Attacking(Character self) {
		this.self = self;
	}
	
	public void trigger() {
		
		if(self.getDirection() == 90) {//hitting right
			
			for(Character target : ObjectsController.characters) {
				if(self.getXPosCenter() + self.getAttributes().attackRange > target.getX() && self.getXPosCenter() < target.getXPosCenter() /*+ target.getWidth()*/ &&
						(target.getYPosCenter() > self.getY() && target.getYPosCenter() < self.getY() + self.getHeight())) {
					
					target.getAttributes().health -= 10;
					if(target.getAttributes().health <= 0) {
						target.getAnimate().setAnimation(2, target.spriteDeath, false, new Death(target));
					} else {
						target.getAnimate().setAnimation(2, target.spriteHurt, false, null);
					}
				}
			}
			
		} else if(self.getDirection() == 270) {//hitting left
			
			for(Character target : ObjectsController.characters) {
				if(self.getXPosCenter() - self.getAttributes().attackRange < target.getX() + target.getWidth()  && self.getXPosCenter() > target.getXPosCenter()  &&
						(target.getYPosCenter() > self.getY() && target.getYPosCenter() < self.getY() + self.getHeight())) {
					
					target.getAttributes().health -= 10;
					if(target.getAttributes().health <= 0) {
						target.getAnimate().setAnimation(2, target.spriteDeath, false, new Death(target));
					} else {
						target.getAnimate().setAnimation(2, target.spriteHurt, false, null);
					}
				}
			}
		}
		
		self.setIsAttacking(false);
	}
	
}
