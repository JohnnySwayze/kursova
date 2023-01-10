package com.cinema;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;
public class AuthorizationController {
    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;
    private Stage stage;
    private Scene scene;
    private Parent root;

    private String name;
    private int id;
    DatabaseHandler handler =new DatabaseHandler();
    public void SwitchToRegistration(ActionEvent event) throws IOException {
        root = FXMLLoader.load(PostMenuController.class.getResource("registration.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void SwitchToAfisha(ActionEvent event) throws IOException {
        root = FXMLLoader.load(PostMenuController.class.getResource("post.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
    public void AuthorizationBtn (ActionEvent event) throws IOException {
        String emailText = login_field.getText().trim();
        String loginPassword = password_field.getText().trim();
        if (!emailText.equals("") && !loginPassword.equals("")) {
            loginUser(emailText, loginPassword);
            if (HelloController.getConnection == 1)
            {
                root = FXMLLoader.load(PostMenuController.class.getResource("person.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();
            }
            else
            {
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Користувача з такими данними не існує");
                alert.setContentText("Повернутися до авторизації");
                alert.showAndWait();
            }
        }
        else
        {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Ви не заповнили всі поля");
            alert.setContentText("Повернутися до авторизації");
            alert.showAndWait();
        }

    }
    private void loginUser(String emailText,String loginPassword) {

        String url1="SELECT * FROM users WHERE email=? and password = ? ;";
        try(PreparedStatement preparedStatement=handler.getToBD().prepareStatement(url1))
        {
            preparedStatement.setString(1,emailText);
            preparedStatement.setString(2,loginPassword);
            ResultSet resultSet=preparedStatement.executeQuery();
            int count=0;
            while(resultSet.next())
            {
                id=resultSet.getInt(1);
                name=resultSet.getString(2);


                count++;
                if (count>=1)
                {
                    if (name.equals("admin"))
                    {
                        HelloController.admin=1;
                    }
                    else
                    {
                        HelloController.admin=0;
                    }
                    HelloController.email_user=emailText;
                    HelloController.id_user=id;
                    HelloController.getConnection=1;

                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
