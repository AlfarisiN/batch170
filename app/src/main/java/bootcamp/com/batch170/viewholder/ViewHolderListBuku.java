package bootcamp.com.batch170.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import bootcamp.com.batch170.R;
import bootcamp.com.batch170.models.Buku;

/**
 * Created by Eric on 17/10/2018.
 */

public class ViewHolderListBuku extends RecyclerView.ViewHolder {
    private TextView judulBuku, infoBuku;

    public ViewHolderListBuku(View itemView) {
        super(itemView);

        judulBuku = (TextView) itemView.findViewById(R.id.judulBuku);
        infoBuku = (TextView) itemView.findViewById(R.id.infoBuku);
    }

    public void setBuku(Context context, Buku buku){
        if(buku != null){
            judulBuku.setText(buku.getJudul_buku());

            infoBuku.setText("Rp "+buku.getHarga() + ", " + buku.getStok());
        }
    }
}
