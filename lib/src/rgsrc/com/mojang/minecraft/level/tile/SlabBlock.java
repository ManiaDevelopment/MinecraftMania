package com.mojang.minecraft.level.tile;

import com.mojang.minecraft.level.Level;
import com.mojang.minecraft.level.tile.Block;

public final class SlabBlock extends Block {

   private boolean al;


   public SlabBlock(int var1, boolean var2) {
      super(var1, 6);
      this.al = var2;
      if(!var2) {
         this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
      }

   }

   protected final int b(int var1) {
      return var1 <= 1?6:5;
   }

   public final boolean c() {
      return this.al;
   }

   public final void b(Level var1, int var2, int var3, int var4, int var5) {
      if(this == Block.V) {
         ;
      }
   }

   public final void c(Level var1, int var2, int var3, int var4) {
      if(this != Block.V) {
         super.c(var1, var2, var3, var4);
      }

      if(var1.getTile(var2, var3 - 1, var4) == V.ac) {
         var1.setTile(var2, var3, var4, 0);
         var1.setTile(var2, var3 - 1, var4, Block.U.ac);
      }

   }

   public final int g() {
      return Block.V.ac;
   }

   public final boolean a() {
      return this.al;
   }

   public final boolean a(Level var1, int var2, int var3, int var4, int var5) {
      if(this != Block.V) {
         super.a(var1, var2, var3, var4, var5);
      }

      return var5 == 1?true:(!super.a(var1, var2, var3, var4, var5)?false:(var5 == 0?true:var1.getTile(var2, var3, var4) != this.ac));
   }
}
