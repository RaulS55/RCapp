
package or.example.rdesmarket_v10;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;

import java.io.File;

public class Notificacion extends Activity {

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        File file = new File(Environment.getExternalStorageDirectory(), "TurnoRegistroCivil.pdf");
        Uri path = Uri.fromFile(file);
        Intent pdfOpenintent = new Intent(Intent.ACTION_VIEW);
        pdfOpenintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        pdfOpenintent.setDataAndType(path, "application/*");

        startActivity(pdfOpenintent);
    }

    public void asdasd(){

    }
}
