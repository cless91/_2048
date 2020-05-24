package com.example.deuxmillequarantehuit.moteurjeu;

public class Tile {
    public int x;
    public int y;
    public int value;

    private Tile(int x, int y) {
        this.x = x;
        this.y = y;
        value = 2;
    }

    private Tile(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public static Tile createTile2(int x, int y) {
        return new Tile(x, y);
    }

    public static Tile createTile(int x, int y, int value) {
        assert Utils.isPowerOfTwo(value);
        return new Tile(x, y, value);
    }

    @Override
    public String toString() {
        return "Tile{" +
                "x=" + x +
                ", y=" + y +
                ", value=" + value +
                '}';
    }
}
