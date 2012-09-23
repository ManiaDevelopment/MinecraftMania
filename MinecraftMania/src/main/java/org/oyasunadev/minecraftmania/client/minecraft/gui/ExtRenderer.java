package org.oyasunadev.minecraftmania.client.minecraft.gui;

import com.mojang.minecraft.Minecraft;
import com.mojang.minecraft.gui.FontRenderer;
import com.mojang.minecraft.render.ShapeRender;
import com.mojang.minecraft.render.TextureManager;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

/**
 * Created with IntelliJ IDEA.
 * User: Oliver Yasuna
 * Date: 9/16/12
 * Time: 11:10 PM
 */
public class ExtRenderer
{
	public static void drawBox(int x1, int y1, int x2, int y2, int color)
	{
		float red = (color >> 16 & 255) / 255F;
		float green = (color >> 8 & 255) / 255F;
		float blue = (color & 255) / 255F;
		float alpha = (color >>> 24) / 255F;

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glColor4f(red, green, blue, alpha);

		ShapeRender.a.b();
		ShapeRender.a.b(x1, y2, 0);
		ShapeRender.a.b(x2, y2, 0);
		ShapeRender.a.b(x2, y1, 0);
		ShapeRender.a.b(x1, y1, 0);
		ShapeRender.a.a();

		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_BLEND);
	}

	// TODO: Test.
	public static void drawText(String text, int x, int y, int color, boolean shadow, boolean centered)
	{
		FontRenderer renderer = Minecraft.minecraft.fontRenderer;

		int x1 = x;

		if(centered)
		{
			x1 -= renderer.a(text) / 2;
		}

		if(shadow)
		{
			renderer.a(text, x1, y, color);
		} else {
			renderer.b(text, x1, y, color);
		}
	}

	// TODO: Test.
	public void drawDirtBG()
	{
		TextureManager textureManager = Minecraft.minecraft.textureManager;

		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureManager.a("/dirt.png"));

		int width = Display.getWidth() * 240 / Display.getHeight();
		int height = Display.getHeight() * 240 / Display.getHeight();

		ShapeRender.a.b();
		ShapeRender.a.a(4210752);
		ShapeRender.a.a(0, height, 0, 0, height / 32);
		ShapeRender.a.a(width, height, 0, width / 32, height / 32);
		ShapeRender.a.a(width, 0, 0, width / 32, 0);
		ShapeRender.a.a(0, 0, 0, 0, 0);
		ShapeRender.a.a();
	}
}
