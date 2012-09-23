package org.oyasunadev.minecraftmania.client.minecraft;

import com.mojang.minecraft.MinecraftApplet;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Oliver Yasuna
 * Date: 9/2/12
 * Time: 1:16 PM
 */
public class MMApplet extends MinecraftApplet
{
	public MMApplet(boolean fullscreen, String username, String sessionid, boolean haspaid, String server, int port, String mppass)
	{
		parameters = new HashMap<String, String>();

		parameters.put("fullscreen", String.valueOf(fullscreen));
		parameters.put("username", username);
		parameters.put("sessionid", sessionid);
		parameters.put("haspaid", String.valueOf(haspaid));
		parameters.put("server", server);
		parameters.put("port", String.valueOf(port));
		parameters.put("mppass", mppass);
	}

	@Override
	public URL getDocumentBase()
	{
		try {
			return new URL("http://minecraft.net:80/play.jsp");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public URL getCodeBase()
	{
		try {
			return new URL("http://minecraft.net:80/");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public String getParameter(String name)
	{
		return parameters.get(name);
	}

	@Override
	public void resize(Dimension d)
	{
		super.resize(d);

		minecraft.width = d.width;
		minecraft.height = d.height;
	}

	private Map<String, String> parameters;
}
