package nns.server;
import java.net.InetAddress;

/**
 * Created by daile on 2017/6/7.
 */
public class ReqEntity {

    private String userId;

    private byte op;
    
    private InetAddress address;

    private byte[] content;
    
	public ReqEntity(String userId, byte op) {
		super();
		this.userId = userId;
		this.op = op;
	}

	public ReqEntity(String userId, byte op, byte[] content) {
		super();
		this.userId = userId;
		this.op = op;
		this.content = content;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public byte getOp() {
		return op;
	}

	public void setOp(byte op) {
		this.op = op;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "ReqEntity [userId=" + userId + ", op=" + op + ", content=" + content + "]";
	}
}
