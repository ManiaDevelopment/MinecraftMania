package com.mojang.minecraft.model;

import com.mojang.util.MathHelper;

public final class Vec3D {

   public float a;
   public float b;
   public float c;


   public Vec3D(float var1, float var2, float var3) {
      this.a = var1;
      this.b = var2;
      this.c = var3;
   }

   public final Vec3D a(Vec3D var1) {
      return new Vec3D(this.a - var1.a, this.b - var1.b, this.c - var1.c);
   }

   public final Vec3D a() {
      float var1 = MathHelper.c(this.a * this.a + this.b * this.b + this.c * this.c);
      return new Vec3D(this.a / var1, this.b / var1, this.c / var1);
   }

   public final Vec3D a(float var1, float var2, float var3) {
      return new Vec3D(this.a + var1, this.b + var2, this.c + var3);
   }

   public final float b(Vec3D var1) {
      float var2 = var1.a - this.a;
      float var3 = var1.b - this.b;
      float var4 = var1.c - this.c;
      return MathHelper.c(var2 * var2 + var3 * var3 + var4 * var4);
   }

   public final float c(Vec3D var1) {
      float var2 = var1.a - this.a;
      float var3 = var1.b - this.b;
      float var4 = var1.c - this.c;
      return var2 * var2 + var3 * var3 + var4 * var4;
   }

   public final Vec3D a(Vec3D var1, float var2) {
      float var3 = var1.a - this.a;
      float var4 = var1.b - this.b;
      float var5 = var1.c - this.c;
      return var3 * var3 < 1.0E-7F?null:((var2 = (var2 - this.a) / var3) >= 0.0F && var2 <= 1.0F?new Vec3D(this.a + var3 * var2, this.b + var4 * var2, this.c + var5 * var2):null);
   }

   public final Vec3D b(Vec3D var1, float var2) {
      float var3 = var1.a - this.a;
      float var4 = var1.b - this.b;
      float var5 = var1.c - this.c;
      return var4 * var4 < 1.0E-7F?null:((var2 = (var2 - this.b) / var4) >= 0.0F && var2 <= 1.0F?new Vec3D(this.a + var3 * var2, this.b + var4 * var2, this.c + var5 * var2):null);
   }

   public final Vec3D c(Vec3D var1, float var2) {
      float var3 = var1.a - this.a;
      float var4 = var1.b - this.b;
      float var5;
      return (var5 = var1.c - this.c) * var5 < 1.0E-7F?null:((var2 = (var2 - this.c) / var5) >= 0.0F && var2 <= 1.0F?new Vec3D(this.a + var3 * var2, this.b + var4 * var2, this.c + var5 * var2):null);
   }

   public final String toString() {
      return "(" + this.a + ", " + this.b + ", " + this.c + ")";
   }
}
