package com.mojang.minecraft;

import com.mojang.minecraft.Minecraft;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;

public final class ResourceDownloadThread extends Thread {

   private File b;
   private Minecraft c;
   boolean a = false;


   public ResourceDownloadThread(File var1, Minecraft var2) {
      this.c = var2;
      this.setName("Resource download thread");
      this.setDaemon(true);
      this.b = new File(var1, "resources/");
      if(!this.b.exists() && !this.b.mkdirs()) {
         throw new RuntimeException("The working directory could not be created: " + this.b);
      }
   }

   public final void run() {
      // $FF: Couldn't be decompiled
   }

   private void a(URL var1, File var2) {
      byte[] var3 = new byte[4096];
      DataInputStream var5 = new DataInputStream(var1.openStream());
      DataOutputStream var6 = new DataOutputStream(new FileOutputStream(var2));
      boolean var4 = false;

      do {
         int var7;
         if((var7 = var5.read(var3)) < 0) {
            var5.close();
            var6.close();
            return;
         }

         var6.write(var3, 0, var7);
      } while(!this.a);

   }
}
