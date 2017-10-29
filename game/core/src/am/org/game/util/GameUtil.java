package am.org.game.util;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Input.Keys;

import am.org.game.screen.AnswerScreen;

public class GameUtil {
	static Map<Integer, int[]> ANSWER_DEFAULT_POSITION = new HashMap<Integer, int[]>();
	static {
		ANSWER_DEFAULT_POSITION.put(1, new int[] {55, 550});
		ANSWER_DEFAULT_POSITION.put(2, new int[] {55, 410});
		ANSWER_DEFAULT_POSITION.put(3, new int[] {55, 260});
		ANSWER_DEFAULT_POSITION.put(4, new int[] {55, 110});
		ANSWER_DEFAULT_POSITION.put(5, new int[] {610, 550});
		ANSWER_DEFAULT_POSITION.put(6, new int[] {610, 410});
		ANSWER_DEFAULT_POSITION.put(7, new int[] {610, 260});
		ANSWER_DEFAULT_POSITION.put(8, new int[] {610, 110});
	}

	public static int[] getDefaultAnswerPosition(int ID){
		return ANSWER_DEFAULT_POSITION.get(ID);
	}

    public static boolean isQuestion(int keycode) {
        int size = AnswerScreen._questions.size();
        int keyStep = -1;
        if(Keys.NUM_1 <= keycode && keycode < (Keys.NUM_1 + size)) {
            keyStep = Keys.NUM_1;
        } else if(size > 9 && (Keys.NUMPAD_1 <= keycode && keycode < (Keys.NUMPAD_1 + size - 9))) {
            keyStep = Keys.NUMPAD_1 - 9;
        } else {
            return false;
        }
        AnswerScreen.QUESTION = keycode - keyStep;
        return true;
    }
}
