package fr.insarouen.iti.compilation.sgbd.moteur.valeur;

import fr.insarouen.iti.compilation.sgbd.exceptions.TypeInvalideException;
import fr.insarouen.iti.compilation.sgbd.moteur.Type;

/** Classe représentant VARCHAR */
public class Chaine extends Valeur {

    /** Construit un VARCHAR avec sa valeur
     * 
     * @param valeur la valeur du VARCHAR
     */
    public Chaine(String valeur) {
        super(Type.VARCHAR, valeur);
    }

    /** Compare deux VARCHAR
     * 
     * @param valeur la valeur à comparer
     * @return 1 si valeur1 est supérieure à valeur2, 0 si elles sont égales et 
            -1 si valeur2 supérieure à valeur1
     * @throws TypeInvalideException si valeur n'est pas de type Chaine
     */
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