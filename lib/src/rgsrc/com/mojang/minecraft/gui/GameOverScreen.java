package com.mojang.minecraft.gui;

import com.mojang.minecraft.gui.Button;
import com.mojang.minecraft.gui.GenerateLevelScreen;
import com.mojang.minecraft.gui.LoadLevelScreen;
import com.mojang.minecraft.gui.OptionsScreen;
import com.mojang.minecraft.gui.PopUpScreen;
import org.lwjgl.opengl.GL11;

public final class GameOverScreen extends PopUpScreen {

   public final void a() {
      this.d.clear();
      this.d.add(new Button(1, this.b / 2 - 100, this.c / 4 + 72, "Generate new level..."));
      this.d.add(new Button(2, this.b / 2 - 100, this.c / 4 + 96, "Load level.."));
      if(this.a.sessionData == null) {
         ((Button)this.d.get(2)).g = false;
      }

   }

   protected final void a(Button var1) {
      if(var1.f == 0) {
         this.a.a((PopUpScreen)(new OptionsScreen(this, this.a.settings)));
      }

      if(var1.f == 1) {
         this.a.a((PopUpScreen)(new GenerateLevelScreen(this)));
      }

      if(this.a.sessionData != null && var1.f == 2) {
         this.a.a((PopUpScreen)(new LoadLevelScreen(this)));
      }

   }

   public final void a(int var1, int var2) {
      a(0, 0, this.b, this.c, 1615855616, -1602211792);
      GL11.glPushMatrix();
      GL11.glScalef(2.0F, 2.0F, 2.0F);
      a(this.f, "Game over!", this.b / 2 / 2, 30, 16777215);
      GL11.glPopMatrix();
      a(this.f, "Score: &e" + this.a.player.getScore(), this.b / 2, 100, 16777215);
      super.a(var1, var2);
   }
}
