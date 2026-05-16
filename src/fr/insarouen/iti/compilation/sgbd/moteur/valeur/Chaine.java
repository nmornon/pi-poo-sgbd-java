package fr.insarouen.iti.compilation.sgbd.moteur.valeur;

import fr.insarouen.iti.compilation.sgbd.exceptions.TypeInvalideException;
import fr.insarouen.iti.compilation.sgbd.moteur.Type;

public class Chaine extends Valeur {
    public Chaine(String valeur) {
        super(Type.VARCHAR, valeur);
    }

    @Override
    public int compareTo(Valeur valeur) {
        if (!(valeur instanceof Chaine)) {
            throw new TypeInvalideException(String.format(
                "Comparaison impossible entre les valeurs '%s' et '%s',"
              + " le type '%s' n'est pas comparable avec le type '%s'",
                this.getValeur(), valeur.getValeur(),
                this.getType(), valeur.getType()));
        }
        String valeur1 = (String) this.getValeur();
        String valeur2 = (String) valeur.getValeur();
        return valeur1.compareTo(valeur2);
    }
}