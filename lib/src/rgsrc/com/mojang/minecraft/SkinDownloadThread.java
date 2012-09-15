package com.mojang.minecraft;

import com.mojang.minecraft.Minecraft;
import com.mojang.minecraft.player.Player;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.imageio.ImageIO;

final class SkinDownloadThread extends Thread {

   // $FF: synthetic field
   private Minecraft a;


   SkinDownloadThread(Minecraft var1) {
      this.a = var1;
      super();
   }

   public final void run() {
      if(this.a.sessionData != null) {
         HttpURLConnection var1 = null;

         try {
            (var1 = (HttpURLConnection)(new URL("http://www.minecraft.net/skin/" + this.a.sessionData.b + ".png")).openConnection()).setDoInput(true);
            var1.setDoOutput(false);
            var1.connect();
            if(var1.getResponseCode() != 404) {
               Player.newTexture = ImageIO.read(var1.getInputStream());
               return;
            }
         } catch (Exception var4) {
            var4.printStackTrace();
            return;
         } finally {
            var1.disconnect();
         }

      }
   }
}
