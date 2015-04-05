package simpleGameDemo;

import com.leleusoft.gameframework.Game;
import com.leleusoft.gameframework.Graphics;
import com.leleusoft.gameframework.Graphics.ImageFormat;
import com.leleusoft.gameframework.implementation.AndroidScreen;

public class LoadingScreen extends AndroidScreen {

	public LoadingScreen(Game game) {
		super(game);		
	}


	/**
	 * this method is called when the screen rotates. If you have some
	 * screen size dependent object, you should update it here
	 */
	@Override
	public void updateParams() {


	}


	@Override
	public void update(float deltaTime) {		
		//here we will load some game elements needed to the nextScreen (menu screen)
		Graphics g = game.getGraphics();
		GameAssets.hello_world_img = g.newImage("helloworld.png", ImageFormat.ARGB8888);
		GameAssets.player_cannon = g.newImage("player_cannon.png",  ImageFormat.ARGB8888);

		game.setScreen(new MainScreen(game));
		//super.update(deltaTime); 
	}

	/**
	 * this method is called in a loop to paint game elements on screen
	 */
	@Override
	public void paint(float deltaTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void backButton() {
		// TODO Auto-generated method stub

	}

}
