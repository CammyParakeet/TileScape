package com.parakeetstudios.tilescape;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.parakeetstudios.tilescape.core.commands.debug.JoinGameCmd;
import com.parakeetstudios.tilescape.data.TilescapeConfig;
import com.parakeetstudios.tilescape.inject.TilescapeModule;
import com.parakeetstudios.tilescape.managers.CentralQueueRegistry;
import com.parakeetstudios.tilescape.managers.GameManager;
import com.parakeetstudios.tilescape.managers.UtilityManager;
import com.parakeetstudios.tilescape.utils.Paralog;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.Set;

/**
 * @author Cammy
 * @version 1.0
 */

@Singleton
public class TilescapePlugin extends JavaPlugin {

    @Inject
    private Injector injector;

    @Inject
    private Set<UtilityManager> utilityManagers;

    @Override
    public void onLoad() {
        Paralog.init(getLogger());
        try {
            injector = Guice.createInjector(new TilescapeModule(this));
        } catch (Exception e) {
            getLogger().severe("Failed to create Guice injector: " + e.getMessage());
            e.printStackTrace();
        }
    }


    @Override
    public void onEnable() {
        saveDefaultConfig();
        reloadConfig();

        Paralog.info(getName() + " is starting...");

        // enable utilities
        // TODO - should only activate queue-managers that have are enabled in config
        utilityManagers.forEach(UtilityManager::onEnable);


        // temp cmd setup
        Objects.requireNonNull(getCommand("join")).setExecutor(new JoinGameCmd(injector.getInstance(CentralQueueRegistry.class)));

        TilescapeConfig cfg = injector.getInstance(TilescapeConfig.class);
        Paralog.info(cfg.getChessLocations().toString());

        //TODO
    }


    @Override
    public void onDisable() {
        Paralog.info(getName() + " is shutting down...");
        utilityManagers.forEach(UtilityManager::onDisable);
    }


}

