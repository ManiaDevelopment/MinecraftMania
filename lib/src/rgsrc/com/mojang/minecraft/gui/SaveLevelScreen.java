package com.mojang.minecraft.gui;

import com.mojang.minecraft.Minecraft;
import com.mojang.minecraft.gui.Button;
import com.mojang.minecraft.gui.LevelNameScreen;
import com.mojang.minecraft.gui.LoadLevelScreen;
import com.mojang.minecraft.gui.PopUpScreen;
import java.io.File;

public final class SaveLevelScreen extends LoadLevelScreen {

   public SaveLevelScreen(PopUpScreen var1) {
      super(var1);
      this.h = "Save level";
      this.l = true;
   }

   public final void a() {
      super.a();
      ((Button)this.d.get(5)).e = "Save file...";
   }

   protected final void a(String[] var1) {
      for(int var2 = 0; var2 < 5; ++var2) {
         ((Button)this.d.get(var2)).e = var1[var2];
         ((Button)this.d.get(var2)).h = true;
         ((Button)this.d.get(var2)).g = this.a.sessionData.e;
      }

   }

   public final void a(int var1, int var2) {
      super.a(var1, var2);
      if(!this.a.sessionData.e) {
         a(this.b / 2 - 80, 72, this.b / 2 + 80, 120, -536870912, -536870912);
         a(this.f, "Premium only!", this.b / 2, 80, 16748688);
         a(this.f, "Purchase the game to be able", this.b / 2, 96, 14712960);
         a(this.f, "to save your levels online.", this.b / 2, 104, 14712960);
      }
   }

   protected final void a(File var1) {
      if(!var1.getName().endsWith(".mine")) {
         var1 = new File(var1.getParentFile(), var1.getName() + ".mine");
      }

      File var2 = var1;
      Minecraft var3 = this.a;
      this.a.levelIO.a(var3.level, var2);
      this.a.a(this.g);
   }

   protected final void a(int var1) {
      this.a.a((PopUpScreen)(new LevelNameScreen(this, ((Button)this.d.get(var1)).e, var1)));
   }
}
