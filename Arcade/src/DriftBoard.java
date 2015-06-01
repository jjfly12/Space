import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class DriftBoard extends Board implements Runnable { 
    private ArrayList<Asteroid> asteroids;
    private DriftPlayer player;

    private int asX = 100;
    private int asY = 15;
    private int direction = -1;

    private boolean play = true;
    private Thread animator;
    
    public int w=500;
    public int h=500;

    public DriftBoard() 
    {
        setFocusable(false);
        setBackground(Color.black);
        setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(500,500));
        init();
    }
    public void init() {

        player = new DriftPlayer();
        asteroids = new ArrayList<Asteroid>();
        if (animator == null||!play) {
            animator = new Thread(this);
            animator.start();
        }
    }
    public void drawAsteroids(Graphics g) 
    {
        Iterator it = asteroids.iterator();
        while (it.hasNext()) {
            Asteroid asteroid = (Asteroid) it.next();
            if (asteroid.isVisible()) {
                g.drawImage(asteroid.getImage(), (int) asteroid.getX(), (int) asteroid.getY(),15,15, this);
            }
            if (asteroid.isDying()) {
                asteroid.die();
                asteroids.remove(asteroid);
            }
        }
    }
    public void drawPlayer(Graphics g) {

        if (player.isVisible()) {
            g.drawImage(player.getImage(), (int) player.getX(), (int) player.getY(),15,15, this);
        }
        if (player.isDying()) {
            player.die();
            play = false;
        }
    }
    public void paint(Graphics g)
    {
      super.paint(g);
      if (play) {
        drawAsteroids(g);
        drawPlayer(g);
      }
      Toolkit.getDefaultToolkit().sync();
      g.dispose();
    }

    public void gameOver()
    {
        Graphics g = this.getGraphics();
        Font small = new Font("Helvetica", Font.BOLD, 15);
        FontMetrics metr = this.getFontMetrics(small);
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString("Game Over", (w - metr.stringWidth("Game Over"))/2, h/2);
    } 
    public void win()
    {
        Graphics g = this.getGraphics();
        Font small = new Font("Helvetica", Font.BOLD, 15);
        FontMetrics metr = this.getFontMetrics(small);
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString("win", (w - metr.stringWidth("win"))/2, h/2);
    }
    public void animationCycle()  
    {
        player.act();
        
        Iterator i3 = asteroids.iterator();
        Random r = new Random();
        while (i3.hasNext()) {
            int shot = r.nextInt(12);
            Asteroid b = (Asteroid) i3;
            if (shot==1) 
            {
            	asteroids.add(new Asteroid(r.nextInt(500), r.nextInt(4)));
            	System.out.println("Asteroid added!");
            }
            int bombX = (int) b.getX();
            int bombY = (int) b.getY();
            int playerX = (int) player.getX();
            int playerY = (int) player.getY();

            if (player.isVisible() && b.isVisible()) {
                if (bombX>=playerX&&bombX<=playerX+15&&bombY>=playerY&&bombY<=playerY+15) {
                        player.setDying(true);
                        b.die();
                        play = false;
                    }
            }
            if (b.isVisible()) {
                b.act();
            }
        }
    }

    public void run() {
        long beforeTime, timeDiff, sleep;
        beforeTime = System.currentTimeMillis();

        while (play) {
            repaint();
            animationCycle();
            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = 20 - timeDiff;
            if (sleep < 0) 
                sleep = 2;
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
            }
            beforeTime = System.currentTimeMillis();
        }
        gameOver();
    }
    public void keyPressed(KeyEvent e) {
    	player.keyPressed(e);
    }

    public void keyReleased(KeyEvent e) {
    	player.keyReleased(e);
    }
}