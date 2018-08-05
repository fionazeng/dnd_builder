package android.powerword.siegfried.com.dnd_builder.custom;

import android.content.Context;
import android.graphics.Color;
import android.powerword.siegfried.com.dnd_builder.R;
import android.powerword.siegfried.com.dnd_builder.model.Hero;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HeroLinearlayout extends LinearLayout {
    public HeroLinearlayout(Context context) {
        this(context, null, 0,0);
    }

    public HeroLinearlayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0,0);
    }

    public HeroLinearlayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public HeroLinearlayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        this.setOrientation(LinearLayout.HORIZONTAL);
        for(int index = 0; index < 4;index ++) {
            LinearLayout.LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            if(index != 0) {
                layoutParams.leftMargin = 20;
            }
            TextView textView = new TextView(this.getContext());
            textView.setTextColor(Color.WHITE);
            textView.setGravity(Gravity.CENTER);
            this.addView(textView, layoutParams);
//            textView.setId();
        }
    }

    public void setHero(Hero hero) {
        TextView name = (TextView) this.getChildAt(0);
        TextView race = (TextView) this.getChildAt(1);
        TextView level = (TextView) this.getChildAt(2);
        TextView clazz = (TextView) this.getChildAt(3);
        name.setText(hero.name);
        race.setText(hero.race);
        level.setText(hero.level);
        clazz.setText(hero.clazz);
    }
}
