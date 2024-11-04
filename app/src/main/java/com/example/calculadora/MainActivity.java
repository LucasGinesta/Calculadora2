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
    private Calculator calculator;
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

        calculator = new Calculator();

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
        findViewById(R.id.buttonMenos).setOnClickListener(this::clickOperacion);
        findViewById(R.id.buttonMultiplicacion).setOnClickListener(this::clickOperacion);
        findViewById(R.id.buttonDivision).setOnClickListener(this::clickOperacion);
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

            if (operacion != null) {
                double resultado = calculator.calcular(calculator.getResultadoAcumulado(), numero, operacion);
                calculator.setResultadoAcumulado(resultado);
            } else { // Primer número ingresado
                calculator.setResultadoAcumulado(numero);
            }

            operacion = ((Button) view).getText().toString();
            textView.setText(String.valueOf(calculator.getResultadoAcumulado()));
            numeroActual = ""; // Reiniciar el número actual para la siguiente entrada
        }
    }

    // Método para calcular el resultado final al presionar igual
    public void igual(View view) {
        if (!numeroActual.isEmpty() && operacion != null) {
            double segundoNumero = Double.parseDouble(numeroActual);
            double resultado = calculator.calcular(calculator.getResultadoAcumulado(), segundoNumero, operacion);

            calculator.setResultadoAcumulado(resultado);
            textView.setText(String.valueOf(resultado));

            numeroActual = ""; // Limpiar el número actual
            operacion = null;
            nuevaOperacion = true; // Inicia una nueva operación
        }
    }

    // Método para borrar
    public void borrar() {
        numeroActual = "";
        calculator.reiniciar();
        operacion = null;
        textView.setText("0");
        nuevaOperacion = true; // Iniciar nueva operación tras borrar
    }
}
