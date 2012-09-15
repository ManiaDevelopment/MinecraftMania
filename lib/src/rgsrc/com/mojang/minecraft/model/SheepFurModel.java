package com.mojang.minecraft.model;

import com.mojang.minecraft.model.AnimalModel;
import com.mojang.minecraft.model.ModelRenderer;

public final class SheepFurModel extends AnimalModel {

   public SheepFurModel() {
      super(12, 0.0F);
      this.b = new ModelRenderer(0, 0);
      this.b.a(-3.0F, -4.0F, -4.0F, 6, 6, 6, 0.6F);
      this.b.a(0.0F, 6.0F, -8.0F);
      this.c = new ModelRenderer(28, 8);
      this.c.a(-4.0F, -10.0F, -7.0F, 8, 16, 6, 1.75F);
      this.c.a(0.0F, 5.0F, 2.0F);
      float var1 = 0.5F;
      this.d = new ModelRenderer(0, 16);
      this.d.a(-2.0F, 0.0F, -2.0F, 4, 6, 4, var1);
      this.d.a(-3.0F, 12.0F, 7.0F);
      this.e = new ModelRenderer(0, 16);
      this.e.a(-2.0F, 0.0F, -2.0F, 4, 6, 4, var1);
      this.e.a(3.0F, 12.0F, 7.0F);
      this.f = new ModelRenderer(0, 16);
      this.f.a(-2.0F, 0.0F, -2.0F, 4, 6, 4, var1);
      this.f.a(-3.0F, 12.0F, -5.0F);
      this.g = new ModelRenderer(0, 16);
      this.g.a(-2.0F, 0.0F, -2.0F, 4, 6, 4, var1);
      this.g.a(3.0F, 12.0F, -5.0F);
   }
}
