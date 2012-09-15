package com.mojang.minecraft.level.tile;

import com.mojang.minecraft.item.PrimedTnt;
import com.mojang.minecraft.level.Level;
import com.mojang.minecraft.level.tile.Block;
import com.mojang.minecraft.particle.ParticleManager;

public final class TNTBlock extends Block {

   public TNTBlock(int var1, int var2) {
      super(46, 8);
   }

   protected final int b(int var1) {
      return var1 == 0?this.ab + 2:(var1 == 1?this.ab + 1:this.ab);
   }

   public final int f() {
      return 0;
   }

   public final void f(Level var1, int var2, int var3, int var4) {
      if(!var1.creativeMode) {
         PrimedTnt var5;
         (var5 = new PrimedTnt(var1, (float)var2 + 0.5F, (float)var3 + 0.5F, (float)var4 + 0.5F)).life = a.nextInt(var5.life / 4) + var5.life / 8;
         var1.addEntity(var5);
      }

   }

   public final void a(Level var1, int var2, int var3, int var4, ParticleManager var5) {
      if(!var1.creativeMode) {
         var1.addEntity(new PrimedTnt(var1, (float)var2 + 0.5F, (float)var3 + 0.5F, (float)var4 + 0.5F));
      } else {
         super.a(var1, var2, var3, var4, var5);
      }
   }
}
