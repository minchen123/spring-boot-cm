package com.cm.snmp;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;
import java.util.Vector;
import lombok.extern.slf4j.Slf4j;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.DefaultPDUFactory;
import org.snmp4j.util.TableEvent;
import org.snmp4j.util.TableUtils;

/**
 * @Description todo
 * @Author cm
 * @Date 2020/9/11 10:00
 */
//@Slf4j
public class SnmpService {

    private Snmp snmp;
    private Address targetAddress;
    private String hostComputer;
    private String port;
    private String community;
    private int version;
    private CommunityTarget communityTarget;

    public SnmpService(String hostComputer,String port ,String community, int version){
        this.hostComputer = hostComputer;
        this.community = community;
        this.port=port;
        if (version == SnmpConstants.version2c){
            this.version= SnmpConstants.version2c;
        }else {
            //log.info("版本错误");
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
            communityTarget.setRetries(2);
            // 超时时间
            communityTarget.setTimeout(2*1000);
            // 设置版本
            communityTarget.setVersion(version);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // 单个获取方式
    public void getSnmpGet(String... oid) throws IOException {
        for (int i=0;i<oid.length;i++){
            PDU pdu=new PDU();
            pdu.add(new VariableBinding(new OID(oid[i])));
            // 设置请求方式
            pdu.setType(PDU.GET);
            ResponseEvent event=snmp.send(pdu,communityTarget);
            if (null!=event){
                readResponse(event);
            }
        }
    }

    // 对结果进行解析
    public void readResponse(ResponseEvent event){
        if (null!=event&&event.getResponse()!=null){
            System.out.println("收到回复，正在解析");
            Vector<VariableBinding> vector= (Vector<VariableBinding>) event.getResponse().getVariableBindings();
            for (int i=0;i<vector.size();i++){
                VariableBinding vec= vector.elementAt(i);
                System.out.println(vec);
            }
        }else
            System.out.println("没有收到回复");
    }
    // 遍历请求
    public void snmpWalk2(String oids[]){
        //HashMap<String,String> mapList=new ArrayList<String,String>();
        // 设置TableUtil的工具
        TableUtils utils=new TableUtils(snmp,new DefaultPDUFactory(PDU.GETBULK));
        utils.setMaxNumRowsPerPDU(2);
        OID[] clounmOid =new OID[oids.length];
        for (int i=0;i<oids.length;i++){
            clounmOid[i]=new OID(oids[i]);
        }
        // 获取查询结果list,new OID("0"),new OID("40")设置输出的端口数量
        List<TableEvent> list=utils.getTable(communityTarget,clounmOid,new OID("0"),new OID("40"));
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

}
