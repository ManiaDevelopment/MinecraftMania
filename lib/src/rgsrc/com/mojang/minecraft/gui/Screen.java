package com.mojang.minecraft.gui;

import com.mojang.minecraft.gui.FontRenderer;
import com.mojang.minecraft.render.ShapeRender;
import org.lwjgl.opengl.GL11;

public class Screen {

   protected float i = 0.0F;


   protected static void a(int var0, int var1, int var2, int var3, int var4) {
      float var5 = (float)(var4 >>> 24) / 255.0F;
      float var6 = (float)(var4 >> 16 & 255) / 255.0F;
      float var7 = (float)(var4 >> 8 & 255) / 255.0F;
      float var9 = (float)(var4 & 255) / 255.0F;
      ShapeRender var8 = ShapeRender.a;
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      GL11.glBlendFunc(770, 771);
      GL11.glColor4f(var6, var7, var9, var5);
      var8.b();
      var8.b((float)var0, (float)var3, 0.0F);
      var8.b((float)var2, (float)var3, 0.0F);
      var8.b((float)var2, (float)var1, 0.0F);
      var8.b((float)var0, (float)var1, 0.0F);
      var8.a();
      GL11.glEnable(3553);
      GL11.glDisable(3042);
   }

   protected static void a(int var0, int var1, int var2, int var3, int var4, int var5) {
      float var6 = (float)(var4 >>> 24) / 255.0F;
      float var7 = (float)(var4 >> 16 & 255) / 255.0F;
      float var8 = (float)(var4 >> 8 & 255) / 255.0F;
      float var12 = (float)(var4 & 255) / 255.0F;
      float var9 = (float)(var5 >>> 24) / 255.0F;
      float var10 = (float)(var5 >> 16 & 255) / 255.0F;
      float var11 = (float)(var5 >> 8 & 255) / 255.0F;
      float var13 = (float)(var5 & 255) / 255.0F;
      GL11.glDisable(3553);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glBegin(7);
      GL11.glColor4f(var7, var8, var12, var6);
      GL11.glVertex2f((float)var2, (float)var1);
      GL11.glVertex2f((float)var0, (float)var1);
      GL11.glColor4f(var10, var11, var13, var9);
      GL11.glVertex2f((float)var0, (float)var3);
      GL11.glVertex2f((float)var2, (float)var3);
      GL11.glEnd();
      GL11.glDisable(3042);
      GL11.glEnable(3553);
   }

   public static void a(FontRenderer var0, String var1, int var2, int var3, int var4) {
      var0.a(var1, var2 - var0.a(var1) / 2, var3, var4);
   }

   public static void b(FontRenderer var0, String var1, int var2, int var3, int var4) {
      var0.a(var1, var2, var3, var4);
   }

   public final void b(int var1, int var2, int var3, int var4, int var5, int var6) {
      float var7 = 0.00390625F;
      float var8 = 0.00390625F;
      ShapeRender var9 = ShapeRender.a;
      ShapeRender.a.b();
      var9.a((float)var1, (float)(var2 + var6), this.i, (float)var3 * var7, (float)(var4 + var6) * var8);
      var9.a((float)(var1 + var5), (float)(var2 + var6), this.i, (float)(var3 + var5) * var7, (float)(var4 + var6) * var8);
      var9.a((float)(var1 + var5), (float)var2, this.i, (float)(var3 + var5) * var7, (float)var4 * var8);
      var9.a((float)var1, (float)var2, this.i, (float)var3 * var7, (float)var4 * var8);
      var9.a();
   }
}
