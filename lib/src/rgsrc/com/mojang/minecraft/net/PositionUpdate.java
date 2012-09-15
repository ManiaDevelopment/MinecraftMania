package com.mojang.minecraft.net;


public final class PositionUpdate {

   public float a;
   public float b;
   public float c;
   public float d;
   public float e;
   public boolean f = false;
   public boolean g = false;


   public PositionUpdate(float var1, float var2, float var3, float var4, float var5) {
      this.a = var1;
      this.b = var2;
      this.c = var3;
      this.d = var4;
      this.e = var5;
      this.f = true;
      this.g = true;
   }

   public PositionUpdate(float var1, float var2, float var3) {
      this.a = var1;
      this.b = var2;
      this.c = var3;
      this.g = true;
      this.f = false;
   }

   public PositionUpdate(float var1, float var2) {
      this.d = var1;
      this.e = var2;
      this.f = true;
      this.g = false;
   }
}
