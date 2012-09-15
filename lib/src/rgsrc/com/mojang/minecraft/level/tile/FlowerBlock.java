package com.mojang.minecraft.level.tile;

import com.mojang.minecraft.level.Level;
import com.mojang.minecraft.level.tile.Block;
import com.mojang.minecraft.phys.AABB;
import com.mojang.minecraft.render.ShapeRender;
import com.mojang.util.MathHelper;
import java.util.Random;

public class FlowerBlock extends Block {

   protected FlowerBlock(int var1, int var2) {
      super(var1);
      this.ab = var2;
      this.a(true);
      float var3 = 0.2F;
      this.a(0.5F - var3, 0.0F, 0.5F - var3, var3 + 0.5F, var3 * 3.0F, var3 + 0.5F);
   }

   public void a(Level var1, int var2, int var3, int var4, Random var5) {
      if(!var1.growTrees) {
         int var6 = var1.getTile(var2, var3 - 1, var4);
         if(!var1.isLit(var2, var3, var4) || var6 != Block.g.ac && var6 != Block.f.ac) {
            var1.setTile(var2, var3, var4, 0);
         }

      }
   }

   private void a(ShapeRender var1, float var2, float var3, float var4) {
      int var15;
      int var5 = (var15 = this.b(15)) % 16 << 4;
      int var6 = var15 / 16 << 4;
      float var16 = (float)var5 / 256.0F;
      float var17 = ((float)var5 + 15.99F) / 256.0F;
      float var7 = (float)var6 / 256.0F;
      float var18 = ((float)var6 + 15.99F) / 256.0F;

      for(int var8 = 0; var8 < 2; ++var8) {
         float var9 = (float)((double)MathHelper.a((float)var8 * 3.1415927F / 2.0F + 0.7853982F) * 0.5D);
         float var10 = (float)((double)MathHelper.b((float)var8 * 3.1415927F / 2.0F + 0.7853982F) * 0.5D);
         float var11 = var2 + 0.5F - var9;
         var9 += var2 + 0.5F;
         float var13 = var3 + 1.0F;
         float var14 = var4 + 0.5F - var10;
         var10 += var4 + 0.5F;
         var1.a(var11, var13, var14, var17, var7);
         var1.a(var9, var13, var10, var16, var7);
         var1.a(var9, var3, var10, var16, var18);
         var1.a(var11, var3, var14, var17, var18);
         var1.a(var9, var13, var10, var17, var7);
         var1.a(var11, var13, var14, var16, var7);
         var1.a(var11, var3, var14, var16, var18);
         var1.a(var9, var3, var10, var17, var18);
      }

   }

   public final AABB b(int var1, int var2, int var3) {
      return null;
   }

   public final boolean b() {
      return false;
   }

   public final boolean c() {
      return false;
   }

   public final void b(ShapeRender var1) {
      var1.c(0.0F, 1.0F, 0.0F);
      var1.b();
      this.a(var1, 0.0F, 0.4F, -0.3F);
      var1.a();
   }

   public final boolean a() {
      return false;
   }

   public final boolean a(Level var1, int var2, int var3, int var4, ShapeRender var5) {
      float var6 = var1.getBrightness(var2, var3, var4);
      var5.a(var6, var6, var6);
      this.a(var5, (float)var2, (float)var3, (float)var4);
      return true;
   }

   public final void a(ShapeRender var1) {
      var1.a(1.0F, 1.0F, 1.0F);
      this.a(var1, (float)-2, 0.0F, 0.0F);
   }
}
