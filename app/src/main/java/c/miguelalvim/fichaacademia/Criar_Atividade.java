package c.miguelalvim.fichaacademia;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Criar_Atividade extends AppCompatActivity {
    Button btt_salvar;
    EditText txt_nome,txt_numero;

    BDHandler bdHandler;
    SQLiteDatabase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_atividade);

        //Botões
        btt_salvar = findViewById(R.id.btt_salvar);

        //Edits
        txt_nome = findViewById(R.id.txt_nome);
        txt_numero = findViewById(R.id.txt_numero);

        //BD
        bdHandler = new BDHandler(getApplicationContext());
        bd = bdHandler.getReadableDatabase();

        btt_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent result = new Intent();
                ContentValues vals = new ContentValues();
                vals.put("nome", txt_nome.getText().toString());
                vals.put("num_aparelho", Integer.parseInt(txt_numero.getText().toString()));

                long id_ficha_criada = bd.insert("atividade", null, vals);
                if(id_ficha_criada!=-1) {
                    result.putExtra("id_atividade", id_ficha_criada);
                    setResult(RESULT_OK, result);
                    Toast.makeText(Criar_Atividade.this, "Adição da atividade concluida com exito", Toast.LENGTH_SHORT).show();
                }else{
                    setResult(RESULT_CANCELED, null);
                    Toast.makeText(Criar_Atividade.this, "Falha na criação da atividade", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }


}
