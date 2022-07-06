package dev.frankheijden.insights.api.concurrent.containers;

import dev.frankheijden.insights.api.concurrent.ScanOptions;
import dev.frankheijden.insights.api.objects.chunk.ChunkCuboid;
import dev.frankheijden.insights.api.objects.chunk.ChunkLocation;
import net.minecraft.world.level.chunk.LevelChunkSection;
import org.bukkit.Chunk;
import org.bukkit.craftbukkit.v1_19_R1.CraftChunk;

public class LoadedChunkContainer extends ChunkContainer {

    private final Chunk chunk;

    /**
     * Constructs a new LoadedChunkContainer, for scanning of a loaded chunk.
     */
    public LoadedChunkContainer(Chunk chunk, ChunkCuboid cuboid, ScanOptions options) {
        super(ChunkLocation.of(chunk), cuboid, options);

        this.chunk = chunk;
    }

    @Override
    public LevelChunkSection[] chunkSections() {
        return ((CraftChunk) chunk).getHandle().getSections();
    }
}
