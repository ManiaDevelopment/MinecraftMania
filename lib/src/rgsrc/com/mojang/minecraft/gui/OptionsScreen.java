package com.mojang.minecraft.gui;

import com.mojang.minecraft.GameSettings;
import com.mojang.minecraft.gui.Button;
import com.mojang.minecraft.gui.ControlsScreen;
import com.mojang.minecraft.gui.OptionButton;
import com.mojang.minecraft.gui.PopUpScreen;

public final class OptionsScreen extends PopUpScreen {

   private PopUpScreen parent;
   private String title = "Options";
   private GameSettings settings;


   public OptionsScreen(PopUpScreen var1, GameSettings var2) {
      this.parent = var1;
      this.settings = var2;
   }

   public final void a() {
      for(int var1 = 0; var1 < this.settings.t; ++var1) {
         this.d.add(new OptionButton(var1, this.b / 2 - 155 + var1 % 2 * 160, this.c / 6 + 24 * (var1 >> 1), this.settings.b(var1)));
      }

      this.d.add(new Button(100, this.b / 2 - 100, this.c / 6 + 120 + 12, "Controls..."));
      this.d.add(new Button(200, this.b / 2 - 100, this.c / 6 + 168, "Done"));
   }

   protected final void a(Button var1) {
      if(var1.g) {
         if(var1.f < 100) {
            this.settings.b(var1.f, 1);
            var1.e = this.settings.b(var1.f);
         }

         if(var1.f == 100) {
            this.a.a((PopUpScreen)(new ControlsScreen(this, this.settings)));
         }

         if(var1.f == 200) {
            this.a.a(this.parent);
         }

      }
   }

   public final void a(int var1, int var2) {
      a(0, 0, this.b, this.c, 1610941696, -1607454624);
      a(this.f, this.title, this.b / 2, 20, 16777215);
      super.a(var1, var2);
   }
}
