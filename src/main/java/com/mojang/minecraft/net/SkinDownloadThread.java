package com.mojang.minecraft.net;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: Oliver Yasuna
 * Date: 9/8/12
 * Time: 11:14 PM
 */
public final class SkinDownloadThread extends Thread
{
	public SkinDownloadThread(NetworkPlayer networkPlayer)
	{
		this.networkPlayer = networkPlayer;
	}

	@Override
	public final void run()
	{
		HttpURLConnection connection = null;

		try {
			connection = (HttpURLConnection)(new URL("http://www.minecraft.net/skin/" + networkPlayer.name + ".png").openConnection());

			connection.setDoInput(true);
			connection.setDoOutput(false);

			connection.connect();

			if(connection.getResponseCode() == 400 || connection.getResponseCode() == 403 || connection.getResponseCode() == 404
					|| connection.getResponseCode() == 502)
			{
				connection = (HttpURLConnection)(new URL("http://www.minecraft.net/skin/oyasunadev.png").openConnection());

				connection.setDoInput(true);
				connection.setDoOutput(false);

				connection.connect();

				if(connection.getResponseCode() != 400 || connection.getResponseCode() != 403 || connection.getResponseCode() != 404
						|| connection.getResponseCode() != 502)
				{
					networkPlayer.newTexture = ImageIO.read(connection.getInputStream());
				}

				return;
			}

			networkPlayer.newTexture = ImageIO.read(connection.getInputStream());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			connection.disconnect();
		}
	}

	private NetworkPlayer networkPlayer;
}
