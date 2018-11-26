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

import bootcamp.com.batch170.R;
import bootcamp.com.batch170.belajar.CameraActivity;
import bootcamp.com.batch170.belajar.ImagePickerActivity;
import bootcamp.com.batch170.belajar.ImageSliderActivity;
import bootcamp.com.batch170.belajar.MarcommActivity;
import bootcamp.com.batch170.belajar.MasterMenuActivity;
import bootcamp.com.batch170.belajar.MenuNavigasiActivity;
import bootcamp.com.batch170.belajar.RetrofitActivity;
import bootcamp.com.batch170.belajar.RetrofitAdvanceActivity;
import bootcamp.com.batch170.belajar.VolleyActivity;
import bootcamp.com.batch170.belajar.VolleyAdvanceActivity;
import bootcamp.com.batch170.belajar.VolleyMultilevelJSONActivity;
import bootcamp.com.batch170.database1.DaftarMahasiswaActivity;
import bootcamp.com.batch170.database2.DaftarBukuActivity;

/**
 * Created by Eric on 10/10/2018.
 */

public class CustomListAdapter extends BaseAdapter{
    private Context context;
    private int[] LIST_ICON;
    private String[] LIST_TITLE;
    private String[] LIST_DESCRIPTION;

    public CustomListAdapter(Context context,
                             int[] LIST_ICON,
                             String[] LIST_TITLE,
                             String[] LIST_DESCRIPTION){
        this.context = context;
        this.LIST_ICON = LIST_ICON;
        this.LIST_TITLE = LIST_TITLE;
        this.LIST_DESCRIPTION = LIST_DESCRIPTION;
    }

    @Override
    public int getCount() {
        return LIST_ICON.length;
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

        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.custom_list_layout, null);

            holder.iconList = (ImageView) convertView.findViewById(R.id.iconList);
            holder.titleList = (TextView) convertView.findViewById(R.id.titleList);
            holder.descriptionList = (TextView) convertView.findViewById(R.id.descriptionList);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        //set element list
        holder.iconList.setImageResource(LIST_ICON[position]);
        holder.titleList.setText(LIST_TITLE[position]);
        holder.descriptionList.setText(LIST_DESCRIPTION[position]);

        //deklarasikan aksi klik
        LinearLayout linearLayout = (LinearLayout) convertView.findViewById(R.id.listLayout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position == 0){
                    //list index ke 0 di klik
                    Intent intent = new Intent(context, VolleyActivity.class);
                    context.startActivity(intent);
                }
                else if(position == 1){
                    //activity ke 2
                    Intent intent = new Intent(context, VolleyAdvanceActivity.class);
                    context.startActivity(intent);
                }
                else if(position == 2){
                    //activity ke 3
                    Intent intent = new Intent(context, VolleyMultilevelJSONActivity.class);
                    context.startActivity(intent);
                }
                else if(position == 3){
                    //activity ke 4
                    Intent intent = new Intent(context, RetrofitActivity.class);
                    context.startActivity(intent);
                }
                else if(position == 4){
                    //activity ke 5
                    Intent intent = new Intent(context, RetrofitAdvanceActivity.class);
                    context.startActivity(intent);
                }
                else if(position == 5){
                    Intent intent = new Intent(context, CameraActivity.class);
                    context.startActivity(intent);
                }
                else if(position == 6){
                    Intent intent = new Intent(context, ImagePickerActivity.class);
                    context.startActivity(intent);
                }
                else if(position == 7){
                    Intent intent = new Intent(context, ImageSliderActivity.class);
                    context.startActivity(intent);
                }
                else if(position == 8){
                    Intent intent = new Intent(context, DaftarMahasiswaActivity.class);
                    context.startActivity(intent);
                }
                else if(position == 9){
                    Intent intent = new Intent(context, DaftarBukuActivity.class);
                    context.startActivity(intent);
                }
                else if(position == 10){
                    Intent intent = new Intent(context, MasterMenuActivity.class);
                    context.startActivity(intent);
                }
                else if(position == 11){
                    Intent intent = new Intent(context, MenuNavigasiActivity.class);
                    context.startActivity(intent);
                }
                else if(position == 12){
                    Intent intent = new Intent(context, MarcommActivity.class);
                    context.startActivity(intent);
                }
            }
        });

        convertView.setTag(holder);
        return convertView;
    }

    //inner class utk ViewHolder
    class ViewHolder {
        ImageView iconList;
        TextView titleList;
        TextView descriptionList;
    }
}
