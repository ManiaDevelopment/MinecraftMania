package com.mojang.minecraft.model;

import com.mojang.minecraft.model.Model;
import com.mojang.minecraft.model.ModelRenderer;
import com.mojang.util.MathHelper;

public class AnimalModel extends Model {

   public ModelRenderer b = new ModelRenderer(0, 0);
   public ModelRenderer c;
   public ModelRenderer d;
   public ModelRenderer e;
   public ModelRenderer f;
   public ModelRenderer g;


   public AnimalModel(int var1, float var2) {
      this.b.a(-4.0F, -4.0F, -8.0F, 8, 8, 8, 0.0F);
      this.b.a(0.0F, (float)(18 - var1), -6.0F);
      this.c = new ModelRenderer(28, 8);
      this.c.a(-5.0F, -10.0F, -7.0F, 10, 16, 8, 0.0F);
      this.c.a(0.0F, (float)(17 - var1), 2.0F);
      this.d = new ModelRenderer(0, 16);
      this.d.a(-2.0F, 0.0F, -2.0F, 4, var1, 4, 0.0F);
      this.d.a(-3.0F, (float)(24 - var1), 7.0F);
      this.e = new ModelRenderer(0, 16);
      this.e.a(-2.0F, 0.0F, -2.0F, 4, var1, 4, 0.0F);
      this.e.a(3.0F, (float)(24 - var1), 7.0F);
      this.f = new ModelRenderer(0, 16);
      this.f.a(-2.0F, 0.0F, -2.0F, 4, var1, 4, 0.0F);
      this.f.a(-3.0F, (float)(24 - var1), -5.0F);
      this.g = new ModelRenderer(0, 16);
      this.g.a(-2.0F, 0.0F, -2.0F, 4, var1, 4, 0.0F);
      this.g.a(3.0F, (float)(24 - var1), -5.0F);
   }

   public final void a(float var1, float var2, float var3, float var4, float var5, float var6) {
      this.b.g = var4 / 57.295776F;
      this.b.f = var5 / 57.295776F;
      this.c.f = 1.5707964F;
      this.d.f = MathHelper.b(var1 * 0.6662F) * 1.4F * var2;
      this.e.f = MathHelper.b(var1 * 0.6662F + 3.1415927F) * 1.4F * var2;
      this.f.f = MathHelper.b(var1 * 0.6662F + 3.1415927F) * 1.4F * var2;
      this.g.f = MathHelper.b(var1 * 0.6662F) * 1.4F * var2;
      this.b.a(var6);
      this.c.a(var6);
      this.d.a(var6);
      this.e.a(var6);
      this.f.a(var6);
      this.g.a(var6);
   }
}
