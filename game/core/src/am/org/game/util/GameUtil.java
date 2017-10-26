package am.org.game.util;

import java.util.HashMap;
import java.util.Map;

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
}
