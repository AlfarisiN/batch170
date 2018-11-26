package bootcamp.com.batch170.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import bootcamp.com.batch170.R;
import bootcamp.com.batch170.belajar.DetailPhotoActivity;
import bootcamp.com.batch170.belajar.VolleyActivity;
import bootcamp.com.batch170.belajar.VolleyAdvanceActivity;
import bootcamp.com.batch170.models.ModelPhotos;
import bootcamp.com.batch170.utility.TemporaryData;

/**
 * Created by Eric on 10/10/2018.
 */

public class CustomListAdvanceAdapter extends BaseAdapter{
    private Context context;
    private List<ModelPhotos> models;

    public CustomListAdvanceAdapter(Context context,
                                    List<ModelPhotos> models) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        ViewHolderAdvance holder;
        if(convertView == null){
            holder = new ViewHolderAdvance();
            convertView = inflater.inflate(R.layout.custom_list_advance_layout, null);

            holder.iconAdvance = (ImageView) convertView.findViewById(R.id.iconAdvance);
            holder.titleAdvance = (TextView) convertView.findViewById(R.id.titleAdvance);
        }
        else{
            holder = (ViewHolderAdvance) convertView.getTag();
        }

        //set icon
        String urlImage = models.get(position).getThumbnail();
        Picasso.get().load(urlImage).into(holder.iconAdvance);

        //set title
        String title = models.get(position).getTitle();
        holder.titleAdvance.setText(title);

        //handling click
        LinearLayout list = (LinearLayout)  convertView.findViewById(R.id.listAdvance);
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TemporaryData.setModelPhotos(models.get(position));

                Intent intent = new Intent(context, DetailPhotoActivity.class);
                context.startActivity(intent);
            }
        });

        convertView.setTag(holder);
        return convertView;
    }

    class ViewHolderAdvance{
        ImageView iconAdvance;
        TextView titleAdvance;
    }
}
