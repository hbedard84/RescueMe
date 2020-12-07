import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Views/animalAdoptionView.fxml"));
        Scene scene = new Scene(root);
        //add custom css styling to scene
        scene.getStylesheets().add("CSS/style.css");
        stage.setTitle("Rescue Me - Animal Adoption Finder");
        // change default icon to paw print image
        stage.getIcons().add(new Image("Images/paw.png"));
        //block the ability to maximize the scene
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
