package de.jarnbjo.vorbis;

import de.jarnbjo.vorbis.VorbisFormatException;
import java.util.ArrayList;
import java.util.HashMap;

public final class d {

   private HashMap a = new HashMap();


   public d(de.jarnbjo.a.a.a var1) {
      if(var1.b(48) != 126896460427126L) {
         throw new VorbisFormatException("The identification header has an illegal leading.");
      } else {
         a(var1);
         int var2 = var1.a(32);

         for(int var3 = 0; var3 < var2; ++var3) {
            String var4;
            int var5 = (var4 = a(var1)).indexOf(61);
            String var6 = var4.substring(0, var5);
            var4 = var4.substring(var5 + 1);
            ArrayList var7;
            if((var7 = (ArrayList)this.a.get(var6)) == null) {
               var7 = new ArrayList();
               this.a.put(var6, var7);
            }

            var7.add(var4);
         }

         var1.a(8);
      }
   }

   private static String a(de.jarnbjo.a.a.a var0) {
      int var1;
      byte[] var2 = new byte[var1 = var0.a(32)];

      for(int var3 = 0; var3 < var1; ++var3) {
         var2[var3] = (byte)var0.a(8);
      }

      return new String(var2, "UTF-8");
   }
}
