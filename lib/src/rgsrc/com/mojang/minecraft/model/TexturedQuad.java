package com.mojang.minecraft.model;

import com.mojang.minecraft.model.PositionTextureVertex;

public final class TexturedQuad {

   public PositionTextureVertex[] a;


   private TexturedQuad(PositionTextureVertex[] var1) {
      this.a = var1;
   }

   public TexturedQuad(PositionTextureVertex[] var1, int var2, int var3, int var4, int var5) {
      this(var1);
      float var7 = 0.0015625F;
      float var6 = 0.003125F;
      var1[0] = var1[0].a((float)var4 / 64.0F - var7, (float)var3 / 32.0F + var6);
      var1[1] = var1[1].a((float)var2 / 64.0F + var7, (float)var3 / 32.0F + var6);
      var1[2] = var1[2].a((float)var2 / 64.0F + var7, (float)var5 / 32.0F - var6);
      var1[3] = var1[3].a((float)var4 / 64.0F - var7, (float)var5 / 32.0F - var6);
   }

   public TexturedQuad(PositionTextureVertex[] var1, float var2, float var3, float var4, float var5) {
      this(var1);
      var1[0] = var1[0].a(var4, var3);
      var1[1] = var1[1].a(var2, var3);
      var1[2] = var1[2].a(var2, var5);
      var1[3] = var1[3].a(var4, var5);
   }
}
