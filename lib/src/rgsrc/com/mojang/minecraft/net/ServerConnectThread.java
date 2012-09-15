package com.mojang.minecraft.net;

import com.mojang.minecraft.Minecraft;
import com.mojang.minecraft.gui.ErrorScreen;
import com.mojang.minecraft.gui.PopUpScreen;
import com.mojang.minecraft.net.NetworkManager;
import com.mojang.minecraft.net.PacketType;
import com.mojang.net.NetworkHandler;
import java.io.IOException;

final class ServerConnectThread extends Thread {

   // $FF: synthetic field
   private String a;
   // $FF: synthetic field
   private int b;
   // $FF: synthetic field
   private String c;
   // $FF: synthetic field
   private String d;
   // $FF: synthetic field
   private Minecraft e;
   // $FF: synthetic field
   private NetworkManager f;


   ServerConnectThread(NetworkManager var1, String var2, int var3, String var4, String var5, Minecraft var6) {
      this.f = var1;
      this.a = var2;
      this.b = var3;
      this.c = var4;
      this.d = var5;
      this.e = var6;
      super();
   }

   public final void run() {
      boolean var1;
      NetworkManager var2;
      try {
         NetworkManager var10000 = this.f;
         NetworkHandler var4 = new NetworkHandler(this.a, this.b);
         var10000.b = var4;
         var2 = this.f;
         NetworkManager var5 = this.f;
         NetworkHandler var10001 = this.f.b;
         this.f.b.e = var5;
         var2 = this.f;
         this.f.b.a(PacketType.b, new Object[]{Byte.valueOf((byte)7), this.c, this.d, Integer.valueOf(0)});
         var1 = true;
         var2 = this.f;
         this.f.d = var1;
      } catch (IOException var3) {
         this.e.online = false;
         this.e.networkManager = null;
         this.e.a((PopUpScreen)(new ErrorScreen("Failed to connect", "You failed to connect to the server. It\'s probably down!")));
         var1 = false;
         var2 = this.f;
         this.f.d = var1;
      }
   }
}
