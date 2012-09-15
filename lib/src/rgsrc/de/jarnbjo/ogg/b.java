package de.jarnbjo.ogg;

import de.jarnbjo.ogg.a;
import de.jarnbjo.ogg.c;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.LinkedList;

public final class b {

   private URLConnection b;
   private InputStream c;
   private int d;
   public HashMap a;
   private c e;


   public b(URL var1) {
      new Object();
      new LinkedList();
      this.d = 0;
      this.a = new HashMap();
      this.b = var1.openConnection();
      this.c = this.b.getInputStream();
      this.b.getContentLength();
      this.e = c.a(this.c);
      this.d += this.e.a();
      a var3 = new a(this);
      c var2;
      this.a.put(new Integer((var2 = this.e).c), var3);
   }

   public final c a() {
      c var1;
      if(this.e != null) {
         var1 = this.e;
         this.e = null;
         return var1;
      } else {
         var1 = c.a(this.c);
         this.d += var1.a();
         return var1;
      }
   }
}
