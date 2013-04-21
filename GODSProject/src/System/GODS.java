package System;
import java.awt.EventQueue;

import javax.swing.JFrame;

import Frame.AppFrame;


public class GODS {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//GODS window = new GODS();
					//window.frame.setVisible(true);
					
					AppFrame.getInstance().setVisible(true);
					Swarmling ling = new Swarmling(new Vector2(100,100));
					ling.setGoal(new Vector2(550, 180));
					ling.changeState(SwarmlingState.GoingTo);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GODS() {
		
		
		//Swarmide ide = new Swarmide();
		//Swarmodon odon = new Swarmodon();
		
		
		initialize();
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
