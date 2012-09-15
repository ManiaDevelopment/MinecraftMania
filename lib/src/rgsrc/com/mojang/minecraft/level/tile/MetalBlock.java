package com.mojang.minecraft.level.tile;

import com.mojang.minecraft.level.tile.Block;

public final class MetalBlock extends Block {

   public MetalBlock(int var1, int var2) {
      super(var1);
      this.ab = var2;
   }

   protected final int b(int var1) {
      return var1 == 1?this.ab - 16:(var1 == 0?this.ab + 16:this.ab);
   }
}
