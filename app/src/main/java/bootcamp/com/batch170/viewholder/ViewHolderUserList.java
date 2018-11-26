package bootcamp.com.batch170.viewholder;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import bootcamp.com.batch170.R;
import bootcamp.com.batch170.models.listuser.Datum;

/**
 * Created by Eric on 12/10/2018.
 */

public class ViewHolderUserList extends RecyclerView.ViewHolder{
    private TextView fullName, number;
    private ImageView avatar;
    public LinearLayout layoutUserList;

    public ViewHolderUserList(View itemView) {
        super(itemView);

        fullName = (TextView) itemView.findViewById(R.id.fullName);
        number = (TextView) itemView.findViewById(R.id.number);
        avatar = (ImageView) itemView.findViewById(R.id.avatar);
        layoutUserList = (LinearLayout) itemView.findViewById(R.id.layoutUserList);
    }

    public void setModel(Datum user, int index, Context context){
        //set fullname
        String fullname = user.getFirstName()+" "+user.getLastName();
        fullName.setText(fullname);

        //set avatar
        String url = user.getAvatar();
        Glide.with(context).load(url).into(avatar);

        if(index%2 == 1){
            layoutUserList.setBackgroundColor(Color.YELLOW);
        }
        else{
            layoutUserList.setBackgroundColor(Color.rgb(0,255,0));
        }

        //set number
        int numberCount = index+1;
        number.setText(""+numberCount);
    }
}
