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

public class SpaceBoard extends Board implements Runnable { 
    private ArrayList<Alien> aliens;
    private Player player;
    private Bullet bullet;

    private int asX = 100;
    private int asY = 15;
    private int direction = -1;
    private int deaths = 0;

    private boolean play = true;
    private Thread animator;
    
    public int w=500;
    public int h=500;

    public SpaceBoard() 
    {
        setFocusable(false);
        setBackground(Color.black);
        setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(500,500));
        init();
    }
    public void init() {

        aliens = new ArrayList<Alien>();
        for (int i=0; i < 4; i++) {
            for (int j=0; j < 6; j++) {
                Alien alien = new Alien(asX + 20*j, asY + 20*i);
                aliens.add(alien);
            }
        }

        player = new Player();
        bullet = new Bullet();
        if (animator == null||!play) {
            animator = new Thread(this);
            animator.start();
        }
    }
    public void drawAliens(Graphics g) 
    {
        Iterator it = aliens.iterator();
        while (it.hasNext()) {
            Alien alien = (Alien) it.next();
            if (alien.isVisible()) {
                g.drawImage(alien.getImage(), alien.getX(), alien.getY(),15,15, this);
            }
            if (alien.isDying()) {
                alien.die();
            }
        }
    }
    public void drawPlayer(Graphics g) {

        if (player.isVisible()) {
            g.drawImage(player.getImage(), player.getX(), player.getY(),15,15, this);
        }
        if (player.isDying()) {
            player.die();
            play = false;
        }
    }
    public void drawShot(Graphics g) {
        if (bullet.isVisible())
            g.drawImage(bullet.getImage(), bullet.getX(), bullet.getY(),5,5, this);
    }
    public void drawBombing(Graphics g) {
        Iterator i3 = aliens.iterator();
        while (i3.hasNext()) {
            Alien a = (Alien) i3.next();
            Alien.Bomb b = a.getBomb();
            if (!b.destroyed) {
                g.drawImage(b.getImage(), b.getX(), b.getY(),10,10, this); 
            }
        }
    }
    public void paint(Graphics g)
    {
      super.paint(g);
      if (play) {
        drawAliens(g);
        drawPlayer(g);
        drawShot(g);
        drawBombing(g);
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
        if (deaths == 24) 
        {
            play = false;
            win();
        }
        player.act();
        if (bullet.isVisible()) 
        {
            Iterator it = aliens.iterator();
            int X = bullet.getX();
            int Y = bullet.getY();

            while (it.hasNext())
            {
                Alien alien = (Alien) it.next();
                int aX = alien.getX();
                int aY = alien.getY();
                if (alien.isVisible() && bullet.isVisible()) 
                {
                    if (X>=aX&&X<=aX+15&&Y>=aY&&Y<=aY+15) 
                    {
                            alien.setDying(true);
                            deaths++;
                            bullet.die();
                    }
                }
            }
            int y = bullet.getY();
            y -= 5;
            if (y < 0)bullet.die();
            else bullet.setY(y);
        }
         Iterator it1 = aliens.iterator();
         while (it1.hasNext()) 
         {
             Alien a1 = (Alien) it1.next();
             int x = a1.getX();
             if (x  >= w-15&&direction != -1) 
             {
                 direction = -1;
                 Iterator i1 = aliens.iterator();
                 while (i1.hasNext()) {
                     Alien a2 = (Alien) i1.next();
                     a2.setY(a2.getY()+15);
                 }
             }

            if (x <= 15 && direction != 1) 
            {
                direction = 1;
                Iterator i2 = aliens.iterator();
                while (i2.hasNext()) 
                {
                    Alien a = (Alien)i2.next();
                    a.setY(a.getY()+15);
                }
            }
        }
        Iterator it = aliens.iterator();
        while (it.hasNext()) {
            Alien alien = (Alien) it.next();
            if (alien.isVisible()) {
                if (alien.getY() > 400 - 15) {
                    play = false;
                }
                alien.act((direction));
            }
        }
        Iterator i3 = aliens.iterator();
        Random r = new Random();
        while (i3.hasNext()) {
            int shot = r.nextInt(15);
            Alien a = (Alien) i3.next();
            Alien.Bomb b = a.getBomb();
            if (shot==1 && a.isVisible() && b.destroyed) 
            {
                b.destroyed=false;
                b.setX(a.getX());
                b.setY(a.getY());   
            }
            int bombX = b.getX();
            int bombY = b.getY();
            int playerX = player.getX();
            int playerY = player.getY();

            if (player.isVisible() && !b.destroyed) {
                if (bombX>=playerX&&bombX<=playerX+15&&bombY>=playerY&&bombY<=playerY+15) {
                        player.setDying(true);
                        b.destroyed=true;
                    }
            }
            if (!b.destroyed) {
                b.setY(b.getY() + 1);   
                if (b.getY() >= 400) {
                    b.destroyed=true;
                }
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
    public void keyReleased(KeyEvent e) {
        player.keyReleased(e);
    }

    public void keyPressed(KeyEvent e) {

      player.keyPressed(e);

      int x = player.getX();
      int y = player.getY();

      if (play)
      {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (!bullet.isVisible())
                bullet = new Bullet(x, y);
        }
      }
    }
}