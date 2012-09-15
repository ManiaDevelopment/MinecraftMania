package com.mojang.minecraft.level.tile;

import com.mojang.minecraft.MovingObjectPosition;
import com.mojang.minecraft.item.Item;
import com.mojang.minecraft.level.Level;
import com.mojang.minecraft.level.liquid.LiquidType;
import com.mojang.minecraft.level.tile.BookshelfBlock;
import com.mojang.minecraft.level.tile.DirtBlock;
import com.mojang.minecraft.level.tile.FlowerBlock;
import com.mojang.minecraft.level.tile.GlassBlock;
import com.mojang.minecraft.level.tile.GrassBlock;
import com.mojang.minecraft.level.tile.LeavesBlock;
import com.mojang.minecraft.level.tile.LiquidBlock;
import com.mojang.minecraft.level.tile.MetalBlock;
import com.mojang.minecraft.level.tile.MushroomBlock;
import com.mojang.minecraft.level.tile.OreBlock;
import com.mojang.minecraft.level.tile.SandBlock;
import com.mojang.minecraft.level.tile.SaplingBlock;
import com.mojang.minecraft.level.tile.SlabBlock;
import com.mojang.minecraft.level.tile.SpongeBlock;
import com.mojang.minecraft.level.tile.StillLiquidBlock;
import com.mojang.minecraft.level.tile.StoneBlock;
import com.mojang.minecraft.level.tile.TNTBlock;
import com.mojang.minecraft.level.tile.Tile$SoundType;
import com.mojang.minecraft.level.tile.WoodBlock;
import com.mojang.minecraft.model.Vec3D;
import com.mojang.minecraft.particle.ParticleManager;
import com.mojang.minecraft.particle.TerrainParticle;
import com.mojang.minecraft.phys.AABB;
import com.mojang.minecraft.render.ShapeRender;
import java.util.Random;

public class Block {

   protected static Random a = new Random();
   public static final Block[] b = new Block[256];
   public static final boolean[] c = new boolean[256];
   private static boolean[] al = new boolean[256];
   private static boolean[] am = new boolean[256];
   public static final boolean[] d = new boolean[256];
   private static int[] an = new int[256];
   public static final Block e;
   public static final Block f;
   public static final Block g;
   public static final Block h;
   public static final Block i;
   public static final Block j;
   public static final Block k;
   public static final Block l;
   public static final Block m;
   public static final Block n;
   public static final Block o;
   public static final Block p;
   public static final Block q;
   public static final Block r;
   public static final Block s;
   public static final Block t;
   public static final Block u;
   public static final Block v;
   public static final Block w;
   public static final Block x;
   public static final Block y;
   public static final Block z;
   public static final Block A;
   public static final Block B;
   public static final Block C;
   public static final Block D;
   public static final Block E;
   public static final Block F;
   public static final Block G;
   public static final Block H;
   public static final Block I;
   public static final Block J;
   public static final Block K;
   public static final Block L;
   public static final Block M;
   public static final Block N;
   public static final Block O;
   public static final Block P;
   public static final Block Q;
   public static final Block R;
   public static final Block S;
   public static final Block T;
   public static final Block U;
   public static final Block V;
   public static final Block W;
   public static final Block X;
   public static final Block Y;
   public static final Block Z;
   public static final Block aa;
   public int ab;
   public final int ac;
   public Tile$SoundType ad;
   private int ao;
   private boolean ap;
   public float ae;
   public float af;
   public float ag;
   public float ah;
   public float ai;
   public float aj;
   public float ak;


   protected Block(int var1) {
      this.ap = true;
      b[var1] = this;
      this.ac = var1;
      this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      al[var1] = this.c();
      am[var1] = this.a();
      d[var1] = false;
   }

   public boolean a() {
      return true;
   }

   protected final Block a(Tile$SoundType var1, float var2, float var3, float var4) {
      this.ak = var3;
      this.ad = var1;
      this.ao = (int)(var4 * 20.0F);
      return this;
   }

