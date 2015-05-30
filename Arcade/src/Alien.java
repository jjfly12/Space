import javax.swing.ImageIcon;


public class Alien extends Pix {
    private Bomb bomb;
    public Alien(int x, int y) {
        this.x = x;
        this.y = y;
        bomb = new Bomb(x, y+15);
        ImageIcon ii = new ImageIcon(this.getClass().getResource("/alien.png"));
        setImage(ii.getImage());
    }
    public void act(int direction) {
        this.x += direction;
    }
    public Bomb getBomb() {
        return bomb;
    }
    public class Bomb extends Pix {
        protected boolean destroyed;
        public Bomb(int x, int y) 
        {
            destroyed=true;
            this.x = x;
            this.y = y;
            ImageIcon ii = new ImageIcon(this.getClass().getResource("/bomb.png"));
            setImage(ii.getImage());
        }
    }
}