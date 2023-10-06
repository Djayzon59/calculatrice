package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class Operande {
    private static int precision = 60;
    private static NumberFormat numberFormat = NumberFormat.getInstance(Locale.FRANCE);
    private String string;
    private BigDecimal bigDecimal = new BigDecimal(0);


    public Operande(String string) {
        this.string = string;
    }



    protected void ajouteChiffre(String touche){
        if(string.equals("0")){
            string = touche;
            myFormatToBigDecimal();
        }
        else {
            string = string + touche;
            myFormatToBigDecimal();
        }
    }

    protected void ajouterPoint(){
        if(string.isEmpty()){
            string = "0,";
            myFormatToBigDecimal();
        }
        else{
            string = string + ",";
            myFormatToBigDecimal();
        }
    }

    private void myFormatToBigDecimal(){
        try{
            if(string.isEmpty()) bigDecimal = BigDecimal.valueOf(0);
            else bigDecimal = BigDecimal.valueOf(numberFormat.parse(string).doubleValue());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    private void bigDecimalToMyFormat(){
        numberFormat.setMaximumFractionDigits(32);
        string = numberFormat.format(bigDecimal);
    }
    protected void add (Operande other){
        bigDecimal = bigDecimal.add(other.bigDecimal);
        bigDecimalToMyFormat();
    }
    protected void multiply (Operande other){
        bigDecimal = bigDecimal.multiply(other.bigDecimal);
        bigDecimalToMyFormat();
    }
    protected void substract (Operande other){
        bigDecimal = bigDecimal.subtract(other.bigDecimal);
        bigDecimalToMyFormat();
    }
    protected void divide (Operande other){
        try{
            bigDecimal = bigDecimal.divide(other.bigDecimal, precision, RoundingMode.FLOOR);
            bigDecimalToMyFormat();
        }catch(Exception e){
            string = "ERROR";
        }
    }
    protected void diviserPar100(){
        try{
            bigDecimal = bigDecimal.divide(BigDecimal.valueOf(100), precision, RoundingMode.FLOOR);
            bigDecimalToMyFormat();
        }catch(Exception e){
            string = "ERROR";
        }
    }
    public void multiplyByPourcent(Operande other) {
        bigDecimal = bigDecimal.multiply(other.bigDecimal);
        bigDecimal = bigDecimal.divide(BigDecimal.valueOf(100), precision, RoundingMode.FLOOR);
        bigDecimalToMyFormat();
    }

    public void divideByPourcent(Operande other) {
        bigDecimal = bigDecimal.divide(other.bigDecimal, precision, RoundingMode.FLOOR);
        bigDecimal = bigDecimal.multiply(BigDecimal.valueOf(100));
        bigDecimalToMyFormat();
    }

    public void additionnerByPourcent(Operande other) {
        other.bigDecimal = other.bigDecimal.divide(BigDecimal.valueOf(100), precision, RoundingMode.FLOOR);
        bigDecimal = bigDecimal.add(bigDecimal.multiply(other.bigDecimal));
        bigDecimalToMyFormat();
    }

    public void soustraireByPourcent(Operande other) {
        other.bigDecimal = other.bigDecimal.divide(BigDecimal.valueOf(100), precision, RoundingMode.FLOOR);
        bigDecimal = bigDecimal.subtract(bigDecimal.multiply(other.bigDecimal));
        bigDecimalToMyFormat();
    }


    @Override
    public String toString() {
        return string;
    }



}
