package dev.frankheijden.insights.commands;

import cloud.commandframework.annotations.Argument;
import cloud.commandframework.annotations.CommandMethod;
import cloud.commandframework.annotations.CommandPermission;
import cloud.commandframework.annotations.Flag;
import dev.frankheijden.insights.api.InsightsPlugin;
import dev.frankheijden.insights.api.region.Region;
import dev.frankheijden.insights.api.commands.InsightsCommand;
import dev.frankheijden.insights.api.concurrent.ScanOptions;
import dev.frankheijden.insights.api.config.limits.Limit;
import dev.frankheijden.insights.api.objects.chunk.ChunkPart;
import dev.frankheijden.insights.api.objects.wrappers.ScanObject;
import dev.frankheijden.insights.api.reflection.RTileEntityTypes;
import dev.frankheijden.insights.api.tasks.ScanTask;
import dev.frankheijden.insights.api.utils.Constants;
import org.bukkit.entity.Player;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CommandMethod("scanregion <region>")
public class CommandScanRegion extends InsightsCommand {

    public CommandScanRegion(InsightsPlugin plugin) {
        super(plugin);
    }

    @CommandMethod("tile")
    @CommandPermission("insights.scanregion.tile")
    private void handleTileScan(
            Player player,
            @Argument("region") Region region,
            @Flag(value = "group-by-chunk", aliases = { "c" }) boolean groupByChunk
    ) {
        handleScan(
                player,
                region,
                RTileEntityTypes.getTileEntities(),
                ScanOptions.materialsOnly(),
                false,
                groupByChunk
        );
    }

    @CommandMethod("entity")
    @CommandPermission("insights.scanregion.entity")
    private void handleEntityScan(
            Player player,
            @Argument("region") Region region,
            @Flag(value = "group-by-chunk", aliases = { "c" }) boolean groupByChunk
    ) {
        handleScan(player, region, Constants.SCAN_ENTITIES, ScanOptions.entitiesOnly(), false, groupByChunk);
    }

    @CommandMethod("all")
    @CommandPermission("insights.scanregion.all")
    private void handleAllScan(
            Player player,
            @Argument("region") Region region,
            @Flag(value = "group-by-chunk", aliases = { "c" }) boolean groupByChunk
    ) {
        handleScan(player, region, null, ScanOptions.scanOnly(), false, groupByChunk);
    }

    @CommandMethod("custom <items>")
    @CommandPermission("insights.scanregion.custom")
    private void handleCustomScan(
            Player player,
            @Argument("region") Region region,
            @Flag(value = "group-by-chunk", aliases = { "c" }) boolean groupByChunk,
            @Argument("items") ScanObject<?>[] items
    ) {
        handleScan(player, region, new HashSet<>(Arrays.asList(items)), ScanOptions.scanOnly(), true, groupByChunk);
    }

    @CommandMethod("limit <limit>")
    @CommandPermission("insights.scanregion.limit")
    private void handleLimitScan(
            Player player,
            @Argument("region") Region region,
            @Flag(value = "group-by-chunk", aliases = { "c" }) boolean groupByChunk,
            @Argument("limit") Limit limit
    ) {
        handleScan(player, region, limit.scanObjects(), limit.getScanOptions(), false, groupByChunk);
    }

    /**
     * Checks the player's location for a region and scans it for materials.
     */
    public void handleScan(
            Player player,
            Region region,
            Set<? extends ScanObject<?>> items,
            ScanOptions options,
            boolean displayZeros,
            boolean groupByChunk
    ) {
        List<ChunkPart> parts = region.generateChunkParts();
        if (groupByChunk) {
            ScanTask.scanAndDisplayGroupedByChunk(plugin, player, parts, parts.size(), options, items, false);
        } else {
            ScanTask.scanAndDisplay(plugin, player, parts, parts.size(), options, items, displayZeros);
        }
    }
}
