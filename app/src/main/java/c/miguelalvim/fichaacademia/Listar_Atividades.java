package c.miguelalvim.fichaacademia;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Listar_Atividades extends AppCompatActivity {
    Button criar_atividade;
    BDHandler bdHandler;
    SQLiteDatabase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela__selecionar__atividade);
        criar_atividade = findViewById(R.id.criar_atividade);


        //BD
        bdHandler = new BDHandler(getApplicationContext());
        bd = bdHandler.getReadableDatabase();

        //Trecho de código onde é retornado todas as fichas existentes
        Cursor c = bd.rawQuery("SELECT * FROM atividade ", null);
        if (c.moveToFirst()) {
            do {
                int id = Integer.parseInt(c.getString(c.getColumnIndex("id")));
                String name = c.getString(c.getColumnIndex("nome"));
                String cpf = c.getString(c.getColumnIndex("num_aparelho"));
                //Adicionar aqui a aparte de população da lista

            } while (c.moveToNext());
        }
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
}
