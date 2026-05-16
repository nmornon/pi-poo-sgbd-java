package fr.insarouen.iti.compilation.sgbd.conditions;

import fr.insarouen.iti.compilation.sgbd.exceptions.AttributInexistantException;
import fr.insarouen.iti.compilation.sgbd.moteur.Tuple;
import fr.insarouen.iti.compilation.sgbd.moteur.Valeur;

public class OperandeAttribut extends Operande {
    private String identifiant;

    public OperandeAttribut(String prefix, String attribut) {
        this(String.format("%s.%s", prefix, attribut));
    }

    public OperandeAttribut(String identifiant) {
        this.identifiant = identifiant;
    }

    @Override
    public Valeur evaluer(Tuple tuple) {
        if (!tuple.hasIdentifiant(this.identifiant)) {
            throw new AttributInexistantException(String.format(
                "Attribut '%s' inexistant dans la relation", this.identifiant));
        }
        return tuple.getValeur(this.identifiant);
    }
}
