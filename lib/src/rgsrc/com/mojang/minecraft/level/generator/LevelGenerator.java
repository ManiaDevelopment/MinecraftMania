package com.mojang.minecraft.level.generator;

import com.mojang.minecraft.ProgressBarDisplay;
import com.mojang.minecraft.level.Level;
import com.mojang.minecraft.level.generator.noise.CombinedNoiseGenerator;
import com.mojang.minecraft.level.generator.noise.NoiseGeneratorOctaves;
import com.mojang.minecraft.level.tile.Block;
import com.mojang.util.MathHelper;
import java.util.ArrayList;
import java.util.Random;

public final class LevelGenerator {

   private ProgressBarDisplay a;
   private int b;
   private int c;
   private int d;
   private Random e = new Random();
   private byte[] f;
   private int g;
   private int[] h = new int[1048576];


   public LevelGenerator(ProgressBarDisplay var1) {
      this.a = var1;
   }

   public final Level a(String var1, int var2, int var3, int var4) {
      this.a.a("Generating level");
      this.b = var2;
      this.c = var3;
      this.d = 64;
      this.g = 32;
      this.f = new byte[var2 * var3 << 6];
      this.a.b("Raising..");
      LevelGenerator var5 = this;
      CombinedNoiseGenerator var6 = new CombinedNoiseGenerator(new NoiseGeneratorOctaves(this.e, 8), new NoiseGeneratorOctaves(this.e, 8));
      CombinedNoiseGenerator var7 = new CombinedNoiseGenerator(new NoiseGeneratorOctaves(this.e, 8), new NoiseGeneratorOctaves(this.e, 8));
      NoiseGeneratorOctaves var8 = new NoiseGeneratorOctaves(this.e, 6);
      int[] var9 = new int[this.b * this.c];
      float var10 = 1.3F;

      int var11;
      int var12;
      for(var11 = 0; var11 < var5.b; ++var11) {
         var5.a(var11 * 100 / (var5.b - 1));

         for(var12 = 0; var12 < var5.c; ++var12) {
            double var13 = var6.a((double)((float)var11 * var10), (double)((float)var12 * var10)) / 6.0D + (double)-4;
            double var15 = var7.a((double)((float)var11 * var10), (double)((float)var12 * var10)) / 5.0D + 10.0D + (double)-4;
            if(var8.a((double)var11, (double)var12) / 8.0D > 0.0D) {
               var15 = var13;
            }

            double var19;
            if((var19 = Math.max(var13, var15) / 2.0D) < 0.0D) {
               var19 *= 0.8D;
            }

            var9[var11 + var12 * var5.b] = (int)var19;
         }
      }

      this.a.b("Eroding..");
      int[] var42 = var9;
      var5 = this;
      var7 = new CombinedNoiseGenerator(new NoiseGeneratorOctaves(this.e, 8), new NoiseGeneratorOctaves(this.e, 8));
      CombinedNoiseGenerator var49 = new CombinedNoiseGenerator(new NoiseGeneratorOctaves(this.e, 8), new NoiseGeneratorOctaves(this.e, 8));

      int var23;
      int var51;
      int var54;
      for(var51 = 0; var51 < var5.b; ++var51) {
         var5.a(var51 * 100 / (var5.b - 1));

         for(var54 = 0; var54 < var5.c; ++var54) {
            double var21 = var7.a((double)(var51 << 1), (double)(var54 << 1)) / 8.0D;
            var12 = var49.a((double)(var51 << 1), (double)(var54 << 1)) > 0.0D?1:0;
            if(var21 > 2.0D) {
               var23 = ((var42[var51 + var54 * var5.b] - var12) / 2 << 1) + var12;
               var42[var51 + var54 * var5.b] = var23;
            }
         }
      }

      this.a.b("Soiling..");
      var42 = var9;
      var5 = this;
      int var46 = this.b;
      int var48 = this.c;
      var51 = this.d;
      NoiseGeneratorOctaves var53 = new NoiseGeneratorOctaves(this.e, 8);

      int var25;
      int var24;
      int var27;
      int var26;
      int var28;
      for(var24 = 0; var24 < var46; ++var24) {
         var5.a(var24 * 100 / (var5.b - 1));

         for(var11 = 0; var11 < var48; ++var11) {
            var12 = (int)(var53.a((double)var24, (double)var11) / 24.0D) - 4;
            var25 = (var23 = var42[var24 + var11 * var46] + var5.g) + var12;
            var42[var24 + var11 * var46] = Math.max(var23, var25);
            if(var42[var24 + var11 * var46] > var51 - 2) {
               var42[var24 + var11 * var46] = var51 - 2;
            }

            if(var42[var24 + var11 * var46] < 1) {
               var42[var24 + var11 * var46] = 1;
            }

            for(var26 = 0; var26 < var51; ++var26) {
               var27 = (var26 * var5.c + var11) * var5.b + var24;
               var28 = 0;
               if(var26 <= var23) {
                  var28 = Block.g.ac;
               }

               if(var26 <= var25) {
                  var28 = Block.e.ac;
               }

               if(var26 == 0) {
                  var28 = Block.n.ac;
               }

               var5.f[var27] = (byte)var28;
            }
         }
      }

      this.a.b("Carving..");
      boolean var45 = true;
      boolean var44 = false;
      var5 = this;
      var48 = this.b;
      var51 = this.c;
      var54 = this.d;
      var24 = var48 * var51 * var54 / 256 / 64 << 1;

      for(var11 = 0; var11 < var24; ++var11) {
         var5.a(var11 * 100 / (var24 - 1) / 4);
         float var55 = var5.e.nextFloat() * (float)var48;
         float var59 = var5.e.nextFloat() * (float)var54;
         float var56 = var5.e.nextFloat() * (float)var51;
         var26 = (int)((var5.e.nextFloat() + var5.e.nextFloat()) * 200.0F);
         float var61 = var5.e.nextFloat() * 3.1415927F * 2.0F;
         float var64 = 0.0F;
         float var29 = var5.e.nextFloat() * 3.1415927F * 2.0F;
         float var30 = 0.0F;
         float var31 = var5.e.nextFloat() * var5.e.nextFloat();

         for(int var32 = 0; var32 < var26; ++var32) {
            var55 += MathHelper.a(var61) * MathHelper.b(var29);
            var56 += MathHelper.b(var61) * MathHelper.b(var29);
            var59 += MathHelper.a(var29);
            var61 += var64 * 0.2F;
            var64 = (var64 *= 0.9F) + (var5.e.nextFloat() - var5.e.nextFloat());
            var29 = (var29 + var30 * 0.5F) * 0.5F;
            var30 = (var30 *= 0.75F) + (var5.e.nextFloat() - var5.e.nextFloat());
            if(var5.e.nextFloat() >= 0.25F) {
               float var43 = var55 + (var5.e.nextFloat() * 4.0F - 2.0F) * 0.2F;
               float var50 = var59 + (var5.e.nextFloat() * 4.0F - 2.0F) * 0.2F;
               float var33 = var56 + (var5.e.nextFloat() * 4.0F - 2.0F) * 0.2F;
               float var34 = ((float)var5.d - var50) / (float)var5.d;
               var34 = 1.2F + (var34 * 3.5F + 1.0F) * var31;
               var34 = MathHelper.a((float)var32 * 3.1415927F / (float)var26) * var34;

               for(int var35 = (int)(var43 - var34); var35 <= (int)(var43 + var34); ++var35) {
                  for(int var36 = (int)(var50 - var34); var36 <= (int)(var50 + var34); ++var36) {
                     for(int var37 = (int)(var33 - var34); var37 <= (int)(var33 + var34); ++var37) {
                        float var38 = (float)var35 - var43;
                        float var39 = (float)var36 - var50;
                        float var40 = (float)var37 - var33;
                        if(var38 * var38 + var39 * var39 * 2.0F + var40 * var40 < var34 * var34 && var35 >= 1 && var36 >= 1 && var37 >= 1 && var35 < var5.b - 1 && var36 < var5.d - 1 && var37 < var5.c - 1) {
                           int var66 = (var36 * var5.c + var37) * var5.b + var35;
                           if(var5.f[var66] == Block.e.ac) {
                              var5.f[var66] = 0;
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      this.a(Block.t.ac, 90, 1, 4);
      this.a(Block.s.ac, 70, 2, 4);
      this.a(Block.r.ac, 50, 3, 4);
      this.a.b("Watering..");
      var5 = this;
      var51 = Block.m.ac;
      this.a(0);

      for(var54 = 0; var54 < var5.b; ++var54) {
         var5.a(var54, var5.d / 2 - 1, 0, 0, var51);
         var5.a(var54, var5.d / 2 - 1, var5.c - 1, 0, var51);
      }

      for(var54 = 0; var54 < var5.c; ++var54) {
         var5.a(0, var5.d / 2 - 1, var54, 0, var51);
         var5.a(var5.b - 1, var5.d / 2 - 1, var54, 0, var51);
      }

      var54 = var5.b * var5.c / 8000;

      for(var24 = 0; var24 < var54; ++var24) {
         if(var24 % 100 == 0) {
            var5.a(var24 * 100 / (var54 - 1));
         }

         var11 = var5.e.nextInt(var5.b);
         var12 = var5.g - 1 - var5.e.nextInt(2);
         var23 = var5.e.nextInt(var5.c);
         if(var5.f[(var12 * var5.c + var23) * var5.b + var11] == 0) {
            var5.a(var11, var12, var23, 0, var51);
         }
      }

      var5.a(100);
      this.a.b("Melting..");
      var5 = this;
      var46 = this.b * this.c * this.d / 20000;

      for(var48 = 0; var48 < var46; ++var48) {
         if(var48 % 100 == 0) {
            var5.a(var48 * 100 / (var46 - 1));
         }

         var51 = var5.e.nextInt(var5.b);
         var54 = (int)(var5.e.nextFloat() * var5.e.nextFloat() * (float)(var5.g - 3));
         var24 = var5.e.nextInt(var5.c);
         if(var5.f[(var54 * var5.c + var24) * var5.b + var51] == 0) {
            var5.a(var51, var54, var24, 0, Block.o.ac);
         }
      }

      var5.a(100);
      this.a.b("Growing..");
      var42 = var9;
      var5 = this;
      var46 = this.b;
      var48 = this.c;
      var51 = this.d;
      var53 = new NoiseGeneratorOctaves(this.e, 8);
      NoiseGeneratorOctaves var58 = new NoiseGeneratorOctaves(this.e, 8);

      int var63;
      for(var11 = 0; var11 < var46; ++var11) {
         var5.a(var11 * 100 / (var5.b - 1));

         for(var12 = 0; var12 < var48; ++var12) {
            boolean var60 = var53.a((double)var11, (double)var12) > 8.0D;
            boolean var57 = var58.a((double)var11, (double)var12) > 12.0D;
            var27 = ((var26 = var42[var11 + var12 * var46]) * var5.c + var12) * var5.b + var11;
            if(((var28 = var5.f[((var26 + 1) * var5.c + var12) * var5.b + var11] & 255) == Block.l.ac || var28 == Block.m.ac) && var26 <= var51 / 2 - 1 && var57) {
               var5.f[var27] = (byte)Block.q.ac;
            }

            if(var28 == 0) {
               var63 = Block.f.ac;
               if(var26 <= var51 / 2 - 1 && var60) {
                  var63 = Block.p.ac;
               }

               var5.f[var27] = (byte)var63;
            }
         }
      }

      this.a.b("Planting..");
      var42 = var9;
      var5 = this;
      var46 = this.b;
      var48 = this.b * this.c / 3000;

      for(var51 = 0; var51 < var48; ++var51) {
         var54 = var5.e.nextInt(2);
         var5.a(var51 * 50 / (var48 - 1));
         var24 = var5.e.nextInt(var5.b);
         var11 = var5.e.nextInt(var5.c);

         for(var12 = 0; var12 < 10; ++var12) {
            var23 = var24;
            var25 = var11;

            for(var26 = 0; var26 < 5; ++var26) {
               var23 += var5.e.nextInt(6) - var5.e.nextInt(6);
               var25 += var5.e.nextInt(6) - var5.e.nextInt(6);
               if((var54 < 2 || var5.e.nextInt(4) == 0) && var23 >= 0 && var25 >= 0 && var23 < var5.b && var25 < var5.c) {
                  var27 = var42[var23 + var25 * var46] + 1;
                  if((var5.f[(var27 * var5.c + var25) * var5.b + var23] & 255) == 0) {
                     var63 = (var27 * var5.c + var25) * var5.b + var23;
                     if((var5.f[((var27 - 1) * var5.c + var25) * var5.b + var23] & 255) == Block.f.ac) {
                        if(var54 == 0) {
                           var5.f[var63] = (byte)Block.O.ac;
                        } else if(var54 == 1) {
                           var5.f[var63] = (byte)Block.P.ac;
                        }
                     }
                  }
               }
            }
         }
      }

      var42 = var9;
      var5 = this;
      var46 = this.b;
      var51 = this.b * this.c * this.d / 2000;

      for(var54 = 0; var54 < var51; ++var54) {
         var24 = var5.e.nextInt(2);
         var5.a(var54 * 50 / (var51 - 1) + 50);
         var11 = var5.e.nextInt(var5.b);
         var12 = var5.e.nextInt(var5.d);
         var23 = var5.e.nextInt(var5.c);

         for(var25 = 0; var25 < 20; ++var25) {
            var26 = var11;
            var27 = var12;
            var28 = var23;

            for(var63 = 0; var63 < 5; ++var63) {
               var26 += var5.e.nextInt(6) - var5.e.nextInt(6);
               var27 += var5.e.nextInt(2) - var5.e.nextInt(2);
               var28 += var5.e.nextInt(6) - var5.e.nextInt(6);
               if((var24 < 2 || var5.e.nextInt(4) == 0) && var26 >= 0 && var28 >= 0 && var27 >= 1 && var26 < var5.b && var28 < var5.c && var27 < var42[var26 + var28 * var46] - 1 && (var5.f[(var27 * var5.c + var28) * var5.b + var26] & 255) == 0) {
                  int var62 = (var27 * var5.c + var28) * var5.b + var26;
                  if((var5.f[((var27 - 1) * var5.c + var28) * var5.b + var26] & 255) == Block.e.ac) {
                     if(var24 == 0) {
                        var5.f[var62] = (byte)Block.Q.ac;
                     } else if(var24 == 1) {
                        var5.f[var62] = (byte)Block.R.ac;
                     }
                  }
               }
            }
         }
      }

      Level var65;
      (var65 = new Level()).waterLevel = this.g;
      var65.setData(var2, 64, var3, this.f);
      var65.createTime = System.currentTimeMillis();
      var65.creator = var1;
      var65.name = "A Nice World";
      int[] var52 = var9;
      Level var47 = var65;
      var5 = this;
      var48 = this.b;
      var51 = this.b * this.c / 4000;

      for(var54 = 0; var54 < var51; ++var54) {
         var5.a(var54 * 50 / (var51 - 1) + 50);
         var24 = var5.e.nextInt(var5.b);
         var11 = var5.e.nextInt(var5.c);

         for(var12 = 0; var12 < 20; ++var12) {
            var23 = var24;
            var25 = var11;

            for(var26 = 0; var26 < 20; ++var26) {
               var23 += var5.e.nextInt(6) - var5.e.nextInt(6);
               var25 += var5.e.nextInt(6) - var5.e.nextInt(6);
               if(var23 >= 0 && var25 >= 0 && var23 < var5.b && var25 < var5.c) {
                  var27 = var52[var23 + var25 * var48] + 1;
                  if(var5.e.nextInt(4) == 0) {
                     var47.maybeGrowTree(var23, var27, var25);
                  }
               }
            }
         }
      }

      return var65;
   }

   private void a(int var1, int var2, int var3, int var4) {
      byte var25 = (byte)var1;
      var4 = this.b;
      int var5 = this.c;
      int var6 = this.d;
      int var7 = var4 * var5 * var6 / 256 / 64 * var2 / 100;

      for(int var8 = 0; var8 < var7; ++var8) {
         this.a(var8 * 100 / (var7 - 1) / 4 + var3 * 100 / 4);
         float var9 = this.e.nextFloat() * (float)var4;
         float var10 = this.e.nextFloat() * (float)var6;
         float var11 = this.e.nextFloat() * (float)var5;
         int var12 = (int)((this.e.nextFloat() + this.e.nextFloat()) * 75.0F * (float)var2 / 100.0F);
         float var13 = this.e.nextFloat() * 3.1415927F * 2.0F;
         float var14 = 0.0F;
         float var15 = this.e.nextFloat() * 3.1415927F * 2.0F;
         float var16 = 0.0F;

         for(int var17 = 0; var17 < var12; ++var17) {
            var9 += MathHelper.a(var13) * MathHelper.b(var15);
            var11 += MathHelper.b(var13) * MathHelper.b(var15);
            var10 += MathHelper.a(var15);
            var13 += var14 * 0.2F;
            var14 = (var14 *= 0.9F) + (this.e.nextFloat() - this.e.nextFloat());
            var15 = (var15 + var16 * 0.5F) * 0.5F;
            var16 = (var16 *= 0.9F) + (this.e.nextFloat() - this.e.nextFloat());
            float var18 = MathHelper.a((float)var17 * 3.1415927F / (float)var12) * (float)var2 / 100.0F + 1.0F;

            for(int var19 = (int)(var9 - var18); var19 <= (int)(var9 + var18); ++var19) {
               for(int var20 = (int)(var10 - var18); var20 <= (int)(var10 + var18); ++var20) {
                  for(int var21 = (int)(var11 - var18); var21 <= (int)(var11 + var18); ++var21) {
                     float var22 = (float)var19 - var9;
                     float var23 = (float)var20 - var10;
                     float var24 = (float)var21 - var11;
                     if(var22 * var22 + var23 * var23 * 2.0F + var24 * var24 < var18 * var18 && var19 >= 1 && var20 >= 1 && var21 >= 1 && var19 < this.b - 1 && var20 < this.d - 1 && var21 < this.c - 1) {
                        int var26 = (var20 * this.c + var21) * this.b + var19;
                        if(this.f[var26] == Block.e.ac) {
                           this.f[var26] = var25;
                        }
                     }
                  }
               }
            }
         }
      }

   }

   private void a(int var1) {
      this.a.a(var1);
   }

   private long a(int var1, int var2, int var3, int var4, int var5) {
      byte var20 = (byte)var5;
      ArrayList var21 = new ArrayList();
      byte var6 = 0;
      int var7 = 1;

      int var8;
      for(var8 = 1; 1 << var7 < this.b; ++var7) {
         ;
      }

      while(1 << var8 < this.c) {
         ++var8;
      }

      int var9 = this.c - 1;
      int var10 = this.b - 1;
      int var22 = var6 + 1;
      this.h[0] = ((var2 << var8) + var3 << var7) + var1;
      long var11 = 0L;
      var1 = this.b * this.c;

      while(var22 > 0) {
         --var22;
         var2 = this.h[var22];
         if(var22 == 0 && var21.size() > 0) {
            this.h = (int[])var21.remove(var21.size() - 1);
            var22 = this.h.length;
         }

         var3 = var2 >> var7 & var9;
         int var13 = var2 >> var7 + var8;

         int var14;
         int var15;
         for(var15 = var14 = var2 & var10; var14 > 0 && this.f[var2 - 1] == 0; --var2) {
            --var14;
         }

         while(var15 < this.b && this.f[var2 + var15 - var14] == 0) {
            ++var15;
         }

         int var16 = var2 >> var7 & var9;
         int var17 = var2 >> var7 + var8;
         if(var16 != var3 || var17 != var13) {
            System.out.println("Diagonal flood!?");
         }

         boolean var23 = false;
         boolean var24 = false;
         boolean var18 = false;
         var11 += (long)(var15 - var14);

         for(var14 = var14; var14 < var15; ++var14) {
            this.f[var2] = var20;
            boolean var19;
            if(var3 > 0) {
               if((var19 = this.f[var2 - this.b] == 0) && !var23) {
                  if(var22 == this.h.length) {
                     var21.add(this.h);
                     this.h = new int[1048576];
                     var22 = 0;
                  }

                  this.h[var22++] = var2 - this.b;
               }

               var23 = var19;
            }

            if(var3 < this.c - 1) {
               if((var19 = this.f[var2 + this.b] == 0) && !var24) {
                  if(var22 == this.h.length) {
                     var21.add(this.h);
                     this.h = new int[1048576];
                     var22 = 0;
                  }

                  this.h[var22++] = var2 + this.b;
               }

               var24 = var19;
            }

            if(var13 > 0) {
               byte var25 = this.f[var2 - var1];
               if((var20 == Block.n.ac || var20 == Block.o.ac) && (var25 == Block.l.ac || var25 == Block.m.ac)) {
                  this.f[var2 - var1] = (byte)Block.e.ac;
               }

               if((var19 = var25 == 0) && !var18) {
                  if(var22 == this.h.length) {
                     var21.add(this.h);
                     this.h = new int[1048576];
                     var22 = 0;
                  }

                  this.h[var22++] = var2 - var1;
               }

               var18 = var19;
            }

            ++var2;
         }
      }

      return var11;
   }
}
