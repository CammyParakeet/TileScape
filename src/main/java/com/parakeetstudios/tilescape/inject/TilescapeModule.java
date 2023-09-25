package com.parakeetstudios.tilescape.inject;

import com.google.inject.AbstractModule;
import com.parakeetstudios.tilescape.TilescapePlugin;

/**
 * @author Cammy
 * @version 1.0
 */


/**
 * TODO
 */
public class TilescapeModule extends AbstractModule {

    private final TilescapePlugin plugin;

    public TilescapeModule(TilescapePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    protected void configure() {
        bind(TilescapePlugin.class).toInstance(plugin);
    }

}
