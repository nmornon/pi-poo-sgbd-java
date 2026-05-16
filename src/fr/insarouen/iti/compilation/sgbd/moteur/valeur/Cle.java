package fr.insarouen.iti.compilation.sgbd.moteur.valeur;

import fr.insarouen.iti.compilation.sgbd.exceptions.TypeInvalideException;
import fr.insarouen.iti.compilation.sgbd.moteur.Type;

public class Cle extends Valeur {
    public Cle(int valeur) {
        super(Type.SERIAL, valeur);
    }

    @Override
    public int compareTo(Valeur valeur) {
        if (!(valeur instanceof Cle) && !(valeur instanceof Entier)) {
            throw new TypeInvalideException(String.format(
                "Comparaison impossible entre les valeurs '%s' et '%s',"
              + " le type '%s' n'est pas comparable avec le type '%s'",
                this.getValeur(), valeur.getValeur(),
                this.getType(), valeur.getType()));
        }
        Integer valeur1 = Integer.valueOf((Integer) this.getValeur());
        Integer valeur2 = Integer.valueOf((Integer) valeur.getValeur());
        return valeur1.compareTo(valeur2);
    }
}