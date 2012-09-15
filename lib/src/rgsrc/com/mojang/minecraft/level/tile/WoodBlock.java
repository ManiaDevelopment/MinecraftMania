package com.mojang.minecraft.level.tile;

import com.mojang.minecraft.level.tile.Block;

public final class WoodBlock extends Block {

   protected WoodBlock(int var1) {
      super(17);
      this.ab = 20;
   }

   public final int f() {
      return a.nextInt(3) + 3;
   }

   public final int g() {
      return Block.i.ac;
   }

   protected final int b(int var1) {
      return var1 == 1?21:(var1 == 0?21:20);
   }
}
