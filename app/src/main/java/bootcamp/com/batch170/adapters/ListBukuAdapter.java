package bootcamp.com.batch170.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import bootcamp.com.batch170.R;
import bootcamp.com.batch170.models.Buku;
import bootcamp.com.batch170.viewholder.ViewHolderListBuku;

/**
 * Created by Eric on 17/10/2018.
 */

public class ListBukuAdapter extends RecyclerView.Adapter<ViewHolderListBuku> {
    private Context context;
    private List<Buku> bukuList;

    public ListBukuAdapter(Context context, List<Buku> bukuList) {
        this.context = context;
        this.bukuList = bukuList;
    }

    @Override
    public ViewHolderListBuku onCreateViewHolder(ViewGroup parent, int viewType) {
        View customView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.custom_list_buku_layout,
                parent,
                false
        );

        return new ViewHolderListBuku(customView);
    }

    @Override
    public void onBindViewHolder(ViewHolderListBuku holder, int position) {
        if(bukuList.size() > 0) {
            Buku buku = bukuList.get(position);
            holder.setBuku(context, buku);
        }
    }

    @Override
    public int getItemCount() {
        if(bukuList == null) {
            return 0;
        }
        else{
            return bukuList.size();
        }
    }
}
