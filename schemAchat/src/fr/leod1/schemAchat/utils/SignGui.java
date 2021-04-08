package fr.leod1.schemAchat.utils;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.BlockPosition;
import com.comphenix.protocol.wrappers.WrappedBlockData;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import one.util.streamex.StreamEx;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.util.function.BiConsumer;
import java.util.logging.Logger;

import static fr.leod1.schemAchat.SchemAchat.InstanceMain;


public class SignGui
{

    private BiConsumer<Player, String[]> listener;
    private String[] lines;
    private BlockPosition position;
    private boolean color;

    public void show(Player player)
    {
        ProtocolManager protocol = ProtocolLibrary.getProtocolManager();

        PacketContainer open = protocol.createPacket(PacketType.Play.Server.OPEN_SIGN_EDITOR);
        PacketContainer remove = protocol.createPacket(PacketType.Play.Server.BLOCK_CHANGE);

        Location location = SignLocator.get().next(player.getLocation().getChunk());
        BlockPosition position = this.position = new BlockPosition(location.getBlockX(), 0, location.getBlockZ());

        open.getBlockPositionModifier().write(0, position);
        remove.getBlockPositionModifier().write(0, position);
        remove.getBlockData().write(0, WrappedBlockData.createData(Material.AIR));

        try
        {
            PacketContainer block = protocol.createPacket(PacketType.Play.Server.BLOCK_CHANGE);

            if(lines != null)
            {
                // TODO: Replaced in 1.9.4 with TILE_ENTITY_DATA packet.
                PacketContainer update = protocol.createPacket(PacketType.Play.Server.UPDATE_SIGN);

                block.getBlockPositionModifier().write(0, position);
                block.getBlockData().write(0, WrappedBlockData.createData(Material.SIGN_POST));
                update.getBlockPositionModifier().write(0, position);
                update.getChatComponentArrays().write(0, wrap());

                protocol.sendServerPacket(player, block);
                protocol.sendServerPacket(player, update);
            }

            protocol.sendServerPacket(player, open);
            protocol.sendServerPacket(player, block);
            protocol.sendServerPacket(player, remove);
        }
        catch (InvocationTargetException e)
        {
            return;
        }

        if(listener != null)
        {
            protocol.addPacketListener(new PacketAdapter(InstanceMain, PacketType.Play.Client.UPDATE_SIGN)
            {
                @Override
                public void onPacketReceiving(PacketEvent event)
                {
                    protocol.removePacketListener(this);

                    PacketContainer packet = event.getPacket();
                    BlockPosition position = packet.getBlockPositionModifier().read(0);
                    WrappedChatComponent[] components = packet.getChatComponentArrays().read(0);

                    if(SignGui.this.position == null)
                    {
                        throw new IllegalStateException("Sign update called but position not yet set.");
                    }

                    if(SignGui.this.position.equals(position))
                    {
                        Bukkit.getScheduler().runTask(plugin, () -> listener.accept(event.getPlayer(), unwrap(components)));
                    }
                }
            });
        }
    }

    private WrappedChatComponent[] wrap()
    {
        return StreamEx.of(lines)
                .map(line -> line == null ? "" : line)
                .map(WrappedChatComponent::fromText)
                .toArray(WrappedChatComponent[]::new);
    }

    private String[] unwrap(WrappedChatComponent[] components)
    {
        return StreamEx.of(components)
                .map(component -> IChatBaseComponent.ChatSerializer.a(component.getJson()).getText())
                .map(text -> color ? ChatColor.translateAlternateColorCodes('&', text) : text)
                .toArray(String[]::new);
    }

    public SignGui line(Line line, String text)
    {
        if(lines == null)
        {
            lines = new String[4];
        }

        lines[line.ordinal()] = text;
        return this;
    }

    public SignGui listener(BiConsumer<Player, String[]> listener)
    {
        this.listener = listener;
        return this;
    }

    public SignGui color()
    {
        this.color = true;
        return this;
    }
}
