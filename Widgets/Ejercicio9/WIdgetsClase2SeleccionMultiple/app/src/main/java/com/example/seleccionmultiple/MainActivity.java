package com.example.seleccionmultiple;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.seleccionmultiple.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private TextView textView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> items;
    private ActionMode actionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        textView = findViewById(R.id.textView3);

        // Datos iniciales
        items = new ArrayList<>();
        items.add("León");
        items.add("Zamora");
        items.add("Salamanca");
        items.add("Palencia");
        items.add("Valladolid");
        items.add("Ávila");
        items.add("Burgos");
        items.add("Segovia");
        items.add("Soria");

        // Configuración del adaptador
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, items);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        // Listener para inicializar ActionMode al interactuar con elementos
        listView.setOnItemClickListener((parent, view, position, id) -> {
            if (actionMode == null) {
                actionMode = startActionMode(actionModeCallback);
            }

            // Actualizar texto
            updateSelectionText();
        });
    }

    // Callback para gestionar el ActionMode
    private final ActionMode.Callback actionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            getMenuInflater().inflate(R.menu.contextual_menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false; // No necesitamos actualizar el menú
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            // Usar if-else para manejar las acciones del menú
            int itemId = item.getItemId();
            if (itemId == R.id.seleccionar_todo) {
                selectAllItems();
                return true;
            } else if (itemId == R.id.deseleccionar_todo) {
                deselectAllItems();
                return true;
            } else if (itemId == R.id.eliminar_seleccionados) {
                deleteSelectedItems();
                return true;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            actionMode = null;
        }
    };

    // Métodos para las acciones del menú
    private void selectAllItems() {
        for (int i = 0; i < items.size(); i++) {
            listView.setItemChecked(i, true);
        }
        updateSelectionText();
        Toast.makeText(this, "Todos los elementos seleccionados", Toast.LENGTH_SHORT).show();
    }

    private void deselectAllItems() {
        for (int i = 0; i < items.size(); i++) {
            listView.setItemChecked(i, false);
        }
        updateSelectionText();
        Toast.makeText(this, "Selección eliminada", Toast.LENGTH_SHORT).show();
    }

    private void deleteSelectedItems() {
        ArrayList<String> selectedItems = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            if (listView.isItemChecked(i)) {
                selectedItems.add(items.get(i));
            }
        }

        items.removeAll(selectedItems);
        adapter.notifyDataSetChanged();
        updateSelectionText();
        Toast.makeText(this, "Elementos eliminados", Toast.LENGTH_SHORT).show();
    }

    // Actualizar texto con los elementos seleccionados
    private void updateSelectionText() {
        StringBuilder selected = new StringBuilder("Has elegido: ");
        for (int i = 0; i < items.size(); i++) {
            if (listView.isItemChecked(i)) {
                selected.append(items.get(i)).append(", ");
            }
        }
        textView.setText(selected.toString());
    }
}
