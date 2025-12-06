package sanswee.mvtest;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class MVTest extends JavaPlugin {

    public static MVTest instance;
    @Override
    public void onEnable() {
        NamespacedKey key = new NamespacedKey(this, "owner");
        instance = this;




        getServer().getPluginManager().registerEvents(new HardcoreWorldSettings(), this);
        // Register Brigadier commands during the COMMANDS lifecycle
        this.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, event -> {
                    final Commands registrar = event.registrar();

                    // /sanswee tp
                    LiteralArgumentBuilder<CommandSourceStack> root =
                            Commands.literal("smv")
                                    .then(
                                            Commands.literal("tp")
                                                    .then(
                                                            Commands.argument("world", StringArgumentType.word())
                                                                    .executes(ctx -> {
                                                                        String worldName = StringArgumentType.getString(ctx, "world");
                                                                        World world = Bukkit.getWorld(worldName);
                                                                        if (world == null) {
                                                                            ctx.getSource().getSender().sendMessage("World not found: " + worldName);
                                                                            return 0; // failure
                                                                        }

                                                                        if (ctx.getSource().getExecutor() instanceof Player player) {
                                                                            player.teleport(world.getSpawnLocation());
                                                                            player.sendMessage("Teleported to world: " + worldName);
                                                                            return 1; // success
                                                                        }

                                                                        ctx.getSource().getSender().sendMessage("Only players can run this command.");
                                                                        return 0;
                                                                    })
                                                    )
                                    );

                    registrar.register(root.build(), "sw"); // optional alias
                });

        // Plugin startup logic
        new WorldCreator("hardcore")
                .hardcore(true)
                .createWorld();




    }

    public static MVTest getInstance() {
        return instance;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
