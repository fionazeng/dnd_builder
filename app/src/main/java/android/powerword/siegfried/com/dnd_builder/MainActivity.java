package android.powerword.siegfried.com.dnd_builder;
import android.databinding.DataBindingUtil;
import android.powerword.siegfried.com.dnd_builder.annotation.ComputedUtils;
import android.powerword.siegfried.com.dnd_builder.annotation.DataStore;
import android.powerword.siegfried.com.dnd_builder.databinding.ActivityMainBinding;
import android.powerword.siegfried.com.dnd_builder.viewmodel.HeroVM;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.databinding.library.baseAdapters.BR;

public class MainActivity extends AppCompatActivity {


    @DataStore(BR.vm)
    HeroVM heroVM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ComputedUtils.injected(this, viewDataBinding);
    }
}