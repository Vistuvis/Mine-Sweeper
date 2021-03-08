package sample;

import java.util.Random;

public class CellManager {

    private int sizeX, sizeY, mines = 90;
    private Cell[][] cells;
    public boolean firstClick = false;

    public CellManager(int x, int y) {
        sizeX = x;
        sizeY = y;
        cells = new Cell[sizeX][sizeY];
        createNewGrid();

    }

    public boolean checkWin() {
        for (int row = 0; row < sizeX; row++) {
            for (int col = 0; col < sizeY; col++) {

                if (cells[row][col].state == State.COVERED || (!cells[row][col].getMine() && cells[row][col].getState() == State.FLAGGED)) {
                    return false;
                }

            }
        }
        return true;
    }

    public void reset() {
        for (int row = 0; row < sizeX; row++) {
            for (int col = 0; col < sizeY; col++) {
                cells[row][col].setState(State.COVERED);
                cells[row][col].clearMine();
            }
        }

        placeMines();
        firstClick = false;
        System.out.println("Resetting...");

    }

    private void clearMines() {
        for (int row = 0; row < sizeX; row++) {
            for (int col = 0; col < sizeY; col++) {
                cells[row][col].clearMine();
            }
        }

    }

    public Cell returnCell(int x, int y) {
        return cells[x][y];
    }

    public void setCell(int x, int y, State state) {

        cells[x][y].setState(state);
    }

    public void revealBombs() {
        for (int row = 0; row < sizeX; row++) {
            for (int col = 0; col < sizeY; col++) {
                if(misFlagged(row, col)){
                    cells[row][col].setState(State.MISFLAGGED);
                }
                else if (isBomb(row, col) && !(cells[row][col].getState() == State.FLAGGED)) {
                    cells[row][col].setState(State.MINE);
                }
            }
        }
    }

    public boolean isBomb(int x, int y) {
        return cells[x][y].getMine();
    }
    public boolean misFlagged(int x, int y){
        return cells[x][y].getState() == State.FLAGGED && !isBomb(x,y);
    }

    public void createNewGrid() {


        for (int row = 0; row < sizeX; row++) {
            for (int col = 0; col < sizeY; col++) {

                cells[row][col] = new Cell();
            }
        }
        //placeMines();
    }

    public void placeMines() {
        int temp = mines;

        Random rand = new Random();
        int x = rand.nextInt(sizeX);
        int y = rand.nextInt(sizeY);
        while (temp > 0) {
            if (cells[x][y].getMine()) {
                //  System.out.println("Mine here at: " + x + "," + y);
                x = rand.nextInt(sizeX);
                y = rand.nextInt(sizeY);
            } else {
                cells[x][y].setMine();
                temp--;
            }
        }
    }

    public boolean arrayOutFfBounds(int x, int y) {

        return x < 0 || y < 0 || x >= sizeX || y >= sizeY;

    }

    public int returnMines(int x, int y) {
        int num = 0;
        for (int startX = -1; startX <= 1; startX++) {
            for (int startY = -1; startY <= 1; startY++) {
                if (!arrayOutFfBounds(startX + x, startY + y) && cells[startX + x][startY + y].getMine()) {
                    num++;
                }
                //System.out.println(startX+x +"," +(startY+y));
            }
        }
        return num;
    }

    public void secondaryClick(int row, int col) {
        if (cells[row][col].getState() == State.COVERED) {
            cells[row][col].setState(State.FLAGGED);
        } else if (cells[row][col].getState() == State.FLAGGED) {
            cells[row][col].setState(State.COVERED);
        }
    }

    public void firstClick(int x, int y) {
        System.out.println("Calling first click");
        clearMines();
        placeMines();
        if (cells[x][y].getMine()) {
            firstClick(x, y);
        } else {
            firstClick = true;
        }
    }

    public void primaryClick(int row, int col) {

        if (!firstClick) {

            firstClick(row, col);
        }

        System.out.println("(" + row + "," + col + ")");
        if (arrayOutFfBounds(row, col)) return;
        if (cells[row][col].getState() == State.FLAGGED) return;
        if (cells[row][col].getState() == State.EMPTY) return;
        if (cells[row][col].getMine()) {
            System.out.println("Hit a bomb");
            return;
        }
        int mines = returnMines(row, col);
        cells[row][col].setState(mines);
        if (mines != 0) return;
        primaryClick(row - 1, col - 1);
        primaryClick(row - 1, col + 1);
        primaryClick(row + 1, col + 1);
        primaryClick(row + 1, col - 1);
        primaryClick(row + 1, col + 0);
        primaryClick(row - 1, col + 0);
        primaryClick(row + 0, col + 1);
        primaryClick(row + 0, col - 1);

        // System.out.println(row + "," + col);
        // System.out.println(mines);
    }

}
