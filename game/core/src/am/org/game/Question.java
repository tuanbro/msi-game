package am.org.game;

import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import am.org.game.actor.MyActor;

public class Question extends MyActor{
	private int ID;
	private List<Answer> _answers;
	
	public Question(Texture img, Rectangle boundBG, Rectangle boundText, boolean twinkle, BitmapFont font, int iD, String question, List<Answer> answers) {
		super(img, boundBG, boundText, font, question);
		ID = iD;
		_answers = answers;
	}
	
	@Override
	public void draw(SpriteBatch batch) {
		super.draw(batch);
		for(Answer a : _answers){
			a.draw(batch);
		}
	}
	
	public void addAnswer(Answer a){
		_answers.add(a);
	}
	
	public Answer getAnswer(int index) {
		return _answers.get(index);
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public List<Answer> getAnswers() {
		return _answers;
	}

	public void setAnswers(List<Answer> answers) {
		_answers = answers;
	}

	@Override
	public void reset() {
		super.reset();
		for(Answer a : _answers){
			a.reset();
		}
	}
	
	
	
}
