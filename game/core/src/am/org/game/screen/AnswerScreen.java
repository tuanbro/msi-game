package am.org.game.screen;

import static am.org.game.resources.InitDataResource.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Rectangle;

import am.org.game.Answer;
import am.org.game.MainGame;
import am.org.game.GameStatus;
import am.org.game.Question;
import am.org.game.actor.MyActor;
import am.org.game.util.GameUtil;

/*
 * 		public static final int NUM_1 = 8;
		public static final int NUM_2 = 9;
		public static final int NUM_3 = 10;
		public static final int NUM_4 = 11;
		public static final int NUM_5 = 12;
		public static final int NUM_6 = 13;
 */
public class AnswerScreen extends MyScreen {
    public static int QUESTION = -1;

    private int _x;
    private int _point;
    private Properties _prop;
    private List<MyActor> _xs;
    private List<Texture> _blank;
    private Sound _sCorrect, _sFail;
    public static List<Question> _questions = new ArrayList<Question>();
    private Question _currentQuestion;
    private Texture _texQuestion, _texPoint, _texAnswer;
    private BitmapFont _fontMark, _fontQuestion, _fontAnswer, _fontPoint;

    public AnswerScreen() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(FONT_FILE));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.characters = CAP_CHARS + NOR_CHARS + NUM_CHAS + UNICODE_CHARS + CAP_UNICODE_CHARS + SPEC_CHARS;
        Color textColor = new Color(1, 1, 1, 1);
        // Font Mark
        parameter.size = FONT_SIZE_MARK;
        _fontMark = generator.generateFont(parameter);
        _fontMark.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
        // Font Question
        parameter.size = FONT_SIZE_QUESTION;
        _fontQuestion = generator.generateFont(parameter);
        _fontQuestion.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
        _fontQuestion.setColor(textColor);
        // Font Answer
        parameter.size = FONT_SIZE_ANSWER;
        _fontAnswer = generator.generateFont(parameter);
        _fontAnswer.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
        _fontAnswer.setColor(textColor);
        // Font Point
        parameter.size = FONT_SIZE_POINT;
        _fontPoint = generator.generateFont(parameter);
        _fontPoint.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
        _fontPoint.setColor(textColor);
        // Load IMG
        _texPoint = new Texture("m.png");
        _texQuestion = new Texture("q.png");
        _texAnswer = new Texture("a.png");

        _questions = new ArrayList<Question>();
        FileHandle handle;
        boolean exists;
        int[] totalQuestion;
        StringTokenizer answerPerQuestion;
        String properties = EMPTY_STRING;

        // Load configure
        handle = Gdx.files.local(QUESTION_FILE);
        exists = handle.exists();
        if (exists) {
            _prop = new Properties();
            try {
                _prop.load(new InputStreamReader(handle.read(), "UTF-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        properties = _prop.getProperty(CONF_QUESTION_KEY);
        answerPerQuestion = new StringTokenizer(properties, COMMA_TOKEN);
        totalQuestion = new int[answerPerQuestion.countTokens()];
        for (int i = 0; answerPerQuestion.hasMoreTokens(); i++) {
            totalQuestion[i] = Integer.valueOf(answerPerQuestion.nextToken());
        }

        Question question;
        List<Answer> answers;
        String qID;
        String aID;
        StringTokenizer answerProperties;
        for (int i = 1; i <= totalQuestion.length; i++) {
            qID = Q_KEY + i;
            properties = _prop.getProperty(qID);
            answers = new ArrayList<Answer>();
            question = new Question(_texQuestion, new Rectangle(MainGame.x, MainGame.y, WIDTH, HEIGHT),
                    new Rectangle(120 + MainGame.x, 725 + MainGame.y, 0, 0), true, _fontQuestion, i, properties,
                    answers);
            int totalAnswer = totalQuestion[i - 1];
            for (int j = 1; j <= totalAnswer; j++) {
                aID = A_KEY + j;
                answerProperties = new StringTokenizer(_prop.getProperty(qID + DOT_TOKEN + aID), COMMA_TOKEN);
                int[] position = GameUtil.getDefaultAnswerPosition(j);
                int x = position[0];
                int y = position[1];
                answers.add(new Answer(_texAnswer,
                        new Rectangle(x + MainGame.x - ANSWER_X_PLUS, y + MainGame.y - ANSWER_Y_PLUS,
                                _texAnswer.getWidth(), _texAnswer.getHeight()),
                        new Rectangle(x + MainGame.x, y + MainGame.y, 0, 0), _fontAnswer, j,
                        answerProperties.nextToken(),
                        new MyActor(new Rectangle(x + MainGame.x + POINT_X_PLUS, y + MainGame.y - POINT_Y_PLUS, 0, 0),
                                _fontPoint, answerProperties.nextToken())));
            }
            _questions.add(question);
        }

        // Load sound
        _sCorrect = Gdx.audio.newSound(Gdx.files.internal("ting.wav"));
        _sFail = Gdx.audio.newSound(Gdx.files.internal("fail.mp3"));
    }

    @Override
    public void init() {
        _xs = new ArrayList<MyActor>();
        _point = 0;
        _x = 0;
        _currentQuestion = _questions.get(QUESTION);
        _blank = new ArrayList<Texture>();
        for (int i = 8; i > _currentQuestion.getAnswers().size(); i--) {
            _blank.add(new Texture("b" + i + ".png"));
        }
        _currentQuestion.reset();
    }

    @Override
    public void draw(SpriteBatch batch) {
        _currentQuestion.draw(batch);
        for (Texture t : _blank) {
            batch.draw(t, MainGame.x, MainGame.y, WIDTH, HEIGHT);
        }
        for (MyActor x : _xs) {
            x.draw(batch);
        }
        drawPoint(batch);
    }

    private void drawPoint(SpriteBatch batch) {
        batch.draw(_texPoint, MainGame.x, MainGame.y, WIDTH, HEIGHT);
        if (_point < 10) {
            _fontMark.draw(batch, String.valueOf(_point), 485 + MainGame.x, 530 + MainGame.y);
        } else if (_point < 100) {
            _fontMark.draw(batch, String.valueOf(_point), 465 + MainGame.x, 530 + MainGame.y);
        } else {
            _fontMark.draw(batch, String.valueOf(_point), 440 + MainGame.x, 530 + MainGame.y);
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        if (MainGame.GAME_STATUS == GameStatus.ANSWER) {
            int currentAnswerSize = _currentQuestion.getAnswers().size();
            if (Keys.NUM_1 <= keycode && keycode < (Keys.NUM_1 + currentAnswerSize)) {
                Answer answer = _currentQuestion.getAnswer(keycode - Keys.NUM_1);
                if (!answer._visible) {
                    answer.setVisible(true);
                    _sCorrect.play();
                    _point += Integer.valueOf(answer._mark._text);
                    return true;
                }
                return false;
            } else if (keycode == Keys.F2) {
                MainGame.GAME_STATUS = GameStatus.START;
            } else if (keycode == Keys.SPACE) {
                if (_x < 3) {
                    _x++;
                    Texture xTex = new Texture("x" + _x + ".png");
                    _xs.add(new MyActor(xTex, new Rectangle(MainGame.x, MainGame.y, WIDTH, HEIGHT), false));
                } else if (_x == 3) {
                    _xs = Arrays.asList(new MyActor(new Texture("x.png"), 
                                        new Rectangle(MainGame.x, MainGame.y, WIDTH, HEIGHT),
                                        false));
                }
                _sFail.play();
            }
        }
        return false;
    }

    @Override
    public void dispose() {
        _texQuestion.dispose();
    }

}
