package com.mojang.minecraft.sound;

import com.mojang.minecraft.sound.b;
import com.mojang.minecraft.sound.d;
import com.mojang.minecraft.sound.h;
import com.mojang.minecraft.sound.i;
import com.mojang.minecraft.sound.j;
import com.mojang.minecraft.sound.m;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public final class k {

   private d d = new d();
   public Map a = new HashMap();
   private Map e = new HashMap();
   public Random b = new Random();
   public long c = System.currentTimeMillis() + 60000L;


   public final h a(String var1, float var2, float var3) {
      List var4 = null;
      Map var5 = this.a;
      synchronized(this.a) {
         var4 = (List)this.a.get(var1);
      }

      if(var4 == null) {
         return null;
      } else {
         b var7 = (b)var4.get(this.b.nextInt(var4.size()));
         return new j(var7, var3, var2);
      }
   }

   public void a(File var1, String var2) {
      try {
         for(var2 = var2.substring(0, var2.length() - 4).replaceAll("/", "."); Character.isDigit(var2.charAt(var2.length() - 1)); var2 = var2.substring(0, var2.length() - 1)) {
            ;
         }

         b var7 = d.a(var1.toURI().toURL());
         Map var3 = this.a;
         synchronized(this.a) {
            Object var4;
            if((var4 = (List)this.a.get(var2)) == null) {
               var4 = new ArrayList();
               this.a.put(var2, var4);
            }

            ((List)var4).add(var7);
         }
      } catch (Exception var6) {
         var6.printStackTrace();
      }

   }

   public final void a(String var1, File var2) {
      Map var3 = this.e;
      synchronized(this.e) {
         for(var1 = var1.substring(0, var1.length() - 4).replaceAll("/", "."); Character.isDigit(var1.charAt(var1.length() - 1)); var1 = var1.substring(0, var1.length() - 1)) {
            ;
         }

         Object var4;
         if((var4 = (List)this.e.get(var1)) == null) {
            var4 = new ArrayList();
            this.e.put(var1, var4);
         }

         ((List)var4).add(var2);
      }
   }

   public boolean a(i var1, String var2) {
      List var3 = null;
      Map var4 = this.e;
      synchronized(this.e) {
         var3 = (List)this.e.get(var2);
      }

      if(var3 == null) {
         return false;
      } else {
         File var8 = (File)var3.get(this.b.nextInt(var3.size()));

         try {
            var1.a(new m(var1, var8.toURI().toURL()));
         } catch (MalformedURLException var5) {
            var5.printStackTrace();
         } catch (IOException var6) {
            var6.printStackTrace();
         }

         return true;
      }
   }
}
