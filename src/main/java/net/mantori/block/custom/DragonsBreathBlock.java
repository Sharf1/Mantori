package net.mantori.block.custom;

import net.mantori.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.item.ItemConvertible;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

import java.util.Random;


public class DragonsBreathBlock extends CropBlock {
    public static final IntProperty AGE;
    private static final VoxelShape[] AGE_TO_SHAPE;

    public DragonsBreathBlock(Settings settings) {
        super(settings);
    }



    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return (world.getBaseLightLevel(pos, 0) >= 0) && super.canPlaceAt(state, world, pos);
    }
    public IntProperty getAgeProperty() {
        return AGE;
    }

    public int getMaxAge() {
        return 3;
    }

    protected ItemConvertible getSeedsItem() {
        return ModItems.DRAGONS_BREATH_SEED;
    }

    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return super.canPlantOnTop(floor, world, pos) || floor.isOf(Blocks.END_STONE);
    }



    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextInt(3) != 0) {
            super.randomTick(state, world, pos, random);
        }
        if (world.getBaseLightLevel(pos, 0) >= 0) {
            int i = this.getAge(state);
            if (i < this.getMaxAge()) {
                    world.setBlockState(pos, this.withAge(i + 1), 2);
            }
        }
    }

    protected int getGrowthAmount(World world) {
        return super.getGrowthAmount(world) / 3;
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{AGE});
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return AGE_TO_SHAPE[state.get(this.getAgeProperty())];
    }

    static {
        AGE = Properties.AGE_3;
        AGE_TO_SHAPE = new VoxelShape[]{Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 3.0, 16.0), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 6.0, 16.0), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 8.0, 16.0), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 8.0, 16.0)};
    }
}
