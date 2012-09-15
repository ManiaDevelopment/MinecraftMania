package com.mojang.minecraft.model;

import com.mojang.minecraft.model.Model;
import com.mojang.minecraft.model.ModelRenderer;
import com.mojang.util.MathHelper;

public class HumanoidModel extends Model {

   public ModelRenderer b;
   public ModelRenderer c;
   public ModelRenderer d;
   public ModelRenderer e;
   public ModelRenderer f;
   public ModelRenderer g;
   public ModelRenderer h;


   public HumanoidModel() {
      this(0.0F);
   }

   public HumanoidModel(float var1) {
      this.b = new ModelRenderer(0, 0);
      this.b.a(-4.0F, -8.0F, -4.0F, 8, 8, 8, var1);
      this.c = new ModelRenderer(32, 0);
      this.c.a(-4.0F, -8.0F, -4.0F, 8, 8, 8, var1 + 0.5F);
      this.d = new ModelRenderer(16, 16);
      this.d.a(-4.0F, 0.0F, -2.0F, 8, 12, 4, var1);
      this.e = new ModelRenderer(40, 16);
      this.e.a(-3.0F, -2.0F, -2.0F, 4, 12, 4, var1);
      this.e.a(-5.0F, 2.0F, 0.0F);
      this.f = new ModelRenderer(40, 16);
      this.f.k = true;
      this.f.a(-1.0F, -2.0F, -2.0F, 4, 12, 4, var1);
      this.f.a(5.0F, 2.0F, 0.0F);
      this.g = new ModelRenderer(0, 16);
      this.g.a(-2.0F, 0.0F, -2.0F, 4, 12, 4, var1);
      this.g.a(-2.0F, 12.0F, 0.0F);
      this.h = new ModelRenderer(0, 16);
      this.h.k = true;
      this.h.a(-2.0F, 0.0F, -2.0F, 4, 12, 4, var1);
      this.h.a(2.0F, 12.0F, 0.0F);
   }

   public final void a(float var1, float var2, float var3, float var4, float var5, float var6) {
      this.b(var1, var2, var3, var4, var5, var6);
      this.b.a(var6);
      this.d.a(var6);
      this.e.a(var6);
      this.f.a(var6);
      this.g.a(var6);
      this.h.a(var6);
   }

   public void b(float var1, float var2, float var3, float var4, float var5, float var6) {
      this.b.g = var4 / 57.295776F;
      this.b.f = var5 / 57.295776F;
      this.e.f = MathHelper.b(var1 * 0.6662F + 3.1415927F) * 2.0F * var2;
      this.e.h = (MathHelper.b(var1 * 0.2312F) + 1.0F) * var2;
      this.f.f = MathHelper.b(var1 * 0.6662F) * 2.0F * var2;
      this.f.h = (MathHelper.b(var1 * 0.2812F) - 1.0F) * var2;
      this.g.f = MathHelper.b(var1 * 0.6662F) * 1.4F * var2;
      this.h.f = MathHelper.b(var1 * 0.6662F + 3.1415927F) * 1.4F * var2;
      this.e.h += MathHelper.b(var3 * 0.09F) * 0.05F + 0.05F;
      this.f.h -= MathHelper.b(var3 * 0.09F) * 0.05F + 0.05F;
      this.e.f += MathHelper.a(var3 * 0.067F) * 0.05F;
      this.f.f -= MathHelper.a(var3 * 0.067F) * 0.05F;
   }
}
