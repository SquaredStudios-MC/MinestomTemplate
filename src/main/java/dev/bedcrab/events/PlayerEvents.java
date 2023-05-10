package dev.bedcrab.events;

import net.minestom.server.event.player.PlayerLoginEvent;

public class PlayerEvents {
    /**
     * Called when a player joins
     */
    public void onJoin(PlayerLoginEvent event) {
        final Player player = event.getPlayer();
        InstanceContainer instance = MinestomServer.getInstance().instanceContainer;
        instance.setChunkLoader(new AnvilLoader(PlayerEvents.class.getClassLoader().getResource((String) MinestomServer.configuration.get("worlds-directory") + "/world").getPath()));

        player.setRespawnPoint(new Pos(0, 70, 0)); // Set the player spawn location
        event.setSpawningInstance(instance);
    }
}