import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Mainframe extends JFrame{
	private Board board;
	private JButton snake;
	private JButton space;
	private JButton text;
	private JButton drift;
	private JLabel score;
	private Mainframe m = this;
	
	public Mainframe()
	{
		super("Gaming!");
		JPanel content = new JPanel();
		this.setContentPane(content);
		board = new Board();
		
		JPanel holder = new JPanel();
		holder.setLayout(new BoxLayout(holder,BoxLayout.Y_AXIS));
		score = new JLabel("score");
		score.setPreferredSize(new Dimension(50,100));
		holder.add(score);
		

		snake = new JButton("Snakes");
		snake.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent arg0) {}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mousePressed(MouseEvent arg0) {}
			@Override
			public void mouseReleased(MouseEvent arg0) {
			content.remove(board);
			board = new SnakeBoard();
			content.add(board);
			m.pack();
			}});
		snake.setFocusable(false);
		snake.setPreferredSize(new Dimension(100,50));
		holder.add(snake);
		space = new JButton("Space");
		space.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent arg0) {}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mousePressed(MouseEvent arg0) {}
			@Override
			public void mouseReleased(MouseEvent arg0) {
			content.remove(board);
			board = new SpaceBoard();
			content.add(board);
			m.pack();
			}});
		space.setFocusable(false);
		space.setPreferredSize(new Dimension(100,50));
		holder.add(space);
		
		text = new JButton("Textgame");
		text.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent arg0) {}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mousePressed(MouseEvent arg0) {}
			@Override
			public void mouseReleased(MouseEvent arg0) {
			try {
				content.remove(board);
				board = new TextBoard();
				content.add(board);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			m.pack();
			}});
		text.setFocusable(false);
		text.setPreferredSize(new Dimension(100,50));
		holder.add(text);
		
		drift = new JButton("Driftgame");
		drift.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent arg0) {}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mousePressed(MouseEvent arg0) {}
			@Override
			public void mouseReleased(MouseEvent arg0) {
			content.remove(board);
			board = new DriftBoard();
			content.add(board);
			m.pack();
			}});
		drift.setFocusable(false);
		drift.setPreferredSize(new Dimension(100,50));
		holder.add(drift);
		
		this.add(holder);
		this.pack();
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.addKeyListener(new Key());
		this.setVisible(true);
	} 
	private class Key extends KeyAdapter {

	    public void keyReleased(KeyEvent e) {
	        board.keyReleased(e);
	    }
        @Override
        public void keyPressed(KeyEvent e) {
            board.keyPressed(e);
            score.setText(Integer.toString(board.getScore()));
        }
    }
}
