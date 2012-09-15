package com.mojang.minecraft.sound;

import com.mojang.minecraft.Entity;
import com.mojang.minecraft.sound.e;

public final class n extends e {

   private float a;
   private float b;
   private float c;


   public n(float var1, float var2, float var3, Entity var4) {
      super(var4);
      this.a = var1;
      this.b = var2;
      this.c = var3;
   }

   public final float a() {
      return super.a(this.a, this.c);
   }

   public final float b() {
      return super.a(this.a, this.b, this.c);
   }
}
