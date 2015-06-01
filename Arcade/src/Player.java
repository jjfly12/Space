import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
public class Player extends Pix{
	boolean leftPressed = false;
	boolean rightPressed = false;
    protected int dx;
	
    public Player() 
    {
        ImageIcon ii = new ImageIcon(getClass().getResource("/player.png"));
        setImage(ii.getImage());
        setX(250);
        setY(350);
    }
    public void act() {
        x += dx;
        if (x <= 2) 
            x = 2;
        if (x >= 500 - 30) 
            x = 500 - 30;
    }
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT)
        {
            dx = -2;
            leftPressed = true;
        }

        if (key == KeyEvent.VK_RIGHT)
        {
            dx = 2;
            rightPressed = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT)
        {
        	if (rightPressed)
        		dx = 2;
        	else
        		dx = 0;
        	leftPressed = false;
        }

        if (key == KeyEvent.VK_RIGHT)
        {
        	if (leftPressed)
        		dx = -2;
        	else
        		dx = 0;
        	rightPressed = false;
        }
    }
}