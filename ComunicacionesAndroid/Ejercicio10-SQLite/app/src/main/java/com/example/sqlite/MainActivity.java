package com.example.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText etGrupo, etTitulo;
    private ListView lvDiscos;
    private SQLiteDatabase db;

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

        etGrupo = findViewById(R.id.et1);
        etTitulo = findViewById(R.id.et2);
        lvDiscos = findViewById(R.id.listView);

        // Crear o abrir la base de datos
        db = openOrCreateDatabase("MisDiscos", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Discos (Grupo TEXT, Titulo TEXT);");

        listarDiscos();
    }

    // Menú
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.añadir) {
            añadirDisco();
            return true;
        } else if (id == R.id.borrar) {
            borrarDisco();
            return true;
        } else if (id == R.id.modificar) {
            modificarDisco();
            return true;
        } else if (id == R.id.ordenarTitulo) {
            listarDiscos("Titulo");
            return true;
        } else if (id == R.id.ordenarAutor) {
            listarDiscos("Grupo");
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    // añadir un disco
    private void añadirDisco() {
        String grupo = etGrupo.getText().toString();
        String titulo = etTitulo.getText().toString();

        if (!grupo.isEmpty() && !titulo.isEmpty()) {
            db.execSQL("INSERT INTO Discos VALUES ('" + grupo + "', '" + titulo + "');");
            Toast.makeText(this, "Disco añadido", Toast.LENGTH_SHORT).show();
            etGrupo.setText("");
            etTitulo.setText("");
            listarDiscos();
        } else {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    // borrar un disco
    private void borrarDisco() {
        String grupo = etGrupo.getText().toString();
        String titulo = etTitulo.getText().toString();

        if (!grupo.isEmpty() && !titulo.isEmpty()) {
            db.execSQL("DELETE FROM Discos WHERE Grupo = '" + grupo + "' AND Titulo = '" + titulo + "';");
            Toast.makeText(this, "Disco borrado", Toast.LENGTH_SHORT).show();
            etGrupo.setText("");
            etTitulo.setText("");
            listarDiscos();
        } else {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    // modificar un disco
    private void modificarDisco() {
        String grupo = etGrupo.getText().toString();
        String titulo = etTitulo.getText().toString();

        if (!grupo.isEmpty() && !titulo.isEmpty()) {
            db.execSQL("UPDATE Discos SET Titulo = '" + titulo + "' WHERE Grupo = '" + grupo + "';");
            Toast.makeText(this, "Disco modificado", Toast.LENGTH_SHORT).show();
            etGrupo.setText("");
            etTitulo.setText("");
            listarDiscos();
        } else {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    // listar discos
    private void listarDiscos() {
        listarDiscos(null);
    }

    private void listarDiscos(String ordenarPor) {
        ArrayList<String> lista = new ArrayList<>();
        String query = "SELECT * FROM Discos";
        if (ordenarPor != null) {
            query += " ORDER BY " + ordenarPor;
        }
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String grupo = cursor.getString(0);
                String titulo = cursor.getString(1);
                lista.add(grupo + " - " + titulo);
            }
        } else {
            lista.add("No hay discos registrados");
        }
        cursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.fila_lista, lista);
        lvDiscos.setAdapter(adapter);
    }

    public void annadir(View view){
        añadirDisco();
    }

    public void borrar(View view){
        borrarDisco();
    }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }
}
