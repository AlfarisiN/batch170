package bootcamp.com.batch170.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import bootcamp.com.batch170.R;
import bootcamp.com.batch170.models.employee.DataList;
import bootcamp.com.batch170.viewholder.ViewHolderEmployeeList;


/**
 * Created by Eric on 25/10/2018.
 */

public class EmployeeListAdapter extends RecyclerView.Adapter<ViewHolderEmployeeList> {
    private Context context;
    private List<DataList> dataList;

    public EmployeeListAdapter(Context context, List<DataList> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public ViewHolderEmployeeList onCreateViewHolder(ViewGroup parent, int viewType) {
        View customView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.custom_employee_list_layout,
                parent,
                false
        );

        return new ViewHolderEmployeeList(customView);
    }

    @Override
    public void onBindViewHolder(ViewHolderEmployeeList holder, int position) {
        final DataList data = dataList.get(position);
        holder.setModel(data, position);
    }

    @Override
    public int getItemCount() {
        if(dataList == null) {
            return 0;
        }
        else{
            return dataList.size();
        }
    }
}
