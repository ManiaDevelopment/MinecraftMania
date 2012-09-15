package de.jarnbjo.vorbis;

import de.jarnbjo.vorbis.b;
import de.jarnbjo.vorbis.h;
import de.jarnbjo.vorbis.j;
import de.jarnbjo.vorbis.m;
import de.jarnbjo.vorbis.n;

final class k {

   private int e;
   int a;
   private m[] f;
   m b;
   int[][] c;
   private int g;
   int[][] d;


   protected k(n var1, b var2, j var3) {
      boolean var10 = false;
      int var4 = 0;
      this.e = var1.d();
      this.f = var2.b.a;
      this.b = this.f[var1.e()];
      m var8 = this.b;
      int var11 = this.b.b;
      this.c = new int[this.e][];

      int var5;
      int var6;
      int var9;
      for(var9 = 0; var9 < this.e; ++var9) {
         if((var5 = h.a(var1.f()[var9])) != 0) {
            if(var5 > var4) {
               var4 = var5;
            }

            this.c[var9] = new int[var5];

            for(var6 = 0; var6 < var5; ++var6) {
               if((var1.f()[var9] & 1 << var6) != 0) {
                  this.c[var9][var6] = var1.g()[var9][var6];
               }
            }
         }
      }

      this.g = (int)Math.rint(Math.pow((double)this.e, (double)var11));
      this.a = var4;
      this.d = new int[this.g][];

      for(var9 = 0; var9 < this.g; ++var9) {
         var5 = var9;
         var6 = this.g / this.e;
         this.d[var9] = new int[var11];

         for(int var7 = 0; var7 < var11; ++var7) {
            var4 = var5 / var6;
            var5 -= var4 * var6;
            var6 /= this.e;
            this.d[var9][var7] = var4;
         }
      }

   }
}
