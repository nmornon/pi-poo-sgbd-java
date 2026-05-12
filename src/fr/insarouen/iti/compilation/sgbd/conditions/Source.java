package fr.insarouen.iti.compilation.sgbd.conditions;

public class Source{
    private String nomTable;
    private String alias;

    public Source(String nomTable, String alias){
        this.nomTable=nomTable;
        this.alias=alias;
    }

    public String getNomTable(){
        return this.nomTable;
    }

    public String getAlias(){
        return this.alias;
    }

    public boolean aAlias(){
        return this.alias != null;
    }

    public String getNomUtilise(){
        if (aAlias()){
            return this.alias;
        }
        return this.nomTable;
    }
}

