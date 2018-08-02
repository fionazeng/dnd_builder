package android.powerword.siegfried.com.dnd_builder;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.powerword.siegfried.com.dnd_builder.adapter.AttrAdapter;
import android.powerword.siegfried.com.dnd_builder.adapter.SimpleItemTouchHelperCallback;
import android.powerword.siegfried.com.dnd_builder.databinding.ActivityMainBinding;
import android.powerword.siegfried.com.dnd_builder.model.Hero;
import android.powerword.siegfried.com.dnd_builder.model.HeroAttr;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.databinding.library.baseAdapters.BR;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    ArrayList<HeroAttr> arrayList;
    AttrAdapter attrAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Hero hero = new Hero("齐格飞", "人类", "1级", "法师");
        viewDataBinding.setVariable(BR.hero, hero);



        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);

        attrAdapter = new AttrAdapter();


        viewDataBinding.setVariable(BR.manager, layoutManager);
        viewDataBinding.setVariable(BR.adapter, attrAdapter);
        viewDataBinding.setVariable(BR.listener, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    for(int i=0; i< arrayList.size();i++) {
                        int finalValue = getFinalValue();
                        arrayList.get(i).value = finalValue;

                    }

                    attrAdapter.initData(arrayList);

            }
        });

        HeroAttr str = new HeroAttr(HeroAttr.Attrs.STR, 8);
        HeroAttr agi = new HeroAttr(HeroAttr.Attrs.AGI, 8);
        HeroAttr con = new HeroAttr(HeroAttr.Attrs.CON, 8);
        HeroAttr wis = new HeroAttr(HeroAttr.Attrs.WIS, 8);
        HeroAttr intelligent = new HeroAttr(HeroAttr.Attrs.INT, 8);
        HeroAttr charm = new HeroAttr(HeroAttr.Attrs.CHAR, 8);


        arrayList = new ArrayList<>();

        arrayList.add((str));
        arrayList.add((agi));
        arrayList.add((con));
        arrayList.add((wis));
        arrayList.add((intelligent));
        arrayList.add((charm));


        attrAdapter.initData(arrayList);


        //创建SimpleItemTouchHelperCallback
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(attrAdapter);
        //用Callback构造ItemtouchHelper
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        //调用ItemTouchHelper的attachToRecyclerView方法建立联系
        touchHelper.attachToRecyclerView(viewDataBinding.recyclerView);

//        RecyclerView recyclerView;
//        recyclerView.setLayoutManager();

    }




    private int getFinalValue() {
        Double[] values = {
                new Double(Math.floor(Math.random() * 6 + 1)),
                new Double(Math.floor(Math.random() * 6 + 1)),
                new Double(Math.floor(Math.random() * 6 + 1)),
                new Double(Math.floor(Math.random() * 6 + 1))
        };


        ArrayList<Double> array = new ArrayList<>();
        Collections.addAll(array, values);
        Collections.sort(array);
        array.remove(0);
        int finalValue = 0;
        for(int j = 0;j < array.size();j++) {
            finalValue += array.get(j);
        }
        return finalValue > 6 ? finalValue : getFinalValue();
    }


}
