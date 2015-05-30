import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
public class Player extends Pix{
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
        }

        if (key == KeyEvent.VK_RIGHT)
        {
            dx = 2;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT)
        {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT)
        {
            dx = 0;
        }
    }
}