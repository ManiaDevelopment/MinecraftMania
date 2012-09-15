package com.mojang.minecraft.level.tile;

import com.mojang.minecraft.level.Level;
import com.mojang.minecraft.level.tile.Block;
import java.util.Random;

public final class GrassBlock extends Block {

   protected GrassBlock(int var1) {
      super(2);
      this.ab = 3;
      this.a(true);
   }

   protected final int b(int var1) {
      return var1 == 1?0:(var1 == 0?2:3);
   }

   public final void a(Level var1, int var2, int var3, int var4, Random var5) {
      if(var5.nextInt(4) == 0) {
         if(!var1.isLit(var2, var3, var4)) {
            var1.setTile(var2, var3, var4, Block.g.ac);
         } else {
            for(int var9 = 0; var9 < 4; ++var9) {
               int var6 = var2 + var5.nextInt(3) - 1;
               int var7 = var3 + var5.nextInt(5) - 3;
               int var8 = var4 + var5.nextInt(3) - 1;
               if(var1.getTile(var6, var7, var8) == Block.g.ac && var1.isLit(var6, var7, var8)) {
                  var1.setTile(var6, var7, var8, Block.f.ac);
               }
            }

         }
      }
   }

   public final int g() {
      return Block.g.g();
   }
}
