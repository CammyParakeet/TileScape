package com.parakeetstudios.tilescape;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.parakeetstudios.tilescape.inject.TilescapeModule;
import com.parakeetstudios.tilescape.managers.GameManager;
import com.parakeetstudios.tilescape.utils.Paralog;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Cammy
 * @version 1.0
 */

@Singleton
public class TilescapePlugin extends JavaPlugin {

    private Injector injector;

    @Inject
    private GameManager gameManager;

    @Override
    public void onLoad() {
        Paralog.init(getLogger());
        injector = Guice.createInjector(new TilescapeModule(this));
    }


    @Override
    public void onEnable() {
        Paralog.info(getName() + " is starting...");

        //TODO
    }


    @Override
    public void onDisable() {
        Paralog.info(getName() + " is shutting down...");
    }


}

