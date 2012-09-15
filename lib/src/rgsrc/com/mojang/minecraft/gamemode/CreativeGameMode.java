package com.mojang.minecraft.gamemode;

import com.mojang.minecraft.AvailableBlockType;
import com.mojang.minecraft.Minecraft;
import com.mojang.minecraft.gamemode.GameMode;
import com.mojang.minecraft.gui.BlockSelectScreen;
import com.mojang.minecraft.gui.PopUpScreen;
import com.mojang.minecraft.level.Level;
import com.mojang.minecraft.level.tile.Block;
import com.mojang.minecraft.player.Player;

public final class CreativeGameMode extends GameMode {

   public CreativeGameMode(Minecraft var1) {
      super(var1);
      this.creative = true;
   }

   public final void a() {
      this.minecraft.a((PopUpScreen)(new BlockSelectScreen()));
   }

   public final void a(Level var1) {
      super.a(var1);
      var1.removeAllNonCreativeModeEntities();
      var1.creativeMode = true;
      var1.growTrees = false;
   }

   public final void a(Player var1) {
      for(int var2 = 0; var2 < 9; ++var2) {
         var1.inventory.count[var2] = 1;
         if(var1.inventory.slots[var2] <= 0) {
            var1.inventory.slots[var2] = ((Block)AvailableBlockType.a.get(var2)).ac;
         }
      }

   }

   public final boolean b() {
      return false;
   }
}
