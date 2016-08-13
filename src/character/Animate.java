package character;

import events.Event;
import graphics.SpriteSheet;

public class Animate {
	
	private Character actor;
	private int frame = 0;
	private float delay = 1, timer = 0;
	private boolean isPaused = false, loop = true, stop = false;
	private Event startEvent, endEvent;
	 
	public Animate(Character actor) {
		this.actor = actor;
	}
	
	public void setAnimation(float delay, SpriteSheet sprite, boolean loop, Event endEvent) {
		this.delay = delay;
		this.endEvent = endEvent;
		this.loop = loop;
		play(sprite, endEvent);
	}
	
	public void play(SpriteSheet s, Event end) {
		if(end != null) {
			endEvent = end;
		}
		
		isPaused = false;
		actor.setSprite(s);
	}
	
	public void pause() {
		isPaused = true;
	}
	
	public void clear() {
		frame = 0;
	}
	
	public float getAnimationDelay() {
		return delay;
	}
	
	public void update() {
		if(actor.getIsAnimatable() && !isPaused && !stop) {
			timer--;
			if(timer <= 0) {
				frame++;
				if(frame >= actor.getSprite().getNumberOfFrames()) {
					if(endEvent != null) {
						endEvent.trigger();
					}
					frame = 0;
					if(!loop) {
						System.out.println("ANIMATE END");
						setAnimation(2, actor.spriteIdle, false, null);
						loop = true;
					}
				}
				actor.animate(frame);
				timer = delay;
			}
		}
	}
}
