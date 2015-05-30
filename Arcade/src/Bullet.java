import javax.swing.ImageIcon;
public class Bullet extends Pix
{
	public Bullet(){}
	public Bullet(int x, int y) {
		ImageIcon temp = new ImageIcon(getClass().getResource("/bullet.png"));
		setImage(temp.getImage());
		setX(x);
		setY(y);
	}
}