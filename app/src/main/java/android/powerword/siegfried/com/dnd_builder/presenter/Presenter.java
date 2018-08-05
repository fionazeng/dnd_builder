package android.powerword.siegfried.com.dnd_builder.presenter;

import android.databinding.ViewDataBinding;
import android.powerword.siegfried.com.dnd_builder.model.HeroAttrs;

import java.util.ArrayList;
import java.util.Collections;

public class Presenter {

    public Presenter() {
    }
    public HeroAttrs getHeroAttrs() {
        int[] values = new int[6];
        for(int i=0; i< 6;i++) {
            values[i] = getFinalValue();
        }
        return HeroAttrs.getHeroAttrs(values);
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
