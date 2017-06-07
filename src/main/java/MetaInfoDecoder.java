import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by daile on 2017/6/7.
 */
public class MetaInfoDecoder extends ByteToMessageDecoder {

    private byte userIdLength = 15;

    private byte opLength = 1;

    private byte hdrLength = (byte)(userIdLength + opLength);


    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int length = in.readableBytes();
        byte[] dst = new byte[userIdLength];
        if(length >= hdrLength) {
            in.readBytes(dst, 0, 15);
            String userId = new String(dst);
            byte op = in.readByte();
        }
    }

}
