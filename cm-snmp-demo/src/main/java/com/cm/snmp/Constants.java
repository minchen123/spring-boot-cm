package com.cm.snmp;

/**
 * @Description snmp指令
 * @Author cm
 * @Date 2020/9/11 11:16
 */
public class Constants {

    /**
     * Get请求方式
     */
    //系统基本信息
    public static final String sysDescr = ".1.3.6.1.2.1.1.1.0";
    //机器名
    public static final String sysName = ".1.3.6.1.2.1.1.5.0";
    //空闲CPU百分比
    public static final String ssCpuIdle = ".1.3.6.1.4.1.2021.11.11.0";


    public static final String memory = ".1.3.6.1.2.1.25.2.2.0";




    /**
     * Wakl请求方式
     */
//    public static final String[] ifOids = new String[]{
//        ".1.3.6.1.2.1.2.2.1.1",
//        ".1.3.6.1.2.1.2.2.1.2",
//        ".1.3.6.1.2.1.2.2.1.3",
//        ".1.3.6.1.2.1.2.2.1.4",
//        ".1.3.6.1.2.1.2.2.1.5",
//        ".1.3.6.1.2.1.2.2.1.6",
//        ".1.3.6.1.2.1.2.2.1.7",
//        ".1.3.6.1.2.1.2.2.1.8",
//        ".1.3.6.1.2.1.2.2.1.9",
//        ".1.3.6.1.2.1.2.2.1.10",
//        ".1.3.6.1.2.1.2.2.1.11",
//        ".1.3.6.1.2.1.2.2.1.12",
//        ".1.3.6.1.2.1.2.2.1.13",
//        ".1.3.6.1.2.1.2.2.1.14",
//        ".1.3.6.1.2.1.2.2.1.15",
//        ".1.3.6.1.2.1.2.2.1.16",
//        ".1.3.6.1.2.1.2.2.1.17",
//        ".1.3.6.1.2.1.2.2.1.18",
//        ".1.3.6.1.2.1.2.2.1.19",
//        ".1.3.6.1.2.1.2.2.1.20",
//        ".1.3.6.1.2.1.2.2.1.21",
//        ".1.3.6.1.2.1.2.2.1.22"
//    };

    //网络接口
    public static final String[] ifOids = new String[]{
        ".1.3.6.1.2.1.2.2.1.1",
        ".1.3.6.1.2.1.2.2.1.2",
        ".1.3.6.1.2.1.2.2.1.3",
        ".1.3.6.1.2.1.2.2.1.4",
        ".1.3.6.1.2.1.2.2.1.5",
        ".1.3.6.1.2.1.2.2.1.6",
        ".1.3.6.1.2.1.2.2.1.7",
        ".1.3.6.1.2.1.2.2.1.8",
        ".1.3.6.1.2.1.2.2.1.9",
        ".1.3.6.1.2.1.2.2.1.10",
        ".1.3.6.1.2.1.2.2.1.11",
        ".1.3.6.1.2.1.2.2.1.12",
        ".1.3.6.1.2.1.2.2.1.13",
        ".1.3.6.1.2.1.2.2.1.14",
        ".1.3.6.1.2.1.2.2.1.15",
        ".1.3.6.1.2.1.2.2.1.16",
        ".1.3.6.1.2.1.2.2.1.17",
        ".1.3.6.1.2.1.2.2.1.18",
        ".1.3.6.1.2.1.2.2.1.19",
        ".1.3.6.1.2.1.2.2.1.20",
        ".1.3.6.1.2.1.2.2.1.21",
        ".1.3.6.1.2.1.2.2.1.22"
    };

    //获取磁盘命令指令
    public static String[] oidsDisk = {"1.3.6.1.2.1.25.2.3.1.2",  //type 存储单元类型
        "1.3.6.1.2.1.25.2.3.1.3",  //descr
        "1.3.6.1.2.1.25.2.3.1.4",  //unit 存储单元大小
        "1.3.6.1.2.1.25.2.3.1.5",  //size 总存储单元数
        "1.3.6.1.2.1.25.2.3.1.6"   //used 使用存储单元数
    };



}
