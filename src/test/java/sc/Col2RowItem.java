package sc;

import com.endless.tools.swt.base.YtComposite;
import com.endless.tools.swt.sc.PartFace;
import com.endless.tools.swt.ui.text.YtText;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import java.util.List;

public class Col2RowItem extends YtComposite implements PartFace<ColumnVo, ColumnVoUi> {
    /**
     * 转换列
     * */
    Button transferBtn;
    /**
     * 保留列
     * */
    Button reserveBtn;

    /**
     * 忽略
     * */
    Button ignoreBtn;
    /**
     * 列名
     * */
    YtText nameText;
    /**
     * 分组的key，主要是多个列归属于同一条数据
     * */
    YtText groupKeyText;

    ColumnVo columnVo ;
    ColumnVoUi columnVoUi = new ColumnVoUi();


    public Col2RowItem(Composite parent, ColumnVo columnVo) {
        super(parent);
        this.columnVo = columnVo;
        columnVoUi.setColumnVo(columnVo);
        init();

    }

    private void init(){
        nameText = new YtText(this,SWT.BORDER);
        nameText.setGdFill(true,true);

        reserveBtn = new Button(this, SWT.RADIO);
        reserveBtn.setText("保留");


        transferBtn= new Button(this, SWT.RADIO);
        transferBtn.setText("转换");
        transferBtn.setSelection(true);

        groupKeyText = new YtText(this,SWT.BORDER);
        groupKeyText.setText("    ");
        transferBtn.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                groupKeyText.setEditable(transferBtn.getSelection());
            }
        });

        ignoreBtn = new Button(this, SWT.RADIO);
        ignoreBtn.setText("忽略");


        setGridLayoutByChildren(false);
    }


    @Override
    public void input(List<ColumnVo> input) {

    }

    @Override
    public void input(ColumnVo jsonObject) {
        nameText.setText(jsonObject.getFieldName());
    }

    @Override
    public ColumnVoUi output() {
        columnVoUi.setReserve(reserveBtn.getSelection());
        columnVoUi.setIgnore(ignoreBtn.getSelection());
        columnVoUi.setTransfer(transferBtn.getSelection());
        columnVoUi.setGroupKey(groupKeyText.getText().trim());
        return columnVoUi;
    }
}
