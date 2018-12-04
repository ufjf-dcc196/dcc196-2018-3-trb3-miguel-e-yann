package c.miguelalvim.fichaacademia;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Fichas";
    private static final int DATABASE_VERSION = 1;

    BDHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys = ON;");
        db.execSQL(
                "CREATE TABLE atividade (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "nome TEXT, num_aparelho INTEGER)"
        );
        db.execSQL(
                "CREATE TABLE ficha (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "nome TEXT, vezes_por_semana INTEGER)"
        );
        db.execSQL(
                "CREATE TABLE ficha_atividade (id_atividade INTEGER, id_ficha INTEGER, " +
                        "FOREIGN KEY(id_atividade) REFERENCES atividade(id)," +
                        "FOREIGN KEY(id_ficha) REFERENCES ficha(id)," +
                        "PRIMARY KEY(id_atividade, id_ficha))"
        );
        //Populate for DEBUG
        //db.rawQuery("INSERT INTO pessoa values('Miguel Alvim','12312312312','nope@nopes.n')",null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(
                "DROP TABLE IF EXISTS pessoa"
        );
        db.execSQL(
                "DROP TABLE IF EXISTS evento"
        );
        onCreate(db);
    }
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db,oldVersion,newVersion);
    }
}
