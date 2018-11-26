package bootcamp.com.batch170.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import bootcamp.com.batch170.R;
import bootcamp.com.batch170.models.news.Doc;
import bootcamp.com.batch170.models.news.Multimedium;
import bootcamp.com.batch170.retrofit.APIUtilities;

/**
 * Created by Eric on 11/10/2018.
 */

public class ViewHolderNewsList extends RecyclerView.ViewHolder{
    private ImageView newsIcon;
    private TextView newsSnippet, newsSource, newsCount;

    public ViewHolderNewsList(View itemView) {
        super(itemView);

        newsIcon = (ImageView) itemView.findViewById(R.id.newsIcon);
        newsSnippet = (TextView) itemView.findViewById(R.id.newsSnippet);
        newsSource = (TextView) itemView.findViewById(R.id.newsSource);
        newsCount = (TextView) itemView.findViewById(R.id.newsCount);
    }

    public void setModel(Doc doc, Context context){
        //ambil url image dari multimedia, tapi index ke 0 saja
        List<Multimedium> multimedia = doc.getMultimedia();
        if(multimedia.size() > 0){
            String url = APIUtilities.BASE_IMAGE_URL_NYTIMES + multimedia.get(0).getUrl();
            Picasso.get().load(url).into(newsIcon);
        }

        newsSnippet.setText(doc.getSnippet());
        newsSource.setText(doc.getSource());
        newsCount.setText(""+doc.getWordCount());

    }
}
