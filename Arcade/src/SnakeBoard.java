import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


public class SnakeBoard extends Board implements ActionListener{

	private int direction;
	private boolean play;
	private Timer timer = new Timer(50, this);
	private int pixH;
	private int pixW;
	private int pixSize;
	
	private int x[];
	private int y[];
	
	private int body;
	private int ax;
	private int ay;
	
	private Image bodyPic;
	private Image pointPic;
	private Image headPic;
	
	public int getScore(){return (body-1)*2;}
	public SnakeBoard()
	{
		super();
		this.addKeyListener(new Actions());
		this.setFocusable(true);
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.setOpaque(true);
		this.pixH=50;
		this.pixW=50;
		this.x = new int[pixH*pixW];
		this.y = new int[pixH*pixW];
		this.pixSize = 15;
		this.setPreferredSize(new Dimension(pixW*pixSize,pixH*pixSize));
		loadImage();
		init();
	}
	private void loadImage()
	{
		ImageIcon temp = new ImageIcon(getClass().getResource("/body.png"));
		bodyPic = temp.getImage();
		temp = new ImageIcon(getClass().getResource("/point.png"));
		pointPic = temp.getImage();
		temp = new ImageIcon(getClass().getResource("/head.png"));
		headPic = temp.getImage();
	}
	public void init()
	{
		play=true;
		body = 1;
		for(int i = 0;i<body;i++)
		{
			x[i]=pixW/2-i;
			y[i]=pixH/2;
		}
		newPoint();
		timer.start();
	}
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		refresh(g);
	}
	private void refresh(Graphics g)
	{
		if(play)
		{
			g.drawImage(pointPic, ax*pixSize, ay*pixSize, this);
			for(int i = 0;i<body;i++)
			{
				if(i==0)
					g.drawImage(headPic, x[i]*pixSize, y[i]*pixSize,pixSize,pixSize, this);
				else
					g.drawImage(bodyPic, x[i]*pixSize, y[i]*pixSize,pixSize,pixSize, this);
			}
			Toolkit.getDefaultToolkit().sync();
		}
		else
			gameover(g);
	}
	private void gameover(Graphics g)
	{
		timer.stop();
		g.setColor(Color.WHITE);
		g.setFont(new Font("Helvetica", Font.BOLD,20));
		FontMetrics temp = getFontMetrics(g.getFont());
		g.drawString("6AM3 0V3R",(pixW*pixSize-temp.stringWidth("6AM3 0V3R"))/2,pixH*pixSize/2 );
	}
	private void checkPoint()
	{
		if(x[0]==ax&&y[0]==ay)
		{
			body++;
			newPoint();
		}
	}
	private void move()
	{
		if(!(direction==0))
		for(int i=body;i>0;i--)
		{
			x[i]=x[i-1];
			y[i]=y[i-1];
		}
		switch(direction)
		{
			case 1:x[0]--;break;
			case 2:x[0]++;break;
			case 3:y[0]--;break;
			case 4:y[0]++;break;
		}
	}
	private void checkDeath()
	{
		for (int i= body;i>0;i--)
		{
            if(x[0]==x[i]&&y[0]==y[i])
                play = false;
        }
        if (y[0] >= pixH)play=false;
        if (y[0] < 0)play=false;
        if (x[0] >= pixW)play=false;
        if (x[0] < 0)play=false;
	}
	private void newPoint()
	{
		ax=((int)(Math.random()*(pixW-1)));
		ay=((int)(Math.random()*(pixH-1)));
		for (int i= body;i>=0;i--)
		{
            if(ax==x[i]&&ay==y[i])
                newPoint();
        }
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(play)
		{
			checkPoint();
			checkDeath();
			move();
		}
		this.repaint();
	}
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
        if (play) 
        {
            if (key == KeyEvent.VK_LEFT&&direction!=2)direction = 1;
            else if (key == KeyEvent.VK_RIGHT&&direction!=1)direction = 2;
            else if (key == KeyEvent.VK_UP&&direction!=4)direction = 3;
            else if (key == KeyEvent.VK_DOWN&&direction!=3)direction = 4;
            else if (key == KeyEvent.VK_SPACE) 
            {
                if (timer.isRunning())timer.stop();
                else timer.start();
            }
        }
		
	}
	class Actions extends KeyAdapter 
	{
        @Override
        public void keyPressed(KeyEvent e) 
        {
            int key = e.getKeyCode();
            if (play) 
            {
                if (key == KeyEvent.VK_LEFT&&direction!=2)direction = 1;
                else if (key == KeyEvent.VK_RIGHT&&direction!=1)direction = 2;
                else if (key == KeyEvent.VK_UP&&direction!=4)direction = 3;
                else if (key == KeyEvent.VK_DOWN&&direction!=3)direction = 4;
                else if (key == KeyEvent.VK_SPACE) 
                {
                    if (timer.isRunning())timer.stop();
                    else timer.start();
                }
            } 
        }
	}
}
