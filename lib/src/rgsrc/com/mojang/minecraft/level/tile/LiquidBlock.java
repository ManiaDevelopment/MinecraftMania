package com.mojang.minecraft.level.tile;

import com.mojang.minecraft.level.Level;
import com.mojang.minecraft.level.liquid.LiquidType;
import com.mojang.minecraft.level.tile.Block;
import com.mojang.minecraft.phys.AABB;
import com.mojang.minecraft.render.ShapeRender;
import java.util.Random;

public class LiquidBlock extends Block {

   protected LiquidType al;
   protected int am;
   protected int an;


   protected LiquidBlock(int var1, LiquidType var2) {
      super(var1);
      this.al = var2;
      this.ab = 14;
      if(var2 == LiquidType.c) {
         this.ab = 30;
      }

      Block.d[var1] = true;
      this.an = var1;
      this.am = var1 + 1;
      float var4 = 0.01F;
      float var3 = 0.1F;
      this.a(var4 + 0.0F, 0.0F - var3 + var4, var4 + 0.0F, var4 + 1.0F, 1.0F - var3 + var4, var4 + 1.0F);
      this.a(true);
      if(var2 == LiquidType.c) {
         this.a(16);
      }

   }

   public final boolean a() {
      return false;
   }

   public final void b(Level var1, int var2, int var3, int var4) {
      var1.addToTickNextTick(var2, var3, var4, this.an);
   }

   public void a(Level var1, int var2, int var3, int var4, Random var5) {
      boolean var7 = false;
      var4 = var4;
      var3 = var3;
      var2 = var2;
      var1 = var1;
      this = this;
      boolean var8 = false;

      boolean var6;
      do {
         --var3;
         if(var1.getTile(var2, var3, var4) != 0 || !this.g(var1, var2, var3, var4)) {
            break;
         }

         if(var6 = var1.setTile(var2, var3, var4, this.an)) {
            var8 = true;
         }
      } while(var6 && this.al != LiquidType.c);

      ++var3;
      if(this.al == LiquidType.b || !var8) {
         var8 = var8 | this.h(var1, var2 - 1, var3, var4) | this.h(var1, var2 + 1, var3, var4) | this.h(var1, var2, var3, var4 - 1) | this.h(var1, var2, var3, var4 + 1);
      }

      if(!var8) {
         var1.setTileNoUpdate(var2, var3, var4, this.am);
      } else {
         var1.addToTickNextTick(var2, var3, var4, this.an);
      }

   }

   private boolean g(Level var1, int var2, int var3, int var4) {
      if(this.al == LiquidType.b) {
         for(int var7 = var2 - 2; var7 <= var2 + 2; ++var7) {
            for(int var5 = var3 - 2; var5 <= var3 + 2; ++var5) {
               for(int var6 = var4 - 2; var6 <= var4 + 2; ++var6) {
                  if(var1.getTile(var7, var5, var6) == Block.w.ac) {
                     return false;
                  }
               }
            }
         }
      }

      return true;
   }

   private boolean h(Level var1, int var2, int var3, int var4) {
      if(var1.getTile(var2, var3, var4) == 0) {
         if(!this.g(var1, var2, var3, var4)) {
            return false;
         }

         if(var1.setTile(var2, var3, var4, this.an)) {
            var1.addToTickNextTick(var2, var3, var4, this.an);
         }
      }

      return false;
   }

   protected final float a(Level var1, int var2, int var3, int var4) {
      return this.al == LiquidType.c?100.0F:var1.getBrightness(var2, var3, var4);
   }

   public final boolean a(Level var1, int var2, int var3, int var4, int var5) {
      int var6;
      return var2 >= 0 && var3 >= 0 && var4 >= 0 && var2 < var1.width && var4 < var1.height?((var6 = var1.getTile(var2, var3, var4)) != this.an && var6 != this.am?(var5 == 1 && (var1.getTile(var2 - 1, var3, var4) == 0 || var1.getTile(var2 + 1, var3, var4) == 0 || var1.getTile(var2, var3, var4 - 1) == 0 || var1.getTile(var2, var3, var4 + 1) == 0)?true:super.a(var1, var2, var3, var4, var5)):false):false;
   }

   public final void a(ShapeRender var1, int var2, int var3, int var4, int var5) {
      super.a(var1, var2, var3, var4, var5);
      super.b(var1, var2, var3, var4, var5);
   }

   public final AABB b(int var1, int var2, int var3) {
      return null;
   }

   public final boolean b() {
      return true;
   }

   public final boolean c() {
      return false;
   }

   public final LiquidType d() {
      return this.al;
   }

   public void b(Level var1, int var2, int var3, int var4, int var5) {
      if(var5 != 0) {
         LiquidType var6 = Block.b[var5].d();
         if(this.al == LiquidType.b && var6 == LiquidType.c || var6 == LiquidType.b && this.al == LiquidType.c) {
            var1.setTile(var2, var3, var4, Block.e.ac);
            return;
         }
      }

      var1.addToTickNextTick(var2, var3, var4, var5);
   }

   public final int e() {
      return this.al == LiquidType.c?5:0;
   }

   public final void a(Level var1, int var2, int var3, int var4, float var5) {}

   public final void e(Level var1, int var2, int var3, int var4) {}

   public final int f() {
      return 0;
   }

   public final int j() {
      return this.al == LiquidType.b?1:0;
   }
}
