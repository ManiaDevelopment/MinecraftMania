package com.mojang.minecraft.model;

import com.mojang.minecraft.model.PositionTextureVertex;
import com.mojang.minecraft.model.TexturedQuad;
import com.mojang.minecraft.model.Vec3D;
import org.lwjgl.opengl.GL11;

public final class ModelRenderer {

   public PositionTextureVertex[] a;
   public TexturedQuad[] b;
   private int m;
   private int n;
   public float c;
   public float d;
   public float e;
   public float f;
   public float g;
   public float h;
   public boolean i = false;
   public int j = 0;
   public boolean k = false;
   public boolean l = true;
   private boolean o = false;


   public ModelRenderer(int var1, int var2) {
      this.m = var1;
      this.n = var2;
   }

   public final void a(float var1, float var2, float var3, int var4, int var5, int var6, float var7) {
      this.a = new PositionTextureVertex[8];
      this.b = new TexturedQuad[6];
      float var8 = var1 + (float)var4;
      float var9 = var2 + (float)var5;
      float var10 = var3 + (float)var6;
      var1 -= var7;
      var2 -= var7;
      var3 -= var7;
      var8 += var7;
      var9 += var7;
      var10 += var7;
      if(this.k) {
         var7 = var8;
         var8 = var1;
         var1 = var7;
      }

      PositionTextureVertex var20 = new PositionTextureVertex(var1, var2, var3, 0.0F, 0.0F);
      PositionTextureVertex var11 = new PositionTextureVertex(var8, var2, var3, 0.0F, 8.0F);
      PositionTextureVertex var12 = new PositionTextureVertex(var8, var9, var3, 8.0F, 8.0F);
      PositionTextureVertex var18 = new PositionTextureVertex(var1, var9, var3, 8.0F, 0.0F);
      PositionTextureVertex var13 = new PositionTextureVertex(var1, var2, var10, 0.0F, 0.0F);
      PositionTextureVertex var15 = new PositionTextureVertex(var8, var2, var10, 0.0F, 8.0F);
      PositionTextureVertex var21 = new PositionTextureVertex(var8, var9, var10, 8.0F, 8.0F);
      PositionTextureVertex var14 = new PositionTextureVertex(var1, var9, var10, 8.0F, 0.0F);
      this.a[0] = var20;
      this.a[1] = var11;
      this.a[2] = var12;
      this.a[3] = var18;
      this.a[4] = var13;
      this.a[5] = var15;
      this.a[6] = var21;
      this.a[7] = var14;
      this.b[0] = new TexturedQuad(new PositionTextureVertex[]{var15, var11, var12, var21}, this.m + var6 + var4, this.n + var6, this.m + var6 + var4 + var6, this.n + var6 + var5);
      this.b[1] = new TexturedQuad(new PositionTextureVertex[]{var20, var13, var14, var18}, this.m, this.n + var6, this.m + var6, this.n + var6 + var5);
      this.b[2] = new TexturedQuad(new PositionTextureVertex[]{var15, var13, var20, var11}, this.m + var6, this.n, this.m + var6 + var4, this.n + var6);
      this.b[3] = new TexturedQuad(new PositionTextureVertex[]{var12, var18, var14, var21}, this.m + var6 + var4, this.n, this.m + var6 + var4 + var4, this.n + var6);
      this.b[4] = new TexturedQuad(new PositionTextureVertex[]{var11, var20, var18, var12}, this.m + var6, this.n + var6, this.m + var6 + var4, this.n + var6 + var5);
      this.b[5] = new TexturedQuad(new PositionTextureVertex[]{var13, var15, var21, var14}, this.m + var6 + var4 + var6, this.n + var6, this.m + var6 + var4 + var6 + var4, this.n + var6 + var5);
      if(this.k) {
         for(int var16 = 0; var16 < this.b.length; ++var16) {
            TexturedQuad var17;
            PositionTextureVertex[] var19 = new PositionTextureVertex[(var17 = this.b[var16]).a.length];

            for(var4 = 0; var4 < var17.a.length; ++var4) {
               var19[var4] = var17.a[var17.a.length - var4 - 1];
            }

            var17.a = var19;
         }
      }

   }

   public final void a(float var1, float var2, float var3) {
      this.c = var1;
      this.d = var2;
      this.e = var3;
   }

   public final void a(float var1) {
      if(this.l) {
         if(!this.i) {
            this.b(var1);
         }

         if(this.f == 0.0F && this.g == 0.0F && this.h == 0.0F) {
            if(this.c == 0.0F && this.d == 0.0F && this.e == 0.0F) {
               GL11.glCallList(this.j);
            } else {
               GL11.glTranslatef(this.c * var1, this.d * var1, this.e * var1);
               GL11.glCallList(this.j);
               GL11.glTranslatef(-this.c * var1, -this.d * var1, -this.e * var1);
            }
         } else {
            GL11.glPushMatrix();
            GL11.glTranslatef(this.c * var1, this.d * var1, this.e * var1);
            if(this.h != 0.0F) {
               GL11.glRotatef(this.h * 57.295776F, 0.0F, 0.0F, 1.0F);
            }

            if(this.g != 0.0F) {
               GL11.glRotatef(this.g * 57.295776F, 0.0F, 1.0F, 0.0F);
            }

            if(this.f != 0.0F) {
               GL11.glRotatef(this.f * 57.295776F, 1.0F, 0.0F, 0.0F);
            }

            GL11.glCallList(this.j);
            GL11.glPopMatrix();
         }
      }
   }

   public void b(float var1) {
      this.j = GL11.glGenLists(1);
      GL11.glNewList(this.j, 4864);
      GL11.glBegin(7);

      for(int var2 = 0; var2 < this.b.length; ++var2) {
         TexturedQuad var10000 = this.b[var2];
         float var3 = var1;
         TexturedQuad var4 = var10000;
         Vec3D var5 = var10000.a[1].a.a(var4.a[0].a).a();
         Vec3D var6 = var4.a[1].a.a(var4.a[2].a).a();
         GL11.glNormal3f((var5 = (new Vec3D(var5.b * var6.c - var5.c * var6.b, var5.c * var6.a - var5.a * var6.c, var5.a * var6.b - var5.b * var6.a)).a()).a, var5.b, var5.c);

         for(int var7 = 0; var7 < 4; ++var7) {
            PositionTextureVertex var8;
            GL11.glTexCoord2f((var8 = var4.a[var7]).b, var8.c);
            GL11.glVertex3f(var8.a.a * var3, var8.a.b * var3, var8.a.c * var3);
         }
      }

      GL11.glEnd();
      GL11.glEndList();
      this.i = true;
   }
}
