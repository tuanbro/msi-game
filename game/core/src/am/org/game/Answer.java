package am.org.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import am.org.game.actor.MyActor;

public class Answer extends MyActor{
	public int ID;
	public MyActor _mark;
	
	public Answer(Texture img, Rectangle boundBG, Rectangle boundText, BitmapFont font,int iD, String answer, MyActor mark) {
		super(img, boundBG, boundText, false, font, answer, false);
		ID = iD;
		_mark = mark;
		_mark._visible = _visible;
		_mark._twinkle = _twinkle;
	}

	public void draw(SpriteBatch batch) {
		super.draw(batch);
		_mark.draw(batch);
	}
	
	public void setVisible(boolean b){
		_visible = b;
		_mark._visible = b;
	}
	
	@Override
	public void reset() {
		_visible = false;
		_twinkle = false;
		_mark._visible = false;
		_mark._twinkle = false;
	}
}
