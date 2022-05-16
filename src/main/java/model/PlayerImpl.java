package model;

public class PlayerImpl {
    private int piecesHeight;
    private int piecesWidth;
    private int color;
    private int[][] pieces;

    public PlayerImpl(int color) {
        piecesHeight = 13;
        piecesWidth = 21;
        this.color = color;
        pieces = new int[piecesHeight][piecesWidth];

        //first
        pieces[11][1] = color;

        //second
        pieces[11][3] = color;
        pieces[11][4] = color;

        //third
        pieces[11][6] = color;
        pieces[11][7] = color;
        pieces[11][8] = color;

        //fourth
        pieces[10][11] = color;
        pieces[11][10] = color;
        pieces[11][11] = color;

        //fifth
        pieces[5][19] = color;
        pieces[6][19] = color;
        pieces[7][19] = color;
        pieces[8][19] = color;

        //sixth
        pieces[10][19] = color;
        pieces[11][17] = color;
        pieces[11][18] = color;
        pieces[11][19] = color;

        //seventh
        pieces[7][17] = color;
        pieces[8][16] = color;
        pieces[8][17] = color;
        pieces[9][17] = color;

        //eighth
        pieces[10][13] = color;
        pieces[10][14] = color;
        pieces[11][13] = color;
        pieces[11][14] = color;

        //ninth
        pieces[6][14] = color;
        pieces[7][13] = color;
        pieces[7][14] = color;
        pieces[8][13] = color;

        //tenth
        pieces[1][1] = color;
        pieces[1][2] = color;
        pieces[1][3] = color;
        pieces[1][4] = color;
        pieces[1][5] = color;

        //eleventh
        pieces[3][1] = color;
        pieces[3][2] = color;
        pieces[3][3] = color;
        pieces[3][4] = color;
        pieces[4][4] = color;

        //twelfth
        pieces[5][1] = color;
        pieces[5][2] = color;
        pieces[6][2] = color;
        pieces[6][3] = color;
        pieces[6][4] = color;

        //thirteenth
        pieces[8][6] = color;
        pieces[8][7] = color;
        pieces[8][8] = color;
        pieces[9][6] = color;
        pieces[9][7] = color;

        //fourteenth
        pieces[5][6] = color;
        pieces[5][7] = color;
        pieces[5][8] = color;
        pieces[6][6] = color;
        pieces[6][8] = color;

        //fifteenth
        pieces[8][2] = color;
        pieces[9][1] = color;
        pieces[9][2] = color;
        pieces[9][3] = color;
        pieces[9][4] = color;

        //sixteenth
        pieces[5][10] = color;
        pieces[5][11] = color;
        pieces[5][12] = color;
        pieces[6][11] = color;
        pieces[7][11] = color;

        //seventeenth
        pieces[1][13] = color;
        pieces[1][14] = color;
        pieces[1][15] = color;
        pieces[2][13] = color;
        pieces[3][13] = color;

        //eighteenth
        pieces[1][17] = color;
        pieces[1][18] = color;
        pieces[2][18] = color;
        pieces[2][19] = color;
        pieces[3][19] = color;

        //nineteenth
        pieces[1][7] = color;
        pieces[1][8] = color;
        pieces[2][7] = color;
        pieces[3][6] = color;
        pieces[3][7] = color;

        //twentieth
        pieces[1][10] = color;
        pieces[2][10] = color;
        pieces[2][11] = color;
        pieces[3][9] = color;
        pieces[3][10] = color;

        //twenty-first
        pieces[3][16] = color;
        pieces[4][15] = color;
        pieces[4][16] = color;
        pieces[4][17] = color;
        pieces[5][16] = color;
    }

    public boolean hasPiece(int height, int width) {
        if(pieces[height][width] == 0) {
            return false;
        }
        return true;
    }

