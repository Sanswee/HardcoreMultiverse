package sanswee.mvtest;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.TileState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;


public class HardcoreWorldSettings implements Listener {

    @EventHandler
    public void onWorldLoad(WorldLoadEvent event) {
        World world = event.getWorld();

        if (world.getName().equalsIgnoreCase("hardcore")) {
            // Force hardcore-like rules
            world.setDifficulty(Difficulty.HARD);
            world.setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, false);
            world.setGameRule(GameRule.KEEP_INVENTORY, true);

            // You could add more rules here, e.g.
            // world.setGameRule(GameRule.KEEP_INVENTORY, false);
            // world.setGameRule(GameRule.NATURAL_REGENERATION, true);
            world.getPlayers().forEach(p -> p.sendMessage("Hardcore settings applied to this world."));
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        if (event.getEntity().getWorld().getName().equalsIgnoreCase("hardcore")){
            event.getEntity().getInventory().clear();
            event.getEntity().setExperienceLevelAndProgress(0);
            NamespacedKey key = new NamespacedKey(MVTest.instance, "owner");
            PersistentDataContainer pdc = event.getPlayer().getPersistentDataContainer();
            int count = pdc.get(key, PersistentDataType.INTEGER);
            pdc.set(key, PersistentDataType.INTEGER, 1 + count);

            event.getPlayer().sendMessage("You are now on run: " + (1 + count));
        }

    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        World world = event.getBlock().getWorld();
        Block block = event.getBlock();
        Player player = event.getPlayer();
        if (world.getName().equalsIgnoreCase("hardcore")){
            if (block.getState() instanceof TileState tile){

                    String uuid = player.getUniqueId().toString();
                    NamespacedKey key = new NamespacedKey(MVTest.instance, "owner");
                    PersistentDataContainer pdc = tile.getPersistentDataContainer();
                    PersistentDataContainer pdc2 = player.getPersistentDataContainer();
                    String runCount = pdc2.get(key, PersistentDataType.INTEGER).toString();
                    String uniqueKey = uuid + ":" + runCount;

                    tile.getPersistentDataContainer().set(key, PersistentDataType.STRING, uniqueKey);
                    tile.update();
                    //player.sendMessage("Unique key for " + block + " is " + uniqueKey);
            }


            //player.sendMessage("You placed a " + block.getType() + " at " + block.getLocation());


        }


    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        if (event.getPlayer().getWorld().getName().equalsIgnoreCase("hardcore")){
            Block block = event.getClickedBlock();
            if (block.getState() instanceof TileState tile){
                Player player = event.getPlayer();

                NamespacedKey key = new NamespacedKey(MVTest.instance, "owner");
                PersistentDataContainer pdc = player.getPersistentDataContainer();
                String uuid = player.getUniqueId().toString();
                String runCount = pdc.get(key, PersistentDataType.INTEGER).toString();
                String uniqueKey = uuid + ":" + runCount;

                PersistentDataContainer pdc2 = tile.getPersistentDataContainer();
                String blockKey = pdc2.get(key, PersistentDataType.STRING);
                String subKey = blockKey.substring(0, 36);
                if (!uniqueKey.equalsIgnoreCase(blockKey) &&  (subKey.equalsIgnoreCase(uuid))){
                    block.setType(Material.AIR);
                    //event.setCancelled(true);
                    player.sendMessage("You can no longer interact with this block.");
                }
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        World world = player.getWorld();
        if (world.getName().equalsIgnoreCase("hardcore")){
            NamespacedKey key = new NamespacedKey(MVTest.instance, "owner");
            PersistentDataContainer pdc = player.getPersistentDataContainer();
            if (pdc.get(key, PersistentDataType.INTEGER) == null){
                pdc.set(key, PersistentDataType.INTEGER, 1);
                player.sendMessage("You are on life: " + 1);
            }
        }
    }
}
