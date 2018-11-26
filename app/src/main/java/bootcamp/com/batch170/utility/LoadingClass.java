package bootcamp.com.batch170.utility;

import android.app.ProgressDialog;
import android.content.Context;

import bootcamp.com.batch170.R;

/**
 * Created by Eric on 10/10/2018.
 */

public class LoadingClass {
    public static ProgressDialog loadingAnimationAndText(Context context, String text){
        ProgressDialog loading = new ProgressDialog(context, R.style.SimpleLoadingStyle);
        loading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        loading.setCancelable(false);
        loading.setCanceledOnTouchOutside(false);
        loading.setMessage(text);

        return loading;
    }

    public static ProgressDialog loadingAnimation(Context context){
        ProgressDialog loading = new ProgressDialog(context, R.style.SimpleLoadingStyle);
        loading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        loading.setCancelable(false);
        loading.setCanceledOnTouchOutside(false);

        return loading;
    }
}