   protected final void a(boolean var1) {
      c[this.ac] = var1;
   }

   protected final void a(float var1, float var2, float var3, float var4, float var5, float var6) {
      this.ae = var1;
      this.af = var2;
      this.ag = var3;
      this.ah = var4;
      this.ai = var5;
      this.aj = var6;
   }

   protected Block(int var1, int var2) {
      this(var1);
      this.ab = var2;
   }

   public final void a(int var1) {
      an[this.ac] = 16;
   }

   public void a(ShapeRender var1) {
      float var2 = 0.5F;
      float var3 = 0.8F;
      float var4 = 0.6F;
      var1.a(var2, var2, var2);
      this.a(var1, -2, 0, 0, 0);
      var1.a(1.0F, 1.0F, 1.0F);
      this.a(var1, -2, 0, 0, 1);
      var1.a(var3, var3, var3);
      this.a(var1, -2, 0, 0, 2);
      var1.a(var3, var3, var3);
      this.a(var1, -2, 0, 0, 3);
      var1.a(var4, var4, var4);
      this.a(var1, -2, 0, 0, 4);
      var1.a(var4, var4, var4);
      this.a(var1, -2, 0, 0, 5);
   }

   protected float a(Level var1, int var2, int var3, int var4) {
      return var1.getBrightness(var2, var3, var4);
   }

   public boolean a(Level var1, int var2, int var3, int var4, int var5) {
      return !var1.isSolidTile(var2, var3, var4);
   }

   protected int b(int var1) {
      return this.ab;
   }

   public void a(ShapeRender var1, int var2, int var3, int var4, int var5) {
      int var6 = this.b(var5);
      this.a(var1, var2, var3, var4, var5, var6);
   }

   public final void a(ShapeRender var1, int var2, int var3, int var4, int var5, int var6) {
      int var7 = var6 % 16 << 4;
      int var8 = var6 / 16 << 4;
      float var9 = (float)var7 / 256.0F;
      float var17 = ((float)var7 + 15.99F) / 256.0F;
      float var10 = (float)var8 / 256.0F;
      float var11 = ((float)var8 + 15.99F) / 256.0F;
      if(var5 >= 2 && var6 < 240) {
         if(this.af >= 0.0F && this.ai <= 1.0F) {
            var10 = ((float)var8 + this.af * 15.99F) / 256.0F;
            var11 = ((float)var8 + this.ai * 15.99F) / 256.0F;
         } else {
            var10 = (float)var8 / 256.0F;
            var11 = ((float)var8 + 15.99F) / 256.0F;
         }
      }

      float var16 = (float)var2 + this.ae;
      float var14 = (float)var2 + this.ah;
      float var18 = (float)var3 + this.af;
      float var15 = (float)var3 + this.ai;
      float var12 = (float)var4 + this.ag;
      float var13 = (float)var4 + this.aj;
      if(var5 == 0) {
         var1.a(var16, var18, var13, var9, var11);
         var1.a(var16, var18, var12, var9, var10);
         var1.a(var14, var18, var12, var17, var10);
         var1.a(var14, var18, var13, var17, var11);
      } else if(var5 == 1) {
         var1.a(var14, var15, var13, var17, var11);
         var1.a(var14, var15, var12, var17, var10);
         var1.a(var16, var15, var12, var9, var10);
         var1.a(var16, var15, var13, var9, var11);
      } else if(var5 == 2) {
         var1.a(var16, var15, var12, var17, var10);
         var1.a(var14, var15, var12, var9, var10);
         var1.a(var14, var18, var12, var9, var11);
         var1.a(var16, var18, var12, var17, var11);
      } else if(var5 == 3) {
         var1.a(var16, var15, var13, var9, var10);
         var1.a(var16, var18, var13, var9, var11);
         var1.a(var14, var18, var13, var17, var11);
         var1.a(var14, var15, var13, var17, var10);
      } else if(var5 == 4) {
         var1.a(var16, var15, var13, var17, var10);
         var1.a(var16, var15, var12, var9, var10);
         var1.a(var16, var18, var12, var9, var11);
         var1.a(var16, var18, var13, var17, var11);
      } else if(var5 == 5) {
         var1.a(var14, var18, var13, var9, var11);
         var1.a(var14, var18, var12, var17, var11);
         var1.a(var14, var15, var12, var17, var10);
         var1.a(var14, var15, var13, var9, var10);
      }
   }

