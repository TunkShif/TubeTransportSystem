package enhanced.tts.item;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import enhanced.tts.TubeTransportSystem;
import enhanced.tts.block.BlockStation;
import enhanced.tts.block.BlockStationHorizontal;
import enhanced.tts.util.Utilities;

public class ItemStation extends ItemBlockWithMetadata {
    public static IIcon entrance, side;

    public ItemStation(Block b) {
        super(b, b);
    }

    @Override
    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
        if (side == 0 || side == 1) {
            if (!world.isAirBlock(x, y + 1, z))
                return false;

            metadata = Utilities.entityGetDirection(player).ordinal();

            if (world.setBlock(x, y, z, field_150939_a, metadata, 3) && world.setBlock(x, y + 1, z, field_150939_a, metadata + BlockStation.SHIFT, 3)) {
                if (world.getBlock(x, y, z) == field_150939_a) {
                    field_150939_a.onBlockPlacedBy(world, x, y, z, player, stack);
                    field_150939_a.onPostBlockPlaced(world, x, y, z, metadata);
                }

                if (world.getBlock(x, y + 1, z) == field_150939_a) {
                    field_150939_a.onBlockPlacedBy(world, x, y + 1, z, player, stack);
                    field_150939_a.onPostBlockPlaced(world, x, y + 1, z, metadata + BlockStation.SHIFT);
                }

                return true;
            }
        } else {
            ForgeDirection d = Utilities.entityGetDirection(player);
            int x2 = x + d.offsetX, y2 = y + d.offsetY, z2 = z + d.offsetZ;
            metadata = d.ordinal();

            if (!world.isAirBlock(x2, y2, z2))
                return false;

            if (world.setBlock(x, y, z, BlockStationHorizontal.instance, metadata + BlockStationHorizontal.SHIFT, 3) && world.setBlock(x2, y2, z2, BlockStationHorizontal.instance, metadata, 3)) {
                if (world.getBlock(x, y, z) == BlockStationHorizontal.instance) {
                    BlockStationHorizontal.instance.onBlockPlacedBy(world, x, y, z, player, stack);
                    BlockStationHorizontal.instance.onPostBlockPlaced(world, x, y, z, metadata + BlockStationHorizontal.SHIFT);
                }

                if (world.getBlock(x, y + 1, z) == BlockStationHorizontal.instance) {
                    BlockStationHorizontal.instance.onBlockPlacedBy(world, x2, y2, z2, player, stack);
                    BlockStationHorizontal.instance.onPostBlockPlaced(world, x2, y2, z2, metadata);
                }

                return true;
            }
        }

        return false;
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        super.registerIcons(iconRegister);
        entrance = iconRegister.registerIcon(TubeTransportSystem.MOD_ID + ":station1");
        side = iconRegister.registerIcon(TubeTransportSystem.MOD_ID + ":station2");
    }
}
