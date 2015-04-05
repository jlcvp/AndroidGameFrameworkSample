package simpleGameDemo;

import simpleGameDemo.gameElements.FloatingHelloWorld;
import android.graphics.Color;

import com.leleusoft.gameframework.Game;
import com.leleusoft.gameframework.Graphics;
import com.leleusoft.gameframework.implementation.AndroidScreen;

public class MainScreen extends AndroidScreen {

	FloatingHelloWorld floatingObject;
	public MainScreen(Game game) {
		super(game);
		floatingObject = new FloatingHelloWorld(GameAssets.hello_world_img);
		
	}
	
	@Override
	public void update(float deltaTime) {
		
		floatingObject.update(game.getGraphics());
		
		super.update(deltaTime); //this is called to sync FPS of your game
	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		g.clearScreen(Color.LTGRAY);
		g.drawImage(floatingObject.img, floatingObject.position.x, floatingObject.position.y);
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
