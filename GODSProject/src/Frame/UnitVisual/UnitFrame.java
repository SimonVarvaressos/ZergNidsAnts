package Frame.UnitVisual;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Frame.OvermindFrame;

public class UnitFrame extends JPanel{

	private JLabel _ImgLabel = new JLabel();
	
	public UnitFrame(VisualType aVisualType){
		super();
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		this.setSize(aVisualType.size(), aVisualType.size());
		_ImgLabel = new JLabel(new ImageIcon(this.getClass().getResource(aVisualType.path())));
		this.add(this._ImgLabel, BorderLayout.CENTER);
		this.setVisible(true);
	}
}
