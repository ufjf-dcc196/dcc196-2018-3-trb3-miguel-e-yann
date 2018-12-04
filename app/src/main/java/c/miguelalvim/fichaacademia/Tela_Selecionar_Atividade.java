package c.miguelalvim.fichaacademia;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Tela_Selecionar_Atividade extends AppCompatActivity {

    BDHandler bdHandler;
    SQLiteDatabase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela__selecionar__atividade);

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
        //Adicionar aqui o código de seleção da atividade
        //Retornar o id da atividade selecionada
    }
}
