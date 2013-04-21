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
					
					Overmind theOvermind = new Overmind(new Vector2(550,180));
					AppFrame.getInstance().setVisible(true);
					Swarmodon odon = new Swarmodon(new Vector2(550, 180));
					Swarmide ide = new Swarmide(new Vector2(550, 180));
					Swarmling ling = new Swarmling(new Vector2(550, 180));
					odon.changeState(SwarmodonState.GoingTo);
					ide.changeState(SwarmideState.GoingTo);
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
