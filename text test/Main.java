
import java.util.*;

public class Main {

	private static int x;
	private static int y;
	private static String[][] location;
	private static boolean[] eventList = new boolean[11];
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
	private static String[] items;
	private static Scanner in = new Scanner(System.in);
	private static boolean end = false;
	private static boolean win = false;
	private static boolean w;
	private static boolean e;
	private static boolean n;
	private static boolean s;
	public static void main(String[] args) {
		initlocs();
		System.out.println("insert cool acsii art here");
		System.out.println("title stuff and welcome");
		System.out.println("intro later");
		x=4;
		y=4;
		
		while(!end)
		{
			w=false;n=false;s=false;e=false;
			System.out.println(location[y][x]);
			System.out.println();
			System.out.println(exits());
			action(parse());
			if(x==0&&y==2)
			{
				System.out.println(location[y][x]);
				end=true;
			}
			if(x==2&&y==1)
			{
				System.out.println(location[y][x]);
				end=true;
			}
		}
		if(win)
		{
			System.out.println("you leave school, happy that you finished your ap test");
		}
		System.out.println("the game has ended");
		
	}
	public static void action(String act)
	{
		boolean moved=false;
		if(act.equals("move n")||act.equals("n"))
		{
			if(n)
			{
				y-=1;
				System.out.println("you move north");
				moved = true;
			}
			else if(x==0&&y==3)
			{
				end = true;
				System.out.println("You got eaten by the grue");
				moved= true;
			}
			else if(x==1&&y==3&&!eventList[7])
			{
				end = true;
				System.out.println("You try to move forward but you can't, because you are dead.");
				moved = true;
			}
			else if(x==1&&y==1&&!eventList[8])
			{
				System.out.println("You move forward and hit your head on the door three times.\n It doesn't work.");
			}
			else
			{
				System.out.println("you try to move north but you can't, for multiple reasons.");
			}
		}
		else if(act.equals("move s")||act.equals("s"))
		{
			if(s)
			{
				y+=1;
				System.out.println("you move south");
				moved = true;
			}
			else
			{
				System.out.println("You try to move south but can't, for multiple reasons.");
			}
		}
		else if(act.equals("move e")||act.equals("e"))
		{
			if(e)
			{
				x+=1;
				System.out.println("you move east");
				moved = true;
			}
			else
			{
				System.out.println("You try to move east but can't, for multiple reasons");
			}
		}
		else if(act.equals("move w")||act.equals("w"))
		{
			if(w)
			{
				x-=1;
				System.out.println("you move west");
				moved = true;
			}
			else if(x==2&&y==4&&!eventList[3])
			{
				System.out.println("You move west and stub your toe.\n It looks like you need to open the door first,");
			}
			else
			{
				System.out.println("You try to move west but can't, for multiple reasons.");
			}
		}
		else if(act.equals("open chest"))
		{
			if(x==4&&y==1&&!eventList[2])
			{
				System.out.println("You open the chest and get a key!");
				eventList[2]=true;
			}
			else if(x==4&&y==1&&eventList[2])
			{
				System.out.println("You open the chest, but it was empty");
			}
			else
			{
				System.out.println("You attempt to open a chest, but the only chest here is yours");
			}
		}
		else if(act.equals("open door"))
		{
			if(x==2&&y==4&&!eventList[3]&&!eventList[2])
			{
				System.out.println("You open the door, but it was locked.");
			}
			else if(x==2&&y==4&&!eventList[3]&&eventList[2])
			{
				System.out.println("You open the door with your KEY, and now the door is open.");
				eventList[3]= true;
			}
			else if(x==2&&y==4&&eventList[3])
			{
				System.out.println("You open the door but it can't open anymore");
			}
			else if(x==1&&y==1&&!eventList[8]&&!eventList[9])
			{
				System.out.println("Yeah, I don't think that's going to work.\nIt's just too heavy");
			}
			else if(x==1&&y==1&&!eventList[8]&&eventList[9])
			{
				System.out.println("You attempt to open the door, but your lantern OIL spills out down your pants.\n THe door opens out of pity");
				eventList[8]=true;
				eventList[9]=false;
				moved=true;
			}
			else if(x==1&&y==1&&eventList[8])
			{
				System.out.println("The door is open, no joke");
			}
			else
			{
				System.out.println("I think most of the doors here don't exist yet");
			}
		}
		else if(act.equals("open lantern"))
		{
			if(!eventList[9]&&eventList[1])
			{
				System.out.println("You open the LANTERN open and get lantern OIL!\nidk where you put it tho");
				eventList[1]=false;
				eventList[9]=true;
			}
			else
			{
				System.out.println("What LANTERN?");
			}
		}
		else if(act.equals("open grate"))
		{
			if(x==1&&y==0)
			{
				System.out.println("You open the DOOR and leave as class ends.");
				end=true;
				win=true;
				moved=true;
			}
			else
			{
				System.out.print("What GRATE");
			}
		}
		else if(act.equals("take lantern"))
		{
			if(x==3&&y==1&&!eventList[1])
			{
				System.out.println("You take the LANTERN.");
				eventList[1]=true;
			}
			else
			{
				System.out.println("What LANTERN?");
			}
		}
		else if(act.equals("take sword"))
		{
			if(x==0&&y==4&&!eventList[4])
			{
				System.out.println("You take the PEN from the shelf");
				eventList[4]=true;
			}
			else
			{
				System.out.println("What SWORD?");
			}
		}
		else if(act.equals("take shield"))
		{
			if(x==0&&y==3&&!eventList[6])
			{
				System.out.println("dat grue tho");
			}
			else if(x==0&&y==3&&eventList[6]&&!eventList[5])
			{
				System.out.println("you take the ERASER from the wall");
				eventList[5] =true;
			}
			else
			{
				System.out.println("What SHIELD?");
			}
		}
		else if(act.equals("use lantern"))
		{
			if(x==0&&y==3&&!eventList[6]&&eventList[1])
			{
				System.out.println("You wave your LANTERN and the grue goes away");
				eventList[6]=true;
			}
			else
			{
				System.out.println("I'd like to but I don't want to");
			}
		}
		else if(act.equals("use sword"))
		{
			System.out.println("What SWORD?");
		}
		else if(act.equals("use pen"))
		{
			if(x==1&&y==3&&eventList[4]&&eventList[5])
			{
				System.out.println("You take up your pen and guess C for like half the questions.\n Your eraser is fully chewed through due to stress.\n At last the APTEST is dead.");
				eventList[7]=true;
				moved=true;
			}
			else if(x==1&&y==3&&eventList[4]&&!eventList[5])
			{
				System.out.println("You take up your pen and guess C for like half the questions.\n You die from stress halfway through.");
				end = true;
				moved = true;
			}
			else 
			{
				System.out.println("What PEN?");
			}
		}
		else if(act.equals("use shield"))
		{
				System.out.println("What SHIELD?");
		}
		else if(act.equals("use eraser"))
		{
			if(eventList[5])
				System.out.println("You chew on the ERASER a bit");
			else
				System.out.println("What ERASER");
		}
		else
			System.out.println("idk");
		if(!moved)
			action(parse());
	}
	
