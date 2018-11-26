package bootcamp.com.batch170.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import bootcamp.com.batch170.R;
import bootcamp.com.batch170.models.employee.DataList;
import bootcamp.com.batch170.models.employee.Employee;

/**
 * Created by Eric on 25/10/2018.
 */

public class ViewHolderEmployeeList extends RecyclerView.ViewHolder{
    private TextView userID, userName, roleCode, roleName, firstName, lastName;

    public ViewHolderEmployeeList(View itemView) {
        super(itemView);

        userID = (TextView) itemView.findViewById(R.id.userID);
        userName = (TextView) itemView.findViewById(R.id.userName);
        roleCode = (TextView) itemView.findViewById(R.id.roleCode);
        roleName = (TextView) itemView.findViewById(R.id.roleName);
        firstName = (TextView) itemView.findViewById(R.id.firstName);
        lastName = (TextView) itemView.findViewById(R.id.lastName);
    }

    public void setModel(DataList dataList, int index){
        if(dataList.getId() != null) userID.setText(""+dataList.getId());
        if(dataList.getUsername() != null) userName.setText(dataList.getUsername());

        if(dataList.getRole().getCode() != null) roleCode.setText(""+dataList.getRole().getCode());
        if(dataList.getRole().getName() != null) roleName.setText(dataList.getRole().getName());

        if(dataList.getEmployee().getFirstName() != null) firstName.setText(dataList.getEmployee().getFirstName());
        if(dataList.getEmployee().getLastName() != null) lastName.setText(dataList.getEmployee().getLastName());
    }
}