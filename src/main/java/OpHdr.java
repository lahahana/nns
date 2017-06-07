/**
 * Created by daile on 2017/6/7.
 */
public enum OpHdr {

    REGISTRER(0x00), SUBSCRIBE(0x01);

    int value;

    OpHdr(int value) {
        this.value = value;
    }
}
