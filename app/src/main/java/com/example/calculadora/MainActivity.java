package com.example.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private String numeroActual = "";
    private double resultadoAcumulado = 0;
    private String operacion;
    private boolean nuevaOperacion = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textView = findViewById(R.id.textView);
        textView.setText("0");

        // Asignar listeners a los botones
        findViewById(R.id.button0).setOnClickListener(this::clickNumero);
        findViewById(R.id.button1).setOnClickListener(this::clickNumero);
        findViewById(R.id.button2).setOnClickListener(this::clickNumero);
        findViewById(R.id.button3).setOnClickListener(this::clickNumero);
        findViewById(R.id.button4).setOnClickListener(this::clickNumero);
        findViewById(R.id.button5).setOnClickListener(this::clickNumero);
        findViewById(R.id.button6).setOnClickListener(this::clickNumero);
        findViewById(R.id.button7).setOnClickListener(this::clickNumero);
        findViewById(R.id.button8).setOnClickListener(this::clickNumero);
        findViewById(R.id.button9).setOnClickListener(this::clickNumero);
        findViewById(R.id.buttonMas).setOnClickListener(this::clickOperacion);
        findViewById(R.id.buttonIgual).setOnClickListener(this::igual);
        findViewById(R.id.buttonC).setOnClickListener(v -> borrar());
    }

    // Gestionar clicks en números
    public void clickNumero(View view) {
        if (nuevaOperacion) {
            numeroActual = ""; // Reiniciar el número si es una nueva operación
            nuevaOperacion = false;
        }

        Button button = (Button) view;
        numeroActual += button.getText().toString();
        textView.setText(numeroActual);
    }

    // Gestionar clicks en operaciones
    public void clickOperacion(View view) {
        if (!numeroActual.isEmpty()) {
            double numero = Double.parseDouble(numeroActual);

            if (operacion != null) { // Si ya hay una operación pendiente, calcula el acumulado
                resultadoAcumulado = calcularResultado(resultadoAcumulado, numero, operacion);
            } else { // Primer número ingresado
                resultadoAcumulado = numero;
            }

            operacion = ((Button) view).getText().toString();
            textView.setText(String.valueOf(resultadoAcumulado));
            numeroActual = ""; // Reiniciar el número actual para la siguiente entrada
        }
    }

    // Método para calcular el resultado
    public void igual(View view) {
        if (!numeroActual.isEmpty() && operacion != null) {
            double segundoNumero = Double.parseDouble(numeroActual);
            resultadoAcumulado = calcularResultado(resultadoAcumulado, segundoNumero, operacion);

            textView.setText(String.valueOf(resultadoAcumulado));
            numeroActual = ""; // Limpiar el número actual
            operacion = null;
            nuevaOperacion = true; // Inicia una nueva operación
        }
    }

    // Método para realizar la operación
    private double calcularResultado(double num1, double num2, String operacion) {
        switch (operacion) {
            case "+":
                return num1 + num2;
            // Agregar otros casos si quieres soportar más operaciones
            default:
                return num1;
        }
    }

    // Método para borrar
    public void borrar() {
        numeroActual = "";
        resultadoAcumulado = 0;
        operacion = null;
        textView.setText("0");
        nuevaOperacion = true; // Iniciar nueva operación tras borrar
    }
}
