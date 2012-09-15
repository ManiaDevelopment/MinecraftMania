package com.mojang.minecraft.render;

import com.mojang.minecraft.Minecraft;
import com.mojang.minecraft.level.Level;
import com.mojang.minecraft.player.Player;
import com.mojang.minecraft.render.Chunk;
import com.mojang.minecraft.render.ShapeRender;
import com.mojang.minecraft.render.TextureManager;
import com.mojang.minecraft.render.f;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public final class LevelRenderer {

   public Level a;
   public TextureManager b;
   public int c;
   public IntBuffer d = BufferUtils.createIntBuffer(65536);
   public List e = new ArrayList();
   private Chunk[] j;
   public Chunk[] f;
   private int k;
   private int l;
   private int m;
   private int n;
   public Minecraft g;
   private int[] o = new int['\uc350'];
   public int h = 0;
   private float p = -9999.0F;
   private float q = -9999.0F;
   private float r = -9999.0F;
   public float i;


   public LevelRenderer(Minecraft var1, TextureManager var2) {
      this.g = var1;
      this.b = var2;
      this.c = GL11.glGenLists(2);
      this.n = GL11.glGenLists(4096 << 6 << 1);
   }

   public final void a() {
      int var1;
      if(this.f != null) {
         for(var1 = 0; var1 < this.f.length; ++var1) {
            this.f[var1].b();
         }
      }

      this.k = this.a.width / 16;
      this.l = this.a.depth / 16;
      this.m = this.a.height / 16;
      this.f = new Chunk[this.k * this.l * this.m];
      this.j = new Chunk[this.k * this.l * this.m];
      var1 = 0;

      int var2;
      int var4;
      for(var2 = 0; var2 < this.k; ++var2) {
         for(int var3 = 0; var3 < this.l; ++var3) {
            for(var4 = 0; var4 < this.m; ++var4) {
               this.f[(var4 * this.l + var3) * this.k + var2] = new Chunk(this.a, var2 << 4, var3 << 4, var4 << 4, 16, this.n + var1);
               this.j[(var4 * this.l + var3) * this.k + var2] = this.f[(var4 * this.l + var3) * this.k + var2];
               var1 += 2;
            }
         }
      }

      for(var2 = 0; var2 < this.e.size(); ++var2) {
         ((Chunk)this.e.get(var2)).c = false;
      }

      this.e.clear();
      GL11.glNewList(this.c, 4864);
      LevelRenderer var9 = this;
      float var10 = 0.5F;
      GL11.glColor4f(0.5F, var10, var10, 1.0F);
      ShapeRender var11 = ShapeRender.a;
      float var12 = this.a.getGroundLevel();
      int var5 = 128;
      if(128 > this.a.width) {
         var5 = this.a.width;
      }

      if(var5 > this.a.height) {
         var5 = this.a.height;
      }

      int var6 = 2048 / var5;
      var11.b();

      int var7;
      for(var7 = -var5 * var6; var7 < var9.a.width + var5 * var6; var7 += var5) {
         for(int var8 = -var5 * var6; var8 < var9.a.height + var5 * var6; var8 += var5) {
            var10 = var12;
            if(var7 >= 0 && var8 >= 0 && var7 < var9.a.width && var8 < var9.a.height) {
               var10 = 0.0F;
            }

            var11.a((float)var7, var10, (float)(var8 + var5), 0.0F, (float)var5);
            var11.a((float)(var7 + var5), var10, (float)(var8 + var5), (float)var5, (float)var5);
            var11.a((float)(var7 + var5), var10, (float)var8, (float)var5, 0.0F);
            var11.a((float)var7, var10, (float)var8, 0.0F, 0.0F);
         }
      }

      var11.a();
      GL11.glColor3f(0.8F, 0.8F, 0.8F);
      var11.b();

      for(var7 = 0; var7 < var9.a.width; var7 += var5) {
         var11.a((float)var7, 0.0F, 0.0F, 0.0F, 0.0F);
         var11.a((float)(var7 + var5), 0.0F, 0.0F, (float)var5, 0.0F);
         var11.a((float)(var7 + var5), var12, 0.0F, (float)var5, var12);
         var11.a((float)var7, var12, 0.0F, 0.0F, var12);
         var11.a((float)var7, var12, (float)var9.a.height, 0.0F, var12);
         var11.a((float)(var7 + var5), var12, (float)var9.a.height, (float)var5, var12);
         var11.a((float)(var7 + var5), 0.0F, (float)var9.a.height, (float)var5, 0.0F);
         var11.a((float)var7, 0.0F, (float)var9.a.height, 0.0F, 0.0F);
      }

      GL11.glColor3f(0.6F, 0.6F, 0.6F);

      for(var7 = 0; var7 < var9.a.height; var7 += var5) {
         var11.a(0.0F, var12, (float)var7, 0.0F, 0.0F);
         var11.a(0.0F, var12, (float)(var7 + var5), (float)var5, 0.0F);
         var11.a(0.0F, 0.0F, (float)(var7 + var5), (float)var5, var12);
         var11.a(0.0F, 0.0F, (float)var7, 0.0F, var12);
         var11.a((float)var9.a.width, 0.0F, (float)var7, 0.0F, var12);
         var11.a((float)var9.a.width, 0.0F, (float)(var7 + var5), (float)var5, var12);
         var11.a((float)var9.a.width, var12, (float)(var7 + var5), (float)var5, 0.0F);
         var11.a((float)var9.a.width, var12, (float)var7, 0.0F, 0.0F);
      }

      var11.a();
      GL11.glEndList();
      GL11.glNewList(this.c + 1, 4864);
      var9 = this;
      GL11.glColor3f(1.0F, 1.0F, 1.0F);
      var10 = this.a.getWaterLevel();
      GL11.glBlendFunc(770, 771);
      var11 = ShapeRender.a;
      var4 = 128;
      if(128 > this.a.width) {
         var4 = this.a.width;
      }

      if(var4 > this.a.height) {
         var4 = this.a.height;
      }

      var5 = 2048 / var4;
      var11.b();

      for(var6 = -var4 * var5; var6 < var9.a.width + var4 * var5; var6 += var4) {
         for(var7 = -var4 * var5; var7 < var9.a.height + var4 * var5; var7 += var4) {
            float var13 = var10 - 0.1F;
            if(var6 < 0 || var7 < 0 || var6 >= var9.a.width || var7 >= var9.a.height) {
               var11.a((float)var6, var13, (float)(var7 + var4), 0.0F, (float)var4);
               var11.a((float)(var6 + var4), var13, (float)(var7 + var4), (float)var4, (float)var4);
               var11.a((float)(var6 + var4), var13, (float)var7, (float)var4, 0.0F);
               var11.a((float)var6, var13, (float)var7, 0.0F, 0.0F);
               var11.a((float)var6, var13, (float)var7, 0.0F, 0.0F);
               var11.a((float)(var6 + var4), var13, (float)var7, (float)var4, 0.0F);
               var11.a((float)(var6 + var4), var13, (float)(var7 + var4), (float)var4, (float)var4);
               var11.a((float)var6, var13, (float)(var7 + var4), 0.0F, (float)var4);
            }
         }
      }

      var11.a();
      GL11.glDisable(3042);
      GL11.glEndList();
      this.a(0, 0, 0, this.a.width, this.a.depth, this.a.height);
   }

   public final int a(Player var1, int var2) {
      float var3 = var1.x - this.p;
      float var4 = var1.y - this.q;
      float var5 = var1.z - this.r;
      if(var3 * var3 + var4 * var4 + var5 * var5 > 64.0F) {
         this.p = var1.x;
         this.q = var1.y;
         this.r = var1.z;
         Arrays.sort(this.j, new f(var1));
      }

      int var6 = 0;

      for(int var7 = 0; var7 < this.j.length; ++var7) {
         var6 = this.j[var7].a(this.o, var6, var2);
      }

      this.d.clear();
      this.d.put(this.o, 0, var6);
      this.d.flip();
      if(this.d.remaining() > 0) {
         GL11.glBindTexture(3553, this.b.a("/terrain.png"));
         GL11.glCallLists(this.d);
      }

      return this.d.remaining();
   }

   public final void a(int var1, int var2, int var3, int var4, int var5, int var6) {
      var1 /= 16;
      var2 /= 16;
      var3 /= 16;
      var4 /= 16;
      var5 /= 16;
      var6 /= 16;
      if(var1 < 0) {
         var1 = 0;
      }

      if(var2 < 0) {
         var2 = 0;
      }

      if(var3 < 0) {
         var3 = 0;
      }

      if(var4 > this.k - 1) {
         var4 = this.k - 1;
      }

      if(var5 > this.l - 1) {
         var5 = this.l - 1;
      }

      if(var6 > this.m - 1) {
         var6 = this.m - 1;
      }

      for(var1 = var1; var1 <= var4; ++var1) {
         for(int var7 = var2; var7 <= var5; ++var7) {
            for(int var8 = var3; var8 <= var6; ++var8) {
               Chunk var9;
               if(!(var9 = this.f[(var8 * this.l + var7) * this.k + var1]).c) {
                  var9.c = true;
                  this.e.add(this.f[(var8 * this.l + var7) * this.k + var1]);
               }
            }
         }
      }

   }
}
