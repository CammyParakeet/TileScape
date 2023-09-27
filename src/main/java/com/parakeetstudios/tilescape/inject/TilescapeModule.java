package com.parakeetstudios.tilescape.inject;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Names;
import com.parakeetstudios.tilescape.TilescapePlugin;
import com.parakeetstudios.tilescape.core.piece.PieceController;
import com.parakeetstudios.tilescape.core.piece.chess.ChessDebugController;
import com.parakeetstudios.tilescape.data.TilescapeConfig;
import com.parakeetstudios.tilescape.game.board.Board;
import com.parakeetstudios.tilescape.game.board.chess.ChessBoard;
import com.parakeetstudios.tilescape.game.piece.Piece;
import com.parakeetstudios.tilescape.game.piece.chess.ChessPiece;
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
        // bind game manager
        bind(GameManager.class);

        Multibinder<GameManager> gameManagerBinder = Multibinder.newSetBinder(binder(), GameManager.class);
        gameManagerBinder.addBinding().to(ChessGameManager.class);

        // Board Factory builder
        install(new FactoryModuleBuilder()
                // bind chessboard
                .implement(Board.class, Names.named("chess"), ChessBoard.class)
                //.implement(Board.class, Names.named("shogi"), ShogiBoard.class) example
                .build(BoardFactory.class));

        // Piece Factory builder
        install(new FactoryModuleBuilder()
                .implement(Piece.class, Names.named("chess"), ChessPiece.class)
                //.implement(Piece.class, Names.named("shogi"), ShogiPiece.class) example
                .build(PieceFactory.class));

        // Piece Type Factory builder
        install(new FactoryModuleBuilder()
                .implement(PieceController.class, Names.named("chess_debug"), ChessDebugController.class)
                //.implement(PieceController.class, Names.named("shogi_debug"), ShogiDebugController.class)
                .build(PieceControllerFactory.class));
    }

    // Provide a single config wrapper instance to our classes
    @Provides @Singleton
    TilescapeConfig provideConfig() {
        return new TilescapeConfig(plugin.getConfig());
    }

}
