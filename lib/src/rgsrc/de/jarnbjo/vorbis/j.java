package de.jarnbjo.vorbis;

import de.jarnbjo.vorbis.VorbisFormatException;
import de.jarnbjo.vorbis.q;

final class j {

   boolean a;
   private int c;
   private int d;
   int b;


   protected j(de.jarnbjo.a.a.a var1, q var2) {
      this.a = var1.a();
      this.c = var1.a(16);
      this.d = var1.a(16);
      this.b = var1.a(8);
      if(this.c != 0) {
         throw new VorbisFormatException("Window type = " + this.c + ", != 0");
      } else if(this.d != 0) {
         throw new VorbisFormatException("Transform type = " + this.d + ", != 0");
      } else if(this.b > var2.d.length) {
         throw new VorbisFormatException("Mode mapping number is higher than total number of mappings.");
      }
   }
}
