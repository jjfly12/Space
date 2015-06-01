import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class SpaceShip extends SpaceObject{
	
	public SpaceShip(int x, int y) {
		super(x, y);
		try {
			boat = ImageIO.read(new File("ship.png"));
		} catch (IOException e){
			//e.printStackTrace();
			System.out.println("dude wtf");
		}
	}

	private char direction;
	private static final double MAX_VELOCITY = 4; //pixels per second
	private double accel = .5;
	private double friction = .2;
	private BufferedImage boat;
	
	
	public void move(char dir){
		this.direction = dir;

		System.out.println(getXVel() + ", " + dir);
	}
	
	public void move() {
		int x;
		int y;
		double xVel;
		double yVel;
		
		switch (direction){
		case 'w':
			break;
		case 's':
			break;
		case 'd':
			x = getX();
			xVel = getXVel();
			if (xVel < 0){
				setXVel(0);
				System.out.println("Veloctiy reset (+)");
			}
			if (xVel < SpaceShip.MAX_VELOCITY)
				setXVel(xVel + this.accel + .01);
			else if (xVel > SpaceShip.MAX_VELOCITY)
				setXVel(SpaceShip.MAX_VELOCITY);
			setX((int) (x + getXVel()));
			break;
		case 'a':
			x = getX();
			xVel = getXVel();
			if (xVel > 0){
				setXVel(0);
				System.out.println("Veloctiy reset (+)");
			}
			if (xVel > -SpaceShip.MAX_VELOCITY)
				setXVel(xVel - this.accel - .1);
			else if (xVel < -SpaceShip.MAX_VELOCITY)
				setXVel(-SpaceShip.MAX_VELOCITY);
			setX((int) (x + getXVel()));
			break;
		default:
			if (getXVel()*getXVel() > 1){
				setXVel(getXVel() * friction);
			}
			if (getYVel()*getYVel() > 1){
				setYVel(getYVel() * friction);
			}
			break;
		}
	}
	
	public void fire(){
		
	}

	public double getFriction() {
		return friction;
	}

	public void setFriction(double friction) {
		this.friction = friction;
	}

	public double getAccel() {
		return accel;
	}

	public void setAccel(double accel) {
		this.accel = accel;
	}
	
	public BufferedImage getImage(){
		return this.boat;
	}
}
