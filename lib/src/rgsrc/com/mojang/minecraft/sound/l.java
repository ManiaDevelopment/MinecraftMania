package com.mojang.minecraft.sound;

import com.mojang.minecraft.sound.m;
import de.jarnbjo.ogg.EndOfOggStreamException;
import java.nio.ByteBuffer;

final class l extends Thread {

   // $FF: synthetic field
   private m a;


   public l(m var1) {
      this.a = var1;
      super();
      this.setPriority(10);
      this.setDaemon(true);
   }

   public final void run() {
      while(true) {
         try {
            if(this.a.g) {
               return;
            }

            m var1 = this.a;
            ByteBuffer var2;
            m var10001;
            if(this.a.a == null) {
               var1 = this.a;
               if(this.a.b != null) {
                  var1 = this.a;
                  var2 = this.a.b;
                  var10001 = this.a;
                  this.a.a = var2;
                  var2 = null;
                  var1 = this.a;
                  this.a.b = null;
                  var1 = this.a;
                  this.a.a.clear();
               }
            }

            var1 = this.a;
            if(this.a.a != null) {
               var1 = this.a;
               if(this.a.a.remaining() != 0) {
                  while(true) {
                     var1 = this.a;
                     if(this.a.a.remaining() == 0) {
                        break;
                     }

                     var1 = this.a;
                     var1 = this.a;
                     var2 = this.a.a;
                     de.jarnbjo.vorbis.b var9 = this.a.d;
                     int var10 = this.a.d.a(var2.array(), var2.position(), var2.remaining());
                     var2.position(var2.position() + var10);
                     boolean var11;
                     if(var11 = var10 <= 0) {
                        this.a.f = true;
                        this.a.g = true;
                        break;
                     }
                  }
               }
            }

            var1 = this.a;
            if(this.a.a != null) {
               var1 = this.a;
               if(this.a.c == null) {
                  var1 = this.a;
                  this.a.a.flip();
                  var1 = this.a;
                  var2 = this.a.a;
                  var10001 = this.a;
                  this.a.c = var2;
                  var2 = null;
                  var1 = this.a;
                  this.a.a = var2;
               }
            }

            Thread.sleep(10L);
            var1 = this.a;
            if(this.a.e.a) {
               continue;
            }
         } catch (EndOfOggStreamException var6) {
            return;
         } catch (Exception var7) {
            var7.printStackTrace();
            return;
         } finally {
            this.a.f = true;
         }

         return;
      }
   }
}
