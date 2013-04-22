package System;

import java.awt.Point;

import Frame.EnvironmentFrame;
import Frame.UnitVisual.UnitFrame;
import Frame.UnitVisual.VisualType;

public class EnergyBall extends GameObject{

	private int numberLeft;
	private UnitFrame _frame;
	Point _pos; 
	
	
	public EnergyBall(int size, int posX, int posY)
	{
		super();
		numberLeft = size;
		_pos = new Point();
		_pos.x = posX;
		_pos.y = posY;
		
		if(size <= 4){
			_frame = new UnitFrame(VisualType.D_TERANLING);
		}else if (size <= 8){
			_frame = new UnitFrame(VisualType.D_TERANIDE);
		}else{
			_frame = new UnitFrame(VisualType.D_TERANODON);
		}
	}
	
	public void takeFoodUnit(){
		numberLeft = numberLeft - 1;
		if(numberLeft <= 0){
			EnvironmentFrame.getInstance().removeEnergyBall(this);
		}
	}
	
	public UnitFrame getFrame(){
		return _frame;
	}
	
	public int getXi(){
		return _pos.x;
	}
	
	public int getYi(){
		return _pos.y;
	}
	
	public Vector2 getPos(){
		return new Vector2(_pos.x,_pos.y);
	}
	
	public Point getPoint(){
		return _pos;
	}
}
