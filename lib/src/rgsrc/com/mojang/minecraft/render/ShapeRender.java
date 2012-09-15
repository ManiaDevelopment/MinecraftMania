package com.mojang.minecraft.render;

import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public final class ShapeRender {

   private FloatBuffer b = BufferUtils.createFloatBuffer(524288);
   private float[] c = new float[524288];
   private int d = 0;
   private float e;
   private float f;
   private float g;
   private float h;
   private float i;
   private boolean j = false;
   private boolean k = false;
   private int l = 3;
   private int m = 0;
   private boolean n = false;
   public static ShapeRender a = new ShapeRender();


   public final void a() {
      if(this.d > 0) {
         this.b.clear();
         this.b.put(this.c, 0, this.m);
         this.b.flip();
         if(this.k && this.j) {
            GL11.glInterleavedArrays(10794, 0, this.b);
         } else if(this.k) {
            GL11.glInterleavedArrays(10791, 0, this.b);
         } else if(this.j) {
            GL11.glInterleavedArrays(10788, 0, this.b);
         } else {
            GL11.glInterleavedArrays(10785, 0, this.b);
         }

         GL11.glEnableClientState('\u8074');
         if(this.k) {
            GL11.glEnableClientState('\u8078');
         }

         if(this.j) {
            GL11.glEnableClientState('\u8076');
         }

         GL11.glDrawArrays(7, 0, this.d);
         GL11.glDisableClientState('\u8074');
         if(this.k) {
            GL11.glDisableClientState('\u8078');
         }

         if(this.j) {
            GL11.glDisableClientState('\u8076');
         }
      }

      this.d();
   }

   private void d() {
      this.d = 0;
      this.b.clear();
      this.m = 0;
   }

   public final void b() {
      this.d();
      this.j = false;
      this.k = false;
      this.n = false;
   }

   public final void a(float var1, float var2, float var3) {
      if(!this.n) {
         if(!this.j) {
            this.l += 3;
         }

         this.j = true;
         this.g = var1;
         this.h = var2;
         this.i = var3;
      }
   }

   public final void a(float var1, float var2, float var3, float var4, float var5) {
      if(!this.k) {
         this.l += 2;
      }

      this.k = true;
      this.e = var4;
      this.f = var5;
      this.b(var1, var2, var3);
   }

   public final void b(float var1, float var2, float var3) {
      if(this.k) {
         this.c[this.m++] = this.e;
         this.c[this.m++] = this.f;
      }

      if(this.j) {
         this.c[this.m++] = this.g;
         this.c[this.m++] = this.h;
         this.c[this.m++] = this.i;
      }

      this.c[this.m++] = var1;
      this.c[this.m++] = var2;
      this.c[this.m++] = var3;
      ++this.d;
      if(this.d % 4 == 0 && this.m >= 524288 - (this.l << 2)) {
         this.a();
      }

   }

   public final void a(int var1) {
      int var2 = var1 >> 16 & 255;
      int var3 = var1 >> 8 & 255;
      var1 &= 255;
      int var10001 = var2;
      int var10002 = var3;
      var3 = var1;
      var2 = var10002;
      var1 = var10001;
      byte var7 = (byte)var1;
      byte var4 = (byte)var2;
      byte var8 = (byte)var3;
      byte var6 = var4;
      byte var5 = var7;
      if(!this.n) {
         if(!this.j) {
            this.l += 3;
         }

         this.j = true;
         this.g = (float)(var5 & 255) / 255.0F;
         this.h = (float)(var6 & 255) / 255.0F;
         this.i = (float)(var8 & 255) / 255.0F;
      }

   }

   public final void c() {
      this.n = true;
   }

   public final void c(float var1, float var2, float var3) {
      GL11.glNormal3f(var1, var2, var3);
   }

}
