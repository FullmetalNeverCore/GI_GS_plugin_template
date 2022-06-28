package xyz.grasscutters.pltm;

import emu.grasscutter.game.player.Player;
import emu.grasscutter.server.event.EventHandler;
import emu.grasscutter.server.event.HandlerPriority;
import emu.grasscutter.server.event.player.PlayerJoinEvent;
import xyz.grasscutters.pltm.objects.PluginConfig;

public final class EventListeners {
    private static final PluginConfig config = PluginTemplate.getInstance().getConfiguration();
    

    public static void onJoin(PlayerJoinEvent event) {
        if(!config.sendJoinMessage) return; 
        
        Player player = event.getPlayer();
        player.dropMessage(config.joinMessage); 
    }
}
