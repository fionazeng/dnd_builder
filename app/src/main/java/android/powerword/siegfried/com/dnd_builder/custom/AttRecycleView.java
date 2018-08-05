package android.powerword.siegfried.com.dnd_builder.custom;

import android.content.Context;
import android.powerword.siegfried.com.dnd_builder.adapter.AttrAdapter;
import android.powerword.siegfried.com.dnd_builder.model.HeroAttrItem;
import android.powerword.siegfried.com.dnd_builder.model.HeroAttrs;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import java.util.ArrayList;

public class AttRecycleView extends RecyclerView {
    private AttrAdapter attrAdapter;

    public AttRecycleView(Context context) {
        this(context, null, 0);
    }

    public AttRecycleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AttRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
        initHeroAttrs();
    }

    private void init() {
        GridLayoutManager layoutManager = new GridLayoutManager(this.getContext(), 2);

        attrAdapter = new AttrAdapter();
        this.setLayoutManager(layoutManager);
        this.setAdapter(attrAdapter);
    }

    public void setHeroAttrs(HeroAttrs heroAttrs) {
        if(heroAttrs == null) {
            return ;
        }
       this.setHeroAttrs(heroAttrs.attrs);
    }
    private void initHeroAttrs() {
        HeroAttrs attrs = HeroAttrs.getInitHeroAttrs();
        this.setHeroAttrs(attrs);
    }

    private void setHeroAttrs(ArrayList<HeroAttrItem> arrayList) {
        this.attrAdapter.initData(arrayList);
    }
}
