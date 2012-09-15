package de.jarnbjo.ogg;

import de.jarnbjo.ogg.EndOfOggStreamException;
import de.jarnbjo.ogg.b;
import de.jarnbjo.ogg.c;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public final class a {

   private b a;
   private ArrayList b = new ArrayList();
   private int c;
   private c d;
   private int e;


   public a(b var1) {
      new ArrayList();
      this.c = 0;
      this.a = var1;
   }

   private synchronized c b() {
      this.d = this.a.a();
      return this.d;
   }

   public final synchronized byte[] a() {
      ByteArrayOutputStream var1 = new ByteArrayOutputStream();
      boolean var2 = false;
      if(this.d == null) {
         this.d = this.b();
      }

      int var4;
      do {
         c var3 = this.d;
         if(this.e >= this.d.d.length) {
            this.e = 0;
            var3 = this.d;
            if(this.d.b) {
               throw new EndOfOggStreamException();
            }

            this.d = this.b();
            if(var1.size() == 0) {
               var3 = this.d;
               if(this.d.a) {
                  var2 = false;

                  while(!var2) {
                     var3 = this.d;
                     if(this.d.e[this.e++] != 255) {
                        var2 = true;
                     }

                     var3 = this.d;
                     if(this.e > this.d.f.length) {
                        b var10001 = this.a;
                        ((Integer)this.b.get(this.c++)).intValue();
                        this.d = var10001.a();
                     }
                  }
               }
            }
         }

         var3 = this.d;
         if(this.d.e.length == 0) {
            break;
         }

         var3 = this.d;
         var4 = this.d.e[this.e];
         var3 = this.d;
         var3 = this.d;
         var1.write(this.d.g, this.d.d[this.e], var4);
         ++this.e;
      } while(var4 == 255);

      return var1.toByteArray();
   }
}
