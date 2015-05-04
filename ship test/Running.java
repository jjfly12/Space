import javax.swing.JFrame;
public class Running {

	public static void main(String[] args)
	{
		JFrame test = new JFrame();
		test.add(new Main());
		test.pack();
		test.setSize(1000, 1000);
		test.setVisible(true);
	}
	
}
