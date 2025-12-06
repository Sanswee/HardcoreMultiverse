package sanswee.mvtest;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MVCommands {
    LiteralArgumentBuilder<CommandSourceStack> root =
            Commands.literal("sanswee")
                    .then(
                            Commands.literal("tp")
                                    .executes(ctx -> {
                                        Player p = (Player) ctx.getSource().getExecutor(); // basic example
                                        p.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
                                        p.sendMessage("Teleported!");
                                        return 1;
                                    })
                    );
}
