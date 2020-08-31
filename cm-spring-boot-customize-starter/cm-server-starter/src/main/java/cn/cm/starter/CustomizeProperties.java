package cn.cm.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description todo
 * @Author cm
 * @Date 2020/8/26 15:41
 */
@ConfigurationProperties(prefix = "cm.server")
public class CustomizeProperties {

    /**
     * 默认端口
     */
    private static final Integer DEFAULT_PORT = 8100;

    /**
     * 端口
     */
    private Integer port = DEFAULT_PORT;

    public static Integer getDefaultPort() {
        return DEFAULT_PORT;
    }

    public Integer getPort() {
        return port;
    }

    public CustomizeProperties setPort(Integer port) {
        this.port = port;
        return this;
    }

}
