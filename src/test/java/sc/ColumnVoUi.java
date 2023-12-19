package sc;

import lombok.Data;

@Data
public class ColumnVoUi {
    private ColumnVo columnVo;
    /**
     * 转换列
     * */
    boolean transfer;
    /**
     * 保留列
     * */
    boolean reserve;

    /**
     * 不处理
     * */
    boolean ignore;

    String groupKey;


}
