import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import java.io.File;
import javafx.scene.paint.Color;

public class MainController {

	@FXML
	private RadioButton rbDistance;
	@FXML
	private RadioButton rbTime;
	@FXML
	private RadioButton rbBoth;

	@FXML
	private TextField tfStart;
	@FXML
	private TextField tfEnd;

	@FXML
	private TextArea output;
	@FXML
	private Pane drawPane;

	private final ToggleGroup group = new ToggleGroup();
	private final FileLoaderTwoPass loader = new FileLoaderTwoPass();

	@FXML
	public void initialize() {
		rbDistance.setToggleGroup(group);
		rbTime.setToggleGroup(group);
		rbBoth.setToggleGroup(group);
		rbDistance.setSelected(true);
	}

	@FXML
	public void chooseFile() {
		try {
			FileChooser fc = new FileChooser();
			File file = fc.showOpenDialog(null);
			if (file == null)
				return;

			loader.load(file);

			tfStart.setText(loader.start);
			tfEnd.setText(loader.end);

			if (loader.option == 1)
				rbDistance.setSelected(true);
			else if (loader.option == 2)
				rbTime.setSelected(true);
			else
				rbBoth.setSelected(true);

			output.setText("File loaded successfully.\n");

		} catch (Exception e) {
			output.setText("Error reading file\n" + e.getMessage());
		}
	}

	@FXML
	public void runAlgorithm() {

		if (loader.graph == null) {
			output.setText("Please load file first.");
			return;
		}

		int s = loader.graph.vertices.binarySearch(tfStart.getText().trim());
		int d = loader.graph.vertices.binarySearch(tfEnd.getText().trim());

		if (s == -1 || d == -1) {
			output.setText("Start or End not found in graph.");
			return;
		}

		StringBuilder text = new StringBuilder();

		// ===== DISTANCE ONLY =====
		if (rbDistance.isSelected()) {

			Dijkstra dij = new Dijkstra();
			dij.run(loader.graph, s, 1);

			MyArrayList<String> path = PathUtil.buildPath(loader.graph, dij, d);

			drawPane.getChildren().clear();
			PathDrawer.draw(drawPane, path, Color.DODGERBLUE, 0);

			text.append("=== DISTANCE ===\n");
			text.append("Distance Cost = ").append(dij.dist[d]).append("\n");
			text.append("Path: ");
			appendPath(text, path);
		}

		// ===== TIME ONLY =====
		else if (rbTime.isSelected()) {

			Dijkstra dij = new Dijkstra();
			dij.run(loader.graph, s, 2);

			MyArrayList<String> path = PathUtil.buildPath(loader.graph, dij, d);

			drawPane.getChildren().clear();
			PathDrawer.draw(drawPane, path, Color.CRIMSON, 0);

			text.append("=== TIME ===\n");
			text.append("Time Cost = ").append(dij.dist[d]).append("\n");
			text.append("Path: ");
			appendPath(text, path);
		}

		// ===== BOTH =====
		else {

			drawPane.getChildren().clear();

			// Distance
			Dijkstra dijDist = new Dijkstra();
			dijDist.run(loader.graph, s, 1);
			MyArrayList<String> distPath = PathUtil.buildPath(loader.graph, dijDist, d);

			// Time
			Dijkstra dijTime = new Dijkstra();
			dijTime.run(loader.graph, s, 2);
			MyArrayList<String> timePath = PathUtil.buildPath(loader.graph, dijTime, d);

			PathDrawer.draw(drawPane, distPath, Color.DODGERBLUE, -30);

			PathDrawer.draw(drawPane, timePath, Color.CRIMSON, +30);

			text.append("=== DISTANCE ===\n");
			text.append("Distance Cost = ").append(dijDist.dist[d]).append("\n");
			text.append("Path: ");
			appendPath(text, distPath);

			text.append("\n=== TIME ===\n");
			text.append("Time Cost = ").append(dijTime.dist[d]).append("\n");
			text.append("Path: ");
			appendPath(text, timePath);
		}

		output.setText(text.toString());
	}

	private void appendPath(StringBuilder sb, MyArrayList<String> path) {
		for (int i = 0; i < path.size(); i++) {
			sb.append(path.get(i));
			if (i < path.size() - 1)
				sb.append(" -> ");
		}
		sb.append("\n");
	}
}
