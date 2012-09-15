package de.jarnbjo.vorbis;

import com.mojang.util.MathHelper;

final class l {

   private int a;
   private int b;
   private float[] c;
   private int[] d;
   private float e;
   private float f;
   private float g;
   private float h;
   private float[] i = new float[1024];
   private float[] j = new float[1024];


   protected l(int var1) {
      this.d = new int[var1 / 4];
      this.c = new float[var1 + var1 / 4];
      this.b = (int)Math.rint(Math.log((double)var1) / Math.log(2.0D));
      this.a = var1;
      int var2;
      int var3 = (var2 = 0 + var1 / 2) + 1;
      int var4;
      int var5 = (var4 = var2 + var1 / 2) + 1;

      int var6;
      for(var6 = 0; var6 < var1 / 4; ++var6) {
         this.c[0 + (var6 << 1)] = MathHelper.b(3.1415927F / (float)var1 * (float)(var6 * 4));
         this.c[1 + (var6 << 1)] = -MathHelper.a(3.1415927F / (float)var1 * (float)(var6 * 4));
         this.c[var2 + (var6 << 1)] = MathHelper.b(3.1415927F / (float)(var1 * 2) * (float)(var6 * 2 + 1));
         this.c[var3 + (var6 << 1)] = MathHelper.a(3.1415927F / (float)(var1 * 2) * (float)(var6 * 2 + 1));
      }

      for(var6 = 0; var6 < var1 / 8; ++var6) {
         this.c[var4 + (var6 << 1)] = MathHelper.b(3.1415927F / (float)var1 * (float)(var6 * 4 + 2));
         this.c[var5 + (var6 << 1)] = -MathHelper.a(3.1415927F / (float)var1 * (float)(var6 * 4 + 2));
      }

      var6 = (1 << this.b - 1) - 1;
      var2 = 1 << this.b - 2;

      for(var3 = 0; var3 < var1 / 8; ++var3) {
         var4 = 0;

         for(var5 = 0; var2 >>> var5 != 0; ++var5) {
            if((var2 >>> var5 & var3) != 0) {
               var4 |= 1 << var5;
            }
         }

         this.d[var3 << 1] = ~var4 & var6;
         this.d[(var3 << 1) + 1] = var4;
      }

   }

   protected final synchronized void a(float[] var1, float[] var2, int[] var3) {
      var1 = var1;
      if(this.i.length < this.a / 2) {
         this.i = new float[this.a / 2];
      }

      if(this.j.length < this.a / 2) {
         this.j = new float[this.a / 2];
      }

      float[] var4 = this.i;
      float[] var5 = this.j;
      int var6 = this.a >> 1;
      int var7 = this.a >> 2;
      int var8 = this.a >> 3;
      int var9 = -1;
      int var10 = 0;
      int var11 = var6;

      int var12;
      for(var12 = 0; var12 < var8; ++var12) {
         var9 += 2;
         this.e = var1[var9];
         var9 += 2;
         this.f = var1[var9];
         --var11;
         this.g = this.c[var11];
         --var11;
         this.h = this.c[var11];
         var4[var10++] = -this.f * this.g - this.e * this.h;
         var4[var10++] = this.e * this.g - this.f * this.h;
      }

      var9 = var6;

      for(var12 = 0; var12 < var8; ++var12) {
         var9 -= 2;
         this.e = var1[var9];
         var9 -= 2;
         this.f = var1[var9];
         --var11;
         this.g = this.c[var11];
         --var11;
         this.h = this.c[var11];
         var4[var10++] = this.f * this.g + this.e * this.h;
         var4[var10++] = this.f * this.h - this.e * this.g;
      }

      var11 = var8;
      var10 = var7;
      var9 = var6;
      var8 = this.a;
      var5 = var5;
      var4 = var4;
      l var25 = this;
      var12 = var7;
      int var13 = 0;
      int var14 = var7;
      int var15 = var6;

      int var16;
      for(var16 = 0; var16 < var10; ++var16) {
         float var17 = var4[var12] - var4[var13];
         var5[var14 + var16] = var4[var12++] + var4[var13++];
         float var18 = var4[var12] - var4[var13];
         var15 -= 4;
         var5[var16++] = var17 * var25.c[var15] + var18 * var25.c[var15 + 1];
         var5[var16] = var18 * var25.c[var15] - var17 * var25.c[var15 + 1];
         var5[var14 + var16] = var4[var12++] + var4[var13++];
      }

      int var19;
      float var21;
      float var20;
      float var23;
      float var24;
      int var34;
      int var35;
      for(var16 = 0; var16 < var25.b - 3; ++var16) {
         var34 = var8 >>> var16 + 2;
         var35 = 1 << var16 + 3;
         var10 = var9 - 2;
         var15 = 0;

         for(var13 = 0; var13 < var34 >>> 2; ++var13) {
            var19 = var10;
            var14 = var10 - (var34 >> 1);
            var20 = var25.c[var15];
            var21 = var25.c[var15 + 1];
            var10 -= 2;
            ++var34;

            for(int var22 = 0; var22 < 2 << var16; ++var22) {
               var25.e = var5[var19];
               var25.f = var5[var14];
               var23 = var25.e - var25.f;
               var4[var19] = var25.e + var25.f;
               ++var19;
               var25.e = var5[var19];
               ++var14;
               var25.f = var5[var14];
               var24 = var25.e - var25.f;
               var4[var19] = var25.e + var25.f;
               var4[var14] = var24 * var20 - var23 * var21;
               var4[var14 - 1] = var23 * var20 + var24 * var21;
               var19 -= var34;
               var14 -= var34;
            }

            --var34;
            var15 += var35;
         }

         float[] var33 = var5;
         var5 = var4;
         var4 = var33;
      }

      var16 = var8;
      var34 = 0;
      var35 = 0;
      var10 = var9 - 1;

      float var29;
      float var32;
      for(var12 = 0; var12 < var11; ++var12) {
         var13 = var25.d[var34++];
         var19 = var25.d[var34++];
         var20 = var5[var13] - var5[var19 + 1];
         var24 = var5[var13 - 1] + var5[var19];
         var21 = var5[var13] + var5[var19 + 1];
         var23 = var5[var13 - 1] - var5[var19];
         float var36 = var20 * var25.c[var16];
         var29 = var24 * var25.c[var16++];
         float var30 = var20 * var25.c[var16];
         var32 = var24 * var25.c[var16++];
         var4[var35++] = (var21 + var30 + var29) * 16383.0F;
         var4[var10--] = (-var23 + var32 - var36) * 16383.0F;
         var4[var35++] = (var23 + var32 - var36) * 16383.0F;
         var4[var10--] = (var21 - var30 - var29) * 16383.0F;
      }

      float[] var31 = var4;
      var10 = 0;
      var11 = var6;
      var12 = var7;
      int var26 = var7 - 1;
      int var27;
      int var28 = (var27 = var7 + var6) - 1;

      for(var6 = 0; var6 < var7; ++var6) {
         this.e = var31[var10++];
         this.f = var31[var10++];
         this.g = this.c[var11++];
         this.h = this.c[var11++];
         var29 = this.e * this.h - this.f * this.g;
         var32 = -(this.e * this.g + this.f * this.h);
         var3[var12] = (int)(-var29 * var2[var12]);
         var3[var26] = (int)(var29 * var2[var26]);
         var3[var27] = (int)(var32 * var2[var27]);
         var3[var28] = (int)(var32 * var2[var28]);
         ++var12;
         --var26;
         ++var27;
         --var28;
      }

   }
}