    public int checkPiece(int height, int width) {
        int piece = 0;
        switch(height) {
            case 1:
                switch(width) {
                    case 1: case 2: case 3: case 4: case 5:
                        piece = 10;
                        break;
                    case 7: case 8:
                        piece = 19;
                        break;
                    case 10:
                        piece = 20;
                        break;
                    case 13: case 14: case 15:
                        piece = 17;
                        break;
                    case 17: case 18:
                        piece = 18;
                        break;
                    default:
                        break;
                }
                break;
            case 2:
                switch(width) {
                    case 7:
                        piece = 19;
                        break;
                    case 10: case 11:
                        piece = 20;
                        break;
                    case 13:
                        piece = 17;
                        break;
                    case 18: case 19:
                        piece = 18;
                        break;
                    default:
                        break;
                }
                break;
            case 3:
                switch(width) {
                    case 1: case 2: case 3: case 4:
                        piece = 11;
                        break;
                    case 6: case 7:
                        piece = 19;
                        break;
                    case 9: case 10:
                        piece = 20;
                        break;
                    case 13:
                        piece = 17;
                        break;
                    case 16:
                        piece = 21;
                        break;
                    case 19:
                        piece = 18;
                        break;
                    default:
                        break;
                }
                break;
            case 4:
                switch(width) {
                    case 4:
                        piece = 11;
                        break;
                    case 15: case 16: case 17:
                        piece = 21;
                        break;
                    default:
                        break;
                }
                break;
            case 5:
                switch(width) {
                    case 1: case 2:
                        piece = 12;
                        break;
                    case 6: case 7: case 8:
                        piece = 14;
                        break;
                    case 10: case 11: case 12:
                        piece = 16;
                        break;
                    case 16:
                        piece = 21;
                        break;
                    case 19:
                        piece = 5;
                        break;
                    default:
                        break;
                }
                break;
            case 6:
                switch(width) {
                    case 2: case 3: case 4:
                        piece = 12;
                        break;
                    case 6: case 8:
                        piece = 14;
                        break;
                    case 11:
                        piece = 16;
                        break;
                    case 14:
                        piece = 9;
                        break;
                    case 19:
                        piece = 5;
                        break;
                    default:
                        break;
                }
                break;
            case 7:
                switch(width) {
                    case 11:
                        piece = 16;
                        break;
                    case 13: case 14:
                        piece = 9;
                        break;
                    case 17:
                        piece = 7;
                        break;
                    case 19:
                        piece = 5;
                        break;
                    default:
                        break;
                }
                break;
            case 8:
                switch(width) {
                    case 2:
                        piece = 15;
                        break;
                    case 6: case 7: case 8:
                        piece = 13;
                        break;
                    case 13:
                        piece = 9;
                        break;
                    case 16: case 17:
                        piece = 7;
                        break;
                    case 19:
                        piece = 5;
                        break;
                    default:
                        break;
                }
                break;
            case 9:
                switch(width) {
                    case 1: case 2: case 3: case 4:
                        piece = 15;
                        break;
                    case 6: case 7:
                        piece = 13;
                        break;
                    case 17:
                        piece = 7;
                        break;
                    default:
                        break;
                }
                break;
            case 10:
                switch(width) {
                    case 11:
                        piece = 4;
                        break;
                    case 13: case 14:
                        piece = 8;
                        break;
                    case 19:
                        piece = 6;
                        break;
                    default:
                        break;
                }
                break;
            case 11:
                switch(width) {
                    case 1:
                        piece = 1;
                        break;
                    case 3: case 4:
                        piece = 2;
                        break;
                    case 6: case 7: case 8:
                        piece = 3;
                        break;
                    case 10: case 11:
                        piece = 4;
                        break;
                    case 13: case 14:
                        piece = 8;
                        break;
                    case 17: case 18: case 19:
                        piece = 6;
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
        return piece;
    }

    public void clearPiece(int piece) {
        switch(piece) {
            case 1:
                pieces[11][1] = 0;
                break;
            case 2:
                pieces[11][3] = 0;
                pieces[11][4] = 0;
                break;
            case 3:
                pieces[11][6] = 0;
                pieces[11][7] = 0;
                pieces[11][8] = 0;
                break;
            case 4:
                pieces[10][11] = 0;
                pieces[11][10] = 0;
                pieces[11][11] = 0;
                break;
            case 5:
                pieces[5][19] = 0;
                pieces[6][19] = 0;
                pieces[7][19] = 0;
                pieces[8][19] = 0;
                break;
            case 6:
                pieces[10][19] = 0;
                pieces[11][17] = 0;
                pieces[11][18] = 0;
                pieces[11][19] = 0;
                break;
            case 7:
                pieces[7][17] = 0;
                pieces[8][16] = 0;
                pieces[8][17] = 0;
                pieces[9][17] = 0;
                break;
            case 8:
                pieces[10][13] = 0;
                pieces[10][14] = 0;
                pieces[11][13] = 0;
                pieces[11][14] = 0;
                break;
            case 9:
                pieces[6][14] = 0;
                pieces[7][13] = 0;
                pieces[7][14] = 0;
                pieces[8][13] = 0;
                break;
            case 10:
                pieces[1][1] = 0;
                pieces[1][2] = 0;
                pieces[1][3] = 0;
                pieces[1][4] = 0;
                pieces[1][5] = 0;
                break;
            case 11:
                pieces[3][1] = 0;
                pieces[3][2] = 0;
                pieces[3][3] = 0;
                pieces[3][4] = 0;
                pieces[4][4] = 0;
                break;
            case 12:
                pieces[5][1] = 0;
                pieces[5][2] = 0;
                pieces[6][2] = 0;
                pieces[6][3] = 0;
                pieces[6][4] = 0;
                break;
            case 13:
                pieces[8][6] = 0;
                pieces[8][7] = 0;
                pieces[8][8] = 0;
                pieces[9][6] = 0;
                pieces[9][7] = 0;
                break;
            case 14:
                pieces[5][6] = 0;
                pieces[5][7] = 0;
                pieces[5][8] = 0;
                pieces[6][6] = 0;
                pieces[6][8] = 0;
                break;
            case 15:
                pieces[8][2] = 0;
                pieces[9][1] = 0;
                pieces[9][2] = 0;
                pieces[9][3] = 0;
                pieces[9][4] = 0;
                break;
            case 16:
                pieces[5][10] = 0;
                pieces[5][11] = 0;
                pieces[5][12] = 0;
                pieces[6][11] = 0;
                pieces[7][11] = 0;
                break;
            case 17:
                pieces[1][13] = 0;
                pieces[1][14] = 0;
                pieces[1][15] = 0;
                pieces[2][13] = 0;
                pieces[3][13] = 0;
                break;
            case 18:
                pieces[1][17] = 0;
                pieces[1][18] = 0;
                pieces[2][18] = 0;
                pieces[2][19] = 0;
                pieces[3][19] = 0;
                break;
            case 19:
                pieces[1][7] = 0;
                pieces[1][8] = 0;
                pieces[2][7] = 0;
                pieces[3][6] = 0;
                pieces[3][7] = 0;
                break;
            case 20:
                pieces[1][10] = 0;
                pieces[2][10] = 0;
                pieces[2][11] = 0;
                pieces[3][9] = 0;
                pieces[3][10] = 0;
                break;
            case 21:
                pieces[3][16] = 0;
                pieces[4][15] = 0;
                pieces[4][16] = 0;
                pieces[4][17] = 0;
                pieces[5][16] = 0;
                break;
        }
        return;
    }
}
