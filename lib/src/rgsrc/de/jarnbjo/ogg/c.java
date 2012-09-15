package de.jarnbjo.ogg;

import de.jarnbjo.ogg.EndOfOggStreamException;
import java.io.EOFException;
import java.io.InputStream;
import java.io.RandomAccessFile;

public final class c {

   boolean a;
   boolean b;
   int c;
   int[] d;
   int[] e;
   private int h;
   byte[] f;
   byte[] g;


   protected c() {}

   private c(boolean var1, boolean var2, boolean var3, long var4, int var6, int var7, int var8, int[] var9, int[] var10, int var11, byte[] var12, byte[] var13, byte[] var14) {
      this.a = var1;
      this.b = var3;
      this.c = var6;
      this.d = var9;
      this.e = var10;
      this.h = var11;
      this.f = var13;
      this.g = var14;
   }

   public static c a(InputStream var0) {
      InputStream var10000 = var0;
      boolean var1 = false;
      return a(var10000, false);
   }

   private static c a(Object var0, boolean var1) {
      try {
         boolean var2 = false;
         byte[] var19 = new byte[27];
         if(var0 instanceof RandomAccessFile) {
            RandomAccessFile var3;
            if((var3 = (RandomAccessFile)var0).getFilePointer() == var3.length()) {
               return null;
            }

            var3.readFully(var19);
         } else if(var0 instanceof InputStream) {
            a((InputStream)var0, var19);
         }

         int var4;
         de.jarnbjo.a.a.a var20;
         if((var4 = (var20 = new de.jarnbjo.a.a.a(var19)).a(32)) != 1399285583) {
            String var22;
            for(var22 = Integer.toHexString(var4); var22.length() < 8; var22 = "0" + var22) {
               ;
            }

            char var5 = (char)Integer.valueOf((var22 = var22.substring(6, 8) + var22.substring(4, 6) + var22.substring(2, 4) + var22.substring(0, 2)).substring(0, 2), 16).intValue();
            char var6 = (char)Integer.valueOf(var22.substring(2, 4), 16).intValue();
            char var7 = (char)Integer.valueOf(var22.substring(4, 6), 16).intValue();
            char var8 = (char)Integer.valueOf(var22.substring(6, 8), 16).intValue();
            System.out.println("Ogg packet header is 0x" + var22 + " (" + var5 + var6 + var7 + var8 + "), should be 0x4f676753 (OggS)");
         }

         var20.a(8);
         byte var23;
         boolean var25 = ((var23 = (byte)var20.a(8)) & 1) != 0;
         boolean var26 = (var23 & 2) != 0;
         boolean var27 = (var23 & 4) != 0;
         long var9 = var20.b(64);
         var4 = var20.a(32);
         int var24 = var20.a(32);
         int var11 = var20.a(32);
         int var21;
         int[] var12 = new int[var21 = var20.a(8)];
         int[] var13 = new int[var21];
         int var14 = 0;
         byte[] var15 = new byte[var21];

         for(int var16 = 0; var16 < var21; ++var16) {
            int var17 = 0;
            if(var0 instanceof RandomAccessFile) {
               var17 = ((RandomAccessFile)var0).readByte() & 255;
            } else if(var0 instanceof InputStream) {
               var17 = ((InputStream)var0).read();
            }

            var15[var16] = (byte)var17;
            var13[var16] = var17;
            var12[var16] = var14;
            var14 += var17;
         }

         byte[] var28 = null;
         if(!var1) {
            var28 = new byte[var14];
            if(var0 instanceof RandomAccessFile) {
               ((RandomAccessFile)var0).readFully(var28);
            } else if(var0 instanceof InputStream) {
               a((InputStream)var0, var28);
            }
         }

         return new c(var25, var26, var27, var9, var4, var24, var11, var12, var13, var14, var19, var15, var28);
      } catch (EOFException var18) {
         throw new EndOfOggStreamException();
      }
   }

   private static void a(InputStream var0, byte[] var1) {
      int var3;
      for(int var2 = 0; var2 < var1.length; var2 += var3) {
         if((var3 = var0.read(var1, var2, var1.length - var2)) == -1) {
            throw new EndOfOggStreamException();
         }
      }

   }

   public final int a() {
      return this.g != null?27 + this.f.length + this.g.length:this.h;
   }
}
