package am.org.game.screen;

import static am.org.game.resources.InitDataResource.HEIGHT;
import static am.org.game.resources.InitDataResource.WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import am.org.game.GameStatus;
import am.org.game.MainGame;

public class GuideScreen extends MyScreen{
    private Texture _img;
    private Sound _sQuestion;
    
    public GuideScreen() {
        _img = new Texture("g.png");
        _sQuestion = Gdx.audio.newSound(Gdx.files.internal("question.wav"));
    }
    
    @Override
    public void init() {
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(_img, MainGame.x, MainGame.y, WIDTH, HEIGHT);
    }
    
    @Override
    public boolean keyDown(int keycode) {
        if (MainGame.GAME_STATUS == GameStatus.GUIDE) {
            if (Keys.NUM_1 <= keycode && keycode < (Keys.NUM_1 + AnswerScreen._questions.size())) {
                _sQuestion.play();
                MainGame.GAME_STATUS = GameStatus.ANSWER;
                AnswerScreen.QUESTION = keycode - Keys.NUM_1;
                return true;
            } else if (keycode == Keys.F2) {
                MainGame.GAME_STATUS = GameStatus.START;
            }
        }
        return false;
    }

    @Override
    public void dispose() {
        _img.dispose();
        _sQuestion.dispose();
    }

}
