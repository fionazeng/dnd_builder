package android.powerword.siegfried.com.dnd_builder.annotation;

import android.support.annotation.Nullable;

import java.lang.reflect.Method;

public class Spell {
    private Object owner;
    private Method method;

    public Spell(Object owner, Method method) throws Exception {
        if(owner == null || method == null) {
            throw new IllegalArgumentException("Illegal Argument Exception!");
        }
        this.owner = owner;
        this.method = method;

    }
    public Object cast(Object ... args) {
        try {
          return method.invoke(owner, args);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
