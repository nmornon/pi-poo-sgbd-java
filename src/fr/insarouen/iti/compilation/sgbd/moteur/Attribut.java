package fr.insarouen.iti.compilation.sgbd.moteur;

public class Attribut {
    private String identifiant;
    private Type type;

    public Attribut(String identifiant, Type type) {
        this.identifiant = identifiant;
        this.type = type;
    }

    public String getIdentifiant() {
        return this.identifiant;
    }

    public Type getType() {
        return this.type;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (!(object instanceof Attribut attribut)) {
            return false;
        }
        return this.getIdentifiant().equals(attribut.getIdentifiant())
            && this.getType().equals(attribut.getType());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.getIdentifiant().hashCode();
        hash = 31 * hash + this.getType().hashCode();
        return super.hashCode();
    }
}
