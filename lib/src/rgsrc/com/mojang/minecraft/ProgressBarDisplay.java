package com.mojang.minecraft;

import com.mojang.minecraft.Minecraft;
import com.mojang.minecraft.StopGameException;
import com.mojang.minecraft.render.ShapeRender;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public final class ProgressBarDisplay {

   private String text = "";
   private Minecraft minecraft;
   private String title = "";
   private long start = System.currentTimeMillis();


   public ProgressBarDisplay(Minecraft var1) {
      this.minecraft = var1;
   }

   public final void a(String var1) {
      if(!this.minecraft.running) {
         throw new StopGameException();
      } else {
         this.title = var1;
         int var3 = this.minecraft.width * 240 / this.minecraft.height;
         int var2 = this.minecraft.height * 240 / this.minecraft.height;
         GL11.glClear(256);
         GL11.glMatrixMode(5889);
         GL11.glLoadIdentity();
         GL11.glOrtho(0.0D, (double)var3, (double)var2, 0.0D, 100.0D, 300.0D);
         GL11.glMatrixMode(5888);
         GL11.glLoadIdentity();
         GL11.glTranslatef(0.0F, 0.0F, -200.0F);
      }
   }

   public final void b(String var1) {
      if(!this.minecraft.running) {
         throw new StopGameException();
      } else {
         this.text = var1;
         this.a(-1);
      }
   }

   public final void a(int var1) {
      if(!this.minecraft.running) {
         throw new StopGameException();
      } else {
         long var2;
         if((var2 = System.currentTimeMillis()) - this.start < 0L || var2 - this.start >= 20L) {
            this.start = var2;
            int var4 = this.minecraft.width * 240 / this.minecraft.height;
            int var5 = this.minecraft.height * 240 / this.minecraft.height;
            GL11.glClear(16640);
            ShapeRender var6 = ShapeRender.a;
            int var7 = this.minecraft.textureManager.a("/dirt.png");
            GL11.glBindTexture(3553, var7);
            float var10 = 32.0F;
            var6.b();
            var6.a(4210752);
            var6.a(0.0F, (float)var5, 0.0F, 0.0F, (float)var5 / var10);
            var6.a((float)var4, (float)var5, 0.0F, (float)var4 / var10, (float)var5 / var10);
            var6.a((float)var4, 0.0F, 0.0F, (float)var4 / var10, 0.0F);
            var6.a(0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
            var6.a();
            if(var1 >= 0) {
               var7 = var4 / 2 - 50;
               int var8 = var5 / 2 + 16;
               GL11.glDisable(3553);
               var6.b();
               var6.a(8421504);
               var6.b((float)var7, (float)var8, 0.0F);
               var6.b((float)var7, (float)(var8 + 2), 0.0F);
               var6.b((float)(var7 + 100), (float)(var8 + 2), 0.0F);
               var6.b((float)(var7 + 100), (float)var8, 0.0F);
               var6.a(8454016);
               var6.b((float)var7, (float)var8, 0.0F);
               var6.b((float)var7, (float)(var8 + 2), 0.0F);
               var6.b((float)(var7 + var1), (float)(var8 + 2), 0.0F);
               var6.b((float)(var7 + var1), (float)var8, 0.0F);
               var6.a();
               GL11.glEnable(3553);
            }

            this.minecraft.fontRenderer.a(this.title, (var4 - this.minecraft.fontRenderer.a(this.title)) / 2, var5 / 2 - 4 - 16, 16777215);
            this.minecraft.fontRenderer.a(this.text, (var4 - this.minecraft.fontRenderer.a(this.text)) / 2, var5 / 2 - 4 + 8, 16777215);
            Display.update();

            try {
               Thread.yield();
            } catch (Exception var9) {
               ;
            }
         }
      }
   }
}
