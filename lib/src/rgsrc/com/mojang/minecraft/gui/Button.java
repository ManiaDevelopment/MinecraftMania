package com.mojang.minecraft.gui;

import com.mojang.minecraft.gui.Screen;

public class Button extends Screen {

   int a;
   int b;
   public int c;
   public int d;
   public String e;
   public int f;
   public boolean g;
   public boolean h;


   public Button(int var1, int var2, int var3, String var4) {
      this(var1, var2, var3, 200, 20, var4);
   }

   protected Button(int var1, int var2, int var3, int var4, int var5, String var6) {
      this.a = 200;
      this.b = 20;
      this.g = true;
      this.h = true;
      this.f = var1;
      this.c = var2;
      this.d = var3;
      this.a = var4;
      this.b = 20;
      this.e = var6;
   }
}
