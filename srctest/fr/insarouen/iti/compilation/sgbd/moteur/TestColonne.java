package fr.insarouen.iti.compilation.sgbd.moteur;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class TestColonne {
    @Test
    public void test_equals() {
        Colonne colonne11 = new Colonne("id", Type.SERIAL);
        Colonne colonne21 = new Colonne("nom", Type.VARCHAR);
        Colonne colonne31 = new Colonne("puissance", Type.INT);

        Colonne colonne12 = new Colonne("id", Type.SERIAL);
        Colonne colonne22 = new Colonne("nom", Type.VARCHAR);
        Colonne colonne32 = new Colonne("puissance", Type.VARCHAR);


        assertThat(colonne11, equalTo(colonne12));
        assertThat(colonne21, equalTo(colonne22));
        assertThat(colonne31, equalTo(colonne32));
        assertThat(colonne11.hashCode(), is(colonne12.hashCode()));
        assertThat(colonne21.hashCode(), is(colonne22.hashCode()));
        assertThat(colonne31.hashCode(), is(colonne32.hashCode()));
    }
}
