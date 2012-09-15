package com.mojang.minecraft.gui;

import com.mojang.minecraft.gui.Button;
import com.mojang.minecraft.gui.PopUpScreen;

public final class GenerateLevelScreen extends PopUpScreen {

   private PopUpScreen parent;


   public GenerateLevelScreen(PopUpScreen var1) {
      this.parent = var1;
   }

   public final void a() {
      this.d.clear();
      this.d.add(new Button(0, this.b / 2 - 100, this.c / 4, "Small"));
      this.d.add(new Button(1, this.b / 2 - 100, this.c / 4 + 24, "Normal"));
      this.d.add(new Button(2, this.b / 2 - 100, this.c / 4 + 48, "Huge"));
      this.d.add(new Button(3, this.b / 2 - 100, this.c / 4 + 120, "Cancel"));
   }

   protected final void a(Button var1) {
      if(var1.f == 3) {
         this.a.a(this.parent);
      } else {
         this.a.a(var1.f);
         this.a.a((PopUpScreen)null);
         this.a.b();
      }
   }

   public final void a(int var1, int var2) {
      a(0, 0, this.b, this.c, 1610941696, -1607454624);
      a(this.f, "Generate new level", this.b / 2, 40, 16777215);
      super.a(var1, var2);
   }
}
