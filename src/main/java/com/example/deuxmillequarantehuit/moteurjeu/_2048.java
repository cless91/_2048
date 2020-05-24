package com.example.deuxmillequarantehuit.moteurjeu;

import java.util.*;

public class _2048 {
    List<Tile> tiles = new ArrayList<>();

    private _2048() {
        tiles.add(new Tile(
                new Random().nextInt(4),
                new Random().nextInt(4)));
    }

    public static _2048 newRegularGame() {
        _2048 game = new _2048();
        return game;
    }

    public static _2048 fromTiles(Tile ... tiles) {
        _2048 game = new _2048();
        game.tiles.addAll(Arrays.asList(tiles));
        return game;
    }

    public Collection<Tile> getNonZeroTiles() {
        return tiles;
    }
}
