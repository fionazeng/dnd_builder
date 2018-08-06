package android.powerword.siegfried.com.dnd_builder.annotation;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ComputedUtils {

    public static void injected(LifecycleOwner owner,Object container, ViewDataBinding binding) {
//        Field[] declaredFields = container.getClass().getDeclaredFields();
        castSpell(owner, container, binding);
    }
    public static void injected(LifecycleOwner myOwner, ViewDataBinding binding){
//        Field[] declaredFields = myOwner.getClass().getDeclaredFields();
        castSpell(myOwner, null, binding);
    }

    private static void castSpell(LifecycleOwner myOwner, Object container, ViewDataBinding binding) {

        try {
            if(container == null) {
                container = myOwner;
            }
            Field[] declaredFields = container.getClass().getDeclaredFields();
            for ( Field field:  declaredFields) {
                if (field.isAnnotationPresent(DataStore.class)) {
                    ViewModel viewmodel = injectDataStore(container, binding, field);
                    Field[] vmFields = viewmodel.getClass().getDeclaredFields();
                    for (Field vmField: vmFields) {
                        if (vmField.isAnnotationPresent(Computed.class)) {
                            vmField.setAccessible(true);
                            injectComputed(myOwner, binding, viewmodel, vmField);
                        } else if(vmField.isAnnotationPresent(Data.class)) {
                            vmField.setAccessible(true);
                            injectData(binding, viewmodel, vmField);
                        }
                    }
                }
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void injectMethod(ViewModel viewmodel) throws IllegalAccessException, InstantiationException {
        Method[] declaredMethods = viewmodel.getClass().getDeclaredMethods();
        for(Method method : declaredMethods) {
            int value = method.getAnnotation(android.powerword.siegfried.com.dnd_builder.annotation.Method.class).value();
            Class proxy = method.getAnnotation(android.powerword.siegfried.com.dnd_builder.annotation.Method.class).proxy();
            Object o = proxy.newInstance();

        }
    }

    private static void injectData(ViewDataBinding binding, ViewModel viewmodel, Field vmField) throws IllegalAccessException {
        int brData = vmField.getAnnotation(Data.class).value();
        binding.setVariable(brData, vmField.get(viewmodel));
    }

    private static void injectComputed(LifecycleOwner myOwner, ViewDataBinding binding, ViewModel viewmodel, Field vmField) throws IllegalAccessException {
        int brComputed = vmField.getAnnotation(Computed.class).value();
        MutableLiveData mutableLiveData = (MutableLiveData) vmField.get(viewmodel);
        mutableLiveData.observe(myOwner, obj -> binding.setVariable(brComputed, obj));
    }

    @Nullable
    private static ViewModel injectDataStore(Object container, ViewDataBinding binding, Field field) throws IllegalAccessException {
        DataStore annotation = field.getAnnotation(DataStore.class);
        field.setAccessible(true);
        int brValue = annotation.value();
        ViewModel viewmodel = (ViewModel) field.get(container);
        binding.setVariable(brValue, viewmodel);
        return viewmodel;
    }
}
