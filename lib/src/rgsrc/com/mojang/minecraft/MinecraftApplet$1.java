package com.mojang.minecraft;

import com.mojang.minecraft.MinecraftApplet;
import java.awt.Canvas;

final class MinecraftApplet$1 extends Canvas {

   private static final long serialVersionUID = 1L;
   // $FF: synthetic field
   final MinecraftApplet this$0;


   MinecraftApplet$1(MinecraftApplet var1) {
      this.this$0 = var1;
   }

   public final synchronized void addNotify() {
      super.addNotify();
      this.this$0.startGameThread();
   }

   public final synchronized void removeNotify() {
      this.this$0.stopGameThread();
      super.removeNotify();
   }
}
