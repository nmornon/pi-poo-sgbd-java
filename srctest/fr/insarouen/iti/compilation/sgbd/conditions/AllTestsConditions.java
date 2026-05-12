package fr.insarouen.iti.compilation.sgbd.conditions;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    TestConditionSimple.class,
    TestExpressionLogique.class
})

public class AllTestsConditions {}