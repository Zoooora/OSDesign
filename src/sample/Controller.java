package sample;

import Multi_threading.Server;
import javafx.event.ActionEvent;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.awt.*;


public class Controller {
    public TextField totalTime;
    public TextField arrInterval;
    public TextField passingTime;
    public GridPane root;
    public int TotalTime, ArrInterval, PassingTime;

    public void confirm(ActionEvent actionEvent) throws Exception{
        if(totalTime.getText().isEmpty() || arrInterval.getText().isEmpty() || passingTime.getText().isEmpty()) {
            new Alert(Alert.AlertType.NONE, "请输入正整数", new ButtonType[]{ButtonType.OK}).show();
            totalTime.clear();
            arrInterval.clear();
            passingTime.clear();
            return ;
        }
        TotalTime = new inputController().digit(totalTime);
        ArrInterval = new inputController().digit(arrInterval);
        PassingTime = new inputController().digit(passingTime);
        if(TotalTime == -1 || ArrInterval == -1 || PassingTime == -1) return;
        else{
            Server server = new Server();
            server.start_up(ArrInterval, PassingTime, TotalTime);
            root.getScene().getWindow().hide();
            Show show = new Show();
            show.newPage();

        }
    }
}
