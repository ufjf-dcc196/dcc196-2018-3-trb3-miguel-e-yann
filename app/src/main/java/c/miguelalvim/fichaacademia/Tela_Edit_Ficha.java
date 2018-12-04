package c.miguelalvim.fichaacademia;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Tela_Edit_Ficha extends AppCompatActivity {
    EditText txt_nome, txt_vezes;
    Button btt_add_atividade;

    BDHandler bdHandler;
    SQLiteDatabase bd;

    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela__edit__ficha);

        //BD
        bdHandler = new BDHandler(getApplicationContext());
        bd = bdHandler.getReadableDatabase();

        extras = getIntent().getExtras();
        if(extras!=null){
            @SuppressLint("Recycle") Cursor c = bd.rawQuery("SELECT * FROM ficha WHERE id=" + extras.getInt("id", -1), null);
            c.moveToFirst();
            txt_nome.setText(c.getString(c.getColumnIndex("nome")));
            txt_vezes.setText(c.getString(c.getColumnIndex("vezes_por_semana")));
        }else{
            finish();
        }

        btt_add_atividade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tela_Edit_Ficha.this,Tela_Selecionar_Atividade.class);
                intent.putExtra("id", extras.getInt("id", -1));
                startActivityForResult(intent,0);//Request code 0 = atividade selecionada
            }
        });



        //Query para pegar as atividades da ficha; deve se colocar o resultado em uma lista!
        Cursor c = bd.rawQuery("SELECT a.id,a.nome FROM ficha_atividade fa,atividade a " +
                "WHERE fa.id_ficha="+extras.getInt("id", -1)+" AND a.id = fa.id_ficha",null);
        if (c.moveToFirst()){
            do {
                int id = Integer.parseInt(c.getString(c.getColumnIndex("id")));
                String name = c.getString(c.getColumnIndex("nome"));
                //Adicionar aqui a aparte de população da lista

            } while(c.moveToNext());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case 0 :{//Nova Atividade selecionada
                if (resultCode == Activity.RESULT_OK){

                }
            }break;
        }
    }
}