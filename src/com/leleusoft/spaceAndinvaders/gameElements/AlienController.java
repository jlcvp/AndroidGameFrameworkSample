package com.leleusoft.spaceAndinvaders.gameElements;

import java.util.ArrayList;
import java.util.List;

import com.leleusoft.gameframework.Sound;
import com.leleusoft.gameframework.implementation.AndroidGraphics;
import com.leleusoft.spaceAndinvaders.GameAssets;

public class AlienController {
	List<Alien> list;
	long loop_timer;
	long bg_interval;
	int currentBgSound;
	Sound[] bg;
	Sound alienKillSound;

	public AlienController(List<Alien> enemylist)
	{
		loop_timer = 0;
		bg_interval = 875;
		this.list = enemylist;
		bg = GameAssets.bg;
		alienKillSound = GameAssets.alien_death_sound;
	}
	
	public void update(long elapsedTime)
	{
		loop_timer+= elapsedTime;
		if(loop_timer>bg_interval)
		{
			loop_timer =0;
			currentBgSound= (currentBgSound+1)%bg.length;
			if(currentBgSound==0 && bg_interval>50)
			{
				bg_interval -=25;
			}
			bg[currentBgSound].play(0.8f);			
		}
		
		updateAliens(elapsedTime);
	}
	
	public void drawAliens(AndroidGraphics g) {
		for(Alien alien:list)
		{
			g.drawImage(alien.getImage(),alien.getPosition().x,alien.getPosition().y);
		}

	}	
	
	
	public void handleCleannings() {
		ArrayList<Alien> temp = new ArrayList<Alien>(list.size());

		for(Alien i:list)
		{
			if(i.shouldRemove())
			{
				temp.add(i);
			}
		}

		for(Alien i:temp)
		{
			list.remove(i);
		}

	}
	
	
	public int handleColisionsAndScore(Projectile projectile) {
		List<Alien> enemies = list;
		int score = 0;
		if(projectile != null)
			for(Alien alien:enemies)
			{
				if(alien.isAlive() && (alien.getColisionRect().contains(projectile.getPosition().x, projectile.getPosition().y) 
						||alien.getColisionRect().contains(projectile.getPosition().x, projectile.getPosition().y+14)))
				{
					alien.kill();
					score+=25;
					projectile=null;
					alienKillSound.play(1.0f);
					//shootSound.stop(); //in the original arcade, the sound didn't stop at colision
					break;
				}
			}
		return score;
	}
	
	private void updateAliens(long deltaTime) {
		for(Alien alien:list)
		{
			alien.update(deltaTime);
		}		
	}

}
