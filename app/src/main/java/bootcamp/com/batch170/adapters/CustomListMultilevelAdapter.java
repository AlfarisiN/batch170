package bootcamp.com.batch170.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import bootcamp.com.batch170.R;
import bootcamp.com.batch170.models.ModelUser;

/**
 * Created by Eric on 11/10/2018.
 */

public class CustomListMultilevelAdapter extends BaseAdapter {
    private Context context;
    private List<ModelUser> models;

    public CustomListMultilevelAdapter(Context context, List<ModelUser> models) {
        this.context = context;
        this.models = models;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        ViewHolderMultilevel holder;
        if(convertView == null){
            holder = new ViewHolderMultilevel();
            convertView = inflater.inflate
                    (R.layout.custom_list_multilevel_layout, null);

            holder.namaUser = (TextView) convertView.findViewById(R.id.namaUser);
            holder.alamatUser = (TextView) convertView.findViewById(R.id.alamatUser);
            holder.koordinatUser = (TextView) convertView.findViewById(R.id.koordinatUser);

            holder.listMultilevel = (LinearLayout) convertView.findViewById(R.id.listMultilevel);
        }
        else{
            holder = (ViewHolderMultilevel) convertView.getTag();
        }

        //set value
        ModelUser model = models.get(position);
        String name = model.getName();
        //nama user
        holder.namaUser.setText(name);

        //alamat
        String alamat = model.getStreet() + " - "
                + model.getSuite() + ", "
                + model.getCity();
        holder.alamatUser.setText(alamat);

        //koordinat
        String koordinat = model.getLat() + ", " + model.getLng();
        holder.koordinatUser.setText(koordinat);

        //set background color
        if(position%2 == 1){
            //ganjil
            holder.listMultilevel.setBackgroundColor(Color.CYAN);
        }
        else{
            //genap
            holder.listMultilevel.setBackgroundColor(Color.YELLOW);
        }

        convertView.setTag(holder);
        return convertView;
    }

    class ViewHolderMultilevel {
        TextView namaUser;
        TextView alamatUser;
        TextView koordinatUser;

        LinearLayout listMultilevel;
    }
}
