package org.oyasunadev.minecraftmania.client.minecraft;

import com.mojang.minecraft.Entity;
import com.mojang.minecraft.Minecraft;
import com.mojang.minecraft.ProgressBarDisplay;
import com.mojang.minecraft.level.Level;
import com.mojang.minecraft.net.NetworkManager;
import com.mojang.minecraft.player.Player;
import com.mojang.minecraft.render.texture.TextureFX;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.*;
import org.lwjgl.opengl.DisplayMode;
import org.oyasunadev.minecraftmania.client.gui.MPFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.Calendar;
import java.util.zip.GZIPOutputStream;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created with IntelliJ IDEA.
 * User: Oliver Yasuna
 * Date: 9/2/12
 * Time: 1:48 PM
 */
public class MMPlayer2 extends Player
{
	public MMPlayer2(Level level, Player player, Minecraft minecraft, boolean bypass, JFrame mpFrame)
	{
		super(level);

		level_ = level;
		this.minecraft = minecraft;
		this.mpFrame = mpFrame;

		this.bypass = bypass;

		input = player.input;
		inventory = player.inventory;
		userType = player.userType;
		oBob = player.oBob;
		bob = player.bob;
		score = player.score;
		arrows = player.arrows;
		invulnerableDuration = player.invulnerableDuration;
		health = player.health;
		lastHealth = player.lastHealth;
		invulnerableTime = player.invulnerableTime;
		rot = player.rot;
		timeOffs = player.timeOffs;
		speed = player.speed;
		rotA = player.rotA;
		hasHair = player.hasHair;
		allowAlpha = player.allowAlpha;
		rotOffs = player.rotOffs;
		renderOffset = player.renderOffset;
		hurtDir = player.hurtDir;
		oTilt = player.oTilt;
		modelName = player.modelName;
		airSupply = player.airSupply;
		hurtTime = player.hurtTime;
		hurtDuration = player.hurtDuration;
		deathTime = player.deathTime;
		attackTime = player.attackTime;
		tilt = player.tilt;
		ai = player.ai;
		level = player.level; //this.level = level; //
		xo = player.xo;
		yo = player.yo;
		zo = player.zo;
		x = player.x;
		y = player.y;
		z = player.z;
		xd = player.xd;
		yd = player.yd;
		zd = player.zd;
		yRot = player.yRot;
		xRot = player.xRot;
		yRotO = player.yRotO;
		xRotO = player.xRotO;
		heightOffset = player.heightOffset;
		bbWidth = player.bbWidth;
		bbHeight = player.bbHeight;
		bb = player.bb;
		onGround = player.onGround;
		horizontalCollision = player.horizontalCollision;
		collision = player.collision;
		slide = player.slide;
		removed = player.removed;
		makeStepSound = player.makeStepSound;
		noPhysics = player.noPhysics;
		hovered = player.hovered;
		walkDistO = player.walkDistO;
		walkDist = player.walkDist;
		fallDistance = player.fallDistance;
		xOld = player.xOld;
		blockMap = level.blockMap; //
		yOld = player.yOld;
		zOld = player.zOld;
		ySlideOffset = player.ySlideOffset;
		footSize = player.footSize;
		textureId = player.textureId;
		pushthrough = player.pushthrough;

		setPos(player.x, player.y, player.z);

		movement = new ControlMovement(this);
	}

