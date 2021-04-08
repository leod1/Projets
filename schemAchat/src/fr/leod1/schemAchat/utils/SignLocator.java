package fr.leod1.schemAchat.utils;

import one.util.streamex.IntStreamEx;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkUnloadEvent;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SignLocator implements Listener
{
    private static final SignLocator instance = new SignLocator();

    private Map<Chunk, Iterator<Location>> iterators = new HashMap<>();

    @EventHandler(ignoreCancelled = true)
    public void onChunkUnload(ChunkUnloadEvent event)
    {
        iterators.remove(event.getChunk());
    }

    public Location next(Chunk chunk)
    {
        Iterator<Location> iterator = iterators.get(chunk);

        if (iterator == null || !iterator.hasNext())
        {
            iterator = create(chunk);
            iterators.put(chunk, iterator);
        }

        return iterator.next();
    }

    private Iterator<Location> create(Chunk chunk)
    {
        int minX = chunk.getX() << 4;
        int minZ = chunk.getZ() << 4;
        int maxX = minX + 16;
        int maxZ = minZ + 16;

        return IntStreamEx.range(0, chunk.getWorld().getMaxHeight())
                .flatMapToObj(y -> IntStreamEx.range(minX, maxX)
                        .flatMapToObj(x -> IntStreamEx.range(minZ, maxZ)
                                .mapToObj(z -> new Location(chunk.getWorld(), x, y, z))))
                .iterator();
    }

    public static SignLocator get()
    {
        return instance;
    }
}
