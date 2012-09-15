package com.mojang.minecraft.level.liquid;


public final class LiquidType {

   private static LiquidType[] d = new LiquidType[4];
   public static final LiquidType a = new LiquidType(0);
   public static final LiquidType b = new LiquidType(1);
   public static final LiquidType c = new LiquidType(2);


   private LiquidType(int var1) {
      d[var1] = this;
   }

}
