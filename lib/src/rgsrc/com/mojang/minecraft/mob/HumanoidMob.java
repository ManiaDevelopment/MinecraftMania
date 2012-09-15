package com.mojang.minecraft.mob;

import com.mojang.minecraft.level.Level;
import com.mojang.minecraft.mob.Mob;
import com.mojang.minecraft.model.HumanoidModel;
import com.mojang.minecraft.model.Model;
import com.mojang.minecraft.render.TextureManager;
import org.lwjgl.opengl.GL11;

public class HumanoidMob extends Mob {

   public static final long serialVersionUID = 0L;
   public boolean helmet = Math.random() < 0.20000000298023224D;
   public boolean armor = Math.random() < 0.20000000298023224D;


   public HumanoidMob(Level var1, float var2, float var3, float var4) {
      super(var1);
      this.modelName = "humanoid";
      this.setPos(var2, var3, var4);
   }

   public void renderModel$f1c6c5a(TextureManager var1, float var2, float var3, float var4, float var5, float var6, float var7) {
      super.renderModel$f1c6c5a(var1, var2, var3, var4, var5, var6, var7);
      Model var9 = modelCache.a(this.modelName);
      GL11.glEnable(3008);
      if(this.allowAlpha) {
         GL11.glEnable(2884);
      }

      if(this.hasHair) {
         GL11.glDisable(2884);
         HumanoidModel var10;
         (var10 = (HumanoidModel)var9).c.g = var10.b.g;
         var10.c.f = var10.b.f;
         var10.c.a(var7);
         GL11.glEnable(2884);
      }

      if(this.armor || this.helmet) {
         GL11.glBindTexture(3553, var1.a("/armor/plate.png"));
         GL11.glDisable(2884);
         HumanoidModel var8;
         (var8 = (HumanoidModel)modelCache.a("humanoid.armor")).b.l = this.helmet;
         var8.d.l = this.armor;
         var8.e.l = this.armor;
         var8.f.l = this.armor;
         var8.g.l = false;
         var8.h.l = false;
         HumanoidModel var11 = (HumanoidModel)var9;
         var8.b.g = var11.b.g;
         var8.b.f = var11.b.f;
         var8.e.f = var11.e.f;
         var8.e.h = var11.e.h;
         var8.f.f = var11.f.f;
         var8.f.h = var11.f.h;
         var8.g.f = var11.g.f;
         var8.h.f = var11.h.f;
         var8.b.a(var7);
         var8.d.a(var7);
         var8.e.a(var7);
         var8.f.a(var7);
         var8.g.a(var7);
         var8.h.a(var7);
         GL11.glEnable(2884);
      }

      GL11.glDisable(3008);
   }
}
