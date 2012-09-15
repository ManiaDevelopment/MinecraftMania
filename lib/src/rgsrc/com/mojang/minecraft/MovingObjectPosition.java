package com.mojang.minecraft;

import com.mojang.minecraft.Entity;
import com.mojang.minecraft.model.Vec3D;

public final class MovingObjectPosition {

   public int a;
   public int b;
   public int c;
   public int d;
   public int e;
   public Vec3D f;
   public Entity g;


   public MovingObjectPosition(int var1, int var2, int var3, int var4, Vec3D var5) {
      this.a = 0;
      this.b = var1;
      this.c = var2;
      this.d = var3;
      this.e = var4;
      this.f = new Vec3D(var5.a, var5.b, var5.c);
   }

   public MovingObjectPosition(Entity var1) {
      this.a = 1;
      this.g = var1;
   }
}
