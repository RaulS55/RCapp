package or.example.rdesmarket_v10;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.security.cert.TrustAnchor;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ConfirmarTurno extends AppCompatActivity implements Serializable {
    Button conf,can,desc,salir,requisitos;
    TextView titulo,fecha,hora,nya,dni,lugar,direccion,telefono,fecha_reserva;
    SimpleDateFormat df;
    String formattedDate;

    Dialog myDialog;
    Bitmap bmp, scaledBitmap, bmp_check_box, scaled_check_box;

    //NOTIFICACION
    private static final String CANAL_1_ID = "canal1";
    private static final String CANAL_1_NOMBRE = "Canal 1";
    private static final String CANAL_1_DESCRIPCION = "Este es elCanal 1";
    private static final String CANAL_2_ID = "canal 2";
    private static final String CANAL_2_NOMBRE = "Canal 2";
    private static final String CANAL_2_DESCRIPCION = "Este es el Canal 2";
    private NotificationManagerCompat notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_turno);

        //OBTENER FECHA ACTUAL
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());
        df = new SimpleDateFormat("dd/MMM/yyyy");
        formattedDate = df.format(c.getTime());

        llenarCampos();

        bmp = BitmapFactory.decodeResource(getResources(),R.drawable.img_confirmar25);
        scaledBitmap = Bitmap.createScaledBitmap(bmp, 450, 130, false); //450, 170   460,150
        bmp_check_box = BitmapFactory.decodeResource(getResources(),R.drawable.check_box);
        scaled_check_box = Bitmap.createScaledBitmap(bmp_check_box, 15, 15, false); //

        notificationManager = NotificationManagerCompat.from(this);
        //createNotificationChannel(); //NOTIFICACION

        ActivityCompat.requestPermissions(ConfirmarTurno.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        conf = (Button) findViewById(R.id.bt_confirmar);
        can = (Button) findViewById(R.id.bt_cancelar);
        desc = (Button) findViewById(R.id.bt_descargar);
        salir = (Button) findViewById(R.id.bt_salir);
        requisitos = (Button) findViewById(R.id.bt_requisitos);

        myDialog = new Dialog(this);
        ShowPopUp(R.layout.emergente2);
    }

    public void llenarCampos(){
        //vincular labels
        titulo = (TextView) findViewById(R.id.lb_titulo);
        fecha = (TextView) findViewById(R.id.lb_fecha_confir);
        hora = (TextView) findViewById(R.id.lb_holra_confir);
        nya = (TextView) findViewById(R.id.lb_NyA_confir);
        dni = (TextView) findViewById(R.id.lb_dni_confir);
        lugar = (TextView) findViewById(R.id.lb_lugar_confir);
        direccion = (TextView) findViewById(R.id.lb_direccion_confir);
        telefono = (TextView) findViewById(R.id.lb_telefono_confir);
        fecha_reserva = (TextView) findViewById(R.id.lb_fechaTurno_confir);

        TurnoObj turno = (TurnoObj) getIntent().getSerializableExtra("turno");
        hora.setText("Hora: "+getIntent().getStringExtra("hora"));
        fecha.setText("Turno para el dia: "+getIntent().getStringExtra("fecha"));

        if(turno.getTipo().equals("Nacimiento")){
            titulo.setText("Turno para registro de NACIMIENTO");
        }else {
            if (turno.getTipo().equals("DNIPasaporte")){
                titulo.setText("Turno para DNI/PASAPORTE");
            }else {
                titulo.setText("Turno para RECONOCIMIENTO");
            }
        }
        nya.setText("NyA: "+turno.getNombre() + " "+ turno.getApellido());
        dni.setText("DNI: "+turno.getDni());
        direccion.setText("Dirección: "+turno.getDireccion());
        telefono.setText("Telefono: "+turno.getTelefono());


        fecha_reserva.setText("Fecha de Reserva: "+formattedDate);
    }



    public void ShowPopUp (int view){
        Button botonCerrar;
        myDialog.setContentView(R.layout.emergente2);
        botonCerrar = (Button) myDialog.findViewById(R.id.bt_cerrarEmergente2);
        botonCerrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }

    //Boton para mostrar requisitos
    public void mostrarRquisitos(View view){

        TurnoObj turno = (TurnoObj) getIntent().getSerializableExtra("turno");
        //Mostrar los requisitos de cada turno
        switch (turno.getTipo()){
            case "Nacimiento":
                ShowPopUp2(R.layout.emergente3,R.id.bt_cerrarEmergente3);
                break;

            case "DNIPasaporte":
                ShowPopUp2(R.layout.emergente4,R.id.bt_cerrarEmergente4);
                break;

            case "Reconocimiento":
                ShowPopUp2(R.layout.emergente5,R.id.bt_cerrarEmergente5);
                break;
        }

    }

    public void ShowPopUp2 (int view,int view2){
        Button botonCerrar;
        myDialog.setContentView(view);
        botonCerrar = (Button) myDialog.findViewById(view2);
        botonCerrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }


    //Crear PDF
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void crearPDF(View view){
        Paint myPaint = new Paint();
        Paint TituloPaint = new Paint(); //
        Paint RequisitosPaint = new Paint();//

        TurnoObj turno = (TurnoObj) getIntent().getSerializableExtra("turno");
        PdfDocument NuevoPDF = new PdfDocument();
        PdfDocument.PageInfo NuevaPagInfo = new PdfDocument.PageInfo.Builder(990, 1650, 1).create();
        PdfDocument.Page pagina = NuevoPDF.startPage(NuevaPagInfo);
        Canvas canvas = pagina.getCanvas();

        //Titulo
        String text ="";
        if(turno.getTipo().equals("Nacimiento")){
            text ="Turno para registro de NACIMIENTO";
        }else {
            if (turno.getTipo().equals("DNIPasaporte")){
                text ="Turno para DNI/PASAPORTE";
            }else {
                text ="Turno para RECONOCIMIENTO";
            }
        }

        TituloPaint.setTextSize(24);
        TituloPaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
        canvas.drawText("TRÁMITE",210,100,TituloPaint);
        canvas.drawText(text,90,140,TituloPaint);
        //Logo del gobierno
        canvas.drawBitmap(scaledBitmap,50,160,myPaint);

        //Datos de turno
        myPaint.setTextSize(20);
        canvas.drawText(fecha.getText().toString(),50,330,myPaint);
        canvas.drawText(hora.getText().toString(),50,380,myPaint);
        canvas.drawText("NyA: "+turno.getNombre() + " "+ turno.getApellido(), 50, 430, myPaint);
        canvas.drawText("DNI: "+turno.getDni(), 50, 480, myPaint);
        canvas.drawText("Dirección: "+turno.getDireccion(), 50, 530, myPaint);
        canvas.drawText("Telefono: "+turno.getTelefono(), 50, 580, myPaint);
        canvas.drawText("Código de Operación: ", 50, 630, myPaint);
        canvas.drawText("Fecha de reserva: "+formattedDate, 50, 680, myPaint);
        myPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(38,300,490, 400, myPaint); //Dibujar rectangulo
        RequisitosPaint.setColor(Color.GRAY);

        //Mostrar los requisitos de cada turno
        switch (turno.getTipo()){
            case "Nacimiento":
                RequisitosNacimiento(canvas, RequisitosPaint);
                break;

            case "DNIPasaporte":
                RequisitosDNI(canvas, RequisitosPaint);
                break;

            case "Reconocimiento":
                RequisitosRec(canvas, RequisitosPaint);
                break;
        }


        NuevoPDF.finishPage(pagina);  //CERRAR PAGINA

        String myFilePath = Environment.getExternalStorageDirectory().getPath()+"/Download/TurnoRCivil"+turno.getTipo()+".pdf";
        File myFile = new File(myFilePath);

        try{
            NuevoPDF.writeTo(new FileOutputStream(myFile));
            Toast.makeText(this,"PDF descargado "+myFilePath,Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this,"ERROR",Toast.LENGTH_SHORT).show();
        }

        NuevoPDF.close();

        Uri uri = Uri.parse(Environment.getExternalStorageDirectory()+"/Download/TurnoRCivil.pdf");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, "application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        //NOTIFICACION
        notificacionDescarga(turno.getTipo());

        desc.setVisibility(View.INVISIBLE);
    }

    private void RequisitosRec(Canvas canvas, Paint myPaint){
        myPaint.setTextSize(24);
        canvas.drawText("REQUISITOS", 680, 100, myPaint);
        myPaint.setTextSize(16);
        canvas.drawBitmap(scaled_check_box,570,125,myPaint);
        canvas.drawBitmap(scaled_check_box,570,265,myPaint);
        canvas.drawBitmap(scaled_check_box,570,335,myPaint);
        canvas.drawBitmap(scaled_check_box,570,370,myPaint);
        canvas.drawText("  Partida de Nacimiento actualizada del menor", 590, 140, myPaint);
        canvas.drawText("para reconocimiento. La misma NO debe superar", 590, 175, myPaint);
        canvas.drawText("los 10 días desde la fecha de emisión. Debe ser", 590, 210, myPaint);
        canvas.drawText("fotocopia del libro, NO partida express.", 590, 245, myPaint);
        canvas.drawText("  Madre y padre deben presentarse con DNI vigente ", 590, 280, myPaint);
        canvas.drawText("y fotocopia de DNI.", 590, 315, myPaint);
        canvas.drawText("  No deben concurrir al turno con el bebé.", 590, 350, myPaint);
        canvas.drawText("  Traer lapicera azul o negra.", 590, 385, myPaint);

    }


    private void RequisitosDNI(Canvas canvas, Paint myPaint){
        myPaint.setTextSize(24);
        canvas.drawText("IMPORTANTE", 680, 100, myPaint);
        myPaint.setTextSize(16);
        canvas.drawBitmap(scaled_check_box,570,125,myPaint);
        canvas.drawText("Mayor de 18 años con trámite de Actualización", 590, 140, myPaint);
        canvas.drawText("de 14 o 16 años realizado en su momento:", 590, 175, myPaint);
        canvas.drawText("- LLevar DNI. Si lo extraviaste o te lo robaron,", 590, 210, myPaint);
        canvas.drawText("llevar la denuncia.", 590, 245, myPaint);
        canvas.drawText("- Para cambio de domicilio, llevar certificado de ", 590, 280, myPaint);
        canvas.drawText("residencia expedido por la policía o boleta de ", 590, 315, myPaint);
        canvas.drawText("impuestos o servicios a nombre del solicitante.", 590, 350, myPaint);
        canvas.drawBitmap(scaled_check_box,570,385,myPaint);
        canvas.drawText("Mayor de 18 años y trámite de Actualización de ", 590, 400, myPaint);
        canvas.drawText("14 o 16 años NO REALIZADO en su momento: ", 590, 435, myPaint);
        canvas.drawText("- Llevar Partida de Nacimiento actualizada", 590, 470, myPaint);
        canvas.drawText("ORIGINAL(No se acepta fotocopia)", 590, 505, myPaint);
        canvas.drawText("- DNI ORIGINAL de padre o madre (en caso de", 590, 540, myPaint);
        canvas.drawText("fallecido certificado de fallecimiento).", 590, 575, myPaint);
        canvas.drawText("- LLevar DNI. Si lo extraviaste o te lo robaron,", 590, 610, myPaint);
        canvas.drawText("llevar la denuncia.", 590, 645, myPaint);
        canvas.drawText("- Para cambio de domicilio, llevar certificado de ", 590, 680, myPaint);
        canvas.drawText("residencia expedido por la policía o boleta de ", 590, 715, myPaint);
        canvas.drawText("impuestos o servicios a nombre del solicitante.", 590, 750, myPaint);

    }

    private void RequisitosNacimiento(Canvas canvas, Paint myPaint){
        myPaint.setTextSize(24);
        canvas.drawText("REQUISITOS", 680, 100, myPaint);
        myPaint.setTextSize(16);
        canvas.drawBitmap(scaled_check_box,570,125,myPaint);
        canvas.drawBitmap(scaled_check_box,570,160,myPaint);
        canvas.drawBitmap(scaled_check_box,570,195,myPaint);
        canvas.drawBitmap(scaled_check_box,570,265,myPaint);
        canvas.drawBitmap(scaled_check_box,570,300,myPaint);
        canvas.drawBitmap(scaled_check_box,570,335,myPaint);
        canvas.drawBitmap(scaled_check_box,570,370,myPaint);
        canvas.drawBitmap(scaled_check_box,570,405,myPaint);
        canvas.drawBitmap(scaled_check_box,570,440,myPaint);
        canvas.drawText("  Certificado médico de nacimiento original.", 590, 140, myPaint); //distancia 15
        canvas.drawText("  Padres con DNI.", 590, 175, myPaint);
        canvas.drawText("  Padres casados, presentar acta o libreta", 590, 210, myPaint);
        canvas.drawText("de matrimonio civil.", 590, 245, myPaint);
        canvas.drawText("  Madre sola, presentarse con DNI vigente.", 590, 280, myPaint);
        canvas.drawText("  Carnet de vacunas del nacido.", 590, 315, myPaint);
        canvas.drawText("  Si tiene más hijos, llevar sus actas de nacimiento.", 590, 350, myPaint);
        canvas.drawText("  Presencia de ambos padres.", 590, 385, myPaint);
        canvas.drawText("  NO deben concurrir al turno con el bebé.", 590, 420, myPaint);
        canvas.drawText("  Traer lapicera azul o negra.", 590, 455, myPaint);
    }


    private void notificacionDescarga(String tipo){
        String nombre = "Turno descargado";
        String descripcion = "Guardado en Download";
        Context context = this;

        File pdfFile = new File("/storage/emulated/0/Download/TurnoRCivil"+tipo+".pdf");
        Uri pdfUri = FileProvider.getUriForFile(context, "com.example.myapp.fileprovider", pdfFile );
        Intent intent = new Intent();
        intent.setDataAndType(pdfUri, "application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        try{
            startActivity(intent);
        }catch (ActivityNotFoundException e){
            Toast.makeText(this,"No se puede visualizar el pdf",Toast.LENGTH_SHORT).show();
        }



        //Crear PendingIntent para pasarle a la notificacion
        PendingIntent contentIntent = PendingIntent.getActivity(
                this,
                0,
                intent,
                0
        );

        createNotificationChannel(); //CREAR CANALES DE NOTIFICACION
        Notification notificacion = new NotificationCompat.Builder(this,CANAL_2_ID).setSmallIcon(R.drawable.ic_baseline_get_app_24)
                .setContentTitle(nombre).setContentText(descripcion).setPriority(NotificationCompat.PRIORITY_LOW)
                //Agregar intent a la notificacion
                .setContentIntent(contentIntent)
                //Otras propiedades
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .build();
        notificationManager.notify(1,notificacion);
    }

    //CREAR CANALES DE NOTIFICACIONES
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            //CANAL 1
            /*NotificationChannel canal1 = new NotificationChannel(0
                    CANAL_1_ID,
                    CANAL_1_NOMBRE,
                    NotificationManager.IMPORTANCE_HIGH
            );
            canal1.setDescription(CANAL_1_DESCRIPCION);*/
            //CANAL 2
            NotificationChannel canal2 = new NotificationChannel(
                    CANAL_2_ID,
                    CANAL_2_NOMBRE,
                    NotificationManager.IMPORTANCE_HIGH
            );
            canal2.setDescription(CANAL_2_DESCRIPCION);
            //CONFIGURAR NOTIFICACIONES
            NotificationManager manager = getSystemService(NotificationManager.class);
            //manager.createNotificationChannel(canal1);
            manager.createNotificationChannel(canal2);

        }
    }


    //BOTON CONFIRMAR TURNO
    public void confirmar(View view){
        conf.setVisibility(View.INVISIBLE);
        can.setVisibility(View.INVISIBLE);
        desc.setEnabled(true);
        salir.setEnabled(true);

    }

    public void cancelar(View view){
        Intent pas = new Intent(this,MainActivity.class);
        startActivity(pas);
    }



}