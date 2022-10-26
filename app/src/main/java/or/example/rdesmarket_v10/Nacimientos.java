package or.example.rdesmarket_v10;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Nacimientos extends AppCompatActivity {

    private Spinner spinner1,spinner2;
    private TextView resultado;
    private EditText dni,nombre,apellido,direccion,telefono;

    int dni_numero;
    private Boolean Band_Paridad = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nacimientos);


        dni = (EditText) findViewById(R.id.et_DNI);
        nombre = (EditText) findViewById(R.id.et_Nombre);
        apellido = (EditText) findViewById(R.id.et_Apellido);
        direccion = (EditText) findViewById(R.id.et_Direccion);
        telefono = (EditText) findViewById(R.id.et_Telefono);



        resultado = (TextView) findViewById(R.id.id_resultado);
        //String valorDni = dni.getText().toString();
        String valorDni = dni.getText().toString();

        spinner2 = (Spinner) findViewById(R.id.spinner_vive);
        spinner2.setEnabled(false);


        llenarSpiner1();
        seleccionSpinner1(); // Permite el cambio de oficina al cambiar de valor el spinner


    }

    //**********************************METODOS CREADOS**************************************************

    public void dinamicDNI(){
        dni.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Toast.makeText(getApplicationContext(), "Cambió a " + editable.toString(), Toast.LENGTH_SHORT).show();

                if(spinner1.getSelectedItem().toString().equals("Hospital Materno Infantil Dr. H. Quintana")){
                    spinnerVive();
                }


            }
        });
    }

    public  void seleccionSpinner1(){
        //Actualiza segun la seleccion en el Spinner
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                spinnerLugarNacimiento();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }

    public void seleccionSpinner2(){
        dinamicDNI();
        //Actualiza segun la seleccion en el Spinner
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                spinnerVive();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });
    }

    public void spinnerVive(){
        String selecVive = spinner2.getSelectedItem().toString();

        switch (selecVive){
            case "seleccionar": resultado.setText("");
                break;
            case "Alto Comedero": resultado.setText("Registro de Alto Comedero");
            break;
            case "Interior de la provincia/ Otra provincia": resultado.setText("Hospital Materno Infantil");
                break;
            case "Otro barrio de S.S. Jujuy":

                String valorDni = dni.getText().toString();
                if (!valorDni.isEmpty()){
                    Paridad();
                    if (Band_Paridad==true){
                        resultado.setText("Capital 2 (Barrio Cuyaya)");
                    }else {
                        resultado.setText("Hospital Materno Infantil");
                    }
                }else {
                    Toast.makeText(this,"Por favor complete el campo DNI",Toast.LENGTH_SHORT).show();
                }

                break;
            default: resultado.setText("No encontrado");
        }
    }

    public void Paridad(){
        String valorDni = dni.getText().toString();

            dni_numero = Integer.parseInt(valorDni);

            if (dni_numero%2==0){
                Band_Paridad=true;
            }else {
                Band_Paridad=false;
            }

    }


