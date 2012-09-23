package org.oyasunadev.minecraftmania.client.minecraft.gui;

import com.mojang.minecraft.gui.Button;
import com.mojang.minecraft.gui.PopUpScreen;

/**
 * Created with IntelliJ IDEA.
 * User: Oliver Yasuna
 * Date: 9/16/12
 * Time: 10:06 PM
 */
public final class NewLvlScreen extends PopUpScreen
{
	public NewLvlScreen(PopUpScreen parent)
	{
		this.parent = parent;
	}

	private PopUpScreen parent;

	@Override
	public void a()
	{
		d.clear();

		// TODO: finish

		d.add(new TextBox(0, true, true, b / 2 - 100, c / 4, 200, 20, "null", true));
		d.add(new Button(1, b / 2 - 100, c / 4 + 24, "Small (64x64x64)"));
		d.add(new Button(2, b / 2 - 100, c / 4 + 48, "Normal (128x128x128)"));
		d.add(new Button(3, b / 2 - 100, c / 4 + 72, "Huge (254x256x256)"));
		d.add(new Button(4, b / 2 - 100, c / 4 + 120, "Cancel"));
	}

	@Override
	protected void a(Button button)
	{
		if(button.f == 4)
		{
			a.a(parent);
		} else {
			a.a(button.f);
			a.a((PopUpScreen)null);
			a.b();
		}
	}

	@Override
	public void a(int x, int y)
	{
		a(0, 0, b, c, 1610941696, -1607454624);
		a(f, "Execute \"newlvl\"", b / 2, 40, 16777215);

		super.a(x, y);
	}
}
