package or.example.rdesmarket_v10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;

import or.example.rdesmarket_v10.utilidades.Utilidades;
import or.example.rdesmarket_v10.utilidades.UtilidadesFecha;

public class Fecha extends AppCompatActivity implements Serializable {
    //RECYCLER
    ArrayList<String> lista_horas,listaFechas,listaDias;
    RecyclerView recycler, recycler_calendario;
    private TextView fecha;
    Calendar calendar = Calendar.getInstance();
    String fechamostrar = calendar.get(Calendar.DAY_OF_MONTH)+"/"+calendar.get(Calendar.MONTH)+"/"+calendar.get(Calendar.YEAR), hora="";
    //CalendarView calendario;

    Dialog myDialog;

    //CALENDAR
    CalendarView calendarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fecha);

        myDialog = new Dialog(this);

        fecha = (TextView) findViewById(R.id.lb_fecha);
       //calendario = (CalendarView) findViewById(R.id.calendario);

        llamadoRecyclerCalendario();
        //configCalendario();
        ShowPopUp(R.layout.emergente1);
        llamadoRecycler();
        //obtenerFecha();
    }

    public void configCalendario(){
        calendar = Calendar.getInstance();
        int i = 0;
        while (i<=6){
            if (calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY || calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY ) {
                //Toast.makeText(this,"ENTRE"+calendar.get(Calendar.DAY_OF_MONTH),Toast.LENGTH_SHORT).show();
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }else {
                i++;
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                // System.out.println("DIferente dia"+calendar.get(Calendar.DAY_OF_MONTH));
            }
        }


        //calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        //calendar.set(Calendar.HOUR_OF_DAY, 23);//not sure this is needed
        long endOfMonth = calendar.getTimeInMillis();

        calendar = Calendar.getInstance();

        long startOfMonth = calendar.getTimeInMillis();


        //calendario.setMinDate(startOfMonth);  // Fecha minima
        //calendario.setMaxDate(endOfMonth);//Fecha maxima


    }



    public void ShowPopUp (int view){
        Button botonCerrar;
        myDialog.setContentView(R.layout.emergente1);
        botonCerrar = (Button) myDialog.findViewById(R.id.button5);
        botonCerrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }

    //Arreglo del RecyclerView
    public void llamadoRecycler(){
        //RECYCLER
        recycler = findViewById(R.id.recycler_horas);
        //recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recycler.setLayoutManager(new GridLayoutManager(this,3));
        lista_horas= new ArrayList<String>();

        for (int i = 8;i<=12;i++ ){
            lista_horas.add(i+":"+"00");
            for (int j=10;j<=50;j=j+10){
                lista_horas.add(i+":"+j);   //AQUI LO LLENO DE EJEMPLO ESTO SE DEBE LLENAR SEGUN LA BASE DE DATOS
            }
        }
        ListAdapterHoras adapter = new ListAdapterHoras(lista_horas);

        //ONCLICK
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),lista_horas.get(recycler.getChildAdapterPosition(view)),Toast.LENGTH_SHORT).show();

            }
        });

        recycler.setAdapter(adapter);

        DividerItemDecoration lineaSeparadora = new DividerItemDecoration(this,LinearLayoutManager.VERTICAL);
        DividerItemDecoration lineaSeparadora2 = new DividerItemDecoration(this,LinearLayoutManager.HORIZONTAL);
        recycler.addItemDecoration(lineaSeparadora);
        recycler.addItemDecoration(lineaSeparadora2);
    }
//************************************************************************************************************************
    public void llamadoRecyclerCalendario(){
        //RECYCLER
        recycler_calendario = findViewById(R.id.calendario);
        //recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recycler_calendario.setLayoutManager(new GridLayoutManager(this,1));
        listaFechas= new ArrayList<String>();
        listaDias= new ArrayList<String>();

        //****************Carga Fechas*************************
        calendar = Calendar.getInstance();
        String dia = "";
        int i = 0;
        while (i<=6){
            if (calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY || calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY ) {
                //Toast.makeText(this,"ENTRE"+calendar.get(Calendar.DAY_OF_MONTH),Toast.LENGTH_SHORT).show();
                //listaFechas.add(calendar.get(Calendar.DAY_OF_MONTH)+"/"+(calendar.get(Calendar.MONTH)+1)+"/"+calendar.get(Calendar.YEAR));
                //dia=diaSpañol(calendar.get(Calendar.DAY_OF_WEEK));
                //listaDias.add(dia);
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                //i++;
            }else {
                i++;
                listaFechas.add(calendar.get(Calendar.DAY_OF_MONTH)+"/"+(calendar.get(Calendar.MONTH)+1)+"/"+calendar.get(Calendar.YEAR));
                dia=diaSpañol(calendar.get(Calendar.DAY_OF_WEEK));
                listaDias.add(dia);
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                // System.out.println("DIferente dia"+calendar.get(Calendar.DAY_OF_MONTH));
            }
        }



        /*for (int i = 8;i<=12;i++ ){
            listaFechas.add(i+":"+"00");
            for (int j=10;j<=50;j=j+10){
                listaFechas.add(i+":"+j);   //AQUI LO LLENO DE EJEMPLO ESTO SE DEBE LLENAR SEGUN LA BASE DE DATOS
            }
        }*/
        ListAdapterFechas adapter = new ListAdapterFechas(listaFechas,listaDias);

        //ONCLICK
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),listaFechas.get(recycler.getChildAdapterPosition(view)),Toast.LENGTH_SHORT).show();

            }
        });

        recycler_calendario.setAdapter(adapter);

        DividerItemDecoration lineaSeparadora = new DividerItemDecoration(this,LinearLayoutManager.VERTICAL);
        DividerItemDecoration lineaSeparadora2 = new DividerItemDecoration(this,LinearLayoutManager.HORIZONTAL);
        recycler_calendario.addItemDecoration(lineaSeparadora);
        recycler_calendario.addItemDecoration(lineaSeparadora2);
    }


    public String diaSpañol(int d){
        String dia;
        switch (d){
            case 1: dia="Domingo";
                break;
            case 2: dia="Lunes";
                break;
            case 3: dia="Martes"; break;
            case 4: dia="Miercoles"; break;
            case 5: dia="Jueves"; break;
            case 6: dia="Viernes"; break;
            case 7: dia="Sabado"; break;
            default: dia="asdasd";
        }
        return dia;
    }


    //Obtener fecha del calendario
    public void obtenerFecha(){
        //CALENDAR
        /*calendario = (CalendarView) findViewById(R.id.calendario);

        calendario.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int anio, int mes, int dia) {
                fechamostrar = dia+"/"+mes+"/"+anio;
                fecha.setText(fechamostrar);
                fecha.setTypeface(Typeface.DEFAULT_BOLD);

            }
        });*/
    }

    public void cambiarConfirmar(View view){
        String seleccion = Utilidades.seleccion;
        String seleccionFecha = UtilidadesFecha.seleccion;
        TurnoObj turno = (TurnoObj) getIntent().getSerializableExtra("turno");
        Intent pas = new Intent(this,ConfirmarTurno.class);

       // if (!fechamostrar.equals("")){

            pas.putExtra("turno",turno);
            pas.putExtra("fecha",seleccionFecha);
            pas.putExtra("hora",seleccion);
            startActivity(pas);
        //}else{
           // Toast.makeText(this,"Debe seleccionar fecha y hora",Toast.LENGTH_SHORT).show();
        //}

    }

}
