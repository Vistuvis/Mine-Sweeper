package sample;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Face extends Button {

    ImageView
            smile = new ImageView(new Image("sample/res/smile.png")),
            cool = new ImageView(new Image("sample/res/cool.png")),
            dead = new ImageView(new Image("sample/res/dead.png"));

    public Face() {
        double size = 50;

        setMinWidth(size);
        setMaxWidth(size);
        setMinHeight(size);
        setMaxHeight(size);

        setGraphic(smile);

    }

    public void win() {
        setGraphic(cool);
    }

    public void lose() {
        setGraphic(dead);
    }

    public void reset(CellManager c) {
        c.reset();
        setGraphic(smile);
    }


}
