package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class inputController {
    public int digit(TextField textField){
        int len, n, output = 0;
        boolean loop = true;
        String str = textField.getText();
        char[] input = str.toCharArray();
        len = input.length;
        n = 0;
        for (; n < input.length; n++) {
            if (input[n] == '-' || input[n] < 48 || input[n] > 57) {
                new Alert(Alert.AlertType.NONE, "请输入正整数", new ButtonType[]{ButtonType.OK}).show();
                textField.clear();
                return -1;
            }
        }
        for (n = 0, len -= 1; n <= len; n++) {
            output += ((input[n] - 48) * Math.pow(10, len - n));
        }
        return output;
    }
}
