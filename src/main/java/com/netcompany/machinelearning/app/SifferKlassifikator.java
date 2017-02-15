package com.netcompany.machinelearning.app;

import java.io.File;

class SifferKlassifikator {

    SifferKlassifikator() {

    }

    final Integer prediker(final File bildefil) {

        /*TODO Oppgave 5? Bytt ut den heller dårlige random-klassifikatoren under.

        Hint: Tren et nevralt nettverk å få modellen inn her på en eller annen måte.
        Hint: Valgt fil er tilgjengelig i valgtFil
        Hint: DataHjelper har en bildeTilIntArray-metode som sannsynligvis vil være til hjelp*/

        return (Integer) (int) (Math.random() * 10);
    }
}

