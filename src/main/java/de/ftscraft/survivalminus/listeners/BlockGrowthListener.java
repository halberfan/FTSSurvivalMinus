package de.ftscraft.survivalminus.listeners;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockGrowEvent;

public class BlockGrowthListener implements Listener {

    @EventHandler
    public void onBlockGrowth(BlockGrowEvent event) {
        Block block = event.getBlock();

    }

}
