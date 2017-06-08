package nns.server;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;

public class NettyPublisher implements Publisher {

	@Override
	public ChannelFuture publish(Msg msg) {
		byte[] msgBytes = msg.getBytes();
		ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.ioBuffer(msgBytes.length);
		byteBuf.writeBytes(msgBytes);
		Cache.registeredUsers.values().parallelStream().forEach(x -> x.writeAndFlush(byteBuf));
		return null;
	}

}
