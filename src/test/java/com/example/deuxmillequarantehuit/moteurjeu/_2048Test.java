package com.example.deuxmillequarantehuit.moteurjeu;

import net.jqwik.api.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static com.example.deuxmillequarantehuit.moteurjeu.Tile.createTile;
import static com.example.deuxmillequarantehuit.moteurjeu.Tile.createTile2;
import static org.assertj.core.api.Assertions.assertThat;

public class _2048Test {

    private _2048 game;

    @Property
    void canCreateGame(@ForAll("randomNewGame") _2048 game) {
        Collection<Tile> nonZeroTiles = game.getTiles();
        assertThat(nonZeroTiles).hasSize(1);
        assertThat(nonZeroTiles.iterator().next().x).isBetween(0, 3);
        assertThat(nonZeroTiles.iterator().next().y).isBetween(0, 3);
    }

    @Provide
    Arbitrary<_2048> randomNewGame() {
        return Arbitraries.randoms().map(random -> _2048.newRegularGame());
    }

    @Nested
    public class NewlyCreatedGameWithOnlyOneTile {

        @BeforeEach
        void setUp() {
            game = _2048.fromTiles(createTile2(2, 2));
        }

        @Test
        void moveRight() {
            game.moveRight();
            Tile tile = game.getTiles().iterator().next();
            assertThat(tile.x).isEqualTo(2);
            assertThat(tile.y).isEqualTo(3);
        }

        @Test
        void moveLeft() {
            game.moveLeft();
            Tile tile = game.getTiles().iterator().next();
            assertThat(tile.x).isEqualTo(2);
            assertThat(tile.y).isEqualTo(0);
        }

        @Test
        void moveUp() {
            game.moveUp();
            Tile tile = game.getTiles().iterator().next();
            assertThat(tile.x).isEqualTo(0);
            assertThat(tile.y).isEqualTo(2);
        }

        @Test
        void moveDown() {
            game.moveDown();
            Tile tile = game.getTiles().iterator().next();
            assertThat(tile.x).isEqualTo(3);
            assertThat(tile.y).isEqualTo(2);
        }
    }

    @Nested
    public class MoveMerge {

        @BeforeEach
        void setUp() {
            game = _2048.fromTiles(
                    createTile2(2, 2),
                    createTile2(0, 2),
                    createTile2(2, 0),
                    createTile2(3, 2),
                    createTile2(2, 3));
        }

        @Test
        void moveRightEasier() {
            game = _2048.fromTiles(
                    createTile2(2, 2),
                    createTile2(0, 2),
                    createTile2(3, 2),
                    createTile2(2, 3));
            game.moveRight();

            assertThat(game.getTiles())
                    .usingFieldByFieldElementComparator()
                    .containsExactlyInAnyOrder(
                            createTile2(0, 3),
                            createTile(2, 3, 4),
                            createTile2(3, 3));
        }

        @Test
        void moveRight() {
            game.moveRight();

            assertThat(game.getTiles())
                    .usingFieldByFieldElementComparator()
                    .containsExactlyInAnyOrder(
                            createTile2(0, 3),
                            createTile2(2, 2),
                            createTile(2, 3, 4),
                            createTile2(3, 3));
        }
    }
}
