package de.jarnbjo.vorbis;

import de.jarnbjo.vorbis.VorbisFormatException;
import de.jarnbjo.vorbis.a;
import de.jarnbjo.vorbis.b;
import de.jarnbjo.vorbis.q;

abstract class i {

   protected static i a(b var0, de.jarnbjo.a.a.a var1, q var2) {
      int var3;
      switch(var3 = var1.a(16)) {
      case 0:
         return new a(var0, var1, var2);
      default:
         throw new VorbisFormatException("Mapping type " + var3 + " is not supported.");
      }
   }

   protected abstract int[] a();

   protected abstract int[] b();

   protected abstract int[] c();

   protected abstract int[] d();

   protected abstract int[] e();

   protected abstract int f();

   protected abstract int g();
}
