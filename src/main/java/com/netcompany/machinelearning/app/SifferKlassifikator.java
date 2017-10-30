package com.netcompany.machinelearning.app;

import java.io.File;

class SifferKlassifikator {

    SifferKlassifikator() {

    }

    final Integer prediker(final File bildefil) {

        /* Oppgave 5: Bytt ut den heller dårlige random-klassifikatoren under.

        Hint: Tren et nevralt nettverk å få modellen inn her på en eller annen måte.
              Nettverket kan f.eks bygges og trenes i konstruktøren, eller legge inn alt i denne metoden.
              Bruk det du har gjort i oppg 1-4 her.

        Hint: DataHjelper har en bildeTilIntArray-metode som sannsynligvis vil være til hjelp.
              Bruk hjelpeklassen DataHjelper når du skal lese inn et bilde.
              Deretter flat ut og normaliser som gjort tidligere. Til slutt, prediker bilde du leste inn.

        */

        return (Integer) (int) (Math.random() * 10);
    }
}

