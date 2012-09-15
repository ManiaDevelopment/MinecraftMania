package com.mojang.minecraft.net;

import com.mojang.minecraft.Minecraft;
import com.mojang.minecraft.gui.ErrorScreen;
import com.mojang.minecraft.gui.PopUpScreen;
import com.mojang.minecraft.net.NetworkPlayer;
import com.mojang.minecraft.net.PacketType;
import com.mojang.minecraft.net.ServerConnectThread;
import com.mojang.net.NetworkHandler;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public final class NetworkManager {

   public ByteArrayOutputStream a;
   public NetworkHandler b;
   public Minecraft c;
   public boolean d = false;
   public boolean e = false;
   public HashMap f = new HashMap();


   public NetworkManager(Minecraft var1, String var2, int var3, String var4, String var5) {
      var1.online = true;
      this.c = var1;
      (new ServerConnectThread(this, var2, var3, var4, var5, var1)).start();
   }

   public final void a(int var1, int var2, int var3, int var4, int var5) {
      this.b.a(PacketType.f, new Object[]{Integer.valueOf(var1), Integer.valueOf(var2), Integer.valueOf(var3), Integer.valueOf(var4), Integer.valueOf(var5)});
   }

   public final void a(Exception var1) {
      this.b.a();
      this.c.a((PopUpScreen)(new ErrorScreen("Disconnected!", var1.getMessage())));
      var1.printStackTrace();
   }

   public final boolean a() {
      NetworkHandler var1;
      return this.b != null && (var1 = this.b).a;
   }

   public final List b() {
      ArrayList var1;
      (var1 = new ArrayList()).add(this.c.sessionData.b);
      Iterator var3 = this.f.values().iterator();

      while(var3.hasNext()) {
         NetworkPlayer var2 = (NetworkPlayer)var3.next();
         var1.add(var2.name);
      }

      return var1;
   }
}
