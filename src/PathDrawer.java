import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class PathDrawer {

	public static void draw(Pane pane, MyArrayList<String> path, Color color, double yOffset) {

		if (path.size() == 0)
			return;

		double x = 40;
		double y = pane.getPrefHeight() / 2 + yOffset;

		Circle prev = null;

		for (int i = 0; i < path.size(); i++) {

			Circle c = new Circle(x, y, 10);
			c.setFill(color);

			Text t = new Text(x - 10, y - 20, path.get(i));

			pane.getChildren().addAll(c, t);

			if (prev != null) {
				Line line = new Line(prev.getCenterX(), prev.getCenterY(), c.getCenterX(), c.getCenterY());
				line.setStroke(color);
				pane.getChildren().add(line);
			}

			prev = c;
			x += 80;
		}
	}
}
