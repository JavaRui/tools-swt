package com.endless.tool.swt.save;

import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;

/**
 * 用以UiAndFieldUtils的映射功能
 * */
public interface UiAndFieldFace<T> {

    void input(T in);
    T output();

    static void addDispose(final Composite composite){
        composite.addDisposeListener(new DisposeListener() {
            @Override
            public void widgetDisposed(DisposeEvent e) {
                UiAndFieldUtils.save(composite);
            }
        });
    }



}
