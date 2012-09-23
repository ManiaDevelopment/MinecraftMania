package com.mojang.minecraft;

import java.io.*;
import java.nio.channels.FileChannel;

/**
 * Created with IntelliJ IDEA.
 * User: Oliver Yasuna
 * Date: 9/8/12
 * Time: 11:21 PM
 */
public class ResourceDownloadThread extends Thread
{
	public ResourceDownloadThread(File minecraftFolder, Minecraft minecraft)
	{
		this.minecraft = minecraft;

		setName("Resource download thread");
		setDaemon(true);

		this.minecraftFolder = minecraftFolder;
		resources = new File(minecraftFolder, "resources");

		if(!resources.exists() && !resources.mkdirs())
		{
			throw new RuntimeException("The working directory could not be created: " + resources);
		}

		copyDirectory(new File("minecraft/resources"), new File(minecraftFolder, "resources"));
	}

	@Override
	public void run()
	{
		File soundsFolder = new File(resources, "sound");
		File stepsFolder = new File(soundsFolder, "step");

		for(int i = 1; i <= 4; i++)
		{
			minecraft.audioManager.a(new File(stepsFolder, "grass" + i + ".ogg"), "step/grass" + i + ".ogg");
			minecraft.audioManager.a(new File(stepsFolder, "gravel" + i + ".ogg"), "step/gravel" + i + ".ogg");
			minecraft.audioManager.a(new File(stepsFolder, "stone" + i + ".ogg"), "step/stone" + i + ".ogg");
			minecraft.audioManager.a(new File(stepsFolder, "wood" + i + ".ogg"), "step/wood" + i + ".ogg");
		}

		File musicFolder = new File(resources, "music");

		for(int i = 1; i <= 3; i++)
		{
			minecraft.audioManager.a("calm" + i + ".ogg", new File(musicFolder, "calm" + i + ".ogg"));
		}

		a = true;
	}

	private File minecraftFolder;
	private File resources;
	private Minecraft minecraft;
	boolean a = false;

	public void copyDirectory(File sourceLocation , File targetLocation)
	{
		try {
			if(sourceLocation.isDirectory())
			{
				if(!targetLocation.exists())
				{
					targetLocation.mkdir();
				}

				String[] children = sourceLocation.list();

				for(int i = 0; i < children.length; i++)
				{
					copyDirectory(new File(sourceLocation, children[i]), new File(targetLocation, children[i]));
				}
			} else {
				InputStream in = new FileInputStream(sourceLocation);
				OutputStream out = new FileOutputStream(targetLocation);

				byte[] buf = new byte[1024];

				int len;
				while((len = in.read(buf)) > 0)
				{
					out.write(buf, 0, len);
				}

				in.close();
				out.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
