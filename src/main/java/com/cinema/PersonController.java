package com.cinema;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
public class PersonController {
    @FXML
    private Label id_user;

    @FXML
    private Label nameofuser;

    @FXML
    private Label secondnameofuser;

    @FXML
    private Label ageofuser;
    private Parent root;
    private Stage stage;
    private Scene scene;
    @FXML
    private TableView<Tickets> ticketstable;

    @FXML
    private TableColumn<Tickets,Integer> tickets_id;

    @FXML
    private TableColumn<Tickets,String> nameofmovie;

    @FXML
    private TableColumn<Tickets,String> nameofhall;

    @FXML
    private TableColumn<Tickets,String> dateofmovie;

    @FXML
    private TableColumn<Tickets,String> oclockofmovie;

    @FXML
    private TableColumn<Tickets,Integer> numberofplace;

    @FXML
    private TableColumn<Tickets,String> conditionofplace;
    @FXML
    private TableColumn<Tickets,Integer> row_of_hall;

    @FXML
    private HBox buybackbox;
    @FXML
    private Button returnbtn;
    @FXML
    private Button changeablebtn;
    @FXML
    private ImageView personimg;

    DatabaseHandler databaseHandler = new DatabaseHandler();
    private  ArrayList<Integer> status = new ArrayList<>();
    private ObservableList<Tickets> observableList = FXCollections.observableArrayList();
    private Tickets tickets =null;


