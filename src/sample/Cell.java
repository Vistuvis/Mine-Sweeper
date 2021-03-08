package sample;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Cell extends Button {


    State state;
    boolean mine = false;
    ImageView
            imageCovered = new ImageView(new Image("sample/res/cover.png")),
            imageHitBomb = new ImageView(new Image("sample/res/mine-red.png")),
            imageEmpty = new ImageView(new Image("sample/res/0.png")),
            imageFlagged = new ImageView(new Image("sample/res/flag.png")),
            imageMine = new ImageView(new Image("sample/res/mine2.png")),
            imageOne = new ImageView(new Image("sample/res/1.png")),
            imageTwo = new ImageView(new Image("sample/res/2.png")),
            imageThree = new ImageView(new Image("sample/res/3.png")),
            imageFour = new ImageView(new Image("sample/res/4.png")),
            imageFive = new ImageView(new Image("sample/res/5.png")),
            imageSix = new ImageView(new Image("sample/res/6.png")),
            imageMisFlagged = new ImageView(new Image("sample/res/mine-misflagged.png"));


    static int totalMines;

    public Cell() {
        state = State.COVERED;
        double size = 30;

        setMinWidth(size);
        setMaxWidth(size);
        setMinHeight(size);
        setMaxHeight(size);


        setGraphic(imageCovered);

    }

    public Cell(boolean mine) {
        state = State.COVERED;
        double size = 30;
        this.mine = mine;

        setMinWidth(size);
        setMaxWidth(size);
        setMinHeight(size);
        setMaxHeight(size);
        setGraphic(imageCovered);

    }

    public void clearMine() {
        this.mine = false;
    }

    public boolean getMine() {
        return mine;
    }

    public void setMine() {
        this.mine = true;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
        switch (state) {
            case MINE:
                setGraphic(imageMine);
                break;
            case FLAGGED:
                setGraphic(imageFlagged);
                break;
            case COVERED:
                setGraphic(imageCovered);
                break;
            case HITMINE:
                setGraphic(imageHitBomb);
                break;
            case MISFLAGGED:
                setGraphic(imageMisFlagged);
                break;

        }
    }

    public void setState(int mines) {

        switch (mines) {
            case 0:
                setGraphic(imageEmpty);
                this.state = State.EMPTY;
                break;
            case 1:
                setGraphic((imageOne));
                this.state = State.ONE;
                break;
            case 2:
                setGraphic(imageTwo);
                this.state = State.TWO;
                break;
            case 3:
                setGraphic(imageThree);
                this.state = State.THREE;
                break;
            case 4:
                setGraphic(imageFour);
                this.state = State.FOUR;
                break;
            case 5:
                setGraphic(imageFive);
                this.state = State.FIVE;
                break;
            case 6:
                setGraphic(imageSix);
                this.state = State.SIX;
                break;
            case 7:
                setGraphic(imageSix);
                this.state = State.SEVEN;
                break;
            case 8:
                setGraphic(imageSix);
                this.state = State.EIGHT;
                break;

        }
    }

}

enum State {EMPTY, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, COVERED, FLAGGED, MINE, HITMINE, MISFLAGGED}