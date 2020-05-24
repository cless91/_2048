package com.example.deuxmillequarantehuit.moteurjeu;

import java.util.*;

public class _2048 {
    private final int maxCoord;
    List<Tile> tiles = new ArrayList<>();
    private final int size;

    private _2048() {
        this(4);
    }

    private _2048(int size) {
        this.size = size;
        maxCoord = size - 1;
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
        for (int i = 0; i < size; i++) {
            int finalI = i;
            Iterator<Tile> tileIterator = tiles.stream()
                    .filter(tile -> tile.x == finalI)
                    .sorted(Comparator.comparingInt(tile -> ((Tile) tile).y).reversed())
                    .iterator();
            Optional<Tile> currentElement = tileIterator.hasNext() ? Optional.of(tileIterator.next()) : Optional.empty();
            Optional<Tile> nextElement = tileIterator.hasNext() ? Optional.of(tileIterator.next()) : Optional.empty();
            currentElement.ifPresent(currentTile -> {
                currentTile.y = maxCoord;
                nextElement.ifPresent(nextTile -> {
                    if(Objects.equals(nextTile.value, currentTile.value)){
                        currentTile.value *= 2;
                        tiles.remove(nextTile);
                    }
                });
            });
        }
        tiles.forEach(tile -> tile.y = maxCoord);
    }

    public void moveLeft() {
        tiles.forEach(tile -> tile.y = 0);
    }

    public void moveUp() {
        tiles.forEach(tile -> tile.x = 0);
    }

    public void moveDown() {
        tiles.forEach(tile -> tile.x = maxCoord);
    }
}
