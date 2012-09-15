package de.jarnbjo.vorbis;

import de.jarnbjo.vorbis.VorbisFormatException;
import de.jarnbjo.vorbis.b;
import de.jarnbjo.vorbis.h;
import de.jarnbjo.vorbis.i;
import de.jarnbjo.vorbis.q;

final class a extends i {

   private int[] a;
   private int[] b;
   private int[] c;
   private int[] d;
   private int[] e;


   protected a(b var1, de.jarnbjo.a.a.a var2, q var3) {
      int var4 = 1;
      if(var2.a()) {
         var4 = var2.a(4) + 1;
      }

      int var8;
      int var5 = h.a((var8 = var1.a.a) - 1);
      int var6;
      int var7;
      if(var2.a()) {
         var6 = var2.a(8) + 1;
         this.a = new int[var6];
         this.b = new int[var6];

         for(var7 = 0; var7 < var6; ++var7) {
            this.a[var7] = var2.a(var5);
            this.b[var7] = var2.a(var5);
            if(this.a[var7] == this.b[var7] || this.a[var7] >= var8 || this.b[var7] >= var8) {
               System.err.println(this.a[var7]);
               System.err.println(this.b[var7]);
               throw new VorbisFormatException("The channel magnitude and/or angle mismatch.");
            }
         }
      } else {
         this.a = new int[0];
         this.b = new int[0];
      }

      if(var2.a(2) != 0) {
         throw new VorbisFormatException("A reserved mapping field has an invalid value.");
      } else {
         this.c = new int[var8];
         if(var4 > 1) {
            for(var6 = 0; var6 < var8; ++var6) {
               this.c[var6] = var2.a(4);
               if(this.c[var6] > var4) {
                  throw new VorbisFormatException("A mapping mux value is higher than the number of submaps");
               }
            }
         } else {
            for(var6 = 0; var6 < var8; ++var6) {
               this.c[var6] = 0;
            }
         }

         this.d = new int[var4];
         this.e = new int[var4];
         var6 = var3.b.length;
         var7 = var3.c.length;

         for(var8 = 0; var8 < var4; ++var8) {
            var2.a(8);
            this.d[var8] = var2.a(8);
            this.e[var8] = var2.a(8);
            if(this.d[var8] > var6) {
               throw new VorbisFormatException("A mapping floor value is higher than the number of floors.");
            }

            if(this.e[var8] > var7) {
               throw new VorbisFormatException("A mapping residue value is higher than the number of residues.");
            }
         }

      }
   }

   protected final int[] a() {
      return this.b;
   }

   protected final int[] b() {
      return this.a;
   }

   protected final int[] c() {
      return this.c;
   }

   protected final int[] d() {
      return this.d;
   }

   protected final int[] e() {
      return this.e;
   }

   protected final int f() {
      return this.b.length;
   }

   protected final int g() {
      return this.d.length;
   }
}
