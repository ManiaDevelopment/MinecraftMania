package com.mojang.minecraft.player;

import com.mojang.minecraft.mob.ai.BasicAI;
import com.mojang.minecraft.player.Player;

final class Player$1 extends BasicAI {

   public static final long serialVersionUID = 0L;
   // $FF: synthetic field
   final Player this$0;


   Player$1(Player var1) {
      this.this$0 = var1;
   }

   protected final void update() {
      this.jumping = this.this$0.input.jumping;
      this.xxa = this.this$0.input.xxa;
      this.yya = this.this$0.input.yya;
   }
}
