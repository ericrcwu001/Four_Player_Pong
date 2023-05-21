module com.eric.four_player_pong {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.eric.four_player_pong to javafx.fxml;
    exports com.eric.four_player_pong;
//    exports com.eric.four_player_pong.sfx;
}