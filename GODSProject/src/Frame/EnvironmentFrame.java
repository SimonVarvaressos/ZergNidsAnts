package Frame;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import Frame.UnitVisual.UnitFrame;
import Frame.UnitVisual.VisualType;

// !!! -> size of environment : max x = 568     max y = 389

public class EnvironmentFrame extends JPanel {


	private static final String TXT_TITLE = " ENVIRONMENT ";
	
	private BorderLayout _MainLayout = new BorderLayout();
	
	private JPanel _field = new JPanel();
	
	private TitledBorder _EnvironmentBorder = new TitledBorder(TXT_TITLE);
	
	private UnitFrame _overlord = new UnitFrame(VisualType.OVERMIND);
	
	public EnvironmentFrame(){
		super();
		initField();
		
	}
	
	private void initField(){
		this.setLayout(_MainLayout);
		this.setBorder(_EnvironmentBorder);
		_field.setLayout(null);
		_field.setBackground(java.awt.Color.GRAY);
		this.add(_field,BorderLayout.CENTER);
		
		//this.add(_overlord,BorderLayout.CENTER);
		_overlord.setLocation(545,170);
		_field.add(this._overlord);

		System.out.println(_field.getHeight());
		System.out.println(_field.getWidth());
		//this.repaint();
	}
	
}
