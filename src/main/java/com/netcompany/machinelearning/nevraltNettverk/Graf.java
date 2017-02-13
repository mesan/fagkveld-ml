package com.netcompany.machinelearning.nevraltNettverk;

import org.deeplearning4j.nn.api.Model;
import org.deeplearning4j.optimize.api.IterationListener;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * User: Oystein Kvamme Repp
 * Date: 06.02.2017
 * Time: 22.55
 */
class Graf extends ApplicationFrame implements IterationListener {
    private boolean invoked = false;
    private int iterasjonTeller = 0;
    private int plottFrekvens = 10;
    private final DefaultCategoryDataset grafData;

    Graf(final int plottFrekvens) {
        super("Swing baby Swing!");
        grafData = new DefaultCategoryDataset();
        this.plottFrekvens = plottFrekvens;
        final JFreeChart linjeDiagram = ChartFactory.createLineChart(
                "Feil per iterasjon",
                "Iterasjon",
                "Feil",
                grafData,
                PlotOrientation.VERTICAL,
                true, true, false);
        final ChartPanel panel = new ChartPanel(linjeDiagram);
        panel.setPreferredSize(new java.awt.Dimension(600, 500));
        setContentPane(panel);
        pack();
        RefineryUtilities.centerFrameOnScreen(this);
        setVisible(true);
    }

    @Override
    public boolean invoked() {
        return invoked;
    }

    @Override
    public void invoke() {
        this.invoked = true;
    }

    @Override
    public void iterationDone(final Model modell, final int iterasjon) {
        if (iterasjonTeller <= 0) {
            iterasjonTeller = 1;
        }
        if (iterasjonTeller % plottFrekvens == 0) {
            invoke();
            final double result = modell.score();
            grafData.addValue(result, "score", Integer.toString(iterasjonTeller));
        }
        iterasjonTeller++;
    }
}
