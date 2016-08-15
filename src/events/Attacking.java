package events;

import character.Character;
import game.ObjectsController;

public class Attacking extends Event {
	
	public Character self;
	
	public Attacking(Character self) {
		this.self = self;
	}
	
	public void trigger() {
		
		if(self.getMovement().direction == 90) {//hitting right
			
			for(Character target : ObjectsController.characters) {
				
				if(target.getCollisionBox().getCollisionPoint((int)(self.getXPosCenter() + self.getAttributes().attackRange), (int)(self.getYPosCenter()))) {
					target.getAttributes().health -= 10;
					if(target.getAttributes().health <= 0) {
						target.getAnimate().setAnimation(2, target.spriteDeath, false, new Death(target));
					} else {
						target.getAnimate().setAnimation(2, target.spriteHurt, false, null);
					}
				}
			}
			
		} else if(self.getMovement().direction == 270) {//hitting left
			
			for(Character target : ObjectsController.characters) {
				
				if(target.getCollisionBox().getCollisionPoint((int)(self.getXPosCenter() - self.getAttributes().attackRange), (int)(self.getYPosCenter()))) {
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
