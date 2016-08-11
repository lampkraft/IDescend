package game;

import input.Keyboard;
import graphics.Background;
import graphics.Tile;
import graphics.View;
import network.MultiplayerHandler;
import network.NetworkPlayer;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.Timer;


public class Game extends Canvas implements Runnable
{
    private static final long serialVersionUID = 1L;

    // Resolution
    public static int width = 1280;
    public static int height = 720;
    public static int scale = 1;
    
    //
    private long lastTime, timer, now;
    private final double ns = 1000000000.0 / 60.0;
    private double delta = 0;
    private int frames = 0, ups = 0;

    public static String title = "I Descend";
    public JFrame frame;
    
    public Timer animationTimer;

    private Thread thread;
    
    private Keyboard key;
    private boolean running = false, readyToQuit = false;

    private Player player;
    private List<NetworkPlayer> netPlayers = new ArrayList();
    private Level level;
    private Background background;

    private MultiplayerHandler multiplayer;

    private View view;

    public Game()
    {
        Dimension size = new Dimension (width * scale, height * scale);
        setPreferredSize(size);
        
        //animationTimer = new Timer();

        frame = new JFrame();
        key = new Keyboard();
        view = new View(width, height);

        addKeyListener(key);

        // Clean up when program exits
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                running = false;
                while(!readyToQuit);
                    
                if (multiplayer != null)
                    multiplayer.close();
            }
        });

        initiateWorld();
    }

    private void initiateWorld()
    {
        try {
			player = new Player(ImageIO.read(new File("res/sprites/skeleton_walk.png")));
			background  = new Background(ImageIO.read(new File("res/backgrounds/fog.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
        player.setPosition(width / 2, height / 2);
        level = new Level(player, "res/levels/level0.lvl");
        multiplayer = new MultiplayerHandler(false, player);
    }

    public synchronized void start()
    {
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }

    public synchronized void stop()
    {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run()
    {
        lastTime = System.nanoTime();
        timer = System.currentTimeMillis();
        now = System.nanoTime();

        requestFocus();

        while (running)
        {
            now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            
            while (delta >= 1) {
            	//Animation.update();
	            update(delta);
	            render();
	            delta = 0;
	            ups++;
            }
            
            frames++;

            if (System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                frame.setTitle(title + " | " + ups + "ups | " + frames + " fps" + " | Player width and height: " + player.getWidth() + " " + player.getHeight() + "| Player position: x " + player.getX() + " y " + player.getY());
                ups = 0;
                frames = 0;
            }
        }
        
        readyToQuit = true;
    }
    
    private void removeNetPlayer(String id)
    {
        for(int i = 0; i < netPlayers.size(); i++)
        {
            if(netPlayers.get(i).getId().equals(id))
            {
                netPlayers.remove(i);
                break;
            }
        }
    }

    public void updateMultiplayer(double delta)
    {
        //if(multiplayer.hasNewPlayer())
            //netPlayers.add(new NetworkPlayer(multiplayer.getNewPlayer(), image3));
        
        multiplayer.update(delta);
        while(multiplayer.hasNewUpdate())
        {
            String msg = multiplayer.getNextUpdate();
            String[] words = msg.split(" ");
            
            if(words[0].equals("rm"))
            {
                multiplayer.removeClient(words[1]);
                removeNetPlayer(words[1]);
            }
        }
        
        for(NetworkPlayer np : netPlayers)
        {
            np.update(delta);
        }
    }
    
    public void update(double delta)
    {
        key.update();
        player.update(key, delta, view);
        level.update();

        updateMultiplayer(delta);
        
        if(key.xkey.isPressed())
            multiplayer.setAsHost(true);
        if(key.zkey.isPressed())
            multiplayer.setAsHost(false);
        if(key.ckey.isPressed())
        {
            if(multiplayer.isHost())
                multiplayer.disconnectAllClients();
            else
            {
                if(!multiplayer.isConnected())
                    multiplayer.connectToHost();
                else
                    multiplayer.disconnectFromHost();
            }
        }
        if(key.tkey.isHeld())
            view.zoom(0.5f, delta);
        if(key.ykey.isHeld())
            view.zoom(-0.5f, delta);
    }

    public void render()
    {
        BufferStrategy bs = getBufferStrategy();
        
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics2D g = (Graphics2D)bs.getDrawGraphics();

        g.setColor(Color.darkGray);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Draw begin
        background.drawStretched(g, view);
        level.render(g, view);

        for(NetworkPlayer c : netPlayers) {
            c.draw(g, view, c.getImage());
        }

        //player.draw(g, view, player.getImage());

        // Draw end

        g.dispose();
        bs.show();
    }
}
