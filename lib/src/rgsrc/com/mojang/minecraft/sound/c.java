package com.mojang.minecraft.sound;

import com.mojang.minecraft.Entity;
import com.mojang.minecraft.sound.e;

public final class c extends e {

   private Entity a;


   public c(Entity var1, Entity var2) {
      super(var2);
      this.a = var1;
   }

   public final float a() {
      return super.a(this.a.x, this.a.z);
   }

   public final float b() {
      return super.a(this.a.x, this.a.y, this.a.z);
   }
}
