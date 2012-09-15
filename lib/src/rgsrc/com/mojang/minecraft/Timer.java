package com.mojang.minecraft;


public final class Timer {

   float a;
   double b;
   public int c;
   public float d;
   public float e = 1.0F;
   public float f = 0.0F;
   long g;
   long h;
   double i = 1.0D;


   public Timer(float var1) {
      this.a = var1;
      this.g = System.currentTimeMillis();
      this.h = System.nanoTime() / 1000000L;
   }
}
