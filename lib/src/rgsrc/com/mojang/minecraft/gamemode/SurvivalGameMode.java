package com.mojang.minecraft.gamemode;

import com.mojang.minecraft.Entity;
import com.mojang.minecraft.ProgressBarDisplay;
import com.mojang.minecraft.gamemode.GameMode;
import com.mojang.minecraft.level.Level;
import com.mojang.minecraft.level.MobSpawner;
import com.mojang.minecraft.level.tile.Block;
import com.mojang.minecraft.mob.Mob;
import com.mojang.minecraft.player.Player;

public final class SurvivalGameMode extends GameMode {

   private int hitX;
   private int hitY;
   private int hitZ;
   private int hits;
   private int blockHardness;
   private int hitDelay;
   private MobSpawner mobSpawner;


   public final void b(Player var1) {
      var1.inventory.slots[8] = Block.X.ac;
      var1.inventory.count[8] = 10;
   }

   public final void b(int var1, int var2, int var3) {
      int var4 = this.minecraft.level.getTile(var1, var2, var3);
      Block.b[var4].e(this.minecraft.level, var1, var2, var3);
      super.b(var1, var2, var3);
   }

   public final boolean a(int var1) {
      return this.minecraft.player.inventory.removeResource(var1);
   }

   public final void a(int var1, int var2, int var3) {
      int var4;
      if((var4 = this.minecraft.level.getTile(var1, var2, var3)) > 0 && Block.b[var4].h() == 0) {
         this.b(var1, var2, var3);
      }

   }

   public final void c() {
      this.hits = 0;
      this.hitDelay = 0;
   }

   public final void a(int var1, int var2, int var3, int var4) {
      if(this.hitDelay > 0) {
         --this.hitDelay;
      } else if(var1 == this.hitX && var2 == this.hitY && var3 == this.hitZ) {
         int var5;
         if((var5 = this.minecraft.level.getTile(var1, var2, var3)) != 0) {
            Block var6 = Block.b[var5];
            this.blockHardness = var6.h();
            var6.a(this.minecraft.level, var1, var2, var3, var4, this.minecraft.particleManager);
            ++this.hits;
            if(this.hits == this.blockHardness + 1) {
               this.b(var1, var2, var3);
               this.hits = 0;
               this.hitDelay = 5;
            }

         }
      } else {
         this.hits = 0;
         this.hitX = var1;
         this.hitY = var2;
         this.hitZ = var3;
      }
   }

   public final void a(float var1) {
      if(this.hits <= 0) {
         this.minecraft.a.i = 0.0F;
      } else {
         this.minecraft.a.i = ((float)this.hits + var1 - 1.0F) / (float)this.blockHardness;
      }
   }

   public final float d() {
      return 4.0F;
   }

   public final boolean a(Player var1, int var2) {
      Block var3;
      if((var3 = Block.b[var2]) == Block.R && this.minecraft.player.inventory.removeResource(var2)) {
         var1.hurt((Entity)null, 3);
         return true;
      } else if(var3 == Block.Q && this.minecraft.player.inventory.removeResource(var2)) {
         var1.heal(5);
         return true;
      } else {
         return false;
      }
   }

   public final void a(Level var1) {
      super.a(var1);
      this.mobSpawner = new MobSpawner(var1);
   }

   public final void e() {
      MobSpawner var3;
      int var1 = (var3 = this.mobSpawner).level.width * var3.level.height * var3.level.depth / 64 / 64 / 64;
      if(var3.level.random.nextInt(100) < var1 && var3.level.countInstanceOf(Mob.class) < var1 * 20) {
         var3.a(var1, var3.level.player, (ProgressBarDisplay)null);
      }

   }

   public final void b(Level var1) {
      this.mobSpawner = new MobSpawner(var1);
      (this = this).minecraft.progressBar.b("Spawning..");
      int var2 = var1.width * var1.height * var1.depth / 800;
      this.mobSpawner.a(var2, (Entity)null, this.minecraft.progressBar);
   }
}
