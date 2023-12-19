package sc;

import cn.hutool.core.util.RandomUtil;
import com.endless.tools.swt.base.ShellBack;
import com.endless.tools.swt.base.SwtVoid;
import com.endless.tools.swt.log.SimpleLogComp;
import org.eclipse.swt.widgets.Shell;

import java.util.ArrayList;
import java.util.List;

public class ScMain {

    public static void main(String[] args) {
        SwtVoid.createSwt(new ShellBack() {
            @Override
            public void callBack(Shell shell) {
                List list = new ArrayList();
                for (int i = 0; i < 30; i++) {
                    ColumnVo columnVo = new ColumnVo();
                    columnVo.setFieldName(RandomUtil.randomString(5));
                    list.add(columnVo);
                }

                ColumnListPart columnListPart = new ColumnListPart(shell);
                columnListPart.input(list);

                SimpleLogComp simpleLogComp = new SimpleLogComp(shell,0);


            }
        });
    }

}
