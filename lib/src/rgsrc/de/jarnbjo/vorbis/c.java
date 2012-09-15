package de.jarnbjo.vorbis;

import de.jarnbjo.vorbis.VorbisFormatException;
import de.jarnbjo.vorbis.b;
import de.jarnbjo.vorbis.g;
import de.jarnbjo.vorbis.q;

final class c extends g {

   private int[] b;


   protected c(de.jarnbjo.a.a.a var1, q var2) {
      var1.a(8);
      var1.a(16);
      var1.a(16);
      var1.a(6);
      var1.a(8);
      int var3 = var1.a(4) + 1;
      this.b = new int[var3];

      for(var3 = 0; var3 < this.b.length; ++var3) {
         this.b[var3] = var1.a(8);
         if(this.b[var3] > var2.a.length) {
            throw new VorbisFormatException("A floor0_book_list entry is higher than the code book count.");
         }
      }

   }

   protected final g a(b var1, de.jarnbjo.a.a.a var2) {
      throw new UnsupportedOperationException();
   }

   protected final void a(float[] var1) {
      throw new UnsupportedOperationException();
   }
}
