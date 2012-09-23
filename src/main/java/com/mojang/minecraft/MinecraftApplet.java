package com.mojang.minecraft;

import java.applet.Applet;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Oliver Yasuna
 * Date: 9/8/12
 * Time: 11:06 PM
 */
public class MinecraftApplet extends Applet
{
	public MinecraftApplet()
	{
	}

	private static final long serialVersionUID = 1L;

	public Canvas canvas;
	public Minecraft minecraft;

	private Thread thread = null;

	public void init()
	{
		canvas = new Canvas();

		boolean fullscreen = false;
		if(getParameter("fullscreen") != null)
		{
			fullscreen = getParameter("fullscreen").equalsIgnoreCase("true");
		}

		minecraft = new Minecraft(canvas, this, getWidth(), getHeight(), fullscreen);

		minecraft.website = getDocumentBase().getHost();

		if(getDocumentBase().getPort() > 0)
		{
			minecraft.website = minecraft.website + ":" + getDocumentBase().getPort();
		}

		if(getParameter("username") != null && getParameter("sessionid") != null)
		{
			minecraft.sessionData = new AvailableBlockType(getParameter("username"), getParameter("sessionid"));

			if(getParameter("mppass") != null)
			{
				minecraft.sessionData.d = getParameter("mppass");
			}

			minecraft.sessionData.e = "true".equals(getParameter("haspaid"));
		}

		if(getParameter("loadmap_user") != null && getParameter("loadmap_id") != null)
		{
			minecraft.loadmap_user = getParameter("loadmap_user");
			minecraft.loadmap_id = Integer.parseInt(getParameter("loadmap_id"));
		} else if(getParameter("server") != null && getParameter("port") != null) {
			Minecraft tmpMinecraft = minecraft;
			String server = getParameter("server");
			int port = Integer.parseInt(getParameter("port"));
			String server1 = server;
			Minecraft tmpMinecraft1 = tmpMinecraft;

			tmpMinecraft.server = server1;
			tmpMinecraft1.port = port;
		}

		minecraft.k = true;
		setLayout(new BorderLayout());
		add(canvas, "Center");
		canvas.setFocusable(true);
		validate();
	}

	public void startGameThread()
	{
		if(thread == null)
		{
			thread = new Thread(minecraft);

			thread.start();
		}
	}

	public void start()
	{
		minecraft.waiting = false;
	}

	public void stop()
	{
		minecraft.waiting = true;
	}

	public void destroy()
	{
		stopGameThread();
	}

	public void stopGameThread()
	{
		if(thread != null)
		{
			minecraft.running = false;

			try {
				thread.join(1000L);
			} catch (InterruptedException var3) {
				try {
					minecraft.a();
				} catch (Exception var2) {
					var2.printStackTrace();
				}
			}

			thread = null;
		}
	}
}
