package network;

import java.awt.image.BufferedImage;

import character.Character;

/**
 *
 * @author Herman Hallstedt
 * @version 2015-04-11
 */
public class NetworkPlayer extends Character
{
    private String name, id;
    private ClientSocket client;
            
    public NetworkPlayer(ClientSocket client, BufferedImage image)
    {
        super(client.position.x, client.position.y, image, true);
        name = client.name;
        id = client.id;
        
        this.client = client;
    }
    
    public void update(double delta)
    {
        if(client.hasNewData())
        {
            x = client.position.x;
            y = client.position.y;
            movement.velocity.x = client.velocity.x;
            movement.velocity.y = client.velocity.y;
        }
        
        movement.move(delta);
    }
    
    public String getId()
    {
        return id;
    }
}
