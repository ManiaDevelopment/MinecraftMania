package de.jarnbjo.vorbis;

import de.jarnbjo.vorbis.VorbisFormatException;
import de.jarnbjo.vorbis.h;
import java.util.Arrays;

final class m {

   de.jarnbjo.a.a.b a;
   int b;
   private int d;
   private int[] e;
   float[][] c;


   protected m(de.jarnbjo.a.a.a var1) {
      if(var1.a(24) != 5653314) {
         throw new VorbisFormatException("The code book sync pattern is not correct.");
      } else {
         this.b = var1.a(16);
         this.d = var1.a(24);
         this.e = new int[this.d];
         int var2;
         int var3;
         if(var1.a()) {
            var2 = var1.a(5) + 1;

            int var4;
            for(var3 = 0; var3 < this.e.length; var3 += var4) {
               var4 = var1.a(h.a(this.e.length - var3));
               if(var3 + var4 > this.e.length) {
                  throw new VorbisFormatException("The codebook entry length list is longer than the actual number of entry lengths.");
               }

               Arrays.fill(this.e, var3, var3 + var4, var2);
               ++var2;
            }
         } else if(var1.a()) {
            for(var3 = 0; var3 < this.e.length; ++var3) {
               if(var1.a()) {
                  this.e[var3] = var1.a(5) + 1;
               } else {
                  this.e[var3] = -1;
               }
            }
         } else {
            for(var3 = 0; var3 < this.e.length; ++var3) {
               this.e[var3] = var1.a(5) + 1;
            }
         }

         if(!this.a(this.e)) {
            throw new VorbisFormatException("An exception was thrown when building the codebook Huffman tree.");
         } else {
            switch(var2 = var1.a(4)) {
            case 0:
               return;
            case 1:
            case 2:
               float var14 = h.b(var1.a(32));
               float var13 = h.b(var1.a(32));
               int var5 = var1.a(4) + 1;
               boolean var6 = var1.a();
               boolean var7 = false;
               int var9;
               int var10;
               int var15;
               if(var2 == 1) {
                  int var8 = this.b;
                  var15 = this.d;
                  int var10000 = (var9 = (int)Math.pow(2.718281828459045D, Math.log((double)var15) / (double)var8)) + 1;
                  var10 = var8;
                  var8 = var10000;

                  int var11;
                  for(var11 = 1; var10 > 0; var11 *= var8) {
                     --var10;
                  }

                  var15 = var11 <= var15?var9 + 1:var9;
               } else {
                  var15 = this.d * this.b;
               }

               int[] var16 = new int[var15];

               for(var9 = 0; var9 < var16.length; ++var9) {
                  var16[var9] = var1.a(var5);
               }

               this.c = new float[this.d][this.b];
               if(var2 != 1) {
                  throw new UnsupportedOperationException();
               } else {
                  for(var9 = 0; var9 < this.d; ++var9) {
                     float var12 = 0.0F;
                     var2 = 1;

                     for(var5 = 0; var5 < this.b; ++var5) {
                        var10 = var9 / var2 % var15;
                        this.c[var9][var5] = (float)var16[var10] * var13 + var14 + var12;
                        if(var6) {
                           var12 = this.c[var9][var5];
                        }

                        var2 *= var15;
                     }
                  }

                  return;
               }
            default:
               throw new VorbisFormatException("Unsupported codebook lookup type: " + var2);
            }
         }
      }
   }

   private boolean a(int[] var1) {
      this.a = new de.jarnbjo.a.a.b();

      for(int var2 = 0; var2 < var1.length; ++var2) {
         int var3;
         if((var3 = var1[var2]) > 0 && !this.a.a(var3, var2)) {
            return false;
         }
      }

      return true;
   }
}
