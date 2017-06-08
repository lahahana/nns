package nns.server;

public class TextMsg implements Msg {
	
	String info;

	public TextMsg(String info) {
		super();
		this.info = info;
	}

	@Override
	public byte[] getBytes() {
		return info.getBytes();
	}
	
}
