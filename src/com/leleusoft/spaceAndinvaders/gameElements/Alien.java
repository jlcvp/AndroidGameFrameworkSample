package com.leleusoft.spaceAndinvaders.gameElements;

import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;

import com.leleusoft.gameframework.Image;
import com.leleusoft.spaceAndinvaders.GameAssets;
import com.leleusoft.spaceAndinvaders.MovingDirection;
import com.leleusoft.spaceAndinvaders.frameworkHelpers.Animation;

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
		colisionRect = new Rect(position.x, position.y-4,position.x+26, position.y+16+4); //height = 24
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
