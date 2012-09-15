package de.jarnbjo.a.a;

import de.jarnbjo.a.a.b;

public final class a {

   private byte[] a;
   private byte b;
   private int c;
   private int d;
   private int e;


   public a(byte[] var1) {
      this(var1, 0);
   }

   private a(byte[] var1, int var2) {
      this.d = 0;
      this.e = 0;
      this.c = 0;
      this.a = var1;
      this.b = var1[0];
      this.e = 0;
   }

   public final boolean a() {
      if(this.c == 0) {
         if(this.e > 7) {
            this.e = 0;
            this.b = this.a[++this.d];
         }

         return (this.b & 1 << this.e++) != 0;
      } else {
         if(this.e < 0) {
            this.e = 7;
            this.b = this.a[++this.d];
         }

         return (this.b & 1 << this.e--) != 0;
      }
   }

   public final int a(int var1) {
      if(var1 > 32) {
         throw new IllegalArgumentException("Argument \"bits\" must be <= 32");
      } else {
         int var2 = 0;
         int var3;
         if(this.c == 0) {
            for(var3 = 0; var3 < var1; ++var3) {
               if(this.a()) {
                  var2 |= 1 << var3;
               }
            }
         } else {
            if(this.e < 0) {
               this.e = 7;
               this.b = this.a[++this.d];
            }

            if(var1 <= this.e + 1) {
               var3 = this.b & 255;
               var2 = 1 + this.e - var1;
               int var4 = (1 << var1) - 1 << var2;
               var2 = (var3 & var4) >> var2;
               this.e -= var1;
            } else {
               var2 = (this.b & 255 & (1 << this.e + 1) - 1) << var1 - this.e - 1;
               var1 -= this.e + 1;

               for(this.b = this.a[++this.d]; var1 >= 8; this.b = this.a[++this.d]) {
                  var1 -= 8;
                  var2 |= (this.a[this.d] & 255) << var1;
               }

               if(var1 > 0) {
                  var3 = this.a[this.d] & 255;
                  var2 |= var3 >> 8 - var1 & (1 << var1) - 1;
                  this.e = 7 - var1;
               } else {
                  this.b = this.a[--this.d];
                  this.e = -1;
               }
            }
         }

         return var2;
      }
   }

   public final int a(b var1) {
      for(; var1.c == null; var1 = (this.b & 1 << this.e++) != 0?var1.b:var1.a) {
         if(this.e > 7) {
            this.e = 0;
            this.b = this.a[++this.d];
         }
      }

      return var1.c.intValue();
   }

   public final long b(int var1) {
      if(var1 > 64) {
         throw new IllegalArgumentException("Argument \"bits\" must be <= 64");
      } else {
         long var2 = 0L;
         int var4;
         if(this.c == 0) {
            for(var4 = 0; var4 < var1; ++var4) {
               if(this.a()) {
                  var2 |= 1L << var4;
               }
            }
         } else {
            for(var4 = var1 - 1; var4 >= 0; --var4) {
               if(this.a()) {
                  var2 |= 1L << var4;
               }
            }
         }

         return var2;
      }
   }
}
