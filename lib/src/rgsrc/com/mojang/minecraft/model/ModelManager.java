package com.mojang.minecraft.model;

import com.mojang.minecraft.model.AnimalModel;
import com.mojang.minecraft.model.CreeperModel;
import com.mojang.minecraft.model.HumanoidModel;
import com.mojang.minecraft.model.Model;
import com.mojang.minecraft.model.PigModel;
import com.mojang.minecraft.model.SheepFurModel;
import com.mojang.minecraft.model.SheepModel;
import com.mojang.minecraft.model.SkeletonModel;
import com.mojang.minecraft.model.SpiderModel;
import com.mojang.minecraft.model.ZombieModel;

public final class ModelManager {

   private HumanoidModel a = new HumanoidModel(0.0F);
   private HumanoidModel b = new HumanoidModel(1.0F);
   private CreeperModel c = new CreeperModel();
   private SkeletonModel d = new SkeletonModel();
   private ZombieModel e = new ZombieModel();
   private AnimalModel f = new PigModel();
   private AnimalModel g = new SheepModel();
   private SpiderModel h = new SpiderModel();
   private SheepFurModel i = new SheepFurModel();


   public final Model a(String var1) {
      return (Model)(var1.equals("humanoid")?this.a:(var1.equals("humanoid.armor")?this.b:(var1.equals("creeper")?this.c:(var1.equals("skeleton")?this.d:(var1.equals("zombie")?this.e:(var1.equals("pig")?this.f:(var1.equals("sheep")?this.g:(var1.equals("spider")?this.h:(var1.equals("sheep.fur")?this.i:null)))))))));
   }
}
