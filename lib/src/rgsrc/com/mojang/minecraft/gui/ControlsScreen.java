package com.mojang.minecraft.gui;

import com.mojang.minecraft.GameSettings;
import com.mojang.minecraft.gui.Button;
import com.mojang.minecraft.gui.OptionButton;
import com.mojang.minecraft.gui.PopUpScreen;

public final class ControlsScreen extends PopUpScreen {

   private PopUpScreen parent;
   private String title = "Controls";
   private GameSettings settings;
   private int binding = -1;


   public ControlsScreen(PopUpScreen var1, GameSettings var2) {
      this.parent = var1;
      this.settings = var2;
   }

   public final void a() {
      for(int var1 = 0; var1 < this.settings.s.length; ++var1) {
         this.d.add(new OptionButton(var1, this.b / 2 - 155 + var1 % 2 * 160, this.c / 6 + 24 * (var1 >> 1), this.settings.a(var1)));
      }

      this.d.add(new Button(200, this.b / 2 - 100, this.c / 6 + 168, "Done"));
   }

   protected final void a(Button var1) {
      for(int var2 = 0; var2 < this.settings.s.length; ++var2) {
         ((Button)this.d.get(var2)).e = this.settings.a(var2);
      }

      if(var1.f == 200) {
         this.a.a(this.parent);
      } else {
         this.binding = var1.f;
         var1.e = "> " + this.settings.a(var1.f) + " <";
      }
   }

   protected final void a(char var1, int var2) {
      if(this.binding >= 0) {
         this.settings.a(this.binding, var2);
         ((Button)this.d.get(this.binding)).e = this.settings.a(this.binding);
         this.binding = -1;
      } else {
         super.a(var1, var2);
      }
   }

   public final void a(int var1, int var2) {
      a(0, 0, this.b, this.c, 1610941696, -1607454624);
      a(this.f, this.title, this.b / 2, 20, 16777215);
      super.a(var1, var2);
   }
}
