module com.eric.four_player_pong {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.eric.four_player_pong to javafx.fxml;
    exports com.eric.four_player_pong;
}