package android.powerword.siegfried.com.dnd_builder.annotation;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class ComputedUtils {

    public static void injected(LifecycleOwner myOwner, ViewDataBinding binding){
        Field[] declaredFields = myOwner.getClass().getDeclaredFields();
        try {
            for ( Field field:  declaredFields) {
                if (field.isAnnotationPresent(DataStore.class)) {
                    DataStore annotation = field.getAnnotation(DataStore.class);
                    ViewModel viewmodel = injectDataStore(myOwner, binding, field, annotation);
                    Field[] vmFields = viewmodel.getClass().getDeclaredFields();

                    for (Field vmField: vmFields) {
                        if (vmField.isAnnotationPresent(Computed.class)) {
                            injectComputed(myOwner, binding, viewmodel, vmField);
                        } else if(vmField.isAnnotationPresent(Data.class)) {
                            injectData(binding, viewmodel, vmField);
                        }
                    }

                    injectMethod(viewmodel);

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
    private static ViewModel injectDataStore(LifecycleOwner myOwner, ViewDataBinding binding, Field field, DataStore annotation) throws ClassNotFoundException, IllegalAccessException {
        Type genericType = field.getGenericType();
        String s = genericType.toString().replace("class ", "");
        Class<? extends ViewModel> vmClass = (Class<? extends ViewModel>) Class.forName(s);
        Log.i(ComputedUtils.class.getSimpleName(), annotation.value() + "");
        int brValue = annotation.value();
        ViewModel viewmodel = (ViewModel) field.get(myOwner);
        if(viewmodel == null) {
            if(myOwner instanceof FragmentActivity) {
                FragmentActivity activity = (FragmentActivity) myOwner;
                viewmodel = ViewModelProviders.of(activity).get(vmClass);
            } else if(myOwner instanceof Fragment) {
                Fragment fragment = (Fragment) myOwner;
                viewmodel = ViewModelProviders.of(fragment).get(vmClass);
            }
        }
        field.set(myOwner, viewmodel);
        binding.setVariable(brValue, viewmodel);
        return viewmodel;
    }
}
