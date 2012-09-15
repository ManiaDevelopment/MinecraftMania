package org.oyasunadev.minecraftmania.client.gui;

import com.mojang.minecraft.level.Level;
import org.oyasunadev.minecraftmania.client.minecraft.MMApplet;
import org.oyasunadev.minecraftmania.client.minecraft.MMPlayer2;
import org.oyasunadev.minecraftmania.client.util.ParseServers;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Oliver Yasuna
 * Date: 9/2/12
 * Time: 1:14 PM
 */
public class MPFrame extends JFrame implements Runnable
{
	public MPFrame(String username, String sessionid, boolean haspaid, String server, int port, String mppass)
	{
		setTitle("MinecraftMania");
		setSize(854, 480);
		//setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		File iconFile = new File("resources/images/icon.png");
		Image iconImage = null;
		try {
			iconImage = ImageIO.read(iconFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		setIconImage(iconImage);

		this.username = username;
		this.sessionid = sessionid;
		this.haspaid = haspaid;
		this.server = server;
		this.port = port;
		this.mppass = mppass;
	}

	@Override
	public void run()
	{
		boolean set = false;

		try {
			while(true)
			{
				Thread.sleep(1000);

				/*NetworkPlayer np = null;
				for(Object o : mmApplet.minecraft.y.f.values())
				{
					np = (NetworkPlayer)o;

					if(np.displayName.contains("oyasunadev"))
					{
						np.displayName = np.displayName.replace("7", "e");
					}
				}*/

				if((mmApplet.minecraft.level != null && oldLevel != mmApplet.minecraft.level))
				{
					if(mmPlayer2 == null)
					{
						// TODO: Change last argument to false before release.

						mmPlayer2 = new MMPlayer2(mmApplet.minecraft.level, mmApplet.minecraft.player, mmApplet.minecraft, true, this);

						System.out.println("mmPlayer2 set");
					}

					mmApplet.minecraft.player = mmPlayer2;

					System.out.println("Level change actions done");
				}

				oldLevel = mmApplet.minecraft.level;

				// Play music.
				//mmApplet.minecraft.s.a(mmApplet.minecraft.z, "calm");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private String username;
	private String sessionid;
	private boolean haspaid;
	private String server;
	private int port;
	private String mppass;

	private MMApplet mmApplet;
	private MMPlayer2 mmPlayer2;

	private Level oldLevel;

	public void startMinecraft()
	{
		setSize(ParseServers.width, ParseServers.height);

		/**/

		setLayout(new BorderLayout());

		/**/

		System.setProperty("org.lwjgl.opengl.Display.allowSoftwareOpenGL", "false");

		mmApplet = new MMApplet(false, username, sessionid, haspaid, server, port, mppass);

		System.out.println(mppass);

		mmApplet.setSize(getWidth(), getHeight() - 38);

		mmApplet.init();

		//add(Box.createVerticalStrut(10));

		add(mmApplet.canvas);

		mmApplet.startGameThread();

		new Thread(this).start();

		// send message local
		//minecraft.w.a();
	}

	public void finish()
	{
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getWidth()) / 2,
				(Toolkit.getDefaultToolkit().getScreenSize().height - getHeight()) / 2);
	}
}
