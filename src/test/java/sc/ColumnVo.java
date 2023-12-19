package sc;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ColumnVo {



    private String fieldId ;
    private String uiType ;
    private String isPrimary ;
    private String type ;
    private String fieldName ;
    //表示是否有被选择
    private boolean selection;
    private JSONObject property;



}
