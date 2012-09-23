package org.oyasunadev.minecraftmania.client.util;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 * Created with IntelliJ IDEA.
 * User: Oliver Yasuna
 * Date: 9/15/12
 * Time: 5:51 PM
 */
public class LWJGLUtil
{
	public static void setDisplayMode(int width, int height, boolean fullscreen) throws Exception
	{
		DisplayMode[] dm = org.lwjgl.util.Display.getAvailableDisplayModes(width, height, -1, -1, -1, -1, 60, 60);

		org.lwjgl.util.Display.setDisplayMode(dm, new String[] {
				"width=" + width,
				"height=" + height,
				"freq=" + 60,
				"bpp=" + org.lwjgl.opengl.Display.getDisplayMode().getBitsPerPixel()
		});

		Display.setFullscreen(fullscreen);
	}
}
