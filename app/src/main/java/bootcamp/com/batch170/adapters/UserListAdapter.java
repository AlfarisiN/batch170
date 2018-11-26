package bootcamp.com.batch170.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import bootcamp.com.batch170.R;
import bootcamp.com.batch170.models.listuser.Datum;
import bootcamp.com.batch170.viewholder.ViewHolderUserList;

/**
 * Created by Eric on 12/10/2018.
 */

public class UserListAdapter extends RecyclerView.Adapter<ViewHolderUserList> {
    private Context context;
    private List<Datum> userList;

    public UserListAdapter(Context context, List<Datum> userList) {
        this.context = context;
        this.userList = userList;
    }

    @Override
    public ViewHolderUserList onCreateViewHolder(ViewGroup parent, int viewType) {
        View customView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.custom_list_user_layout,
                parent,
                false
        );

        return new ViewHolderUserList(customView);
    }

    @Override
    public void onBindViewHolder(ViewHolderUserList holder, final int position) {
        final Datum user = userList.get(position);
        holder.setModel(user, position, context);

        holder.layoutUserList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Ini list index ke : "+position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if(userList != null){
            return userList.size();
        }
        else {
            return 0;
        }
    }
}
