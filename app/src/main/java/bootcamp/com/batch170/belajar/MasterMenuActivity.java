package bootcamp.com.batch170.belajar;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import bootcamp.com.batch170.R;
import bootcamp.com.batch170.adapters.TabLayoutAdapter;

public class MasterMenuActivity extends AppCompatActivity {
    private Context context = this;

    private TabLayout sliding_tabs;
    private ViewPager viewpager;
    private TabLayoutAdapter tabLayoutAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_menu);

        sliding_tabs = (TabLayout) findViewById(R.id.sliding_tabs);
        viewpager = (ViewPager) findViewById(R.id.viewpager);

        tabLayoutAdapter = new TabLayoutAdapter(context, getSupportFragmentManager());
        viewpager.setAdapter(tabLayoutAdapter);

        sliding_tabs.setupWithViewPager(viewpager);
    }
}
