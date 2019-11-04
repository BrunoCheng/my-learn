package com.suixin.mylearn.L4面向对象.L18interface接口;

/**
 * @Description 接口的应用：代理模式
 * @Date 2016/10/31
 * @Created by acheng
 */
public class NetWorkTest {
    public static void main(String[] args) {
        RealServer realServer = new RealServer();
        ProxyServer proxyServer = new ProxyServer(realServer);
        proxyServer.browse();
    }

}

interface Network {
    public void browse();
}

// 被代理类
class RealServer implements Network {
    @Override
    public void browse() {
        System.out.println("真实服务器上 网浏览信息");
    }
}

// 代理类
class ProxyServer implements Network {
    private Network network;

    public ProxyServer(Network network) {
        this.network = network;
    }

    public void check() {
        System.out.println("检查网络连接等操作");
    }

    public void browse() {
        check();
        network.browse();
    }
}


