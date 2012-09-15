package com.mojang.minecraft.level.generator.noise;

import com.mojang.minecraft.level.generator.noise.NoiseGenerator;
import com.mojang.minecraft.level.generator.noise.NoiseGeneratorPerlin;
import java.util.Random;

public final class NoiseGeneratorOctaves extends NoiseGenerator {

   private NoiseGeneratorPerlin[] perlinNoise;
   private int count;


   public NoiseGeneratorOctaves(Random var1, int var2) {
      this.count = var2;
      this.perlinNoise = new NoiseGeneratorPerlin[var2];

      for(int var3 = 0; var3 < var2; ++var3) {
         this.perlinNoise[var3] = new NoiseGeneratorPerlin(var1);
      }

   }

   public final double a(double var1, double var3) {
      double var5 = 0.0D;
      double var7 = 1.0D;

      for(int var9 = 0; var9 < this.count; ++var9) {
         var5 += this.perlinNoise[var9].a(var1 / var7, var3 / var7) * var7;
         var7 *= 2.0D;
      }

      return var5;
   }
}
