package com.mojang.minecraft.model;

import com.mojang.minecraft.model.HumanoidModel;
import com.mojang.util.MathHelper;

public class ZombieModel extends HumanoidModel {

   public final void b(float var1, float var2, float var3, float var4, float var5, float var6) {
      super.b(var1, var2, var3, var4, var5, var6);
      var1 = MathHelper.a(this.a * 3.1415927F);
      var2 = MathHelper.a((1.0F - (1.0F - this.a) * (1.0F - this.a)) * 3.1415927F);
      this.e.h = 0.0F;
      this.f.h = 0.0F;
      this.e.g = -(0.1F - var1 * 0.6F);
      this.f.g = 0.1F - var1 * 0.6F;
      this.e.f = -1.5707964F;
      this.f.f = -1.5707964F;
      this.e.f -= var1 * 1.2F - var2 * 0.4F;
      this.f.f -= var1 * 1.2F - var2 * 0.4F;
      this.e.h += MathHelper.b(var3 * 0.09F) * 0.05F + 0.05F;
      this.f.h -= MathHelper.b(var3 * 0.09F) * 0.05F + 0.05F;
      this.e.f += MathHelper.a(var3 * 0.067F) * 0.05F;
      this.f.f -= MathHelper.a(var3 * 0.067F) * 0.05F;
   }
}