	public static String exits()
	{
		if(x==4&&y==4){w=true;return"WEST";}
		if(x==3&&y==4){w=true;e=true;n=true;return"WEST,EAST,NORTH";}
		if(x==2&&y==4&&eventList[3]){w=true;e=true;return"WEST,EAST";}
		if(x==2&&y==4&&!eventList[3]){e=true;return"EAST";}
		if(x==1&&y==4){w=true;e=true;n=true;return"WEST,EAST,NORTH";}
		if(x==0&&y==4){e=true;n=true;return"EAST,NORTH";}
		if(x==3&&y==3){n=true;s=true;return"NORTH,SOUTH";}
		if(x==1&&y==3&&!eventList[7]){return"NONE";}
		if(x==1&&y==3&&eventList[7]){n=true;s=true;return"NORTH,SOUTH";}
		if(x==0&&y==3&&eventList[6]){n=true;s=true;return"NORTH,SOUTH";}
		if(x==0&&y==3&&!eventList[6]){s=true;return"SOUTH";}
		if(x==4&&y==2){w=true;n=true;return"WEST,NORTH";}
		if(x==3&&y==2){e=true;n=true;s=true;return"EAST,NORTH,SOUTH";}
		if(x==1&&y==2){w=true;n=true;s=true;return"WEST,NORTH,SOUTH";}
		if(x==4&&y==1){s=true;return"SOUTH";}
		if(x==3&&y==1){w=true;s=true;return"WEST,SOUTH";}
		if(x==1&&y==1&&!eventList[8]){w=true;s=true;return"WEST,SOUTH";}
		if(x==1&&y==1&&eventList[8]){w=true;n=true;s=true;return"WEST,NORTH,SOUTH";}
		if(x==0&&y==1){e=true;return"EAST";}
		if(x==1&&y==0){s=true;return"SOUTH";}
		return"";
	}
	
	public static String parse()
	{
		String string = in.nextLine();
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
		}
		System.out.println(re);
		return re;
	}
	public static void initlocs()
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

}
