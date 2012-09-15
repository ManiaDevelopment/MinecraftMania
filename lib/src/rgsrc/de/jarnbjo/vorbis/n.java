package de.jarnbjo.vorbis;

import de.jarnbjo.vorbis.VorbisFormatException;
import de.jarnbjo.vorbis.b;
import de.jarnbjo.vorbis.j;
import de.jarnbjo.vorbis.k;
import de.jarnbjo.vorbis.o;
import de.jarnbjo.vorbis.p;
import de.jarnbjo.vorbis.q;
import java.util.HashMap;

abstract class n {

   protected int a;
   private int c;
   private int d;
   private int e;
   private int f;
   protected int[] b;
   private int[][] g;
   private HashMap h = new HashMap();


   protected n() {}

   protected n(de.jarnbjo.a.a.a var1, q var2) {
      this.a = var1.a(24);
      this.c = var1.a(24);
      this.d = var1.a(24) + 1;
      this.e = var1.a(6) + 1;
      this.f = var1.a(8);
      this.b = new int[this.e];

      int var3;
      int var4;
      for(var3 = 0; var3 < this.e; ++var3) {
         var4 = 0;
         boolean var5 = false;
         int var6 = var1.a(3);
         if(var1.a()) {
            var4 = var1.a(5);
         }

         this.b[var3] = var4 << 3 | var6;
      }

      this.g = new int[this.e][8];

      for(var3 = 0; var3 < this.e; ++var3) {
         for(var4 = 0; var4 < 8; ++var4) {
            if((this.b[var3] & 1 << var4) != 0) {
               this.g[var3][var4] = var1.a(8);
               if(this.g[var3][var4] > var2.a.length) {
                  throw new VorbisFormatException("Reference to invalid codebook entry in residue header.");
               }
            }
         }
      }

   }

   protected static n a(de.jarnbjo.a.a.a var0, q var1) {
      int var2;
      switch(var2 = var0.a(16)) {
      case 0:
         return new o(var0, var1);
      case 1:
         return new p(var0, var1);
      case 2:
         return new p(var0, var1);
      default:
         throw new VorbisFormatException("Residue type " + var2 + " is not supported.");
      }
   }

   protected abstract void a(b var1, de.jarnbjo.a.a.a var2, j var3, boolean[] var4, float[][] var5);

   protected final int a() {
      return this.a;
   }

   protected final int b() {
      return this.c;
   }

   protected final int c() {
      return this.d;
   }

   protected final int d() {
      return this.e;
   }

   protected final int e() {
      return this.f;
   }

   protected final int[] f() {
      return this.b;
   }

   protected final int[][] g() {
      return this.g;
   }

   protected final void a(n var1) {
      var1.a = this.a;
      var1.g = this.g;
      var1.b = this.b;
      var1.f = this.f;
      var1.e = this.e;
      var1.c = this.c;
      var1.d = this.d;
   }

   protected final k a(b var1, j var2) {
      k var3;
      if((var3 = (k)this.h.get(var2)) == null) {
         var3 = new k(this, var1, var2);
         this.h.put(var2, var3);
      }

      return var3;
   }
}
