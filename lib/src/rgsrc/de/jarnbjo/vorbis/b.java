package de.jarnbjo.vorbis;

import de.jarnbjo.vorbis.VorbisFormatException;
import de.jarnbjo.vorbis.d;
import de.jarnbjo.vorbis.f;
import de.jarnbjo.vorbis.q;
import de.jarnbjo.vorbis.r;
import java.util.LinkedList;

public final class b {

   private de.jarnbjo.ogg.a c;
   public r a;
   private d d;
   q b;
   private f e;
   private byte[] f;
   private int g;
   private int h;
   private Object i;
   private int j;
   private long k;


   public b() {
      new LinkedList();
      this.i = new Object();
      this.j = 0;
   }

   public b(de.jarnbjo.ogg.a var1) {
      new LinkedList();
      this.i = new Object();
      this.j = 0;
      this.c = var1;

      for(int var2 = 0; var2 < 3; ++var2) {
         de.jarnbjo.a.a.a var3;
         switch((var3 = new de.jarnbjo.a.a.a(var1.a())).a(8)) {
         case 1:
            this.a = new r(var3);
         case 2:
         case 4:
         default:
            break;
         case 3:
            this.d = new d(var3);
            break;
         case 5:
            this.b = new q(this, var3);
         }
      }

      if(this.a == null) {
         throw new VorbisFormatException("The file has no identification header.");
      } else if(this.d == null) {
         throw new VorbisFormatException("The file has no commentHeader.");
      } else if(this.b == null) {
         throw new VorbisFormatException("The file has no setup header.");
      } else {
         r var5 = this.a;
         var5 = this.a;
         this.f = new byte[this.a.a * this.a.d << 1];
      }
   }

   public final int a(byte[] var1, int var2, int var3) {
      Object var4 = this.i;
      synchronized(this.i) {
         if(this.e == null) {
            this.e = this.a();
         }

         if(this.f == null || this.g >= this.h) {
            f var5 = this.a();

            try {
               var5.a(this.e, this.f);
               int var10001 = var5.a();
               r var6 = this.a;
               this.h = var10001 * this.a.a << 1;
            } catch (ArrayIndexOutOfBoundsException var8) {
               return 0;
            }

            this.g = 0;
            this.e = var5;
         }

         int var10 = 0;
         boolean var12 = false;
         int var7 = 0;

         int var11;
         for(var11 = this.g; var11 < this.h && var7 < var3; ++var11) {
            var1[var2 + var7++] = this.f[var11];
            ++var10;
         }

         this.g = var11;
         return var10;
      }
   }

   private f a() {
      ++this.j;
      byte[] var1 = this.c.a();
      f var2 = null;

      while(var2 == null) {
         try {
            var2 = new f(this, new de.jarnbjo.a.a.a(var1));
         } catch (ArrayIndexOutOfBoundsException var3) {
            ;
         }
      }

      this.k += (long)var2.a();
      return var2;
   }
}
