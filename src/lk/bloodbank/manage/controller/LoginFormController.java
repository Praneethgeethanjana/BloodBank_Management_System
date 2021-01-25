package lk.bloodbank.manage.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.regex.Pattern;

public class LoginFormController {
    public JFXTextField txtUname;
    public JFXTextField txtPassword;
    public ImageView root;
    public JFXPasswordField pwdPassword;

    public void loginOnAction(ActionEvent actionEvent) throws IOException {

      //  if (Pattern.compile("^[A-z]{1,}$").matcher(txtUname.getText()).matches()) {
        //    if (Pattern.compile("^[0-9]{1,}$").matcher(txtPassword.getText()).matches()) {

        String username=txtUname.getText().trim();
        String password=pwdPassword.getText().trim();

        if (username.length()>0 && password.length()>0){

            if (username.equalsIgnoreCase("ijse")
                    && password.equalsIgnoreCase("1234")){


               URL resource = this.getClass().
                        getResource("/lk/bloodbank/manage/view/MainForm.fxml");
                Parent load = FXMLLoader.load(resource);// ALt + Enter
               Scene scene= new Scene(load);
              /*  Stage stage=(Stage) root.getScene().getWindow();
                stage.getScene(new Scene(FXMLLoader.load(this.getClass().getResource("/lk/bloodbank/manage/view/MainForm.fxml"))));*/
                Stage stage= new Stage();
                stage.setScene(scene);
                stage.show();
            }else{
                new Alert(Alert.AlertType.WARNING,"Try Again !!!!",
                        ButtonType.OK,ButtonType.NO).show();
            }
        }else{
            new Alert(Alert.AlertType.WARNING,"Empty !!!!",
                    ButtonType.OK,ButtonType.NO).show();
        }


        //   Stage stage = (Stage) root.getScene().getWindow();
       //  stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/lk/bloodbank/manage/view/MainForm.fxml"))));


    }
}
