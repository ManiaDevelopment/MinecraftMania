package com.mojang.minecraft.sound;

import com.mojang.minecraft.GameSettings;
import com.mojang.minecraft.sound.a;
import com.mojang.minecraft.sound.f;
import com.mojang.minecraft.sound.g;
import com.mojang.minecraft.sound.h;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.sound.sampled.SourceDataLine;

public final class i implements Runnable {

   public boolean a = false;
   public SourceDataLine b;
   private List d = new ArrayList();
   public GameSettings c;


   public i(GameSettings var1) {
      this.c = var1;
   }

   public final void a(a var1) {
      if(this.a) {
         List var2 = this.d;
         synchronized(this.d) {
            this.d.add(var1);
         }
      }
   }

   public final void a(h var1, f var2) {
      this.a(new g(var1, var2));
   }

   public final void run() {
      int[] var1 = new int[4410];
      int[] var2 = new int[4410];

      for(byte[] var3 = new byte[17640]; this.a; this.b.write(var3, 0, 17640)) {
         try {
            Thread.sleep(1L);
         } catch (InterruptedException var10) {
            var10.printStackTrace();
         }

         Arrays.fill(var1, 0, 4410, 0);
         Arrays.fill(var2, 0, 4410, 0);
         boolean var4 = true;
         int[] var5 = var2;
         int[] var6 = var1;
         List var12 = this.d;
         List var7 = this.d;
         synchronized(this.d) {
            int var8 = 0;

            while(true) {
               if(var8 >= var12.size()) {
                  break;
               }

               if(!((a)var12.get(var8)).a(var6, var5, 4410)) {
                  var12.remove(var8--);
               }

               ++var8;
            }
         }

         int var13;
         if(!this.c.a && !this.c.b) {
            for(var13 = 0; var13 < 4410; ++var13) {
               var3[var13 << 2] = 0;
               var3[(var13 << 2) + 1] = 0;
               var3[(var13 << 2) + 2] = 0;
               var3[(var13 << 2) + 3] = 0;
            }
         } else {
            for(var13 = 0; var13 < 4410; ++var13) {
               int var15 = var1[var13];
               int var14 = var2[var13];
               if(var15 < -32000) {
                  var15 = -32000;
               }

               if(var14 < -32000) {
                  var14 = -32000;
               }

               if(var15 >= 32000) {
                  var15 = 32000;
               }

               if(var14 >= 32000) {
                  var14 = 32000;
               }

               var3[var13 << 2] = (byte)(var15 >> 8);
               var3[(var13 << 2) + 1] = (byte)var15;
               var3[(var13 << 2) + 2] = (byte)(var14 >> 8);
               var3[(var13 << 2) + 3] = (byte)var14;
            }
         }
      }

   }
}
