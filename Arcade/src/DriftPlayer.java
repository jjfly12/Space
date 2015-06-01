import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;


public class DriftPlayer extends DriftPix {
	boolean leftPressed = false;
	boolean rightPressed = false;
	boolean upPressed = false;
	boolean downPressed = false;
	
	long upPressTime = 0;
	long downPressTime = 0;
	long rightPressTime = 0;
	long leftPressTime = 0;
	
    public DriftPlayer() 
    {
        ImageIcon ii = new ImageIcon(getClass().getResource("/player.png"));
        setImage(ii.getImage());
        setX(250);
        setY(350);
    }
    public void act() {
    	System.out.println(dx + ", " + dy + ": " + "(" + x + ", " + y + ")");
    	System.out.println(rightPressed + "r " + leftPressed + "l " + upPressed + "u " + downPressed + "d");
    	

    	if (rightPressed && (System.currentTimeMillis() - rightPressTime > 500 || rightPressTime == 0)){
    		accelEast();
    		rightPressTime = System.currentTimeMillis();
    	}
    	if (leftPressed && (System.currentTimeMillis() - leftPressTime > 500 || leftPressTime == 0)){
    		accelWest();
    		leftPressTime = System.currentTimeMillis();
    	}
    	if (upPressed && (System.currentTimeMillis() - upPressTime > 500 || upPressTime == 0)){
    		accelNorth();
    		upPressTime = System.currentTimeMillis();
    	}
    	if (downPressed && (System.currentTimeMillis() - downPressTime > 500 || downPressTime == 0)){
    		accelSouth();
    		downPressTime = System.currentTimeMillis();
    	}
    	
        x += dx;
        y += dy;
        if (x <= 2){ 
            x = 2;
            dx = 0;
        }
        if (x >= 500 - 30){ 
            x = 500 - 30;
            dx = 0;
        }
        if (y <= 2){ 
            y = 2;
            dy = 0;
        }
        if (y >= 500 - 30){ 
            y = 500 - 30;
            dy = 0;
        }
    }
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT)
        {
            leftPressed = true;
        }
        if (key == KeyEvent.VK_RIGHT)
        {
            rightPressed = true;
        }
        if (key == KeyEvent.VK_UP)
        {
            upPressed = true;
        }
        if (key == KeyEvent.VK_DOWN)
        {
            downPressed = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT)
        {
        	if (rightPressed)
        		accelEast();
        	if (upPressed && !downPressed)
        		accelNorth();
        	if (downPressed && !upPressed)
        		accelSouth();
        	leftPressed = false;
        }

        if (key == KeyEvent.VK_RIGHT)
        {
        	if (leftPressed)
        		accelWest();
        	if (upPressed && !downPressed)
        		accelNorth();
        	if (downPressed && !upPressed)
        		accelSouth();
        	rightPressed = false;
        }
        if (key == KeyEvent.VK_UP)
        {
        	if (downPressed)
        		accelSouth();
        	if (leftPressed && !rightPressed)
        		accelWest();
        	if (rightPressed && !leftPressed)
        		accelEast();
        	upPressed = false;
        }

        if (key == KeyEvent.VK_DOWN)
        {
        	if (upPressed)
        		accelNorth();
        	if (leftPressed && !rightPressed)
        		accelWest();
        	if (rightPressed && !leftPressed)
        		accelEast();
        	downPressed = false;
        }
    }
}
