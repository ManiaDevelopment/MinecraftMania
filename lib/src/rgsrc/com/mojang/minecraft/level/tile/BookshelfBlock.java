package com.mojang.minecraft.level.tile;

import com.mojang.minecraft.level.tile.Block;

public final class BookshelfBlock extends Block {

   public BookshelfBlock(int var1, int var2) {
      super(47, 35);
   }

   protected final int b(int var1) {
      return var1 <= 1?4:this.ab;
   }

   public final int f() {
      return 0;
   }
}
