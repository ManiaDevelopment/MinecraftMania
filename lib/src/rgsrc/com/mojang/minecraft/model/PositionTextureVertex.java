package com.mojang.minecraft.model;

import com.mojang.minecraft.model.Vec3D;

public final class PositionTextureVertex {

   public Vec3D a;
   public float b;
   public float c;


   public PositionTextureVertex(float var1, float var2, float var3, float var4, float var5) {
      this(new Vec3D(var1, var2, var3), var4, var5);
   }

   public final PositionTextureVertex a(float var1, float var2) {
      return new PositionTextureVertex(this, var1, var2);
   }

   private PositionTextureVertex(PositionTextureVertex var1, float var2, float var3) {
      this.a = var1.a;
      this.b = var2;
      this.c = var3;
   }

   private PositionTextureVertex(Vec3D var1, float var2, float var3) {
      this.a = var1;
      this.b = var2;
      this.c = var3;
   }
}
