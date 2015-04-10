package com.leleusoft.spaceAndinvaders.gameElements;

import android.graphics.Point;

import com.leleusoft.gameframework.Image;

public class Barricade extends GenericGameObject{
	int[][] colisionMatrix;
	
	
	public Barricade(Point position)
	{
		colisionMatrix = new int[4][6];
		for(int i=0; i<4;i++)
		{
			for(int j=0; j<6;j++)
			colisionMatrix[i][j]=1;
		}
	}

	@Override
	public void update(long deltaTime) {				
	}

	@Override
	public Image getImage() {		
		return null;
	}
	
	public boolean pew(Point projectile)
	{
		//TODO 
		return colisionSquare.contains(projectile.x, projectile.y);
	}

	
}
