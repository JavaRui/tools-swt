package save;

import com.endless.tools.swt.base.YtComposite;
import com.endless.tools.swt.save.UiAndField;
import com.endless.tools.swt.save.UiCombo;
import com.endless.tools.swt.ui.text.YtText;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AccessPart extends YtComposite implements Runnable{
    @UiAndField(getMethodName = "getText",setMethodName = "setText")
    private YtText appIdText ;
    @UiAndField(getMethodName = "getText",setMethodName = "setText")
    private YtText appSecretText;
    @UiAndField(getMethodName = "getText",setMethodName = "setText")
    private YtText accessTokenText;

    @UiAndField(getMethodName = "getText",setMethodName = "setText" , key="appToken")
    private YtText appTokenText;

//    @UiAndField(getMethodName = "getText",setMethodName = "setText")
//    private YtText tableIdText;
    @UiAndField(getMethodName = "getText",setMethodName = "setText")
    private YtText wikiText;

    @UiAndField()
    UiCombo uiCombo ;

    public static final String APP_ID = "app_id";
    public static final String APP_SECRET = "app_secret";
    public static final String ACCESS_TOKEN = "access_token";
    public static final String WIKI = "wiki";
    public static final String APP_TOKEN = "app_token";

    public static final String TABLE_ID = "table_id";


    @Override
    public void run() {



    }



    public AccessPart(Composite parent) {
        super(parent);
        init();
    }


    private void init(){
        setGd(true,false);

        appIdText = createTextAndLabel(APP_ID,APP_ID);
        appIdText.setText("cli_a49b8e6ea9bed00b");
        appIdText.setGdFill(true,false);
        createLabel("").setData(APP_ID,APP_ID);
        setGridLayout(3,false);

        appSecretText = createTextAndLabel(APP_SECRET,APP_SECRET);
        appSecretText.setText("uo6hN6SvcB73CXbDi9nUBgyNZDX2LG48");
        appSecretText.setGdFill(true,false);
        createLabel("").setData(APP_SECRET,APP_SECRET);

        accessTokenText = createTextAndLabel(ACCESS_TOKEN,ACCESS_TOKEN);
        accessTokenText.setText("");
        accessTokenText.setGdFill(true,false);

        //save accessTokenText.getText() to desktop


        Button btn = new Button(this, SWT.PUSH);
        btn.setText("点击生成");
        btn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                run();
            }
        });
        btn.setData(ACCESS_TOKEN,ACCESS_TOKEN);


        wikiText = createTextAndLabel(WIKI,WIKI);
        wikiText.setGdFill(true, false);
        Button wikiBtn = new Button(this,SWT.PUSH);
        wikiBtn.setText("wiki转换");
        wikiBtn.setData(WIKI,WIKI);


        appTokenText = createTextAndLabel(APP_TOKEN,APP_TOKEN);
        appTokenText.setGdFill(true,false);



//        setChildVisibleFalse(TABLE_ID);
//        run();
        add2Scheduled(this);
    }



    public void setToConfig(){
        String text = accessTokenText.getText();
        if(text.startsWith("t-u-")){
            text = text.replace("t-u-","u-");
            accessTokenText.setText(text);
        }else if(text.startsWith("u-t-")){
            text = text.replace("u-t-","t-");
            accessTokenText.setText(text);
        }
        else if(text.startsWith("u-u-")){
            text = text.replace("u-u-","u-");
            accessTokenText.setText(text);
        }
        else if(text.startsWith("t-t-")){
            text = text.replace("t-t-","t-");
            accessTokenText.setText(text);
        }


    }

    public YtText createTextAndLabel(String labelText,String key) {
        Label label = this.createLabel(labelText);
        label.setData(key,key);
        YtText text = this.createText();
        text.setData(key,key);
        return text;
    }

    public static void add2Scheduled(Runnable runnable){
        service.scheduleAtFixedRate(runnable,0,10, TimeUnit.MINUTES);
    }
    static ScheduledExecutorService service = Executors.newScheduledThreadPool(10);

    public static void main(String[] args) {

        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程池的方式+123");
            }
        },5,2, TimeUnit.SECONDS);

        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程池的方式+33333333333");
            }
        },5,2, TimeUnit.SECONDS);


    }



}
