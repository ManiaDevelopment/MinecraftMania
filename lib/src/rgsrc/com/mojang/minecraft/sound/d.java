package com.mojang.minecraft.sound;

import com.mojang.minecraft.sound.b;
import de.jarnbjo.ogg.EndOfOggStreamException;
import de.jarnbjo.vorbis.r;
import java.net.URL;

public final class d {

   public static b a(URL var0) {
      de.jarnbjo.ogg.a var11 = (de.jarnbjo.ogg.a)(new de.jarnbjo.ogg.b(var0)).a.values().iterator().next();
      de.jarnbjo.vorbis.b var12 = new de.jarnbjo.vorbis.b(var11);
      byte[] var2 = new byte[4096];
      int var3 = 0;
      boolean var1 = false;
      r var14 = var12.a;
      int var4 = var12.a.a;
      short[] var5 = new short[4096];
      int var6 = 0;

      label51:
      while(var3 >= 0) {
         int var15 = 0;

         while(true) {
            try {
               if(var15 < var2.length && (var3 = var12.a(var2, var15, var2.length - var15)) > 0) {
                  var15 += var3;
                  continue;
               }
            } catch (EndOfOggStreamException var10) {
               var3 = -1;
            }

            if(var15 <= 0) {
               break;
            }

            boolean var7 = false;
            int var16 = 0;

            while(true) {
               if(var16 >= var15) {
                  continue label51;
               }

               int var8 = 0;

               for(int var9 = 0; var9 < var4; ++var9) {
                  var8 += var2[var16++] << 8 | var2[var16++] & 255;
               }

               if(var6 == var5.length) {
                  short[] var18 = var5;
                  var5 = new short[var5.length << 1];
                  System.arraycopy(var18, 0, var5, 0, var6);
               }

               var5[var6++] = (short)(var8 / var4);
            }
         }
      }

      if(var6 != var5.length) {
         short[] var17 = var5;
         var5 = new short[var6];
         System.arraycopy(var17, 0, var5, 0, var6);
      }

      r var13;
      return new b(var5, (float)(var13 = var12.a).b);
   }
}