   public final void b(ShapeRender var1, int var2, int var3, int var4, int var5) {
      int var6;
      float var7;
      float var8 = (var7 = (float)((var6 = this.b(var5)) % 16) / 16.0F) + 0.0624375F;
      float var16;
      float var9 = (var16 = (float)(var6 / 16) / 16.0F) + 0.0624375F;
      float var10 = (float)var2 + this.ae;
      float var14 = (float)var2 + this.ah;
      float var11 = (float)var3 + this.af;
      float var15 = (float)var3 + this.ai;
      float var12 = (float)var4 + this.ag;
      float var13 = (float)var4 + this.aj;
      if(var5 == 0) {
         var1.a(var14, var11, var13, var8, var9);
         var1.a(var14, var11, var12, var8, var16);
         var1.a(var10, var11, var12, var7, var16);
         var1.a(var10, var11, var13, var7, var9);
      }

      if(var5 == 1) {
         var1.a(var10, var15, var13, var7, var9);
         var1.a(var10, var15, var12, var7, var16);
         var1.a(var14, var15, var12, var8, var16);
         var1.a(var14, var15, var13, var8, var9);
      }

      if(var5 == 2) {
         var1.a(var10, var11, var12, var8, var9);
         var1.a(var14, var11, var12, var7, var9);
         var1.a(var14, var15, var12, var7, var16);
         var1.a(var10, var15, var12, var8, var16);
      }

      if(var5 == 3) {
         var1.a(var14, var15, var13, var8, var16);
         var1.a(var14, var11, var13, var8, var9);
         var1.a(var10, var11, var13, var7, var9);
         var1.a(var10, var15, var13, var7, var16);
      }

      if(var5 == 4) {
         var1.a(var10, var11, var13, var8, var9);
         var1.a(var10, var11, var12, var7, var9);
         var1.a(var10, var15, var12, var7, var16);
         var1.a(var10, var15, var13, var8, var16);
      }

      if(var5 == 5) {
         var1.a(var14, var15, var13, var7, var16);
         var1.a(var14, var15, var12, var8, var16);
         var1.a(var14, var11, var12, var8, var9);
         var1.a(var14, var11, var13, var7, var9);
      }

   }

   public final AABB a(int var1, int var2, int var3) {
      return new AABB((float)var1 + this.ae, (float)var2 + this.af, (float)var3 + this.ag, (float)var1 + this.ah, (float)var2 + this.ai, (float)var3 + this.aj);
   }

   public AABB b(int var1, int var2, int var3) {
      return new AABB((float)var1 + this.ae, (float)var2 + this.af, (float)var3 + this.ag, (float)var1 + this.ah, (float)var2 + this.ai, (float)var3 + this.aj);
   }

   public boolean b() {
      return true;
   }

   public boolean c() {
      return true;
   }

   public void a(Level var1, int var2, int var3, int var4, Random var5) {}

   public void a(Level var1, int var2, int var3, int var4, ParticleManager var5) {
      for(int var6 = 0; var6 < 4; ++var6) {
         for(int var7 = 0; var7 < 4; ++var7) {
            for(int var8 = 0; var8 < 4; ++var8) {
               float var9 = (float)var2 + ((float)var6 + 0.5F) / (float)4;
               float var10 = (float)var3 + ((float)var7 + 0.5F) / (float)4;
               float var11 = (float)var4 + ((float)var8 + 0.5F) / (float)4;
               var5.a(new TerrainParticle(var1, var9, var10, var11, var9 - (float)var2 - 0.5F, var10 - (float)var3 - 0.5F, var11 - (float)var4 - 0.5F, this));
            }
         }
      }

   }

