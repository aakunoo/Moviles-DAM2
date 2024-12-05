package com.example.contactos;

import android.Manifest;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText etBuscar, etMensaje;
    private Button btnBuscar;
    private ListView lvContactos;
    private ArrayAdapter<String> adapter;
    private List<String> contactosList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        etBuscar = findViewById(R.id.etBuscar);
        etMensaje = findViewById(R.id.etMensaje); 
        btnBuscar = findViewById(R.id.btnBuscar);
        lvContactos = findViewById(R.id.lvContactos);

        contactosList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, R.layout.fila_lista, R.id.txtItemContacto, contactosList);
        lvContactos.setAdapter(adapter);

        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.SEND_SMS
        }, 1);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarContactos();
            }
        });

        lvContactos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                enviarSMS(contactosList.get(position));
                return true;
            }
        });
    }

    private void buscarContactos() {
        contactosList.clear();
        String filtro = etBuscar.getText().toString();
        if (filtro.isEmpty()) {
            Toast.makeText(this, "Por favor, introduce un texto para buscar.", Toast.LENGTH_SHORT).show();
            return;
        }

        String[] proyeccion = {
                ContactsContract.Contacts.DISPLAY_NAME,
                ContactsContract.Contacts.HAS_PHONE_NUMBER
        };
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(
                ContactsContract.Contacts.CONTENT_URI,
                proyeccion,
                ContactsContract.Contacts.DISPLAY_NAME + " LIKE ?",
                new String[]{"%" + filtro + "%"},
                null
        );

        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String nombre = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
                int tieneNumero = cursor.getInt(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                if (tieneNumero > 0) {
                    contactosList.add(nombre);
                }
            }
            cursor.close();
        }

        if (contactosList.isEmpty()) {
            Toast.makeText(this, "No se encontraron contactos.", Toast.LENGTH_SHORT).show();
        }

        adapter.notifyDataSetChanged();
    }

    private void enviarSMS(String contacto) {
        String mensaje = etMensaje.getText().toString();
        if (mensaje.isEmpty()) {
            Toast.makeText(this, "Por favor, introduce un mensaje.", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(contacto, null, mensaje, null, null);
            Toast.makeText(this, "SMS enviado a " + contacto, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Error al enviar SMS.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
