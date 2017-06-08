package nns.server;
import io.netty.channel.ChannelFuture;
import nns.server.Msg;

/**
 * Created by daile on 2017/6/7.
 */
public interface Publisher {

    ChannelFuture publish(Msg msg);

}
