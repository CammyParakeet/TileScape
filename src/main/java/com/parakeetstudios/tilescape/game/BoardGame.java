package com.parakeetstudios.tilescape.game;

import java.util.Set;
import java.util.UUID;

public interface BoardGame {

    void build();

    UUID getGameID();

    Set<UUID> getPlayers();

    Set<UUID> getViewers();

    //Board getBoard();

    boolean isPlayerTurn(UUID pID);

    void endTurn();

    void destroy();


}
