package model;

import java.util.ArrayList;
import java.util.List;

public class BoardImpl {
    private int height;
    private int width;
    private int[][] board;
    private List<CellImpl> piece = new ArrayList<CellImpl>();
    private int isConnected;

    public BoardImpl() {
        height = 20;
        width = 20;
        isConnected = 0;
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
                            if(checkCorner(height, width, color)) {
                                isConnected--;
                            }
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
            if(checkCorner(height, width, color)) {
                isConnected++;
            }
        }
        return;
    }

    public boolean checkCorner(int height, int width, int color) {
        if(height == 0 && width == 0) {
            if(hasColor(height + 1, width + 1, color - 4)) {
                return true;
            }
        }
        else if(height == this.height - 1 && width == this.width - 1) {
            if(hasColor(height - 1, width - 1, color - 4)) {
                return true;
            }
        }
        else if(height == 0 && width == this.width - 1) {
            if(hasColor(height + 1, width - 1, color - 4)) {
                return true;
            }
        }
        else if(height == this.height - 1 && width == 0) {
            if(hasColor(height - 1, width + 1, color - 4)) {
                return true;
            }
        }
        else if(height == 0) {
            if(hasColor(height + 1, width + 1, color - 4) || hasColor(height + 1, width - 1, color - 4)) {
                return true;
            }
        }
        else if(width == 0) {
            if(hasColor(height + 1, width + 1, color - 4) || hasColor(height - 1, width + 1, color - 4)) {
                return true;
            }
        }
        else if(height == this.height - 1) {
            if(hasColor(height - 1, width + 1, color - 4) || hasColor(height - 1, width - 1, color - 4)) {
                return true;
            }
        }
        else if(height == this.width - 1) {
            if(hasColor(height - 1, width - 1, color - 4) || hasColor(height + 1, width - 1, color - 4)) {
                return true;
            }
        }
        else {
            if(hasColor(height + 1, width + 1, color - 4) || hasColor(height - 1, width + 1, color - 4) || hasColor(height - 1, width - 1, color - 4) || hasColor(height + 1, width - 1, color - 4)) {
                return true;
            }
        }
        return false;
    }

    public void changeColors() {
        for(CellImpl cell : piece) {
            board[cell.getCellHeight()][cell.getCellWidth()] = board[cell.getCellHeight()][cell.getCellWidth()] - 4;
        }
        piece.clear();
    }

    public boolean checkPiece(int no) {
        List<CellImpl> ordered = new ArrayList<CellImpl>();
        ordered.add(piece.get(0));
        boolean added = false;
        for(int i = 1; i < piece.size(); i++) {
            CellImpl cell1 = piece.get(i);
            for(int j = ordered.size() - 1; j >= 0; j--) {
                CellImpl cell2 = piece.get(j);
                if(cell2.getCellHeight() > cell1.getCellHeight()) {
                    ordered.add(j, cell1);
                    added = true;
                    break;
                }
                else if(cell2.getCellHeight() == cell1.getCellHeight()) {
                    if(cell2.getCellWidth() > cell1.getCellWidth()) {
                        ordered.add(j, cell1);
                        added = true;
                        break;
                    }
                    else {
                        continue;
                    }
                }
                else {
                    continue;
                }
            }
            if(!added) {
                ordered.add(cell1);
            }
            added = false;
        }
        if(no == 1) {
            if(ordered.size() == 1) {
                return true;
            }
        }
        else if(no == 2) {            //Should have used functions to check if something is one direction of another, ur dumb
            if(ordered.size() == 2) {
                CellImpl cell1 = ordered.get(0);
                CellImpl cell2 = ordered.get(1);
                if(cell2.getCellHeight() == cell1.getCellHeight() && cell2.getCellWidth() - 1 == cell1.getCellWidth()) {
                    return true;
                }
                else if(cell2.getCellHeight() - 1 == cell1.getCellHeight() && cell2.getCellWidth() == cell1.getCellWidth()) {
                    return true;
                }
            }
        }
        else if(no == 3) {
            if(ordered.size() == 3) {
                CellImpl cell1 = ordered.get(0);
                CellImpl cell2 = ordered.get(1);
                CellImpl cell3 = ordered.get(2);
                if(cell2.getCellHeight() == cell1.getCellHeight() && cell2.getCellWidth() - 1 == cell1.getCellWidth()) {
                    if(cell3.getCellHeight() == cell2.getCellHeight() && cell3.getCellWidth() - 1 == cell2.getCellWidth()) {
                        return true;
                    }
                }
                else if(cell2.getCellHeight() - 1 == cell1.getCellHeight() && cell2.getCellWidth() == cell1.getCellWidth()) {
                    if(cell3.getCellHeight() - 1 == cell2.getCellHeight() && cell3.getCellWidth() == cell2.getCellWidth()) {
                        return true;
                    }
                }
            }
        }
        else if(no == 4) {
            if(ordered.size() == 3) {
                CellImpl cell1 = ordered.get(0);
                CellImpl cell2 = ordered.get(1);
                CellImpl cell3 = ordered.get(2);
                if(cell2.getCellHeight() == cell1.getCellHeight() && cell2.getCellWidth() - 1 == cell1.getCellWidth()) {
                    if(cell3.getCellHeight() - 1 == cell1.getCellHeight() && cell3.getCellWidth() == cell1.getCellWidth()) {
                        return true;
                    }
                    else if(cell3.getCellHeight() - 1 == cell2.getCellHeight() && cell3.getCellWidth() == cell2.getCellWidth()) {
                        return true;
                    }
                }
                else if(cell2.getCellHeight() - 1 == cell1.getCellHeight() && cell2.getCellWidth() == cell1.getCellWidth()) {
                    if(cell3.getCellHeight() == cell2.getCellHeight() && cell3.getCellWidth() - 1 == cell2.getCellWidth()) {
                        return true;
                    }
                }
                else if(cell2.getCellHeight() - 1 == cell1.getCellHeight() && cell2.getCellWidth() + 1 == cell1.getCellWidth()) {
                    if(cell3.getCellHeight() - 1 == cell1.getCellHeight() && cell3.getCellWidth() == cell1.getCellWidth()) {
                        return true;
                    }
                }
            }
        }
        else if(no == 5) {
            if(ordered.size() == 4) {
                CellImpl cell1 = ordered.get(0);
                CellImpl cell2 = ordered.get(1);
                CellImpl cell3 = ordered.get(2);
                CellImpl cell4 = ordered.get(3);
                if(cell2.getCellHeight() == cell1.getCellHeight() && cell2.getCellWidth() - 1 == cell1.getCellWidth()) {
                    if(cell3.getCellHeight() == cell2.getCellHeight() && cell3.getCellWidth() - 1 == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() == cell3.getCellHeight() && cell4.getCellWidth() - 1 == cell3.getCellWidth()) {
                            return true;
                        }
                    }
                }
                else if(cell2.getCellHeight() - 1 == cell1.getCellHeight() && cell2.getCellWidth() == cell1.getCellWidth()) {
                    if(cell3.getCellHeight() - 1 == cell2.getCellHeight() && cell3.getCellWidth() == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() - 1 == cell3.getCellHeight() && cell4.getCellWidth() == cell3.getCellWidth()) {
                            return true;
                        }
                    }
                }
            }
        }
        else if(no == 6) {
            if(ordered.size() == 4) {
                CellImpl cell1 = ordered.get(0);
                CellImpl cell2 = ordered.get(1);
                CellImpl cell3 = ordered.get(2);
                CellImpl cell4 = ordered.get(3);
                if(cell2.getCellHeight() == cell1.getCellHeight() && cell2.getCellWidth() - 1 == cell1.getCellWidth()) {
                    if(cell3.getCellHeight() - 1 == cell1.getCellHeight() && cell3.getCellWidth() == cell1.getCellWidth()) {
                        if(cell4.getCellHeight() - 1 == cell3.getCellHeight() && cell4.getCellWidth() == cell3.getCellWidth()) {
                            return true;
                        }
                    }
                    else if(cell3.getCellHeight() - 1 == cell2.getCellHeight() && cell3.getCellWidth() == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() - 1 == cell3.getCellHeight() && cell4.getCellWidth() == cell3.getCellWidth()) {
                            return true;
                        }
                    }
                    else if(cell3.getCellHeight() == cell2.getCellHeight() && cell3.getCellWidth() - 1 == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() - 1 == cell1.getCellHeight() && cell4.getCellWidth() == cell1.getCellWidth()) {
                            return true;
                        }
                        else if(cell4.getCellHeight() - 1 == cell3.getCellHeight() && cell4.getCellWidth() == cell3.getCellWidth()) {
                            return true;
                        }
                    }
                }
                else if(cell2.getCellHeight() - 1 == cell1.getCellHeight() && cell2.getCellWidth() == cell1.getCellWidth()) {
                    if(cell3.getCellHeight() - 1 == cell2.getCellHeight() && cell3.getCellWidth() + 1 == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() == cell3.getCellHeight() && cell4.getCellWidth() - 1 == cell3.getCellWidth()) {
                            return true;
                        }
                    }
                    else if(cell3.getCellHeight() - 1 == cell2.getCellHeight() && cell3.getCellWidth() == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() == cell3.getCellHeight() && cell4.getCellWidth() - 1 == cell3.getCellWidth()) {
                            return true;
                        }
                    }
                    else if(cell3.getCellHeight() == cell2.getCellHeight() && cell3.getCellWidth() - 1 == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() == cell3.getCellHeight() && cell4.getCellWidth() - 1 == cell3.getCellWidth()) {
                            return true;
                        }
                    }
                }
                else if(cell2.getCellHeight() - 1 == cell1.getCellHeight() && cell2.getCellWidth() + 2 == cell1.getCellWidth()) {
                    if(cell3.getCellHeight() == cell2.getCellHeight() && cell3.getCellWidth() - 1 == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() == cell3.getCellHeight() && cell4.getCellWidth() - 1 == cell3.getCellWidth()) {
                            return true;
                        }
                    }
                }
            }
        }
        else if(no == 7) {
            if(ordered.size() == 4) {
                CellImpl cell1 = ordered.get(0);
                CellImpl cell2 = ordered.get(1);
                CellImpl cell3 = ordered.get(2);
                CellImpl cell4 = ordered.get(3);
                if(cell2.getCellHeight() - 1 == cell1.getCellHeight() && cell2.getCellWidth() + 1 == cell1.getCellWidth()) {
                    if(cell3.getCellHeight() - 1 == cell1.getCellHeight() && cell3.getCellWidth() == cell1.getCellWidth()) {
                        if(cell4.getCellHeight() == cell3.getCellHeight() && cell4.getCellWidth() - 1 == cell3.getCellWidth()) {
                            return true;
                        }
                        else if(cell4.getCellHeight() - 1 == cell3.getCellHeight() && cell4.getCellWidth() == cell3.getCellWidth()) {
                            return true;
                        }
                    }
                }
                else if(cell2.getCellHeight() - 1 == cell1.getCellHeight() && cell2.getCellWidth() == cell1.getCellWidth()) {
                    if(cell3.getCellHeight() == cell2.getCellHeight() && cell3.getCellWidth() - 1 == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() - 1 == cell2.getCellHeight() && cell4.getCellWidth() == cell2.getCellWidth()) {
                            return true;
                        }
                    }
                }
                else if(cell2.getCellHeight() == cell1.getCellHeight() && cell2.getCellWidth() - 1 == cell1.getCellWidth()) {
                    if(cell3.getCellHeight() == cell2.getCellHeight() && cell3.getCellWidth() - 1 == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() - 1 == cell2.getCellHeight() && cell4.getCellWidth() == cell2.getCellWidth()) {
                            return true;
                        }
                    }
                }
            }
        }
        else if(no == 8) {
            if(ordered.size() == 4) {
                CellImpl cell1 = ordered.get(0);
                CellImpl cell2 = ordered.get(1);
                CellImpl cell3 = ordered.get(2);
                CellImpl cell4 = ordered.get(3);
                if(cell2.getCellHeight() == cell1.getCellHeight() && cell2.getCellWidth() - 1 == cell1.getCellWidth()) {
                    if(cell3.getCellHeight() - 1 == cell1.getCellHeight() && cell3.getCellWidth() == cell1.getCellWidth()) {
                        if(cell4.getCellHeight() == cell3.getCellHeight() && cell4.getCellWidth() - 1 == cell3.getCellWidth()) {
                            return true;
                        }
                    }
                }
            }
        }
        else if(no == 9) {
            if(ordered.size() == 4) {
                CellImpl cell1 = ordered.get(0);
                CellImpl cell2 = ordered.get(1);
                CellImpl cell3 = ordered.get(2);
                CellImpl cell4 = ordered.get(3);
                if(cell2.getCellHeight() == cell1.getCellHeight() && cell2.getCellWidth() - 1 == cell1.getCellWidth()) {
                    if(cell3.getCellHeight() - 1 == cell2.getCellHeight() && cell3.getCellWidth() == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() == cell3.getCellHeight() && cell4.getCellWidth() - 1 == cell3.getCellWidth()) {
                            return true;
                        }
                    }
                    else if(cell3.getCellHeight() - 1 == cell1.getCellHeight() && cell3.getCellWidth() + 1 == cell1.getCellWidth()) {
                        if(cell4.getCellHeight() == cell3.getCellHeight() && cell4.getCellWidth() - 1 == cell3.getCellWidth()) {
                            return true;
                        }
                    }
                }
                else if(cell2.getCellHeight() - 1 == cell1.getCellHeight() && cell2.getCellWidth() == cell1.getCellWidth()) {
                    if(cell3.getCellHeight() == cell2.getCellHeight() && cell3.getCellWidth() - 1 == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() - 1 == cell3.getCellHeight() && cell4.getCellWidth() == cell3.getCellWidth()) {
                            return true;
                        }
                    }
                }
                else if(cell2.getCellHeight() - 1 == cell1.getCellHeight() && cell2.getCellWidth() + 1 == cell1.getCellWidth()) {
                    if(cell3.getCellHeight() == cell2.getCellHeight() && cell3.getCellWidth() - 1 == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() - 1 == cell2.getCellHeight() && cell4.getCellWidth() == cell2.getCellWidth()) {
                            return true;
                        }
                    }
                }
            }
        }
        else if(no == 10) {
            if(ordered.size() == 5) {
                CellImpl cell1 = ordered.get(0);
                CellImpl cell2 = ordered.get(1);
                CellImpl cell3 = ordered.get(2);
                CellImpl cell4 = ordered.get(3);
                CellImpl cell5 = ordered.get(4);
                if(cell2.getCellHeight() == cell1.getCellHeight() && cell2.getCellWidth() - 1 == cell1.getCellWidth()) {
                    if(cell3.getCellHeight() == cell2.getCellHeight() && cell3.getCellWidth() - 1 == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() == cell3.getCellHeight() && cell4.getCellWidth() - 1 == cell3.getCellWidth()) {
                            if(cell5.getCellHeight() == cell4.getCellHeight() && cell5.getCellWidth() - 1 == cell4.getCellWidth()) {
                                return true;
                            }
                        }
                    }
                }
                else if(cell2.getCellHeight() - 1 == cell1.getCellHeight() && cell2.getCellWidth() == cell1.getCellWidth()) {
                    if(cell3.getCellHeight() - 1 == cell2.getCellHeight() && cell3.getCellWidth() == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() - 1 == cell3.getCellHeight() && cell4.getCellWidth() == cell3.getCellWidth()) {
                            if(cell5.getCellHeight() - 1 == cell4.getCellHeight() && cell5.getCellWidth() == cell4.getCellWidth()) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        else if(no == 11) {
            if(ordered.size() == 5) {
                CellImpl cell1 = ordered.get(0);
                CellImpl cell2 = ordered.get(1);
                CellImpl cell3 = ordered.get(2);
                CellImpl cell4 = ordered.get(3);
                CellImpl cell5 = ordered.get(4);
                if(cell2.getCellHeight() == cell1.getCellHeight() && cell2.getCellWidth() - 1 == cell1.getCellWidth()) {
                    if(cell3.getCellHeight() - 1 == cell1.getCellHeight() && cell3.getCellWidth() == cell1.getCellWidth()) {
                        if(cell4.getCellHeight() - 1 == cell3.getCellHeight() && cell4.getCellWidth() == cell3.getCellWidth()) {
                            if(cell5.getCellHeight() - 1 == cell4.getCellHeight() && cell5.getCellWidth() == cell4.getCellWidth()) {
                                return true;
                            }
                        }
                    }
                    else if(cell3.getCellHeight() - 1 == cell2.getCellHeight() && cell3.getCellWidth() == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() - 1 == cell3.getCellHeight() && cell4.getCellWidth() == cell3.getCellWidth()) {
                            if(cell5.getCellHeight() - 1 == cell4.getCellHeight() && cell5.getCellWidth() == cell4.getCellWidth()) {
                                return true;
                            }
                        }
                    }
                    if(cell3.getCellHeight() == cell2.getCellHeight() && cell3.getCellWidth() - 1 == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() == cell3.getCellHeight() && cell4.getCellWidth() - 1 == cell3.getCellWidth()) {
                            if(cell5.getCellHeight() - 1 == cell4.getCellHeight() && cell5.getCellWidth() == cell4.getCellWidth()) {
                                return true;
                            }
                            else if(cell5.getCellHeight() - 1 == cell1.getCellHeight() && cell5.getCellWidth() == cell1.getCellWidth()) {
                                return true;
                            }
                        }
                    }
                }
                else if(cell2.getCellHeight() - 1 == cell1.getCellHeight() && cell2.getCellWidth() == cell1.getCellWidth()) {
                    if(cell3.getCellHeight() - 1 == cell2.getCellHeight() && cell3.getCellWidth() == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() - 1 == cell3.getCellHeight() && cell4.getCellWidth() == cell3.getCellWidth()) {
                            if(cell5.getCellHeight() == cell4.getCellHeight() && cell5.getCellWidth() - 1 == cell4.getCellWidth()) {
                                return true;
                            }
                        }
                        else if(cell4.getCellHeight() - 1 == cell3.getCellHeight() && cell4.getCellWidth() + 1 == cell3.getCellWidth()) {
                            if(cell5.getCellHeight() == cell4.getCellHeight() && cell5.getCellWidth() - 1 == cell4.getCellWidth()) {
                                return true;
                            }
                        }
                    }
                    else if(cell3.getCellHeight() == cell2.getCellHeight() && cell3.getCellWidth() - 1 == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() == cell3.getCellHeight() && cell4.getCellWidth() - 1 == cell3.getCellWidth()) {
                            if(cell5.getCellHeight() == cell4.getCellHeight() && cell5.getCellWidth() - 1 == cell4.getCellWidth()) {
                                return true;
                            }
                        }
                    }
                }
                else if(cell2.getCellHeight() - 1 == cell1.getCellHeight() && cell2.getCellWidth() + 3 == cell1.getCellWidth()) {
                    if(cell3.getCellHeight() == cell2.getCellHeight() && cell3.getCellWidth() - 1 == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() == cell3.getCellHeight() && cell4.getCellWidth() - 1 == cell3.getCellWidth()) {
                            if(cell5.getCellHeight() == cell4.getCellHeight() && cell5.getCellWidth() - 1 == cell4.getCellWidth()) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        else if(no == 12) {
            if(ordered.size() == 5) {
                CellImpl cell1 = ordered.get(0);
                CellImpl cell2 = ordered.get(1);
                CellImpl cell3 = ordered.get(2);
                CellImpl cell4 = ordered.get(3);
                CellImpl cell5 = ordered.get(4);
                if(cell2.getCellHeight() - 1 == cell1.getCellHeight() && cell2.getCellWidth() == cell1.getCellWidth()) {
                    if(cell3.getCellHeight() - 1 == cell2.getCellHeight() && cell3.getCellWidth() == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() == cell3.getCellHeight() && cell4.getCellWidth() - 1 == cell3.getCellWidth()) {
                            if(cell5.getCellHeight() - 1 == cell4.getCellHeight() && cell5.getCellWidth() == cell4.getCellWidth()) {
                                return true;
                            }
                        }
                    }
                    else if(cell3.getCellHeight() - 1 == cell2.getCellHeight() && cell3.getCellWidth() + 1 == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() == cell3.getCellHeight() && cell4.getCellWidth() - 1 == cell3.getCellWidth()) {
                            if(cell5.getCellHeight() - 1 == cell3.getCellHeight() && cell5.getCellWidth() == cell3.getCellWidth()) {
                                return true;
                            }
                        }
                    }
                    else if(cell3.getCellHeight() == cell2.getCellHeight() && cell3.getCellWidth() - 1 == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() - 1 == cell3.getCellHeight() && cell4.getCellWidth() == cell3.getCellWidth()) {
                            if(cell5.getCellHeight() - 1 == cell4.getCellHeight() && cell5.getCellWidth() == cell4.getCellWidth()) {
                                return true;
                            }
                        }
                    }
                }
                else if(cell2.getCellHeight() - 1 == cell1.getCellHeight() && cell2.getCellWidth() + 1 == cell1.getCellWidth()) {
                    if(cell3.getCellHeight() == cell2.getCellHeight() && cell3.getCellWidth() - 1 == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() - 1 == cell2.getCellHeight() && cell2.getCellWidth() == cell3.getCellWidth()) {
                            if(cell5.getCellHeight() - 1 == cell4.getCellHeight() && cell5.getCellWidth() == cell4.getCellWidth()) {
                                return true;
                            }
                        }
                    }
                }
                else if(cell2.getCellHeight() == cell1.getCellHeight() && cell2.getCellWidth() - 1 == cell1.getCellWidth()) {
                    if(cell3.getCellHeight() == cell2.getCellHeight() && cell3.getCellWidth() - 1 == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() - 1 == cell3.getCellHeight() && cell4.getCellWidth() == cell3.getCellWidth()) {
                            if(cell5.getCellHeight() == cell4.getCellHeight() && cell5.getCellWidth() - 1 == cell4.getCellWidth()) {
                                return true;
                            }
                        }
                        else if(cell4.getCellHeight() - 1 == cell1.getCellHeight() && cell4.getCellWidth() + 1 == cell1.getCellWidth()) {
                            if(cell5.getCellHeight() == cell4.getCellHeight() && cell5.getCellWidth() - 1 == cell4.getCellWidth()) {
                                return true;
                            }
                        }
                    }
                    else if(cell3.getCellHeight() - 1 == cell2.getCellHeight() && cell3.getCellWidth() == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() == cell3.getCellHeight() && cell4.getCellWidth() - 1 == cell3.getCellWidth()) {
                            if(cell5.getCellHeight() == cell4.getCellHeight() && cell5.getCellWidth() - 1 == cell4.getCellWidth()) {
                                return true;
                            }
                        }
                    }
                    else if(cell3.getCellHeight() - 1 == cell1.getCellHeight() && cell3.getCellWidth() + 2 == cell1.getCellWidth()) {
                        if(cell4.getCellHeight() == cell3.getCellHeight() && cell4.getCellWidth() - 1 == cell3.getCellWidth()) {
                            if(cell5.getCellHeight() == cell4.getCellHeight() && cell5.getCellWidth() - 1 == cell4.getCellWidth()) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        else if(no == 13) {
            if(ordered.size() == 5) {
                CellImpl cell1 = ordered.get(0);
                CellImpl cell2 = ordered.get(1);
                CellImpl cell3 = ordered.get(2);
                CellImpl cell4 = ordered.get(3);
                CellImpl cell5 = ordered.get(4);
                if(cell2.getCellHeight() == cell1.getCellHeight() && cell2.getCellWidth() - 1 == cell1.getCellWidth()) {
                    if(cell3.getCellHeight() - 1 == cell1.getCellHeight() && cell3.getCellWidth() == cell1.getCellWidth()) {
                        if(cell4.getCellHeight() == cell3.getCellHeight() && cell4.getCellWidth() - 1 == cell3.getCellWidth()) {
                            if(cell5.getCellHeight() - 1 == cell3.getCellHeight() && cell5.getCellWidth() == cell3.getCellWidth()) {
                                return true;
                            }
                            else if(cell5.getCellHeight() - 1 == cell4.getCellHeight() && cell5.getCellWidth() == cell4.getCellWidth()) {
                                return true;
                            }
                            else if(cell5.getCellHeight() == cell4.getCellHeight() && cell5.getCellWidth() - 1 == cell4.getCellWidth()) {
                                return true;
                            }
                        }
                    }
                    else if(cell3.getCellHeight() == cell2.getCellHeight() && cell3.getCellWidth() - 1 == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() - 1 == cell1.getCellHeight() && cell4.getCellWidth() == cell1.getCellWidth()) {
                            if(cell5.getCellHeight() == cell4.getCellHeight() && cell5.getCellWidth() - 1 == cell4.getCellWidth()) {
                                return true;
                            }
                        }
                        else if(cell4.getCellHeight() - 1 == cell2.getCellHeight() && cell4.getCellWidth() == cell2.getCellWidth()) {
                            if(cell5.getCellHeight() == cell4.getCellHeight() && cell5.getCellWidth() - 1 == cell4.getCellWidth()) {
                                return true;
                            }
                        }
                    }
                    else if(cell3.getCellHeight() - 1 == cell1.getCellHeight() && cell3.getCellWidth() + 1 == cell1.getCellWidth()) {
                        if(cell4.getCellHeight() == cell3.getCellHeight() && cell4.getCellWidth() - 1 == cell3.getCellWidth()) {
                            if(cell5.getCellHeight() == cell4.getCellHeight() && cell5.getCellWidth() - 1 == cell4.getCellWidth()) {
                                return true;
                            }
                        }
                    }
                }
                else if(cell2.getCellHeight() - 1 == cell1.getCellHeight() && cell2.getCellWidth() == cell1.getCellWidth()) {
                    if(cell3.getCellHeight() == cell2.getCellHeight() && cell3.getCellWidth() - 1 == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() - 1 == cell2.getCellHeight() && cell4.getCellWidth() == cell2.getCellWidth()) {
                            if(cell5.getCellHeight() - 1 == cell3.getCellHeight() && cell5.getCellWidth() == cell3.getCellWidth()) {
                                return true;
                            }
                        }
                    }
                }
                else if(cell2.getCellHeight() - 1 == cell1.getCellHeight() && cell2.getCellWidth() + 1 == cell1.getCellWidth()) {
                    if(cell3.getCellHeight() == cell2.getCellHeight() && cell3.getCellWidth() - 1 == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() - 1 == cell2.getCellHeight() && cell4.getCellWidth() == cell2.getCellWidth()) {
                            if(cell5.getCellHeight() - 1 == cell3.getCellHeight() && cell5.getCellWidth() == cell3.getCellWidth()) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        else if(no == 14) {
            if(ordered.size() == 5) {
                CellImpl cell1 = ordered.get(0);
                CellImpl cell2 = ordered.get(1);
                CellImpl cell3 = ordered.get(2);
                CellImpl cell4 = ordered.get(3);
                CellImpl cell5 = ordered.get(4);
                if(cell2.getCellHeight() == cell1.getCellHeight() && cell2.getCellWidth() - 1 == cell1.getCellWidth()) {
                    if(cell3.getCellHeight() - 1 == cell1.getCellHeight() && cell3.getCellWidth() == cell1.getCellWidth()) {
                        if(cell4.getCellHeight() - 1 == cell3.getCellHeight() && cell4.getCellWidth() == cell3.getCellWidth()) {
                            if(cell5.getCellHeight() == cell4.getCellHeight() && cell5.getCellWidth() - 1 == cell4.getCellWidth()) {
                                return true;
                            }
                        }
                    }
                    else if(cell3.getCellHeight() - 1 == cell2.getCellHeight() && cell3.getCellWidth() == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() - 1 == cell3.getCellHeight() && cell4.getCellWidth() + 1 == cell3.getCellWidth()) {
                            if(cell5.getCellHeight() == cell4.getCellHeight() && cell5.getCellWidth() - 1 == cell4.getCellWidth()) {
                                return true;
                            }
                        }
                    }
                    else if(cell3.getCellHeight() == cell2.getCellHeight() && cell3.getCellWidth() - 1 == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() - 1 == cell1.getCellHeight() && cell4.getCellWidth() == cell1.getCellWidth()) {
                            if(cell5.getCellHeight() - 1 == cell3.getCellHeight() && cell5.getCellWidth() == cell3.getCellWidth()) {
                                return true;
                            }
                        }
                    }
                }
                else if(cell2.getCellHeight() == cell1.getCellHeight() && cell2.getCellWidth() - 2 == cell1.getCellWidth()) {
                    if(cell3.getCellHeight() - 1 == cell1.getCellHeight() && cell3.getCellWidth() == cell1.getCellWidth()) {
                        if(cell4.getCellHeight() == cell3.getCellHeight() && cell4.getCellWidth() - 1 == cell3.getCellWidth()) {
                            if(cell5.getCellHeight() == cell4.getCellHeight() && cell5.getCellWidth() - 1 == cell4.getCellWidth()) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        else if(no == 15) {
            if(ordered.size() == 5) {
                CellImpl cell1 = ordered.get(0);
                CellImpl cell2 = ordered.get(1);
                CellImpl cell3 = ordered.get(2);
                CellImpl cell4 = ordered.get(3);
                CellImpl cell5 = ordered.get(4);
                if(cell2.getCellHeight() == cell1.getCellHeight() && cell2.getCellWidth() - 1 == cell1.getCellWidth()) {
                    if(cell3.getCellHeight() == cell2.getCellHeight() && cell3.getCellWidth() - 1 == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() == cell3.getCellHeight() && cell4.getCellWidth() - 1 == cell3.getCellWidth()) {
                            if(cell5.getCellHeight() - 1 == cell2.getCellHeight() && cell5.getCellWidth() == cell2.getCellWidth()) {
                                return true;
                            }
                            else if(cell5.getCellHeight() - 1 == cell3.getCellHeight() && cell5.getCellWidth() == cell3.getCellWidth()) {
                                return true;
                            }
                        }
                    }
                }
                else if(cell2.getCellHeight() - 1 == cell1.getCellHeight() && cell2.getCellWidth() == cell1.getCellWidth()) {
                    if(cell3.getCellHeight() - 1 == cell2.getCellHeight() && cell3.getCellWidth() == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() == cell3.getCellHeight() && cell4.getCellWidth() - 1 == cell3.getCellWidth()) {
                            if(cell5.getCellHeight() - 1 == cell3.getCellHeight() && cell5.getCellWidth() == cell3.getCellWidth()) {
                                return true;
                            }
                        }
                    }
                    else if(cell3.getCellHeight() == cell2.getCellHeight() && cell3.getCellWidth() - 1 == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() - 1 == cell2.getCellHeight() && cell4.getCellWidth() == cell2.getCellWidth()) {
                            if(cell5.getCellHeight() - 1 == cell4.getCellHeight() && cell5.getCellWidth() == cell4.getCellWidth()) {
                                return true;
                            }
                        }
                    }
                    else if(cell3.getCellHeight() - 1 == cell2.getCellHeight() && cell3.getCellWidth() + 1 == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() == cell3.getCellHeight() && cell4.getCellWidth() - 1 == cell3.getCellWidth()) {
                            if(cell5.getCellHeight() - 1 == cell4.getCellHeight() && cell5.getCellWidth() == cell4.getCellWidth()) {
                                return true;
                            }
                        }
                    }
                }
                else if(cell2.getCellHeight() - 1 == cell1.getCellHeight() && cell2.getCellWidth() + 1 == cell1.getCellWidth()) {
                    if(cell3.getCellHeight() == cell2.getCellHeight() && cell3.getCellWidth() - 1 == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() - 1 == cell3.getCellHeight() && cell4.getCellWidth() == cell3.getCellWidth()) {
                            if(cell5.getCellHeight() - 1 == cell4.getCellHeight() && cell5.getCellWidth() == cell4.getCellWidth()) {
                                return true;
                            }
                        }
                        else if(cell4.getCellHeight() == cell3.getCellHeight() && cell4.getCellWidth() - 1 == cell3.getCellWidth()) {
                            if(cell5.getCellHeight() == cell4.getCellHeight() && cell5.getCellWidth() - 1 == cell4.getCellWidth()) {
                                return true;
                            }
                        }
                    }
                }
                else if(cell2.getCellHeight() - 1 == cell1.getCellHeight() && cell2.getCellWidth() + 2 == cell1.getCellWidth()) {
                    if(cell3.getCellHeight() == cell2.getCellHeight() && cell3.getCellWidth() - 1 == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() == cell3.getCellHeight() && cell4.getCellWidth() - 1 == cell3.getCellWidth()) {
                            if(cell5.getCellHeight() == cell4.getCellHeight() && cell5.getCellWidth() - 1 == cell4.getCellWidth()) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        else if(no == 16) {
            if(ordered.size() == 5) {
                CellImpl cell1 = ordered.get(0);
                CellImpl cell2 = ordered.get(1);
                CellImpl cell3 = ordered.get(2);
                CellImpl cell4 = ordered.get(3);
                CellImpl cell5 = ordered.get(4);
                if(cell2.getCellHeight() - 1 == cell1.getCellHeight() && cell2.getCellWidth() == cell1.getCellWidth()) {
                    if(cell3.getCellHeight() == cell2.getCellHeight() && cell3.getCellWidth() - 1 == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() == cell3.getCellHeight() && cell4.getCellWidth() - 1 == cell3.getCellWidth()) {
                            if(cell5.getCellHeight() - 1 == cell2.getCellHeight() && cell5.getCellWidth() == cell2.getCellWidth()) {
                                return true;
                            }
                        }
                    }
                    else if(cell3.getCellHeight() - 1 == cell2.getCellHeight() && cell3.getCellWidth() + 1 == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() == cell3.getCellHeight() && cell4.getCellWidth() - 1 == cell3.getCellWidth()) {
                            if(cell5.getCellHeight() == cell4.getCellHeight() && cell5.getCellWidth() - 1 == cell4.getCellWidth()) {
                                return true;
                            }
                        }
                    }
                }
                else if(cell2.getCellHeight() == cell1.getCellHeight() && cell2.getCellWidth() - 1 == cell1.getCellWidth()) {
                    if(cell3.getCellHeight() == cell2.getCellHeight() && cell3.getCellWidth() - 1 == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() - 1 == cell2.getCellHeight() && cell4.getCellWidth() == cell2.getCellWidth()) {
                            if(cell5.getCellHeight() - 1 == cell4.getCellHeight() && cell5.getCellWidth() == cell4.getCellWidth()) {
                                return true;
                            }
                        }
                    }
                }
                else if(cell2.getCellHeight() - 1 == cell1.getCellHeight() && cell2.getCellWidth() + 2 == cell1.getCellWidth()) {
                    if(cell3.getCellHeight() == cell2.getCellHeight() && cell3.getCellWidth() - 1 == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() == cell3.getCellHeight() && cell4.getCellWidth() - 1 == cell3.getCellWidth()) {
                            if(cell5.getCellHeight() - 1 == cell4.getCellHeight() && cell5.getCellWidth() == cell4.getCellWidth()) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        else if(no == 17) {
            if(ordered.size() == 5) {
                CellImpl cell1 = ordered.get(0);
                CellImpl cell2 = ordered.get(1);
                CellImpl cell3 = ordered.get(2);
                CellImpl cell4 = ordered.get(3);
                CellImpl cell5 = ordered.get(4);
                if(cell2.getCellHeight() == cell1.getCellHeight() && cell2.getCellWidth() - 1 == cell1.getCellWidth()) {
                    if(cell3.getCellHeight() == cell2.getCellHeight() && cell3.getCellWidth() - 1 == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() - 1 == cell3.getCellHeight() && cell4.getCellWidth() == cell3.getCellWidth()) {
                            if(cell5.getCellHeight() - 1 == cell4.getCellHeight() && cell5.getCellWidth() == cell4.getCellWidth()) {
                                return true;
                            }
                        }
                        else if(cell4.getCellHeight() - 1 == cell1.getCellHeight() && cell4.getCellWidth() == cell1.getCellWidth()) {
                            if(cell5.getCellHeight() - 1 == cell4.getCellHeight() && cell5.getCellWidth() == cell4.getCellWidth()) {
                                return true;
                            }
                        }
                    }
                }
                else if(cell2.getCellHeight() - 1 == cell1.getCellHeight() && cell2.getCellWidth() == cell1.getCellWidth()) {
                    if(cell3.getCellHeight() - 1 == cell2.getCellHeight() && cell3.getCellWidth() == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() == cell3.getCellHeight() && cell4.getCellWidth() - 1 == cell3.getCellWidth()) {
                            if(cell5.getCellHeight() == cell4.getCellHeight() && cell5.getCellWidth() - 1 == cell4.getCellWidth()) {
                                return true;
                            }
                        }
                    }
                    else if(cell3.getCellHeight() - 1 == cell2.getCellHeight() && cell3.getCellWidth() + 2 == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() == cell3.getCellHeight() && cell4.getCellWidth() - 1 == cell3.getCellWidth()) {
                            if(cell5.getCellHeight() == cell4.getCellHeight() && cell5.getCellWidth() - 1 == cell4.getCellWidth()) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        else if(no == 18) {
            if(ordered.size() == 5) {
                CellImpl cell1 = ordered.get(0);
                CellImpl cell2 = ordered.get(1);
                CellImpl cell3 = ordered.get(2);
                CellImpl cell4 = ordered.get(3);
                CellImpl cell5 = ordered.get(4);
                if(cell2.getCellHeight() == cell1.getCellHeight() && cell2.getCellWidth() - 1 == cell1.getCellWidth()) {
                    if(cell3.getCellHeight() - 1 == cell2.getCellHeight() && cell3.getCellWidth() == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() == cell3.getCellHeight() && cell4.getCellWidth() - 1 == cell3.getCellWidth()) {
                            if(cell5.getCellHeight() - 1 == cell4.getCellHeight() && cell5.getCellWidth() == cell4.getCellWidth()) {
                                return true;
                            }
                        }
                    }
                    else if(cell3.getCellHeight() - 1 == cell1.getCellHeight() && cell3.getCellWidth() + 1 == cell1.getCellWidth()) {
                        if(cell4.getCellHeight() == cell3.getCellHeight() && cell4.getCellWidth() - 1 == cell3.getCellWidth()) {
                            if(cell5.getCellHeight() - 1 == cell3.getCellHeight() && cell5.getCellWidth() == cell3.getCellWidth()) {
                                return true;
                            }
                        }
                    }
                }
                else if(cell2.getCellHeight() - 1 == cell1.getCellHeight() && cell2.getCellWidth() == cell1.getCellWidth()) {
                    if(cell3.getCellHeight() == cell2.getCellHeight() && cell3.getCellWidth() - 1 == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() - 1 == cell3.getCellHeight() && cell4.getCellWidth() == cell3.getCellWidth()) {
                            if(cell5.getCellHeight() == cell4.getCellHeight() && cell5.getCellWidth() - 1 == cell4.getCellWidth()) {
                                return true;
                            }
                        }
                    }
                }
                else if(cell2.getCellHeight() - 1 == cell1.getCellHeight() && cell2.getCellWidth() + 1 == cell1.getCellWidth()) {
                    if(cell3.getCellHeight() == cell2.getCellHeight() && cell3.getCellWidth() - 1 == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() - 1 == cell2.getCellHeight() && cell4.getCellWidth() + 1 == cell3.getCellWidth()) {
                            if(cell5.getCellHeight() == cell4.getCellHeight() && cell5.getCellWidth() - 1 == cell4.getCellWidth()) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        else if(no == 19) {
            if(ordered.size() == 5) {
                CellImpl cell1 = ordered.get(0);
                CellImpl cell2 = ordered.get(1);
                CellImpl cell3 = ordered.get(2);
                CellImpl cell4 = ordered.get(3);
                CellImpl cell5 = ordered.get(4);
                if(cell2.getCellHeight() == cell1.getCellHeight() && cell2.getCellWidth() - 1 == cell1.getCellWidth()) {
                    if(cell3.getCellHeight() - 1 == cell2.getCellHeight() && cell3.getCellWidth() == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() - 1 == cell3.getCellHeight() && cell4.getCellWidth() == cell3.getCellWidth()) {
                            if(cell5.getCellHeight() == cell4.getCellHeight() && cell5.getCellWidth() - 1 == cell4.getCellWidth()) {
                                return true;
                            }
                        }
                    }
                    else if(cell3.getCellHeight() - 1 == cell1.getCellHeight() && cell3.getCellWidth() == cell1.getCellWidth()) {
                        if(cell4.getCellHeight() - 1 == cell3.getCellHeight() && cell4.getCellWidth() + 1 == cell3.getCellWidth()) {
                            if(cell5.getCellHeight() == cell4.getCellHeight() && cell5.getCellWidth() - 1 == cell4.getCellWidth()) {
                                return true;
                            }
                        }
                    }
                }
                else if(cell2.getCellHeight() - 1 == cell1.getCellHeight() && cell2.getCellWidth() == cell1.getCellWidth()) {
                    if(cell3.getCellHeight() == cell2.getCellHeight() && cell3.getCellWidth() - 1 == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() == cell3.getCellHeight() && cell4.getCellWidth() - 1 == cell3.getCellWidth()) {
                            if(cell5.getCellHeight() - 1 == cell4.getCellHeight() && cell5.getCellWidth() == cell4.getCellWidth()) {
                                return true;
                            }
                        }
                    }
                }
                else if(cell2.getCellHeight() - 1 == cell1.getCellHeight() && cell2.getCellWidth() + 2 == cell1.getCellWidth()) {
                    if(cell3.getCellHeight() == cell2.getCellHeight() && cell3.getCellWidth() - 1 == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() == cell3.getCellHeight() && cell4.getCellWidth() - 1 == cell3.getCellWidth()) {
                            if(cell5.getCellHeight() - 1 == cell2.getCellHeight() && cell5.getCellWidth() == cell2.getCellWidth()) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        else if(no == 20) {
            if(ordered.size() == 5) {
                CellImpl cell1 = ordered.get(0);
                CellImpl cell2 = ordered.get(1);
                CellImpl cell3 = ordered.get(2);
                CellImpl cell4 = ordered.get(3);
                CellImpl cell5 = ordered.get(4);
                if(cell2.getCellHeight() - 1 == cell1.getCellHeight() && cell2.getCellWidth() == cell1.getCellWidth()) {
                    if(cell3.getCellHeight() == cell2.getCellHeight() && cell3.getCellWidth() - 1 == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() == cell3.getCellHeight() && cell4.getCellWidth() - 1 == cell3.getCellWidth()) {
                            if(cell5.getCellHeight() - 1 == cell3.getCellHeight() && cell5.getCellWidth() == cell3.getCellWidth()) {
                                return true;
                            }
                        }
                        else if(cell4.getCellHeight() - 1 == cell2.getCellHeight() && cell4.getCellWidth() + 1 == cell2.getCellWidth()) {
                            if(cell5.getCellHeight() == cell4.getCellHeight() && cell5.getCellWidth() - 1 == cell4.getCellWidth()) {
                                return true;
                            }
                        }
                    }
                }
                else if(cell2.getCellHeight() - 1 == cell1.getCellHeight() && cell2.getCellWidth() + 2 == cell1.getCellWidth()) {
                    if(cell3.getCellHeight() == cell2.getCellHeight() && cell3.getCellWidth() - 1 == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() == cell3.getCellHeight() && cell4.getCellWidth() - 1 == cell3.getCellWidth()) {
                            if(cell5.getCellHeight() - 1 == cell3.getCellHeight() && cell5.getCellWidth() == cell3.getCellWidth()) {
                                return true;
                            }
                        }
                    }
                }
                else if(cell2.getCellHeight() - 1 == cell1.getCellHeight() && cell2.getCellWidth() + 1 == cell1.getCellWidth()) {
                    if(cell3.getCellHeight() == cell2.getCellHeight() && cell3.getCellWidth() - 1 == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() == cell3.getCellHeight() && cell4.getCellWidth() - 1 == cell3.getCellWidth()) {
                            if(cell5.getCellHeight() - 1 == cell2.getCellHeight() && cell5.getCellWidth() == cell2.getCellWidth()) {
                                return true;
                            }
                            else if(cell5.getCellHeight() - 1 == cell4.getCellHeight() && cell5.getCellWidth() == cell4.getCellWidth()) {
                                return true;
                            }
                        }
                        else if(cell4.getCellHeight() - 1 == cell3.getCellHeight() && cell4.getCellWidth() == cell3.getCellWidth()) {
                            if(cell5.getCellHeight() == cell4.getCellHeight() && cell5.getCellWidth() - 1 == cell4.getCellWidth()) {
                                return true;
                            }
                        }
                    }
                }
                else if(cell2.getCellHeight() == cell1.getCellHeight() && cell2.getCellWidth() - 1 == cell1.getCellWidth()) {
                    if(cell3.getCellHeight() - 1 == cell2.getCellHeight() && cell3.getCellWidth() == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() == cell3.getCellHeight() && cell4.getCellWidth() - 1 == cell3.getCellWidth()) {
                            if(cell5.getCellHeight() - 1 == cell3.getCellHeight() && cell5.getCellWidth() == cell3.getCellWidth()) {
                                return true;
                            }
                        }
                    }
                    else if(cell3.getCellHeight() - 1 == cell1.getCellHeight() && cell3.getCellWidth() + 1 == cell1.getCellWidth()) {
                        if(cell4.getCellHeight() == cell3.getCellHeight() && cell4.getCellWidth() - 1 == cell3.getCellWidth()) {
                            if(cell5.getCellHeight() - 1 == cell4.getCellHeight() && cell5.getCellWidth() == cell4.getCellWidth()) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        else if(no == 21) {
            if(ordered.size() == 5) {
                CellImpl cell1 = ordered.get(0);
                CellImpl cell2 = ordered.get(1);
                CellImpl cell3 = ordered.get(2);
                CellImpl cell4 = ordered.get(3);
                CellImpl cell5 = ordered.get(4);
                if(cell2.getCellHeight() - 1 == cell1.getCellHeight() && cell2.getCellWidth() + 1 == cell1.getCellWidth()) {
                    if(cell3.getCellHeight() == cell2.getCellHeight() && cell3.getCellWidth() - 1 == cell2.getCellWidth()) {
                        if(cell4.getCellHeight() == cell3.getCellHeight() && cell4.getCellWidth() - 1 == cell3.getCellWidth()) {
                            if(cell5.getCellHeight() - 1 == cell3.getCellHeight() && cell5.getCellWidth() == cell3.getCellWidth()) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public void checkWinner() {
        //not sure yet whether to check from board or remaining pieces
        return;
    }
}
