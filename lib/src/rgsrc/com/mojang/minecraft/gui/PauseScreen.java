package com.mojang.minecraft.gui;

import com.mojang.minecraft.gui.Button;
import com.mojang.minecraft.gui.GenerateLevelScreen;
import com.mojang.minecraft.gui.LoadLevelScreen;
import com.mojang.minecraft.gui.OptionsScreen;
import com.mojang.minecraft.gui.PopUpScreen;
import com.mojang.minecraft.gui.SaveLevelScreen;

public final class PauseScreen extends PopUpScreen {

   public final void a() {
      this.d.clear();
      this.d.add(new Button(0, this.b / 2 - 100, this.c / 4, "Options..."));
      this.d.add(new Button(1, this.b / 2 - 100, this.c / 4 + 24, "Generate new level..."));
      this.d.add(new Button(2, this.b / 2 - 100, this.c / 4 + 48, "Save level.."));
      this.d.add(new Button(3, this.b / 2 - 100, this.c / 4 + 72, "Load level.."));
      this.d.add(new Button(4, this.b / 2 - 100, this.c / 4 + 120, "Back to game"));
      if(this.a.sessionData == null) {
         ((Button)this.d.get(2)).g = false;
         ((Button)this.d.get(3)).g = false;
      }

      if(this.a.networkManager != null) {
         ((Button)this.d.get(1)).g = false;
         ((Button)this.d.get(2)).g = false;
         ((Button)this.d.get(3)).g = false;
      }

   }

   protected final void a(Button var1) {
      if(var1.f == 0) {
         this.a.a((PopUpScreen)(new OptionsScreen(this, this.a.settings)));
      }

      if(var1.f == 1) {
         this.a.a((PopUpScreen)(new GenerateLevelScreen(this)));
      }

      if(this.a.sessionData != null) {
         if(var1.f == 2) {
            this.a.a((PopUpScreen)(new SaveLevelScreen(this)));
         }

         if(var1.f == 3) {
            this.a.a((PopUpScreen)(new LoadLevelScreen(this)));
         }
      }

      if(var1.f == 4) {
         this.a.a((PopUpScreen)null);
         this.a.b();
      }

   }

   public final void a(int var1, int var2) {
      a(0, 0, this.b, this.c, 1610941696, -1607454624);
      a(this.f, "Game menu", this.b / 2, 40, 16777215);
      super.a(var1, var2);
   }
}
