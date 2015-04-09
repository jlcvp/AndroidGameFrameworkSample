package com.leleusoft.spaceAndinvaders;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;

import com.leleusoft.gameframework.Game;
import com.leleusoft.gameframework.Input.TouchEvent;
import com.leleusoft.gameframework.Sound;
import com.leleusoft.gameframework.implementation.AndroidGraphics;
import com.leleusoft.gameframework.implementation.AndroidScreen;
import com.leleusoft.spaceAndinvaders.gameElements.Alien;
import com.leleusoft.spaceAndinvaders.gameElements.FloatingHelloWorld;
import com.leleusoft.spaceAndinvaders.gameElements.Joystick;
import com.leleusoft.spaceAndinvaders.gameElements.Joystick.JoystickState;
import com.leleusoft.spaceAndinvaders.gameElements.PlayerCannon;
import com.leleusoft.spaceAndinvaders.gameElements.Projectile;

public class MainScreen extends AndroidScreen {

	private static final int BUTTON_WIDTH = 60;
	private static final int BUTON_HEIGHT = 40;
	private static final int DEFAULT_PADDING = 30;

	//declare your game objects here	
	FloatingHelloWorld floatingObject;
	PlayerCannon cannon;
	Projectile projectile;
	List<Alien> enemies;
	Sound shootSound;
	Sound alienKillSound;
	int score,lives,highScore;
	Joystick joystick;

	//Help Objects 
	Paint font;



	//	FPSCounter mFPSCounter = new FPSCounter() {
	//
	//		@Override
	//		protected void logOutput() {		
	//			fps = frames;
	//		}
	//	};

	Rect button_fire;
	boolean isFireButtonPressed;


	public MainScreen(Game game) {
		super(game);
		cannon = new PlayerCannon(new Point(game.getGraphics().getWidth()/2,game.getGraphics().getHeight()), GameAssets.player_cannon);
		isFireButtonPressed = false;
		createAliens();
		shootSound = GameAssets.shoot_sound;
		alienKillSound = GameAssets.alien_death_sound;

		font = new Paint();
		Typeface typeface = Typeface.createFromAsset(((Activity)game).getAssets(), "space_invaders.ttf");
		font.setTypeface(typeface);
		font.setTextSize(20);
		font.setAntiAlias(true);

		lives = 3;

		button_fire = new Rect(game.getGraphics().getWidth()- BUTTON_WIDTH -DEFAULT_PADDING, 
				game.getGraphics().getHeight() - DEFAULT_PADDING -BUTON_HEIGHT,
				game.getGraphics().getWidth()-DEFAULT_PADDING, 
				game.getGraphics().getHeight() - DEFAULT_PADDING);

		joystick = new Joystick(new Point(30+DEFAULT_PADDING,button_fire.top));

	}

	private void createAliens() {
		enemies = new ArrayList<Alien>();
		Alien alien;
		projectile=null;
		for(int i=0; i<5; i++){
			for(int j=0;j<11;j++)
			{
				alien = new Alien(GameAssets.alien1, 700, new Point(DEFAULT_PADDING + (22+10)*j , 240+DEFAULT_PADDING + 32*i), null);
				enemies.add(alien);
			}
			System.gc();
		}		
	}

	@Override
	public void update(float deltaTime) {


		handleTouchEvents();
		cannon.update(0);
		updateAliens((long)deltaTime);
		if(projectile!=null) // if projectile is on screen(exists) 
		{
			projectile.update((long)deltaTime);
			if(projectile.getPosition().y<=0)
			{
				projectile = null;
			}
		}


		handleColisions();
		handleCleannings();


		super.update(deltaTime); //this is called to sync FPS of your game
	}

	private void handleCleannings() {
		ArrayList<Alien> temp = new ArrayList<Alien>(enemies.size());

		for(Alien i:enemies)
		{
			if(i.shouldRemove())
			{
				temp.add(i);
			}
		}

		for(Alien i:temp)
		{
			enemies.remove(i);
		}

	}

	private void handleColisions() {
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

	}

	private void updateAliens(long deltaTime) {
		for(Alien alien:enemies)
		{
			alien.update(deltaTime);
		}		
	}

