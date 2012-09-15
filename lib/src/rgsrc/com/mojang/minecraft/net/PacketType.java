package com.mojang.minecraft.net;


public final class PacketType {

   public static final PacketType[] a = new PacketType[256];
   public static final PacketType b = new PacketType(new Class[]{Byte.TYPE, String.class, String.class, Byte.TYPE});
   public static final PacketType c;
   public static final PacketType d;
   public static final PacketType e;
   public static final PacketType f;
   public static final PacketType g;
   public static final PacketType h;
   public static final PacketType i;
   public static final PacketType j;
   public static final PacketType k;
   public static final PacketType l;
   public static final PacketType m;
   public static final PacketType n;
   public static final PacketType o;
   public static final PacketType p;
   public final int q;
   private static int t;
   public final byte r;
   public Class[] s;


   private PacketType(Class ... var1) {
      this.r = (byte)(t++);
      a[this.r] = this;
      this.s = new Class[var1.length];
      int var2 = 0;

      for(int var3 = 0; var3 < var1.length; ++var3) {
         Class var4 = var1[var3];
         this.s[var3] = var4;
         if(var4 == Long.TYPE) {
            var2 += 8;
         } else if(var4 == Integer.TYPE) {
            var2 += 4;
         } else if(var4 == Short.TYPE) {
            var2 += 2;
         } else if(var4 == Byte.TYPE) {
            ++var2;
         } else if(var4 == Float.TYPE) {
            var2 += 4;
         } else if(var4 == Double.TYPE) {
            var2 += 8;
         } else if(var4 == byte[].class) {
            var2 += 1024;
         } else if(var4 == String.class) {
            var2 += 64;
         }
      }

      this.q = var2;
   }

   static {
      new PacketType(new Class[0]);
      c = new PacketType(new Class[0]);
      d = new PacketType(new Class[]{Short.TYPE, byte[].class, Byte.TYPE});
      e = new PacketType(new Class[]{Short.TYPE, Short.TYPE, Short.TYPE});
      f = new PacketType(new Class[]{Short.TYPE, Short.TYPE, Short.TYPE, Byte.TYPE, Byte.TYPE});
      g = new PacketType(new Class[]{Short.TYPE, Short.TYPE, Short.TYPE, Byte.TYPE});
      h = new PacketType(new Class[]{Byte.TYPE, String.class, Short.TYPE, Short.TYPE, Short.TYPE, Byte.TYPE, Byte.TYPE});
      i = new PacketType(new Class[]{Byte.TYPE, Short.TYPE, Short.TYPE, Short.TYPE, Byte.TYPE, Byte.TYPE});
      j = new PacketType(new Class[]{Byte.TYPE, Byte.TYPE, Byte.TYPE, Byte.TYPE, Byte.TYPE, Byte.TYPE});
      k = new PacketType(new Class[]{Byte.TYPE, Byte.TYPE, Byte.TYPE, Byte.TYPE});
      l = new PacketType(new Class[]{Byte.TYPE, Byte.TYPE, Byte.TYPE});
      m = new PacketType(new Class[]{Byte.TYPE});
      n = new PacketType(new Class[]{Byte.TYPE, String.class});
      o = new PacketType(new Class[]{String.class});
      p = new PacketType(new Class[]{Byte.TYPE});
      t = 0;
   }
}
