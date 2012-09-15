package com.mojang.minecraft.gui;

import com.mojang.minecraft.gui.PopUpScreen;
import com.mojang.minecraft.net.NetworkManager;
import com.mojang.minecraft.net.PacketType;
import org.lwjgl.input.Keyboard;

public final class ChatInputScreen extends PopUpScreen {

   private String g = "";
   private int h = 0;


   public final void a() {
      Keyboard.enableRepeatEvents(true);
   }

   public final void b() {
      Keyboard.enableRepeatEvents(false);
   }

   public final void c() {
      ++this.h;
   }

   protected final void a(char var1, int var2) {
      if(var2 == 1) {
         this.a.a((PopUpScreen)null);
      } else if(var2 == 28) {
         NetworkManager var10000 = this.a.networkManager;
         String var4 = this.g.trim();
         NetworkManager var3 = var10000;
         if((var4 = var4.trim()).length() > 0) {
            var3.b.a(PacketType.n, new Object[]{Integer.valueOf(-1), var4});
         }

         this.a.a((PopUpScreen)null);
      } else {
         if(var2 == 14 && this.g.length() > 0) {
            this.g = this.g.substring(0, this.g.length() - 1);
         }

         if("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ,.:-_\'*!\\\"#%/()=+?[]{}<>@|$;".indexOf(var1) >= 0 && this.g.length() < 64 - (this.a.sessionData.b.length() + 2)) {
            this.g = this.g + var1;
         }

      }
   }

   public final void a(int var1, int var2) {
      a(2, this.c - 14, this.b - 2, this.c - 2, Integer.MIN_VALUE);
      b(this.f, "> " + this.g + (this.h / 6 % 2 == 0?"_":""), 4, this.c - 12, 14737632);
   }

   protected final void a(int var1, int var2, int var3) {
      if(var3 == 0 && this.a.hud.clickedPlayer != null) {
         if(this.g.length() > 0 && !this.g.endsWith(" ")) {
            this.g = this.g + " ";
         }

         this.g = this.g + this.a.hud.clickedPlayer;
         var1 = 64 - (this.a.sessionData.b.length() + 2);
         if(this.g.length() > var1) {
            this.g = this.g.substring(0, var1);
         }
      }

   }
}
