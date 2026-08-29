import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainFX extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Scene scene = new Scene(FXMLLoader.load(getClass().getResource("main.fxml")));
		stage.setTitle("Dijkstra Project");
		stage.setScene(scene);
		stage.show();
	}
}
