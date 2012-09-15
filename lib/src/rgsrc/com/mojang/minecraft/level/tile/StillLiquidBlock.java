package com.mojang.minecraft.level.tile;

import com.mojang.minecraft.level.Level;
import com.mojang.minecraft.level.liquid.LiquidType;
import com.mojang.minecraft.level.tile.Block;
import com.mojang.minecraft.level.tile.LiquidBlock;
import java.util.Random;

public final class StillLiquidBlock extends LiquidBlock {

   protected StillLiquidBlock(int var1, LiquidType var2) {
      super(var1, var2);
      this.an = var1 - 1;
      this.am = var1;
      this.a(false);
   }

   public final void a(Level var1, int var2, int var3, int var4, Random var5) {}

   public final void b(Level var1, int var2, int var3, int var4, int var5) {
      boolean var6 = false;
      if(var1.getTile(var2 - 1, var3, var4) == 0) {
         var6 = true;
      }

      if(var1.getTile(var2 + 1, var3, var4) == 0) {
         var6 = true;
      }

      if(var1.getTile(var2, var3, var4 - 1) == 0) {
         var6 = true;
      }

      if(var1.getTile(var2, var3, var4 + 1) == 0) {
         var6 = true;
      }

      if(var1.getTile(var2, var3 - 1, var4) == 0) {
         var6 = true;
      }

      if(var5 != 0) {
         LiquidType var7 = Block.b[var5].d();
         if(this.al == LiquidType.b && var7 == LiquidType.c || var7 == LiquidType.b && this.al == LiquidType.c) {
            var1.setTile(var2, var3, var4, Block.e.ac);
            return;
         }
      }

      if(var6) {
         var1.setTileNoUpdate(var2, var3, var4, this.an);
         var1.addToTickNextTick(var2, var3, var4, this.an);
      }

   }
}
