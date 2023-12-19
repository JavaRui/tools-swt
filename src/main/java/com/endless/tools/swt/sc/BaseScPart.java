package com.endless.tools.swt.sc;

import com.endless.tools.swt.base.YtComposite;
import com.endless.tools.swt.log.LogFace;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

import java.util.ArrayList;
import java.util.List;

public  abstract class BaseScPart<T, K> extends YtComposite implements PartFace<T, K> {
    private final ScrolledComposite sc;
    private  YtComposite content;

    private List itemList;
//    private  List<T> itemJsonList = new ArrayList<>();

    public BaseScPart(Composite parent, int border) {
        super(parent,border);
        setGd(true,true);

        this.setFillLayout();
        // 滑动面板
        sc  = new ScrolledComposite(this, SWT.H_SCROLL | SWT.V_SCROLL);
        sc.setLayout(new FillLayout());
        itemList = new ArrayList();
        initContent();
    }


    @Override
    public void input(List<T> input) {
        content.removeAllChild();
        content.dispose();
//        itemJsonList.clear();
        itemList.clear();
        initContent();

        createItems(input);
        layout();
    }


    protected void createItems(List<T> input){
        input.forEach(o->{
            createItem(content , o);
//            itemJsonList.add(o);
        });
    }

    protected abstract void createItem(YtComposite comp, T o);


    protected void initContent(){
        content = new YtComposite(sc, SWT.None);
        content.setGridLayout();
        sc.setContent(content);
    }

    protected YtComposite getContent(){
        return content;
    }


    @Override
    public void layout() {

        content.setSize(content.computeSize(SWT.DEFAULT, SWT.DEFAULT));
        content.layout();

        LogFace.log("我被刷新了-=================================");

        super.layout();
    }



}
