package fr.insarouen.iti.compilation.sgbd.moteur;

public record Attribut(String identifiant, Type type) {

    /**
     * Prefixe l'attribut avec le préfixe spécifié s'il n'est pas déjà préfixé.
     * 
     * @param prefix le préfix
     * @return l'attribut préfixé
     */
    public Attribut prefixer(String prefix) {
        if (!this.identifiant.contains(".")) {
            String identifiant = String.format(("%s.%s"), prefix, this.identifiant);
            return new Attribut(identifiant, this.type);
        }
        return new Attribut(this.identifiant, this.type);
    }
}