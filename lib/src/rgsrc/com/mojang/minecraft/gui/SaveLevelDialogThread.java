package com.mojang.minecraft.gui;

import com.mojang.minecraft.gui.LoadLevelScreen;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

final class SaveLevelDialogThread extends Thread {

   // $FF: synthetic field
   private LoadLevelScreen a;


   SaveLevelDialogThread(LoadLevelScreen var1) {
      this.a = var1;
      super();
   }

   public final void run() {
      JFileChooser var1;
      LoadLevelScreen var2;
      try {
         LoadLevelScreen var10000 = this.a;
         var1 = new JFileChooser();
         var10000.k = var1;
         FileNameExtensionFilter var3 = new FileNameExtensionFilter("Minecraft levels", new String[]{"mine"});
         var2 = this.a;
         this.a.k.setFileFilter(var3);
         var2 = this.a;
         this.a.k.setMultiSelectionEnabled(false);
         int var7;
         if(this.a.l) {
            var2 = this.a;
            var7 = this.a.k.showSaveDialog(this.a.a.canvas);
         } else {
            var2 = this.a;
            var7 = this.a.k.showOpenDialog(this.a.a.canvas);
         }

         if(var7 == 0) {
            (var2 = this.a).m = this.a.k.getSelectedFile();
         }
      } finally {
         boolean var6 = false;
         var2 = this.a;
         this.a.j = false;
         var1 = null;
         var2 = this.a;
         this.a.k = var1;
      }

   }
}
