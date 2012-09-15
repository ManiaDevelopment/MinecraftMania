package com.mojang.minecraft.render;

import com.mojang.minecraft.level.Level;
import com.mojang.minecraft.level.tile.Block;
import com.mojang.minecraft.player.Player;
import com.mojang.minecraft.render.ClippingHelper;
import com.mojang.minecraft.render.ShapeRender;
import com.mojang.util.MathHelper;
import org.lwjgl.opengl.GL11;

public final class Chunk {

   private Level d;
   private int e = -1;
   private static ShapeRender f = ShapeRender.a;
   public static int a = 0;
   private int g;
   private int h;
   private int i;
   private int j;
   private int k;
   private int l;
   public boolean b = false;
   private boolean[] m = new boolean[2];
   public boolean c;


   public Chunk(Level var1, int var2, int var3, int var4, int var5, int var6) {
      this.d = var1;
      this.g = var2;
      this.h = var3;
      this.i = var4;
      this.j = this.k = this.l = 16;
      MathHelper.c((float)(this.j * this.j + this.k * this.k + this.l * this.l));
      this.e = var6;
      this.c();
   }

   public final void a() {
      ++a;
      int var1 = this.g;
      int var2 = this.h;
      int var3 = this.i;
      int var4 = this.g + this.j;
      int var5 = this.h + this.k;
      int var6 = this.i + this.l;

      int var7;
      for(var7 = 0; var7 < 2; ++var7) {
         this.m[var7] = true;
      }

      for(var7 = 0; var7 < 2; ++var7) {
         boolean var8 = false;
         boolean var9 = false;
         GL11.glNewList(this.e + var7, 4864);
         f.b();

         for(int var10 = var1; var10 < var4; ++var10) {
            for(int var11 = var2; var11 < var5; ++var11) {
               for(int var12 = var3; var12 < var6; ++var12) {
                  int var13;
                  if((var13 = this.d.getTile(var10, var11, var12)) > 0) {
                     Block var14;
                     if((var14 = Block.b[var13]).j() != var7) {
                        var8 = true;
                     } else {
                        var9 |= var14.a(this.d, var10, var11, var12, f);
                     }
                  }
               }
            }
         }

         f.a();
         GL11.glEndList();
         if(var9) {
            this.m[var7] = false;
         }

         if(!var8) {
            break;
         }
      }

   }

   public final float a(Player var1) {
      float var2 = var1.x - (float)this.g;
      float var3 = var1.y - (float)this.h;
      float var4 = var1.z - (float)this.i;
      return var2 * var2 + var3 * var3 + var4 * var4;
   }

   private void c() {
      for(int var1 = 0; var1 < 2; ++var1) {
         this.m[var1] = true;
      }

   }

   public final void b() {
      this.c();
      this.d = null;
   }

   public final int a(int[] var1, int var2, int var3) {
      if(!this.b) {
         return var2;
      } else {
         if(!this.m[var3]) {
            var1[var2++] = this.e + var3;
         }

         return var2;
      }
   }

   public final void a(ClippingHelper var1) {
      this.b = var1.a((float)this.g, (float)this.h, (float)this.i, (float)(this.g + this.j), (float)(this.h + this.k), (float)(this.i + this.l));
   }

}
