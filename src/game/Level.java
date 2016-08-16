package game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import character.Character;
import graphics.Background;
import graphics.Tile;
import graphics.View;
import math.Maths;

public class Level{
	
	private FileReader fr;
	private BufferedReader br;
	private Player player;
	private Background background;

	
	public Level(Player player, String path) {
		this.player = player;
		ObjectsController.addObject(player);
		loadLevelData(path);
		System.gc();
	}
	
	private void loadLevelData(String path) {
		
		try {
			background  = new Background(ImageIO.read(new File("res/backgrounds/fog.png")));
			fr = new FileReader(path);
			JSONParser parser = new JSONParser();
			Object o = parser.parse(fr);
			JSONObject jsonObject = (JSONObject) o;
			generateBackground();
			extractTiles(jsonObject);
			extractCharacters(jsonObject);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		br = new BufferedReader(fr);
		
		try {
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void extractTiles(JSONObject jsonObject) {
		
		JSONArray jsonArray = (JSONArray)jsonObject.get("Tiles");
		Iterator<JSONObject> i = jsonArray.iterator();
		while (i.hasNext()) {
			JSONObject inner = (JSONObject) i.next();
			
			try {
				Entity tile = new Tile(
						Float.parseFloat(inner.get("X").toString()),
						Float.parseFloat(inner.get("Y").toString()),
						Integer.parseInt(inner.get("Width").toString()),
						Integer.parseInt(inner.get("Height").toString()),
						Float.parseFloat(inner.get("Depth").toString()),
						ImageIO.read(new File((String)inner.get("Image_url"))),
						false
						);
				
				if(inner.get("Collision").toString().equals("true")) {
					tile.createCollisionBox(5, 5, 5, 5);
					tile.setIsSolid(true);
					tile.setName("Solid");
				}
				
				ObjectsController.addObject(tile);
				ObjectsController.addTile((Tile)tile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private void extractCharacters(JSONObject jsonObject) {
		
		JSONArray jsonArray = (JSONArray)jsonObject.get("Actors");
		Iterator<JSONObject> i = jsonArray.iterator();
		while (i.hasNext()) {
			JSONObject inner = (JSONObject) i.next();
			
			try {
				Entity character = new Character(
						Float.parseFloat(inner.get("X").toString()),
						Float.parseFloat(inner.get("Y").toString()),
						ImageIO.read(new File((String)inner.get("Image_url"))),
						true
					);
				ObjectsController.addObject(character);
				ObjectsController.addCharacter((Character)character);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private void generateBackground() {
        for(int hor = 0; hor < 20; hor++) {
            for(int ver = 0; ver < 20; ver++) {
				try {
					ObjectsController.addTile(new Tile(90 * hor, 60 * ver, 150, 60, 2f, ImageIO.read(new File("res/tiles/tile_150_60_00.png")), false));
				} catch (IOException e) {
					e.printStackTrace();
				}
                
            }
        }
	}
	
	public void update() {
		updateDrawOrder();
	}
	
	public void render(Graphics2D g, View view) {

		background.drawTiled(g, view);
		
		ObjectsController.tilesIterator = ObjectsController.tiles.iterator();
		Entity t;
		while(ObjectsController.tilesIterator.hasNext()) {//Draw background
			t = ObjectsController.tilesIterator.next();
			if((t.getX() > player.getX() - 900 && t.getX() < player.getX() + 900) && (t.getY() > player.getY() - 500 && t.getY() < player.getY() + 500)) {
				t.update();
				t.draw(g, view, t.getImage());
			}
		}
		
		ObjectsController.objectsIterator = ObjectsController.objects.iterator();
		Entity e;
		while (ObjectsController.objectsIterator.hasNext()) {
			e = ObjectsController.objectsIterator.next();
			if((e.getX() > player.getX() - 900 && e.getX() < player.getX() + 900) && (e.getY() > player.getY() - 500 && e.getY() < player.getY() + 500)) {
				e.update();
				e.draw(g, view, e.getImage());
			}
		}
	}
	
	private void updateDrawOrder() {
		 ObjectsController.objects.sort(new Comparator<Entity>() {
			@Override
			public int compare(Entity e1, Entity e2) {
				return e2.getDepth() - e1.getDepth();
			}
		});
	}
}
