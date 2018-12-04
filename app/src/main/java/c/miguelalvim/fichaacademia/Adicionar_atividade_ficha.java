package c.miguelalvim.fichaacademia;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Adicionar_atividade_ficha extends Activity {
    Button adicionar;
    TextView num_rep, num_serie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_atividade_ficha);
        adicionar = findViewById(R.id.btn_add);
        num_rep = findViewById(R.id.num_rep);
        num_serie = findViewById(R.id.num_serie);
    }
}
