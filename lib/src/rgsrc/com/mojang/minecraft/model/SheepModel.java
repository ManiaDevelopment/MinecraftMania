package com.mojang.minecraft.model;

import com.mojang.minecraft.model.AnimalModel;
import com.mojang.minecraft.model.ModelRenderer;

public final class SheepModel extends AnimalModel {

   public SheepModel() {
      super(12, 0.0F);
      this.b = new ModelRenderer(0, 0);
      this.b.a(-3.0F, -4.0F, -6.0F, 6, 6, 8, 0.0F);
      this.b.a(0.0F, 6.0F, -8.0F);
      this.c = new ModelRenderer(28, 8);
      this.c.a(-4.0F, -10.0F, -7.0F, 8, 16, 6, 0.0F);
      this.c.a(0.0F, 5.0F, 2.0F);
   }
}
