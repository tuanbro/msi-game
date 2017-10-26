package am.org.game.resources;

public class InitDataResource {
    // ChupaChupGame
    public static final int ORIGINAL_WIDTH = 1024;
    public static final int ORIGINAL_HEIGHT = 764;
    public static int WIDTH = 1000;
    public static int HEIGHT = WIDTH * ORIGINAL_HEIGHT / ORIGINAL_WIDTH;

    // AnswerScreen
    public static final String CAP_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String NOR_CHARS = "a\\bcde\\fghijklm\\nopq\\rs\\tuvwxyz";
    public static final String NUM_CHAS = "1234567890";
    public static final String SPEC_CHARS = ",?.():-";
    public static final String UNICODE_CHARS = "â ấ ầ ẩ ậ ẫ"
                                                + "ê ế ề ệ ễ ể"
                                                + "ă ắ ằ ẳ ặ ẵ"
                                                + "é è ẻ ẹ ẽ"
                                                + "ú ù ụ ủ ũ"
                                                + "í ì ỉ ị ĩ"
                                                + "ó ò ỏ ọ õ"
                                                + "á à ả ạ ã"
                                                + "ố ồ ổ ộ ỗ ô"
                                                + "đ ý ỳ ỷ ỵ ỹ"
                                                + "ớ ờ ở ợ ỡ ơ"
                                                + "ứ ừ ử ự ữ ư";
public static final String CAP_UNICODE_CHARS = "Â Ấ Ầ Ẩ Ậ Ẫ"
                                                + "Ê Ế Ề Ệ Ễ Ể"
                                                + "Ă Ắ Ằ Ẳ Ặ Ẵ"
                                                + "É È Ẻ Ẹ Ẽ"
                                                + "Ú Ù Ụ Ủ Ũ"
                                                + "Í Ì Ỉ Ị Ĩ"
                                                + "Ó Ò Ỏ Ọ Õ"
                                                + "Á À Ả Ạ Ã"
                                                + "Ố Ồ Ổ Ộ Ỗ Ô"
                                                + "Đ Ý Ỳ Ỷ Ỵ Ỹ"
                                                + "Ớ Ờ Ở Ợ Ỡ Ơ"
                                                + "Ứ Ừ Ử Ự Ữ Ư";
    public static final int ANSWER_Y_PLUS = 100;
    public static final int ANSWER_X_PLUS = 12;
    public static final int POINT_Y_PLUS = 25;
    public static final int POINT_X_PLUS = 263;
    public static final String A_KEY = "a";
    public static final String Q_KEY = "q";
    public static final String COMMA_TOKEN = "|";
    public static final String CONF_QUESTION_KEY = "q.a";
    public static final int FONT_SIZE_MARK = 70;
    public static final int FONT_SIZE_QUESTION = 35;
    public static final int FONT_SIZE_ANSWER = 25;
    public static final int FONT_SIZE_POINT = 50;
    public static final String QUESTION_FILE = "question.dat";
    public static final String EMPTY_STRING = "";
    public static final String FONT_FILE = "ARIAL.TTF";
    public static final String DOT_TOKEN = ".";
}