	private void handleTouchEvents() {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

		for(TouchEvent event:touchEvents)
		{
			switch(event.type)
			{
				case TouchEvent.TOUCH_DOWN:

					if(button_fire.contains(event.x, event.y) && projectile == null)
					{
						projectile = new Projectile(new Point(cannon.getPosition().x+cannon.getImage().getWidth()/2,cannon.getPosition().y-8));
						shootSound.play(1.3f);
						isFireButtonPressed = true;
					}
					else if(joystick.getElementBoundaries().contains(event.x, event.y))
					{
						joystick.setheadPosition(event.x);
						cannon.setMoving(true);
						cannon.setMovingDirecion(joystick.getDirection());
					}
					break;

				case TouchEvent.TOUCH_UP:
					//HEAVY WORKAROUND, but fast! (XGH FEELINGS), don't do this at home
					//HEAVY WORKAROUND, but fast! (XGH FEELINGS), don't do this at home
					//HEAVY WORKAROUND, but fast! (XGH FEELINGS), don't do this at home

					if(event.x<=game.getGraphics().getWidth()/2 && joystick.getState() == JoystickState.DRAGGED)
					{
						joystick.releaseJoystick();
						cannon.setMoving(false);
						cannon.setMovingDirecion(MovingDirection.STAY);
					}
					else
					{
						isFireButtonPressed = false;
					}

					break;

				case TouchEvent.TOUCH_DRAGGED:
					//HEAVY WORKAROUND, but fast! (XGH FEELINGS), don't do this at home
					if(event.x<=game.getGraphics().getWidth()/2 && joystick.getState() == JoystickState.DRAGGED)
					{
						joystick.setheadPosition(event.x);
						cannon.setMovingDirecion(joystick.getDirection());
					}

					break;
			}			
		}

	}


	@Override
	public void paint(float deltaTime) {
		AndroidGraphics g = (AndroidGraphics) game.getGraphics();
		g.clearScreen(Color.BLACK);
		g.drawImage(cannon.getImage(), cannon.getPosition().x, cannon.getPosition().y);	
		drawUI(g);
		drawAliens(g);
		if(projectile!=null)
		{
			drawProjectile(g);
		}
	}





	private void drawUI(AndroidGraphics g) {

		if(isFireButtonPressed)
			g.drawImage(GameAssets.button_fire_pressed, button_fire.left, button_fire.top);
		else
			g.drawImage(GameAssets.button_fire_unpressed, button_fire.left, button_fire.top);

//		g.drawRect(button_fire.left, button_fire.top, button_fire.width(), button_fire.height(), Color.RED);
//		font.setTextAlign(Align.CENTER);
//		g.drawString("FIRE", button_fire.centerX(), button_fire.centerY(), font);


		//SCORE
		font.setTextAlign(Align.LEFT);
		font.setColor(Color.WHITE);	

		g.drawString("SCORE< 1 >   HI-SCORE", DEFAULT_PADDING, DEFAULT_PADDING, font);
		g.drawString(""+score, DEFAULT_PADDING+20, DEFAULT_PADDING+(int)font.getTextSize()+5, font);


		//mFPSCounter.logFrame();

		//		g.drawString("FPS: "+fps , game.getGraphics().getWidth()-DEFAULT_PADDING, DEFAULT_PADDING, font);	
		//

		//green bottom line
		g.drawRect(0, button_fire.top -50, g.getWidth()-1, 2, Color.rgb(32, 255, 32));


		//lives
		font.setTextSize(20);		
		font.setColor(Color.YELLOW);
		font.setTextAlign(Align.LEFT);
		g.drawString("LIVES: "+lives, DEFAULT_PADDING-15,button_fire.top-25 , font);
		for(int i=0;i<lives-1;i++)
		{
			g.drawImage(GameAssets.player_cannon, DEFAULT_PADDING+100 +(35*i), button_fire.top - 45);
		}

		//joystick		
		g.drawImage(joystick.joystick_pad, joystick.getPadPosition().x, joystick.getPadPosition().y);
		g.drawImage(joystick.joystick_head, joystick.getHeadPosition().x, joystick.getHeadPosition().y);

	}

	private void drawProjectile(AndroidGraphics g) {
		g.drawRect(projectile.getPosition().x, projectile.getPosition().y, 3, 14, Color.rgb(32, 255, 32));

	}

	private void drawAliens(AndroidGraphics g) {
		for(Alien alien:enemies)
		{
			g.drawImage(alien.getImage(),alien.getPosition().x,alien.getPosition().y);
		}

	}

	@Override
	public void pause() {		

	}

	@Override
	public void resume() {


	}

	@Override
	public void dispose() {		

	}

	/**
	 * method called when Android back button is pressed
	 */
	@Override
	public void backButton() {


	}

	@Override
	public void updateParams() {

	}

}
