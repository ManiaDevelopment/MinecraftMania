package com.mojang.minecraft.level.generator.noise;

import com.mojang.minecraft.level.generator.noise.NoiseGenerator;

public final class CombinedNoiseGenerator extends NoiseGenerator {

   private NoiseGenerator noise1;
   private NoiseGenerator noise2;


   public CombinedNoiseGenerator(NoiseGenerator var1, NoiseGenerator var2) {
      this.noise1 = var1;
      this.noise2 = var2;
   }

   public final double a(double var1, double var3) {
      return this.noise1.a(var1 + this.noise2.a(var1, var3), var3);
   }
}
