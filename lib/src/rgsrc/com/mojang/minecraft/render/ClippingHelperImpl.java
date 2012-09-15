package com.mojang.minecraft.render;

import com.mojang.minecraft.render.ClippingHelper;
import com.mojang.util.MathHelper;
import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public final class ClippingHelperImpl extends ClippingHelper {

   private static ClippingHelperImpl e = new ClippingHelperImpl();
   private FloatBuffer f = BufferUtils.createFloatBuffer(16);
   private FloatBuffer g = BufferUtils.createFloatBuffer(16);
   private FloatBuffer h = BufferUtils.createFloatBuffer(16);


   public static ClippingHelper a() {
      ClippingHelperImpl var0 = e;
      e.f.clear();
      var0.g.clear();
      var0.h.clear();
      GL11.glGetFloat(2983, var0.f);
      GL11.glGetFloat(2982, var0.g);
      var0.f.flip().limit(16);
      var0.f.get(var0.b);
      var0.g.flip().limit(16);
      var0.g.get(var0.c);
      var0.d[0] = var0.c[0] * var0.b[0] + var0.c[1] * var0.b[4] + var0.c[2] * var0.b[8] + var0.c[3] * var0.b[12];
      var0.d[1] = var0.c[0] * var0.b[1] + var0.c[1] * var0.b[5] + var0.c[2] * var0.b[9] + var0.c[3] * var0.b[13];
      var0.d[2] = var0.c[0] * var0.b[2] + var0.c[1] * var0.b[6] + var0.c[2] * var0.b[10] + var0.c[3] * var0.b[14];
      var0.d[3] = var0.c[0] * var0.b[3] + var0.c[1] * var0.b[7] + var0.c[2] * var0.b[11] + var0.c[3] * var0.b[15];
      var0.d[4] = var0.c[4] * var0.b[0] + var0.c[5] * var0.b[4] + var0.c[6] * var0.b[8] + var0.c[7] * var0.b[12];
      var0.d[5] = var0.c[4] * var0.b[1] + var0.c[5] * var0.b[5] + var0.c[6] * var0.b[9] + var0.c[7] * var0.b[13];
      var0.d[6] = var0.c[4] * var0.b[2] + var0.c[5] * var0.b[6] + var0.c[6] * var0.b[10] + var0.c[7] * var0.b[14];
      var0.d[7] = var0.c[4] * var0.b[3] + var0.c[5] * var0.b[7] + var0.c[6] * var0.b[11] + var0.c[7] * var0.b[15];
      var0.d[8] = var0.c[8] * var0.b[0] + var0.c[9] * var0.b[4] + var0.c[10] * var0.b[8] + var0.c[11] * var0.b[12];
      var0.d[9] = var0.c[8] * var0.b[1] + var0.c[9] * var0.b[5] + var0.c[10] * var0.b[9] + var0.c[11] * var0.b[13];
      var0.d[10] = var0.c[8] * var0.b[2] + var0.c[9] * var0.b[6] + var0.c[10] * var0.b[10] + var0.c[11] * var0.b[14];
      var0.d[11] = var0.c[8] * var0.b[3] + var0.c[9] * var0.b[7] + var0.c[10] * var0.b[11] + var0.c[11] * var0.b[15];
      var0.d[12] = var0.c[12] * var0.b[0] + var0.c[13] * var0.b[4] + var0.c[14] * var0.b[8] + var0.c[15] * var0.b[12];
      var0.d[13] = var0.c[12] * var0.b[1] + var0.c[13] * var0.b[5] + var0.c[14] * var0.b[9] + var0.c[15] * var0.b[13];
      var0.d[14] = var0.c[12] * var0.b[2] + var0.c[13] * var0.b[6] + var0.c[14] * var0.b[10] + var0.c[15] * var0.b[14];
      var0.d[15] = var0.c[12] * var0.b[3] + var0.c[13] * var0.b[7] + var0.c[14] * var0.b[11] + var0.c[15] * var0.b[15];
      var0.a[0][0] = var0.d[3] - var0.d[0];
      var0.a[0][1] = var0.d[7] - var0.d[4];
      var0.a[0][2] = var0.d[11] - var0.d[8];
      var0.a[0][3] = var0.d[15] - var0.d[12];
      a(var0.a, 0);
      var0.a[1][0] = var0.d[3] + var0.d[0];
      var0.a[1][1] = var0.d[7] + var0.d[4];
      var0.a[1][2] = var0.d[11] + var0.d[8];
      var0.a[1][3] = var0.d[15] + var0.d[12];
      a(var0.a, 1);
      var0.a[2][0] = var0.d[3] + var0.d[1];
      var0.a[2][1] = var0.d[7] + var0.d[5];
      var0.a[2][2] = var0.d[11] + var0.d[9];
      var0.a[2][3] = var0.d[15] + var0.d[13];
      a(var0.a, 2);
      var0.a[3][0] = var0.d[3] - var0.d[1];
      var0.a[3][1] = var0.d[7] - var0.d[5];
      var0.a[3][2] = var0.d[11] - var0.d[9];
      var0.a[3][3] = var0.d[15] - var0.d[13];
      a(var0.a, 3);
      var0.a[4][0] = var0.d[3] - var0.d[2];
      var0.a[4][1] = var0.d[7] - var0.d[6];
      var0.a[4][2] = var0.d[11] - var0.d[10];
      var0.a[4][3] = var0.d[15] - var0.d[14];
      a(var0.a, 4);
      var0.a[5][0] = var0.d[3] + var0.d[2];
      var0.a[5][1] = var0.d[7] + var0.d[6];
      var0.a[5][2] = var0.d[11] + var0.d[10];
      var0.a[5][3] = var0.d[15] + var0.d[14];
      a(var0.a, 5);
      return e;
   }

   private static void a(float[][] var0, int var1) {
      float var2 = MathHelper.c(var0[var1][0] * var0[var1][0] + var0[var1][1] * var0[var1][1] + var0[var1][2] * var0[var1][2]);
      var0[var1][0] /= var2;
      var0[var1][1] /= var2;
      var0[var1][2] /= var2;
      var0[var1][3] /= var2;
   }

}
