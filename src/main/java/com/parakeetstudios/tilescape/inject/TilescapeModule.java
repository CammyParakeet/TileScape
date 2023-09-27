package com.parakeetstudios.tilescape.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.multibindings.Multibinder;
import com.parakeetstudios.tilescape.TilescapePlugin;
import com.parakeetstudios.tilescape.core.piece.chess.DebugChessSpawner;
import com.parakeetstudios.tilescape.data.TilescapeConfig;
import com.parakeetstudios.tilescape.game.board.chess.ChessBoard;
import com.parakeetstudios.tilescape.game.piece.chess.ChessPiece;
import com.parakeetstudios.tilescape.game.piece.shogi.ShogiPiece;
import com.parakeetstudios.tilescape.managers.GameManager;
import com.parakeetstudios.tilescape.managers.chess.ChessGameManager;

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

        // bind game managers
        Multibinder<GameManager> gameManagerBinder = Multibinder.newSetBinder(binder(), GameManager.class);
        gameManagerBinder.addBinding().to(ChessGameManager.class);


        // bind board classes
        install(new FactoryModuleBuilder().build(new TypeLiteral<BoardFactory<ChessBoard>>(){}));

        // bind piece classes
        install(new FactoryModuleBuilder().build(new TypeLiteral<PieceFactory<ChessPiece>>(){}));
        install(new FactoryModuleBuilder().build(new TypeLiteral<PieceFactory<ShogiPiece>>(){}));

        //bind piece spawner
        //install(new FactoryModuleBuilder().build(new TypeLiteral<PieceSpawnerFactory<DebugChessSpawner>>(){}));


    }

    // Provide a single config wrapper instance to our classes
    @Provides @Singleton
    TilescapeConfig provideConfig() {
        return new TilescapeConfig(plugin.getConfig());
    }


}
