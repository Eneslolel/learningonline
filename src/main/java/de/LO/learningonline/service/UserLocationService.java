package de.LO.learningonline.service;

import org.springframework.stereotype.Service;

/**
 * Liefert die Entfernung (in Kilometern) zwischen dem angemeldeten Nutzer
 * (z.B. über seine bei uns hinterlegte Stadt/PLZ) und dem Prüfungsort.
 * <p>
 * Aktuell als Platzhalter: liefert immer 0.0.
 */
@Service
public class UserLocationService {

    /**
     * Berechnet die Distanz des Users (per E-Mail-Identifier) zum
     * Prüfungsort mit der gegebenen ID.
     *
     * @param userEmail die E-Mail des aktuell eingeloggten Nutzers
     * @param ortId     die ID des Prüfungsorts
     * @return Distanz in Kilometern (hier: immer 0.0)
     */
    public double distance(String userEmail, Long ortId) {
        // TODO: hier kann man später echte Geo-Daten abfragen
        return 0.0;
    }
}
