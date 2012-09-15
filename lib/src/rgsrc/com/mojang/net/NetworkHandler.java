package com.mojang.net;

import com.mojang.minecraft.net.NetworkManager;
import com.mojang.minecraft.net.PacketType;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public final class NetworkHandler {

   public volatile boolean a;
   public SocketChannel b = SocketChannel.open();
   public ByteBuffer c = ByteBuffer.allocate(1048576);
   public ByteBuffer d = ByteBuffer.allocate(1048576);
   public NetworkManager e;
   private Socket f;
   private boolean g = false;
   private byte[] h = new byte[64];


   public NetworkHandler(String var1, int var2) {
      this.b.connect(new InetSocketAddress(var1, var2));
      this.b.configureBlocking(false);
      System.currentTimeMillis();
      this.f = this.b.socket();
      this.a = true;
      this.c.clear();
      this.d.clear();
      this.f.setTcpNoDelay(true);
      this.f.setTrafficClass(24);
      this.f.setKeepAlive(false);
      this.f.setReuseAddress(false);
      this.f.setSoTimeout(100);
      this.f.getInetAddress().toString();
   }

   public final void a() {
      try {
         if(this.d.position() > 0) {
            this.d.flip();
            this.b.write(this.d);
            this.d.compact();
         }
      } catch (Exception var2) {
         ;
      }

      this.a = false;

      try {
         this.b.close();
      } catch (Exception var1) {
         ;
      }

      this.f = null;
      this.b = null;
   }

   public final void a(PacketType var1, Object ... var2) {
      if(this.a) {
         this.d.put(var1.r);

         for(int var3 = 0; var3 < var2.length; ++var3) {
            Class var10001 = var1.s[var3];
            Object var4 = var2[var3];
            Class var5 = var10001;
            NetworkHandler var6 = this;
            if(this.a) {
               try {
                  if(var5 == Long.TYPE) {
                     var6.d.putLong(((Long)var4).longValue());
                  } else if(var5 == Integer.TYPE) {
                     var6.d.putInt(((Number)var4).intValue());
                  } else if(var5 == Short.TYPE) {
                     var6.d.putShort(((Number)var4).shortValue());
                  } else if(var5 == Byte.TYPE) {
                     var6.d.put(((Number)var4).byteValue());
                  } else if(var5 == Double.TYPE) {
                     var6.d.putDouble(((Double)var4).doubleValue());
                  } else if(var5 == Float.TYPE) {
                     var6.d.putFloat(((Float)var4).floatValue());
                  } else {
                     byte[] var9;
                     if(var5 != String.class) {
                        if(var5 == byte[].class) {
                           if((var9 = (byte[])((byte[])var4)).length < 1024) {
                              var9 = Arrays.copyOf(var9, 1024);
                           }

                           var6.d.put(var9);
                        }
                     } else {
                        var9 = ((String)var4).getBytes("UTF-8");
                        Arrays.fill(var6.h, (byte)32);

                        int var8;
                        for(var8 = 0; var8 < 64 && var8 < var9.length; ++var8) {
                           var6.h[var8] = var9[var8];
                        }

                        for(var8 = var9.length; var8 < 64; ++var8) {
                           var6.h[var8] = 32;
                        }

                        var6.d.put(var6.h);
                     }
                  }
               } catch (Exception var7) {
                  this.e.a(var7);
               }
            }
         }

      }
   }

   public Object a(Class var1) {
      if(!this.a) {
         return null;
      } else {
         try {
            if(var1 == Long.TYPE) {
               return Long.valueOf(this.c.getLong());
            } else if(var1 == Integer.TYPE) {
               return Integer.valueOf(this.c.getInt());
            } else if(var1 == Short.TYPE) {
               return Short.valueOf(this.c.getShort());
            } else if(var1 == Byte.TYPE) {
               return Byte.valueOf(this.c.get());
            } else if(var1 == Double.TYPE) {
               return Double.valueOf(this.c.getDouble());
            } else if(var1 == Float.TYPE) {
               return Float.valueOf(this.c.getFloat());
            } else if(var1 == String.class) {
               this.c.get(this.h);
               return (new String(this.h, "UTF-8")).trim();
            } else if(var1 == byte[].class) {
               byte[] var3 = new byte[1024];
               this.c.get(var3);
               return var3;
            } else {
               return null;
            }
         } catch (Exception var2) {
            this.e.a(var2);
            return null;
         }
      }
   }
}
