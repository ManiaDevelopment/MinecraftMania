package de.jarnbjo.vorbis;

import de.jarnbjo.vorbis.VorbisFormatException;
import de.jarnbjo.vorbis.l;

public final class r {

   public int a;
   public int b;
   int c;
   int d;
   l[] e = new l[2];


   public r(de.jarnbjo.a.a.a var1) {
      if(var1.b(48) != 126896460427126L) {
         throw new VorbisFormatException("The identification header has an illegal leading.");
      } else {
         var1.a(32);
         this.a = var1.a(8);
         this.b = var1.a(32);
         var1.a(32);
         var1.a(32);
         var1.a(32);
         int var4 = var1.a(8);
         this.c = 1 << (var4 & 15);
         this.d = 1 << (var4 >> 4);
         this.e[0] = new l(this.c);
         this.e[1] = new l(this.d);
         var1.a(8);
      }
   }
}
