import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by daile on 2017/6/7.
 */
public class NettyServer {

    private String inetHost = "localhost";

    private int inetPort = 8080;

    public NettyServer(String inetHost, int inetPort) {
        this.inetHost = inetHost;
        this.inetPort = inetPort;
    }

    private void start() {
        EventLoopGroup bossEventGroup = new NioEventLoopGroup();
        EventLoopGroup workerEventGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossEventGroup, workerEventGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 2000)
                .option(ChannelOption.SO_KEEPALIVE, false)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_RCVBUF, 1024)
                .option(ChannelOption.AUTO_READ, true)
                .option(ChannelOption.RCVBUF_ALLOCATOR, AdaptiveRecvByteBufAllocator.DEFAULT)
                .option(ChannelOption.SO_RCVBUF, 1024)
                .childHandler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ch.pipeline().addLast(new PortalHandler());
                    }
                });
        try{
            ChannelFuture bindFuture = serverBootstrap.bind(inetHost, inetPort).sync();
            bindFuture.channel().close().sync();

        }catch (Exception e) {

        }finally {
            bossEventGroup.shutdownGracefully();
            workerEventGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new NettyServer("localhost", 8080).start();
    }
}
