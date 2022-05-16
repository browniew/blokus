package model;

public class CellImpl {
    private int height;
    private int width;

    public CellImpl(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public int getCellHeight() {
        return height;
    }

    public int getCellWidth() {
        return width;
    }
}
