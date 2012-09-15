package com.mojang.minecraft.gui;

import com.mojang.minecraft.Minecraft;
import com.mojang.minecraft.gui.Button;
import com.mojang.minecraft.gui.PopUpScreen;
import org.lwjgl.input.Keyboard;

public final class LevelNameScreen extends PopUpScreen {

   private PopUpScreen g;
   private String h = "Enter level name:";
   private int j;
   private String k;
   private int l = 0;


   public LevelNameScreen(PopUpScreen var1, String var2, int var3) {
      this.g = var1;
      this.j = var3;
      this.k = var2;
      if(this.k.equals("-")) {
         this.k = "";
      }

   }

   public final void a() {
      this.d.clear();
      Keyboard.enableRepeatEvents(true);
      this.d.add(new Button(0, this.b / 2 - 100, this.c / 4 + 120, "Save"));
      this.d.add(new Button(1, this.b / 2 - 100, this.c / 4 + 144, "Cancel"));
      ((Button)this.d.get(0)).g = this.k.trim().length() > 1;
   }

   public final void b() {
      Keyboard.enableRepeatEvents(false);
   }

   public final void c() {
      ++this.l;
   }

   protected final void a(Button var1) {
      if(var1.g) {
         if(var1.f == 0 && this.k.trim().length() > 1) {
            Minecraft var10000 = this.a;
            int var10001 = this.j;
            String var2 = this.k.trim();
            int var3 = var10001;
            Minecraft var4 = var10000;
            var10000.levelIO.a(var4.level, var4.website, var4.sessionData.b, var4.sessionData.c, var2, var3);
            this.a.a((PopUpScreen)null);
            this.a.b();
         }

         if(var1.f == 1) {
            this.a.a(this.g);
         }

      }
   }

   protected final void a(char var1, int var2) {
      if(var2 == 14 && this.k.length() > 0) {
         this.k = this.k.substring(0, this.k.length() - 1);
      }

      if("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ,.:-_\'*!\"#%/()=+?[]{}<>".indexOf(var1) >= 0 && this.k.length() < 64) {
         this.k = this.k + var1;
      }

      ((Button)this.d.get(0)).g = this.k.trim().length() > 1;
   }

   public final void a(int var1, int var2) {
      a(0, 0, this.b, this.c, 1610941696, -1607454624);
      a(this.f, this.h, this.b / 2, 40, 16777215);
      int var3 = this.b / 2 - 100;
      int var4 = this.c / 2 - 10;
      a(var3 - 1, var4 - 1, var3 + 200 + 1, var4 + 20 + 1, -6250336);
      a(var3, var4, var3 + 200, var4 + 20, -16777216);
      b(this.f, this.k + (this.l / 6 % 2 == 0?"_":""), var3 + 4, var4 + 6, 14737632);
      super.a(var1, var2);
   }
}
