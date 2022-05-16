package model;

import java.util.ArrayList;
import java.util.List;

public class BoardImpl {
    private int height;
    private int width;
    private int[][] board;
    private List<CellImpl> piece = new ArrayList<CellImpl>();

    public BoardImpl() {
        height = 20;
        width = 20;
        board = new int[height][width];
    }

    public List<CellImpl> getPiece() {
        return piece;
    }

    public void clearPiece() {
        piece.clear();
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
        if(color >= 5) {
            if(height == 0 && width == 0) {
                if(hasColor(height + 1, width, color - 4) || hasColor(height, width + 1, color - 4)) {
                    return;
                }
            }
            else if(height == this.height - 1 && width == this.width - 1) {
                if(hasColor(height - 1, width, color - 4) || hasColor(height, width - 1, color - 4)) {
                    return;
                }
            }
            else if(height == 0 && width == this.width - 1) {
                if(hasColor(height + 1, width, color - 4) || hasColor(height, width - 1, color - 4)) {
                    return;
                }
            }
            else if(height == this.height - 1 && width == 0) {
                if(hasColor(height - 1, width, color - 4) || hasColor(height, width + 1, color - 4)) {
                    return;
                }
            }
            else if(height == 0) {
                if(hasColor(height + 1, width, color - 4) || hasColor(height, width + 1, color - 4) || hasColor(height, width - 1, color - 4)) {
                    return;
                }
            }
            else if(width == 0) {
                if(hasColor(height + 1, width, color - 4) || hasColor(height, width + 1, color - 4) || hasColor(height - 1, width, color - 4)) {
                    return;
                }
            }
            else if(height == this.height - 1) {
                if(hasColor(height - 1, width, color - 4) || hasColor(height, width - 1, color - 4) || hasColor(height, width + 1, color - 4)) {
                    return;
                }
            }
            else if(height == this.width - 1) {
                if(hasColor(height - 1, width, color - 4) || hasColor(height, width - 1, color - 4) || hasColor(height + 1, width, color - 4)) {
                    return;
                }
            }
            else {
                if(hasColor(height + 1, width, color - 4) || hasColor(height, width + 1, color - 4) || hasColor(height - 1, width, color - 4) || hasColor(height, width - 1, color - 4)) {
                    return;
                }
            }
        }
        if(hasPiece(height, width)) {
            if(hasColor(height, width, color)) {
                board[height][width] = 0;
                for(int i = 0; i < piece.size(); i++) {
                    if (piece.get(i).getCellHeight() == height) {
                        if(piece.get(i).getCellWidth() == width) {
                            piece.remove(i);
                            return;
                        }
                    }
                }
            }
        }
        else {
            if(piece.size() >= 5){
                return;
            }
            board[height][width] = color;
            CellImpl cell = new CellImpl(height, width);
            piece.add(cell);
        }
        return;
    }

    public void checkWinner() {
        //not sure yet whether to check from board or remaining pieces
        return;
    }
}
