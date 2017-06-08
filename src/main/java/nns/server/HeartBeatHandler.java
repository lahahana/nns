package nns.server;
import io.netty.handler.timeout.IdleStateHandler;

public class HeartBeatHandler extends IdleStateHandler {

	public HeartBeatHandler(int readerIdleTimeSeconds, int writerIdleTimeSeconds, int allIdleTimeSeconds) {
		super(readerIdleTimeSeconds, writerIdleTimeSeconds, allIdleTimeSeconds);
	}
	
	

}
