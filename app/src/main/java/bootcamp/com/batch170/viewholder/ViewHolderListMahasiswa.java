package bootcamp.com.batch170.viewholder;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import bootcamp.com.batch170.R;

/**
 * Created by Eric on 16/10/2018.
 */

public class ViewHolderListMahasiswa extends RecyclerView.ViewHolder {
    public LinearLayout listMahasiswa;
    private TextView namaMahasiswa;

    public ViewHolderListMahasiswa(View itemView) {
        super(itemView);

        listMahasiswa = (LinearLayout) itemView.findViewById(R.id.listMahasiswa);
        namaMahasiswa = (TextView) itemView.findViewById(R.id.namaMahasiswa);
    }

    public void setNamaMahasiswa(Context context, String nama, int position){
        if(nama != null) {
            namaMahasiswa.setText(nama);
        }

        if(position%2 == 1){
           listMahasiswa.setBackgroundColor(Color.GRAY);
        }
        else{
            listMahasiswa.setBackgroundColor(Color.CYAN);
        }
    }
}
