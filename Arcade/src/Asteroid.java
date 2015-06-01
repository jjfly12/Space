import javax.swing.ImageIcon;


public class Asteroid extends DriftPix{
	 public Asteroid(int xy, int factor) {
	        if (factor == 0){ //top
	        	this.x = xy;
	        	this.y = 0;
	        	dx = (Math.random() - .5)*15;
	        	dy = Math.random()*15;
	        } else if (factor == 1){ //right
	        	this.y = xy;
	        	this.x = 500;
	        	dx = -Math.random()*15;
	        	dy = (Math.random() - .5)*15;
	        } else if (factor == 2){ //bottom
	        	this.x = xy;
	        	this.y = 500;
	        	dy = -Math.random()*15;
	        	dx = (Math.random() - .5)*15;
	        } else if (factor == 3){ //left
	        	this.y = xy;
	        	this.x = 500;
	        	dx = Math.random()*15;
	        	dy = (Math.random() - .5)*15;
	        }
	        ImageIcon ii = new ImageIcon(this.getClass().getResource("/bomb.png"));
	        setImage(ii.getImage());
	 }
	 
	 public void act(){
		 x += dx;
		 y += dy;
		 if (x > 500 || x < 0)
			 this.die();
		 else if (y > 500 || y < 0)
			 this.die();
	 }
}
