package com.mojang.minecraft.level;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.util.HashSet;
import java.util.Set;

public final class LevelObjectInputStream extends ObjectInputStream {

   private Set classes = new HashSet();


   public LevelObjectInputStream(InputStream var1) {
      super(var1);
      this.classes.add("com.mojang.minecraft.player.Player$1");
      this.classes.add("com.mojang.minecraft.mob.Creeper$1");
      this.classes.add("com.mojang.minecraft.mob.Skeleton$1");
   }

   protected final ObjectStreamClass readClassDescriptor() {
      ObjectStreamClass var1 = super.readClassDescriptor();
      return this.classes.contains(var1.getName())?ObjectStreamClass.lookup(Class.forName(var1.getName())):var1;
   }
}
