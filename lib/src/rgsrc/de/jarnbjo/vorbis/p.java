package de.jarnbjo.vorbis;

import de.jarnbjo.vorbis.VorbisFormatException;
import de.jarnbjo.vorbis.b;
import de.jarnbjo.vorbis.j;
import de.jarnbjo.vorbis.k;
import de.jarnbjo.vorbis.m;
import de.jarnbjo.vorbis.n;
import de.jarnbjo.vorbis.q;

final class p extends n {

   private p() {}

   protected p(de.jarnbjo.a.a.a var1, q var2) {
      super(var1, var2);
   }

   protected final void a(b var1, de.jarnbjo.a.a.a var2, j var3, boolean[] var4, float[][] var5) {
      k var22 = this.a(var1, var3);
      int var6 = (this.b() - this.a()) / this.c();
      int var7 = this.c();
      m var8 = var22.b;
      int var9 = var22.b.b;
      int var10 = (var6 + var9 - 1) / var9;
      int var11 = 0;

      for(int var12 = 0; var12 < var4.length; ++var12) {
         if(!var4[var12]) {
            ++var11;
         }
      }

      float[][] var26 = new float[var11][];
      var11 = 0;

      for(int var13 = 0; var13 < var4.length; ++var13) {
         if(!var4[var13]) {
            var26[var11++] = var5[var13];
         }
      }

      int[][] var27 = new int[var10][];

      for(int var23 = 0; var23 < var22.a; ++var23) {
         int var24 = 0;

         for(var10 = 0; var24 < var6; ++var10) {
            if(var23 == 0) {
               var8 = var22.b;
               if((var11 = var2.a(var22.b.a)) == -1) {
                  throw new VorbisFormatException("");
               }

               var27[var10] = var22.d[var11];
               if(var27[var10] == null) {
                  throw new VorbisFormatException("");
               }
            }

            for(var11 = 0; var11 < var9 && var24 < var6; ++var24) {
               int var14 = this.a + var24 * var7;
               if((this.b[var27[var10][var11]] & 1 << var23) != 0) {
                  q var25 = var1.b;
                  if((var8 = var1.b.a[var22.c[var27[var10][var11]][var23]]) != null) {
                     de.jarnbjo.a.a.a var17 = var2;
                     float[][] var28 = var26;
                     var8 = var8;
                     int var18 = 0;
                     int var19;
                     if((var19 = var26.length) != 0) {
                        int var20 = (var14 + var7) / var19;
                        int var16 = var14 / var19;

                        while(var16 < var20) {
                           float[] var21 = var8.c[var17.a(var8.a)];

                           for(int var15 = 0; var15 < var8.b; ++var15) {
                              int var10001 = var18++;
                              var28[var10001][var16] += var21[var15];
                              if(var18 == var19) {
                                 var18 = 0;
                                 ++var16;
                              }
                           }
                        }
                     }
                  }
               }

               ++var11;
            }
         }
      }

   }

   public final Object clone() {
      p var1 = new p();
      this.a(var1);
      return var1;
   }
}
