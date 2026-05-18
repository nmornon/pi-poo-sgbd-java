package fr.insarouen.iti.compilation.sgbd;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import fr.insarouen.iti.compilation.sgbd.conditions.AllTestsConditions;
import fr.insarouen.iti.compilation.sgbd.moteur.AllTestsMoteur;
import fr.insarouen.iti.compilation.sgbd.persistance.AllTestsPersistance;

@RunWith(Suite.class)
@SuiteClasses({
    AllTestsMoteur.class,
    AllTestsConditions.class,
    AllTestsPersistance.class
})

public class AllTests {}