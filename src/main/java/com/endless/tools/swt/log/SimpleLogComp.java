package com.endless.tools.swt.log;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import com.endless.tools.swt.base.SwtVoid;
import com.endless.tools.swt.base.YtComposite;
import com.endless.tools.swt.ui.text.YtText;
import com.endless.tools.swt.util.ButtonUtil;
import com.endless.tools.swt.util.LayoutUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import java.util.Date;

/**
 * 优先使用logFace的类，做输出，LogFace.log(xxxx)这样，这样可以兼容没有ui的情况
 * */
public class SimpleLogComp extends YtComposite {

    private Text logText ;

    public SimpleLogComp(Composite parent, int style) {
        super(parent, style);
        init();
    }

    private void init() {

        logText = new Text(this, SWT.BORDER|SWT.WRAP|SWT.WRAP|SWT.V_SCROLL);
        logText.setLayoutData(LayoutUtil.createFillGrid(1));
        LogFace.setLogText(logText);


        setGridLayoutByChildren(false);
        createBtnComp();

    }

    public Text getLogText(){
        return logText;
    }

    public void setTextTime(String text){
        String format = DateUtil.format(new Date(), "hh:mm:ss   ");
        setText(format+text);
    }
    public void setText(String text){
        logText.setText(text);
    }

    public void append(String text){
//        String format = DateUtil.format(new Date(), "hh:mm:ss   ");
        logText.append(text+"\n");
    }
    public void appendTime(String text){
        String format = DateUtil.format(new Date(), "hh:mm:ss   ");
        logText.append(format+text+"\n");
    }

    /**
     * 创建按钮
     * */
    public YtComposite createBtnComp(){
        YtComposite btnComp = new YtComposite(this);
        YtText pathText = btnComp.createText();
        pathText.setText(SwtVoid.getUserDir()+"temp.log");

        Button saveBtn = new Button(btnComp,SWT.PUSH);
        saveBtn.setText("保存日志");
        saveBtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                FileUtil.writeString(logText.getText(),pathText.getText(),"UTF-8");
                logText.setText("-----------   保存完成   -----------");
            }
        });

        Button copyBtn = new Button(btnComp,SWT.PUSH);
        copyBtn.setText("复制日志");
        copyBtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                SwtVoid.addCopy(logText.getText());
                logText.setText("-----------   复制完成   -----------");
            }
        });


        Button clearBtn = new Button(btnComp,SWT.PUSH);
        clearBtn.setText("清除日志");
        clearBtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                logText.setText("");
            }
        });
        btnComp.setGridLayoutByChildren(false);
        return btnComp;
    }




}
