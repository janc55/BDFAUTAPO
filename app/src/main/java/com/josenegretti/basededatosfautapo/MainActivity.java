package com.josenegretti.basededatosfautapo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et1, et2, et3, et4, et5, et6;
    private Cursor fila;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.etId);
        et2 = (EditText) findViewById(R.id.etNombre);
        et3 = (EditText) findViewById(R.id.etApellidos);
        et4 = (EditText) findViewById(R.id.etTelefono);
        et5 = (EditText) findViewById(R.id.etDireccion);
        et6 = (EditText) findViewById(R.id.etCapacitador);

    }

    public void nuevo(View view) {

        DBHelper admin = new DBHelper(this, "administracion", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String id = et1.getText().toString();
        String nombre = et2.getText().toString();
        String apellidos = et3.getText().toString();
        String telefono = et4.getText().toString();
        String direccion = et5.getText().toString();
        String capacitador = et6.getText().toString();

        Cursor fila = db.rawQuery("select * from beneficiarios where id=" + id, null);

        if (!fila.moveToFirst()) {  //devuelve true o false
            ContentValues registro = new ContentValues();

            registro.put("id", id);
            registro.put("nombre", nombre);
            registro.put("apellidos", apellidos);
            registro.put("telefono", telefono);
            registro.put("direccion", direccion);
            registro.put("capacitador", capacitador);

            db.insert("beneficiarios", null, registro);
            db.close();

            et1.setText("");
            et2.setText("");
            et3.setText("");
            et4.setText("");
            et5.setText("");
            et6.setText("");

            Toast.makeText(this, "Se cargaron los datos de la persona",
                    Toast.LENGTH_SHORT).show();
        } else {
            db.close();
            Toast.makeText(this, "La persona ya existe", Toast.LENGTH_SHORT).show();
        }
    }

    public void consulta(View v) {

        DBHelper admin = new DBHelper(this, "administracion", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String id = et1.getText().toString();

        Cursor fila = db.rawQuery(
                "select nombre,apellidos,telefono,direccion,capacitador from beneficiarios where id=" + id, null);
        if (fila.moveToFirst()) {
            et2.setText(fila.getString(0));
            et3.setText(fila.getString(1));
            et4.setText(fila.getString(2));
            et5.setText(fila.getString(3));
            et6.setText(fila.getString(4));
        } else
            Toast.makeText(this, "No existe la persona",
                    Toast.LENGTH_SHORT).show();
        db.close();
    }

    public void borrar(View v) {

        DBHelper admin = new DBHelper(this, "administracion", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String id = et1.getText().toString();

        int cant = db.delete("beneficiarios", "id=" + id, null);

        db.close();
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        et5.setText("");
        et6.setText("");
        if (cant == 1)
            Toast.makeText(this, "Se borro a la persona con dicho documento",
                    Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "No existe la persona",
                    Toast.LENGTH_SHORT).show();
    }


    public void modificacion(View v) {

        DBHelper admin = new DBHelper(this, "administracion", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String id = et1.getText().toString();
        String nombre = et2.getText().toString();
        String apellidos = et3.getText().toString();
        String telefono = et4.getText().toString();
        String direccion = et5.getText().toString();
        String capacitador = et6.getText().toString();

        ContentValues registro = new ContentValues();

        registro.put("nombre", nombre);
        registro.put("apellidos", apellidos);
        registro.put("telefono", telefono);
        registro.put("direccion", direccion);
        registro.put("capacitador", capacitador);

        int cant = db.update("beneficiarios", registro, "id=" + id, null);

        db.close();

        if (cant == 1)
            Toast.makeText(this, "Se modificaron los datos",
                    Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "No existe la persona",
                    Toast.LENGTH_SHORT).show();

    }

    public void inicio(View v) {

        DBHelper admin = new DBHelper(this, "administracion", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

         fila = db.rawQuery(
                "select * from beneficiarios order by id asc ", null);

        if (fila.moveToFirst()) {  //si ha devuelto 1 fila, vamos al primero (que es el unico)
            et1.setText(fila.getString(0));
            et2.setText(fila.getString(1));
            et3.setText(fila.getString(2));
            et4.setText(fila.getString(3));
            et5.setText(fila.getString(4));
            et6.setText(fila.getString(5));
        } else
            Toast.makeText(this, "No hay registrados" ,
                    Toast.LENGTH_SHORT).show();
        db.close();
    }

    public void anterior(View view){
        try {

            if (!fila.isFirst()) {  //si ha devuelto 1 fila, vamos al primero (que es el unico)
                fila.moveToPrevious();
                et1.setText(fila.getString(0));
                et2.setText(fila.getString(1));
                et3.setText(fila.getString(2));
                et4.setText(fila.getString(3));
                et5.setText(fila.getString(4));
                et6.setText(fila.getString(5));
            } else
                Toast.makeText(this, "Llego al principio de la tabla",
                        Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void siguiente(View view){
        try {

            if (!fila.isLast()) {  //si ha devuelto 1 fila, vamos al primero (que es el unico)
                fila.moveToNext();
                et1.setText(fila.getString(0));
                et2.setText(fila.getString(1));
                et3.setText(fila.getString(2));
                et4.setText(fila.getString(3));
                et5.setText(fila.getString(4));
                et6.setText(fila.getString(5));
            } else
                Toast.makeText(this, "Llego al final de la tabla",
                        Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }




    public void fin(View v) {

        DBHelper admin = new DBHelper(this, "administracion", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        fila = db.rawQuery(
                "select * from beneficiarios order by id asc ", null);

        if (fila.moveToLast()) {  //si ha devuelto 1 fila, vamos al primero (que es el unico)
            et1.setText(fila.getString(0));
            et2.setText(fila.getString(1));
            et3.setText(fila.getString(2));
            et4.setText(fila.getString(3));
            et5.setText(fila.getString(4));
            et6.setText(fila.getString(5));
        } else
            Toast.makeText(this, "No hay registrados" ,
                    Toast.LENGTH_SHORT).show();
        db.close();
    }

    public void onReset(View view){
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        et5.setText("");
        et6.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
