package dev.bedcrab.events;

import net.minestom.server.event.player.PlayerLoginEvent;

public class PlayerEvents {
    /**
     * Called when a player joins
     */
    public void onJoin(PlayerLoginEvent event) {
        // event.setSpawningInstance(...);
        // event.getPlayer().setRespawnPoint(new Pos(0.5, 64, 0.5));
    }
}