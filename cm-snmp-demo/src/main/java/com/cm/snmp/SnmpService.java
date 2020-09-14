package com.cm.snmp;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;
import java.util.Vector;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.MessageProcessingModel;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.DefaultPDUFactory;
import org.snmp4j.util.PDUFactory;
import org.snmp4j.util.TableEvent;
import org.snmp4j.util.TableUtils;

/**
 * @Description
 * @Author cm
 * @Date 2020/9/11 10:00
 */
//@Slf4j
public class SnmpService {

    private Snmp snmp;
    private String hostComputer;
    private String port;
    private String community;
    private int version;
    private CommunityTarget communityTarget;
    private long timeout;
    private int retries;


    public SnmpService(String hostComputer, String port, String community, int version,long timeout, int retries) {
        this.hostComputer = hostComputer;
        this.community = community;
        this.port = port;
        this.timeout = timeout;
        this.retries = retries;
        if (version == SnmpConstants.version2c) {
            this.version = SnmpConstants.version2c;
        } else {
            //log.info("版本错误");
            return;
        }
        init();
    }

    // 初始化
    public void init(){
        try {
            TransportMapping transportMapping=new DefaultUdpTransportMapping();
            snmp=new Snmp(transportMapping);
            snmp.listen();
            // 设置权限
            communityTarget=new CommunityTarget();
            communityTarget.setCommunity(new OctetString(community));
            communityTarget.setAddress(new UdpAddress(hostComputer+"/"+port));
            // 通信不成功重复次数
            communityTarget.setRetries(retries);
            // 超时时间
            communityTarget.setTimeout(timeout);
            // 设置版本
            communityTarget.setVersion(version);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 单个获取方式
    public void getSnmpGet(String... oid) throws IOException {
        for (int i = 0; i < oid.length; i++) {
            PDU pdu = new PDU();
            pdu.add(new VariableBinding(new OID(oid[i])));
            // 设置请求方式
            pdu.setType(PDU.GET);
            ResponseEvent event = snmp.send(pdu, communityTarget);
            if (null != event) {
                readResponse(event);
            }
        }
    }

    // 对结果进行解析
    public void readResponse(ResponseEvent event) {
        if (null != event && event.getResponse() != null) {
            System.out.println("收到回复，正在解析");
            Vector<VariableBinding> vector = (Vector<VariableBinding>) event.getResponse().getVariableBindings();
            for (int i = 0; i < vector.size(); i++) {
                VariableBinding vec = vector.elementAt(i);
                System.out.println(vec);
            }
        } else {
            System.out.println("没有收到回复");
        }
    }

    /**
     * walk方式 PDU未提供walk，snmp4j提供TableUtils处理
     * @param oids
     */
    public void snmpWalk2(String oids[]) {
        //HashMap<String,String> mapList=new ArrayList<String,String>();
        // 设置TableUtil的工具
        TableUtils utils = new TableUtils(snmp, new DefaultPDUFactory(PDU.GETBULK));
        utils.setMaxNumRowsPerPDU(2);
        OID[] clounmOid = new OID[oids.length];
        for (int i = 0; i < oids.length; i++) {
            clounmOid[i] = new OID(oids[i]);
        }
        // 获取查询结果list,new OID("0"),new OID("40")设置输出的端口数量
        List<TableEvent> list = utils.getTable(communityTarget, clounmOid, new OID("0"), new OID("40"));
        for (int i = 0; i < list.size(); i++) {
            // 取list中的一行
            TableEvent te = (TableEvent) list.get(i);
            // 对每一行结果进行再次拆分
            VariableBinding[] vb = te.getColumns();
            //HashMap<String,String> map  = new HashMap<String,String>();
            if (vb != null) {
                for (int j = 0; j < vb.length; j++) {
                    System.out.println(vb[j].toString());
                }
            } else {
                throw new NullPointerException("被监控系统的网络不通或IP或其它相关配置错识！");
            }
        }
    }

    /**
     * 测网络通不通 类似 ping ip
     *
     * @return
     * @throws IOException
     */
    public boolean isEthernetConnection(String hostIp) throws IOException {
        InetAddress ad = InetAddress.getByName(hostIp);
        boolean state = ad.isReachable(2000);// 测试是否可以达到该地址 2秒超时
        return state;
    }


    //获取内存相关信息
    public static void collectMemory() {
        TransportMapping transport = null ;
        Snmp snmp = null ;
        CommunityTarget target;
        String[] oids = {"1.3.6.1.2.1.25.2.3.1.2",  //type 存储单元类型
            "1.3.6.1.2.1.25.2.3.1.3",  //descr
            "1.3.6.1.2.1.25.2.3.1.4",  //unit 存储单元大小
            "1.3.6.1.2.1.25.2.3.1.5",  //size 总存储单元数
            "1.3.6.1.2.1.25.2.3.1.6"}; //used 使用存储单元数;
        String PHYSICAL_MEMORY_OID = "1.3.6.1.2.1.25.2.1.2";//物理存储
        String VIRTUAL_MEMORY_OID = "1.3.6.1.2.1.25.2.1.3"; //虚拟存储
        try {
            transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);//创建snmp
            snmp.listen();//监听消息
            target = new CommunityTarget();
            target.setCommunity(new OctetString("public"));
            target.setRetries(2);
            target.setAddress(GenericAddress.parse("udp:39.105.178.126/161"));
            target.setTimeout(8000);
            target.setVersion(SnmpConstants.version2c);
            TableUtils tableUtils = new TableUtils(snmp, new PDUFactory() {
                @Override
                public PDU createPDU(Target arg0) {
                    PDU request = new PDU();
                    request.setType(PDU.GET);
                    return request;
                }

                public PDU createPDU(MessageProcessingModel messageProcessingModel) {
                    return null;
                }
            });
            OID[] columns = new OID[oids.length];
            for (int i = 0; i < oids.length; i++)
                columns[i] = new OID(oids[i]);
            @SuppressWarnings("unchecked")
            List<TableEvent> list = tableUtils.getTable(target, columns, null, null);
            if(list.size()==1 && list.get(0).getColumns()==null){
                System.out.println(" null");
            }else{
                for(TableEvent event : list){
                    VariableBinding[] values = event.getColumns();
                    if(values == null) continue;
                    int unit = Integer.parseInt(values[2].getVariable().toString());//unit 存储单元大小
                    int totalSize = Integer.parseInt(values[3].getVariable().toString());//size 总存储单元数
                    int usedSize = Integer.parseInt(values[4].getVariable().toString());//used  使用存储单元数
                    String oid = values[0].getVariable().toString();
                    if (PHYSICAL_MEMORY_OID.equals(oid)){
                        System.out.println("PHYSICAL_MEMORY----->物理内存大小："+(long)totalSize * unit/(1024*1024*1024)+"G   内存使用率为："+(long)usedSize*100/totalSize+"%");
                    }else if (VIRTUAL_MEMORY_OID.equals(oid)) {
                        System.out.println("VIRTUAL_MEMORY----->虚拟内存大小："+(long)totalSize * unit/(1024*1024*1024)+"G   内存使用率为："+(long)usedSize*100/totalSize+"%");
                    }
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                if(transport!=null)
                    transport.close();
                if(snmp!=null)
                    snmp.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    //获取磁盘相关信息
    public static void collectDisk() {
        TransportMapping transport = null ;
        Snmp snmp = null ;
        CommunityTarget target;
        String DISK_OID = "1.3.6.1.2.1.25.2.1.4";
        String[] oids = {"1.3.6.1.2.1.25.2.3.1.2",  //type 存储单元类型
            "1.3.6.1.2.1.25.2.3.1.3",  //descr
            "1.3.6.1.2.1.25.2.3.1.4",  //unit 存储单元大小
            "1.3.6.1.2.1.25.2.3.1.5",  //size 总存储单元数
            "1.3.6.1.2.1.25.2.3.1.6"}; //used 使用存储单元数;
        try {
            transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);//创建snmp
            snmp.listen();//监听消息
            target = new CommunityTarget();
            target.setCommunity(new OctetString("public"));
            target.setRetries(2);
            target.setAddress(GenericAddress.parse("udp:39.105.178.126/161"));
            target.setTimeout(8000);
            target.setVersion(SnmpConstants.version2c);
            TableUtils tableUtils = new TableUtils(snmp, new PDUFactory() {
                @Override
                public PDU createPDU(Target arg0) {
                    PDU request = new PDU();
                    request.setType(PDU.GET);
                    return request;
                }

                @Override
                public PDU createPDU(MessageProcessingModel messageProcessingModel) {
                    return null;
                }
            });
            OID[] columns = new OID[oids.length];
            for (int i = 0; i < oids.length; i++)
                columns[i] = new OID(oids[i]);
            @SuppressWarnings("unchecked")
            List<TableEvent> list = tableUtils.getTable(target, columns, null, null);
            if(list.size()==1 && list.get(0).getColumns()==null){
                System.out.println(" null");
            }else{
                for(TableEvent event : list){
                    VariableBinding[] values = event.getColumns();
                    if(values == null ||!DISK_OID.equals(values[0].getVariable().toString()))
                        continue;
                    int unit = Integer.parseInt(values[2].getVariable().toString());//unit 存储单元大小
                    int totalSize = Integer.parseInt(values[3].getVariable().toString());//size 总存储单元数
                    int usedSize = Integer.parseInt(values[4].getVariable().toString());//used  使用存储单元数
                    System.out.println("   磁盘大小："+(long)totalSize*unit/(1024*1024*1024)+"G   磁盘使用率为："+(long)usedSize*100/totalSize+"%");
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                if(transport!=null)
                    transport.close();
                if(snmp!=null)
                    snmp.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * walk方式 PDU未提供walk，snmp4j提供TableUtils处理
     * @param oids
     */
    public void snmpWalkDisk(String oids[]) {
        String DISK_OID = "1.3.6.1.2.1.25.2.1.4";
        TableUtils utils = new TableUtils(snmp, new DefaultPDUFactory(PDU.GETBULK));
        utils.setMaxNumRowsPerPDU(2);
        OID[] clounmOid = new OID[oids.length];
        for (int i = 0; i < oids.length; i++) {
            clounmOid[i] = new OID(oids[i]);
        }
        // 获取查询结果list,new OID("0"),new OID("40")设置输出的端口数量
        List<TableEvent> list = utils.getTable(communityTarget, clounmOid, null, null);

        if (list.size() == 1 && list.get(0).getColumns() == null) {
            System.out.println(" null");
        } else {
            for(TableEvent event : list){
                VariableBinding[] values = event.getColumns();
                if(values == null ||!DISK_OID.equals(values[0].getVariable().toString()))
                    continue;
                int unit = Integer.parseInt(values[2].getVariable().toString());//unit 存储单元大小
                int totalSize = Integer.parseInt(values[3].getVariable().toString());//size 总存储单元数
                int usedSize = Integer.parseInt(values[4].getVariable().toString());//used  使用存储单元数
                System.out.println("磁盘大小："+(long)totalSize*unit/(1024*1024*1024)+"G   磁盘使用率为："+(long)usedSize*100/totalSize+"%");
            }
        }
    }


}
