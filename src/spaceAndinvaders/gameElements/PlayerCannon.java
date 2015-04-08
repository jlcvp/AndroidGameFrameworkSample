package spaceAndinvaders.gameElements;

import spaceAndinvaders.MovingDirection;
import android.graphics.Point;

import com.leleusoft.gameframework.Image;

public class PlayerCannon extends GenericGameObject{
	//canvas size = anything x 448 //scaled related to 448 in device aspect ratio
	private static final int MIN_X_POSITION = 10;
	private static final int STEP = 5;
	private static final int MAX_X_POSITION = 448 -(10+20);
	private static final int Y_POSITION = 160; //related to the bottom of the screen
	
	
	Image image;
	boolean isMoving;
	


	MovingDirection movingDirecion;
	
	public PlayerCannon(Point point, Image img)
	{
		position = new Point(point.x, point.y-Y_POSITION);
		image = img;
		isMoving = false;
		movingDirecion=MovingDirection.STAY;
	}
	
	
	public void update(long ignoredParam) //the cannon itself doesn't have animation
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
