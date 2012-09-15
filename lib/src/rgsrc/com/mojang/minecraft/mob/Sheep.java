package com.mojang.minecraft.mob;

import com.mojang.minecraft.Entity;
import com.mojang.minecraft.item.Item;
import com.mojang.minecraft.level.Level;
import com.mojang.minecraft.level.tile.Block;
import com.mojang.minecraft.mob.QuadrupedMob;
import com.mojang.minecraft.mob.Sheep$1;
import com.mojang.minecraft.model.AnimalModel;
import com.mojang.minecraft.player.Player;
import com.mojang.minecraft.render.TextureManager;
import org.lwjgl.opengl.GL11;

public class Sheep extends QuadrupedMob {

   public static final long serialVersionUID = 0L;
   public boolean hasFur = true;
   public boolean grazing = false;
   public int grazingTime = 0;
   public float graze;
   public float grazeO;


   public Sheep(Level var1, float var2, float var3, float var4) {
      super(var1, var2, var3, var4);
      this.setSize(1.4F, 1.72F);
      this.setPos(var2, var3, var4);
      this.heightOffset = 1.72F;
      this.modelName = "sheep";
      this.textureName = "/mob/sheep.png";
      this.ai = new Sheep$1(this);
   }

   protected void aiStep() {
      super.aiStep();
      this.grazeO = this.graze;
      if(this.grazing) {
         this.graze += 0.2F;
      } else {
         this.graze -= 0.2F;
      }

      if(this.graze < 0.0F) {
         this.graze = 0.0F;
      }

      if(this.graze > 1.0F) {
         this.graze = 1.0F;
      }

   }

   public void die(Entity var1) {
      if(var1 != null) {
         var1.awardKillScore(this, 10);
      }

      int var2 = (int)(Math.random() + Math.random() + 1.0D);

      for(int var3 = 0; var3 < var2; ++var3) {
         this.level.addEntity(new Item(this.level, this.x, this.y, this.z, Block.Q.ac));
      }

      super.die(var1);
   }

   public void hurt(Entity var1, int var2) {
      if(this.hasFur && var1 instanceof Player) {
         this.hasFur = false;
         int var3 = (int)(Math.random() * 3.0D + 1.0D);

         for(var2 = 0; var2 < var3; ++var2) {
            this.level.addEntity(new Item(this.level, this.x, this.y, this.z, Block.N.ac));
         }

      } else {
         super.hurt(var1, var2);
      }
   }

   public void renderModel$f1c6c5a(TextureManager var1, float var2, float var3, float var4, float var5, float var6, float var7) {
      AnimalModel var8;
      float var9 = (var8 = (AnimalModel)modelCache.a(this.modelName)).b.d;
      float var10 = var8.b.e;
      var8.b.d += (this.grazeO + (this.graze - this.grazeO) * var3) * 8.0F;
      var8.b.e -= this.grazeO + (this.graze - this.grazeO) * var3;
      super.renderModel$f1c6c5a(var1, var2, var3, var4, var5, var6, var7);
      if(this.hasFur) {
         GL11.glBindTexture(3553, var1.a("/mob/sheep_fur.png"));
         GL11.glDisable(2884);
         AnimalModel var11;
         (var11 = (AnimalModel)modelCache.a("sheep.fur")).b.g = var8.b.g;
         var11.b.f = var8.b.f;
         var11.b.d = var8.b.d;
         var11.b.c = var8.b.c;
         var11.c.g = var8.c.g;
         var11.c.f = var8.c.f;
         var11.d.f = var8.d.f;
         var11.e.f = var8.e.f;
         var11.f.f = var8.f.f;
         var11.g.f = var8.g.f;
         var11.b.a(var7);
         var11.c.a(var7);
         var11.d.a(var7);
         var11.e.a(var7);
         var11.f.a(var7);
         var11.g.a(var7);
      }

      var8.b.d = var9;
      var8.b.e = var10;
   }
}
