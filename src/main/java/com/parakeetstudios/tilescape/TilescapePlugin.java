package com.parakeetstudios.tilescape;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.parakeetstudios.tilescape.inject.TilescapeModule;
import com.parakeetstudios.tilescape.utils.Paralog;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Cammy
 * @version 1.0
 */

@Singleton
public class TilescapePlugin extends JavaPlugin {

    private Injector injector;

    @Override
    public void onLoad() {
        injector = Guice.createInjector(new TilescapeModule(this));
    }


    @Override
    public void onEnable() {
        Paralog.init(getLogger());

        //TODO
    }


    @Override
    public void onDisable() {

    }


}

