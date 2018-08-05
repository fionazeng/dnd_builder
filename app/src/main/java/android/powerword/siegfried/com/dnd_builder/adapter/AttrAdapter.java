package android.powerword.siegfried.com.dnd_builder.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.powerword.siegfried.com.dnd_builder.R;
import android.powerword.siegfried.com.dnd_builder.model.HeroAttrItem;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;

import java.util.ArrayList;
import java.util.Collections;

public class AttrAdapter extends RecyclerView.Adapter<AttrAdapter.AttrHolder> implements ItemTouchHelperAdapter {


    private ArrayList<HeroAttrItem> arraylist;

    @NonNull
    @Override
    public AttrHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.holder_attr, parent, false);
        return new AttrHolder(view) ;
    }

    public HeroAttrItem getDataByPosition(int position) {
        return this.arraylist == null ?null: this.arraylist.get(position);
    }


    @Override
    public void onBindViewHolder(@NonNull AttrHolder holder, int position) {
        ViewDataBinding bind = DataBindingUtil.bind(holder.itemView);
        bind.setVariable(BR.attrs, getDataByPosition(position));
    }

    @Override
    public int getItemCount() {
        return this.arraylist == null ? 0 : this.arraylist.size();
    }

    public void initData(ArrayList<HeroAttrItem> arrayList) {
        this.arraylist= arrayList;
        notifyDataSetChanged();
    }




    public void reloadedNotify(int fromPosition, int toPosition) {
        HeroAttrItem fromValue = this.getDataByPosition(fromPosition);
        HeroAttrItem toValue = this.getDataByPosition(toPosition);
        HeroAttrItem copyValue1 = new HeroAttrItem(fromValue.attrs, toValue.value);
        HeroAttrItem copyValue2 = new HeroAttrItem(toValue.attrs, fromValue.value);
        arraylist.set(fromPosition, copyValue2);
        arraylist.set(toPosition, copyValue1);
        notifyItemMoved(fromPosition, toPosition);
    }

    private void reloadData() {
        notifyDataSetChanged();
    }

    @Override
    public void onItemMove(RecyclerView.ViewHolder source, RecyclerView.ViewHolder target) {
        int fromPosition = source.getAdapterPosition();
        int toPosition = target.getAdapterPosition();
        if (fromPosition < arraylist.size() && toPosition < arraylist.size()) {
            //交换数据位置
            Collections.swap(arraylist, fromPosition, toPosition);
            //刷新位置交换
//            notifyItemMoved(fromPosition, toPosition);
            reloadedNotify(fromPosition, toPosition);
        }
        //移动过程中移除view的放大效果
        onItemClear(source);
    }



    @Override
    public void onItemDissmiss(RecyclerView.ViewHolder source) {
        int position = source.getAdapterPosition();
        arraylist.remove(position); //移除数据
        notifyItemRemoved(position);//刷新数据移除
    }

    @Override
    public void onItemSelect(RecyclerView.ViewHolder source) {
        //当拖拽选中时放大选中的view
        source.itemView.setScaleX(1.2f);
        source.itemView.setScaleY(1.2f);
    }

    @Override
    public void onItemClear(RecyclerView.ViewHolder source) {
        //拖拽结束后恢复view的状态
        source.itemView.setScaleX(1.0f);
        source.itemView.setScaleY(1.0f);
    }

    public class AttrHolder extends RecyclerView.ViewHolder{
        public AttrHolder(View itemView) {
            super(itemView);
        }
    }


}
