package nns.server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by daile on 2017/6/7.
 */
public class NettyServer {

	private static final Logger logger = LoggerFactory.getLogger(NettyServer.class);

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
                .option(ChannelOption.SO_RCVBUF, 1024)
                .option(ChannelOption.AUTO_READ, true)
                .option(ChannelOption.RCVBUF_ALLOCATOR, AdaptiveRecvByteBufAllocator.DEFAULT)
                .option(ChannelOption.SO_RCVBUF, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, false)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                    	ch.pipeline().addLast(new MetaInfoDecoder());
                        ch.pipeline().addLast(new PortalHandler());
                    }
                });
        try{
            ChannelFuture bindFuture = serverBootstrap.bind(inetHost, inetPort).sync();
            logger.info("Netty Server Started.");
            Thread.sleep(5000L);
            new NettyPublisher().publish(new TextMsg("I'm server"));
            bindFuture.channel().closeFuture().sync();

        }catch (Exception e) {

        }finally {
            bossEventGroup.shutdownGracefully().syncUninterruptibly();
            workerEventGroup.shutdownGracefully().syncUninterruptibly();
        }
    }

    public static void main(String[] args) {
        new NettyServer("localhost", 8080).start();
    }
}
