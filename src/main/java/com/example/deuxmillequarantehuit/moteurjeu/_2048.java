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
            int yLim = maxCoord;
            Iterator<Tile> tileIterator = sortRowRightToLeft(rowIndex);
            Tile currentElement = getNextTile(tileIterator);
            while (currentElement != null) {
                currentElement.y = yLim;
                Tile nextElement = getNextTile(tileIterator);
                if (nextElement != null) {
                    if (nextElement.value == currentElement.value) {
                        currentElement.value *= 2;
                        tiles.remove(nextElement);
                        yLim--;
                    } else {
                        nextElement.y = currentElement.y - 1;
                        yLim -= 2;
                    }
                }
                currentElement = getNextTile(tileIterator);
            }
        }
    }

    private Tile getNextTile(Iterator<Tile> sortedTilesIterator) {
        return sortedTilesIterator.hasNext() ? sortedTilesIterator.next() : null;
    }

    private Iterator<Tile> sortRowRightToLeft(int rowIndex) {
        return tiles.stream()
                .filter(tile -> tile.x == rowIndex)
                .sorted(Comparator.comparingInt(tile -> ((Tile) tile).y).reversed())
                .iterator();
    }

    private Iterator<Tile> sortRowLeftToRight(int rowIndex) {
        Iterator<Tile> iterator = tiles.stream()
                .filter(tile -> tile.x == rowIndex)
                .sorted(Comparator.comparingInt(tile -> tile.y))
                .iterator();
        return iterator;
    }

    private Iterator<Tile> sortRowUpToDown(int colIndex) {
        Iterator<Tile> iterator = tiles.stream()
                .filter(tile -> tile.y == colIndex)
                .sorted(Comparator.comparingInt(tile -> tile.x))
                .iterator();
        return iterator;
    }

    public void moveLeft() {
        for (int rowIndex = 0; rowIndex < size; rowIndex++) {
            int yLim = 0;
            Iterator<Tile> tileIterator = sortRowLeftToRight(rowIndex);
            Tile currentElement = getNextTile(tileIterator);
            while (currentElement != null) {
                currentElement.y = yLim;
                Tile nextElement = getNextTile(tileIterator);
                if (nextElement != null) {
                    if (nextElement.value == currentElement.value) {
                        currentElement.value *= 2;
                        tiles.remove(nextElement);
                        yLim++;
                    } else {
                        nextElement.y = currentElement.y + 1;
                        yLim += 2;
                    }
                }
                currentElement = getNextTile(tileIterator);
            }
        }
    }

    public void moveUp() {
        for (int colIndex = 0; colIndex < size; colIndex++) {
            int xLim = 0;
            Iterator<Tile> tileIterator = sortRowUpToDown(colIndex);
            Tile currentElement = getNextTile(tileIterator);
            while (currentElement != null) {
                currentElement.x = xLim;
                Tile nextElement = getNextTile(tileIterator);
                if (nextElement != null) {
                    if (nextElement.value == currentElement.value) {
                        currentElement.value *= 2;
                        tiles.remove(nextElement);
                        xLim++;
                    } else {
                        nextElement.x = currentElement.x + 1;
                        xLim += 2;
                    }
                }
                currentElement = getNextTile(tileIterator);
            }
        }
    }

    public void moveDown() {
        tiles.forEach(tile -> tile.x = maxCoord);
    }
}
