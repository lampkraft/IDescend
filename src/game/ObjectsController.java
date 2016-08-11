package game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import character.Character;
import graphics.Tile;

public class ObjectsController {
	
	public static List<Character> characters = new ArrayList<Character>();
	public static List<Tile> tiles = new ArrayList<Tile>();
	public static List<Entity> objects = new ArrayList<Entity>();
	public static Iterator<Entity> objectsIterator;
	public static Iterator<Tile> tilesIterator;
	public static Iterator<Character> characterIterator;
	
	public static int getNoOfCharacters() {
		return characters.size();
	}
	
	public static int getNoOfTiles() {
		return tiles.size();
	}
	
	public static int getNoOfObjects() {
		return objects.size();
	}
	
	public static void addCharacter(Character character) {
		characters.add(character);
	}
	
	public static void addTile(Tile tile) {
		tiles.add(tile);
	}
	
	public static void addObject(Entity e) {
		objects.add(e);
	}
	
	public static void deleteObject(int index) {
		objects.remove(index);
	}
}
