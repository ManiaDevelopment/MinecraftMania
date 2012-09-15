package com.mojang.minecraft.level.tile;

import com.mojang.minecraft.level.Level;
import com.mojang.minecraft.level.tile.Block;

public final class GlassBlock extends Block {

   private boolean al = false;


   protected GlassBlock(int var1, int var2, boolean var3) {
      super(20, 49);
   }

   public final boolean c() {
      return false;
   }

   public final boolean a(Level var1, int var2, int var3, int var4, int var5) {
      int var6 = var1.getTile(var2, var3, var4);
      return !this.al && var6 == this.ac?false:super.a(var1, var2, var3, var4, var5);
   }

   public final boolean b() {
      return false;
   }
}
