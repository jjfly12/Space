import java.awt.Image;


public class DriftPix{
	protected double dx = 0;
	protected double dy = 0;
	
	public static final double DX_MAX = 3;
	public static final double DY_MAX = 3;
	public static final double ACCEL = .2;
	
	private boolean visible;
    private Image image;
    protected double x;
    protected double y;
    protected boolean dying;
    public DriftPix() {visible = true;}
    public void die(){visible = false;}
    public boolean isVisible() {return visible;}
    protected void setVisible(boolean visible){this.visible = visible;}
    public void setImage(Image image){this.image = image;}
    public Image getImage(){return image;}
    public void setX(int x){this.x = x;}
    public void setY(int y){this.y = y;}
    public double getY(){return (int) y;}
    public double getX(){return (int) x;}
    public void setDying(boolean dying){this.dying = dying;}
    public boolean isDying(){return this.dying;}
    
	public void accelEast(){
		if (dx < DX_MAX){
			dx += ACCEL;
			System.out.println("Going east!");
		}
	}
	
	public void accelWest(){
		if (dx > -DX_MAX){
			dx -= ACCEL;
		}
	}
	
	public void accelNorth(){
		if (dy > -DY_MAX){
			dy -= ACCEL;
			System.out.println("Going up!");
		}
	}

	public void accelSouth(){
		if (dy < DY_MAX){
			dy += ACCEL;
		}
	}
	
	public void decelerate(){
		if (dy > -DY_MAX && dy < 0){
			dy -= ACCEL*.7;
			if (dy > 0)
				dy = 0;
		}
		if (dy < DY_MAX && dy > 0){
			dy += ACCEL*.7;
			if (dy < 0)
				dy = 0;
		}
		if (dx > -DX_MAX && dx < 0){
			dx += ACCEL*.7;
			if (dx > 0)
				dx = 0;
		}
		if (dx < DX_MAX && dx > 0){
			dx -= ACCEL*.7;
			if (dx < 0)
				dx = 0;
		}
		if (Math.abs(dx - ACCEL) < ACCEL)
			dx = 0;
		if (Math.abs(dy - ACCEL) < ACCEL)
			dx = 0;
	}
}
