package com.parakeetstudios.tilescape.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.name.Names;
import com.parakeetstudios.tilescape.TilescapePlugin;
import com.parakeetstudios.tilescape.data.TilescapeConfig;
import com.parakeetstudios.tilescape.game.board.Board;
import com.parakeetstudios.tilescape.game.board.chess.ChessBoard;
import com.parakeetstudios.tilescape.managers.GameManager;

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
        // create single plugin instance
        bind(TilescapePlugin.class).toInstance(plugin);
        // bind eager game manager
        bind(GameManager.class).asEagerSingleton();

        install(new FactoryModuleBuilder()
                // bind chessboard
                .implement(Board.class, Names.named("chess"), ChessBoard.class)
                //.implement(Board.class, Names.named("idk something else?"), Something.class)
                .build(BoardFactory.class));
    }

    @Provides @Singleton
    TilescapeConfig provideConfig() {
        return new TilescapeConfig(plugin.getConfig());
    }

}
