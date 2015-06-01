import javax.swing.ImageIcon;


public class Asteroid extends DriftPix{
	 public Asteroid(int xy, int factor) {
	        if (factor == 0){ //top
	        	this.x = xy+50;
	        	this.y = 0;
	        	dx = (Math.random() - .5);
	        	dy = Math.random()*1.5;
	        } else if (factor == 1){ //right
	        	this.y = xy+50;
	        	this.x = 500;
	        	dx = -Math.random()*1.5;
	        	dy = (Math.random() - .5);
	        } else if (factor == 2){ //bottom
	        	this.x = xy+50;
	        	this.y = 500;
	        	dy = -Math.random()*1.5;
	        	dx = (Math.random() - .5);
	        } else if (factor == 3){ //left
	        	this.y = xy+50;
	        	this.x = 500;
	        	dx = Math.random()*1.5;
	        	dy = (Math.random() - .5);
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
