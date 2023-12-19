package sc;

import com.endless.tools.swt.base.YtComposite;
import com.endless.tools.swt.sc.BaseScPart;
import com.endless.tools.swt.sc.PartFace;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ColumnListPart extends BaseScPart<ColumnVo, List<ColumnVoUi>> implements PartFace<ColumnVo, List<ColumnVoUi>> {


    private final  List<Col2RowItem> columnItemList = new ArrayList<>();

    public ColumnListPart(Composite ytComposite){
        super(ytComposite,SWT.BORDER);

    }

    @Override
    protected void initContent() {
        super.initContent();
        getContent().setGridLayout(2,true);
    }

    @Override
    public List<ColumnVoUi> output() {
        List<ColumnVoUi> columnVoUiList = new ArrayList<>();

        columnItemList.forEach(col2RowItem -> {
            ColumnVoUi output = col2RowItem.output();
            columnVoUiList.add(output);
        });
        return columnVoUiList;
    }

    @Override
    protected void createItem(YtComposite contentParent , ColumnVo vo) {
        Col2RowItem col2RowItem = new Col2RowItem(contentParent,vo);
        col2RowItem.setGd(true,false);
        col2RowItem.input(vo);

        GridData gd = new GridData(400,42);
        col2RowItem.setLayoutData(gd);

        columnItemList.add(col2RowItem);
    }


}
