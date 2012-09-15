package com.mojang.minecraft.gamemode;

import com.mojang.minecraft.Minecraft;
import com.mojang.minecraft.level.Level;
import com.mojang.minecraft.level.tile.Block;
import com.mojang.minecraft.level.tile.Tile$SoundType;
import com.mojang.minecraft.player.Player;

public class GameMode {

   protected final Minecraft minecraft;
   public boolean creative = false;


   public GameMode(Minecraft var1) {
      this.minecraft = var1;
   }

   public void a(Level var1) {
      var1.creativeMode = false;
      var1.growTrees = true;
   }

   public void a() {}

   public void a(int var1, int var2, int var3) {
      this.b(var1, var2, var3);
   }

   public boolean a(int var1) {
      return true;
   }

   public void b(int var1, int var2, int var3) {
      Level var4 = this.minecraft.level;
      Block var5 = Block.b[var4.getTile(var1, var2, var3)];
      boolean var6 = var4.netSetTile(var1, var2, var3, 0);
      if(var5 != null && var6) {
         if(this.minecraft.d()) {
            this.minecraft.networkManager.a(var1, var2, var3, 0, this.minecraft.player.inventory.getSelected());
         }

         if(var5.ad != Tile$SoundType.none) {
            var4.playSound("step." + var5.ad.name, (float)var1, (float)var2, (float)var3, (var5.ad.getVolume() + 1.0F) / 2.0F, var5.ad.getPitch() * 0.8F);
         }

         var5.a(var4, var1, var2, var3, this.minecraft.particleManager);
      }

   }

   public void a(int var1, int var2, int var3, int var4) {}

   public void c() {}

   public void a(float var1) {}

   public float d() {
      return 5.0F;
   }

   public boolean a(Player var1, int var2) {
      return false;
   }

   public void b(Player var1) {}

   public void e() {}

   public void b(Level var1) {}

   public boolean b() {
      return true;
   }

   public void a(Player var1) {}
}
