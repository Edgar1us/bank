package edbeca.simarro.bank.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;

import edbeca.simarro.bank.Adapters.AdapterCuenta;
import edbeca.simarro.bank.BD.MiBancoOperacional;
import edbeca.simarro.bank.R;
import edbeca.simarro.bank.pojo.Cliente;
import edbeca.simarro.bank.pojo.Cuenta;

public class PosicionGlobalActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private Cliente cliente;
    private ListView listaCuentas;
    private MiBancoOperacional mbo;
    private AdapterCuenta<Cuenta> adaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posicion_global);
        mbo = MiBancoOperacional.getInstance(this);

        cliente = (Cliente) getIntent().getSerializableExtra("Cliente");
        listaCuentas = (ListView) findViewById(R.id.listaCuentas);


        adaptador = new AdapterCuenta<>(this, mbo.getCuentas(cliente));
        listaCuentas.setAdapter(adaptador);
        listaCuentas.setOnItemClickListener(this);


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Intent intent = new Intent(view.getContext(), MovimientoActivity.class);
        intent.putExtra("Cuenta", mbo.getCuentas(cliente).get(i));
        startActivity(intent);

    }
}