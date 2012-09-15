package com.mojang.minecraft.level.tile;

import com.mojang.minecraft.level.tile.Block;

public final class OreBlock extends Block {

   public OreBlock(int var1, int var2) {
      super(var1, var2);
   }

   public final int g() {
      return this == Block.t?Block.V.ac:(this == Block.r?Block.S.ac:(this == Block.s?Block.T.ac:this.ac));
   }

   public final int f() {
      return a.nextInt(3) + 1;
   }
}
