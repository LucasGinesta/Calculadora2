package com.example.calculadora;

public class Calculator {

    private double resultadoAcumulado;

    public Calculator() {
        resultadoAcumulado = 0;
    }

    public double calcular(double num1, double num2, String operacion) {
        switch (operacion) {
            case "+":
                return num1 + num2;

            case "-":
                return num1 - num2;


            case "*":
                return num1 * num2;


            case "/":
                return num1 / num2;

        }
        return num1;
    }

    public void reiniciar() {
        resultadoAcumulado = 0;
    }

    public double getResultadoAcumulado() {
        return resultadoAcumulado;
    }

    public void setResultadoAcumulado(double resultadoAcumulado) {
        this.resultadoAcumulado = resultadoAcumulado;
    }


}
