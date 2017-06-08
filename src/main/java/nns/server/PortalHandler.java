package nns.server;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * Created by daile on 2017/6/7.
 */
@Sharable
public class PortalHandler extends SimpleChannelInboundHandler<ReqEntity> {

    private static final Logger logger = LoggerFactory.getLogger(PortalHandler.class);
    
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        logger.info("{} registered", ctx.channel().remoteAddress());
        ctx.channel().writeAndFlush(Unpooled.copiedBuffer("Welcome".getBytes()));
        super.channelRegistered(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ReqEntity msg) throws Exception {
    	logger.info("read msg:{}", msg);
    	if(msg.getOp() == OpHdr.REGISTRER.value) {
    		Cache.registeredUsers.put(msg.getUserId(), ctx.channel());
    	}else if(msg.getOp() == OpHdr.SUBSCRIBE.value) {
    		// user can subscribe topics after register
    	}else if(msg.getOp() == OpHdr.UNSUBSCRIBE.value) {
    		// user can unsubscribe topics 
    	}
    }

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if(evt instanceof IdleStateEvent) {
			if(evt.equals(IdleStateEvent.WRITER_IDLE_STATE_EVENT)) {
				//client not send message to server for a idle time, remove from registered users
			}
		}
		super.userEventTriggered(ctx, evt);
	}
    
}