   public final void a(Level var1, int var2, int var3, int var4, int var5, ParticleManager var6) {
      float var7 = 0.1F;
      float var8 = (float)var2 + a.nextFloat() * (this.ah - this.ae - var7 * 2.0F) + var7 + this.ae;
      float var9 = (float)var3 + a.nextFloat() * (this.ai - this.af - var7 * 2.0F) + var7 + this.af;
      float var10 = (float)var4 + a.nextFloat() * (this.aj - this.ag - var7 * 2.0F) + var7 + this.ag;
      if(var5 == 0) {
         var9 = (float)var3 + this.af - var7;
      }

      if(var5 == 1) {
         var9 = (float)var3 + this.ai + var7;
      }

      if(var5 == 2) {
         var10 = (float)var4 + this.ag - var7;
      }

      if(var5 == 3) {
         var10 = (float)var4 + this.aj + var7;
      }

      if(var5 == 4) {
         var8 = (float)var2 + this.ae - var7;
      }

      if(var5 == 5) {
         var8 = (float)var2 + this.ah + var7;
      }

      var6.a((new TerrainParticle(var1, var8, var9, var10, 0.0F, 0.0F, 0.0F, this)).setPower(0.2F).scale(0.6F));
   }

   public LiquidType d() {
      return LiquidType.a;
   }

   public void b(Level var1, int var2, int var3, int var4, int var5) {}

   public void b(Level var1, int var2, int var3, int var4) {}

   public int e() {
      return 0;
   }

   public void c(Level var1, int var2, int var3, int var4) {}

   public void d(Level var1, int var2, int var3, int var4) {}

   public int f() {
      return 1;
   }

   public int g() {
      return this.ac;
   }

   public final int h() {
      return this.ao;
   }

   public void e(Level var1, int var2, int var3, int var4) {
      this.a(var1, var2, var3, var4, 1.0F);
   }

   public void a(Level var1, int var2, int var3, int var4, float var5) {
      if(!var1.creativeMode) {
         int var6 = this.f();

         for(int var7 = 0; var7 < var6; ++var7) {
            if(a.nextFloat() <= var5) {
               float var8 = 0.7F;
               float var9 = a.nextFloat() * var8 + (1.0F - var8) * 0.5F;
               float var10 = a.nextFloat() * var8 + (1.0F - var8) * 0.5F;
               var8 = a.nextFloat() * var8 + (1.0F - var8) * 0.5F;
               var1.addEntity(new Item(var1, (float)var2 + var9, (float)var3 + var10, (float)var4 + var8, this.g()));
            }
         }

      }
   }

   public void b(ShapeRender var1) {
      var1.b();

      for(int var2 = 0; var2 < 6; ++var2) {
         if(var2 == 0) {
            var1.c(0.0F, 1.0F, 0.0F);
         }

         if(var2 == 1) {
            var1.c(0.0F, -1.0F, 0.0F);
         }

         if(var2 == 2) {
            var1.c(0.0F, 0.0F, 1.0F);
         }

         if(var2 == 3) {
            var1.c(0.0F, 0.0F, -1.0F);
         }

         if(var2 == 4) {
            var1.c(1.0F, 0.0F, 0.0F);
         }

         if(var2 == 5) {
            var1.c(-1.0F, 0.0F, 0.0F);
         }

         this.a(var1, 0, 0, 0, var2);
      }

      var1.a();
   }

   public final boolean i() {
      return this.ap;
   }

