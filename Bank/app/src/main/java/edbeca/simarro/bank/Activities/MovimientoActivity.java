package edbeca.simarro.bank.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import edbeca.simarro.bank.Adapters.AdapterMovimiento;
import edbeca.simarro.bank.BD.MiBancoOperacional;
import edbeca.simarro.bank.R;
import edbeca.simarro.bank.pojo.Cuenta;
import edbeca.simarro.bank.pojo.Movimiento;

public class MovimientoActivity extends AppCompatActivity {

    private Cuenta cuenta;
    private ListView listaMovimientos;
    private MiBancoOperacional mbo;
    private AdapterMovimiento<Movimiento> adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimiento);
        mbo = MiBancoOperacional.getInstance(this);

        cuenta = (Cuenta) getIntent().getSerializableExtra("Cuenta");
        listaMovimientos = (ListView) findViewById(R.id.listViewMovimientos);

        adaptador = new AdapterMovimiento<Movimiento>(this, mbo.getMovimientos(cuenta));
        //adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mbo.getMovimientos(cuenta));
        listaMovimientos.setAdapter(adaptador);
    }

}