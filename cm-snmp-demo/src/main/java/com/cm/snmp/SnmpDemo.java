package com.cm.snmp;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Description Snmp
 * @Author cm
 * @Date 2020/9/11 14:01
 */
@Component
@EnableScheduling
@PropertySource(name = "application.yml", value = {
    "classpath:application.yml" }, ignoreResourceNotFound = false, encoding = "UTF-8")
public class SnmpDemo {

    @Value("${server.ip}")
    private String ip;
    @Value("${server.port}")
    private String port;
    @Value("${snmp.setting.community}")
    private String community;
    @Value("${snmp.setting.version}")
    private int version;

    @Scheduled(cron = "${job.cron}")
    private void getServerInfo() {
        SnmpService snmpService=new SnmpService(ip,port,community, version);
        try {
            snmpService.getSnmpGet(Constants.sysDescr,Constants.sysName,Constants.sysObjectID,Constants.ifNumber);
            snmpService.snmpWalk2(Constants.ifOids);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
