package fr.insarouen.iti.compilation.sgbd.conditions;

import fr.insarouen.iti.compilation.sgbd.exceptions.ColonneInexistantException;
import fr.insarouen.iti.compilation.sgbd.moteur.Tuple;
import fr.insarouen.iti.compilation.sgbd.moteur.valeur.Valeur;

public class OperandeColonne extends Operande {
    private String identifiant;

    public OperandeColonne(String prefix, String attribut) {
        this(String.format("%s.%s", prefix, attribut));
    }

    public OperandeColonne(String identifiant) {
        this.identifiant = identifiant;
    }

    @Override
    public Valeur evaluer(Tuple tuple) {
        if (!tuple.hasIdentifiant(this.identifiant)) {
            throw new ColonneInexistantException(String.format(
                "Attribut '%s' inexistant dans la relation", this.identifiant));
        }
        return tuple.getValeur(this.identifiant);
    }
}
