package c.miguelalvim.fichaacademia;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Listar_Atividades extends AppCompatActivity {
    Button criar_atividade;
    BDHandler bdHandler;
    SQLiteDatabase bd;
    ArrayList<Atividade> atividades = new ArrayList<>();
    ArrayList<String> nomeatividades = new ArrayList<>();
    ArrayAdapter<String> atividadesAdapter;
    ListView lsatividadesView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela__selecionar__atividade);
        criar_atividade = findViewById(R.id.criar_atividade);
        //BD
        bdHandler = new BDHandler(getApplicationContext());
        bd = bdHandler.getReadableDatabase();

        updateNamesList();
        lsatividadesView = findViewById(R.id.lsListaAtividades);
        atividadesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, nomeatividades);
        lsatividadesView.setAdapter(atividadesAdapter);
        atividadesAdapter.notifyDataSetChanged();

        criar_atividade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Listar_Atividades.this, Criar_Atividade.class);
                startActivityForResult(intent, 1);
            }
        });
        //Adicionar aqui o código de seleção da atividade
        //Retornar o id da atividade selecionada
    }

    private void updateNamesList() {
        atividades.clear();
        nomeatividades.clear();

        Cursor c = bd.rawQuery("SELECT * FROM atividade ", null);
        if (c.moveToFirst()) {
            do {
                int id = Integer.parseInt(c.getString(c.getColumnIndex("id")));
                String nome = c.getString(c.getColumnIndex("nome"));
                int num_aparelho = c.getInt(c.getColumnIndex("num_aparelho"));
                atividades.add(new Atividade(nome, num_aparelho, id));
                nomeatividades.add(nome);
            } while (c.moveToNext());
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            updateNamesList();
        }
    }
}
