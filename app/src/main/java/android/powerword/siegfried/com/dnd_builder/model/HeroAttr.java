package android.powerword.siegfried.com.dnd_builder.model;

public class   HeroAttr {

    public Attrs attrs;
    public int value;

    public HeroAttr(Attrs attrs, int value) {
        this.attrs = attrs;
        this.value = value;
    }

    public int getBonus(){
        return (this.value - 10) /2;
    }

    public enum Attrs{
        STR("力量"),AGI("敏捷"),CON("耐力"), // 身体属性
        WIS("感知"),INT("智力"),CHAR("魅力"); // 心智属性



        private String value;

        Attrs(String value) {
            this.value = value;
        }

        public String getTitle() {
            return this.value;
        }
    }

}
