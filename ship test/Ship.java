import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Ship {
	private final String ping= "ship.png";
	private final int xMove = 10;
	private final int yMove = 10;
	private double x;
	private double y;
	private double spin =0;
	private boolean alive;
	private BufferedImage boat;
	public double xMulti = 0;
	public double yMulti = 0;
	public double friction = 0.9;
	public int direction=0;
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	boolean spinning;
	
	
	public Ship(int x, int y)
	{
		try {
			File fil = new File(ping);
			boat = ImageIO.read(fil);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.alive = true;
		this.x = x;
		this.y = y;
	}
	public void move()
	{
		if(left)spin+=1;
		if(right)spin-=1;
		if(up)
		{
            yMulti -= 0.5*Math.sin(Math.toRadians(direction));
            xMulti += 0.5*Math.cos(Math.toRadians(direction)); 
		}
		if(down)
		{
            yMulti += 0.5*Math.sin(Math.toRadians(direction));
            xMulti -= 0.5*Math.cos(Math.toRadians(direction)); 
		}
		this.x += (xMove*xMulti);
		this.y += (yMove*yMulti);
		direction+=spin;
		//decelerate!
		xMulti *= friction;
		yMulti *= friction;
		spin *= friction;
		if (spin>0.1)spinning = true;
		else spinning = false;
		
		
	}
	public double getX(){return x;}
	public void setX(int x){this.x=x;}
	public double getY(){return y;}
	public void setY(int y){this.y=y;}
	public double getSpin(){return x;}
	public void setSpin(double spin){this.x=x;}
	public BufferedImage getImage(){return boat;}
	public void setLife(boolean life){this.alive=life;}
	public boolean getLife(){return alive;}
	public Rectangle getBound(){return new Rectangle((int)x,(int)y,boat.getWidth(),boat.getHeight());}
	public void setImage(BufferedImage mage){this.boat=mage;}

	public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
        }

        if (key == KeyEvent.VK_LEFT) {
            left = true;
        }

        if (key == KeyEvent.VK_RIGHT) {
            right = true;
        }

        if (key == KeyEvent.VK_UP) {
        	up = true;
        }

        if (key == KeyEvent.VK_DOWN) {
        	down = true;
        }
    }
	public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
        }

        if (key == KeyEvent.VK_LEFT) {
            left = false;
        }

        if (key == KeyEvent.VK_RIGHT) {
            right = false;
        }

        if (key == KeyEvent.VK_UP) {
        	up = false;
        }

        if (key == KeyEvent.VK_DOWN) {
        	down = false;
        }
    }
	

}
