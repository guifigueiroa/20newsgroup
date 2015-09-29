package each.ppgsi.plotting;

import javax.swing.JFrame;

import org.math.plot.Plot2DPanel;

public class PlotGraph {
	
	public static void main(String args[]){
		double[] x = { 10, 20, 40, 90 };
		double[] y = { 20, 10, 11, 48 };
		new PlotGraph().plot(x,y);
	}

	public void plot(double[] x, double[] y) {
		// create your PlotPanel (you can use it as a JPanel)
		Plot2DPanel plot = new Plot2DPanel();

		// add a line plot to the PlotPanel
		plot.addLinePlot("my plot", x, y);

		// put the PlotPanel in a JFrame, as a JPanel
		JFrame frame = new JFrame("a plot panel");
		frame.setContentPane(plot);
		frame.setBounds(200, 150, 700, 500);
		frame.setVisible(true);
	}
}
