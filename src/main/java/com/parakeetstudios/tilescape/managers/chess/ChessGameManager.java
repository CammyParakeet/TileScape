package com.parakeetstudios.tilescape.managers.chess;

import com.parakeetstudios.tilescape.game.BoardGame;
import com.parakeetstudios.tilescape.game.games.ChessGame;
import com.parakeetstudios.tilescape.managers.GameManager;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.UUID;

public class ChessGameManager implements GameManager {



    @Override
    public void buildGame(@NotNull UUID pID1, @NotNull UUID pID2) {
        //ChessGame game = new ChessGame()
    }

    @Override
    public void registerGame(@NotNull BoardGame game) {

    }

    @Override
    public boolean isPlayerInGame(@NotNull UUID pID) {
        return false;
    }

    @Override
    public void endGame(@NotNull BoardGame game) {

    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }
}
