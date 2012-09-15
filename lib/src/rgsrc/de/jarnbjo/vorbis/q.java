package de.jarnbjo.vorbis;

import de.jarnbjo.vorbis.VorbisFormatException;
import de.jarnbjo.vorbis.b;
import de.jarnbjo.vorbis.g;
import de.jarnbjo.vorbis.i;
import de.jarnbjo.vorbis.j;
import de.jarnbjo.vorbis.m;
import de.jarnbjo.vorbis.n;

final class q {

   m[] a;
   g[] b;
   n[] c;
   i[] d;
   j[] e;


   public q(b var1, de.jarnbjo.a.a.a var2) {
      if(var2.b(48) != 126896460427126L) {
         throw new VorbisFormatException("The setup header has an illegal leading.");
      } else {
         int var3 = var2.a(8) + 1;
         this.a = new m[var3];

         for(var3 = 0; var3 < this.a.length; ++var3) {
            this.a[var3] = new m(var2);
         }

         var3 = var2.a(6) + 1;

         int var4;
         for(var4 = 0; var4 < var3; ++var4) {
            if(var2.a(16) != 0) {
               throw new VorbisFormatException("Time domain transformation != 0");
            }
         }

         var4 = var2.a(6) + 1;
         this.b = new g[var4];

         for(var3 = 0; var3 < var4; ++var3) {
            this.b[var3] = g.a(var2, this);
         }

         var3 = var2.a(6) + 1;
         this.c = new n[var3];

         for(var4 = 0; var4 < var3; ++var4) {
            this.c[var4] = n.a(var2, this);
         }

         var4 = var2.a(6) + 1;
         this.d = new i[var4];

         for(var3 = 0; var3 < var4; ++var3) {
            this.d[var3] = i.a(var1, var2, this);
         }

         var3 = var2.a(6) + 1;
         this.e = new j[var3];

         for(int var5 = 0; var5 < var3; ++var5) {
            this.e[var5] = new j(var2, this);
         }

         if(!var2.a()) {
            throw new VorbisFormatException("The setup header framing bit is incorrect.");
         }
      }
   }
}
