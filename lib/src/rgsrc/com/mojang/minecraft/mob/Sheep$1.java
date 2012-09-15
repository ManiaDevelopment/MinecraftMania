package com.mojang.minecraft.mob;

import com.mojang.minecraft.level.tile.Block;
import com.mojang.minecraft.mob.Sheep;
import com.mojang.minecraft.mob.ai.BasicAI;
import com.mojang.util.MathHelper;

final class Sheep$1 extends BasicAI {

   private static final long serialVersionUID = 1L;
   // $FF: synthetic field
   final Sheep this$0;


   Sheep$1(Sheep var1) {
      this.this$0 = var1;
   }

   protected final void update() {
      float var1 = MathHelper.a(this.this$0.yRot * 3.1415927F / 180.0F);
      float var2 = MathHelper.b(this.this$0.yRot * 3.1415927F / 180.0F);
      var1 = -0.7F * var1;
      var2 = 0.7F * var2;
      int var4 = (int)(this.mob.x + var1);
      int var3 = (int)(this.mob.y - 2.0F);
      int var5 = (int)(this.mob.z + var2);
      if(this.this$0.grazing) {
         if(this.level.getTile(var4, var3, var5) != Block.f.ac) {
            this.this$0.grazing = false;
         } else {
            if(++this.this$0.grazingTime == 60) {
               this.level.setTile(var4, var3, var5, Block.g.ac);
               if(this.random.nextInt(5) == 0) {
                  this.this$0.hasFur = true;
               }
            }

            this.xxa = 0.0F;
            this.yya = 0.0F;
            this.mob.xRot = (float)(40 + this.this$0.grazingTime / 2 % 2 * 10);
         }
      } else {
         if(this.level.getTile(var4, var3, var5) == Block.f.ac) {
            this.this$0.grazing = true;
            this.this$0.grazingTime = 0;
         }

         super.update();
      }
   }
}
