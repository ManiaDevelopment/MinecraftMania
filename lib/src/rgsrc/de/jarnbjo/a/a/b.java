package de.jarnbjo.a.a;


public final class b {

   private int d;
   protected b a;
   protected b b;
   protected Integer c;
   private boolean e;


   public b() {
      this((b)null);
   }

   private b(b var1) {
      this.d = 0;
      this.e = false;
      if(var1 != null) {
         this.d = var1.d + 1;
      }

   }

   private b(b var1, int var2) {
      this(var1);
      this.c = new Integer(var2);
      this.e = true;
   }

   private boolean a() {
      return this.e?true:(this.e = this.a != null && this.a.a() && this.b != null && this.b.a());
   }

   public final boolean a(int var1, int var2) {
      if(this.a()) {
         return false;
      } else {
         b var3;
         if(var1 == 1) {
            if(this.a == null) {
               var3 = new b(this, var2);
               this.a = var3;
               return true;
            } else if(this.b == null) {
               var3 = new b(this, var2);
               this.b = var3;
               return true;
            } else {
               return false;
            }
         } else {
            b var10000;
            if(this.a == null) {
               var3 = new b(this);
               var10000 = this.a = var3;
            } else {
               var10000 = this.a;
            }

            if(var10000.a(var1 - 1, var2)) {
               return true;
            } else {
               if(this.b == null) {
                  var3 = new b(this);
                  var10000 = this.b = var3;
               } else {
                  var10000 = this.b;
               }

               return var10000.a(var1 - 1, var2);
            }
         }
      }
   }
}
