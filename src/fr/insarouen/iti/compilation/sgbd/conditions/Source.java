package fr.insarouen.iti.compilation.sgbd.conditions;

import java.util.Map;
import fr.insarouen.iti.compilation.sgbd.moteur.Identifiant;

public class Source{
    private Identifiant nomTable;
    private Identifiant alias;

    public Source(Identifiant nomTable, Identifiant alias){
        this.nomTable=nomTable;
        this.alias=alias;
    }

    public Identifiant getNomTable(){
        return this.nomTable;
    }

    public Identifiant getAlias(){
        return this.alias;
    }

    public boolean aAlias(){
        return this.alias != null;
    }

    public Identifiant getNomUtilise(){
        if (aAlias()){
            return this.alias;
        }
        return this.nomTable;
    }


}

