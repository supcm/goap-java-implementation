package ru.supcm.level;

public class Pos {
    int x, y;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int x() {
        return x;
    }
    public int y() {
        return y;
    }
    public void add(int x, int y) {
        this.x += x;
        this.y += y;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Pos pos)
            return pos.x == x && pos.y == y;
        return false;
    }
}
