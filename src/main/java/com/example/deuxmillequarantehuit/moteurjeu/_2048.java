package com.example.deuxmillequarantehuit.moteurjeu;

import java.util.*;

public class _2048 {
    List<Tile> tiles = new ArrayList<>();
    private int size;

    private _2048() {
        this(4);
    }

    private _2048(int size) {
        this.size = size;
    }

    public static _2048 newRegularGame() {
        int size = 4;
        _2048 game = new _2048(size);
        game.tiles.add(Tile.createTile2(
                new Random().nextInt(size),
                new Random().nextInt(size)));
        return game;
    }

    public static _2048 fromTiles(Tile... tiles) {
        _2048 game = new _2048();
        game.tiles.addAll(Arrays.asList(tiles));
        return game;
    }

    public Collection<Tile> getTiles() {
        return tiles;
    }

    public void moveRight() {
        tiles.forEach(tile -> tile.y = size - 1);
    }

    public void moveLeft() {
        tiles.forEach(tile -> tile.y = 0);
    }

    public void moveUp() {
        tiles.forEach(tile -> tile.x = 0);
    }

    public void moveDown() {
        tiles.forEach(tile -> tile.x = size - 1);
    }
}
