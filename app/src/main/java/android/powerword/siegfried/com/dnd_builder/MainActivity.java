package android.powerword.siegfried.com.dnd_builder;

import android.powerword.siegfried.com.dnd_builder.adapter.AttrAdapter;
import android.powerword.siegfried.com.dnd_builder.model.HeroAttr;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        TextView tvName = findViewById(R.id.tv_name);
        TextView tvRace = findViewById(R.id.tv_race);
        TextView tvLevel = findViewById(R.id.tv_level);
        TextView tvClass = findViewById(R.id.tv_class);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);

        HeroAttr str = new HeroAttr(HeroAttr.Attrs.STR, 8);
        HeroAttr agi = new HeroAttr(HeroAttr.Attrs.AGI, 8);
        HeroAttr con = new HeroAttr(HeroAttr.Attrs.CON, 8);
        HeroAttr wis = new HeroAttr(HeroAttr.Attrs.WIS, 8);
        HeroAttr intelligent = new HeroAttr(HeroAttr.Attrs.INT, 8);
        HeroAttr charm = new HeroAttr(HeroAttr.Attrs.CHAR, 8);

        ArrayList<HeroAttr> arrayList = new ArrayList<>();

        arrayList.add((str));
        arrayList.add((agi));
        arrayList.add((con));
        arrayList.add((wis));
        arrayList.add((intelligent));
        arrayList.add((charm));

        AttrAdapter attrAdapter = new AttrAdapter();
        attrAdapter.initData(arrayList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(attrAdapter);

    }
}
