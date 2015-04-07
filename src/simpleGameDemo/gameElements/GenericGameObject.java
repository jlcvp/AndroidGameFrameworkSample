package simpleGameDemo.gameElements;

import android.graphics.Point;
import android.graphics.Rect;

import com.leleusoft.gameframework.Image;

public abstract class GenericGameObject {
	Rect colisionSquare;
	Point position;
	
	public Point getPosition()
	{
		return position;
	}
	
	//Method to update the object params, such as animation sprite, and other stuff
	public abstract void update(long deltaTime);
	
	//Will return the current sprite of the object
	public abstract Image getImage();
	
	

}
