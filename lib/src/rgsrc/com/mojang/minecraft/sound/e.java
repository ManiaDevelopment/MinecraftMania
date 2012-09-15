package com.mojang.minecraft.sound;

import com.mojang.minecraft.Entity;
import com.mojang.minecraft.sound.f;
import com.mojang.util.MathHelper;

public abstract class e implements f {

   private Entity a;


   public e(Entity var1) {
      this.a = var1;
   }

   public final float a(float var1, float var2) {
      var1 -= this.a.x;
      var2 -= this.a.z;
      float var3 = MathHelper.c(var1 * var1 + var2 * var2);
      var1 /= var3;
      var2 /= var3;
      if((var3 /= 2.0F) > 1.0F) {
         var3 = 1.0F;
      }

      float var4 = MathHelper.b(-this.a.yRot * 0.017453292F + 3.1415927F);
      return (MathHelper.a(-this.a.yRot * 0.017453292F + 3.1415927F) * var2 - var4 * var1) * var3;
   }

   public final float a(float var1, float var2, float var3) {
      var1 -= this.a.x;
      var2 -= this.a.y;
      float var4 = var3 - this.a.z;
      var4 = MathHelper.c(var1 * var1 + var2 * var2 + var4 * var4);
      if((var4 = 1.0F - var4 / 32.0F) < 0.0F) {
         var4 = 0.0F;
      }

      return var4;
   }
}
