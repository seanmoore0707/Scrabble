package com.view.table;

import com.Game;
import com.view.hall.HallController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author xuwang < xuwang2@student.unimelb.edu.au >
 * @id 979895
 * @date 2018/9/23 23:45
 */
public class ReadyController implements Initializable {
    @FXML private BorderPane borderPane;
    private double xOffset;
    private double yOffset;
    @FXML public Label title;
    @FXML private VBox vBox;
    @FXML private Button inviteBtn;
    private boolean toggle = true;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Drag and Drop animation
        //<editor-fold defaultstate="collapsed" desc=" Drag and Drop">
        borderPane.setOnMousePressed(event -> {
            xOffset = HallController.getStage().getX() - event.getScreenX();
            yOffset = HallController.getStage().getY() - event.getScreenY();
            borderPane.setCursor(Cursor.CLOSED_HAND);
        });

        borderPane.setOnMouseDragged(event -> {
            HallController.getStage().setX(event.getScreenX() + xOffset);
            HallController.getStage().setY(event.getScreenY() + yOffset);
            TableController.getInstance().getReadyStage().setX(event.getScreenX() + xOffset);
            TableController.getInstance().getReadyStage().setY(event.getScreenY() + yOffset);
        });

        borderPane.setOnMouseReleased(event -> {
            borderPane.setCursor(Cursor.DEFAULT);
        });
        //</editor-fold>
    }

    @FXML
    private void ready(){
        TableController.getInstance().getReadyStage().close();
        Game.ready();
        //TODO - send to server (playerAction = ready)
    }

    @FXML
    private void invite(){
        Game.invite();
        if (toggle){
            inviteBtn.setText("CANCEL");
            vBox.setPadding(new Insets(30,0,0,0));

            toggle = false;
        }else if (!toggle){
            vBox.setPadding(new Insets(300,0,0,0));
            inviteBtn.setText("INVITE");


            toggle = true;
        }

    }

    // return to game hall
    public void returnHall() {
        HallController.getStage().close();
        Game.getPrimaryStage().show();
        Game.returnToHall();
    }

    // Minimize Window
    public void minimizeWindow(){
        HallController.getStage().setIconified(true);
        TableController.getInstance().getReadyStage().setIconified(true);
    }
}
