package com.mojang.minecraft.gui;

import org.oyasunadev.minecraftmania.client.minecraft.gui.NewLvlScreen;

/**
 * Created with IntelliJ IDEA.
 * User: Oliver Yasuna
 * Date: 9/16/12
 * Time: 9:39 PM
 */
public final class PauseScreen extends PopUpScreen
{
	@Override
	public void a()
	{
		d.clear();

		d.add(new Button(0, b / 2 - 100, c / 4, "Options..."));
		String generateName = a.sessionData != null ? "Execute \"newlvl\"..." : "Generate new level...";
		d.add(new Button(1, b / 2 - 100, c / 4 + 24, generateName));
		String saveName = a.sessionData != null ? "Dump server level..." : "Save level...";
		d.add(new Button(2, b / 2 - 100, c / 4 + 48, saveName));
		String loadName = a.sessionData != null ? "Execute \"g (goto)\"..." : "Load level...";
		d.add(new Button(3, b / 2 - 100, c / 4 + 72, loadName));
		//d.add(new Button(4, b / 2 - 100, c / 4 + 96, "Texture Packs..."));
		d.add(new Button(5, b / 2 - 100, c / 4 + 120, "Back to game"));

		/*if(a.sessionData == null)
		{
			((Button)d.get(2)).g = false;
			((Button)d.get(3)).g = false;
		}

		if(a.networkManager != null)
		{
			((Button)d.get(1)).g = false;
			((Button)d.get(2)).g = false;
			((Button)d.get(3)).g = false;
		}*/
	}

	@Override
	protected void a(Button button)
	{
		if(button.f == 0)
		{
			a.a(new OptionsScreen(this, a.settings));
		}

		if(button.f == 1)
		{
			if(button.e.equals("Generate new level..."))
			{
				a.a(new GenerateLevelScreen(this));
			} else if(button.e.equals("Execute \"newlvl\"...")) {
				a.a(new NewLvlScreen(this));
			}
		}

		if(button.f == 2)
		{
			if(button.e.equals("Save level..."))
			{
				a.a(new SaveLevelScreen(this));
			} else if(button.e.equals("Dump server level...")) {
				System.out.println("dump level");
			}
		}

		if(button.f == 3)
		{
			if(button.e.equals("Load level..."))
			{
				a.a(new LoadLevelScreen(this));
			} else if(button.e.equals("Execute \"g (goto)\"...")) {
				System.out.println("goto level");
			}
		}

		/*if(button.f == 4) // Texture Packs
		{
			System.out.println();
		}*/

		if(button.f == 5)
		{
			a.a((PopUpScreen)null);
			a.b();
		}
	}

	@Override
	public void a(int x, int y)
	{
		a(0, 0, b, c, 1610941696, -1607454624);
		a(f, "Game menu", b / 2, 40, 16777215);

		super.a(x, y);
	}
}
