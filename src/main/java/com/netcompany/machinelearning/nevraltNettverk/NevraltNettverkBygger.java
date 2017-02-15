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
        final NeuralNetConfiguration.ListBuilder lagliste = new NeuralNetConfiguration.Builder()
                // Bruker samme random-seed hver kjøring, slik at hver kjøring gir samme resultat (fint ved debugging)
                .seed(RAND_SEED)
                // Bruker algoritmen "stochastic gradient descent" for å optimalisere vekter
                .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
                // Antall interasjoner over hver batch (ikke tenk mer på det...)
                .iterations(1)
                // Setter læringsraten -> se medLaeringsrate
                .learningRate(laeringsRate)
                // Setter algoritmen for oppdatering av vektene i nettet
                .updater(Updater.NESTEROVS)
                // Momentum ved oppdatering - hvor mye sist oppdatering av vekt påvirker neste oppdatering (reduserer sjansen for oscillasjon)
                .momentum(0.9)
                // Taktikk for å øke nettets evne til å generalisere
                .regularization(true).l2(1e-4)
                .list();

        // Bygger Dl4j-lag basert på NNLag-klassen
        for (int i = 0; i < lag.size(); i++) {
            final boolean erOutputLag = i == lag.size() - 1;
            final NNLag nnLag = lag.get(i);
            lagliste.layer(
                    i, (erOutputLag ? new OutputLayer.Builder() : new DenseLayer.Builder())
                            // Setter antall koblinger inn i laget
                            .nIn(nnLag.INN)
                            // Setter antall koblinger ut av laget
                            .nOut(nnLag.UT)
                            // Bestemmer "activation function" til nodene i laget.
                            // Relu = rectified linear unit, brukes på de gjemte lagene
                            // Softmax brukes på siste lag -> gir en sannsynlighetsfordeling over de ulike klassene
                            .activation(erOutputLag ? "softmax" : "relu")
                            // Strategi for å initialisere vektene i nettet. Initialiseres disse med smarte verdier, vil nettet konvergere raskere
                            .weightInit(WeightInit.XAVIER)
                            .build());
        }
        lagliste.pretrain(false).backprop(true);

        return new NevraltNettverk(lagliste.build());
    }
}
