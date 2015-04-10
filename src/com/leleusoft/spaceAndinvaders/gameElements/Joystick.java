package com.leleusoft.spaceAndinvaders.gameElements;

import android.graphics.Point;
import android.graphics.Rect;

import com.leleusoft.gameframework.Image;
import com.leleusoft.spaceAndinvaders.GameAssets;
import com.leleusoft.spaceAndinvaders.MovingDirection;

public class Joystick {

	public enum JoystickState{
		DRAGGED, IDLE
	}

	enum JoyDirection
	{
		LEFT_SOFT, LEFT_HARD, NONE, RIGHT_SOFT, RIGHT_HARD
	}

	public Image joystick_head;
	public Image joystick_pad;
	JoystickState joystickState;
	JoyDirection direction;
	Rect elementRect;

	Point position;
	int headPosition; //0 == middle 

	public Joystick(Point position)
	{
		this.position=position;	
		joystick_head = GameAssets.joystick_head;
		joystick_pad = GameAssets.joystick_pad;
		elementRect = new Rect(position.x, position.y, 
				position.x+joystick_pad.getWidth(), 
				position.y+joystick_pad.getHeight());
	}

	public Point getHeadPosition()
	{
		return new Point(position.x+joystick_pad.getWidth()/2+headPosition-25, position.y);
	}

	public Point getPadPosition()
	{
		return position;
	}

	public void setheadPosition(int xPosition)
	{
		joystickState = JoystickState.DRAGGED;
		if(xPosition<=position.x+25){
			headPosition=-37;

		}
		else if(xPosition>=position.x+102)
		{
			headPosition=37;
		}
		else
		{
			headPosition= xPosition - (position.x+63);
		}

		if(headPosition>0)
		{
			if(headPosition<37)
			{
				direction=JoyDirection.RIGHT_SOFT;
			}
			else
			{
				direction = JoyDirection.RIGHT_HARD;
				
			}
			
		}
		else
		{
			if(headPosition>-37)
			{
				direction = JoyDirection.LEFT_SOFT;
			}
			else
			{
				direction = JoyDirection.LEFT_HARD;
				
			}
		}		
	}

	public void releaseJoystick()
	{
		direction=JoyDirection.NONE;
		joystickState = JoystickState.IDLE;
		headPosition=0;
	}

	public JoystickState getState()
	{
		return joystickState;
	}

	public Rect getElementBoundaries()
	{
		return elementRect;
	}

	public MovingDirection getDirection()
	{
		switch(direction)
		{
			case LEFT_HARD:				
			case LEFT_SOFT:
				return MovingDirection.LEFT;

			case NONE:
				return MovingDirection.STAY;

			case RIGHT_SOFT:
			case RIGHT_HARD:
				return MovingDirection.RIGHT;
			default:
				return MovingDirection.STAY;

		}
	}

}
