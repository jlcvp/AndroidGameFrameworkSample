package spaceAndinvaders.gameElements;

import android.graphics.Point;

public class Projectile{
	
	Point position;	
	public Projectile(Point initialPosition)
	{
		position = initialPosition;
	}
	
	public void update(long deltaTime)
	{
		int step=(int)Math.floor(deltaTime/1.5d);		
		position.y-= step<30?step:30; //lag Threshold, will avoid laser passing through alien without killing
	}
	
	public Point getPosition()
	{
		return position;
	}
	
}
