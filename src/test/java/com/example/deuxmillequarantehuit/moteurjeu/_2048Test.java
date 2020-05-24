package com.example.deuxmillequarantehuit.moteurjeu;

import net.jqwik.api.*;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

public class _2048Test {
    @Property
    void canCreateGame(@ForAll("randomNewGame") _2048 game) {
        Collection<Tile> nonZeroTiles = game.getNonZeroTiles();
        assertThat(nonZeroTiles).hasSize(1);
        assertThat(nonZeroTiles.iterator().next().getX()).isBetween(0,3);
        assertThat(nonZeroTiles.iterator().next().getY()).isBetween(0,3);
    }

    @Provide
    Arbitrary<_2048> randomNewGame() {
        return Arbitraries.randoms().map(random -> _2048.newRegularGame());
    }

    @Test
    void moveLeft() {
        _2048 game = _2048.fromTiles(new Tile(2,2));
    }
}
