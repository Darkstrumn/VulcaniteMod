package net.insane96mcp.vulcanite.worldgen;

import java.util.Random;

import com.google.common.base.Predicate;

import net.insane96mcp.vulcanite.lib.Properties;
import net.minecraft.block.BlockStone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class OverworldGenMinable extends WorldGenerator {

    private final IBlockState oreBlock;
    /** The number of blocks to generate. */
	private int numberOfBlocks;
    private final Predicate<IBlockState> forBlock;
    
	public OverworldGenMinable(IBlockState defaultState, int numberOfBlocks, BlockMatcher forBlock) {
        this.oreBlock = defaultState;
        this.numberOfBlocks = numberOfBlocks;
        this.forBlock = forBlock;
	}

	public static ResourceLocation lava = new ResourceLocation("minecraft:lava");
	public static ResourceLocation air = new ResourceLocation("minecraft:air");

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
    	Iterable<BlockPos> blocksAround = BlockPos.getAllInBox(position.add(-3, -3, -3), position.add(3, 3, 3));
    	
    	int lavaAround = 0;
    	int airAround = 0;
    	
    	for (BlockPos blockPos : blocksAround) {
			if (worldIn.getBlockState(blockPos).getBlock().getRegistryName().equals(lava))
				lavaAround++;
			if (worldIn.getBlockState(blockPos).getBlock().getRegistryName().equals(air))
				airAround++;
			
			if (lavaAround >= Properties.config.oreGeneration.overworld.minLavaRequired && airAround >= 1)
				break;
		}
    	if (lavaAround < Properties.config.oreGeneration.overworld.minLavaRequired)
    		return false;
    	if (airAround < 1)
    		return false;
		
    	float f = rand.nextFloat() * (float)Math.PI;
        double d0 = (double)((float)(position.getX() + 8) + MathHelper.sin(f) * (float)this.numberOfBlocks / 8.0F);
        double d1 = (double)((float)(position.getX() + 8) - MathHelper.sin(f) * (float)this.numberOfBlocks / 8.0F);
        double d2 = (double)((float)(position.getZ() + 8) + MathHelper.cos(f) * (float)this.numberOfBlocks / 8.0F);
        double d3 = (double)((float)(position.getZ() + 8) - MathHelper.cos(f) * (float)this.numberOfBlocks / 8.0F);
        double d4 = (double)(position.getY() + rand.nextInt(3) - 2);
        double d5 = (double)(position.getY() + rand.nextInt(3) - 2);

        for (int i = 0; i < this.numberOfBlocks; ++i)
        {
            float f1 = (float)i / (float)this.numberOfBlocks;
            double d6 = d0 + (d1 - d0) * (double)f1;
            double d7 = d4 + (d5 - d4) * (double)f1;
            double d8 = d2 + (d3 - d2) * (double)f1;
            double d9 = rand.nextDouble() * (double)this.numberOfBlocks / 16.0D;
            double d10 = (double)(MathHelper.sin((float)Math.PI * f1) + 1.0F) * d9 + 1.0D;
            double d11 = (double)(MathHelper.sin((float)Math.PI * f1) + 1.0F) * d9 + 1.0D;
            int j = MathHelper.floor(d6 - d10 / 2.0D);
            int k = MathHelper.floor(d7 - d11 / 2.0D);
            int l = MathHelper.floor(d8 - d10 / 2.0D);
            int i1 = MathHelper.floor(d6 + d10 / 2.0D);
            int j1 = MathHelper.floor(d7 + d11 / 2.0D);
            int k1 = MathHelper.floor(d8 + d10 / 2.0D);

            for (int l1 = j; l1 <= i1; ++l1)
            {
                double d12 = ((double)l1 + 0.5D - d6) / (d10 / 2.0D);

                if (d12 * d12 < 1.0D)
                {
                    for (int i2 = k; i2 <= j1; ++i2)
                    {
                        double d13 = ((double)i2 + 0.5D - d7) / (d11 / 2.0D);

                        if (d12 * d12 + d13 * d13 < 1.0D)
                        {
                            for (int j2 = l; j2 <= k1; ++j2)
                            {
                                double d14 = ((double)j2 + 0.5D - d8) / (d10 / 2.0D);

                                if (d12 * d12 + d13 * d13 + d14 * d14 < 1.0D)
                                {
                                    BlockPos blockpos = new BlockPos(l1, i2, j2);

                                    IBlockState state = worldIn.getBlockState(blockpos);
                                    if (state.getBlock().isReplaceableOreGen(state, worldIn, blockpos, this.forBlock))
                                    {
                                        worldIn.setBlockState(blockpos, this.oreBlock, 2);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return true;
	}
	
    static class StonePredicate implements Predicate<IBlockState>
    {
        private StonePredicate()
        {
        }

        public boolean apply(IBlockState p_apply_1_)
        {
            if (p_apply_1_ != null && p_apply_1_.getBlock() == Blocks.STONE)
            {
                BlockStone.EnumType blockstone$enumtype = (BlockStone.EnumType)p_apply_1_.getValue(BlockStone.VARIANT);
                return blockstone$enumtype.isNatural();
            }
            else
            {
                return false;
            }
        }
    }

}

