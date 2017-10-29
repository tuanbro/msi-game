package am.org.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import am.org.game.MainGame;
import am.org.game.resources.InitDataResource;

public class DesktopLauncher {
    public static void main(String[] arg) {
        MainGame listener = new MainGame();
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = InitDataResource.WIDTH;
        config.height = InitDataResource.HEIGHT;
        config.fullscreen = false;
        config.vSyncEnabled = true;
        config.resizable = false;
        new LwjglApplication(listener, config);
    }
}