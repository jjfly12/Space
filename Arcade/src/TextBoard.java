import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class TextBoard extends Board
{
	private int x;
	private int y;
	private String[][] location;
	private boolean[] eventList = new boolean[11];
	/* 0=open chest
	 * 1=get lantern
	 * 2=get key
	 * 3=open 1 door
	 * 4=get sword
	 * 5= get shield
	 * 6= kill grue
	 * 7=kill aptest
	 * 8=open 2 door
	 * 9=get lantern oil
	 * 10=open grate
	 */
	private boolean end = false;
	private boolean win = false;
	private boolean t=false;
	private boolean w;
	private boolean e;
	private boolean n;
	private boolean s;
	
	private JTextArea words;
	private JTextField enter;
	public int getScore()
	{
		int score=0;
		if(eventList[1])score+=5;
		if(eventList[2])score+=3;
		if(eventList[3])score+=5;
		if(eventList[4])score+=3;
		if(eventList[5])score+=3;
		if(eventList[6])score+=7;
		if(eventList[7])score+=10;
		if(eventList[8])score+=5;
		if(eventList[9])score+=10;
		if(eventList[10])score+=20;
		return score;
	}
	public TextBoard() throws InterruptedException
	{
		super();
		this.setBackground(Color.BLACK);
		this.setPreferredSize(new Dimension(500,500));
		this.setOpaque(true);
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		x=4;y=4;
		initlocs();
		Font font = new Font("Helvetica",15, 15);
		
		words=new JTextArea();
		words.setEditable(false);
		words.setBackground(Color.BLACK);
		words.setForeground(Color.WHITE);
		words.setFont(font);
		words.setLineWrap(true);
		words.setWrapStyleWord(true);
		words.setPreferredSize(new Dimension(500,400));
		
		this.add(words);
		
		enter = new JTextField();
		enter.setEditable(true);
		enter.setPreferredSize(new Dimension(500,100));
		enter.setBackground(Color.BLACK);
		enter.setForeground(Color.WHITE);
		enter.setFont(font);
		enter.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY, 3), BorderFactory.createLineBorder(Color.DARK_GRAY, 3, true)));
		this.add(enter);
		write("Hello! Welcome to version 1.1 of Unnamed text game.");
		write("You wake up in a room full of chairs, something feels off about this place. You try to leave but to no avail.");
		write(location[x][y]);
		exits();
		this.addKeyListener(new Actions());
		enter.addKeyListener(new Actions());
	}
	public String parse()
	{
		String string = enter.getText();
		String[] strings = string.toLowerCase().split(" ");
		String re ="";
		
		for(String s:strings)
		{
			if(re != "")re+=" ";
			if(s.equals("n")||s.equals("north")||s.equals("up"))re+="n";
			if(s.equals("s")||s.equals("south")||s.equals("down"))re+="s";
			if(s.equals("e")||s.equals("east")||s.equals("right"))re+="e";
			if(s.equals("w")||s.equals("west")||s.equals("left"))re+="w";
			if(s.equals("go")||s.equals("move")||s.equals("walk"))re+="move";
			if(s.equals("get")||s.equals("take"))re+="take";
			if(s.equals("open"))re+="open";
			if(s.equals("use")||s.equals("wield"))re+="use";
			if(s.equals("look")||s.equals("see")||s.equals("examine"))re+="look";
			if(s.equals("sword"))re+="sword";
			if(s.equals("pen"))re+="pen";
			if(s.equals("shield"))re+="shield";
			if(s.equals("eraser"))re+="eraser";
			if(s.equals("lantern"))re+="lantern";
			if(s.equals("key"))re+="key";
			if(s.equals("chest"))re+="chest";
			if(s.equals("treant"))re+="treant";
			if(s.equals("grate"))re+="grate";
			if(s.equals("door"))re+="door";
			if(s.equals("help"))re+="help";
		}
		enter.setText("");
		return re;
	}
	public void action(String action) throws InterruptedException
	{
		String act[] = action.split(" ");
		if(act.length>0&&act.length<3)
		{
			switch(act[0])
			{
			case"move":
				if(act.length==2)
				{
					switch(act[1])
					{
					case "n":
						if(n)
						{
							y-=1;
							write("you move north.");
							write(location[y][x]);
							exits();
							return;
						}
						else if(x==0&&y==3)
						{
							end = true;
							write("You got eaten by the grue. Good job.");
							return;
						}
						else if(x==1&&y==3&&!eventList[7])
						{
							end = true;
							write("You try to move forward, but alas you were caught.");
							return;
						}
						else if(x==1&&y==1&&!eventList[8])
						{
							write("You move forward and hit your head on the door three times.\n It doesn't work.");
							return;
						}
						else
						{
							write("you try to move north but you can't, for multiple reasons. Mostly because there's nothing at NORTH.");
							return;
						}
					case "s":
						if(s)
						{
							y+=1;
							write("you move south");
							write(location[y][x]);
							exits();
							return;
						}
						else
						{
							write("You try to move south but can't, for multiple reasons.");
							return;
						}
					case "e":
						if(e)
						{
							x+=1;
							write("you move east");
							write(location[y][x]);
							exits();
							return;
						}
						else
						{
							write("You try to move east but can't, for multiple reasons");
							return;
						}
					case "w":
						if(w)
						{
							x-=1;
							write("you move west");
							write(location[y][x]);
							exits();
							return;
						}
						else if(x==2&&y==4&&!eventList[3])
						{
							write("You move west and stub your toe.\n It looks like you need to open the door first,");
							return;
						}
						else
						{
							write("You try to move west but can't, for multiple reasons.");
							return;
						}
					}
				}
				write("You dance around for some reason, it feels like you've accomplished nothing");
				break;
			case"take":
				if(act.length==2)
				{
					switch(act[1])
					{
					case"lantern":
						if(x==3&&y==1&&!eventList[1])
						{
						write("You take the LANTERN.");
						eventList[1]=true;
						return;
						}
						break;
					case"sword":
						if(x==0&&y==4&&!eventList[4])
						{
							write("You take the SWORD from the shelf, on further inspection it's actually a PEN");
							eventList[4]=true;
							return;
						}
						break;
					case"shield":
						if(x==0&&y==3&&!eventList[6])
						{
							write("dat grue tho");
							return;
						}
						else if(x==0&&y==3&&eventList[6]&&!eventList[5])
						{
							write("you take the ERASER from the wall");
							eventList[5] =true;
							return;
						}
						break;
					}
				}
				write("Take what?");
				break;
			case"open":
				if(act.length==2)
				{
					switch(act[1])
					{
					case"chest":
						if(x==4&&y==1&&!eventList[2])
						{
							write("You open the chest and get a key!");
							eventList[2]=true;
							return;
						}
						else if(x==4&&y==1&&eventList[2])
						{
							System.out.println("You open the chest, but it was empty");
							return;
						}
						break;
					case"door":
						if(x==2&&y==4&&!eventList[3]&&!eventList[2])
						{
							write("You open the door, but it was locked.");
							return;
						}
						else if(x==2&&y==4&&!eventList[3]&&eventList[2])
						{
							write("You open the door with your KEY, and now the door is open.");
							eventList[3]= true;
							exits();
							return;
						}
						else if(x==2&&y==4&&eventList[3])
						{
							write("You open the door but it can't open anymore");
							return;
						}
						else if(x==1&&y==1&&!eventList[8]&&!eventList[9])
						{
							write("Yeah, I don't think that's going to work.\nIt's just too heavy");
							return;
						}
						else if(x==1&&y==1&&!eventList[8]&&eventList[9])
						{
							write("You attempt to open the door, but your lantern OIL spills out down your pants.\n THe door opens out of pity");
							eventList[8]=true;
							eventList[9]=false;
							exits();
							return;
						}
						else if(x==1&&y==1&&eventList[8])
						{
							write("The door is open, no joke");
							return;
						}break;
					case"lantern":
						if(!eventList[9]&&eventList[1])
						{
							write("You open the LANTERN open and get lantern OIL!\nidk where you put it tho");
							eventList[1]=false;
							eventList[9]=true;
							return;
						}break;
					case"grate":
						if(x==1&&y==0)
						{
							write("You open the DOOR and leave as class ends.");
							end=true;
							win=true;
							return;
						}break;
					}
				}
				write("Open what?");
				break;
			case"use":
				if(act.length==2)
				{
					switch(act[1])
					{
					case"lantern":
						if(x==0&&y==3&&!eventList[6]&&eventList[1])
						{
							write("You wave your LANTERN and the grue goes away");
							eventList[6]=true;
							return;
						}break;
					case"pen":
						if(x==1&&y==3&&eventList[4]&&eventList[5])
						{
							write("You take up your pen and guess C for like half the questions.\n Your eraser is fully chewed through due to stress.\n At last the APTEST is dead.");
							eventList[7]=true;
							exits();
							return;
						}
						else if(x==1&&y==3&&eventList[4]&&!eventList[5])
						{
							System.out.println("You take up your pen and guess C for like half the questions.\n You die from stress halfway through.");
							end = true;
							return;
						}break;
					case"eraser":
						if(eventList[5])
						{
							System.out.println("You chew on the ERASER a bit");
							return;
						}break;
					}
					
				}
				write("There is a time and a place for everything, but not here");
				break;
			case"look":
				write(location[y][x]);
				exits();
				break;
			case"help":
				write("As of version 1.1 all commands must be of two words and have a verb and a noun. Verbs include the words: MOVE, LOOK, USE, OPEN, TAKE, and HELP");
				break;
			default:
				write("I don't know how to do that");
			}
		}
		else if(act.length>=3)
		{
			write("I don't take any commands above three words, not enough RAM");
		}
	}
	public void write(String text) throws InterruptedException
	{
		String[] word = text.split(" ");
		this.words.append("\n");
		for(String s :word)
		{
			for(int i=0;i<s.length();i++)
			{
				this.words.append(s.substring(i,i+1));
			}
			this.words.append(" ");
		}

words.selectAll();
words.setCaretPosition(words.getDocument().getLength());

		
	}
	
	private void exits() throws InterruptedException
	{
		w=false;e=false;s=false;n=false;
		if(x==4&&y==4){w=true;}
		if(x==3&&y==4){w=true;e=true;n=true;}
		if(x==2&&y==4&&eventList[3]){w=true;e=true;}
		if(x==2&&y==4&&!eventList[3]){e=true;}
		if(x==1&&y==4){w=true;e=true;n=true;}
		if(x==0&&y==4){e=true;n=true;}
		if(x==3&&y==3){n=true;s=true;}
		if(x==1&&y==3&&eventList[7]){n=true;s=true;}
		if(x==0&&y==3&&eventList[6]){n=true;s=true;}
		if(x==0&&y==3&&!eventList[6]){s=true;}
		if(x==4&&y==2){w=true;n=true;}
		if(x==3&&y==2){e=true;n=true;s=true;}
		if(x==1&&y==2){w=true;n=true;s=true;}
		if(x==4&&y==1){s=true;}
		if(x==3&&y==1){w=true;s=true;}
		if(x==1&&y==1&&!eventList[8]){w=true;s=true;}
		if(x==1&&y==1&&eventList[8]){w=true;n=true;s=true;}
		if(x==0&&y==1){e=true;}
		if(x==1&&y==0){s=true;}
		writeExit();
	}
	public void writeExit() throws InterruptedException
	{
		String ex ="Exits are at ";
		if(n)ex+="NORTH ";
		if(s)ex+="SOUTH ";
		if(e)ex+="EAST ";
		if(w)ex+="WEST ";
		write(ex);
	}
	public void initlocs()
	{
		location = new String[5][5];
		location[0][0] = "You find yourself in a corner of the map.\n This is a place that should be locked, but you somehow cheated your way in.\nGood job";
		location[0][1] = "You find yourself at the exit.\n Air rushes into the room, the breeze carrying in branches and leaves through the window.\nA single GRATE lies above you.";
		location[0][2] = "You find yourself in a wall.\n Seriously,wtf";
		location[0][3] = "You find yourself in a wall.\n Seriously,wtf";
		location[0][4] = "You find yourself in a corner of the map.\n This is a place that should be locked, but you somehow cheated your way in.\nGood job";
		location[1][0] = "You find yourself in an empty hallway.\n There's not much here.\n Wind blows in from the EAST.";
		location[1][1] = "You find yourself in a room with a DOOR.\n The DOOR is think and heavy, as if you can't move it. ";
		location[1][2] = "You find yourself in a trap, oops";
		location[1][3] = "You find yourself in an empty hallway.\n Well, almost empty. There is a LANTERN on the wall.";
		location[1][4] = "You find yourself in front of a CHEST.\n You don't care about the rest of the room because there is a CHEST.";
		location[2][0] = "You find yourself in a HOLE.\n You continue to fall until the game ends.";
		location[2][1] = "You find yourself in an empty hallway.\n Good job getting this far, don't mess up now.\n Wind blows in from the WEST and NORTH.";
		location[2][2] = "You find yourself in a wall.\n Seriously,wtf";
		location[2][3] = "You find yourself in a large room.\n This is the west side of the room.\n It's kinda messy here, but you think you can make it to the other side.";
		location[2][4] = "You find yourself in a large room.\n This is the east side of the room.\n It's really clean, but you can hear something coming from the NORTH.";
		location[3][0] = "You find yourself in front of a GRUE.\n You see a shield on the west wall.\n Wind blows in from the NORTH";
		location[3][1] = "You find yourself in front of THE AUTOMATIC PART TWO EXPENSIVE SUPER TREANT.\n Unless you have the right equipment, this might be the end for you.";
		location[3][2] = "You find yourself in a wall.\n Seriously,wtf";
		location[3][3] = "You find yourself in an empty hallway.";
		location[3][4] = "You find yourself in a wall.\n Seriously,wtf";
		location[4][0] = "You find yourself in a storage room.\n There is a SWORD on display for only 11.29 Crone.\n Strange noises come from the north.";
		location[4][1] = "You find yourself in an empty hallway.\n Something seems to be at NORTH";
		location[4][2] = "You find yourself in front of a DOOR.\n Perhaps there is a key?";
		location[4][3] = "You find yourself in an empty hallway";
		location[4][4] = "You find yourself at the start of the Dungeon.\n This is where your adventure started, so I'm sure there is nothing to do here.";
	}
	class Actions extends KeyAdapter 
	{
        @Override
        public void keyPressed(KeyEvent e) 
        {
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_ENTER|| key==KeyEvent.VK_LEFT)
            {
            	if(!end)
            	{
            		try {
            			words.setText("");
            			action(parse());
            		} catch (InterruptedException e1) {
            			// TODO Auto-generated catch block
            			e1.printStackTrace();
            		}
            	}
            	else if(!win&&!t)
            	{
            		try {
						write("\n\n GAME OVER");
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            	}
            	else if(!t)
            	{
            		try {
						write("\n\n YOU WIN");
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            	}
            }
        }
	}
}
