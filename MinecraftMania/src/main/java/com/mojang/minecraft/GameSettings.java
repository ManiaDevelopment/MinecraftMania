package com.mojang.minecraft;

import com.mojang.minecraft.render.TextureManager;
import org.lwjgl.input.Keyboard;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Oliver Yasuna
 * Date: 9/16/12
 * Time: 6:35 PM
 */
public final class GameSettings
{
	public GameSettings(Minecraft minecraft, File file)
	{
		s = new KeyBinding[]{i, j, k, l, m, n, o, p, q, r};
		v = minecraft;
		w = new File(file, "options.txt");
		t = 10;

		a();

		File texturePacksFolder = new File(file, "texturepacks");
		FilenameFilter filter = new FilenameFilter()
		{
			@Override
			public boolean accept(File dir, String name)
			{
				return name.endsWith(".zip");
			}
		};
		String[] files = texturePacksFolder.list(filter);

		texturePacks.add("none");

		for(String s1 : files)
		{
			texturePacks.add(s1);
		}
	}

	private static final String[] u = new String[]{"FAR", "NORMAL", "SHORT", "TINY"}; // fog
	public boolean a = true; // music
	public boolean b = true; // sound
	public boolean c = false; // invert mouse
	public boolean d = false; // show info
	public int e = 0; // view distance
	public boolean f = true; // view bobbing
	public boolean g = false; // anaglyph
	public boolean h = false; // limit fps
	public KeyBinding i = new KeyBinding("Forward", 17);
	public KeyBinding j = new KeyBinding("Left", 30);
	public KeyBinding k = new KeyBinding("Back", 31);
	public KeyBinding l = new KeyBinding("Right", 32);
	public KeyBinding m = new KeyBinding("Jump", 57);
	public KeyBinding n = new KeyBinding("Build", 48);
	public KeyBinding o = new KeyBinding("Chat", 20);
	public KeyBinding p = new KeyBinding("Toggle fog", 33);
	public KeyBinding q = new KeyBinding("Save location", 28);
	public KeyBinding r = new KeyBinding("Load location", 19);
	public KeyBinding[] s; // bindings
	public Minecraft v; // minecraft
	private File w; // file
	public int t; // count

	public boolean smoothing = false;
	public String texturePack = "none";
	public int currentTexturePack = 0;
	public List<String> texturePacks = new ArrayList<String>();

	public String a(int key) // get binding
	{
		return s[key].name + ": " + Keyboard.getKeyName(s[key].key);
	}

	public void a(int key, int keyID) // set binding
	{
		s[key].key = keyID;

		b();
	}

	public void b(int setting, int fogValue) // toggle setting
	{
		if(setting == 0)
		{
			a = !a;
		}

		if(setting == 1)
		{
			b = !b;
		}

		if(setting == 2)
		{
			c = !c;
		}

		if(setting == 3)
		{
			d = !d;
		}

		if(setting == 4)
		{
			e = e + fogValue & 3;
		}

		if(setting == 5)
		{
			f = !f;
		}

		if(setting == 6)
		{
			g = !g;

			TextureManager textureManager = v.textureManager;
			Iterator texManager = v.textureManager.b.keySet().iterator();

			int textureID;
			BufferedImage image;
			while(texManager.hasNext())
			{
				textureID = ((Integer)texManager.next()).intValue();
				image = textureManager.b.get(Integer.valueOf(textureID));
				textureManager.a(image, textureID);
			}

			texManager = textureManager.a.keySet().iterator();

			while(texManager.hasNext())
			{
				String next = (String)texManager.next();

				try {
					if(next.startsWith("##"))
					{
						image = TextureManager.a(ImageIO.read(TextureManager.class.getResourceAsStream(next.substring(2))));
					} else {
						image = ImageIO.read(TextureManager.class.getResourceAsStream(next));
					}

					textureID = textureManager.a.get(next);
					textureManager.a(image, textureID);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}

		if(setting == 7)
		{
			h = !h;
		}

		if(setting == 8)
		{
			smoothing = !smoothing;

			v.textureManager.a.clear();
			v.textureManager.b.clear();

			v.a.a();
		}

		if(setting == 9)
		{
			if(currentTexturePack == texturePacks.size() - 1)
			{
				currentTexturePack = 0;

				texturePack = "none";
			} else {
				currentTexturePack++;

				texturePack = texturePacks.get(currentTexturePack);
			}
		}

		b();
	}

	public String b(int id) // get setting
	{
		return
				id == 0 ? "Music: " + (a ? "ON" : "OFF")
				: (id == 1 ? "Sound: " + (b ? "ON" : "OFF")
				: (id == 2 ? "Invert mouse: " + (c ? "ON" : "OFF")
				: (id == 3 ? "Show FPS: " + (d ? "ON" : "OFF")
				: (id == 4 ? "Render distance: " + u[e]
				: (id == 5 ? "View bobbing: " + (f ? "ON" : "OFF")
				: (id == 6 ? "3d anaglyph: " + (g ? "ON" : "OFF")
				: (id == 7 ? "Limit framerate: " + (h ? "ON" : "OFF")
				: (id == 8 ? "Smoothing: " + (smoothing ? "ON" : "OFF")
				: (id == 9 ? "Texture Pack: " + (currentTexturePack == 0 ? "none" : texturePacks.get(currentTexturePack))
				: "")))))))));
	}

	private void a() // load
	{
		try {
			if(w.exists())
			{
				BufferedReader br = new BufferedReader(new FileReader(w));
				String line = null;

				while((line = br.readLine()) != null)
				{
					String[] setting;

					if((setting = line.split(":"))[0].equals("music"))
					{
						a = setting[1].equals("true");
					}

					if(setting[0].equals("sound"))
					{
						b = setting[1].equals("true");
					}

					if(setting[0].equals("invertYMouse"))
					{
						c = setting[1].equals("true");
					}

					if(setting[0].equals("showFrameRate"))
					{
						d = setting[1].equals("true");
					}

					if(setting[0].equals("viewDistance"))
					{
						e = Integer.parseInt(setting[1]);
					}

					if(setting[0].equals("bobView"))
					{
						f = setting[1].equals("true");
					}

					if(setting[0].equals("anaglyph3d"))
					{
						g = setting[1].equals("true");
					}

					if(setting[0].equals("limitFramerate"))
					{
						h = setting[1].equals("true");
					}

					if(setting[0].equals("smoothing"))
					{
						smoothing = setting[1].equals("true");
					}

					for(int index = 0; index < s.length; index++)
					{
						if(setting[0].equals("key_" + s[index].name))
						{
							s[index].key = Integer.parseInt(setting[1]);
						}
					}
				}

				br.close();
			}
		} catch (Exception e1) {
			throw new RuntimeException("Failed to load options", e1);
		}
	}

	private void b() // save
	{
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(w));

			pw.println("music:" + a);
			pw.println("sound:" + b);
			pw.println("invertYMouse:" + c);
			pw.println("showFrameRate:" + d);
			pw.println("viewDistance:" + e);
			pw.println("bobView:" + f);
			pw.println("anaglyph3d:" + g);
			pw.println("limitFramerate:" + h);

			pw.println("smoothing:" + smoothing);
			pw.println("texturepack:" + texturePack);

			for(int binding = 0; binding < s.length; ++binding)
			{
				pw.println("key_" + s[binding].name + ":" + s[binding].key);
			}

			pw.close();
		} catch (Exception e1) {
			throw new RuntimeException("Failed to save options", e1);
		}
	}

}
