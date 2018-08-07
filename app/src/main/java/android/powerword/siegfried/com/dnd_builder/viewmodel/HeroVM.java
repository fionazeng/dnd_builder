package android.powerword.siegfried.com.dnd_builder.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.powerword.siegfried.com.dnd_builder.annotation.Computed;
import android.powerword.siegfried.com.dnd_builder.annotation.Data;
import android.powerword.siegfried.com.dnd_builder.annotation.Method;
import android.powerword.siegfried.com.dnd_builder.model.Hero;
import android.powerword.siegfried.com.dnd_builder.model.HeroAttrs;
import android.powerword.siegfried.com.dnd_builder.presenter.Presenter;
import android.support.annotation.NonNull;
import android.view.View;

import com.android.databinding.library.baseAdapters.BR;
public class HeroVM extends AndroidViewModel {

    public HeroVM(@NonNull Application application) {
        super(application);
        heroAttrs = new MutableLiveData<>();
        myHero = new Hero("齐格飞", "人类", "1级", "法师");
        presenter = new Presenter();

    }

    @Computed(BR.heroAttrs)
    public MutableLiveData<HeroAttrs> heroAttrs;
    @Data(BR.hero)
    public Hero myHero ;

    protected Presenter presenter;

    @Method(BR.onClickListener)
    public void onClick(){
        HeroAttrs heroAttrs = presenter.getHeroAttrs();
        this.heroAttrs.setValue(heroAttrs);
    }
}
