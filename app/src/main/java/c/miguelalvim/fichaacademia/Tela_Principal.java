package c.miguelalvim.fichaacademia;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Tela_Principal extends AppCompatActivity {
    ArrayList<Ficha> fichas = new ArrayList<>();
    ArrayList<String> nomefichas = new ArrayList<>();
    ArrayAdapter<String> aaFichasAdapter;
    ListView lsFichasView;
    Button btt_Criar_Ficha, btt_atividade;

    BDHandler bdHandler;
    SQLiteDatabase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela__principal);

        //Iniciando o Banco de Dados
        bdHandler = new BDHandler(getApplicationContext());
        bd = bdHandler.getReadableDatabase();
        updateNamesList();

        //Adapter
        lsFichasView = findViewById(R.id.lsListaFichas);
        aaFichasAdapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, nomefichas);
        lsFichasView.setAdapter(aaFichasAdapter);
        aaFichasAdapter.notifyDataSetChanged();

        //Botões
        btt_Criar_Ficha = findViewById(R.id.btt_Criar_Ficha);
        btt_atividade = findViewById(R.id.listar_act);

        btt_Criar_Ficha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tela_Principal.this, Criar_Ficha.class);
                startActivityForResult(intent,0);//Request code 0 = cadastro de ficha
            }
        });
        btt_atividade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tela_Principal.this, Listar_Atividades.class);
                startActivityForResult(intent, 1);
            }
        });
        lsFichasView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Tela_Principal.this, Tela_Edit_Ficha.class);
                intent.putExtra("id", fichas.get(position).id);
                startActivityForResult(intent, 2);//Request code 2 = tela de edição de ficha
            }
        });

    }

    private void updateNamesList() {
        fichas.clear();
        nomefichas.clear();

        @SuppressLint("Recycle") Cursor c = bd.rawQuery("SELECT * FROM ficha", null);
        if (c.moveToFirst()) {
            do {
                int id = Integer.parseInt(c.getString(c.getColumnIndex("id")));
                String nome = c.getString(c.getColumnIndex("nome"));
                String vezes = c.getString(c.getColumnIndex("vezes_por_semana"));
                Log.i("DABDAB", "Loaded Ficha(id=" + id + "): " + nome + " |" + vezes);
                fichas.add(new Ficha(nome, Integer.parseInt(vezes), id));
                nomefichas.add(nome);
            } while (c.moveToNext());
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            updateNamesList();
        }
    }
}
