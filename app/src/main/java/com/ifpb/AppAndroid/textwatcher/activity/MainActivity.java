package com.ifpb.AppAndroid.textwatcher.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.ifpb.AppAndroid.textwatcher.R;
import com.ifpb.AppAndroid.textwatcher.asynctask.BuscarNome;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements TextWatcher {

    private static int MIN = 3;

    private EditText nameEditText;
    static List<String> nomes;
    static ArrayAdapter<String> adapter;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText = (EditText) findViewById(R.id.nomesEditText);
        nameEditText.addTextChangedListener(this);

        ListView nomesListView = (ListView) findViewById(R.id.namesListView);
        nomes = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                nomes);

        nomesListView.setAdapter(adapter);
    }

    @Override

    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override

    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
        Log.i("EditTextListener", "onTextChanged: " + charSequence);
        String nome = charSequence.toString();


        try {

            if (nome.length() >= MIN) {

                JSONObject json = new JSONObject();
                json.put("fullName", nome);

                BuscarNome buscarNome = new BuscarNome();
                buscarNome.execute(json);
            }
            else{
                adapter.clear();
            }

        } catch (JSONException e) {

            Log.e("EditTextListener", e.getMessage());
        }
    }

    @Override

    public void afterTextChanged(Editable s) {

    }

    public static void atualizarLista(List<String> nomes1){
        adapter.clear();
        for (int i = 0;i<nomes1.size();i++) {
            nomes.add(nomes1.get(i));
            adapter.notifyDataSetChanged();
        }
    }
}