	@Override
	public void aiStep()
	{
		//System.out.println(com.mojang.minecraft.net.a.testPacket.r);

		try {
			// TODO: Better system.

			// NOTES:
			// - Default jump height is 0.38F.

			Field field = ProgressBarDisplay.class.getDeclaredField("text");
			field.setAccessible(true);
			String s = (String)field.get(level.rendererContext$5cd64a7f.progressBar);
			field = ProgressBarDisplay.class.getDeclaredField("title");
			field.setAccessible(true);
			String s1 = (String)field.get(level.rendererContext$5cd64a7f.progressBar);

			if(s.length() > 0 && !s.contains("Connecting.."))
			{
				String s2 = (new StringBuilder()).append(s).append(" ").append(s1).toString().toLowerCase();

				if(s2.contains("+hax"))
				{
					fly = true;
					speed1 = true;
					noclip = true;
				}

				if(s2.contains("+fly"))
				{
					fly = true;
				}

				if(s2.contains("+speed"))
				{
					speed1 = true;
				}

				if(s2.contains("+noclip"))
				{
					noclip = true;
				}

				if(s2.contains("+ophax"))
				{
					if(userType >= 100)
					{
						fly = true;
						speed1 = true;
						noclip = true;
					}
				}

				if(s.contains("freebuild") || s1.contains("freebuild"))
				{
					fly = true;
					speed1 = true;
					noclip = true;
				}

				if(bypass)
				{
					fly = true;
					speed1 = true;
					noclip = true;
				}

				super.aiStep();

				boolean inWater = isInWater();
				boolean inLava = isInLava();

				movement.update();

				//if(movement.xray)
				//{
					/*float speedX = 6.0F;
					float speedZ = 6.0F;

					x += Math.sin(xd * 3.1415F / 180.0F) * speed;
					z += Math.cos(zd * 3.1415F / 180.0F) * speed;*/

					/*
					// Walk forward.
					x -= speed * (float)Math.sin(Math.toRadians(xd));
					y += speed * (float)Math.tan(Math.toRadians(yd));
					z += speed * (float)Math.cos(Math.toRadians(zd));
					*/

					/*
					// Walk backwards.
					x += speed * (float)Math.sin(Math.toRadians(xd));
					y -= speed * (float)Math.tan(Math.toRadians(yd));
					z -= speed * (float)Math.cos(Math.toRadians(zd));
					*/

					/*
					// Fly up.
					y += speed;
					*/

					/*
					// Fly down
					y -= speed;
					*/
				//}

				if(fly)
				{
					if(movement.fly)
					{
						// 3D FLY
						// DOESN'T WORK RIGHT.
						//float yy = y;

						move(xd * movement.mult, 0, zd * movement.mult);

						if(movement.flyUp)
						{
							yd = 0.5F * movement.mult;
						} else {
							if(!movement.flyDown)
							{
								yd = 0.0F;
							}
						}

						if(movement.flyDown)
						{
							yd = -0.5F * movement.mult;
						} else {
							if(!movement.flyUp)
							{
								yd = 0.0F;
							}
						}

						float i = (onGround ? 0.1F : 0.02F);
						moveRelative(movement.strafe, movement.move, i * movement.mult * 10.0F);

						xd *= 0.6F;
						zd *= 0.6F;

						// 3D FLY
						// DOESN'T WORK CORRECTLY.
						/*yd = (float)((double)yd - zd);
											   if(horizontalCollision && isFree(xd, ((yd + 0.6F) - y) + yy, zd))
											   {
												   yd = 0.3F;
											   }*/
					}
				}

				if(movement.jump)
				{
					if(inWater)
					{
						yd += 0.08F * movement.mult;
					} else if(inLava) {
						yd += 0.08F * movement.mult;
					} else if(onGround) {
						if(!movement.fall)
						{
							// Regular = 0.38F.
							// 1 Block + Half Block = 0.48F.
							// 2 Blocks = 0.56F.
							yd = 0.38F * movement.mult;

							movement.fall = true;
						}
					}
				} else {
					movement.fall = false;
				}

				if(speed1)
				{
					if(movement.speed1)
					{
						movement.mult = 2.0F;
					} else {
						if(!movement.speed2)
						{
							movement.mult = 1F;
						}
					}

					if(movement.speed2)
					{
						movement.mult = 5F;
					} else {
						if(!movement.speed1)
						{
							movement.mult = 1F;
						}
					}
				}

				if(noclip)
				{
					if(movement.noclip)
					{
						/*if(speed == 0.01f)
						{
							speed *= 5.0f;
						}*/

						if(movement.flyUp)
						{
							yd = 0.5F * movement.mult;
						} else {
							if(!movement.flyDown)
							{
								yd = 0.0F;
							}
						}

						if(movement.flyDown)
						{
							yd = -0.5F * movement.mult;
						} else {
							if(!movement.flyUp)
							{
								yd = 0.0F;
							}
						}

						xd *= 0.6F;
						zd *= 0.6F;

						float i = (onGround ? 0.1F : 0.02F);
						moveRelative(movement.strafe, movement.move, i * movement.mult * 10.0F);

						moveTo(x + xd, (y + yd) - 0.72f, z + zd, yRot, xRot);
					}
				}

				if(!movement.fly && !movement.noclip)
				{
					if(inWater)
					{
						float yy = y;

						moveRelative(movement.strafe, movement.move, 0.02F * movement.mult);
						move(xd * movement.mult, yd * movement.mult, zd * movement.mult);

						xd *= 0.8F;
						yd *= 0.8F;
						zd *= 0.8F;

						yd = (float)((double)yd - 0.02D);

						if(horizontalCollision && isFree(xd, ((yd + 0.6F) - y) + yy, zd))
						{
							yd = 0.3F;
						}
					} else if(inLava) {
						float yy = y;

						moveRelative(movement.strafe, movement.move, 0.02F * movement.mult);
						move(xd * movement.mult, yd * movement.mult, zd * movement.mult);

						xd *= 0.5F;
						yd *= 0.5F;
						zd *= 0.5F;

						yd = (float)((double)yd - 0.02D);

						if(horizontalCollision && isFree(xd, ((yd + 0.6F) - y) + yy, zd))
						{
							yd = 0.3F;
						}
					} else {
						float i = (onGround ? 0.1F : 0.02F);
						moveRelative(movement.strafe, movement.move, i * movement.mult);
					}
				}
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void releaseAllKeys()
	{
		movement.clearKeys();
	}

	@Override
	public void setKey(int paramInt, boolean paramBoolean)
	{
		movement.processKey(paramInt, paramBoolean);
	}

	@Override
	public boolean isPushable()
	{
		return false;
	}

	@Override
	protected void push(float paramFloat1, float paramFloat2, float paramFloat3)
	{
	}

	@Override
	public void hurt(Entity paramEntity, int paramInt)
	{
		if(!level.creativeMode)
		{
			if(health <= 0)
			{
				return;
			}

			ai.hurt(paramEntity, paramInt);

			if(invulnerableTime > invulnerableDuration / 2.0f)
			{
				if(lastHealth - paramInt >= health)
				{
					return;
				}

				health = (lastHealth - paramInt);
			} else {
				lastHealth = health;
				invulnerableTime = invulnerableDuration;
				health -= paramInt;
				hurtTime = (hurtDuration = 10);
			}

			hurtDir = 0.0f;

			if(paramEntity != null)
			{
				float f1 = paramEntity.x - x;
				float f2 = paramEntity.z - z;

				hurtDir = ((float)(Math.atan2(f2, f1) * 180.0D / 3.141592741012573D) - yRot);

				knockback(paramEntity, paramInt, f1, f2);
			} else {
				hurtDir = ((int)(Math.random() * 2.0D) * 180);
			}

			if(health <= 0)
			{
				minecraft.hud.a("&4" + "<Client> YOU HAVE DIED!!!");

				die(paramEntity);
			}
		}
	}

	private Level level_;
	private Minecraft minecraft;
	private JFrame mpFrame;

	private ControlMovement movement;

	private boolean bypass;

	private boolean fly = false;
	private boolean speed1 = false;
	private boolean noclip = false;

	private class ControlMovement
	{
		public ControlMovement(MMPlayer2 player)
		{
			this.player = player;

			move = 0.0F;
			strafe = 0.0F;

			mult = 1.0F;

			jump = false;
			fall = false;

			keylist = new boolean[5];
		}

		private MMPlayer2 player;

		private float move; // Forward/Back.
		private float strafe; // Left/Right.

		private boolean fly = false;
		private boolean flyUp = false;
		private boolean flyDown = false;
		private boolean noclip = false;
		private boolean speed1 = false;
		private boolean speed2 = false;

		private boolean xray = false;

		private float mult;

		private boolean jump;
		private boolean fall;

		private boolean keylist[];

		public void processKey(int id, boolean pressed)
		{
			if(id == minecraft.settings.i.key) //w
			{
				keylist[0] = pressed;
			}

			if(id == minecraft.settings.j.key) //a
			{
				keylist[1] = pressed;
			}

			if(id == minecraft.settings.k.key) //s
			{
				keylist[2] = pressed;
			}

			if(id == minecraft.settings.l.key) //d
			{
				keylist[3] = pressed;
			}

			if(id == Keyboard.KEY_SPACE) //space
			{
				keylist[4] = pressed;
			}

			if(id == Keyboard.KEY_C) //c_Vector
			{
				speed1 = pressed;
			}

			if(id == Keyboard.KEY_LSHIFT) //shift
			{
				speed2 = pressed;
			}

			if(id == Keyboard.KEY_Z && pressed) //z
			{
				fly = !fly;
			}

			if(id == Keyboard.KEY_Q) //q
			{
				flyUp = pressed;
			}

			if(id == Keyboard.KEY_E) //e
			{
				flyDown = pressed;
			}

			if(id == Keyboard.KEY_X && pressed)
			{
				noclip = !noclip;
			}

			if(id == Keyboard.KEY_F2 && pressed)
			{
				//BufferedImage image = new BufferedImage(minecraft.b, minecraft.c_Vector, BufferedImage.TYPE_INT_RGB);

				/*Rectangle rect = new Rectangle(minecraft.b, minecraft.c_Vector);
				BufferedImage capture = minecraft.v.createScreenCapture(rect);

				try {
					ImageIO.write(capture, "bmp", new File("screenshot.bmp"));
				} catch (IOException e) {
					e.printStackTrace();
				}*/

				int width = minecraft.width;
				int height = minecraft.height;
				int bpp = 4;

				glReadBuffer(GL_FRONT);

				ByteBuffer buffer = BufferUtils.createByteBuffer(width * height * bpp);

				glReadPixels(0, 0, width, height, GL_RGBA, GL_UNSIGNED_BYTE, buffer);

				String s = String.format("screenshot_%1$tY%1$tm%1$td%1$tH%1$tM%1$tS.png", Calendar.getInstance());
				File file = new File(s);
				String format = "PNG";
				BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

				for(int x = 0; x < width; x++)
				{
					for(int y = 0; y < height; y++)
					{
						int i = (x + (width * y)) * bpp;
						int r = buffer.get(i) & 0xFF;
						int g = buffer.get(i + 1) & 0xFF;
						int b = buffer.get(i + 2) & 0xFF;

						image.setRGB(x, height - (y + 1), (0xFF << 24) | (r << 16) | (g << 8) | b);
					}
				}

				try {
					ImageIO.write(image, format, file);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if(id == Keyboard.KEY_F3 && pressed)
			{
				// TODO: Folder for each server.

				String serverName = "";
				File levelsFolder = null;

				try {
					Field field = ProgressBarDisplay.class.getDeclaredField("title");

					field.setAccessible(true);

					serverName = (String)field.get(level.rendererContext$5cd64a7f.progressBar);

					levelsFolder = new File("levels/" + serverName);

					if(!levelsFolder.exists())
					{
						levelsFolder.mkdir();
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				}

				String s = String.format("level_%1$tY%1$tm%1$td%1$tH%1$tM%1$tS.dat", Calendar.getInstance());
				//String s = "server_level_" + minecraft.level.width + "_" + minecraft.level.height + "_" + minecraft.level.depth + ".dat";

				FileOutputStream fos = null;
				GZIPOutputStream gzos = null;
				ObjectOutputStream out = null;
				DataOutputStream outputstream = null;

				try {
					fos = new FileOutputStream(new File(levelsFolder, s));
					gzos = new GZIPOutputStream(fos);
					outputstream = new DataOutputStream(gzos);

					outputstream.writeInt(0x271bb788);
					outputstream.writeByte(2);

					out = new ObjectOutputStream(gzos);

					Level level1 = new Level();
					Level level2 = minecraft.level;

					level1.blocks = level2.copyBlocks();
					level1.width = level2.width;
					level1.height = level2.height;
					level1.depth = level2.depth;
					level1.name = level2.name;
					level1.creator = level2.creator;
					level1.createTime = level2.createTime;
					level1.xSpawn = level2.xSpawn;
					level1.ySpawn = level2.ySpawn;
					level1.zSpawn = level2.zSpawn;
					level1.rotSpawn = level2.rotSpawn;

					out.writeObject(level1);

					outputstream.close();
					out.close();
				} catch(IOException ex) {
					ex.printStackTrace();
				}
			}

			if(id == Keyboard.KEY_F4 && pressed)
			{
				try {
					if(Display.isFullscreen())
					{

						Display.setFullscreen(false);
						Display.setDisplayMode(new DisplayMode(854, 480));

						mpFrame.setSize(854, 480);
						((MPFrame)mpFrame).finish();
					} else {
						Display.setDisplayMode(Display.getDesktopDisplayMode());
						Display.setFullscreen(true);
					}
				} catch (LWJGLException e) {
					e.printStackTrace();
				}

				minecraft.resize();
			}

			if(id == Keyboard.KEY_F6 && pressed)
			{
				minecraft.textureManager.a("test.zip");

				minecraft.textureManager.a.clear();
				minecraft.textureManager.b.clear();

				minecraft.a.a();
			}

			/*if(id == Keyboard.KEY_F6 && pressed)
			{
				Minecraft.mipmapMode = 0;

				minecraft.textureManager.a.clear();
				minecraft.textureManager.b.clear();
				minecraft.a.a();
			}

			if(id == Keyboard.KEY_F7 && pressed)
			{
				Minecraft.mipmapMode = 1;

				minecraft.textureManager.a.clear();
				minecraft.textureManager.b.clear();
				minecraft.a.a();
			}

			if(id == Keyboard.KEY_F8 && pressed)
			{
				Minecraft.mipmapMode = 2;

				minecraft.textureManager.a.clear();
				minecraft.textureManager.b.clear();
				minecraft.a.a();
			}*/

			/*if(id == Keyboard.KEY_F7 && pressed)
			{
				/*String s = String.format("level_%1$tY%1$tm%1$td%1$tH%1$tM%1$tS.dat", Calendar.getInstance());
				//String s = "server_level_" + minecraft.level.width + "_" + minecraft.level.height + "_" + minecraft.level.depth + ".dat";

				try {
					minecraft.levelIO.a(minecraft.level, new FileOutputStream(new File(s)));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}*/

				/*int textureID = minecraft.textureManager.a("/terrain2.png");

				GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureId);

				/*for(Object o : minecraft.textureManager.a.keySet())
				{
					System.out.println(o + ", " + minecraft.textureManager.a.get(o));
				}*/

				//minecraft.a.a1(minecraft.player, 0, "/terrain2.png");

				//ShapeRender.a.b();

				//////////////////////////////////////

				/*
			   Mob mob = null;
			   NetworkPlayer np = null;
			   for(Object o : minecraft.y.settings.values())
			   {
				   mob = (Mob)o;
				   np = (NetworkPlayer)mob;

				   np.setPos(player.x, player.y, player.z);

				   /*File file = new File("minecraft/resources/char.png");
				   try {
					   np.newTexture = ImageIO.read(file);
				   } catch (IOException e) {
					   e.printStackTrace();
				   }*/

				//np.displayName = np.displayName.replace("7", "textureIntBuffer");
				//}
			//}
		}

		public final void update()
		{
			move = 0.0F;
			strafe = 0.0F;

			if(keylist[0]) //w
			{
				move--;
			}

			if(keylist[1]) //a
			{
				strafe--;
			}

			if(keylist[2]) //s
			{
				move++;
			}

			if(keylist[3]) //textureByteBuffer
			{
				strafe++;
			}

			jump = keylist[4]; //space

			if(xray)
			{
				// TODO: XRay
			}
		}

		public final void clearKeys()
		{
			for(int i = 0; i < keylist.length; i++)
			{
				keylist[i] = false;
			}
		}
	}
}
