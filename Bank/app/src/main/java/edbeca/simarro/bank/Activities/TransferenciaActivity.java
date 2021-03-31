package edbeca.simarro.bank.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Date;

import edbeca.simarro.bank.Adapters.AdapterCuenta;
import edbeca.simarro.bank.BD.MiBancoOperacional;
import edbeca.simarro.bank.R;
import edbeca.simarro.bank.pojo.Cliente;
import edbeca.simarro.bank.pojo.Cuenta;
import edbeca.simarro.bank.pojo.Movimiento;

public class TransferenciaActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private GridView cuentasOrigen;
    private MiBancoOperacional bo;
    private ArrayAdapter<Cuenta> adaptador;
    private Spinner cuentasDestino;
    private Button btnAceptar;
    private Button btnCancelar;

    private Cliente cliente;
    private Cuenta cuentaOrigen;
    private Cuenta cuentaDestino;
    private Movimiento movimiento;

    private String descripcionTransferencia;
    private EditText edtDescripcion;

    private Float importeTransferencia;
    private EditText edtImporte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferencia);
        bo = MiBancoOperacional.getInstance(this);

        cliente = (Cliente)getIntent().getSerializableExtra("Cliente");

        cuentasOrigen = (GridView) findViewById(R.id.gdCuentas);
        cuentasDestino = (Spinner)findViewById(R.id.spCuentas);
        btnAceptar = (Button)findViewById(R.id.btAceptar);
        btnCancelar = (Button)findViewById(R.id.btCancelar);

        edtImporte = (EditText)findViewById(R.id.editImporte);

        edtDescripcion = (EditText)findViewById(R.id.editTextDescripcion);

        adaptador = new AdapterCuenta<>(this, bo.getCuentas(cliente));

        cuentasOrigen.setAdapter(adaptador);
        cuentasOrigen.setOnItemClickListener(this);


        ArrayAdapter<Cuenta> adaptadorSpCuentas = new ArrayAdapter<Cuenta>(this, android.R.layout.simple_spinner_dropdown_item, bo.getCuentas(cliente));
        adaptadorSpCuentas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cuentasDestino.setAdapter(adaptadorSpCuentas);

        btnAceptar.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if (view.getTag().equals("Cancelar")){
            Toast.makeText(this, "Transferencia no realizada", Toast.LENGTH_SHORT).show();
        } else if (view.getTag().equals("Aceptar")){


            importeTransferencia = Float.parseFloat(edtImporte.getText().toString());
            descripcionTransferencia = edtDescripcion.getText().toString();
            cuentaDestino = (Cuenta)cuentasDestino.getSelectedItem();

            movimiento = new Movimiento(0,new Date(),descripcionTransferencia,importeTransferencia,cuentaOrigen,cuentaDestino);
            bo.transferencia(movimiento);


            Intent intent = new Intent(view.getContext(), PrincipalActivity.class);
            intent.putExtra("Cliente", cliente);
            startActivity(intent);
        }

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        cuentaOrigen = (Cuenta) adapterView.getAdapter().getItem(i);

    }
}