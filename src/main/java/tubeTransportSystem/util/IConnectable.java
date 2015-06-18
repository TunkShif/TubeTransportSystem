package tubeTransportSystem.util;

import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public interface IConnectable {
    public boolean canConnectTo(IBlockAccess blockAccess, int x, int y, int z, ForgeDirection d);
    public boolean canConnectToStrict(IBlockAccess blockAccess, int x, int y, int z, ForgeDirection d);
}
