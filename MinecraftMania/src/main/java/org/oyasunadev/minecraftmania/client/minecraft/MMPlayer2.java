package org.oyasunadev.minecraftmania.client.minecraft;

import com.mojang.minecraft.Entity;
import com.mojang.minecraft.Minecraft;
import com.mojang.minecraft.ProgressBarDisplay;
import com.mojang.minecraft.level.Level;
import com.mojang.minecraft.mob.Creeper;
import com.mojang.minecraft.mob.Mob;
import com.mojang.minecraft.mob.Zombie;
import com.mojang.minecraft.net.NetworkPlayer;
import com.mojang.minecraft.player.Player;
import org.lwjgl.BufferUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.NVFogDistance;
import org.lwjgl.util.glu.GLU;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.io.*;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Calendar;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
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
	public MMPlayer2(Level level, Player player, Minecraft minecraft, boolean bypass)
	{
		super(level);

		level_ = level;
		this.minecraft = minecraft;

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
						// DOESN'T WORK RIGHT.
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
						yd += 0.07F * movement.mult;
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
			if(id == Keyboard.KEY_W) //w
			{
				keylist[0] = pressed;
			}

			if(id == Keyboard.KEY_A) //a
			{
				keylist[1] = pressed;
			}

			if(id == Keyboard.KEY_S) //s
			{
				keylist[2] = pressed;
			}

			if(id == Keyboard.KEY_D) //d
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

				//String s = String.format("level_%1$tY%1$tm%1$td%1$tH%1$tM%1$tS.dat", Calendar.getInstance());
				String s = "server_level_" + minecraft.level.width + "_" + minecraft.level.height + "_" + minecraft.level.depth + ".dat";

				FileOutputStream fos = null;
				GZIPOutputStream gzos = null;
				ObjectOutputStream out = null;
				DataOutputStream outputstream = null;

				try {
					fos = new FileOutputStream(s);
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
				/*minecraft.e.a();
				minecraft.x = false;
				minecraft.y.e = true;*/

				xray = !xray;

				try {
					player.minecraft.textureManager.a(ImageIO.read(new File("terrain2.png")), 16);
					minecraft.textureManager.a(ImageIO.read(new File("terrain2.png")), 16);
				} catch (IOException e) {
					e.printStackTrace();
				}

				Display.update();

				/*// Reset.
				int width = (minecraft.b * 240 / minecraft.c_Vector);
				int height = (minecraft.c_Vector * 240 / minecraft.c_Vector);
				GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
				GL11.glMatrixMode(GL11.GL_PROJECTION);
				GL11.glLoadIdentity();
				GL11.glOrtho(0.0D, width, height, 0.0D, 100.0D, 300.0D);
				GL11.glMatrixMode(GL11.GL_MODELVIEW);
				GL11.glLoadIdentity();
				GL11.glTranslatef(0.0F, 0.0F, -200.0F);*/

				/*
				Mob mob = null;
				NetworkPlayer np = null;
				for(Object o : minecraft.y.f.values())
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

					//np.displayName = np.displayName.replace("7", "c_Vector");
				//}
				/**/

				//minecraft.m is the game display.
				//minecraft.n is the game control.
			}

			if(id == Keyboard.KEY_F6 && pressed)
			{
				//minecraft.n.fontScale = 0.5f;

				/*com.mojang.minecraft.level.tile.a tile;

				for(Object o : com.mojang.minecraft.a.a)
				{
					tile = (com.mojang.minecraft.level.tile.a)o;

					System.out.println(tile);
				}*/

				/*com.mojang.minecraft.b chatline;

				for(Object o : minecraft.w.a)
				{
					chatline = (com.mojang.minecraft.b)o;

					System.out.println(chatline.a);
				}*/

				/*try {
					Field fieldWidth = minecraft.w.getClass().getDeclaredField("f");
					Field fieldHeight = minecraft.w.getClass().getDeclaredField("g");

					fieldWidth.setAccessible(true);
					fieldHeight.setAccessible(true);

					int width = (Integer)fieldWidth.get(minecraft.w);
					int height = (Integer)fieldHeight.get(minecraft.w);

					System.out.println("BEFORE: " + width + ", " + height);

					fieldWidth.set(minecraft.w, (Integer)fieldWidth.get(minecraft.w) / 2);
					fieldHeight.set(minecraft.w, (Integer)fieldHeight.get(minecraft.w) / 2);

					width = (Integer)fieldWidth.get(minecraft.w);
					height = (Integer)fieldHeight.get(minecraft.w);

					System.out.println("AFTER: " + width + ", " + height);
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}*/
			}

			if(id == Keyboard.KEY_F7)
			{
				minecraft.renderer.a();

				Display.update();
			}
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

			if(keylist[3]) //d
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
