package events;

import game.Entity;
import game.ObjectsController;
import graphics.Tile;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;

import character.Character;

public class Death extends Event  {
	
	private Tile tileCorpse;
	private Character character;
	private Iterator<Entity> it;
	
	public Death(Character c) {
		character = c;
		tileCorpse = new Tile(
				character.getX(),
				character.getY(),
				character.getWidth(),
				character.getHeight(),
				character.getDepth(),
				character.spriteCorpse,
				false
				);
	}
	
	public void trigger() {
		//character.setInvisible(true);
		ObjectsController.addTile(tileCorpse);
		
		Entity c;
		
		ObjectsController.objectsIterator = ObjectsController.objects.iterator();
		while (ObjectsController.objectsIterator.hasNext()) {
		    c = ObjectsController.objectsIterator.next();
		    if (character == c) {
		    	ObjectsController.objectsIterator.remove();
		    }
		}
		
		ObjectsController.characterIterator = ObjectsController.characters.iterator();
		while (ObjectsController.characterIterator.hasNext()) {
		    c = ObjectsController.characterIterator.next();
		    if (character == c) {
		    	ObjectsController.characterIterator.remove();
		    }
		}
		
		System.gc();//Invoke the garbage collector
	}
}
