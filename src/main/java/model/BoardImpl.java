package model;

import java.util.ArrayList;
import java.util.List;

public class BoardImpl {
    private int height;
    private int width;
    private int[][] board;
    //private List<CellImpl> piece = new ArrayList<CellImpl>();

    public BoardImpl() {
        height = 20;
        width = 20;
        board = new int[height][width];
    }

    public boolean hasPiece(int height, int width) {
        if(board[height][width] == 0) {
            return false;
        }
        return true;
    }

    public boolean hasColor(int height, int width, int color) {
        if(board[height][width] == color) {
            return true;
        }
        return false;
    }

    public void toggleCell(int height, int width, int color) {
        if(hasPiece(height, width)) {
            if(hasColor(height, width, color)) {
                board[height][width] = 0;
                //piece.remove();
            }
        }
        else {
            board[height][width] = color;
        }
        return;
    }

    public void checkWinner() {
        //not sure yet whether to check from board or remaining pieces
        return;
    }
}
