package com.mojang.minecraft;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: Oliver Yasuna
 * Date: 9/8/12
 * Time: 11:21 PM
 */
public final class ResourceDownloadThread extends Thread
{
	public ResourceDownloadThread(File file, Minecraft minecraft)
	{
		this.minecraft = minecraft;

		setName("Resource download thread");
		setDaemon(true);

		this.file = new File("minecraft", "resources");

		if(!this.file.exists() && !this.file.mkdirs())
		{
			throw new RuntimeException("The working directory could not be created: " + this.file);
		}
	}

	@Override
	public void run()
	{
		File soundsFolder = new File(file, "sound");
		File stepsFolder = new File(soundsFolder, "step");

		for(int i = 1; i <= 4; i++)
		{
			minecraft.audioManager.a(new File(stepsFolder, "grass" + i + ".ogg"), "step/grass" + i + ".ogg");
			minecraft.audioManager.a(new File(stepsFolder, "gravel" + i + ".ogg"), "step/gravel" + i + ".ogg");
			minecraft.audioManager.a(new File(stepsFolder, "stone" + i + ".ogg"), "step/stone" + i + ".ogg");
			minecraft.audioManager.a(new File(stepsFolder, "wood" + i + ".ogg"), "step/wood" + i + ".ogg");
		}

		File musicFolder = new File(file, "music");

		for(int i = 1; i <= 3; i++)
		{
			minecraft.audioManager.a("calm" + i + ".ogg", new File(musicFolder, "calm" + i + ".ogg"));
		}
	}

	private File file;
	private Minecraft minecraft;
	boolean a = false;
}
