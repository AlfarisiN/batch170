package bootcamp.com.batch170.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import bootcamp.com.batch170.R;
import bootcamp.com.batch170.models.news.Doc;
import bootcamp.com.batch170.viewholder.ViewHolderNewsList;

/**
 * Created by Eric on 11/10/2018.
 */

public class NewsAdapter extends RecyclerView.Adapter<ViewHolderNewsList> {
    private Context context;
    private List<Doc> docList;

    public NewsAdapter(Context context, List<Doc> docList) {
        this.context = context;
        this.docList = docList;
    }

    @Override
    public ViewHolderNewsList onCreateViewHolder(ViewGroup parent, int viewType) {
        View customView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_news_list_layout, parent, false);

        return new ViewHolderNewsList(customView);
    }

    @Override
    public void onBindViewHolder(ViewHolderNewsList holder, int position) {
        Doc doc = docList.get(position);
        holder.setModel(doc, context);


    }

    @Override
    public int getItemCount() {
        if(docList != null){
            return docList.size();
        }
        else {
            return 0;
        }
    }
}
