package android.powerword.siegfried.com.dnd_builder.adapter;

import android.powerword.siegfried.com.dnd_builder.R;
import android.powerword.siegfried.com.dnd_builder.model.HeroAttr;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AttrAdapter extends RecyclerView.Adapter<AttrAdapter.AttrHolder> {


    private ArrayList<HeroAttr> arraylist;

    @NonNull
    @Override
    public AttrHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.holder_attr, parent, false);
        return new AttrHolder(view) ;
    }

    public HeroAttr getDataByPosition(int position) {
        return this.arraylist == null ?null: this.arraylist.get(position);
    }
    @Override
    public void onBindViewHolder(@NonNull AttrHolder holder, int position) {
        holder.fillData(getDataByPosition(position));
    }

    @Override
    public int getItemCount() {
        return this.arraylist == null ? 0 : this.arraylist.size();
    }

    public void initData(ArrayList<HeroAttr> arrayList) {
        this.arraylist= arrayList;
        notifyDataSetChanged();
    }

    public class AttrHolder extends RecyclerView.ViewHolder{
        TextView tvBonus;
        TextView tvTitle;
        TextView tvValue;
        public AttrHolder(View itemView) {
            super(itemView);
            init(itemView);
        }

        public void init(View view) {

            tvTitle = view.findViewById(R.id.tv_title);

            tvValue = view.findViewById(R.id.tv_value);

            tvBonus = view.findViewById(R.id.tv_bonus);
        }

        public void fillData(HeroAttr attr) {
            tvTitle.setText(attr.attrs.getTitle());
            tvValue.setText(attr.value + "");
            tvBonus.setText(attr.getBonus() + "");
        }
    }
}
