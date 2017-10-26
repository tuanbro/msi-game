package am.org.game.actor;

import static am.org.game.resources.InitDataResource.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class MyActor {
	public Texture _background;
	public BitmapFont _font;
	public Rectangle _boundBG, _boundText;
	public String _text;
	public boolean _twinkle, _visible;

	private float _time;
	private float _step;
	private boolean _isAnimate;
	public MyActor(Texture img, Rectangle boundBG, Rectangle boundText,  boolean twinkle, BitmapFont font, String text, boolean visible) {
		_background = img;
		_boundBG = boundBG;
		_boundText = boundText;
		_twinkle = twinkle;
		_font = font;
		_text = text;
		_visible = visible;
		_time = 0f;
		_step = 0f;
		_isAnimate = false;
	}
	
	public MyActor(Texture img, Rectangle boundBG, Rectangle boundText, BitmapFont font, String text) {
		this(img, boundBG, boundText, true, font, text, true);
	}

	public MyActor(Rectangle boundText, BitmapFont font, String text) {
		this(null, null, boundText, true, font, text, true);
	}

	public MyActor(Texture img, Rectangle boundBG, boolean twinkle) {
		this(img, boundBG, null, twinkle, null, null, true);
	}

	public void draw(SpriteBatch batch){
		if(_visible){
			if(_boundBG != null){
				float x = _boundBG.getX();
				float y = _boundBG.getY();
				batch.draw(_background, _isAnimate ? WIDTH*2 : x, y, _boundBG.getWidth(), _boundBG.getHeight());
			}
			if(_boundText != null){
				_font.draw(batch, String.valueOf(_text), _isAnimate ? WIDTH*2 : _boundText.getX(), _boundText.getY());
			}
			
			if(!_twinkle){
				_time += Gdx.graphics.getDeltaTime();
				if(1 >= _time){
					_step += Gdx.graphics.getDeltaTime();
					if(_step >= 0.1){
						_isAnimate = !_isAnimate;
						_step = 0f;
					}
				} else {
					_twinkle = true;
					_time = 0f;
					_isAnimate = false;
				}
			}
			
		}
	}
	
	public void reset(){
		_visible = true;
	    _twinkle = true;
	}
}
