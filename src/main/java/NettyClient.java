import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;

/**
 * Created by daile on 2017/6/7.
 */
public class NettyClient {

    private static String inetHost = "localhost";

    private static int inetPort = 8080;

    public static void main(String[] args) {
        EventLoopGroup workerEventGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerEventGroup)
                .channel(SocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 2000)
                .option(ChannelOption.SO_KEEPALIVE, false)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_RCVBUF, 1024)
                .option(ChannelOption.AUTO_READ, true)
                .option(ChannelOption.RCVBUF_ALLOCATOR, AdaptiveRecvByteBufAllocator.DEFAULT)
                .option(ChannelOption.SO_SNDBUF, 17)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ch.pipeline().addLast(new PortalHandler());
                    }
                });
        try{
            bootstrap.connect(inetHost, inetPort).sync();

        }catch (Exception e) {

        }finally {
            workerEventGroup.shutdownGracefully();
        }
    }
}
