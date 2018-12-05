package c.miguelalvim.fichaacademia;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Adicionar_atividade_ficha extends Activity {
    Button adicionar;
    TextView num_rep, num_serie;
    BDHandler bdHandler;
    SQLiteDatabase bd;
    ArrayList<Integer> atividades = new ArrayList<>();
    ArrayList<String> nomeatividades = new ArrayList<>();
    ArrayAdapter<String> atividadesAdapter;
    ListView lsatividadesView;
    int id_atv = -1;
    int id_ficha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_atividade_ficha);
        //find
        adicionar = findViewById(R.id.btn_add);
        num_rep = findViewById(R.id.num_rep);
        num_serie = findViewById(R.id.num_serie);
        lsatividadesView = findViewById(R.id.lsListaAtividades);

        //DB
        bdHandler = new BDHandler(getApplicationContext());
        bd = bdHandler.getReadableDatabase();

        id_ficha = getIntent().getExtras().getInt("id");
        Cursor c = bd.rawQuery("SELECT a.id,a.nome FROM atividade a WHERE NOT EXISTS " +
                "(SELECT * FROM ficha f,ficha_atividade fa WHERE a.id=fa.id_atividade AND f.id=fa.id_ficha AND f.id=" + id_ficha + ")", null);
        if (c.moveToFirst()) {
            do {
                int id = Integer.parseInt(c.getString(c.getColumnIndex("id")));
                String nome = c.getString(c.getColumnIndex("nome"));
                nomeatividades.add(nome);
                atividades.add(id);
            } while (c.moveToNext());
        }
        atividadesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, nomeatividades);
        lsatividadesView.setAdapter(atividadesAdapter);
        atividadesAdapter.notifyDataSetChanged();

        lsatividadesView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                id_atv = atividades.get(position);
            }
        });
        adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!num_rep.getText().toString().isEmpty() && !num_serie.getText().toString().isEmpty() && id_atv != -1) {
                    Intent result = new Intent();
                    ContentValues vals = new ContentValues();
                    vals.put("num_series", num_serie.getText().toString());
                    vals.put("num_repeticoes", Integer.parseInt(num_rep.getText().toString()));
                    vals.put("id_atividade", id_atv);
                    vals.put("id_ficha", id_ficha);

                    long id_ficha_atividade = bd.insert("ficha_atividade", null, vals);
                    if (id_ficha_atividade != -1) {
                        result.putExtra("id_ficha_atividade", id_ficha_atividade);
                        setResult(RESULT_OK, result);
                        Toast.makeText(Adicionar_atividade_ficha.this, "Adição de atividade concluida com exito", Toast.LENGTH_SHORT).show();
                    }
                    finish();
                } else
                    Toast.makeText(Adicionar_atividade_ficha.this, "Há dados não inseridos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
