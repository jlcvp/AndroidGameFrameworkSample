package simpleGameDemo.gameElements;

import simpleGameDemo.MovingDirection;
import android.graphics.Point;

import com.leleusoft.gameframework.Image;

public class PlayerCannon {
	//canvas size = 640x360 //scaled related to 360
	private static final int MIN_X_POSITION = 10;
	private static final int STEP = 4;
	private static final int MAX_X_POSITION = 320;
	private static final int Y_POSITION = 60; //related to the bottom of the screen
	
	Point position;
	Image image;
	boolean isMoving;
	


	MovingDirection movingDirecion;
	
	public PlayerCannon(int initialPosition, Image img)
	{
		position = new Point(initialPosition, Y_POSITION);
		image = img;
		isMoving = false;
		movingDirecion=MovingDirection.STAY;
	}
	
	
	public void update()
	{
		if(isMoving){
			switch(movingDirecion)
			{
			case LEFT:
				moveLeft();
				break;
			case RIGHT:
				moveRight();
				break;
			default:
				break;			
			}
		}
	}
	
	
	
	public Image getImage()
	{
		return image;
	}
	
	public int getXPosition()
	{
		return position.x;
	}
	
	public int getYPosition()
	{
		return position.y;
	}
	
	private void moveLeft()
	{
		if(position.x>MIN_X_POSITION)
			position.x-=STEP;
	}
	
	private void moveRight()
	{
		if(position.x<MAX_X_POSITION)
			position.x+=STEP;
	}
	
	public boolean isMoving() {
		return isMoving;
	}


	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}


	public void setMovingDirecion(MovingDirection movingDirecion) {
		this.movingDirecion = movingDirecion;
	}
	
	
}
