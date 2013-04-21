package System;

public class Vector2 {

	private float x;
	private float y;
	
	Vector2()
	{
		x = 0.0f;
		y = 0.0f;
	}
	
	public Vector2(float theX, float theY)
	{
		x = theX;
		y = theY;
	}
	
	//Accesseurs
	public float getX() { return x; }
	public float getY() { return y; }
	public void setX(float anX) { x = anX; }
	public void setY(float anY) { y = anY; }
	
	//Égalité
	public boolean equals(Vector2 otherVector)
	{
		return (x == otherVector.getX() && y == otherVector.getY());
	}
	
	public void normalize()
	{
		double len = Math.sqrt(x*x + y*y);
		
		if (len != 0.0)
		{
			float s = 1.0f / (float)len;
			x = x*s;
			y = y*s;
		}
	}
}
