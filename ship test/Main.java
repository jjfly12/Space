
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
public class Main extends JPanel implements ActionListener
{

	    private Timer timer;
	    private Ship ship;
	    private boolean ingame;
	    private int B_WIDTH;
	    private int B_HEIGHT;

	    public Main() 
	    {

	        addKeyListener(new TAdapter());
	        setFocusable(true);
	        setBackground(Color.BLACK);
	        setDoubleBuffered(true);
	        ingame = true;
	        setSize(400, 300);
	        ship = new Ship(100,100);
	        timer = new Timer(5, this);
	        timer.start();
	    }

	    public void addNotify() 
	    {
	        super.addNotify();
	        B_WIDTH = getWidth();
	        B_HEIGHT = getHeight();   
	    }

	    public void paint(Graphics g) 
	    {
	        super.paint(g);

	        if (ingame) 
	        {

	            Graphics2D g2d = (Graphics2D)g;
	            if (ship.getLife())
	            {
	            	double rotationRequired = Math.toRadians(ship.getSpin());
	            	double locationX = ship.getImage().getWidth() / 2;
	            	double locationY = ship.getImage().getHeight() / 2;
	            	AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
	            	AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
	                g2d.drawImage(op.filter(ship.getImage(), null), (int)ship.getX(), (int)ship.getY(),this);
	            }



	        }
	        else
	        {
	            String msg = "Game Over";
	            Font small = new Font("Helvetica", Font.BOLD, 14);
	            FontMetrics metr = this.getFontMetrics(small);

	            g.setColor(Color.white);
	            g.setFont(small);
	            g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2,
	                         B_HEIGHT / 2);
	        }
	        Toolkit.getDefaultToolkit().sync();
	        g.dispose();
	    }


	    public void actionPerformed(ActionEvent e) 
	    {
	        ship.move();
	        repaint();  
	    }

	    private class TAdapter extends KeyAdapter 
	    {

	        public void keyPressed(KeyEvent e) 
	        {
	            ship.keyPressed(e);
	        }
	        public void keyReleased(KeyEvent e)
	        {
	        	ship.keyReleased(e);
	        }
	    }
	}
