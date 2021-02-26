package dev.frankheijden.insights.commands.brigadier;

import cloud.commandframework.brigadier.CloudBrigadierManager;
import com.mojang.brigadier.arguments.StringArgumentType;
import dev.frankheijden.insights.commands.parser.ScanObjectArrayArgument;
import io.leangen.geantyref.TypeToken;
import org.bukkit.command.CommandSender;

public class BrigadierHandler {

    private final CloudBrigadierManager<CommandSender, ?> brigadierManager;

    public BrigadierHandler(CloudBrigadierManager<CommandSender, ?> brigadierManager) {
        this.brigadierManager = brigadierManager;
    }

    public void registerTypes() {
        brigadierManager.registerMapping(new TypeToken<ScanObjectArrayArgument.ScanObjectArrayParser>() {
        }, false, argument -> StringArgumentType.greedyString());
    }
}
