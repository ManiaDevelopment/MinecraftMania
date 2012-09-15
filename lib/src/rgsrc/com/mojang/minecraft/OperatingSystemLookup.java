package com.mojang.minecraft;

import com.mojang.minecraft.Minecraft$OS;

// $FF: synthetic class
final class OperatingSystemLookup {

   // $FF: synthetic field
   static final int[] a = new int[Minecraft$OS.values().length];


   static {
      try {
         a[Minecraft$OS.linux.ordinal()] = 1;
      } catch (NoSuchFieldError var3) {
         ;
      }

      try {
         a[Minecraft$OS.solaris.ordinal()] = 2;
      } catch (NoSuchFieldError var2) {
         ;
      }

      try {
         a[Minecraft$OS.windows.ordinal()] = 3;
      } catch (NoSuchFieldError var1) {
         ;
      }

      try {
         a[Minecraft$OS.macos.ordinal()] = 4;
      } catch (NoSuchFieldError var0) {
         ;
      }
   }
}
