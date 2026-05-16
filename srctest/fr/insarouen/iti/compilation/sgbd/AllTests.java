package fr.insarouen.iti.compilation.sgbd;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import fr.insarouen.iti.compilation.sgbd.conditions.AllTestsConditions;
import fr.insarouen.iti.compilation.sgbd.moteur.AllTestsMoteur;

@RunWith(Suite.class)
@SuiteClasses({
    AllTestsMoteur.class,
    AllTestsConditions.class
})

public class AllTests {}