    public void SwitchToAfisha(ActionEvent event) {
        try {
            root = FXMLLoader.load(PersonController.class.getResource("post.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    @FXML
    private void refreshTable() {
        observableList.clear();
        int number_of_place=0;
        int row_of_cinema_hall=0;

        String url = "SELECT * FROM tickets WHERE iduser=?;";
        try (PreparedStatement preparedStatement = databaseHandler.getToBD().prepareStatement(url)) {
            preparedStatement.setInt(1, HelloController.id_user);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int condition = resultSet.getInt("conditionofplace");
                int number=resultSet.getInt("numberofplace")+1;
                String con_ofplace = "";
                status.add(condition);
                if (condition == 1) {

                    con_ofplace = "??????????????????????????";

                }
                if (condition == 2) {

                    con_ofplace = "????????????????";

                }
                if(number<=6)
                {
                    row_of_cinema_hall=1;
                    number_of_place=number%6;
                    if (number==6)
                    {
                        number_of_place=6;
                    }

                }
                else if(number<=12)
                {
                    row_of_cinema_hall=2;
                    number_of_place=number%6;
                    if (number==12)
                    {
                        number_of_place=6;
                    }

                }
                else if(number<=18)
                {
                    row_of_cinema_hall=3;
                    number_of_place=number%6;
                    if (number==18)
                    {
                        number_of_place=6;
                    }

                }
                else if(number<=24)
                {
                    row_of_cinema_hall=4;
                    number_of_place=number%6;
                    if (number==24)
                    {
                        number_of_place=6;
                    }

                }
                else if(number<=30)
                {
                    row_of_cinema_hall=5;
                    number_of_place=number%6;
                    if (number==30)
                    {
                        number_of_place=6;
                    }

                }


                observableList.add(new Tickets(resultSet.getInt("idtickets"),
                        resultSet.getString("nameofmovie"), resultSet.getString("hallname"),
                        resultSet.getString("dateofmovie"), resultSet.getString("seanstime"),row_of_cinema_hall,
                        number_of_place, con_ofplace));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ticketstable.setItems(observableList);
    }
    public void initData() {
        String url = "SELECT*FROM tickets WHERE iduser=?;";
        int number_of_place=0;
        int row_of_cinema_hall=0;

        try (PreparedStatement preparedStatement = databaseHandler.getToBD().prepareStatement(url)) {
            preparedStatement.setInt(1, HelloController.id_user);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int condition = resultSet.getInt("conditionofplace");
                int number=resultSet.getInt("numberofplace")+1;
                String con_ofplace = "";
                status.add(condition);
                if (condition == 1) {

                    con_ofplace = "??????????????????????????";

                }
                if (condition == 2) {

                    con_ofplace = "????????????????";

                }
                if(number<=6)
                {
                    row_of_cinema_hall=1;
                    number_of_place=number%6;
                    if (number==6)
                    {
                        number_of_place=6;
                    }

                }
                else if(number<=12)
                {
                    row_of_cinema_hall=2;
                    number_of_place=number%6;
                    if (number==12)
                    {
                        number_of_place=6;
                    }

                }
                else if(number<=18)
                {
                    row_of_cinema_hall=3;
                    number_of_place=number%6;
                    if (number==18)
                    {
                        number_of_place=6;
                    }

                }
                else if(number<=24)
                {
                    row_of_cinema_hall=4;
                    number_of_place=number%6;
                    if (number==24)
                    {
                        number_of_place=6;
                    }

                }
                else if(number<=30)
                {
                    row_of_cinema_hall=5;
                    number_of_place=number%6;
                    if (number==30)
                    {
                        number_of_place=6;
                    }

                }


                observableList.add(new Tickets(resultSet.getInt("idtickets"),
                        resultSet.getString("nameofmovie"), resultSet.getString("hallname"),
                        resultSet.getString("dateofmovie"), resultSet.getString("seanstime"),row_of_cinema_hall,
                        number_of_place, con_ofplace));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ticketstable.setItems(observableList);
        }
        @FXML
        private void setBuybackbox(MouseEvent event) {
            tickets = ticketstable.getSelectionModel().getSelectedItem();
            if (tickets == null) {
                buybackbox.setVisible(false);
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("???? ???????????? ?????????? ????????");
                alert.setContentText("?????????????????????? ???? ??????????????????");
                alert.showAndWait();
            }
            if (!(tickets == null)) {
                if (tickets.getCondition_of_place().equals("????????????????")) {
                    String ticketsdate = tickets.getData() + " " + tickets.getOclock();
                    Date nowdate = new Date();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
                    try {
                        if (simpleDateFormat.parse(ticketsdate).getTime() - nowdate.getTime() <= (2 * 60 * 60 * 1000)) {
                            returnbtn.setVisible(false);
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setContentText("?????????????????????? ?? ?????????????????? ??????????????");
                            alert.setHeaderText("???? ???????????? ???????????????????? ?????????? ?????? ?????? ???????????? , ???????? ???? ???? ?????????????? ?????????????????? ????????????");
                        }
                        if (nowdate.getTime() - simpleDateFormat.parse(ticketsdate).getTime() >= (4 * 30 * 60 * 1000)) {
                            returnbtn.setVisible(false);
                            changeablebtn.setVisible(false);
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setHeaderText("???????????? ???????????????????? , ?????? ???? ?????????????? ?????? ???????????? ?????????? ?????????????? ????????????");
                            alert.setContentText("?????????????????????? ???? ???????????????????? ????????????????");
                            alert.showAndWait();
                            String url = "DELETE FROM tickets WHERE idtickets=?";
                            try (PreparedStatement preparedStatement = databaseHandler.getToBD().prepareStatement(url)) {
                                preparedStatement.setInt(1, tickets.getId_tickets());
                                preparedStatement.execute();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            refreshTable();
                        }
                    } catch (ParseException e) {
                        System.out.println("????????????????");
                        e.printStackTrace();
                    }
                    returnbtn.setVisible(true);
                    changeablebtn.setVisible(true);
                    buybackbox.setVisible(true);
                    returnbtn.setText("?????????????????? ????????????");
                    changeablebtn.setText("??????????????????????");
                    returnbtn.setOnAction(event1 ->
                    {
                        String url = "DELETE FROM tickets WHERE idtickets=?";
                        try (PreparedStatement preparedStatement = databaseHandler.getToBD().prepareStatement(url)) {
                            preparedStatement.setInt(1, tickets.getId_tickets());
                            preparedStatement.execute();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        refreshTable();
                        buybackbox.setVisible(false);
                    });
                    changeablebtn.setOnAction(event1 -> {
                        FileWriter data = null;
                        try {
                            data = new FileWriter("ticket.txt");
                            data.write("??? ????????????: " + tickets.getId_tickets() + "\n" + "?????????? ????????????: " + tickets.getName_of_movie() +
                                    "\n" + "???????? ????????????: " + tickets.getData() + "\n" + "?????? ?????????????? ????????????: " + tickets.getOclock() + "\n" + "?????????? ????????: " +
                                    tickets.getHall_name() + "\n" +"?????????? ????????: "+tickets.getRow_of_place()+ "\n"+"?????????? ??????????: " + tickets.getNum_of_place() + "\n");
                            data.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                        buybackbox.setVisible(false);
                    });
                }
                if (tickets.getCondition_of_place() == "??????????????????????????") {
                    String ticketsdate = tickets.getData() + " " + tickets.getOclock();
                    Date nowdate = new Date();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
                    try {
                        if (simpleDateFormat.parse(ticketsdate).getTime() - nowdate.getTime() <= (30 * 60 * 1000)) {
                            returnbtn.setVisible(false);
                            changeablebtn.setVisible(false);
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setContentText("?????????????????????? ???? ???????????????????? ????????????????");
                            alert.setContentText("???????? ?????????? ????????????????????");
                            alert.showAndWait();
                            String url = "DELETE FROM tickets WHERE idtickets=?;";
                            try (PreparedStatement preparedStatement = databaseHandler.getToBD().prepareStatement(url)) {
                                preparedStatement.setInt(1, tickets.getId_tickets());
                                preparedStatement.execute();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            refreshTable();
                        }
                    } catch (ParseException e) {
                        System.out.println("????????????????");
                        e.printStackTrace();
                    }
                    returnbtn.setVisible(true);
                    changeablebtn.setVisible(true);
                    buybackbox.setVisible(true);
                    returnbtn.setText("?????????????????? ??????????");
                    changeablebtn.setText("????????????");
                    returnbtn.setOnAction(event1 ->
                    {
                        int id = tickets.getId_tickets();
                        String url = "DELETE FROM tickets WHERE idtickets=?";
                        try (PreparedStatement preparedStatement = databaseHandler.getToBD().prepareStatement(url)) {
                            preparedStatement.setInt(1, id);
                            preparedStatement.execute();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        refreshTable();
                        buybackbox.setVisible(false);
                    });
                    changeablebtn.setOnAction(event2 ->
                    {
                        int id = tickets.getId_tickets();
                        String url1 = "UPDATE tickets SET conditionofplace=2 WHERE idtickets=?";
                        try (PreparedStatement preparedStatement1 = databaseHandler.getToBD().prepareStatement(url1)) {
                            preparedStatement1.setInt(1, id);
                            preparedStatement1.executeUpdate();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        refreshTable();
                        buybackbox.setVisible(false);
                    });

                }
            }
        }
        @FXML
        void exitfromaccount(MouseEvent mouseEvent) {
            HelloController.getConnection=0;
            HelloController.admin=0;
            try {
                root = FXMLLoader.load(PersonController.class.getResource("post.fxml"));
                stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    @FXML
    void initialize()
    {
        personimg.setImage(databaseHandler.download_picture(2));

        initData();
        tickets_id.setCellValueFactory(new PropertyValueFactory<>("id_tickets"));
        nameofmovie.setCellValueFactory(new PropertyValueFactory<>("name_of_movie"));
        nameofhall.setCellValueFactory(new PropertyValueFactory<>("hall_name"));
        dateofmovie.setCellValueFactory(new PropertyValueFactory<>("data"));
        oclockofmovie.setCellValueFactory(new PropertyValueFactory<>("oclock"));
        numberofplace.setCellValueFactory(new PropertyValueFactory<>("num_of_place"));
        conditionofplace.setCellValueFactory(new PropertyValueFactory<>("condition_of_place"));
        row_of_hall.setCellValueFactory(new PropertyValueFactory<>("row_of_place"));



        String url="SELECT*FROM users WHERE email =?;";
        try(PreparedStatement person_st=databaseHandler.getToBD().prepareStatement(url))
        {
            person_st.setString(1,HelloController.email_user);
            ResultSet person_resset=person_st.executeQuery();
            while (person_resset.next())
            {
                this.id_user.setText(String.valueOf(person_resset.getString("idusers")));
                this.nameofuser.setText(person_resset.getString("firstname"));
                this.secondnameofuser.setText(person_resset.getString("lastname"));
                this.ageofuser.setText(String.valueOf(person_resset.getInt("age")));
                HelloController.age=person_resset.getInt("age");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }
}
