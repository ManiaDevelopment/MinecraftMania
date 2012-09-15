package com.mojang.minecraft.gui;

import com.mojang.minecraft.AvailableBlockType;
import com.mojang.minecraft.gui.PopUpScreen;
import com.mojang.minecraft.level.tile.Block;
import com.mojang.minecraft.render.ShapeRender;
import com.mojang.minecraft.render.TextureManager;
import org.lwjgl.opengl.GL11;

public final class BlockSelectScreen extends PopUpScreen {

   public BlockSelectScreen() {
      this.e = true;
   }

   private int b(int var1, int var2) {
      for(int var3 = 0; var3 < AvailableBlockType.a.size(); ++var3) {
         int var4 = this.b / 2 + var3 % 9 * 24 + -108 - 3;
         int var5 = this.c / 2 + var3 / 9 * 24 + -60 + 3;
         if(var1 >= var4 && var1 <= var4 + 24 && var2 >= var5 - 12 && var2 <= var5 + 12) {
            return var3;
         }
      }

      return -1;
   }

   public final void a(int var1, int var2) {
      var1 = this.b(var1, var2);
      a(this.b / 2 - 120, 30, this.b / 2 + 120, 180, -1878719232, -1070583712);
      if(var1 >= 0) {
         var2 = this.b / 2 + var1 % 9 * 24 + -108;
         int var3 = this.c / 2 + var1 / 9 * 24 + -60;
         a(var2 - 3, var3 - 8, var2 + 23, var3 + 24 - 6, -1862270977, -1056964609);
      }

      a(this.f, "Select block", this.b / 2, 40, 16777215);
      TextureManager var7 = this.a.textureManager;
      ShapeRender var8 = ShapeRender.a;
      var2 = var7.a("/terrain.png");
      GL11.glBindTexture(3553, var2);

      for(var2 = 0; var2 < AvailableBlockType.a.size(); ++var2) {
         Block var4 = (Block)AvailableBlockType.a.get(var2);
         GL11.glPushMatrix();
         int var5 = this.b / 2 + var2 % 9 * 24 + -108;
         int var6 = this.c / 2 + var2 / 9 * 24 + -60;
         GL11.glTranslatef((float)var5, (float)var6, 0.0F);
         GL11.glScalef(10.0F, 10.0F, 10.0F);
         GL11.glTranslatef(1.0F, 0.5F, 8.0F);
         GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         if(var1 == var2) {
            GL11.glScalef(1.6F, 1.6F, 1.6F);
         }

         GL11.glTranslatef(-1.5F, 0.5F, 0.5F);
         GL11.glScalef(-1.0F, -1.0F, -1.0F);
         var8.b();
         var4.a(var8);
         var8.a();
         GL11.glPopMatrix();
      }

   }

   protected final void a(int var1, int var2, int var3) {
      if(var3 == 0) {
         this.a.player.inventory.replaceSlot(this.b(var1, var2));
         this.a.a((PopUpScreen)null);
      }

   }
}
