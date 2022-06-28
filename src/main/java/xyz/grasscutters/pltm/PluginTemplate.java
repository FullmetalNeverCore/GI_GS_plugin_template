package xyz.grasscutters.pltm;

import emu.grasscutter.Grasscutter;
import emu.grasscutter.plugin.Plugin;
import emu.grasscutter.server.event.EventHandler;
import emu.grasscutter.server.event.HandlerPriority;
import emu.grasscutter.server.event.player.PlayerJoinEvent;

import xyz.grasscutters.pltm.commands.*;
import xyz.grasscutters.pltm.objects.*;

import java.io.*;
import java.util.stream.Collectors;


public final class PluginTemplate extends Plugin {

    private static PluginTemplate instance;


    public static PluginTemplate getInstance() {
        return instance;
    }

    private PluginConfig configuration;


    @Override public void onLoad() {
    
        instance = this;
 
        File config = new File(this.getDataFolder(), "config.json");
 
        try {
            if(!config.exists() && !config.createNewFile()) {
                this.getLogger().error("Failed to create config file.");
            } else {
                try (FileWriter writer = new FileWriter(config)) {
                    InputStream configStream = this.getResource("config.json");
                    if(configStream == null) {
                        this.getLogger().error("Failed to save default config file.");
                    } else {
                        writer.write(new BufferedReader(
                                new InputStreamReader(configStream)).lines().collect(Collectors.joining("\n"))
                        ); writer.close();

                        this.getLogger().info("Saved default config file.");
                    }
                }
            }

            this.configuration = Grasscutter.getGsonFactory().fromJson(new FileReader(config), PluginConfig.class);
        } catch (IOException exception) {
            this.getLogger().error("Failed to create config file.", exception);
        }
        

        this.getLogger().info("The example plugin has been loaded.");
    }


    @Override public void onEnable() {

        new EventHandler<>(PlayerJoinEvent.class)
                .priority(HandlerPriority.LOW)
                .listener(EventListeners::onJoin)
                .register();
        
   
        this.getHandle().registerCommand(new ExampleCommand());


        this.getLogger().info("The HW plugin has been enabled.");
    }


    @Override public void onDisable() {
        this.getLogger().info("The HW plugin has been disabled.");
    }

    public PluginConfig getConfiguration() {
        return this.configuration;
    }
}
