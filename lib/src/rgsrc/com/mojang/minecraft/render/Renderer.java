package com.mojang.minecraft.render;

import com.mojang.minecraft.Entity;
import com.mojang.minecraft.Minecraft;
import com.mojang.minecraft.level.Level;
import com.mojang.minecraft.level.liquid.LiquidType;
import com.mojang.minecraft.level.tile.Block;
import com.mojang.minecraft.model.Vec3D;
import com.mojang.minecraft.player.Player;
import com.mojang.minecraft.render.HeldBlock;
import com.mojang.util.MathHelper;
import java.nio.FloatBuffer;
import java.util.Random;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public final class Renderer {

   public Minecraft a;
   public float b = 1.0F;
   public boolean c = false;
   public float d = 0.0F;
   public HeldBlock e;
   public int f;
   public Entity g = null;
   public Random h = new Random();
   private volatile int l = 0;
   private volatile int m = 0;
   private FloatBuffer n = BufferUtils.createFloatBuffer(16);
   public float i;
   public float j;
   public float k;


   public Renderer(Minecraft var1) {
      this.a = var1;
      this.e = new HeldBlock(var1);
   }

   public Vec3D a(float var1) {
      Player var4;
      float var2 = (var4 = this.a.player).xo + (var4.x - var4.xo) * var1;
      float var3 = var4.yo + (var4.y - var4.yo) * var1;
      float var5 = var4.zo + (var4.z - var4.zo) * var1;
      return new Vec3D(var2, var3, var5);
   }

   public void b(float var1) {
      Player var3;
      float var2 = (float)(var3 = this.a.player).hurtTime - var1;
      if(var3.health <= 0) {
         var1 += (float)var3.deathTime;
         GL11.glRotatef(40.0F - 8000.0F / (var1 + 200.0F), 0.0F, 0.0F, 1.0F);
      }

      if(var2 >= 0.0F) {
         var2 = MathHelper.a((var2 /= (float)var3.hurtDuration) * var2 * var2 * var2 * 3.1415927F);
         var1 = var3.hurtDir;
         GL11.glRotatef(-var3.hurtDir, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-var2 * 14.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(var1, 0.0F, 1.0F, 0.0F);
      }
   }

   public void c(float var1) {
      Player var4;
      float var2 = (var4 = this.a.player).walkDist - var4.walkDistO;
      var2 = var4.walkDist + var2 * var1;
      float var3 = var4.oBob + (var4.bob - var4.oBob) * var1;
      float var5 = var4.oTilt + (var4.tilt - var4.oTilt) * var1;
      GL11.glTranslatef(MathHelper.a(var2 * 3.1415927F) * var3 * 0.5F, -Math.abs(MathHelper.b(var2 * 3.1415927F) * var3), 0.0F);
      GL11.glRotatef(MathHelper.a(var2 * 3.1415927F) * var3 * 3.0F, 0.0F, 0.0F, 1.0F);
      GL11.glRotatef(Math.abs(MathHelper.b(var2 * 3.1415927F + 0.2F) * var3) * 5.0F, 1.0F, 0.0F, 0.0F);
      GL11.glRotatef(var5, 1.0F, 0.0F, 0.0F);
   }

   public final void a(boolean var1) {
      if(!var1) {
         GL11.glDisable(2896);
         GL11.glDisable(16384);
      } else {
         GL11.glEnable(2896);
         GL11.glEnable(16384);
         GL11.glEnable(2903);
         GL11.glColorMaterial(1032, 5634);
         float var4 = 0.7F;
         float var2 = 0.3F;
         Vec3D var3 = (new Vec3D(0.0F, -1.0F, 0.5F)).a();
         GL11.glLight(16384, 4611, this.a(var3.a, var3.b, var3.c, 0.0F));
         GL11.glLight(16384, 4609, this.a(var2, var2, var2, 1.0F));
         GL11.glLight(16384, 4608, this.a(0.0F, 0.0F, 0.0F, 1.0F));
         GL11.glLightModel(2899, this.a(var4, var4, var4, 1.0F));
      }
   }

   public final void a() {
      int var1 = this.a.width * 240 / this.a.height;
      int var2 = this.a.height * 240 / this.a.height;
      GL11.glClear(256);
      GL11.glMatrixMode(5889);
      GL11.glLoadIdentity();
      GL11.glOrtho(0.0D, (double)var1, (double)var2, 0.0D, 100.0D, 300.0D);
      GL11.glMatrixMode(5888);
      GL11.glLoadIdentity();
      GL11.glTranslatef(0.0F, 0.0F, -200.0F);
   }

   public void b() {
      Level var1 = this.a.level;
      Player var2 = this.a.player;
      GL11.glFog(2918, this.a(this.i, this.j, this.k, 1.0F));
      GL11.glNormal3f(0.0F, -1.0F, 0.0F);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      Block var5;
      if((var5 = Block.b[var1.getTile((int)var2.x, (int)(var2.y + 0.12F), (int)var2.z)]) != null && var5.d() != LiquidType.a) {
         LiquidType var6 = var5.d();
         GL11.glFogi(2917, 2048);
         float var3;
         float var4;
         float var7;
         float var8;
         if(var6 == LiquidType.b) {
            GL11.glFogf(2914, 0.1F);
            var7 = 0.4F;
            var8 = 0.4F;
            var3 = 0.9F;
            if(this.a.settings.g) {
               var4 = (var7 * 30.0F + var8 * 59.0F + var3 * 11.0F) / 100.0F;
               var8 = (var7 * 30.0F + var8 * 70.0F) / 100.0F;
               var3 = (var7 * 30.0F + var3 * 70.0F) / 100.0F;
               var7 = var4;
               var8 = var8;
               var3 = var3;
            }

            GL11.glLightModel(2899, this.a(var7, var8, var3, 1.0F));
         } else if(var6 == LiquidType.c) {
            GL11.glFogf(2914, 2.0F);
            var7 = 0.4F;
            var8 = 0.3F;
            var3 = 0.3F;
            if(this.a.settings.g) {
               var4 = (var7 * 30.0F + var8 * 59.0F + var3 * 11.0F) / 100.0F;
               var8 = (var7 * 30.0F + var8 * 70.0F) / 100.0F;
               var3 = (var7 * 30.0F + var3 * 70.0F) / 100.0F;
               var7 = var4;
               var8 = var8;
               var3 = var3;
            }

            GL11.glLightModel(2899, this.a(var7, var8, var3, 1.0F));
         }
      } else {
         GL11.glFogi(2917, 9729);
         GL11.glFogf(2915, 0.0F);
         GL11.glFogf(2916, this.d);
         GL11.glLightModel(2899, this.a(1.0F, 1.0F, 1.0F, 1.0F));
      }

      GL11.glEnable(2903);
      GL11.glColorMaterial(1028, 4608);
   }

   private FloatBuffer a(float var1, float var2, float var3, float var4) {
      this.n.clear();
      this.n.put(var1).put(var2).put(var3).put(var4);
      this.n.flip();
      return this.n;
   }
}
