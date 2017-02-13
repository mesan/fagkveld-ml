package com.netcompany.machinelearning.nevraltNettverk;

import com.google.common.collect.Lists;
import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.Updater;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.weights.WeightInit;

import java.util.List;

/**
 * User: Oystein Kvamme Repp
 * Date: 29.01.2017
 * Time: 20.59
 */
public class NevraltNettverkBygger {

    private static final Integer RAND_SEED = 42;

    private Double laeringsRate = 0.006;
    private final List<NNLag> lag = Lists.newArrayList();

    /**
     * Legger til et nytt lag i det nevrale nettet. Bygges fra toppen (fra "input layer") og ned (til "output layer").
     *
     * @param antallInn koblinger inn til laget (= antall koblinger ut fra laget over)
     * @param antallUt  koblinger ut fra laget (= antall koblinger inn i laget under)
     * @return {@link NevraltNettverkBygger}
     */
    public NevraltNettverkBygger leggTilLag(final Integer antallInn, final Integer antallUt) {
        lag.add(new NNLag(antallInn, antallUt));

        return this;
    }

    /**
     * Setter læringsraten ("learning rate") til nettverket. Dette styrer hvor mye vektene i nettet endres for hver
     * iterasjon (dvs. hvor mye det "straffes" ved feil og hvor mye det "belønnes" ved riktig prediksjon).
     * <br>
     * Tips1: let i intervallet (0, 1].
     * Tips2: går treningen sakte, prøv å øke
     * Tips3: divergerer nettet, prøv å senke
     *
     * @param laeringsRate positivt tall
     */
    public NevraltNettverkBygger medLaeringsrate(final Double laeringsRate) {
        this.laeringsRate = laeringsRate;

        return this;
    }

    /**
     * Bygger og returnerer en instans av {@link NevraltNettverk}.
     *
     * @return et nevralt nettverk
     */
    public NevraltNettverk bygg() {
        final NeuralNetConfiguration.ListBuilder lagListe = new NeuralNetConfiguration.Builder()
                .seed(RAND_SEED)
                // Bruker algoritmen "stochastic gradient descent" for å optimalisere vekter
                .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
                .iterations(1)
                .learningRate(laeringsRate)
                .updater(Updater.NESTEROVS).momentum(0.9)
                .regularization(true).l2(1e-4)
                .list();

        for (int i = 0; i < lag.size(); i++) {
            final boolean erOutputLag = i == lag.size() - 1;
            final NNLag nnLag = lag.get(i);
            lagListe.layer(
                    i, (erOutputLag ? new OutputLayer.Builder() : new DenseLayer.Builder())
                            .nIn(nnLag.INN)
                            .nOut(nnLag.UT)
                            .activation(erOutputLag ? "softmax" : "relu") // Sørger for softmax på output-lag
                            .weightInit(WeightInit.XAVIER)
                            .build());
        }
        lagListe.pretrain(false).backprop(true);

        return new NevraltNettverk(lagListe.build());
    }
}
