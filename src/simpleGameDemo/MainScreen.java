package simpleGameDemo;

import java.util.List;

import simpleGameDemo.gameElements.FloatingHelloWorld;
import simpleGameDemo.gameElements.PlayerCannon;
import android.graphics.Color;

import com.leleusoft.gameframework.Game;
import com.leleusoft.gameframework.Input.TouchEvent;
import com.leleusoft.gameframework.implementation.AndroidGraphics;
import com.leleusoft.gameframework.implementation.AndroidScreen;

public class MainScreen extends AndroidScreen {

	
	
	//declare your game objects here
	FloatingHelloWorld floatingObject;
	PlayerCannon cannon;
	
	public MainScreen(Game game) {
		super(game);
		cannon = new PlayerCannon(game.getGraphics().getWidth()/2, GameAssets.player_cannon);		
	}
	
	@Override
	public void update(float deltaTime) {
		handleTouchEvents();		
		
		cannon.update();
		
		super.update(deltaTime); //this is called to sync FPS of your game
	}

	private void handleTouchEvents() {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		
		for(TouchEvent event:touchEvents)
		{
			switch(event.type)
			{
				case TouchEvent.TOUCH_DOWN:
					cannon.setMoving(true);
					
					if(event.x<=game.getGraphics().getWidth()/2)
					{
						cannon.setMovingDirecion(MovingDirection.LEFT);
					}
					else
					{
						cannon.setMovingDirecion(MovingDirection.RIGHT);
					}
					break;
					
				case TouchEvent.TOUCH_UP:
					cannon.setMoving(false);					
			}			
		}
		
	}

	@Override
	public void paint(float deltaTime) {
		AndroidGraphics g = (AndroidGraphics) game.getGraphics();
		g.clearScreen(Color.BLACK);
		g.drawImage(cannon.getImage(), cannon.getXPosition(), g.getHeight()-cannon.getYPosition());		
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
