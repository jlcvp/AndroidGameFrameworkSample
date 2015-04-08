package com.leleusoft.spaceAndinvaders.gameElements;

import android.graphics.Point;

import com.leleusoft.gameframework.Graphics;
import com.leleusoft.gameframework.Image;

public class FloatingHelloWorld {
	public Point position;
	int step = 5; //how many pixels it'll skip each frame
	public Image img;
	boolean horizontalDirection,verticalDirection;

	public FloatingHelloWorld(Image image)
	{
		position=new Point(0,0);
		img = image;

	}

	public void update(Graphics g) //the game will control the FPS, no need to concern about timings
	{
		position.x += horizontalDirection?step:-step;
		position.y += verticalDirection?step:-step;

		if(position.x+img.getWidth()>g.getWidth() || position.x<0)
		{
			horizontalDirection=!horizontalDirection;
		}

		if(position.y+img.getHeight()>g.getHeight() || position.y<0)
		{
			verticalDirection=!verticalDirection;
		}

	}
}
