import org.knowm.xchart.*;
import org.knowm.xchart.style.markers.SeriesMarkers;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class KnnAccuracyPlot {

    public static void plotAccuracy(Map<Integer, Double> accuracyByK) {
        List<Integer> kValues = new ArrayList<>(accuracyByK.keySet());
        List<Double> accuracies = new ArrayList<>(accuracyByK.values());

        XYChart chart = new XYChartBuilder()
                .width(800).height(600)
                .title("kNN Accuracy")
                .xAxisTitle("k")
                .yAxisTitle("Accuracy")
                .build();

        chart.getStyler().setMarkerSize(4);
        XYSeries series = chart.addSeries("Accuracy", kValues, accuracies);
        series.setMarker(SeriesMarkers.CIRCLE);

        new SwingWrapper(chart).displayChart();
    }

}
