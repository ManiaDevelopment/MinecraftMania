package com.mojang.minecraft.gui;

import com.mojang.minecraft.gui.PopUpScreen;

public final class ErrorScreen extends PopUpScreen {

   private String title;
   private String message;


   public ErrorScreen(String var1, String var2) {
      this.title = var1;
      this.message = var2;
   }

   public final void a() {}

   public final void a(int var1, int var2) {
      a(0, 0, this.b, this.c, -12574688, -11530224);
      a(this.f, this.title, this.b / 2, 90, 16777215);
      a(this.f, this.message, this.b / 2, 110, 16777215);
      super.a(var1, var2);
   }

   protected final void a(char var1, int var2) {}
}