//Metodo del Spinner Lugar de Nacimiento
    public void spinnerLugarNacimiento(){
        String selecLugarNacimiento = spinner1.getSelectedItem().toString();
        if (selecLugarNacimiento.equals("Hospital Materno Infantil Dr. H. Quintana")){
            spinner2.setEnabled(true);
            resultado.setText("");
            String[] opcion = {"seleccionar","Alto Comedero","Interior de la provincia/ Otra provincia","Otro barrio de S.S. Jujuy"};
            llenarSpiner2(opcion);
            seleccionSpinner2();
        }else {
            spinner2.setEnabled(false);

            switch (selecLugarNacimiento){
                case "Clinica Avenida":
                case "Sanatorio Santa Maria":
                case "Hospital Guillermo Paterson":
                    resultado.setText("San Pedro");
                    break;


                case "Hospital Arturo Zabala":
                case "Clinica San Jose":
                    resultado.setText("Perico");
                    break;

                case "Clinica Ntra Sra de Fatima":
                case "Hospital Wenceslao Gallardo":
                    resultado.setText("Palpala");
                    break;

                case "Clinica Galo":
                case "Hospital San Isidro Labrador":
                    resultado.setText("Monterrico");
                    break;

                case "Clinica la Quiaca":
                case "Hospital Dr. Jorge Uro":
                    resultado.setText("La Quiaca");
                    break;

                case "Clinica del Niño":
                case "Clinica del Norte":
                case "Clinica Lavalle":
                case "Clinica Mayo":
                case "Clinica San Salvador":
                case "Hospital San Roque":
                case "Clinica Ntra Sra del Rosario":
                case "Clinica IMISS":
                    resultado.setText("Central (Solo para DNI cero años)");
                    break;

                case "Clinica Ledesma":
                    resultado.setText("Libertador Gral San Martin");
                    break;
                case "Hospital Oscar Orias":
                    resultado.setText("Ledesma");
                    break;
                case "Hospital Dr. Salvador Mazza":
                    resultado.setText("Tilcara");
                    break;
                case "Hospital Gral Manuel Belgrano":
                    resultado.setText("Humahuaca");
                    break;
                case "Hospital Ntra Sra del Carmen":
                    resultado.setText("El carmen");
                    break;
                case "Hospital Ntra Sra del Rosario":
                    resultado.setText("Abra Pampa");
                    break;
                case "Hospital Pbro Escolastico Zegada":
                    resultado.setText("Fraile Pintado");
                    break;
                case "Clinica Los Lapachos":
                    resultado.setText("Capital 1 (Barrio San Pedrito)");
                    break;
                case "seleccionar":
                    resultado.setText("");
                    break;

                default: resultado.setText("San Pedro");

            }
        }

    }

    //Llena el primer Spinner
    public void llenarSpiner1(){
        spinner1 = (Spinner) findViewById(R.id.spinner_LugarNacimiento);
        String[] opciones_nacimiento = {"seleccionar","Clinica Avenida","Clinica del Niño", "Clinica del Norte","Clinica Galo",
                "Clinica IMISS","Clinica la Quiaca","Clinica Lavalle","Clinica Ledesma","Clinica Los Lapachos","Clinica Mayo",
                "Clinica Ntra Sra de Fatima","Clinica Ntra Sra del Rosario","Clinica San Salvador","Clinica San Jose",
                "Hospital Dr. Salvador Mazza","Hospital Dr. Jorge Uro","Hospital Gral Manuel Belgrano","Hospital Guillermo Paterson",
                "Hospital Materno Infantil Dr. H. Quintana", "Hospital Ntra Sra del Carmen","Hospital Ntra Sra del Rosario",
                "Hospital Oscar Orias","Hospital San Isidro Labrador","Hospital San Roque","Hospital Wenceslao Gallardo",
                "Hospital Arturo Zabala","Hospital Pbro Escolastico Zegada","Sanatorio Santa Maria"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item,opciones_nacimiento);
        spinner1.setAdapter(adapter);

    }

    //Llena el segundo Spiner segun la seleccion (solo cuando es materno infantil)
    public void llenarSpiner2(String[] opciones){

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item,opciones);
        spinner2.setAdapter(adapter);
    }

    public void cambiarAFecha(View view){

        String valorDni = dni.getText().toString();
        String valorNom = nombre.getText().toString();
        String valorApe = apellido.getText().toString();
        String valorTel = telefono.getText().toString();
        String valordir = direccion.getText().toString();


        if (!valorDni.isEmpty()&& resultado.getText().toString()!="" && !valorNom.isEmpty() && !valorApe.isEmpty() && !valorTel.isEmpty() && !valordir.isEmpty() &&
                spinner1.getSelectedItem().toString()!="seleccionar"){

            if ((spinner1.getSelectedItem().toString()=="Hospital Materno Infantil Dr. H. Quintana" && spinner2.getSelectedItem().toString()=="seleccionar")){
                Toast.makeText(this,"Debe completar todos los campos",Toast.LENGTH_SHORT).show();
            }else {

                //Toast.makeText(this,"PASA",Toast.LENGTH_SHORT).show();
                TurnoObj turno = new TurnoObj("Nacimiento",valorNom,valorApe,valorDni,valordir,valorTel,resultado.getText().toString());
                Intent pasar = new Intent(this, Fecha.class);

                pasar.putExtra("turno",turno);
                startActivity(pasar);
            }

        }else {
            Toast.makeText(this,"Debe completar todos los campos",Toast.LENGTH_SHORT).show();
        }

    }
}
