package de.jarnbjo.vorbis;

import de.jarnbjo.vorbis.VorbisFormatException;
import de.jarnbjo.vorbis.b;
import de.jarnbjo.vorbis.g;
import de.jarnbjo.vorbis.h;
import de.jarnbjo.vorbis.q;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

final class e extends g implements Cloneable {

   private int[] b;
   private int c;
   private int d;
   private int e;
   private int[] f;
   private int[] g;
   private int[] h;
   private int[][] i;
   private int[] j;
   private int[] k;
   private int[] l;
   private int[] m;
   private static final int[] n = new int[]{256, 128, 86, 64};


   private e() {}

   protected e(de.jarnbjo.a.a.a var1, q var2) {
      this.c = -1;
      int var3 = var1.a(5);
      this.b = new int[var3];

      int var4;
      for(var4 = 0; var4 < this.b.length; ++var4) {
         this.b[var4] = var1.a(4);
         if(this.b[var4] > this.c) {
            this.c = this.b[var4];
         }
      }

      this.f = new int[this.c + 1];
      this.g = new int[this.c + 1];
      this.h = new int[this.c + 1];
      this.i = new int[this.c + 1][];

      int var5;
      for(var4 = 0; var4 <= this.c; ++var4) {
         this.f[var4] = var1.a(3) + 1;
         this.g[var4] = var1.a(2);
         if(this.f[var4] > var2.a.length || this.g[var4] > var2.a.length) {
            throw new VorbisFormatException("There is a class dimension or class subclasses entry higher than the number of codebooks in the setup header.");
         }

         if(this.g[var4] != 0) {
            this.h[var4] = var1.a(8);
         }

         this.i[var4] = new int[1 << this.g[var4]];

         for(var5 = 0; var5 < this.i[var4].length; ++var5) {
            this.i[var4][var5] = var1.a(8) - 1;
         }
      }

      this.d = var1.a(2) + 1;
      this.e = var1.a(4);
      ArrayList var7;
      (var7 = new ArrayList()).add(new Integer(0));
      var7.add(new Integer(1 << this.e));

      int var6;
      for(var5 = 0; var5 < var3; ++var5) {
         for(var6 = 0; var6 < this.f[this.b[var5]]; ++var6) {
            var7.add(new Integer(var1.a(this.e)));
         }
      }

      this.j = new int[var7.size()];
      this.l = new int[this.j.length];
      this.m = new int[this.j.length];
      Iterator var8 = var7.iterator();

      for(var6 = 0; var6 < this.j.length; ++var6) {
         this.j[var6] = ((Integer)var8.next()).intValue();
      }

      for(var6 = 0; var6 < this.j.length; ++var6) {
         this.l[var6] = h.a(this.j, var6);
         this.m[var6] = h.b(this.j, var6);
      }

   }

   protected final g a(b var1, de.jarnbjo.a.a.a var2) {
      if(!var2.a()) {
         return null;
      } else {
         e var3;
         (var3 = (e)this.clone()).k = new int[this.j.length];
         int var4 = n[this.d - 1];
         var3.k[0] = var2.a(h.a(var4 - 1));
         var3.k[1] = var2.a(h.a(var4 - 1));
         var4 = 2;

         for(int var5 = 0; var5 < this.b.length; ++var5) {
            int var6 = this.b[var5];
            int var7 = this.f[var6];
            int var8 = this.g[var6];
            int var9 = (1 << var8) - 1;
            int var10 = 0;
            q var11;
            if(var8 > 0) {
               var11 = var1.b;
               var10 = var2.a(var1.b.a[this.h[var6]].a);
            }

            for(int var12 = 0; var12 < var7; ++var12) {
               int var13 = this.i[var6][var10 & var9];
               var10 >>>= var8;
               if(var13 >= 0) {
                  int var10001 = var12 + var4;
                  var11 = var1.b;
                  var3.k[var10001] = var2.a(var1.b.a[var13].a);
               } else {
                  var3.k[var12 + var4] = 0;
               }
            }

            var4 += var7;
         }

         return var3;
      }
   }

   protected final void a(float[] var1) {
      int var2 = var1.length;
      int var3;
      boolean[] var4 = new boolean[var3 = this.j.length];
      int var5 = n[this.d - 1];

      int var7;
      int var8;
      int var9;
      int var10;
      int var12;
      int var13;
      for(int var6 = 2; var6 < var3; ++var6) {
         var7 = this.l[var6];
         var8 = this.m[var6];
         int var10000 = this.j[var7];
         int var10001 = this.j[var8];
         int var10002 = this.k[var7];
         int var10003 = this.k[var8];
         var9 = this.j[var6];
         var10 = var10003;
         int var11 = var10002;
         var12 = var10001;
         var13 = var10000;
         var9 = ((var10 -= var11) < 0?-var10:var10) * (var9 - var13) / (var12 - var13);
         var13 = var10 < 0?var11 - var9:var11 + var9;
         var12 = this.k[var6];
         var11 = var5 - var13;
         var9 = var11 < var13?var11 << 1:var13 << 1;
         if(var12 != 0) {
            var4[var7] = true;
            var4[var8] = true;
            var4[var6] = true;
            if(var12 >= var9) {
               this.k[var6] = var11 > var13?var12 - var13 + var13:-var12 + var11 + var13 - 1;
            } else {
               this.k[var6] = (var12 & 1) == 1?var13 - (var12 + 1 >> 1):var13 + (var12 >> 1);
            }
         } else {
            var4[var6] = false;
            this.k[var6] = var13;
         }
      }

      int[] var15 = new int[var3];
      System.arraycopy(this.j, 0, var15, 0, var3);
      boolean[] var20 = var4;
      int[] var19 = this.k;
      int[] var22 = var15;
      var10 = var15.length;

      for(var5 = 0; var5 < var10; ++var5) {
         for(var7 = var5; var7 > 0 && var22[var7 - 1] > var22[var7]; --var7) {
            int var14 = var22[var7];
            var22[var7] = var22[var7 - 1];
            var22[var7 - 1] = var14;
            var14 = var19[var7];
            var19[var7] = var19[var7 - 1];
            var19[var7 - 1] = var14;
            boolean var16 = var20[var7];
            var20[var7] = var20[var7 - 1];
            var20[var7 - 1] = var16;
         }
      }

      var7 = 0;
      var8 = 0;
      var13 = 0;
      var12 = this.k[0] * this.d;
      float[] var21 = new float[var1.length];
      float[] var17 = new float[var1.length];
      Arrays.fill(var21, 1.0F);
      System.arraycopy(var1, 0, var17, 0, var1.length);

      for(var9 = 1; var9 < var3; ++var9) {
         if(var4[var9]) {
            var8 = this.k[var9] * this.d;
            var7 = var15[var9];
            h.a(var13, var12, var7, var8, var1);
            h.a(var13, var12, var7, var8, var21);
            var13 = var7;
            var12 = var8;
         }
      }

      for(float var18 = a[var8]; var7 < var2 / 2; var1[var7++] = var18) {
         ;
      }

   }

   public final Object clone() {
      e var1;
      (var1 = new e()).f = this.f;
      var1.h = this.h;
      var1.g = this.g;
      var1.c = this.c;
      var1.d = this.d;
      var1.b = this.b;
      var1.e = this.e;
      var1.i = this.i;
      var1.j = this.j;
      var1.k = this.k;
      var1.l = this.l;
      var1.m = this.m;
      return var1;
   }

}
