import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by daile on 2017/6/7.
 */
public class PortalHandler extends SimpleChannelInboundHandler<ReqEntity> {

    private static final Logger logger = LoggerFactory.getLogger(PortalHandler.class);

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        logger.info("{} registered", ctx.channel().remoteAddress());
        super.channelRegistered(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ReqEntity msg) throws Exception {
        System.out.println(msg);
    }
}
