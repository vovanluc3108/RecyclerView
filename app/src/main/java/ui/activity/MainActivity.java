package ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.framgia.bang.recyclerview.R;
import data.model.Language;
import java.util.ArrayList;
import ui.adapter.LanguageAdapter;
import ui.adapter.LoadMore;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    private RecyclerView mRecyclerView;
    private ArrayList<Language> mLanguages;
    private LanguageAdapter mLanguageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addEvents() {

        mLanguages = new ArrayList<>();
        mLanguages.add(new Language("JaaAVA"));
        mLanguages.add(new Language("JAVA"));
        mLanguages.add(new Language("JAA"));
        mLanguages.add(new Language("JAVA"));
        mLanguages.add(new Language("JAVA"));
        mLanguages.add(new Language("JAVA"));
        mLanguages.add(new Language("JAVA"));
        mLanguages.add(new Language("JAVA"));
        mLanguages.add(new Language("JAVA"));
        mLanguages.add(new Language("JAVA"));mLanguages.add(new Language("JAVA"));mLanguages.add(new Language("JAVA"));mLanguages.add(new Language("JAVA"));mLanguages.add(new Language("JAVA"));mLanguages.add(new Language("JAVA"));mLanguages.add(new Language("JAVA"));mLanguages.add(new Language("JAVA"));mLanguages.add(new Language("JAVA"));


        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mLanguageAdapter = new LanguageAdapter(this, mLanguages, mRecyclerView);
        mRecyclerView.setAdapter(mLanguageAdapter);

        mRecyclerView.setOnTouchListener(this);

        mLanguageAdapter.setLoadMore(new LoadMore() {
            @Override
            public void onLoadMore() {
                if (mLanguages.size() <= 8){
                    mLanguages.add(null);
                    mLanguageAdapter.notifyItemInserted(mLanguages.size() -1);
                }
                else {
                    Toast.makeText(MainActivity.this, "load success", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void addControls() {
        mRecyclerView = findViewById(R.id.recycler_main);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Log.d("aaaa", "onTouch: ");
        return false;
    }
}
