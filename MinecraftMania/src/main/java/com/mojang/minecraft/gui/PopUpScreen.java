package com.mojang.minecraft.gui;

import com.mojang.minecraft.Minecraft;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

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


	public void a(int var1, int var2) {
		for(int var3 = 0; var3 < this.d.size(); ++var3) {
			Button var10000 = (Button)this.d.get(var3);
			Minecraft var7 = this.a;
			Button var4 = var10000;
			if(var10000.h) {
				FontRenderer var8 = var7.fontRenderer;
				GL11.glBindTexture(3553, var7.textureManager.a("/gui/gui.png"));
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				byte var9 = 1;
				boolean var6 = var1 >= var4.c && var2 >= var4.d && var1 < var4.c + var4.a && var2 < var4.d + var4.b;
				if(!var4.g) {
					var9 = 0;
				} else if(var6) {
					var9 = 2;
				}

				var4.b(var4.c, var4.d, 0, 46 + var9 * 20, var4.a / 2, var4.b);
				var4.b(var4.c + var4.a / 2, var4.d, 200 - var4.a / 2, 46 + var9 * 20, var4.a / 2, var4.b);
				if(!var4.g) {
					Button.a(var8, var4.e, var4.c + var4.a / 2, var4.d + (var4.b - 8) / 2, -6250336);
				} else if(var6) {
					Button.a(var8, var4.e, var4.c + var4.a / 2, var4.d + (var4.b - 8) / 2, 16777120);
				} else {
					Button.a(var8, var4.e, var4.c + var4.a / 2, var4.d + (var4.b - 8) / 2, 14737632);
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

	protected void a(int var1, int var2, int var3) {
		if(var3 == 0) {
			for(var3 = 0; var3 < this.d.size(); ++var3) {
				Button var4;
				Button var7;
				if((var7 = var4 = (Button)this.d.get(var3)).g && var1 >= var7.c && var2 >= var7.d && var1 < var7.c + var7.a && var2 < var7.d + var7.b) {
					this.a(var4);
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
