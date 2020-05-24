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
        for (int rowIndex = 0; rowIndex < size; rowIndex++) {
            int yMax = maxCoord;
            Iterator<Tile> tileIterator = sortRowRightToLeft(rowIndex);
            Tile currentElement = getNextTileRightToLeft(tileIterator);
            while (currentElement != null) {
                currentElement.y = yMax;
                Tile nextElement = getNextTileRightToLeft(tileIterator);
                if (nextElement != null) {
                    if (nextElement.value == currentElement.value) {
                        currentElement.value *= 2;
                        tiles.remove(nextElement);
                        yMax--;
                    } else {
                        nextElement.y = currentElement.y - 1;
                        yMax -= 2;
                    }
                }
                currentElement = getNextTileRightToLeft(tileIterator);
            }
        }
    }

    private Tile getNextTileRightToLeft(Iterator<Tile> sortedTilesIterator) {
        return sortedTilesIterator.hasNext() ? sortedTilesIterator.next() : null;
    }

    private Iterator<Tile> sortRowRightToLeft(int rowIndex) {
        return tiles.stream()
                .filter(tile -> tile.x == rowIndex)
                .sorted(Comparator.comparingInt(tile -> ((Tile) tile).y).reversed())
                .iterator();
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
