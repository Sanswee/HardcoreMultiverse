package sanswee.mvtest;

import org.bukkit.Material;
import java.util.EnumSet;
import java.util.Set;

public class ContainerBlocks {
    public static final Set<Material> CONTAINER_BLOCKS = EnumSet.of(
            // General storage
            Material.CHEST,
            Material.TRAPPED_CHEST,
            Material.BARREL,

            // Shulker boxes (all colors)
            Material.SHULKER_BOX,
            Material.WHITE_SHULKER_BOX,
            Material.ORANGE_SHULKER_BOX,
            Material.MAGENTA_SHULKER_BOX,
            Material.LIGHT_BLUE_SHULKER_BOX,
            Material.YELLOW_SHULKER_BOX,
            Material.LIME_SHULKER_BOX,
            Material.PINK_SHULKER_BOX,
            Material.GRAY_SHULKER_BOX,
            Material.LIGHT_GRAY_SHULKER_BOX,
            Material.CYAN_SHULKER_BOX,
            Material.PURPLE_SHULKER_BOX,
            Material.BLUE_SHULKER_BOX,
            Material.BROWN_SHULKER_BOX,
            Material.GREEN_SHULKER_BOX,
            Material.RED_SHULKER_BOX,
            Material.BLACK_SHULKER_BOX,

            // Utility
            Material.FURNACE,
            Material.BLAST_FURNACE,
            Material.SMOKER,
            Material.BREWING_STAND,
            Material.CARTOGRAPHY_TABLE,
            Material.LOOM,
            Material.STONECUTTER,
            Material.GRINDSTONE,
            Material.ENCHANTING_TABLE, // has a slot
            Material.ANVIL,            // has slots (damage too)

            // Redstone inventories
            Material.HOPPER,
            Material.DROPPER,
            Material.DISPENSER,

            // Misc
            Material.JUKEBOX,
            Material.LECTERN,
            Material.BEACON
    );
}