   public final MovingObjectPosition a(int var1, int var2, int var3, Vec3D var4, Vec3D var5) {
      var4 = var4.a((float)(-var1), (float)(-var2), (float)(-var3));
      var5 = var5.a((float)(-var1), (float)(-var2), (float)(-var3));
      Vec3D var6 = var4.a(var5, this.ae);
      Vec3D var7 = var4.a(var5, this.ah);
      Vec3D var8 = var4.b(var5, this.af);
      Vec3D var9 = var4.b(var5, this.ai);
      Vec3D var10 = var4.c(var5, this.ag);
      var5 = var4.c(var5, this.aj);
      if(!this.a(var6)) {
         var6 = null;
      }

      if(!this.a(var7)) {
         var7 = null;
      }

      if(!this.b(var8)) {
         var8 = null;
      }

      if(!this.b(var9)) {
         var9 = null;
      }

      if(!this.c(var10)) {
         var10 = null;
      }

      if(!this.c(var5)) {
         var5 = null;
      }

      Vec3D var11 = null;
      if(var6 != null) {
         var11 = var6;
      }

      if(var7 != null && (var11 == null || var4.b(var7) < var4.b(var11))) {
         var11 = var7;
      }

      if(var8 != null && (var11 == null || var4.b(var8) < var4.b(var11))) {
         var11 = var8;
      }

      if(var9 != null && (var11 == null || var4.b(var9) < var4.b(var11))) {
         var11 = var9;
      }

      if(var10 != null && (var11 == null || var4.b(var10) < var4.b(var11))) {
         var11 = var10;
      }

      if(var5 != null && (var11 == null || var4.b(var5) < var4.b(var11))) {
         var11 = var5;
      }

      if(var11 == null) {
         return null;
      } else {
         byte var12 = -1;
         if(var11 == var6) {
            var12 = 4;
         }

         if(var11 == var7) {
            var12 = 5;
         }

         if(var11 == var8) {
            var12 = 0;
         }

         if(var11 == var9) {
            var12 = 1;
         }

         if(var11 == var10) {
            var12 = 2;
         }

         if(var11 == var5) {
            var12 = 3;
         }

         return new MovingObjectPosition(var1, var2, var3, var12, var11.a((float)var1, (float)var2, (float)var3));
      }
   }

   private boolean a(Vec3D var1) {
      return var1 == null?false:var1.b >= this.af && var1.b <= this.ai && var1.c >= this.ag && var1.c <= this.aj;
   }

   private boolean b(Vec3D var1) {
      return var1 == null?false:var1.a >= this.ae && var1.a <= this.ah && var1.c >= this.ag && var1.c <= this.aj;
   }

   private boolean c(Vec3D var1) {
      return var1 == null?false:var1.a >= this.ae && var1.a <= this.ah && var1.b >= this.af && var1.b <= this.ai;
   }

   public void f(Level var1, int var2, int var3, int var4) {}

   public boolean a(Level var1, int var2, int var3, int var4, ShapeRender var5) {
      boolean var6 = false;
      float var7 = 0.5F;
      float var8 = 0.8F;
      float var9 = 0.6F;
      float var10;
      if(this.a(var1, var2, var3 - 1, var4, 0)) {
         var10 = this.a(var1, var2, var3 - 1, var4);
         var5.a(var7 * var10, var7 * var10, var7 * var10);
         this.a(var5, var2, var3, var4, 0);
         var6 = true;
      }

      if(this.a(var1, var2, var3 + 1, var4, 1)) {
         var10 = this.a(var1, var2, var3 + 1, var4);
         var5.a(var10 * 1.0F, var10 * 1.0F, var10 * 1.0F);
         this.a(var5, var2, var3, var4, 1);
         var6 = true;
      }

      if(this.a(var1, var2, var3, var4 - 1, 2)) {
         var10 = this.a(var1, var2, var3, var4 - 1);
         var5.a(var8 * var10, var8 * var10, var8 * var10);
         this.a(var5, var2, var3, var4, 2);
         var6 = true;
      }

      if(this.a(var1, var2, var3, var4 + 1, 3)) {
         var10 = this.a(var1, var2, var3, var4 + 1);
         var5.a(var8 * var10, var8 * var10, var8 * var10);
         this.a(var5, var2, var3, var4, 3);
         var6 = true;
      }

      if(this.a(var1, var2 - 1, var3, var4, 4)) {
         var10 = this.a(var1, var2 - 1, var3, var4);
         var5.a(var9 * var10, var9 * var10, var9 * var10);
         this.a(var5, var2, var3, var4, 4);
         var6 = true;
      }

      if(this.a(var1, var2 + 1, var3, var4, 5)) {
         var10 = this.a(var1, var2 + 1, var3, var4);
         var5.a(var9 * var10, var9 * var10, var9 * var10);
         this.a(var5, var2, var3, var4, 5);
         var6 = true;
      }

      return var6;
   }

