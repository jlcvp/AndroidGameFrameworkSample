package simpleGameDemo.gameElements;

import android.graphics.Point;

import com.leleusoft.gameframework.Sound;

public class Projectile{
	
	Point position;	
	public Projectile(Point initialPosition)
	{
		position = initialPosition;
	}
	
	public void update(long deltaTime)
	{
		position.y-= (int)Math.floor(deltaTime/1.5d);
	}
	
	public Point getPosition()
	{
		return position;
	}
	
}
