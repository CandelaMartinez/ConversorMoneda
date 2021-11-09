package com.example.conversormonedas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
//accedo a los elementos de la vista
    EditText eTvalorAconvertirCM;
    TextView tvValorConvertidoCM;
    Spinner monedaActualCM;
    Spinner monedaCambioCM;

//tengo 2 spinners: uno lo llene con un XML y el otro lo llene con este array
   final String[] datosCM= new String[] {"EURO","DOLAR EEUU","PESO ARGENTINO","PESO MEXICANO","LIBRA ESTERLINA","DOLAR CANADIENSE","FRANCO SUIZO","YEN JAPONES","DOLAR AUSTRALIANO","REAL BRASILEÑO"};

//ratio de conversion, paso todas las monedas a euro para luego hacer el calculo
   final double [] ratioCM= new double[]{1,0.86,0.0087,0.042,1.18,0.69,0.93,0.0077,0.63,0.16};

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //lleno el spinner con la moneda a la que quiero cambiar, con el contenido del array datosCM
        ArrayAdapter<String> adaptadorCM= new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,datosCM);
        monedaCambioCM= findViewById(R.id.spMonedaCambioCM);
        monedaCambioCM.setAdapter(adaptadorCM);

        //localizo el spinner moneda actual, que fue llenado con fichero XML, lo guardo en una variable
        monedaActualCM=findViewById(R.id.spMonedaActualCM);

        //localizo el cuadro de texto del tipo number decimal y lo guardo en una variable
       eTvalorAconvertirCM= findViewById(R.id.txtCantidadCM);

       //localizo el text view donde ira el resultado y lo guardo en una variable
       tvValorConvertidoCM= findViewById(R.id.tvConvertidoCM);

   }

    public void convertirMoneda(View view){
       //guardo las posiciones del item elegido en los spinner
        int posicionSpinner1= monedaActualCM.getSelectedItemPosition();
        int posicionSpinner2= monedaCambioCM.getSelectedItemPosition();
        double resultado=0;

        //si el usuario no introdujo ningun valor, sale un cartel pidiendo que lo haga y borra lo que puede haber en el cuadro de resultado
        if(eTvalorAconvertirCM.getText().toString().isEmpty()){

            Toast.makeText(this,"Introducir valor a convertir",Toast.LENGTH_SHORT).show();
            tvValorConvertidoCM.setText("");

        }else {
        //si el usuario si introdujo valor, lo recupero, lo parseo a Double y lo guardo en una variable
            double cantidadAcambiarCM= Double.parseDouble(eTvalorAconvertirCM.getText().toString());

            //si esa variable es mayor a cero,
            // multiplico la cantidad que introdujo el usuario * (posicionArrayRatio del spinner Moneda actual/ posicionArrayRatio del spinner MonedaCambio)
            //coloco el resultado en el text view
            if(cantidadAcambiarCM>0 ){
                resultado= cantidadAcambiarCM * ratioCM[posicionSpinner1]/ratioCM[posicionSpinner2];
                tvValorConvertidoCM.setText(resultado +"");

            }else{

                //si lo que introdujo el usuario es menor o igual a cero, saco un cartel de error
                Toast.makeText(this,"Error al convertir",Toast.LENGTH_SHORT).show();

            }

        }



    }




}