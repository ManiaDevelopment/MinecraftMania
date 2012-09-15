package com.mojang.minecraft.render;

import com.mojang.minecraft.GameSettings;
import com.mojang.minecraft.render.texture.TextureFX;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.imageio.ImageIO;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class TextureManager {

   public HashMap a = new HashMap();
   public HashMap b = new HashMap();
   public IntBuffer c = BufferUtils.createIntBuffer(1);
   public ByteBuffer d = BufferUtils.createByteBuffer(262144);
   public List e = new ArrayList();
   public GameSettings f;


   public TextureManager(GameSettings var1) {
      this.f = var1;
   }

   public final int a(String var1) {
      Integer var2;
      if((var2 = (Integer)this.a.get(var1)) != null) {
         return var2.intValue();
      } else {
         try {
            this.c.clear();
            GL11.glGenTextures(this.c);
            int var4 = this.c.get(0);
            if(var1.startsWith("##")) {
               this.a(a(ImageIO.read(TextureManager.class.getResourceAsStream(var1.substring(2)))), var4);
            } else {
               this.a(ImageIO.read(TextureManager.class.getResourceAsStream(var1)), var4);
            }

            this.a.put(var1, Integer.valueOf(var4));
            return var4;
         } catch (IOException var3) {
            throw new RuntimeException("!!");
         }
      }
   }

   public static BufferedImage a(BufferedImage var0) {
      int var1 = var0.getWidth() / 16;
      BufferedImage var2;
      Graphics var3 = (var2 = new BufferedImage(16, var0.getHeight() * var1, 2)).getGraphics();

      for(int var4 = 0; var4 < var1; ++var4) {
         var3.drawImage(var0, -var4 << 4, var4 * var0.getHeight(), (ImageObserver)null);
      }

      var3.dispose();
      return var2;
   }

   public final int b(BufferedImage var1) {
      this.c.clear();
      GL11.glGenTextures(this.c);
      int var2 = this.c.get(0);
      this.a(var1, var2);
      this.b.put(Integer.valueOf(var2), var1);
      return var2;
   }

   public void a(BufferedImage var1, int var2) {
      GL11.glBindTexture(3553, var2);
      GL11.glTexParameteri(3553, 10241, 9728);
      GL11.glTexParameteri(3553, 10240, 9728);
      var2 = var1.getWidth();
      int var3 = var1.getHeight();
      int[] var4 = new int[var2 * var3];
      byte[] var5 = new byte[var2 * var3 << 2];
      var1.getRGB(0, 0, var2, var3, var4, 0, var2);

      for(int var11 = 0; var11 < var4.length; ++var11) {
         int var6 = var4[var11] >>> 24;
         int var7 = var4[var11] >> 16 & 255;
         int var8 = var4[var11] >> 8 & 255;
         int var9 = var4[var11] & 255;
         if(this.f.g) {
            int var10 = (var7 * 30 + var8 * 59 + var9 * 11) / 100;
            var8 = (var7 * 30 + var8 * 70) / 100;
            var9 = (var7 * 30 + var9 * 70) / 100;
            var7 = var10;
            var8 = var8;
            var9 = var9;
         }

         var5[var11 << 2] = (byte)var7;
         var5[(var11 << 2) + 1] = (byte)var8;
         var5[(var11 << 2) + 2] = (byte)var9;
         var5[(var11 << 2) + 3] = (byte)var6;
      }

      this.d.clear();
      this.d.put(var5);
      this.d.position(0).limit(var5.length);
      GL11.glTexImage2D(3553, 0, 6408, var2, var3, 0, 6408, 5121, this.d);
   }

   public final void a(TextureFX var1) {
      this.e.add(var1);
      var1.a();
   }
}
