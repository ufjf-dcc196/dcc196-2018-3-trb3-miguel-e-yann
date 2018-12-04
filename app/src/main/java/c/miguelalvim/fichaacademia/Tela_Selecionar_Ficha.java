package c.miguelalvim.fichaacademia;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class Tela_Selecionar_Ficha extends AppCompatActivity {

    BDHandler bdHandler;
    SQLiteDatabase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela__selecionar__ficha);

        //BD
        bdHandler = new BDHandler(getApplicationContext());
        bd = bdHandler.getReadableDatabase();

        //Trecho de código onde é retornado todas as fichas existentes
        Cursor c = bd.rawQuery("SELECT * FROM ficha ", null);
        if (c.moveToFirst()){
            do {
                int id = Integer.parseInt(c.getString(c.getColumnIndex("id")));
                String name = c.getString(c.getColumnIndex("nome"));
                String vezes = c.getString(c.getColumnIndex("vezes_por_semana"));
                //Adicionar aqui a aparte de população da lista

            } while(c.moveToNext());
        }
        //Adicionar aqui o código de seleção da ficha
            //Chamando a Activity onde se ve os dados da ficha, se edita os dados e deleta a ficha
                //Intent intent = new Intent(Tela_Selecionar_Ficha.this,Tela_Edit_Ficha.class);
                //startActivityForResult(intent,0);


    }
}
