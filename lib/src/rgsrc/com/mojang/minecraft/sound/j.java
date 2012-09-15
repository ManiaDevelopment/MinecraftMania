package com.mojang.minecraft.sound;

import com.mojang.minecraft.sound.b;
import com.mojang.minecraft.sound.h;

public final class j extends h {

   private b b;
   private float c = 0.0F;
   private float d;


   public j(b var1, float var2, float var3) {
      this.b = var1;
      this.d = var2 * 44100.0F / var1.b;
      this.a = var3;
   }

   public final int a(short[] var1, int var2) {
      if(this.c >= (float)this.b.a.length) {
         return 0;
      } else {
         for(int var3 = 0; var3 < var2; ++var3) {
            int var4 = (int)this.c;
            short var5 = this.b.a[var4];
            short var6 = var4 < this.b.a.length - 1?this.b.a[var4 + 1]:0;
            var1[var3] = (short)((int)((float)var5 + (float)(var6 - var5) * (this.c - (float)var4)));
            this.c += this.d;
            if(this.c >= (float)this.b.a.length) {
               return var3;
            }
         }

         return var2;
      }
   }
}
