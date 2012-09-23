package org.oyasunadev.minecraftmania.client.minecraft.gui;

import com.mojang.minecraft.Minecraft;
import com.mojang.minecraft.gui.FontRenderer;
import com.mojang.minecraft.gui.Screen;

/**
 * Created with IntelliJ IDEA.
 * User: Oliver Yasuna
 * Date: 9/16/12
 * Time: 10:09 PM
 */
public class TextBox extends Screen
{
	public TextBox(int id, boolean visible, boolean enabled, int x, int y, int width, int height, String text, boolean focused)
	{
		this.id = id;
		this.visible = visible;
		this.enabled = enabled;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		this.focused = focused;
	}

	public int id;
	public boolean visible;
	public boolean enabled;

	public int x;
	public int y;

	public int width;
	public int height;

	public String text;

	public boolean focused;

	public void render(boolean mouseOver)
	{
		FontRenderer fontRenderer = Minecraft.minecraft.fontRenderer;

		ExtRenderer.drawBox(x - 1, y - 1, x + width + 1, y + height + 1, -6250336);
		ExtRenderer.drawBox(x, y, x + width, y + height, -16777216);

		if(!enabled)
		{
			//a(fontRenderer, text, x + width / 2, y + (height - 8) / 2, -6250336);
			ExtRenderer.drawText(text, x + width / 2, y + (height - 8) / 2, -6250336, true, true);
		} else if(mouseOver) {
			//a(fontRenderer, text, x + width / 2, y + (height - 8) / 2, 16777120);
			ExtRenderer.drawText(text, x + width / 2, y + (height - 8) / 2, 16777120, true, true);
		} else {
			//a(fontRenderer, text, x + width / 2, y + (height - 8) / 2, 14737632);
			ExtRenderer.drawText(text, x + width / 2, y + (height - 8) / 2, 14737632, true, true);
		}
	}
}
