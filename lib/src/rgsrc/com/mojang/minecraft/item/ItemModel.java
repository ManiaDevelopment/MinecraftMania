package com.mojang.minecraft.item;

import com.mojang.minecraft.model.ModelRenderer;
import com.mojang.minecraft.model.PositionTextureVertex;
import com.mojang.minecraft.model.TexturedQuad;

public final class ItemModel {

   private ModelRenderer modelRenderer = new ModelRenderer(0, 0);


   public ItemModel(int var1) {
      ModelRenderer var10000 = this.modelRenderer;
      int var2 = var1;
      boolean var14 = true;
      var14 = true;
      var14 = true;
      float var3 = -2.0F;
      float var4 = -2.0F;
      float var15 = -2.0F;
      ModelRenderer var16 = var10000;
      var10000.a = new PositionTextureVertex[8];
      var16.b = new TexturedQuad[6];
      PositionTextureVertex var5 = new PositionTextureVertex(var15, var4, var3, 0.0F, 0.0F);
      PositionTextureVertex var6 = new PositionTextureVertex(2.0F, var4, var3, 0.0F, 8.0F);
      PositionTextureVertex var7 = new PositionTextureVertex(2.0F, 2.0F, var3, 8.0F, 8.0F);
      PositionTextureVertex var19 = new PositionTextureVertex(var15, 2.0F, var3, 8.0F, 0.0F);
      PositionTextureVertex var8 = new PositionTextureVertex(var15, var4, 2.0F, 0.0F, 0.0F);
      PositionTextureVertex var20 = new PositionTextureVertex(2.0F, var4, 2.0F, 0.0F, 8.0F);
      PositionTextureVertex var9 = new PositionTextureVertex(2.0F, 2.0F, 2.0F, 8.0F, 8.0F);
      PositionTextureVertex var17 = new PositionTextureVertex(var15, 2.0F, 2.0F, 8.0F, 0.0F);
      var16.a[0] = var5;
      var16.a[1] = var6;
      var16.a[2] = var7;
      var16.a[3] = var19;
      var16.a[4] = var8;
      var16.a[5] = var20;
      var16.a[6] = var9;
      var16.a[7] = var17;
      float var10 = 0.25F;
      float var11 = 0.25F;
      float var12 = ((float)(var2 % 16) + (1.0F - var10)) / 16.0F;
      float var13 = ((float)(var2 / 16) + (1.0F - var11)) / 16.0F;
      var10 = ((float)(var2 % 16) + var10) / 16.0F;
      float var18 = ((float)(var2 / 16) + var11) / 16.0F;
      var16.b[0] = new TexturedQuad(new PositionTextureVertex[]{var20, var6, var7, var9}, var12, var13, var10, var18);
      var16.b[1] = new TexturedQuad(new PositionTextureVertex[]{var5, var8, var17, var19}, var12, var13, var10, var18);
      var16.b[2] = new TexturedQuad(new PositionTextureVertex[]{var20, var8, var5, var6}, var12, var13, var10, var18);
      var16.b[3] = new TexturedQuad(new PositionTextureVertex[]{var7, var19, var17, var9}, var12, var13, var10, var18);
      var16.b[4] = new TexturedQuad(new PositionTextureVertex[]{var6, var5, var19, var7}, var12, var13, var10, var18);
      var16.b[5] = new TexturedQuad(new PositionTextureVertex[]{var8, var20, var9, var17}, var12, var13, var10, var18);
   }

   public final void a() {
      this.modelRenderer.a(0.0625F);
   }
}
