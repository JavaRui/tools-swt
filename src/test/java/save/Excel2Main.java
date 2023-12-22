package save;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.date.Week;
import com.endless.tools.swt.log.LogFace;
import com.endless.tools.swt.base.ShellBack;
import com.endless.tools.swt.base.SwtVoid;
import com.endless.tools.swt.base.YtComposite;
import com.endless.tools.swt.mgr.ChooseDlgMgr;
import com.endless.tools.swt.save.UiAndClass;
import com.endless.tools.swt.save.UiAndField;
import com.endless.tools.swt.save.UiAndFieldUtils;
import com.endless.tools.swt.ui.text.YtText;
import com.endless.tools.swt.util.LayoutUtil;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import java.io.File;
import java.time.LocalDate;
import java.util.Date;

public class Excel2Main extends YtComposite {
    @UiAndField(getMethodName = "getText",setMethodName = "setText" ,key = "out")
    YtText outText;
    @UiAndField(getMethodName = "getText",setMethodName = "setText")
    YtText inputText;

    @UiAndField(getMethodName = "getText",setMethodName = "setText")
    YtText qqqText;


    @UiAndClass
    AccessPart accessPart;

    public Excel2Main(Composite parent) {
        super(parent);
        init();
    }


    private void init(){


        inputText = createTextAndLabel("输入处理的excel地址");

        Button tempBtn = new Button(this, 8);
        tempBtn.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                super.widgetSelected(e);
                String[] filter = new String[]{"*.xlsx", "*.*"};
                String path = ChooseDlgMgr.openFile(filter);
                if (path != null) {
                    inputText.setText(path);
                }
            }
        });
        tempBtn.setText("选择路径");



        inputText.addModifyListener(new ModifyListener() {
            @Override
            public void modifyText(ModifyEvent e) {

                String replace = inputText.getText().replace(".xlsx", DateUtil.format(new Date(), "-mmss") + ".xlsx");
                if(outText.getText().length() == 0){
                    outText.setText(replace);
                }
            }
        });

        inputText.setGdFill(true,false);

        setGridLayoutByChildren(false);
        GridData fillGridNoVer = LayoutUtil.createFillGridNoVer(3);

        outText = createTextAndLabel("输出处理的excel地址");
//        ButtonUtil.createDirBtn(this,outText);
        outText.setGdFill(true,false);

        Button tempBtn2 = new Button(this, 8);
        tempBtn2.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {

                String path = ChooseDlgMgr.openDir();
                String filePath = inputText.getText();
                try{
                    if (path != null) {
                        File file = new File(filePath);
                        outText.setText(path+"/"+file.getName());
                    }
                }catch (Exception e1){
                    LogFace.err(e1);
                }
            }
        });
        tempBtn2.setText("选择路径");

        YtComposite settingComp = new YtComposite(this);
        YtText sheetName = settingComp.createTextAndLabel("sheetName");
        LocalDate today = LocalDate.now();
        sheetName.setText(DateUtil.format(new Date(),"MMdd")+Week.of(  (today.getDayOfWeek().getValue()+1)%7).toChinese("周"));
        sheetName.setGdFill(true,false);
        YtText roomRow = settingComp.createTextAndLabel("roomRow");
        roomRow.setText("3");
        roomRow.setGdFill(true,false);
        YtText rowStart = settingComp.createTextAndLabel("rowStart");
        rowStart.setText("7");
        rowStart.setGdFill(true,false);

        settingComp.setGridLayoutByChildren(true);

        YtText colStart = settingComp.createTextAndLabel("colStart");
        colStart.setText("3");
        colStart.setGdFill(true,false);

        //当前日期，用来判断day1还是day2
        YtText todayText = settingComp.createTextAndLabel("今天日期");
        todayText.setText(DateUtil.format(new Date(),"yyyy/MM/dd"));
        todayText.setGdFill(true,false);
        todayText.addModifyListener(new ModifyListener() {
            @Override
            public void modifyText(ModifyEvent e) {
                try{

                    DateTime parse = DateUtil.parse(todayText.getText(), "yyyy/MM/dd");
                    Date date = new Date(parse.getTime());
                    LocalDate ld = LocalDateTimeUtil.parseDate(todayText.getText(),"yyyy/MM/dd");
                    String s = DateUtil.format(date, "MMdd") +
                            Week.of((ld.getDayOfWeek().getValue()) % 7 + 1).toChinese("周");
                    sheetName.setText(s);
                }catch (Exception e1){
                    e1.printStackTrace();
                }
            }
        });


        settingComp.setLayoutData(fillGridNoVer);


        YtComposite btnComp = new YtComposite(this);
        btnComp.setFillLayout();

        btnComp.setLayoutData(fillGridNoVer);


        Button splitCellBtn = new Button(btnComp, SWT.PUSH);
        splitCellBtn.setText("单元格拆分");
        splitCellBtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                UiAndFieldUtils.save(Excel2Main.this);

            }
        });

        Button changeBtn = new Button(btnComp, SWT.PUSH);
        changeBtn.setText("excel解析");
        changeBtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                try {
                    UiAndFieldUtils.save(Excel2Main.this);
                }catch (Exception e1){
                    e1.printStackTrace();
                }
            }
        });
        //[人员出勤1109].FILTER(CurrentValue.[人员]=[人员]).[11/09直播间（白）].LISTCOMBINE()



        Button openDir = new Button(btnComp,SWT.PUSH);
        openDir.setText("打开文件夹");
        openDir.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {

                String filePath = outText.getText();
                LogFace.err("打开--->   "+filePath);
            }
        });


        UiAndFieldUtils.load(Excel2Main.this);
    }




    public static void main(String[] args) {

        SwtVoid.createSwt(new ShellBack() {
            @Override
            public void callBack(Shell shell) {
                shell.setText("飞书研究院-excel转线上多维表格");
                shell.setSize(700,800);
                new Excel2Main(shell);
            }
        });


    }



}
