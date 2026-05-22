package fr.insarouen.iti.compilation.sgbd.moteur.valeur;

import fr.insarouen.iti.compilation.sgbd.exceptions.TypeInvalideException;
import fr.insarouen.iti.compilation.sgbd.moteur.Type;

/** Classe représentant SERIAL */
public class Cle extends Valeur {
    /** Construit un SERIAL avec sa valeur
     * 
     * @param valeur la valeur de la clé
     */
    public Cle(int valeur) {
        super(Type.SERIAL, valeur);
    }

    /** Compare deux nombres
     * 
     * @param valeur la valeur à comparer
     * @return 1 si valeur1 est supérieure à valeur2, 0 si elles sont égales et 
            -1 si valeur2 supérieure à valeur1
     * @throws TypeInvalideException si valeur n'est pas de type Cle ou Entier
     */
    @Override
    public int compareTo(Valeur valeur) {
        if (!(valeur instanceof Cle) && !(valeur instanceof Entier)) {
            throw new TypeInvalideException(String.format(
                "Comparaison impossible entre les valeurs '%s' et '%s',"
              + " le type '%s' n'est pas comparable avec le type '%s'",
                this.getValeur(), valeur.getValeur(),
                this.getType(), valeur.getType()));
        }
        Integer valeur1 = (Integer) this.getValeur();
        Integer valeur2 = (Integer) valeur.getValeur();
        return valeur1.compareTo(valeur2);
    }
}