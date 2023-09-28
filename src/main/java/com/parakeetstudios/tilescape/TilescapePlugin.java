package com.parakeetstudios.tilescape;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.parakeetstudios.tilescape.game.piece.SimplePieceColor;
import com.parakeetstudios.tilescape.inject.TilescapeModule;
import com.parakeetstudios.tilescape.managers.GameManager;
import com.parakeetstudios.tilescape.utils.Paralog;
import org.bukkit.plugin.java.JavaPlugin;

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
    private Set<GameManager> gameManagers;

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

        Paralog.info("" + SimplePieceColor.WHITE);

        Paralog.info(getName() + " is starting...");

        // iterate over managers to enable
        gameManagers.forEach(GameManager::onEnable);

        //TODO
    }


    @Override
    public void onDisable() {
        Paralog.info(getName() + " is shutting down...");
    }


}

