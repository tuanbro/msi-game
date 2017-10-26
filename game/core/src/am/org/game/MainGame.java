package am.org.game;

import static am.org.game.resources.InitDataResource.HEIGHT;
import static am.org.game.resources.InitDataResource.WIDTH;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import am.org.game.screen.AnswerScreen;
import am.org.game.screen.MyScreen;
import am.org.game.screen.StartScreen;

public class MainGame extends ApplicationAdapter implements InputProcessor {
	public static GameStatus GAME_STATUS = GameStatus.START;
	private SpriteBatch _batch;
	private  OrthographicCamera _camera;
	private MyScreen _startScreen, _answerScreen;
	public static int x;
	public static int y;
	
	public void create () {
		x = Gdx.graphics.getWidth()/2 - WIDTH/2;
		y = Gdx.graphics.getHeight()/2 - HEIGHT/2;
		//
		_batch = new SpriteBatch();
		_camera = new OrthographicCamera();
        _camera.setToOrtho(false, WIDTH, HEIGHT);
		Gdx.input.setInputProcessor(this);
		
		_startScreen = new StartScreen();
		_answerScreen = new AnswerScreen();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.212f, 0.208f, 0.2f, 2);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		_batch.begin();
		switch (GAME_STATUS) {
		case START:
			_startScreen.draw(_batch);
			break;
		case ANSWER:
			_answerScreen.draw(_batch);
			break;
			
		default:
			break;
		}
		_batch.end();
	}
	
	@Override
	public void dispose () {
		_batch.dispose();
		_startScreen.dispose();
		_answerScreen.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		if(!_startScreen.keyDown(keycode)){
			if(!_answerScreen.keyDown(keycode)){
				return false;
			}
		} else {
			_answerScreen.init();
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}
	@Override
	public boolean keyTyped(char character) {
		return false;
	}
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}
	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
