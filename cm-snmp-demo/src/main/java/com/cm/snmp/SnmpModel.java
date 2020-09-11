package com.cm.snmp;

import lombok.Data;
import org.snmp4j.CommunityTarget;
import org.snmp4j.Snmp;
import org.snmp4j.smi.Address;

/**
 * @Description todo
 * @Author cm
 * @Date 2020/9/11 9:27
 */
@Data
public class SnmpModel {

    private Snmp snmp;
    private Address targetAddress;
    private String hostComputer;
    private String port;
    private String community;
    private int version;
    private CommunityTarget communityTarget;

}
