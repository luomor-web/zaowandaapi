package com.siival.bot.modules.bsc.service.lillia;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class LilliaBaseService {
    // status
    public final static Integer STATUS_DEL = -1;               //删除
    public final static Integer STATUS_INIT = 0;               //INIT
    public final static Integer STATUS_OK = 1;                 //正常

    public static final Integer STATUS_READ = 2; // 读取中
    public static final Integer STATUS_FIELD = 2; // 场地费计算结果信息
    public static final Integer STATUS_POWER = 3; // 电力引入费计算结果信息
    public static final Integer STATUS_EXIF = 3; // 已读取exif信息
    public static final Integer STATUS_EXCEL = 3; // 已读取excel信息
    public static final Integer STATUS_CALCULATION = 4; // 计算从表中读取到的数据
    public static final Integer STATUS_AUDITING = 4; // 审计中
    public static final Integer STATUS_SUCCESSFUL = 5; // 计算成功
    public static final Integer STATUS_AUDIT = 5; // 已审计
    public static final Integer STATUS_READ_ERR = 10; // 读取信息错误
    public static final Integer STATUS_AUDIT_ERR = 11; // 已审计

    public final static int SOURCE_MINI_PROGRAM = 1;         //小程序
    public final static int SOURCE_IMPORT = 2;               //导入

    public final static int TYPE_SCENERY = 1;         //实景
    public final static int TYPE_SCENELESS = 2;       //非现场
    public final static int TYPE_QUESTIONNAIRE_SCENERY = 3;       //清查-实景
    public final static int TYPE_QUESTIONNAIRE_SCENELESS = 4;       //清查-非现场
    public final static int TYPE_SCENESNAP = 5;         //随手拍

    public final static Integer YES = 1;              //yes
    public final static Integer NO = 0;               //no

    //读取状态
    //审计状态

    public final static int AUDIT_CODE_OK = 100; //ok
    public final static int AUDIT_CODE_TIME_POS_ERROR = 101; //时间地点异常

    private final Log logger = LogFactory.getLog(LilliaBaseService.class);

    /**
     * 获取精确到秒的时间戳
     *
     * @return int
     */
    public static int getSecondTimestamp(Date date) {
        if (null == date) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime());
        int length = timestamp.length();
        if (length > 3) {
            return Integer.valueOf(timestamp.substring(0, length - 3));
        } else {
            return 0;
        }
    }

    public static String getLocalDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());

        return formatter.format(date);
    }

    public static String getLocalDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return formatter.format(date);
    }

    public static String getRandom(int num) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < num; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static String toJson(Object data) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
