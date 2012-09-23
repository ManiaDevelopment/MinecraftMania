package com.mojang.minecraft.gui;

import com.mojang.minecraft.GameSettings;
import com.mojang.minecraft.render.ShapeRender;
import com.mojang.minecraft.render.TextureManager;
import org.lwjgl.opengl.GL11;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Oliver Yasuna
 * Date: 9/8/12
 * Time: 11:28 PM
 */
public class FontRenderer
{
	public FontRenderer(GameSettings gameSettings1, String image, TextureManager textureManager)
	{
		this.gameSettings = gameSettings1;

		BufferedImage bi = null;

		try {
			bi = ImageIO.read(TextureManager.class.getResourceAsStream(image));
		} catch (IOException e) {
			e.printStackTrace();
		}

		imageSize = new Dimension(bi.getWidth(), bi.getHeight());
		int[] fontData = new int[imageSize.width * imageSize.height];
		charSize = new Dimension(imageSize.width / 16, imageSize.height / 16);

		bi.getRGB(0, 0, imageSize.width, imageSize.height, fontData, 0, imageSize.width);

		for(int character = 0; character < 128; character++)
		{
			int var0 = character % 16;
			int var1 = character / 16;
			int var2 = 0;

			boolean ready = false;

			for(; var2 < charSize.width && !ready; var2++)
			{
				int var3 = (var0 << 3) + var2;

				ready = true;

				for(int var4 = 0; var4 < charSize.height && ready; var4++)
				{
					int var5 = ((var1 << 3) + var4) * imageSize.width;

					if((fontData[var3 + var5] & 255) > 128)
					{
						ready = false;
					}
				}
			}

			if(character == 32)
			{
				var2 = 4;
			}

			widths[character] = var2;
		}

		fontTextureID = textureManager.a(image);

		/*this.gameSettings = gameSettings1;

		BufferedImage bufferedImage;

		try {
			bufferedImage = ImageIO.read(TextureManager.class.getResourceAsStream(image));
		} catch (IOException var13) {
			throw new RuntimeException(var13);
		}

		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();

		int[] fontData = new int[width * height];

		bufferedImage.getRGB(0, 0, width, height, fontData, 0, width);

		for(int character = 0; character < 128; ++character)
		{
			height = character % 16;

			int var7 = character / 16;
			int chWidth = 0;

			for(boolean i = false; chWidth < 8 && !i; ++chWidth)
			{
				int var10 = (height << 3) + chWidth;

				i = true;

				for(int i1 = 0; i1 < 8 && i; ++i1)
				{
					int var12 = ((var7 << 3) + i1) * width;

					if((fontData[var10 + var12] & 255) > 128)
					{
						i = false;
					}
				}
			}

			if(character == 32)
			{
				chWidth = 4;
			}

			widths[character] = chWidth;
		}

		fontTextureID = textureManager.a(image);*/
	}

	private int[] widths = new int[256];
	private int fontTextureID = 0;
	private GameSettings gameSettings;

	public Dimension imageSize;
	public Dimension charSize;

	public void a(String text, int x, int y, int color) // render with shadow.
	{
		a(text, x + 1, y + 1, color, true);
		b(text, x, y, color);
	}

	public void b(String text, int x, int y, int color) // render no shadow.
	{
		a(text, x, y, color, false);
	}

	private void a(String text, int x, int y, int color, boolean shadow) // render.
	{
		if(text != null)
		{
			char[] chars = text.toCharArray();

			if(shadow)
			{
				color = (color & 16579836) >> 2;
			}

			GL11.glBindTexture(3553, fontTextureID);

			ShapeRender var6 = ShapeRender.a;
			ShapeRender.a.b();

			var6.a(color);

			int var7 = 0;

			for(int count = 0; count < chars.length; ++count)
			{
				int var9;

				if(chars[count] == 38 && chars.length > count + 1)
				{
					if((color = "0123456789abcdef".indexOf(chars[count + 1])) < 0)
					{
						color = 15;
					}

					var9 = (color & 8) << 3;

					int var10 = (color & 1) * 191 + var9;
					int var11 = ((color & 2) >> 1) * 191 + var9;

					color = ((color & 4) >> 2) * 191 + var9;

					if(gameSettings.g)
					{
						var9 = (color * 30 + var11 * 59 + var10 * 11) / 100;
						var11 = (color * 30 + var11 * 70) / 100;
						var10 = (color * 30 + var10 * 70) / 100;

						color = var9;
					}

					color = color << 16 | var11 << 8 | var10;

					count += 2;

					if(shadow)
					{
						color = (color & 16579836) >> 2;
					}

					var6.a(color);
				}

				color = chars[count] % 16 << 3;
				var9 = chars[count] / 16 << 3;

				float var13 = 7.99F;

				var6.a((float)(x + var7), (float)y + var13, 0.0F, (float)color / 128.0F, ((float)var9 + var13) / 128.0F);
				var6.a((float)(x + var7) + var13, (float)y + var13, 0.0F, ((float)color + var13) / 128.0F, ((float)var9 + var13) / 128.0F);
				var6.a((float)(x + var7) + var13, (float)y, 0.0F, ((float)color + var13) / 128.0F, (float)var9 / 128.0F);
				var6.a((float)(x + var7), (float)y, 0.0F, (float)color / 128.0F, (float)var9 / 128.0F);

				var7 += widths[chars[count]];
			}

			var6.a();
		}
	}

	public int a(String string) // get width.
	{
		if(string == null)
		{
			return 0;
		} else {
			char[] chars = string.toCharArray();
			int width = 0;

			for(int i = 0; i < chars.length; ++i)
			{
				if(chars[i] == 38)
				{
					++i;
				} else {
					width += widths[chars[i]];
				}
			}

			return width;
		}
	}

	public static String b(String string) // remove bad character.
	{
		char[] chars = string.toCharArray();
		String result = "";

		for(int i = 0; i < chars.length; ++i)
		{
			if(chars[i] == 38)
			{
				++i;
			} else {
				result = result + chars[i];
			}
		}

		return result;
	}
}
