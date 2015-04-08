package spaceAndinvaders;

import com.leleusoft.gameframework.Screen;
import com.leleusoft.gameframework.implementation.AndroidGame;

public class Launcher extends AndroidGame {

	@Override
	public Screen getInitScreen() {
	
		return new LoadingScreen(this);
	}

}
