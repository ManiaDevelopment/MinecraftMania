package de.jarnbjo.vorbis;

import com.mojang.util.MathHelper;
import de.jarnbjo.vorbis.VorbisFormatException;
import de.jarnbjo.vorbis.b;
import de.jarnbjo.vorbis.g;
import de.jarnbjo.vorbis.h;
import de.jarnbjo.vorbis.i;
import de.jarnbjo.vorbis.j;
import de.jarnbjo.vorbis.n;
import de.jarnbjo.vorbis.q;
import de.jarnbjo.vorbis.r;

public final class f {

   private int a;
   private j b;
   private i c;
   private int d;
   private boolean e;
   private boolean f;
   private boolean g;
   private int h;
   private int i;
   private int j;
   private int k;
   private int l;
   private int m;
   private float[] n;
   private float[][] o;
   private int[][] p;
   private g[] q;
   private boolean[] r;
   private static final float[][] s = new float[8][];


   protected f(b var1, de.jarnbjo.a.a.a var2) {
      q var4 = var1.b;
      r var5 = var1.a;
      j[] var7 = var4.e;
      i[] var8 = var4.d;
      n[] var9 = var4.c;
      int var10 = var5.a;
      if(var2.a(1) != 0) {
         throw new VorbisFormatException("Packet type mismatch when trying to create an audio packet.");
      } else {
         this.a = var2.a(h.a(var7.length - 1));

         try {
            this.b = var7[this.a];
         } catch (ArrayIndexOutOfBoundsException var14) {
            throw new VorbisFormatException("Reference to invalid mode in audio packet.");
         }

         j var6 = this.b;
         this.c = var8[this.b.b];
         int[] var19 = this.c.b();
         int[] var21 = this.c.a();
         var6 = this.b;
         this.e = this.b.a;
         int var18 = var5.c;
         int var11 = var5.d;
         this.d = this.e?var11:var18;
         if(this.e) {
            this.f = var2.a();
            this.g = var2.a();
         }

         this.h = this.d / 2;
         if(this.e && !this.f) {
            this.i = this.d / 4 - var18 / 4;
            this.j = this.d / 4 + var18 / 4;
            this.k = var18 / 2;
         } else {
            this.i = 0;
            this.j = this.d / 2;
            this.k = this.h;
         }

         if(this.e && !this.g) {
            this.l = this.d * 3 / 4 - var18 / 4;
            this.m = var18 / 2;
         } else {
            this.l = this.h;
            this.m = this.d / 2;
         }

         this.n = this.b();
         this.q = new g[var10];
         this.r = new boolean[var10];
         this.o = new float[var10][this.d];
         this.p = new int[var10][this.d];
         boolean var20 = true;

         int var3;
         for(var11 = 0; var11 < var10; ++var11) {
            var3 = this.c.c()[var11];
            int var12 = this.c.d()[var3];
            g var13 = var4.b[var12].a(var1, var2);
            this.q[var11] = var13;
            this.r[var11] = var13 == null;
            if(var13 != null) {
               var20 = false;
            }
         }

         if(!var20) {
            for(var11 = 0; var11 < var19.length; ++var11) {
               if(!this.r[var19[var11]] || !this.r[var21[var11]]) {
                  this.r[var19[var11]] = false;
                  this.r[var21[var11]] = false;
               }
            }

            int var24;
            for(var11 = 0; var11 < this.c.g(); ++var11) {
               var3 = 0;
               boolean[] var22 = new boolean[var10];

               for(var24 = 0; var24 < var10; ++var24) {
                  if(this.c.c()[var24] == var11) {
                     var22[var3++] = this.r[var24];
                  }
               }

               var24 = this.c.e()[var11];
               var9[var24].a(var1, var2, this.b, var22, this.o);
            }

            for(var11 = this.c.f() - 1; var11 >= 0; --var11) {
               float[] var16 = this.o[var19[var11]];
               float[] var23 = this.o[var21[var11]];

               for(var24 = 0; var24 < var16.length; ++var24) {
                  float var17 = var23[var24];
                  float var15 = var16[var24];
                  if(var17 > 0.0F) {
                     var23[var24] = var15 > 0.0F?var15 - var17:var15 + var17;
                  } else {
                     var16[var24] = var15 > 0.0F?var15 + var17:var15 - var17;
                     var23[var24] = var15;
                  }
               }
            }

            for(var11 = 0; var11 < var10; ++var11) {
               if(this.q[var11] != null) {
                  this.q[var11].a(this.o[var11]);
               }
            }

            for(var11 = 0; var11 < var10; ++var11) {
               (this.e?var5.e[1]:var5.e[0]).a(this.o[var11], this.n, this.p[var11]);
            }

         }
      }
   }

   private float[] b() {
      int var1 = (this.e?4:0) + (this.f?2:0) + (this.g?1:0);
      float[] var2;
      if((var2 = s[var1]) == null) {
         var2 = new float[this.d];

         int var3;
         float var4;
         for(var3 = 0; var3 < this.k; ++var3) {
            var4 = MathHelper.a((float)((double)((var4 = MathHelper.a((float)(((double)var3 + 0.5D) / (double)this.k * 3.1415927410125732D / 2.0D))) * var4) * 1.5707963705062866D));
            var2[var3 + this.i] = var4;
         }

         for(var3 = this.j; var3 < this.l; var2[var3++] = 1.0F) {
            ;
         }

         for(var3 = 0; var3 < this.m; ++var3) {
            var4 = MathHelper.a((float)((double)((var4 = MathHelper.a((float)(((double)(this.m - var3) - 0.5D) / (double)this.m * 3.1415927410125732D / 2.0D))) * var4) * 1.5707963705062866D));
            var2[var3 + this.l] = var4;
         }

         s[var1] = var2;
      }

      return var2;
   }

   protected final int a() {
      return this.l - this.i;
   }

   protected final void a(f var1, byte[] var2) {
      int var3 = this.o.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         int var5 = 0;
         int var6 = var1.l;
         int[] var7 = var1.p[var4];
         int[] var8 = this.p[var4];

         int var9;
         int var10;
         for(var9 = this.i; var9 < this.j; ++var9) {
            if((var10 = var7[var6++] + var8[var9]) > 32767) {
               var10 = 32767;
            }

            if(var10 < -32768) {
               var10 = -32768;
            }

            var2[var5 + (var4 << 1) + 1] = (byte)var10;
            var2[var5 + (var4 << 1)] = (byte)(var10 >> 8);
            var5 += var3 << 1;
         }

         var5 = (this.j - this.i) * var3 << 1;

         for(var9 = this.j; var9 < this.l; ++var9) {
            if((var10 = var8[var9]) > 32767) {
               var10 = 32767;
            }

            if(var10 < -32768) {
               var10 = -32768;
            }

            var2[var5 + (var4 << 1) + 1] = (byte)var10;
            var2[var5 + (var4 << 1)] = (byte)(var10 >> 8);
            var5 += var3 << 1;
         }
      }

   }

}
