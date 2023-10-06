package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Calculatrice {
    private Operande firstOperande = new Operande("0");
    private Operande secondOperande = new Operande("");
    private String operator = "";
    private List<String> OPERATORS = new ArrayList<>(Arrays.asList("-", "+", "x", "/"));
    private List<String> NUMBER = new ArrayList<>(Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"));
    private boolean isJustCalcul = false;

    public Calculatrice(String value) {
    }


    public void touche(String touche) {
        switch (touche) {
            case "AC" -> clear();
            case "%" -> calculerPourcent();
            case "," -> ajouterPoint();
            case "=" -> calculer();
            default -> ajouterTouche(touche);
        }
    }

    private void ajouterTouche(String touche) {
        if (NUMBER.contains(touche))
            ajouterNumber(touche);
        else if (OPERATORS.contains(touche) && operator.isEmpty())
            operator = touche;
        else {
            calculer();
            operator = touche;
        }

    }

    private void ajouterNumber(String touche) {
        if (operator.isEmpty())
            firstOperande.ajouteChiffre(touche);
        else
            secondOperande.ajouteChiffre(touche);
    }

    private void calculer() {
        isJustCalcul = true;

        switch (operator) {
            case "+" -> {
                firstOperande.add(secondOperande);
                clear();
                isJustCalcul = false;
            }
            case "-" -> {
                firstOperande.substract(secondOperande);
                clear();
                isJustCalcul = false;
            }
            case "x" -> {
                firstOperande.multiply(secondOperande);
                clear();
                isJustCalcul = false;
            }
            case "/" -> {
                firstOperande.divide(secondOperande);
                clear();
                isJustCalcul = false;
            }
        }
    }

    private void ajouterPoint() {
        if (operator.isEmpty())
            firstOperande.ajouterPoint();
        else
            secondOperande.ajouterPoint();
    }

    private void calculerPourcent() {
        isJustCalcul = true;

        if (operator.isEmpty()) {
            firstOperande.diviserPar100();
            isJustCalcul = false;
        } else {
            switch (operator) {
                case "x" -> {
                    firstOperande.multiplyByPourcent(secondOperande);
                    clear();
                    isJustCalcul = false;
                }
                case "/" -> {
                    firstOperande.divideByPourcent(secondOperande);
                    clear();
                    isJustCalcul = false;
                }
                case "+" -> {
                    firstOperande.additionnerByPourcent(secondOperande);
                    clear();
                    isJustCalcul = false;
                }
                case "-" -> {
                    firstOperande.soustraireByPourcent(secondOperande);
                    clear();
                    isJustCalcul = false;
                }
            }
        }


    }


    private void clear() {
        if (!isJustCalcul) {
            firstOperande = new Operande("0");
            operator = "";
            secondOperande = new Operande("");
        } else {
            operator = "";
            secondOperande = new Operande("");
        }
    }


    @Override
    public String toString() {
        return firstOperande.toString() + " " + operator.toString() + " " + secondOperande.toString();

    }
}
