package c.miguelalvim.fichaacademia;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Tela_Principal extends AppCompatActivity {
    Button btt_Criar_Ficha, btt_atividade;

    BDHandler bdHandler;
    SQLiteDatabase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela__principal);

        //Botões
        btt_Criar_Ficha = findViewById(R.id.btt_Criar_Ficha);
        btt_atividade = findViewById(R.id.listar_act);

        //Iniciando o Banco de Dados
        bdHandler = new BDHandler(getApplicationContext());
        bd = bdHandler.getReadableDatabase();


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
    }
}
