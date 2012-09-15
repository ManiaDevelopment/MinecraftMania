package com.mojang.minecraft;

import com.mojang.minecraft.KeyBinding;
import com.mojang.minecraft.Minecraft;
import com.mojang.minecraft.render.TextureManager;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import javax.imageio.ImageIO;
import org.lwjgl.input.Keyboard;

public final class GameSettings {

   private static final String[] u = new String[]{"FAR", "NORMAL", "SHORT", "TINY"};
   public boolean a = true;
   public boolean b = true;
   public boolean c = false;
   public boolean d = false;
   public int e = 0;
   public boolean f = true;
   public boolean g = false;
   public boolean h = false;
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
   public KeyBinding[] s;
   private Minecraft v;
   private File w;
   public int t;


   public GameSettings(Minecraft var1, File var2) {
      this.s = new KeyBinding[]{this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p, this.q, this.r};
      this.t = 8;
      this.v = var1;
      this.w = new File(var2, "options.txt");
      this.a();
   }

   public final String a(int var1) {
      return this.s[var1].name + ": " + Keyboard.getKeyName(this.s[var1].key);
   }

   public final void a(int var1, int var2) {
      this.s[var1].key = var2;
      this.b();
   }

   public final void b(int var1, int var2) {
      if(var1 == 0) {
         this.a = !this.a;
      }

      if(var1 == 1) {
         this.b = !this.b;
      }

      if(var1 == 2) {
         this.c = !this.c;
      }

      if(var1 == 3) {
         this.d = !this.d;
      }

      if(var1 == 4) {
         this.e = this.e + var2 & 3;
      }

      if(var1 == 5) {
         this.f = !this.f;
      }

      if(var1 == 6) {
         this.g = !this.g;
         TextureManager var7 = this.v.textureManager;
         Iterator var3 = this.v.textureManager.b.keySet().iterator();

         int var4;
         BufferedImage var5;
         while(var3.hasNext()) {
            var4 = ((Integer)var3.next()).intValue();
            var5 = (BufferedImage)var7.b.get(Integer.valueOf(var4));
            var7.a(var5, var4);
         }

         var3 = var7.a.keySet().iterator();

         while(var3.hasNext()) {
            String var8 = (String)var3.next();

            try {
               if(var8.startsWith("##")) {
                  var5 = TextureManager.a(ImageIO.read(TextureManager.class.getResourceAsStream(var8.substring(2))));
               } else {
                  var5 = ImageIO.read(TextureManager.class.getResourceAsStream(var8));
               }

               var4 = ((Integer)var7.a.get(var8)).intValue();
               var7.a(var5, var4);
            } catch (IOException var6) {
               var6.printStackTrace();
            }
         }
      }

      if(var1 == 7) {
         this.h = !this.h;
      }

      this.b();
   }

   public final String b(int var1) {
      return var1 == 0?"Music: " + (this.a?"ON":"OFF"):(var1 == 1?"Sound: " + (this.b?"ON":"OFF"):(var1 == 2?"Invert mouse: " + (this.c?"ON":"OFF"):(var1 == 3?"Show FPS: " + (this.d?"ON":"OFF"):(var1 == 4?"Render distance: " + u[this.e]:(var1 == 5?"View bobbing: " + (this.f?"ON":"OFF"):(var1 == 6?"3d anaglyph: " + (this.g?"ON":"OFF"):(var1 == 7?"Limit framerate: " + (this.h?"ON":"OFF"):"")))))));
   }

   private void a() {
      try {
         if(this.w.exists()) {
            BufferedReader var1 = new BufferedReader(new FileReader(this.w));
            String var2 = null;

            while((var2 = var1.readLine()) != null) {
               String[] var5;
               if((var5 = var2.split(":"))[0].equals("music")) {
                  this.a = var5[1].equals("true");
               }

               if(var5[0].equals("sound")) {
                  this.b = var5[1].equals("true");
               }

               if(var5[0].equals("invertYMouse")) {
                  this.c = var5[1].equals("true");
               }

               if(var5[0].equals("showFrameRate")) {
                  this.d = var5[1].equals("true");
               }

               if(var5[0].equals("viewDistance")) {
                  this.e = Integer.parseInt(var5[1]);
               }

               if(var5[0].equals("bobView")) {
                  this.f = var5[1].equals("true");
               }

               if(var5[0].equals("anaglyph3d")) {
                  this.g = var5[1].equals("true");
               }

               if(var5[0].equals("limitFramerate")) {
                  this.h = var5[1].equals("true");
               }

               for(int var3 = 0; var3 < this.s.length; ++var3) {
                  if(var5[0].equals("key_" + this.s[var3].name)) {
                     this.s[var3].key = Integer.parseInt(var5[1]);
                  }
               }
            }

            var1.close();
         }
      } catch (Exception var4) {
         System.out.println("Failed to load options");
         var4.printStackTrace();
      }
   }

   private void b() {
      try {
         PrintWriter var1;
         (var1 = new PrintWriter(new FileWriter(this.w))).println("music:" + this.a);
         var1.println("sound:" + this.b);
         var1.println("invertYMouse:" + this.c);
         var1.println("showFrameRate:" + this.d);
         var1.println("viewDistance:" + this.e);
         var1.println("bobView:" + this.f);
         var1.println("anaglyph3d:" + this.g);
         var1.println("limitFramerate:" + this.h);

         for(int var2 = 0; var2 < this.s.length; ++var2) {
            var1.println("key_" + this.s[var2].name + ":" + this.s[var2].key);
         }

         var1.close();
      } catch (Exception var3) {
         System.out.println("Failed to save options");
         var3.printStackTrace();
      }
   }

}