   public int j() {
      return 0;
   }

   static {
      Block var10000 = (new StoneBlock(1, 1)).a(Tile$SoundType.stone, 1.0F, 1.0F, 1.0F);
      boolean var0 = false;
      Block var1 = var10000;
      var10000.ap = false;
      e = var1;
      f = (new GrassBlock(2)).a(Tile$SoundType.grass, 0.9F, 1.0F, 0.6F);
      g = (new DirtBlock(3, 2)).a(Tile$SoundType.grass, 0.8F, 1.0F, 0.5F);
      var10000 = (new Block(4, 16)).a(Tile$SoundType.stone, 1.0F, 1.0F, 1.5F);
      var0 = false;
      var1 = var10000;
      var10000.ap = false;
      h = var1;
      i = (new Block(5, 4)).a(Tile$SoundType.wood, 1.0F, 1.0F, 1.5F);
      j = (new SaplingBlock(6, 15)).a(Tile$SoundType.none, 0.7F, 1.0F, 0.0F);
      var10000 = (new Block(7, 17)).a(Tile$SoundType.stone, 1.0F, 1.0F, 999.0F);
      var0 = false;
      var1 = var10000;
      var10000.ap = false;
      k = var1;
      l = (new LiquidBlock(8, LiquidType.b)).a(Tile$SoundType.none, 1.0F, 1.0F, 100.0F);
      m = (new StillLiquidBlock(9, LiquidType.b)).a(Tile$SoundType.none, 1.0F, 1.0F, 100.0F);
      n = (new LiquidBlock(10, LiquidType.c)).a(Tile$SoundType.none, 1.0F, 1.0F, 100.0F);
      o = (new StillLiquidBlock(11, LiquidType.c)).a(Tile$SoundType.none, 1.0F, 1.0F, 100.0F);
      p = (new SandBlock(12, 18)).a(Tile$SoundType.gravel, 0.8F, 1.0F, 0.5F);
      q = (new SandBlock(13, 19)).a(Tile$SoundType.gravel, 0.8F, 1.0F, 0.6F);
      var10000 = (new OreBlock(14, 32)).a(Tile$SoundType.stone, 1.0F, 1.0F, 3.0F);
      var0 = false;
      var1 = var10000;
      var10000.ap = false;
      r = var1;
      var10000 = (new OreBlock(15, 33)).a(Tile$SoundType.stone, 1.0F, 1.0F, 3.0F);
      var0 = false;
      var1 = var10000;
      var10000.ap = false;
      s = var1;
      var10000 = (new OreBlock(16, 34)).a(Tile$SoundType.stone, 1.0F, 1.0F, 3.0F);
      var0 = false;
      var1 = var10000;
      var10000.ap = false;
      t = var1;
      u = (new WoodBlock(17)).a(Tile$SoundType.wood, 1.0F, 1.0F, 2.5F);
      v = (new LeavesBlock(18, 22)).a(Tile$SoundType.grass, 1.0F, 0.4F, 0.2F);
      w = (new SpongeBlock(19)).a(Tile$SoundType.cloth, 1.0F, 0.9F, 0.6F);
      x = (new GlassBlock(20, 49, false)).a(Tile$SoundType.metal, 1.0F, 1.0F, 0.3F);
      y = (new Block(21, 64)).a(Tile$SoundType.cloth, 1.0F, 1.0F, 0.8F);
      z = (new Block(22, 65)).a(Tile$SoundType.cloth, 1.0F, 1.0F, 0.8F);
      A = (new Block(23, 66)).a(Tile$SoundType.cloth, 1.0F, 1.0F, 0.8F);
      B = (new Block(24, 67)).a(Tile$SoundType.cloth, 1.0F, 1.0F, 0.8F);
      C = (new Block(25, 68)).a(Tile$SoundType.cloth, 1.0F, 1.0F, 0.8F);
      D = (new Block(26, 69)).a(Tile$SoundType.cloth, 1.0F, 1.0F, 0.8F);
      E = (new Block(27, 70)).a(Tile$SoundType.cloth, 1.0F, 1.0F, 0.8F);
      F = (new Block(28, 71)).a(Tile$SoundType.cloth, 1.0F, 1.0F, 0.8F);
      G = (new Block(29, 72)).a(Tile$SoundType.cloth, 1.0F, 1.0F, 0.8F);
      H = (new Block(30, 73)).a(Tile$SoundType.cloth, 1.0F, 1.0F, 0.8F);
      I = (new Block(31, 74)).a(Tile$SoundType.cloth, 1.0F, 1.0F, 0.8F);
      J = (new Block(32, 75)).a(Tile$SoundType.cloth, 1.0F, 1.0F, 0.8F);
      K = (new Block(33, 76)).a(Tile$SoundType.cloth, 1.0F, 1.0F, 0.8F);
      L = (new Block(34, 77)).a(Tile$SoundType.cloth, 1.0F, 1.0F, 0.8F);
      M = (new Block(35, 78)).a(Tile$SoundType.cloth, 1.0F, 1.0F, 0.8F);
      N = (new Block(36, 79)).a(Tile$SoundType.cloth, 1.0F, 1.0F, 0.8F);
      O = (new FlowerBlock(37, 13)).a(Tile$SoundType.none, 0.7F, 1.0F, 0.0F);
      P = (new FlowerBlock(38, 12)).a(Tile$SoundType.none, 0.7F, 1.0F, 0.0F);
      Q = (new MushroomBlock(39, 29)).a(Tile$SoundType.none, 0.7F, 1.0F, 0.0F);
      R = (new MushroomBlock(40, 28)).a(Tile$SoundType.none, 0.7F, 1.0F, 0.0F);
      var10000 = (new MetalBlock(41, 40)).a(Tile$SoundType.metal, 0.7F, 1.0F, 3.0F);
      var0 = false;
      var1 = var10000;
      var10000.ap = false;
      S = var1;
      var10000 = (new MetalBlock(42, 39)).a(Tile$SoundType.metal, 0.7F, 1.0F, 5.0F);
      var0 = false;
      var1 = var10000;
      var10000.ap = false;
      T = var1;
      var10000 = (new SlabBlock(43, true)).a(Tile$SoundType.stone, 1.0F, 1.0F, 2.0F);
      var0 = false;
      var1 = var10000;
      var10000.ap = false;
      U = var1;
      var10000 = (new SlabBlock(44, false)).a(Tile$SoundType.stone, 1.0F, 1.0F, 2.0F);
      var0 = false;
      var1 = var10000;
      var10000.ap = false;
      V = var1;
      var10000 = (new Block(45, 7)).a(Tile$SoundType.stone, 1.0F, 1.0F, 2.0F);
      var0 = false;
      var1 = var10000;
      var10000.ap = false;
      W = var1;
      X = (new TNTBlock(46, 8)).a(Tile$SoundType.cloth, 1.0F, 1.0F, 0.0F);
      Y = (new BookshelfBlock(47, 35)).a(Tile$SoundType.wood, 1.0F, 1.0F, 1.5F);
      var10000 = (new Block(48, 36)).a(Tile$SoundType.stone, 1.0F, 1.0F, 1.0F);
      var0 = false;
      var1 = var10000;
      var10000.ap = false;
      Z = var1;
      var10000 = (new StoneBlock(49, 37)).a(Tile$SoundType.stone, 1.0F, 1.0F, 10.0F);
      var0 = false;
      var1 = var10000;
      var10000.ap = false;
      aa = var1;
   }
}
