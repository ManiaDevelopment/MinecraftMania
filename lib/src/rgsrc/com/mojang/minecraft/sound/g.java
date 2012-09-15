package com.mojang.minecraft.sound;

import com.mojang.minecraft.sound.a;
import com.mojang.minecraft.sound.f;
import com.mojang.minecraft.sound.h;

public final class g implements a {

   private h a;
   private f b;
   private float c = 0.0F;
   private float d = 1.0F;
   private static short[] e = new short[1];


   public g(h var1, f var2) {
      this.a = var1;
      this.b = var2;
      this.c = var2.a();
      this.d = var2.b() * var1.a;
   }

   public final boolean a(int[] var1, int[] var2, int var3) {
      if(e.length < var3) {
         e = new short[var3];
      }

      int var4;
      boolean var5 = (var4 = this.a.a(e, var3)) > 0;
      float var6 = this.b.a();
      float var7 = this.b.b() * this.a.a;
      int var8 = (int)((this.c > 0.0F?1.0F - this.c:1.0F) * this.d * 65536.0F);
      int var9 = (int)((this.c < 0.0F?1.0F + this.c:1.0F) * this.d * 65536.0F);
      int var10 = (int)((var6 > 0.0F?1.0F - var6:1.0F) * var7 * 65536.0F);
      int var11 = (int)((var6 < 0.0F?var6 + 1.0F:1.0F) * var7 * 65536.0F);
      var10 -= var8;
      var11 -= var9;
      int var12;
      int var13;
      int var14;
      if(var10 == 0 && var11 == 0) {
         if(var8 >= 0 || var9 != 0) {
            var12 = var8;
            var13 = var9;

            for(var14 = 0; var14 < var4; ++var14) {
               var1[var14] += e[var14] * var12 >> 16;
               var2[var14] += e[var14] * var13 >> 16;
            }
         }
      } else {
         for(var12 = 0; var12 < var4; ++var12) {
            var13 = var8 + var10 * var12 / var3;
            var14 = var9 + var11 * var12 / var3;
            var1[var12] += e[var12] * var13 >> 16;
            var2[var12] += e[var12] * var14 >> 16;
         }
      }

      this.c = var6;
      this.d = var7;
      return var5;
   }

}
