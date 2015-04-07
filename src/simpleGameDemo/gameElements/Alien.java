package simpleGameDemo.gameElements;

import simpleGameDemo.GameAssets;
import simpleGameDemo.MovingDirection;
import simpleGameDemo.frameworkHelpers.Animation;
import android.graphics.Point;
import android.graphics.Rect;

import com.leleusoft.gameframework.Image;

public class Alien extends GenericGameObject {
	public static final int DEAD_MAX_TIME = 700; //ms
	Animation sprites;
	Rect colisionRect;
	MovingDirection movingDirection;
	State state;
	long timeElapsedSinceDeath;
	enum State{
		DEAD,ALIVE,READY_TO_REMOVE
	};
	
	//TODO aliens accelerates through gameplay, frametime not constant
	public Alien(Image[] animationSprites, int frameTime, 
				Point initialPosition, MovingDirection initialMovingDiretion)
	{
		position = initialPosition;
		colisionRect = new Rect(position.x, position.y,position.x+26, position.y+16);
		sprites = new Animation();
		for(Image i:animationSprites)
		{
			sprites.addFrame(i, frameTime);
		}
		state = State.ALIVE;
	}
	
	@Override
	public void update(long deltaTime) {		
		switch(state){
		case ALIVE:
			sprites.update(deltaTime);
			break;
		case DEAD:
			timeElapsedSinceDeath+=deltaTime;
			if(timeElapsedSinceDeath>DEAD_MAX_TIME)
			{
				state = State.READY_TO_REMOVE;
			}
			break;
		default:
			break;
		
		}
		
		
	}	
		
	@Override
	public Image getImage() {
		return (state == State.ALIVE?sprites.getImage():GameAssets.dead_alien);		
	}
	
	public Rect getColisionRect()
	{
		return colisionRect;
	}
	
	public boolean shouldRemove()
	{
		return (state==State.READY_TO_REMOVE);
	}
	
	public void kill()
	{
		state = State.DEAD;
		timeElapsedSinceDeath =0;
	}

	public boolean isDead() {
		return state == State.DEAD;
	}

	public boolean isAlive() {
		// TODO Auto-generated method stub
		return state == State.ALIVE;
	}

}
