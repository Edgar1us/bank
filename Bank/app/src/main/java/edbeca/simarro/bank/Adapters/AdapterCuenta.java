package edbeca.simarro.bank.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import edbeca.simarro.bank.R;
import edbeca.simarro.bank.pojo.Cuenta;

public class AdapterCuenta<T> extends ArrayAdapter<T> {
    public AdapterCuenta(Context context, List<T> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Obteniendo una instancia del inflater
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //Salvando la referencia del View de la fila
        View listItemView = convertView;
        //Comprobando si el View no existe
        if (null == convertView) {
            //Si no existe, entonces inflarlo con two_line_list_item.xml
            listItemView = inflater.inflate(R.layout.elementos_cuentas_list,parent,false);
        }
        //Obteniendo instancias de los text views
        TextView cuenta = (TextView)listItemView.findViewById(R.id.numCuenta);
        TextView saldo = (TextView)listItemView.findViewById(R.id.saldo);
        //Obteniendo instancia del ítem en la posición actual
        Cuenta item = (Cuenta) getItem(position);
        cuenta.setText(item.getNumeroCuenta());
        saldo.setText(Float.toString(item.getSaldoActual()));

        if (item.getSaldoActual()<0){
            saldo.setTextColor(Color.parseColor("#FFFF0009"));
        }else{
            saldo.setTextColor(Color.parseColor("#005004"));
        }

        //Devolver al ListView la fila creada
        return listItemView;
    }
}
