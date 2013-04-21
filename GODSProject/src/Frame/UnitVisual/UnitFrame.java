package Frame.UnitVisual;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Frame.OvermindFrame;

public class UnitFrame extends JPanel{

	private JLabel _ImgLabel = new JLabel();
	private VisualType _VisualType = null;
	
	public UnitFrame(){
		super();
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		this.setSize(VisualType.SWARMLING.size(), VisualType.SWARMLING.size());
		_ImgLabel = new JLabel(new ImageIcon(this.getClass().getResource(VisualType.SWARMLING.path())));
		this.add(this._ImgLabel, BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	public UnitFrame(VisualType aVisualType){
		super();
		
		_VisualType = aVisualType;
		
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		this.setSize(aVisualType.size(), aVisualType.size());
		_ImgLabel = new JLabel(new ImageIcon(this.getClass().getResource(aVisualType.path())));
		this.add(this._ImgLabel, BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	public void updateVisualType(VisualType aVisualType){
		_VisualType = aVisualType;
		this.setSize(aVisualType.size(), aVisualType.size());
		_ImgLabel = new JLabel(new ImageIcon(this.getClass().getResource(aVisualType.path())));
	}
	
	public String toString(){
		return _VisualType.path();
	}
}
