package com.mojang.minecraft.gui;

import com.mojang.minecraft.Minecraft;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.oyasunadev.minecraftmania.client.minecraft.gui.ExtRenderer;
import org.oyasunadev.minecraftmania.client.minecraft.gui.TextBox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Oliver Yasuna
 * Date: 9/15/12
 * Time: 5:35 PM
 */
public class PopUpScreen extends Screen {

	protected Minecraft a;
	public int b;
	public int c;
	protected List d = new ArrayList();
	public boolean e = false;
	protected FontRenderer f;


	public void a(int x, int y)
	{
		for(int index = 0; index < this.d.size(); index++)
		{
			Screen screen = (Screen)d.get(index);

			if(screen instanceof Button)
			{
				Button button = (Button)screen;

				if(button.h)
				{
					FontRenderer fontRenderer = a.fontRenderer;

					GL11.glBindTexture(3553, a.textureManager.a("/gui/gui.png"));
					GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

					byte state = 1;
					boolean mouseOver = x >= button.c && y >= button.d && x < button.c + button.a && y < button.d + button.b;

					if(!button.g)
					{
						state = 0;
					} else if(mouseOver) {
						state = 2;
					}

					button.b(button.c, button.d, 0, 46 + state * 20, button.a / 2, button.b);
					button.b(button.c + button.a / 2, button.d, 200 - button.a / 2, 46 + state * 20, button.a / 2, button.b);

					if(!button.g)
					{
						Button.a(fontRenderer, button.e, button.c + button.a / 2, button.d + (button.b - 8) / 2, -6250336);
					} else if(mouseOver) {
						Button.a(fontRenderer, button.e, button.c + button.a / 2, button.d + (button.b - 8) / 2, 16777120);
					} else {
						Button.a(fontRenderer, button.e, button.c + button.a / 2, button.d + (button.b - 8) / 2, 14737632);
					}
				}
			} else if(screen instanceof TextBox) {
				TextBox textBox = (TextBox)screen;

				if(textBox.visible)
				{
					FontRenderer fontRenderer = a.fontRenderer;

					boolean mouseOver = x >= textBox.x && y >= textBox.y && x < textBox.x + textBox.width && y < textBox.y + textBox.height;

					textBox.render(mouseOver);

					/*ExtRenderer.drawBox(textBox.x - 1, textBox.y - 1, textBox.x + textBox.width + 1, textBox.y + textBox.height + 1, -6250336);
					ExtRenderer.drawBox(textBox.x, textBox.y, textBox.x + textBox.width, textBox.y + textBox.height, -16777216);

					if(!textBox.enabled)
					{
						textBox.a(fontRenderer, textBox.text, textBox.x + textBox.width / 2, textBox.y + (textBox.height - 8) / 2, -6250336);
					} else if(mouseOver) {
						textBox.a(fontRenderer, textBox.text, textBox.x + textBox.width / 2, textBox.y + (textBox.height - 8) / 2, 16777120);
					} else {
						textBox.a(fontRenderer, textBox.text, textBox.x + textBox.width / 2, textBox.y + (textBox.height - 8) / 2, 14737632);
					}*/
				}
			}
		}
	}

	protected void a(char var1, int var2) {
		if(var2 == 1) {
			this.a.a((PopUpScreen)null);
			this.a.b();
		}

	}

	protected void a(int var1, int var2, int var3)
	{
		if(var3 == 0)
		{
			for(var3 = 0; var3 < d.size(); var3++)
			{
				Screen screen = (Screen)d.get(var3);

				if(screen instanceof Button)
				{
					Button button = (Button)screen;

					if(button.g && var1 >= button.c && var2 >= button.d && var1 < button.c + button.a && var2 < button.d + button.b)
					{
						a(button);
					}
				} else if(screen instanceof TextBox) {
				}
			}
		}

	}

	protected void a(Button var1) {}

	public final void a(Minecraft var1, int var2, int var3) {
		this.a = var1;
		this.f = var1.fontRenderer;
		this.b = var2;
		this.c = var3;
		this.a();
	}

	public void a() {}

	public final void d() {
		while(Mouse.next()) {
			this.e();
		}

		while(Keyboard.next()) {
			this.f();
		}

	}

	public final void e() {
		if(Mouse.getEventButtonState()) {
			int var1 = Mouse.getEventX() * this.b / this.a.width;
			int var2 = this.c - Mouse.getEventY() * this.c / this.a.height - 1;
			this.a(var1, var2, Mouse.getEventButton());
		}

	}

	public final void f() {
		if(Keyboard.getEventKeyState()) {
			this.a(Keyboard.getEventCharacter(), Keyboard.getEventKey());
		}

	}

	public void c() {}

	public void b() {}
}
