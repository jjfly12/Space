
public abstract class SpaceObject {
	private int xPos; //top left corner of object
	private int yPos;
	private int width;
	private int height;
	
	private double xVel;
	private double yVel;
	
	public SpaceObject(int x, int y){
		this.xPos = x;
		this.yPos = y;
		this.xVel = 0;
		this.yVel = 0;
	}
	
	public abstract void move(char dir);
	public abstract void move();
	
	
	public double getXVel(){
		return xVel;
	}
	public double getYVel(){
		return yVel;
	}
	public void setXVel(double xv){
		this.xVel = xv;
	}
	public void setYVel(double yv){
		this.yVel = yv;
	}
	
	public int getX(){
		return xPos;
	}
	public int getY(){
		return yPos;
	}
	public void setX(int xPos) {
		this.xPos = xPos;
	}
	public void setY(int yPos) {
		this.yPos = yPos;
	}



	public boolean isPointInObject(int x, int y){
		if (x < xPos || y < yPos)
			return false;
		if (x > xPos + width || y > yPos + height)
			return false;
		return true;
	}
}
