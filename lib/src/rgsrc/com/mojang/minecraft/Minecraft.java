package com.mojang.minecraft;

import com.mojang.minecraft.AvailableBlockType;
import com.mojang.minecraft.ChatLine;
import com.mojang.minecraft.Entity;
import com.mojang.minecraft.GameSettings;
import com.mojang.minecraft.Minecraft$OS;
import com.mojang.minecraft.MinecraftApplet;
import com.mojang.minecraft.MovingObjectPosition;
import com.mojang.minecraft.OperatingSystemLookup;
import com.mojang.minecraft.ProgressBarDisplay;
import com.mojang.minecraft.ResourceDownloadThread;
import com.mojang.minecraft.SkinDownloadThread;
import com.mojang.minecraft.SleepForeverThread;
import com.mojang.minecraft.StopGameException;
import com.mojang.minecraft.Timer;
import com.mojang.minecraft.gamemode.CreativeGameMode;
import com.mojang.minecraft.gamemode.GameMode;
import com.mojang.minecraft.gamemode.SurvivalGameMode;
import com.mojang.minecraft.gui.ChatInputScreen;
import com.mojang.minecraft.gui.ErrorScreen;
import com.mojang.minecraft.gui.FontRenderer;
import com.mojang.minecraft.gui.GameOverScreen;
import com.mojang.minecraft.gui.HUDScreen;
import com.mojang.minecraft.gui.PauseScreen;
import com.mojang.minecraft.gui.PopUpScreen;
import com.mojang.minecraft.item.Arrow;
import com.mojang.minecraft.item.Item;
import com.mojang.minecraft.level.Level;
import com.mojang.minecraft.level.LevelIO;
import com.mojang.minecraft.level.generator.LevelGenerator;
import com.mojang.minecraft.level.liquid.LiquidType;
import com.mojang.minecraft.level.tile.Block;
import com.mojang.minecraft.mob.Mob;
import com.mojang.minecraft.model.HumanoidModel;
import com.mojang.minecraft.model.ModelManager;
import com.mojang.minecraft.model.ModelRenderer;
import com.mojang.minecraft.model.Vec3D;
import com.mojang.minecraft.net.NetworkManager;
import com.mojang.minecraft.net.NetworkPlayer;
import com.mojang.minecraft.net.PacketType;
import com.mojang.minecraft.particle.Particle;
import com.mojang.minecraft.particle.ParticleManager;
import com.mojang.minecraft.particle.WaterDropParticle;
import com.mojang.minecraft.phys.AABB;
import com.mojang.minecraft.player.InputHandlerImpl;
import com.mojang.minecraft.player.Player;
import com.mojang.minecraft.render.Chunk;
import com.mojang.minecraft.render.ClippingHelper;
import com.mojang.minecraft.render.ClippingHelperImpl;
import com.mojang.minecraft.render.HeldBlock;
import com.mojang.minecraft.render.LevelRenderer;
import com.mojang.minecraft.render.Renderer;
import com.mojang.minecraft.render.ShapeRender;
import com.mojang.minecraft.render.TextureManager;
import com.mojang.minecraft.render.b;
import com.mojang.minecraft.render.texture.TextureFX;
import com.mojang.minecraft.render.texture.TextureLavaFX;
import com.mojang.minecraft.render.texture.TextureWaterFX;
import com.mojang.minecraft.sound.i;
import com.mojang.minecraft.sound.k;
import com.mojang.net.NetworkHandler;
import com.mojang.util.MathHelper;
import java.awt.AWTException;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.IntBuffer;
import java.util.Collections;
import java.util.List;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Controllers;
import org.lwjgl.input.Cursor;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public final class Minecraft implements Runnable {

   public GameMode gameMode = new CreativeGameMode(this);
   private boolean fullscreen = false;
   public int width;
   public int height;
   private Timer timer = new Timer(20.0F);
   public Level level;
   public LevelRenderer a;
   public Player player;
   public ParticleManager particleManager;
   public AvailableBlockType sessionData = null;
   public String website;
   public Canvas canvas;
   public boolean k = false;
   public volatile boolean waiting = false;
   private Cursor cursor;
   public TextureManager textureManager;
   public FontRenderer fontRenderer;
   public PopUpScreen currentScreen = null;
   public ProgressBarDisplay progressBar = new ProgressBarDisplay(this);
   public Renderer renderer = new Renderer(this);
   public LevelIO levelIO;
   public k audioManager;
   private ResourceDownloadThread resourceThread;
   private int ticks;
   private int blockHitTime;
   public String loadmap_user;
   public int loadmap_id;
   public Robot robot;
   public HUDScreen hud;
   public boolean online;
   public NetworkManager networkManager;
   public i audioPlayer;
   public MovingObjectPosition selected;
   public GameSettings settings;
   private MinecraftApplet minecraftApplet;
   String server;
   int port;
   volatile boolean running;
   public String debugInfo;
   public boolean hasMouse;
   private int lastClick;
   public boolean raining;


   public Minecraft(Canvas var1, MinecraftApplet var2, int var3, int var4, boolean var5) {
      this.levelIO = new LevelIO(this.progressBar);
      this.audioManager = new k();
      this.ticks = 0;
      this.blockHitTime = 0;
      this.loadmap_user = null;
      this.loadmap_id = 0;
      this.online = false;
      new HumanoidModel(0.0F);
      this.selected = null;
      this.server = null;
      this.port = 0;
      this.running = false;
      this.debugInfo = "";
      this.hasMouse = false;
      this.lastClick = 0;
      this.raining = false;

      try {
         UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      } catch (Exception var7) {
         var7.printStackTrace();
      }

      this.minecraftApplet = var2;
      new SleepForeverThread(this);
      this.canvas = var1;
      this.width = var3;
      this.height = var4;
      this.fullscreen = var5;
      if(var1 != null) {
         try {
            this.robot = new Robot();
            return;
         } catch (AWTException var8) {
            var8.printStackTrace();
         }
      }

   }

   public final void a(PopUpScreen var1) {
      if(!(this.currentScreen instanceof ErrorScreen)) {
         if(this.currentScreen != null) {
            this.currentScreen.b();
         }

         if(var1 == null && this.player.health <= 0) {
            var1 = new GameOverScreen();
         }

         this.currentScreen = (PopUpScreen)var1;
         if(var1 != null) {
            if(this.hasMouse) {
               this.player.releaseAllKeys();
               this.hasMouse = false;
               if(this.k) {
                  try {
                     Mouse.setNativeCursor((Cursor)null);
                  } catch (LWJGLException var4) {
                     var4.printStackTrace();
                  }
               } else {
                  Mouse.setGrabbed(false);
               }
            }

            int var2 = this.width * 240 / this.height;
            int var3 = this.height * 240 / this.height;
            ((PopUpScreen)var1).a(this, var2, var3);
            this.online = false;
         } else {
            this.b();
         }
      }
   }

   private static void a(String var0) {
      int var1;
      if((var1 = GL11.glGetError()) != 0) {
         String var2 = GLU.gluErrorString(var1);
         System.out.println("########## GL ERROR ##########");
         System.out.println("@ " + var0);
         System.out.println(var1 + ": " + var2);
         System.exit(0);
      }

   }

   public final void a() {
      try {
         if(this.audioPlayer != null) {
            i var1 = this.audioPlayer;
            this.audioPlayer.a = false;
         }

         if(this.resourceThread != null) {
            ResourceDownloadThread var4 = this.resourceThread;
            this.resourceThread.a = true;
         }
      } catch (Exception var3) {
         ;
      }

      Minecraft var5 = this;
      if(!this.k) {
         try {
            LevelIO.a(var5.level, (OutputStream)(new FileOutputStream(new File("level.dat"))));
         } catch (Exception var2) {
            var2.printStackTrace();
         }
      }

      Mouse.destroy();
      Keyboard.destroy();
      Display.destroy();
   }

   public final void run() {
      this.running = true;

      try {
         Minecraft var1 = this;
         if(this.canvas != null) {
            Display.setParent(this.canvas);
         } else if(this.fullscreen) {
            Display.setFullscreen(true);
            this.width = Display.getDisplayMode().getWidth();
            this.height = Display.getDisplayMode().getHeight();
         } else {
            Display.setDisplayMode(new DisplayMode(this.width, this.height));
         }

         Display.setTitle("Minecraft 0.30");

         try {
            Display.create();
         } catch (LWJGLException var57) {
            var57.printStackTrace();

            try {
               Thread.sleep(1000L);
            } catch (InterruptedException var56) {
               ;
            }

            Display.create();
         }

         Keyboard.create();
         Mouse.create();

         try {
            Controllers.create();
         } catch (Exception var55) {
            var55.printStackTrace();
         }

         a("Pre startup");
         GL11.glEnable(3553);
         GL11.glShadeModel(7425);
         GL11.glClearDepth(1.0D);
         GL11.glEnable(2929);
         GL11.glDepthFunc(515);
         GL11.glEnable(3008);
         GL11.glAlphaFunc(516, 0.0F);
         GL11.glCullFace(1029);
         GL11.glMatrixMode(5889);
         GL11.glLoadIdentity();
         GL11.glMatrixMode(5888);
         a("Startup");
         String var3 = "minecraft";
         String var5 = System.getProperty("user.home", ".");
         String var6;
         File var7;
         switch(OperatingSystemLookup.a[((var6 = System.getProperty("os.name").toLowerCase()).contains("win")?Minecraft$OS.windows:(var6.contains("mac")?Minecraft$OS.macos:(var6.contains("solaris")?Minecraft$OS.solaris:(var6.contains("sunos")?Minecraft$OS.solaris:(var6.contains("linux")?Minecraft$OS.linux:(var6.contains("unix")?Minecraft$OS.linux:Minecraft$OS.unknown)))))).ordinal()]) {
         case 1:
         case 2:
            var7 = new File(var5, '.' + var3 + '/');
            break;
         case 3:
            String var8;
            if((var8 = System.getenv("APPDATA")) != null) {
               var7 = new File(var8, "." + var3 + '/');
            } else {
               var7 = new File(var5, '.' + var3 + '/');
            }
            break;
         case 4:
            var7 = new File(var5, "Library/Application Support/" + var3);
            break;
         default:
            var7 = new File(var5, var3 + '/');
         }

         if(!var7.exists() && !var7.mkdirs()) {
            throw new RuntimeException("The working directory could not be created: " + var7);
         }

         File var2 = var7;
         this.settings = new GameSettings(this, var7);
         this.textureManager = new TextureManager(this.settings);
         this.textureManager.a((TextureFX)(new TextureLavaFX()));
         this.textureManager.a((TextureFX)(new TextureWaterFX()));
         this.fontRenderer = new FontRenderer(this.settings, "/default.png", this.textureManager);
         IntBuffer var9;
         (var9 = BufferUtils.createIntBuffer(256)).clear().limit(256);
         this.a = new LevelRenderer(this, this.textureManager);
         Item.initModels();
         Mob.modelCache = new ModelManager();
         GL11.glViewport(0, 0, this.width, this.height);
         if(this.server != null && this.sessionData != null) {
            Level var85;
            (var85 = new Level()).setData(8, 8, 8, new byte[512]);
            this.a(var85);
         } else {
            boolean var10 = false;

            try {
               if(var1.loadmap_user != null) {
                  var1.a(var1.loadmap_user, var1.loadmap_id);
               } else if(!var1.k) {
                  Level var11 = null;
                  if((var11 = var1.levelIO.a((InputStream)(new FileInputStream(new File("level.dat"))))) != null) {
                     var1.a(var11);
                  }
               }
            } catch (Exception var54) {
               var54.printStackTrace();
            }

            if(this.level == null) {
               this.a(1);
            }
         }

         this.particleManager = new ParticleManager(this.level, this.textureManager);
         if(this.k) {
            try {
               var1.cursor = new Cursor(16, 16, 0, 0, 1, var9, (IntBuffer)null);
            } catch (LWJGLException var53) {
               var53.printStackTrace();
            }
         }

         try {
            var1.audioPlayer = new i(var1.settings);
            i var4 = var1.audioPlayer;

            try {
               AudioFormat var67 = new AudioFormat(44100.0F, 16, 2, true, true);
               var4.b = AudioSystem.getSourceDataLine(var67);
               var4.b.open(var67, 4410);
               var4.b.start();
               var4.a = true;
               Thread var72;
               (var72 = new Thread(var4)).setDaemon(true);
               var72.setPriority(10);
               var72.start();
            } catch (Exception var51) {
               var51.printStackTrace();
               var4.a = false;
            }

            var1.resourceThread = new ResourceDownloadThread(var2, var1);
            var1.resourceThread.start();
         } catch (Exception var52) {
            ;
         }

         a("Post startup");
         this.hud = new HUDScreen(this, this.width, this.height);
         (new SkinDownloadThread(this)).start();
         if(this.server != null && this.sessionData != null) {
            this.networkManager = new NetworkManager(this, this.server, this.port, this.sessionData.b, this.sessionData.d);
         }
      } catch (Exception var62) {
         var62.printStackTrace();
         JOptionPane.showMessageDialog((Component)null, var62.toString(), "Failed to start Minecraft", 0);
         return;
      }

      long var13 = System.currentTimeMillis();
      int var15 = 0;

      try {
         while(this.running) {
            if(this.waiting) {
               Thread.sleep(100L);
            } else {
               if(this.canvas == null && Display.isCloseRequested()) {
                  this.running = false;
               }

               try {
                  Timer var63 = this.timer;
                  long var16;
                  long var18 = (var16 = System.currentTimeMillis()) - var63.g;
                  long var20 = System.nanoTime() / 1000000L;
                  double var24;
                  if(var18 > 1000L) {
                     long var22 = var20 - var63.h;
                     var24 = (double)var18 / (double)var22;
                     var63.i += (var24 - var63.i) * 0.20000000298023224D;
                     var63.g = var16;
                     var63.h = var20;
                  }

                  if(var18 < 0L) {
                     var63.g = var16;
                     var63.h = var20;
                  }

                  double var95;
                  var24 = ((var95 = (double)var20 / 1000.0D) - var63.b) * var63.i;
                  var63.b = var95;
                  if(var24 < 0.0D) {
                     var24 = 0.0D;
                  }

                  if(var24 > 1.0D) {
                     var24 = 1.0D;
                  }

                  var63.f = (float)((double)var63.f + var24 * (double)var63.e * (double)var63.a);
                  var63.c = (int)var63.f;
                  if(var63.c > 100) {
                     var63.c = 100;
                  }

                  var63.f -= (float)var63.c;
                  var63.d = var63.f;

                  for(int var64 = 0; var64 < this.timer.c; ++var64) {
                     ++this.ticks;
                     this.e();
                  }

                  a("Pre render");
                  GL11.glEnable(3553);
                  if(!this.online) {
                     this.gameMode.a(this.timer.d);
                     float var65 = this.timer.d;
                     Renderer var66 = this.renderer;
                     if(this.renderer.c && !Display.isActive()) {
                        var66.a.c();
                     }

                     var66.c = Display.isActive();
                     int var68;
                     int var70;
                     int var86;
                     int var81;
                     if(var66.a.hasMouse) {
                        var81 = 0;
                        var86 = 0;
                        if(var66.a.k) {
                           if(var66.a.canvas != null) {
                              Point var90;
                              var70 = (var90 = var66.a.canvas.getLocationOnScreen()).x + var66.a.width / 2;
                              var68 = var90.y + var66.a.height / 2;
                              Point var75;
                              var81 = (var75 = MouseInfo.getPointerInfo().getLocation()).x - var70;
                              var86 = -(var75.y - var68);
                              var66.a.robot.mouseMove(var70, var68);
                           } else {
                              Mouse.setCursorPosition(var66.a.width / 2, var66.a.height / 2);
                           }
                        } else {
                           var81 = Mouse.getDX();
                           var86 = Mouse.getDY();
                        }

                        byte var91 = 1;
                        if(var66.a.settings.c) {
                           var91 = -1;
                        }

                        var66.a.player.turn((float)var81, (float)(var86 * var91));
                     }

                     if(!var66.a.online) {
                        var81 = var66.a.width * 240 / var66.a.height;
                        var86 = var66.a.height * 240 / var66.a.height;
                        int var94 = Mouse.getX() * var81 / var66.a.width;
                        var70 = var86 - Mouse.getY() * var86 / var66.a.height - 1;
                        if(var66.a.level != null) {
                           float var80 = var65;
                           Renderer var82 = var66;
                           Renderer var27 = var66;
                           Player var28;
                           float var29 = (var28 = var66.a.player).xRotO + (var28.xRot - var28.xRotO) * var65;
                           float var30 = var28.yRotO + (var28.yRot - var28.yRotO) * var65;
                           Vec3D var31 = var66.a(var65);
                           float var32 = MathHelper.b(-var30 * 0.017453292F - 3.1415927F);
                           float var69 = MathHelper.a(-var30 * 0.017453292F - 3.1415927F);
                           float var74 = MathHelper.b(-var29 * 0.017453292F);
                           float var33 = MathHelper.a(-var29 * 0.017453292F);
                           float var34 = var69 * var74;
                           float var87 = var32 * var74;
                           float var36 = var66.a.gameMode.d();
                           Vec3D var71 = var31.a(var34 * var36, var33 * var36, var87 * var36);
                           var66.a.selected = var66.a.level.clip(var31, var71);
                           var74 = var36;
                           if(var66.a.selected != null) {
                              var74 = var66.a.selected.f.b(var66.a(var65));
                           }

                           var31 = var66.a(var65);
                           if(var66.a.gameMode instanceof CreativeGameMode) {
                              var36 = 32.0F;
                           } else {
                              var36 = var74;
                           }

                           var71 = var31.a(var34 * var36, var33 * var36, var87 * var36);
                           var66.g = null;
                           List var37 = var66.a.level.blockMap.getEntities(var28, var28.bb.expand(var34 * var36, var33 * var36, var87 * var36));
                           float var35 = 0.0F;

                           for(var81 = 0; var81 < var37.size(); ++var81) {
                              Entity var88;
                              if((var88 = (Entity)var37.get(var81)).isPickable()) {
                                 var74 = 0.1F;
                                 MovingObjectPosition var78;
                                 if((var78 = var88.bb.grow(var74, var74, var74).clip(var31, var71)) != null && ((var74 = var31.b(var78.f)) < var35 || var35 == 0.0F)) {
                                    var27.g = var88;
                                    var35 = var74;
                                 }
                              }
                           }

                           if(var27.g != null && !(var27.a.gameMode instanceof CreativeGameMode)) {
                              var27.a.selected = new MovingObjectPosition(var27.g);
                           }

                           int var77 = 0;

                           while(true) {
                              if(var77 >= 2) {
                                 GL11.glColorMask(true, true, true, false);
                                 break;
                              }

                              if(var82.a.settings.g) {
                                 if(var77 == 0) {
                                    GL11.glColorMask(false, true, true, false);
                                 } else {
                                    GL11.glColorMask(true, false, false, false);
                                 }
                              }

                              Player var126 = var82.a.player;
                              Level var119 = var82.a.level;
                              LevelRenderer var89 = var82.a.a;
                              ParticleManager var93 = var82.a.particleManager;
                              GL11.glViewport(0, 0, var82.a.width, var82.a.height);
                              Level var26 = var82.a.level;
                              var28 = var82.a.player;
                              var29 = 1.0F / (float)(4 - var82.a.settings.e);
                              var29 = 1.0F - (float)Math.pow((double)var29, 0.25D);
                              var30 = (float)(var26.skyColor >> 16 & 255) / 255.0F;
                              float var117 = (float)(var26.skyColor >> 8 & 255) / 255.0F;
                              var32 = (float)(var26.skyColor & 255) / 255.0F;
                              var82.i = (float)(var26.fogColor >> 16 & 255) / 255.0F;
                              var82.j = (float)(var26.fogColor >> 8 & 255) / 255.0F;
                              var82.k = (float)(var26.fogColor & 255) / 255.0F;
                              var82.i += (var30 - var82.i) * var29;
                              var82.j += (var117 - var82.j) * var29;
                              var82.k += (var32 - var82.k) * var29;
                              var82.i *= var82.b;
                              var82.j *= var82.b;
                              var82.k *= var82.b;
                              Block var73;
                              if((var73 = Block.b[var26.getTile((int)var28.x, (int)(var28.y + 0.12F), (int)var28.z)]) != null && var73.d() != LiquidType.a) {
                                 LiquidType var79;
                                 if((var79 = var73.d()) == LiquidType.b) {
                                    var82.i = 0.02F;
                                    var82.j = 0.02F;
                                    var82.k = 0.2F;
                                 } else if(var79 == LiquidType.c) {
                                    var82.i = 0.6F;
                                    var82.j = 0.1F;
                                    var82.k = 0.0F;
                                 }
                              }

                              if(var82.a.settings.g) {
                                 var74 = (var82.i * 30.0F + var82.j * 59.0F + var82.k * 11.0F) / 100.0F;
                                 var33 = (var82.i * 30.0F + var82.j * 70.0F) / 100.0F;
                                 var34 = (var82.i * 30.0F + var82.k * 70.0F) / 100.0F;
                                 var82.i = var74;
                                 var82.j = var33;
                                 var82.k = var34;
                              }

                              GL11.glClearColor(var82.i, var82.j, var82.k, 0.0F);
                              GL11.glClear(16640);
                              var82.b = 1.0F;
                              GL11.glEnable(2884);
                              var82.d = (float)(512 >> (var82.a.settings.e << 1));
                              GL11.glMatrixMode(5889);
                              GL11.glLoadIdentity();
                              var29 = 0.07F;
                              if(var82.a.settings.g) {
                                 GL11.glTranslatef((float)(-((var77 << 1) - 1)) * var29, 0.0F, 0.0F);
                              }

                              Player var116 = var82.a.player;
                              var69 = 70.0F;
                              if(var116.health <= 0) {
                                 var74 = (float)var116.deathTime + var80;
                                 var69 /= (1.0F - 500.0F / (var74 + 500.0F)) * 2.0F + 1.0F;
                              }

                              GLU.gluPerspective(var69, (float)var82.a.width / (float)var82.a.height, 0.05F, var82.d);
                              GL11.glMatrixMode(5888);
                              GL11.glLoadIdentity();
                              if(var82.a.settings.g) {
                                 GL11.glTranslatef((float)((var77 << 1) - 1) * 0.1F, 0.0F, 0.0F);
                              }

                              var82.b(var80);
                              if(var82.a.settings.f) {
                                 var82.c(var80);
                              }

                              var116 = var82.a.player;
                              GL11.glTranslatef(0.0F, 0.0F, -0.1F);
                              GL11.glRotatef(var116.xRotO + (var116.xRot - var116.xRotO) * var80, 1.0F, 0.0F, 0.0F);
                              GL11.glRotatef(var116.yRotO + (var116.yRot - var116.yRotO) * var80, 0.0F, 1.0F, 0.0F);
                              var69 = var116.xo + (var116.x - var116.xo) * var80;
                              var74 = var116.yo + (var116.y - var116.yo) * var80;
                              var33 = var116.zo + (var116.z - var116.zo) * var80;
                              GL11.glTranslatef(-var69, -var74, -var33);
                              ClippingHelper var76 = ClippingHelperImpl.a();
                              ClippingHelper var100 = var76;
                              LevelRenderer var101 = var82.a.a;

                              int var98;
                              for(var98 = 0; var98 < var101.f.length; ++var98) {
                                 var101.f[var98].a(var100);
                              }

                              var101 = var82.a.a;
                              Collections.sort(var82.a.a.e, new b(var126));
                              var98 = var101.e.size() - 1;
                              int var105;
                              if((var105 = var101.e.size()) > 3) {
                                 var105 = 3;
                              }

                              int var104;
                              for(var104 = 0; var104 < var105; ++var104) {
                                 Chunk var118;
                                 (var118 = (Chunk)var101.e.remove(var98 - var104)).a();
                                 var118.c = false;
                              }

                              var82.b();
                              GL11.glEnable(2912);
                              var89.a(var126, 0);
                              int var83;
                              int var110;
                              ShapeRender var115;
                              int var114;
                              int var125;
                              int var122;
                              int var120;
                              if(var119.isSolid(var126.x, var126.y, var126.z, 0.1F)) {
                                 var120 = (int)var126.x;
                                 var83 = (int)var126.y;
                                 var110 = (int)var126.z;

                                 for(var122 = var120 - 1; var122 <= var120 + 1; ++var122) {
                                    for(var125 = var83 - 1; var125 <= var83 + 1; ++var125) {
                                       for(int var38 = var110 - 1; var38 <= var110 + 1; ++var38) {
                                          var105 = var38;
                                          var98 = var125;
                                          int var99 = var122;
                                          if((var104 = var89.a.getTile(var122, var125, var38)) != 0 && Block.b[var104].c()) {
                                             GL11.glColor4f(0.2F, 0.2F, 0.2F, 1.0F);
                                             GL11.glDepthFunc(513);
                                             var115 = ShapeRender.a;
                                             ShapeRender.a.b();

                                             for(var114 = 0; var114 < 6; ++var114) {
                                                Block.b[var104].a(var115, var99, var98, var105, var114);
                                             }

                                             var115.a();
                                             GL11.glCullFace(1028);
                                             var115.b();

                                             for(var114 = 0; var114 < 6; ++var114) {
                                                Block.b[var104].a(var115, var99, var98, var105, var114);
                                             }

                                             var115.a();
                                             GL11.glCullFace(1029);
                                             GL11.glDepthFunc(515);
                                          }
                                       }
                                    }
                                 }
                              }

                              var82.a(true);
                              Vec3D var103 = var82.a(var80);
                              var89.a.blockMap.render$7127a881(var103, var76, var89.b, var80);
                              var82.a(false);
                              var82.b();
                              float var107 = var80;
                              ParticleManager var96 = var93;
                              var29 = -MathHelper.b(var126.yRot * 3.1415927F / 180.0F);
                              var117 = -(var30 = -MathHelper.a(var126.yRot * 3.1415927F / 180.0F)) * MathHelper.a(var126.xRot * 3.1415927F / 180.0F);
                              var32 = var29 * MathHelper.a(var126.xRot * 3.1415927F / 180.0F);
                              var69 = MathHelper.b(var126.xRot * 3.1415927F / 180.0F);

                              for(var83 = 0; var83 < 2; ++var83) {
                                 if(var96.particles[var83].size() != 0) {
                                    var110 = 0;
                                    if(var83 == 0) {
                                       var110 = var96.textureManager.a("/particles.png");
                                    }

                                    if(var83 == 1) {
                                       var110 = var96.textureManager.a("/terrain.png");
                                    }

                                    GL11.glBindTexture(3553, var110);
                                    ShapeRender var121 = ShapeRender.a;
                                    ShapeRender.a.b();

                                    for(var120 = 0; var120 < var96.particles[var83].size(); ++var120) {
                                       ((Particle)var96.particles[var83].get(var120)).render$12562f50(var121, var107, var29, var69, var30, var117, var32);
                                    }

                                    var121.a();
                                 }
                              }

                              GL11.glBindTexture(3553, var89.b.a("/rock.png"));
                              GL11.glEnable(3553);
                              GL11.glCallList(var89.c);
                              var82.b();
                              var101 = var89;
                              GL11.glBindTexture(3553, var89.b.a("/clouds.png"));
                              GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                              var107 = (float)(var89.a.cloudColor >> 16 & 255) / 255.0F;
                              var29 = (float)(var89.a.cloudColor >> 8 & 255) / 255.0F;
                              var30 = (float)(var89.a.cloudColor & 255) / 255.0F;
                              if(var89.g.settings.g) {
                                 var117 = (var107 * 30.0F + var29 * 59.0F + var30 * 11.0F) / 100.0F;
                                 var32 = (var107 * 30.0F + var29 * 70.0F) / 100.0F;
                                 var69 = (var107 * 30.0F + var30 * 70.0F) / 100.0F;
                                 var107 = var117;
                                 var29 = var32;
                                 var30 = var69;
                              }

                              var115 = ShapeRender.a;
                              var74 = 0.0F;
                              var33 = 4.8828125E-4F;
                              var74 = (float)(var89.a.depth + 2);
                              var34 = ((float)var89.h + var80) * var33 * 0.03F;
                              var35 = 0.0F;
                              var115.b();
                              var115.a(var107, var29, var30);

                              for(var86 = -2048; var86 < var101.a.width + 2048; var86 += 512) {
                                 for(var125 = -2048; var125 < var101.a.height + 2048; var125 += 512) {
                                    var115.a((float)var86, var74, (float)(var125 + 512), (float)var86 * var33 + var34, (float)(var125 + 512) * var33);
                                    var115.a((float)(var86 + 512), var74, (float)(var125 + 512), (float)(var86 + 512) * var33 + var34, (float)(var125 + 512) * var33);
                                    var115.a((float)(var86 + 512), var74, (float)var125, (float)(var86 + 512) * var33 + var34, (float)var125 * var33);
                                    var115.a((float)var86, var74, (float)var125, (float)var86 * var33 + var34, (float)var125 * var33);
                                    var115.a((float)var86, var74, (float)var125, (float)var86 * var33 + var34, (float)var125 * var33);
                                    var115.a((float)(var86 + 512), var74, (float)var125, (float)(var86 + 512) * var33 + var34, (float)var125 * var33);
                                    var115.a((float)(var86 + 512), var74, (float)(var125 + 512), (float)(var86 + 512) * var33 + var34, (float)(var125 + 512) * var33);
                                    var115.a((float)var86, var74, (float)(var125 + 512), (float)var86 * var33 + var34, (float)(var125 + 512) * var33);
                                 }
                              }

                              var115.a();
                              GL11.glDisable(3553);
                              var115.b();
                              var34 = (float)(var101.a.skyColor >> 16 & 255) / 255.0F;
                              var35 = (float)(var101.a.skyColor >> 8 & 255) / 255.0F;
                              var87 = (float)(var101.a.skyColor & 255) / 255.0F;
                              if(var101.g.settings.g) {
                                 var36 = (var34 * 30.0F + var35 * 59.0F + var87 * 11.0F) / 100.0F;
                                 var69 = (var34 * 30.0F + var35 * 70.0F) / 100.0F;
                                 var74 = (var34 * 30.0F + var87 * 70.0F) / 100.0F;
                                 var34 = var36;
                                 var35 = var69;
                                 var87 = var74;
                              }

                              var115.a(var34, var35, var87);
                              var74 = (float)(var101.a.depth + 10);

                              for(var125 = -2048; var125 < var101.a.width + 2048; var125 += 512) {
                                 for(var68 = -2048; var68 < var101.a.height + 2048; var68 += 512) {
                                    var115.b((float)var125, var74, (float)var68);
                                    var115.b((float)(var125 + 512), var74, (float)var68);
                                    var115.b((float)(var125 + 512), var74, (float)(var68 + 512));
                                    var115.b((float)var125, var74, (float)(var68 + 512));
                                 }
                              }

                              var115.a();
                              GL11.glEnable(3553);
                              var82.b();
                              int var108;
                              if(var82.a.selected != null) {
                                 GL11.glDisable(3008);
                                 MovingObjectPosition var10001 = var82.a.selected;
                                 var105 = var126.inventory.getSelected();
                                 boolean var106 = false;
                                 MovingObjectPosition var102 = var10001;
                                 var101 = var89;
                                 ShapeRender var113 = ShapeRender.a;
                                 GL11.glEnable(3042);
                                 GL11.glEnable(3008);
                                 GL11.glBlendFunc(770, 1);
                                 GL11.glColor4f(1.0F, 1.0F, 1.0F, (MathHelper.a((float)System.currentTimeMillis() / 100.0F) * 0.2F + 0.4F) * 0.5F);
                                 if(var89.i > 0.0F) {
                                    GL11.glBlendFunc(774, 768);
                                    var108 = var89.b.a("/terrain.png");
                                    GL11.glBindTexture(3553, var108);
                                    GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
                                    GL11.glPushMatrix();
                                    Block var10000 = (var114 = var89.a.getTile(var102.b, var102.c, var102.d)) > 0?Block.b[var114]:null;
                                    var73 = var10000;
                                    var74 = (var10000.ae + var73.ah) / 2.0F;
                                    var33 = (var73.af + var73.ai) / 2.0F;
                                    var34 = (var73.ag + var73.aj) / 2.0F;
                                    GL11.glTranslatef((float)var102.b + var74, (float)var102.c + var33, (float)var102.d + var34);
                                    var35 = 1.01F;
                                    GL11.glScalef(1.01F, var35, var35);
                                    GL11.glTranslatef(-((float)var102.b + var74), -((float)var102.c + var33), -((float)var102.d + var34));
                                    var113.b();
                                    var113.c();
                                    GL11.glDepthMask(false);
                                    if(var73 == null) {
                                       var73 = Block.e;
                                    }

                                    for(var86 = 0; var86 < 6; ++var86) {
                                       var73.a(var113, var102.b, var102.c, var102.d, var86, 240 + (int)(var101.i * 10.0F));
                                    }

                                    var113.a();
                                    GL11.glDepthMask(true);
                                    GL11.glPopMatrix();
                                 }

                                 GL11.glDisable(3042);
                                 GL11.glDisable(3008);
                                 var10001 = var82.a.selected;
                                 var126.inventory.getSelected();
                                 var106 = false;
                                 var102 = var10001;
                                 GL11.glEnable(3042);
                                 GL11.glBlendFunc(770, 771);
                                 GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.4F);
                                 GL11.glLineWidth(2.0F);
                                 GL11.glDisable(3553);
                                 GL11.glDepthMask(false);
                                 var29 = 0.002F;
                                 if((var104 = var89.a.getTile(var102.b, var102.c, var102.d)) > 0) {
                                    AABB var111 = Block.b[var104].a(var102.b, var102.c, var102.d).grow(var29, var29, var29);
                                    GL11.glBegin(3);
                                    GL11.glVertex3f(var111.x0, var111.y0, var111.z0);
                                    GL11.glVertex3f(var111.x1, var111.y0, var111.z0);
                                    GL11.glVertex3f(var111.x1, var111.y0, var111.z1);
                                    GL11.glVertex3f(var111.x0, var111.y0, var111.z1);
                                    GL11.glVertex3f(var111.x0, var111.y0, var111.z0);
                                    GL11.glEnd();
                                    GL11.glBegin(3);
                                    GL11.glVertex3f(var111.x0, var111.y1, var111.z0);
                                    GL11.glVertex3f(var111.x1, var111.y1, var111.z0);
                                    GL11.glVertex3f(var111.x1, var111.y1, var111.z1);
                                    GL11.glVertex3f(var111.x0, var111.y1, var111.z1);
                                    GL11.glVertex3f(var111.x0, var111.y1, var111.z0);
                                    GL11.glEnd();
                                    GL11.glBegin(1);
                                    GL11.glVertex3f(var111.x0, var111.y0, var111.z0);
                                    GL11.glVertex3f(var111.x0, var111.y1, var111.z0);
                                    GL11.glVertex3f(var111.x1, var111.y0, var111.z0);
                                    GL11.glVertex3f(var111.x1, var111.y1, var111.z0);
                                    GL11.glVertex3f(var111.x1, var111.y0, var111.z1);
                                    GL11.glVertex3f(var111.x1, var111.y1, var111.z1);
                                    GL11.glVertex3f(var111.x0, var111.y0, var111.z1);
                                    GL11.glVertex3f(var111.x0, var111.y1, var111.z1);
                                    GL11.glEnd();
                                 }

                                 GL11.glDepthMask(true);
                                 GL11.glEnable(3553);
                                 GL11.glDisable(3042);
                                 GL11.glEnable(3008);
                              }

                              GL11.glBlendFunc(770, 771);
                              var82.b();
                              GL11.glEnable(3553);
                              GL11.glEnable(3042);
                              GL11.glBindTexture(3553, var89.b.a("/water.png"));
                              GL11.glCallList(var89.c + 1);
                              GL11.glDisable(3042);
                              GL11.glEnable(3042);
                              GL11.glColorMask(false, false, false, false);
                              var120 = var89.a(var126, 1);
                              GL11.glColorMask(true, true, true, true);
                              if(var82.a.settings.g) {
                                 if(var77 == 0) {
                                    GL11.glColorMask(false, true, true, false);
                                 } else {
                                    GL11.glColorMask(true, false, false, false);
                                 }
                              }

                              if(var120 > 0) {
                                 GL11.glBindTexture(3553, var89.b.a("/terrain.png"));
                                 GL11.glCallLists(var89.d);
                              }

                              GL11.glDepthMask(true);
                              GL11.glDisable(3042);
                              GL11.glDisable(2912);
                              if(var82.a.raining) {
                                 float var97 = var80;
                                 var27 = var82;
                                 var28 = var82.a.player;
                                 Level var109 = var82.a.level;
                                 var104 = (int)var28.x;
                                 var108 = (int)var28.y;
                                 var114 = (int)var28.z;
                                 ShapeRender var84 = ShapeRender.a;
                                 GL11.glDisable(2884);
                                 GL11.glNormal3f(0.0F, 1.0F, 0.0F);
                                 GL11.glEnable(3042);
                                 GL11.glBlendFunc(770, 771);
                                 GL11.glBindTexture(3553, var82.a.textureManager.a("/rain.png"));

                                 for(var110 = var104 - 5; var110 <= var104 + 5; ++var110) {
                                    for(var122 = var114 - 5; var122 <= var114 + 5; ++var122) {
                                       var120 = var109.getHighestTile(var110, var122);
                                       var86 = var108 - 5;
                                       var125 = var108 + 5;
                                       if(var86 < var120) {
                                          var86 = var120;
                                       }

                                       if(var125 < var120) {
                                          var125 = var120;
                                       }

                                       if(var86 != var125) {
                                          var74 = ((float)((var27.f + var110 * 3121 + var122 * 418711) % 32) + var97) / 32.0F;
                                          float var124 = (float)var110 + 0.5F - var28.x;
                                          var35 = (float)var122 + 0.5F - var28.z;
                                          float var92 = MathHelper.c(var124 * var124 + var35 * var35) / (float)5;
                                          GL11.glColor4f(1.0F, 1.0F, 1.0F, (1.0F - var92 * var92) * 0.7F);
                                          var84.b();
                                          var84.a((float)var110, (float)var86, (float)var122, 0.0F, (float)var86 * 2.0F / 8.0F + var74 * 2.0F);
                                          var84.a((float)(var110 + 1), (float)var86, (float)(var122 + 1), 2.0F, (float)var86 * 2.0F / 8.0F + var74 * 2.0F);
                                          var84.a((float)(var110 + 1), (float)var125, (float)(var122 + 1), 2.0F, (float)var125 * 2.0F / 8.0F + var74 * 2.0F);
                                          var84.a((float)var110, (float)var125, (float)var122, 0.0F, (float)var125 * 2.0F / 8.0F + var74 * 2.0F);
                                          var84.a((float)var110, (float)var86, (float)(var122 + 1), 0.0F, (float)var86 * 2.0F / 8.0F + var74 * 2.0F);
                                          var84.a((float)(var110 + 1), (float)var86, (float)var122, 2.0F, (float)var86 * 2.0F / 8.0F + var74 * 2.0F);
                                          var84.a((float)(var110 + 1), (float)var125, (float)var122, 2.0F, (float)var125 * 2.0F / 8.0F + var74 * 2.0F);
                                          var84.a((float)var110, (float)var125, (float)(var122 + 1), 0.0F, (float)var125 * 2.0F / 8.0F + var74 * 2.0F);
                                          var84.a();
                                       }
                                    }
                                 }

                                 GL11.glEnable(2884);
                                 GL11.glDisable(3042);
                              }

                              if(var82.g != null) {
                                 var82.g.renderHover(var82.a.textureManager, var80);
                              }

                              GL11.glClear(256);
                              GL11.glLoadIdentity();
                              if(var82.a.settings.g) {
                                 GL11.glTranslatef((float)((var77 << 1) - 1) * 0.1F, 0.0F, 0.0F);
                              }

                              var82.b(var80);
                              if(var82.a.settings.f) {
                                 var82.c(var80);
                              }

                              HeldBlock var112 = var82.e;
                              var117 = var82.e.d + (var112.c - var112.d) * var80;
                              var116 = var112.a.player;
                              GL11.glPushMatrix();
                              GL11.glRotatef(var116.xRotO + (var116.xRot - var116.xRotO) * var80, 1.0F, 0.0F, 0.0F);
                              GL11.glRotatef(var116.yRotO + (var116.yRot - var116.yRotO) * var80, 0.0F, 1.0F, 0.0F);
                              var112.a.renderer.a(true);
                              GL11.glPopMatrix();
                              GL11.glPushMatrix();
                              var69 = 0.8F;
                              if(var112.f) {
                                 var33 = MathHelper.a((var74 = ((float)var112.e + var80) / 7.0F) * 3.1415927F);
                                 GL11.glTranslatef(-MathHelper.a(MathHelper.c(var74) * 3.1415927F) * 0.4F, MathHelper.a(MathHelper.c(var74) * 3.1415927F * 2.0F) * 0.2F, -var33 * 0.2F);
                              }

                              GL11.glTranslatef(0.7F * var69, -0.65F * var69 - (1.0F - var117) * 0.6F, -0.9F * var69);
                              GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                              GL11.glEnable(2977);
                              if(var112.f) {
                                 var33 = MathHelper.a((var74 = ((float)var112.e + var80) / 7.0F) * var74 * 3.1415927F);
                                 GL11.glRotatef(MathHelper.a(MathHelper.c(var74) * 3.1415927F) * 80.0F, 0.0F, 1.0F, 0.0F);
                                 GL11.glRotatef(-var33 * 20.0F, 1.0F, 0.0F, 0.0F);
                              }

                              GL11.glColor4f(var74 = var112.a.level.getBrightness((int)var116.x, (int)var116.y, (int)var116.z), var74, var74, 1.0F);
                              ShapeRender var123 = ShapeRender.a;
                              if(var112.b != null) {
                                 var34 = 0.4F;
                                 GL11.glScalef(0.4F, var34, var34);
                                 GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
                                 GL11.glBindTexture(3553, var112.a.textureManager.a("/terrain.png"));
                                 var112.b.b(var123);
                              } else {
                                 var116.bindTexture$4211e026(var112.a.textureManager);
                                 GL11.glScalef(1.0F, -1.0F, -1.0F);
                                 GL11.glTranslatef(0.0F, 0.2F, 0.0F);
                                 GL11.glRotatef(-120.0F, 0.0F, 0.0F, 1.0F);
                                 GL11.glScalef(1.0F, 1.0F, 1.0F);
                                 var34 = 0.0625F;
                                 ModelRenderer var127;
                                 if(!(var127 = var112.a.player.getModel().f).i) {
                                    var127.b(var34);
                                 }

                                 GL11.glCallList(var127.j);
                              }

                              GL11.glDisable(2977);
                              GL11.glPopMatrix();
                              var112.a.renderer.a(false);
                              if(!var82.a.settings.g) {
                                 break;
                              }

                              ++var77;
                           }

                           var66.a.hud.a(var65, var66.a.currentScreen != null, var94, var70);
                        } else {
                           GL11.glViewport(0, 0, var66.a.width, var66.a.height);
                           GL11.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
                           GL11.glClear(16640);
                           GL11.glMatrixMode(5889);
                           GL11.glLoadIdentity();
                           GL11.glMatrixMode(5888);
                           GL11.glLoadIdentity();
                           var66.a();
                        }

                        if(var66.a.currentScreen != null) {
                           var66.a.currentScreen.a(var94, var70);
                        }

                        Thread.yield();
                        Display.update();
                     }
                  }

                  if(this.settings.h) {
                     Thread.sleep(5L);
                  }

                  a("Post render");
                  ++var15;
               } catch (Exception var58) {
                  this.a((PopUpScreen)(new ErrorScreen("Client error", "The game broke! [" + var58 + "]")));
                  var58.printStackTrace();
               }

               while(System.currentTimeMillis() >= var13 + 1000L) {
                  this.debugInfo = var15 + " fps, " + Chunk.a + " chunk updates";
                  Chunk.a = 0;
                  var13 += 1000L;
                  var15 = 0;
               }
            }
         }

         return;
      } catch (StopGameException var59) {
         ;
      } catch (Exception var60) {
         var60.printStackTrace();
         return;
      } finally {
         this.a();
      }

   }

   public final void b() {
      if(!this.hasMouse) {
         this.hasMouse = true;
         if(this.k) {
            try {
               Mouse.setNativeCursor(this.cursor);
               Mouse.setCursorPosition(this.width / 2, this.height / 2);
            } catch (LWJGLException var2) {
               var2.printStackTrace();
            }

            if(this.canvas == null) {
               this.canvas.requestFocus();
            }
         } else {
            Mouse.setGrabbed(true);
         }

         this.a((PopUpScreen)null);
         this.lastClick = this.ticks + 10000;
      }
   }

   public final void c() {
      if(this.currentScreen == null) {
         this.a((PopUpScreen)(new PauseScreen()));
      }
   }

   private void b(int var1) {
      if(var1 != 0 || this.blockHitTime <= 0) {
         HeldBlock var2;
         if(var1 == 0) {
            var2 = this.renderer.e;
            this.renderer.e.e = -1;
            var2.f = true;
         }

         int var3;
         if(var1 == 1 && (var3 = this.player.inventory.getSelected()) > 0 && this.gameMode.a(this.player, var3)) {
            var2 = this.renderer.e;
            this.renderer.e.c = 0.0F;
         } else if(this.selected == null) {
            if(var1 == 0 && !(this.gameMode instanceof CreativeGameMode)) {
               this.blockHitTime = 10;
            }

         } else {
            if(this.selected.a == 1) {
               if(var1 == 0) {
                  this.selected.g.hurt(this.player, 4);
                  return;
               }
            } else if(this.selected.a == 0) {
               var3 = this.selected.b;
               int var4 = this.selected.c;
               int var5 = this.selected.d;
               if(var1 != 0) {
                  if(this.selected.e == 0) {
                     --var4;
                  }

                  if(this.selected.e == 1) {
                     ++var4;
                  }

                  if(this.selected.e == 2) {
                     --var5;
                  }

                  if(this.selected.e == 3) {
                     ++var5;
                  }

                  if(this.selected.e == 4) {
                     --var3;
                  }

                  if(this.selected.e == 5) {
                     ++var3;
                  }
               }

               Block var6 = Block.b[this.level.getTile(var3, var4, var5)];
               if(var1 == 0) {
                  if(var6 != Block.k || this.player.userType >= 100) {
                     this.gameMode.a(var3, var4, var5);
                     return;
                  }
               } else {
                  int var10;
                  if((var10 = this.player.inventory.getSelected()) <= 0) {
                     return;
                  }

                  Block var8;
                  AABB var9;
                  if(((var8 = Block.b[this.level.getTile(var3, var4, var5)]) == null || var8 == Block.l || var8 == Block.m || var8 == Block.n || var8 == Block.o) && ((var9 = Block.b[var10].b(var3, var4, var5)) == null || (this.player.bb.intersects(var9)?false:this.level.isFree(var9)))) {
                     if(!this.gameMode.a(var10)) {
                        return;
                     }

                     if(this.d()) {
                        this.networkManager.a(var3, var4, var5, var1, var10);
                     }

                     this.level.netSetTile(var3, var4, var5, var10);
                     var2 = this.renderer.e;
                     this.renderer.e.c = 0.0F;
                     Block.b[var10].b(this.level, var3, var4, var5);
                  }
               }
            }

         }
      }
   }

   private void e() {
      if(this.audioPlayer != null) {
         i var1 = this.audioPlayer;
         k var2 = this.audioManager;
         if(System.currentTimeMillis() > var2.c && var2.a(var1, "calm")) {
            var2.c = System.currentTimeMillis() + (long)var2.b.nextInt(900000) + 300000L;
         }
      }

      this.gameMode.e();
      HUDScreen var17 = this.hud;
      ++this.hud.ticks;

      int var16;
      for(var16 = 0; var16 < var17.chatHistory.size(); ++var16) {
         ++((ChatLine)var17.chatHistory.get(var16)).time;
      }

      GL11.glBindTexture(3553, this.textureManager.a("/terrain.png"));
      TextureManager var19 = this.textureManager;

      for(var16 = 0; var16 < var19.e.size(); ++var16) {
         TextureFX var3;
         (var3 = (TextureFX)var19.e.get(var16)).anaglyph = var19.f.g;
         var3.a();
         var19.d.clear();
         var19.d.put(var3.textureData);
         var19.d.position(0).limit(var3.textureData.length);
         GL11.glTexSubImage2D(3553, 0, var3.textureID % 16 << 4, var3.textureID / 16 << 4, 16, 16, 6408, 5121, var19.d);
      }

      int var4;
      int var8;
      int var40;
      int var46;
      int var45;
      if(this.networkManager != null && !(this.currentScreen instanceof ErrorScreen)) {
         if(!this.networkManager.a()) {
            this.progressBar.a("Connecting..");
            this.progressBar.a(0);
         } else {
            NetworkManager var20 = this.networkManager;
            if(this.networkManager.d) {
               NetworkHandler var18 = var20.b;
               if(var20.b.a) {
                  try {
                     NetworkHandler var22 = var20.b;
                     var20.b.b.read(var22.c);
                     var4 = 0;

                     while(var22.c.position() > 0 && var4++ != 100) {
                        var22.c.flip();
                        byte var5 = var22.c.get(0);
                        PacketType var6;
                        if((var6 = PacketType.a[var5]) == null) {
                           throw new IOException("Bad command: " + var5);
                        }

                        if(var22.c.remaining() < var6.q + 1) {
                           var22.c.compact();
                           break;
                        }

                        var22.c.get();
                        Object[] var7 = new Object[var6.s.length];

                        for(var8 = 0; var8 < var7.length; ++var8) {
                           var7[var8] = var22.a(var6.s[var8]);
                        }

                        NetworkManager var42 = var22.e;
                        if(var22.e.d) {
                           if(var6 == PacketType.b) {
                              var42.c.progressBar.a(var7[1].toString());
                              var42.c.progressBar.b(var7[2].toString());
                              var42.c.player.userType = ((Byte)var7[3]).byteValue();
                           } else if(var6 == PacketType.c) {
                              var42.c.a((Level)null);
                              var42.a = new ByteArrayOutputStream();
                           } else if(var6 == PacketType.d) {
                              short var11 = ((Short)var7[0]).shortValue();
                              byte[] var12 = (byte[])((byte[])var7[1]);
                              byte var13 = ((Byte)var7[2]).byteValue();
                              var42.c.progressBar.a(var13);
                              var42.a.write(var12, 0, var11);
                           } else if(var6 == PacketType.e) {
                              try {
                                 var42.a.close();
                              } catch (IOException var14) {
                                 var14.printStackTrace();
                              }

                              byte[] var51 = LevelIO.b(new ByteArrayInputStream(var42.a.toByteArray()));
                              var42.a = null;
                              short var55 = ((Short)var7[0]).shortValue();
                              short var63 = ((Short)var7[1]).shortValue();
                              short var21 = ((Short)var7[2]).shortValue();
                              Level var30;
                              (var30 = new Level()).setNetworkMode(true);
                              var30.setData(var55, var63, var21, var51);
                              var42.c.a(var30);
                              var42.c.online = false;
                              var42.e = true;
                           } else if(var6 == PacketType.g) {
                              if(var42.c.level != null) {
                                 var42.c.level.netSetTile(((Short)var7[0]).shortValue(), ((Short)var7[1]).shortValue(), ((Short)var7[2]).shortValue(), ((Byte)var7[3]).byteValue());
                              }
                           } else {
                              byte var9;
                              String var34;
                              NetworkPlayer var33;
                              short var36;
                              short var10004;
                              byte var10001;
                              short var47;
                              short var10003;
                              if(var6 == PacketType.h) {
                                 var10001 = ((Byte)var7[0]).byteValue();
                                 String var10002 = (String)var7[1];
                                 var10003 = ((Short)var7[2]).shortValue();
                                 var10004 = ((Short)var7[3]).shortValue();
                                 short var10005 = ((Short)var7[4]).shortValue();
                                 byte var10006 = ((Byte)var7[5]).byteValue();
                                 byte var58 = ((Byte)var7[6]).byteValue();
                                 var9 = var10006;
                                 short var10 = var10005;
                                 var47 = var10004;
                                 var36 = var10003;
                                 var34 = var10002;
                                 var5 = var10001;
                                 if(var5 >= 0) {
                                    var9 = (byte)(var9 + 128);
                                    var47 = (short)(var47 - 22);
                                    var33 = new NetworkPlayer(var42.c, var5, var34, var36, var47, var10, (float)(var9 * 360) / 256.0F, (float)(var58 * 360) / 256.0F);
                                    var42.f.put(Byte.valueOf(var5), var33);
                                    var42.c.level.addEntity(var33);
                                 } else {
                                    var42.c.level.setSpawnPos(var36 / 32, var47 / 32, var10 / 32, (float)(var9 * 320 / 256));
                                    var42.c.player.moveTo((float)var36 / 32.0F, (float)var47 / 32.0F, (float)var10 / 32.0F, (float)(var9 * 360) / 256.0F, (float)(var58 * 360) / 256.0F);
                                 }
                              } else {
                                 byte var53;
                                 NetworkPlayer var61;
                                 byte var69;
                                 if(var6 == PacketType.i) {
                                    var10001 = ((Byte)var7[0]).byteValue();
                                    short var66 = ((Short)var7[1]).shortValue();
                                    var10003 = ((Short)var7[2]).shortValue();
                                    var10004 = ((Short)var7[3]).shortValue();
                                    var69 = ((Byte)var7[4]).byteValue();
                                    var9 = ((Byte)var7[5]).byteValue();
                                    var53 = var69;
                                    var47 = var10004;
                                    var36 = var10003;
                                    short var38 = var66;
                                    var5 = var10001;
                                    if(var5 < 0) {
                                       var42.c.player.moveTo((float)var38 / 32.0F, (float)var36 / 32.0F, (float)var47 / 32.0F, (float)(var53 * 360) / 256.0F, (float)(var9 * 360) / 256.0F);
                                    } else {
                                       var53 = (byte)(var53 + 128);
                                       var36 = (short)(var36 - 22);
                                       if((var61 = (NetworkPlayer)var42.f.get(Byte.valueOf(var5))) != null) {
                                          var61.teleport(var38, var36, var47, (float)(var53 * 360) / 256.0F, (float)(var9 * 360) / 256.0F);
                                       }
                                    }
                                 } else {
                                    byte var37;
                                    byte var44;
                                    byte var49;
                                    byte var65;
                                    byte var67;
                                    if(var6 == PacketType.j) {
                                       var10001 = ((Byte)var7[0]).byteValue();
                                       var67 = ((Byte)var7[1]).byteValue();
                                       var65 = ((Byte)var7[2]).byteValue();
                                       byte var64 = ((Byte)var7[3]).byteValue();
                                       var69 = ((Byte)var7[4]).byteValue();
                                       var9 = ((Byte)var7[5]).byteValue();
                                       var53 = var69;
                                       var49 = var64;
                                       var44 = var65;
                                       var37 = var67;
                                       var5 = var10001;
                                       if(var5 >= 0) {
                                          var53 = (byte)(var53 + 128);
                                          if((var61 = (NetworkPlayer)var42.f.get(Byte.valueOf(var5))) != null) {
                                             var61.queue(var37, var44, var49, (float)(var53 * 360) / 256.0F, (float)(var9 * 360) / 256.0F);
                                          }
                                       }
                                    } else if(var6 == PacketType.l) {
                                       var10001 = ((Byte)var7[0]).byteValue();
                                       var67 = ((Byte)var7[1]).byteValue();
                                       var44 = ((Byte)var7[2]).byteValue();
                                       var37 = var67;
                                       var5 = var10001;
                                       if(var5 >= 0) {
                                          var37 = (byte)(var37 + 128);
                                          NetworkPlayer var54;
                                          if((var54 = (NetworkPlayer)var42.f.get(Byte.valueOf(var5))) != null) {
                                             var54.queue((float)(var37 * 360) / 256.0F, (float)(var44 * 360) / 256.0F);
                                          }
                                       }
                                    } else if(var6 == PacketType.k) {
                                       var10001 = ((Byte)var7[0]).byteValue();
                                       var67 = ((Byte)var7[1]).byteValue();
                                       var65 = ((Byte)var7[2]).byteValue();
                                       var49 = ((Byte)var7[3]).byteValue();
                                       var44 = var65;
                                       var37 = var67;
                                       var5 = var10001;
                                       NetworkPlayer var59;
                                       if(var5 >= 0 && (var59 = (NetworkPlayer)var42.f.get(Byte.valueOf(var5))) != null) {
                                          var59.queue(var37, var44, var49);
                                       }
                                    } else if(var6 == PacketType.m) {
                                       var5 = ((Byte)var7[0]).byteValue();
                                       if(var5 >= 0 && (var33 = (NetworkPlayer)var42.f.remove(Byte.valueOf(var5))) != null) {
                                          var33.clear();
                                          var42.c.level.removeEntity(var33);
                                       }
                                    } else if(var6 == PacketType.n) {
                                       var10001 = ((Byte)var7[0]).byteValue();
                                       var34 = (String)var7[1];
                                       var5 = var10001;
                                       if(var5 < 0) {
                                          var42.c.hud.a("&e" + var34);
                                       } else {
                                          var42.f.get(Byte.valueOf(var5));
                                          var42.c.hud.a(var34);
                                       }
                                    } else if(var6 == PacketType.o) {
                                       var42.b.a();
                                       var42.c.a((PopUpScreen)(new ErrorScreen("Connection lost", (String)var7[0])));
                                    } else if(var6 == PacketType.p) {
                                       var42.c.player.userType = ((Byte)var7[0]).byteValue();
                                    }
                                 }
                              }
                           }
                        }

                        if(!var22.a) {
                           break;
                        }

                        var22.c.compact();
                     }

                     if(var22.d.position() > 0) {
                        var22.d.flip();
                        var22.b.write(var22.d);
                        var22.d.compact();
                     }
                  } catch (Exception var15) {
                     var20.c.a((PopUpScreen)(new ErrorScreen("Disconnected!", "You\'ve lost connection to the server")));
                     var20.c.online = false;
                     var15.printStackTrace();
                     var20.b.a();
                     var20.c.networkManager = null;
                  }
               }
            }

            Player var28 = this.player;
            var20 = this.networkManager;
            if(this.networkManager.e) {
               int var24 = (int)(var28.x * 32.0F);
               var4 = (int)(var28.y * 32.0F);
               var40 = (int)(var28.z * 32.0F);
               var46 = (int)(var28.yRot * 256.0F / 360.0F) & 255;
               var45 = (int)(var28.xRot * 256.0F / 360.0F) & 255;
               var20.b.a(PacketType.i, new Object[]{Integer.valueOf(-1), Integer.valueOf(var24), Integer.valueOf(var4), Integer.valueOf(var40), Integer.valueOf(var46), Integer.valueOf(var45)});
            }
         }
      }

      if(this.currentScreen == null && this.player != null && this.player.health <= 0) {
         this.a((PopUpScreen)null);
      }

      if(this.currentScreen == null || this.currentScreen.e) {
         int var25;
         while(Mouse.next()) {
            if((var25 = Mouse.getEventDWheel()) != 0) {
               this.player.inventory.swapPaint(var25);
            }

            if(this.currentScreen == null) {
               if(!this.hasMouse && Mouse.getEventButtonState()) {
                  this.b();
               } else {
                  if(Mouse.getEventButton() == 0 && Mouse.getEventButtonState()) {
                     this.b(0);
                     this.lastClick = this.ticks;
                  }

                  if(Mouse.getEventButton() == 1 && Mouse.getEventButtonState()) {
                     this.b(1);
                     this.lastClick = this.ticks;
                  }

                  if(Mouse.getEventButton() == 2 && Mouse.getEventButtonState() && this.selected != null) {
                     if((var16 = this.level.getTile(this.selected.b, this.selected.c, this.selected.d)) == Block.f.ac) {
                        var16 = Block.g.ac;
                     }

                     if(var16 == Block.U.ac) {
                        var16 = Block.V.ac;
                     }

                     if(var16 == Block.k.ac) {
                        var16 = Block.e.ac;
                     }

                     this.player.inventory.grabTexture(var16, this.gameMode instanceof CreativeGameMode);
                  }
               }
            }

            if(this.currentScreen != null) {
               this.currentScreen.e();
            }
         }

         if(this.blockHitTime > 0) {
            --this.blockHitTime;
         }

         while(Keyboard.next()) {
            this.player.setKey(Keyboard.getEventKey(), Keyboard.getEventKeyState());
            if(Keyboard.getEventKeyState()) {
               if(this.currentScreen != null) {
                  this.currentScreen.f();
               }

               if(this.currentScreen == null) {
                  if(Keyboard.getEventKey() == 1) {
                     this.c();
                  }

                  if(this.gameMode instanceof CreativeGameMode) {
                     if(Keyboard.getEventKey() == this.settings.r.key) {
                        this.player.resetPos();
                     }

                     if(Keyboard.getEventKey() == this.settings.q.key) {
                        this.level.setSpawnPos((int)this.player.x, (int)this.player.y, (int)this.player.z, this.player.yRot);
                        this.player.resetPos();
                     }
                  }

                  Keyboard.getEventKey();
                  if(Keyboard.getEventKey() == 63) {
                     this.raining = !this.raining;
                  }

                  if(Keyboard.getEventKey() == 15 && this.gameMode instanceof SurvivalGameMode && this.player.arrows > 0) {
                     this.level.addEntity(new Arrow(this.level, this.player, this.player.x, this.player.y, this.player.z, this.player.yRot, this.player.xRot, 1.2F));
                     --this.player.arrows;
                  }

                  if(Keyboard.getEventKey() == this.settings.n.key) {
                     this.gameMode.a();
                  }

                  if(Keyboard.getEventKey() == this.settings.o.key && this.networkManager != null && this.networkManager.a()) {
                     this.player.releaseAllKeys();
                     this.a((PopUpScreen)(new ChatInputScreen()));
                  }
               }

               for(var25 = 0; var25 < 9; ++var25) {
                  if(Keyboard.getEventKey() == var25 + 2) {
                     this.player.inventory.selected = var25;
                  }
               }

               if(Keyboard.getEventKey() == this.settings.p.key) {
                  this.settings.b(4, !Keyboard.isKeyDown(42) && !Keyboard.isKeyDown(54)?1:-1);
               }
            }
         }

         if(this.currentScreen == null) {
            if(Mouse.isButtonDown(0) && (float)(this.ticks - this.lastClick) >= this.timer.a / 4.0F && this.hasMouse) {
               this.b(0);
               this.lastClick = this.ticks;
            }

            if(Mouse.isButtonDown(1) && (float)(this.ticks - this.lastClick) >= this.timer.a / 4.0F && this.hasMouse) {
               this.b(1);
               this.lastClick = this.ticks;
            }
         }

         boolean var26 = this.currentScreen == null && Mouse.isButtonDown(0) && this.hasMouse;
         boolean var35 = false;
         if(!this.gameMode.creative && this.blockHitTime <= 0) {
            if(var26 && this.selected != null && this.selected.a == 0) {
               var4 = this.selected.b;
               var40 = this.selected.c;
               var46 = this.selected.d;
               this.gameMode.a(var4, var40, var46, this.selected.e);
            } else {
               this.gameMode.c();
            }
         }
      }

      if(this.currentScreen != null) {
         this.lastClick = this.ticks + 10000;
      }

      if(this.currentScreen != null) {
         this.currentScreen.d();
         if(this.currentScreen != null) {
            this.currentScreen.c();
         }
      }

      if(this.level != null) {
         Renderer var29 = this.renderer;
         ++this.renderer.f;
         HeldBlock var41 = var29.e;
         var29.e.d = var41.c;
         if(var41.f) {
            ++var41.e;
            if(var41.e == 7) {
               var41.e = 0;
               var41.f = false;
            }
         }

         Player var27 = var41.a.player;
         var4 = var41.a.player.inventory.getSelected();
         Block var43 = null;
         if(var4 > 0) {
            var43 = Block.b[var4];
         }

         float var48 = 0.4F;
         float var50;
         if((var50 = (var43 == var41.b?1.0F:0.0F) - var41.c) < -var48) {
            var50 = -var48;
         }

         if(var50 > var48) {
            var50 = var48;
         }

         var41.c += var50;
         if(var41.c < 0.1F) {
            var41.b = var43;
         }

         if(var29.a.raining) {
            Renderer var39 = var29;
            var27 = var29.a.player;
            Level var32 = var29.a.level;
            var40 = (int)var27.x;
            var46 = (int)var27.y;
            var45 = (int)var27.z;

            for(var8 = 0; var8 < 50; ++var8) {
               int var60 = var40 + var39.h.nextInt(9) - 4;
               int var52 = var45 + var39.h.nextInt(9) - 4;
               int var57;
               if((var57 = var32.getHighestTile(var60, var52)) <= var46 + 4 && var57 >= var46 - 4) {
                  float var56 = var39.h.nextFloat();
                  float var62 = var39.h.nextFloat();
                  var39.a.particleManager.a(new WaterDropParticle(var32, (float)var60 + var56, (float)var57 + 0.1F, (float)var52 + var62));
               }
            }
         }

         LevelRenderer var31 = this.a;
         ++this.a.h;
         this.level.tickEntities();
         if(!this.d()) {
            this.level.tick();
         }

         this.particleManager.a();
      }

   }

   public final boolean d() {
      return this.networkManager != null;
   }

   public final void a(int var1) {
      String var2 = this.sessionData != null?this.sessionData.b:"anonymous";
      Level var4 = (new LevelGenerator(this.progressBar)).a(var2, 128 << var1, 128 << var1, 64);
      this.gameMode.b(var4);
      this.a(var4);
   }

   public final boolean a(String var1, int var2) {
      Level var3;
      if((var3 = this.levelIO.a(this.website, var1, var2)) == null) {
         return false;
      } else {
         this.a(var3);
         return true;
      }
   }

   public final void a(Level var1) {
      if(this.minecraftApplet == null || !this.minecraftApplet.getDocumentBase().getHost().equalsIgnoreCase("minecraft.net") && !this.minecraftApplet.getDocumentBase().getHost().equalsIgnoreCase("www.minecraft.net") || !this.minecraftApplet.getCodeBase().getHost().equalsIgnoreCase("minecraft.net") && !this.minecraftApplet.getCodeBase().getHost().equalsIgnoreCase("www.minecraft.net")) {
         var1 = null;
      }

      this.level = var1;
      if(var1 != null) {
         var1.initTransient();
         this.gameMode.a(var1);
         var1.font = this.fontRenderer;
         var1.rendererContext$5cd64a7f = this;
         if(!this.d()) {
            this.player = (Player)var1.findSubclassOf(Player.class);
         } else if(this.player != null) {
            this.player.resetPos();
            this.gameMode.b(this.player);
            if(var1 != null) {
               var1.player = this.player;
               var1.addEntity(this.player);
            }
         }
      }

      if(this.player == null) {
         this.player = new Player(var1);
         this.player.resetPos();
         this.gameMode.b(this.player);
         if(var1 != null) {
            var1.player = this.player;
         }
      }

      if(this.player != null) {
         this.player.input = new InputHandlerImpl(this.settings);
         this.gameMode.a(this.player);
      }

      if(this.a != null) {
         LevelRenderer var3 = this.a;
         if(this.a.a != null) {
            var3.a.removeListener$74652038(var3);
         }

         var3.a = var1;
         if(var1 != null) {
            var1.addListener$74652038(var3);
            var3.a();
         }
      }

      if(this.particleManager != null) {
         ParticleManager var5 = this.particleManager;
         if(var1 != null) {
            var1.particleEngine$13e306ae = var5;
         }

         for(int var4 = 0; var4 < 2; ++var4) {
            var5.particles[var4].clear();
         }
      }

      System.gc();
   }
}
