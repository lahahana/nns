package nns.client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.AdaptiveRecvByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by daile on 2017/6/7.
 */
public class NettyClient {

	private static final Logger logger = LoggerFactory.getLogger(NettyClient.class);

    private static String inetHost = "localhost";

    private static int inetPort = 8080;
    
    public void start() {

        EventLoopGroup workerEventGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerEventGroup)
                .channel(NioSocketChannel.class)
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
                        ch.pipeline().addLast(new ClientPortalHandler());
                    }
                });
        try{
            ChannelFuture connectFuture = bootstrap.connect(inetHost, inetPort).sync();
            logger.info("Netty Client connect successfully");
            connectFuture.channel().closeFuture().sync();
        }catch (Exception e) {

        }finally {
            workerEventGroup.shutdownGracefully().syncUninterruptibly();
        }
    
    }

    public static void main(String[] args) {
    	new NettyClient().start();
    }
}
