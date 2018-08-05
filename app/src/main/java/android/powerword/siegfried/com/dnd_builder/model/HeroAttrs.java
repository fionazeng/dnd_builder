package android.powerword.siegfried.com.dnd_builder.model;

import java.util.ArrayList;

public class HeroAttrs {
    public ArrayList<HeroAttrItem> attrs;

    private HeroAttrs() {
        HeroAttrItem str = new HeroAttrItem(HeroAttrItem.Attrs.STR, 8);
        HeroAttrItem agi = new HeroAttrItem(HeroAttrItem.Attrs.AGI, 8);
        HeroAttrItem con = new HeroAttrItem(HeroAttrItem.Attrs.CON, 8);
        HeroAttrItem wis = new HeroAttrItem(HeroAttrItem.Attrs.WIS, 8);
        HeroAttrItem intelligent = new HeroAttrItem(HeroAttrItem.Attrs.INT, 8);
        HeroAttrItem charm = new HeroAttrItem(HeroAttrItem.Attrs.CHAR, 8);
        attrs = new ArrayList<>();
        attrs.add((str));
        attrs.add((agi));
        attrs.add((con));
        attrs.add((wis));
        attrs.add((intelligent));
        attrs.add((charm));
    }
    private HeroAttrs(ArrayList<HeroAttrItem> attrs) {
        this.attrs = attrs;
    }

    private HeroAttrs(int[] values) throws Exception {
        this();
        if(values == null)
            throw new Exception("请正确初始化英雄属性");
        for(int index = 0;index < values.length;index ++) {
            attrs.get(index).value = values[index];
        }
    }

    public static HeroAttrs getInitHeroAttrs() {
        return new HeroAttrs();
    }

    public static HeroAttrs getHeroAttrs(ArrayList<HeroAttrItem> arrayList) {
        return new HeroAttrs(arrayList);
    }

    public static HeroAttrs getHeroAttrs(int[] values) {
        try {
            return new HeroAttrs(values);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
