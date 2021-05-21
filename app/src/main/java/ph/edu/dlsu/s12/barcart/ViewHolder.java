package ph.edu.dlsu.s12.barcart;



import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {

    private TextView name;
    private final TextView barcode;
    private TextView desc;

    public ViewHolder(@NonNull View v) {
        super(v);

        this.name = v.findViewById(R.id.name_tv);
        this.barcode = v.findViewById(R.id.barcode_tv);
        this.desc = v.findViewById(R.id.desc_tv);

    }
    public void setProductName(String name) { this.name.setText(name); }
    public void setBarcode(String barcode) {
        this.barcode.setText(barcode);
    }
    public void setProductDesc(String desc) {
        this.desc.setText(desc);
    }

}