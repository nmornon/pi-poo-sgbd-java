package fr.insarouen.iti.compilation.sgbd.moteur.valeur;

import fr.insarouen.iti.compilation.sgbd.exceptions.TypeInvalideException;
import fr.insarouen.iti.compilation.sgbd.moteur.Type;

/** Classe représentant INT */
public class Entier extends Valeur {
    /** Construit un INT avec sa valeur
     * 
     * @param valeur la valeur de l'entier
     */
    public Entier(int valeur) {
        super(Type.INT, valeur);
    }

    /** Compare deux nombres
     * 
     * @param valeur la valeur à comparer
     * @return 1 si valeur1 est supérieure à valeur2, 0 si elles sont égales et 
            -1 si valeur2 supérieure à valeur1
     * @throws TypeInvalideException si valeur n'est pas de type Entier ou Cle
     */
    @Override
    public int compareTo(Valeur valeur) {
        if (!(valeur instanceof Entier) && !(valeur instanceof Cle)) {
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