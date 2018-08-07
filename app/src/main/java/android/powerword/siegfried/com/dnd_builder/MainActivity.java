package android.powerword.siegfried.com.dnd_builder;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.powerword.siegfried.com.dnd_builder.annotation.Wizard;
import android.powerword.siegfried.com.dnd_builder.annotation.DataStore;
import android.powerword.siegfried.com.dnd_builder.databinding.ActivityMainBinding;
import android.powerword.siegfried.com.dnd_builder.network.Request;
import android.powerword.siegfried.com.dnd_builder.network.OnHttp;
import android.powerword.siegfried.com.dnd_builder.network.ResultData;
import android.powerword.siegfried.com.dnd_builder.network.ReturnType;
import android.powerword.siegfried.com.dnd_builder.viewmodel.HeroVM;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.databinding.library.baseAdapters.BR;

public class MainActivity extends AppCompatActivity implements OnHttp {


    @DataStore(BR.vm)
    HeroVM heroVM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        heroVM = ViewModelProviders.of(this).get(HeroVM.class);
        Wizard.injected(this, viewDataBinding);

        Request.getApi().login("guest", "123", this);
    }

    @Override
    public void onHttpResult(int requestCode, ReturnType type, ResultData resultData) {
        Log.i(this.getClass().getSimpleName(), resultData.get().toString());
    }
}