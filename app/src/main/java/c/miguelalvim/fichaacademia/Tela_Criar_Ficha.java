package c.miguelalvim.fichaacademia;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Tela_Criar_Ficha extends AppCompatActivity {
    Button btt_salvar;
    EditText txt_nome, txt_vezes;

    BDHandler bdHandler;
    SQLiteDatabase bd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_criar_ficha);

        //Botões
        btt_salvar = findViewById(R.id.btt_salvar);

        //Edits
        txt_nome = findViewById(R.id.txt_nome);
        txt_vezes = findViewById(R.id.txt_vezes);

        //BD
        bdHandler = new BDHandler(getApplicationContext());
        bd = bdHandler.getReadableDatabase();

        btt_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent result = new Intent();
                ContentValues vals = new ContentValues();
                    vals.put("nome", txt_nome.getText().toString());
                    vals.put("vezes_por_semana", Integer.parseInt(txt_vezes.getText().toString()));

                long id_ficha_criada = bd.insert("ficha", null, vals);
                if(id_ficha_criada!=-1) {
                    result.putExtra("id_ficha", id_ficha_criada);
                    setResult(RESULT_OK, result);
                    Toast.makeText(Tela_Criar_Ficha.this, "Criação da ficha concluida com exito", Toast.LENGTH_SHORT).show();
                }else{
                    setResult(RESULT_CANCELED, null);
                    Toast.makeText(Tela_Criar_Ficha.this, "Falha na criação da ficha", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }
}
