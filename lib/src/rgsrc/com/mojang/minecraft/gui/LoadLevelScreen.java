package com.mojang.minecraft.gui;

import com.mojang.minecraft.Minecraft;
import com.mojang.minecraft.gui.Button;
import com.mojang.minecraft.gui.PopUpScreen;
import com.mojang.minecraft.gui.SaveLevelDialogThread;
import com.mojang.minecraft.level.Level;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;

public class LoadLevelScreen extends PopUpScreen implements Runnable {

   protected PopUpScreen g;
   private boolean n = false;
   private boolean o = false;
   private String[] p = null;
   private String q = "";
   protected String h = "Load level";
   boolean j = false;
   JFileChooser k;
   protected boolean l = false;
   protected File m;


   public LoadLevelScreen(PopUpScreen var1) {
      this.g = var1;
   }

   public void run() {
      try {
         if(this.j) {
            try {
               Thread.sleep(100L);
            } catch (InterruptedException var2) {
               var2.printStackTrace();
            }
         }

         this.q = "Getting level list..";
         URL var1 = new URL("http://" + this.a.website + "/listmaps.jsp?user=" + this.a.sessionData.b);
         BufferedReader var4 = new BufferedReader(new InputStreamReader(var1.openConnection().getInputStream()));
         this.p = var4.readLine().split(";");
         if(this.p.length >= 5) {
            this.a(this.p);
            this.o = true;
            return;
         }

         this.q = this.p[0];
         this.n = true;
      } catch (Exception var3) {
         var3.printStackTrace();
         this.q = "Failed to load levels";
         this.n = true;
      }

   }

   protected void a(String[] var1) {
      for(int var2 = 0; var2 < 5; ++var2) {
         ((Button)this.d.get(var2)).g = !var1[var2].equals("-");
         ((Button)this.d.get(var2)).e = var1[var2];
         ((Button)this.d.get(var2)).h = true;
      }

   }

   public void a() {
      (new Thread(this)).start();

      for(int var1 = 0; var1 < 5; ++var1) {
         this.d.add(new Button(var1, this.b / 2 - 100, this.c / 6 + var1 * 24, "---"));
         ((Button)this.d.get(var1)).h = false;
         ((Button)this.d.get(var1)).g = false;
      }

      this.d.add(new Button(5, this.b / 2 - 100, this.c / 6 + 120 + 12, "Load file..."));
      this.d.add(new Button(6, this.b / 2 - 100, this.c / 6 + 168, "Cancel"));
   }

   protected final void a(Button var1) {
      if(!this.j) {
         if(var1.g) {
            if(this.o && var1.f < 5) {
               this.a(var1.f);
            }

            if(this.n || this.o && var1.f == 5) {
               this.j = true;
               SaveLevelDialogThread var2;
               (var2 = new SaveLevelDialogThread(this)).setDaemon(true);
               SwingUtilities.invokeLater(var2);
            }

            if(this.n || this.o && var1.f == 6) {
               this.a.a(this.g);
            }

         }
      }
   }

   protected void a(File var1) {
      File var2 = var1;
      Minecraft var4 = this.a;
      Level var5;
      boolean var10000;
      if((var5 = this.a.levelIO.a(var2)) == null) {
         var10000 = false;
      } else {
         var4.a(var5);
         var10000 = true;
      }

      this.a.a(this.g);
   }

   protected void a(int var1) {
      this.a.a(this.a.sessionData.b, var1);
      this.a.a((PopUpScreen)null);
      this.a.b();
   }

   public void a(int var1, int var2) {
      a(0, 0, this.b, this.c, 1610941696, -1607454624);
      a(this.f, this.h, this.b / 2, 20, 16777215);
      if(this.j) {
         a(this.f, "Selecting file..", this.b / 2, this.c / 2 - 4, 16777215);

         try {
            Thread.sleep(20L);
         } catch (InterruptedException var3) {
            var3.printStackTrace();
         }
      } else {
         if(!this.o) {
            a(this.f, this.q, this.b / 2, this.c / 2 - 4, 16777215);
         }

         super.a(var1, var2);
      }
   }

   public final void b() {
      super.b();
      if(this.k != null) {
         this.k.cancelSelection();
      }

   }

   public final void c() {
      super.c();
      if(this.m != null) {
         this.a(this.m);
         this.m = null;
      }

   }
}
