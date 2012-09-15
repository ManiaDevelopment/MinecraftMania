package com.mojang.minecraft.model;

import com.mojang.minecraft.model.Model;
import com.mojang.minecraft.model.ModelRenderer;
import com.mojang.util.MathHelper;

public final class CreeperModel extends Model {

   private ModelRenderer b = new ModelRenderer(0, 0);
   private ModelRenderer c;
   private ModelRenderer d;
   private ModelRenderer e;
   private ModelRenderer f;
   private ModelRenderer g;
   private ModelRenderer h;


   public CreeperModel() {
      this.b.a(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
      this.c = new ModelRenderer(32, 0);
      this.c.a(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F + 0.5F);
      this.d = new ModelRenderer(16, 16);
      this.d.a(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
      this.e = new ModelRenderer(0, 16);
      this.e.a(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
      this.e.a(-2.0F, 12.0F, 4.0F);
      this.f = new ModelRenderer(0, 16);
      this.f.a(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
      this.f.a(2.0F, 12.0F, 4.0F);
      this.g = new ModelRenderer(0, 16);
      this.g.a(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
      this.g.a(-2.0F, 12.0F, -4.0F);
      this.h = new ModelRenderer(0, 16);
      this.h.a(-2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F);
      this.h.a(2.0F, 12.0F, -4.0F);
   }

   public final void a(float var1, float var2, float var3, float var4, float var5, float var6) {
      this.b.g = var4 / 57.295776F;
      this.b.f = var5 / 57.295776F;
      this.e.f = MathHelper.b(var1 * 0.6662F) * 1.4F * var2;
      this.f.f = MathHelper.b(var1 * 0.6662F + 3.1415927F) * 1.4F * var2;
      this.g.f = MathHelper.b(var1 * 0.6662F + 3.1415927F) * 1.4F * var2;
      this.h.f = MathHelper.b(var1 * 0.6662F) * 1.4F * var2;
      this.b.a(var6);
      this.d.a(var6);
      this.e.a(var6);
      this.f.a(var6);
      this.g.a(var6);
      this.h.a(var6);
   }
}
