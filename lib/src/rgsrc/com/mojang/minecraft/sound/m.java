package com.mojang.minecraft.sound;

import com.mojang.minecraft.sound.a;
import com.mojang.minecraft.sound.i;
import com.mojang.minecraft.sound.l;
import java.net.URL;
import java.nio.ByteBuffer;

public final class m implements a {

   ByteBuffer a = ByteBuffer.allocate(176400);
   ByteBuffer b = ByteBuffer.allocate(176400);
   private ByteBuffer h = null;
   ByteBuffer c = null;
   de.jarnbjo.vorbis.b d;
   i e;
   boolean f = false;
   boolean g = false;


   public m(i var1, URL var2) {
      this.e = var1;
      de.jarnbjo.ogg.a var3 = (de.jarnbjo.ogg.a)(new de.jarnbjo.ogg.b(var2)).a.values().iterator().next();
      this.d = new de.jarnbjo.vorbis.b(var3);
      (new l(this)).start();
   }

   public final boolean a(int[] var1, int[] var2, int var3) {
      if(!this.e.c.a) {
         this.g = true;
         return false;
      } else {
         var3 = var3;
         int var4 = 0;

         while(var3 > 0 && (this.h != null || this.c != null)) {
            if(this.h == null && this.c != null) {
               this.h = this.c;
               this.c = null;
            }

            if(this.h != null && this.h.remaining() > 0) {
               int var5;
               if((var5 = this.h.remaining() / 4) > var3) {
                  var5 = var3;
               }

               for(int var6 = 0; var6 < var5; ++var6) {
                  var1[var4 + var6] += this.h.getShort();
                  var2[var4 + var6] += this.h.getShort();
               }

               var4 += var5;
               var3 -= var5;
            }

            if(this.b == null && this.h != null && this.h.remaining() == 0) {
               this.b = this.h;
               this.h = null;
            }
         }

         return this.h != null || this.c != null || !this.f;
      }
   }
}
