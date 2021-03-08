package com.iqb.been.socket;


public class SocketSyncPPTEntity {

    /**
     * pptSync : yes
     * key : ispring::{ECED7E8E-D5E9-4C16-B737-85F94D852B7E}
     * value : {"lastViewedSlide":2,"viewDuration":137,"slideStates":{"0":{"completed":false,"visited":true},"1":{"completed":false,"visited":true},"2":{"completed":false,"visited":true},"3":{"completed":true,"visited":true}},"slideTimelineStates":{"0":{"s":0,"t":0.001,"S":{"m":{"s":"waitForTrigger","t":1,"T":{"t":[0,1],"at":[{"t":0,"p":[{"time":0,"aL":1602645182739,"freeze":false}]},{"t":0,"p":[{"time":0,"aL":1602645182748,"freeze":false}]}]},"nt":2},"i":[]}},"1":{"s":0,"t":0.001,"S":{"m":{"s":"waitForTrigger","t":1,"T":{"t":[0,1],"at":[{"t":0,"p":[{"time":0,"aL":1602645228961,"freeze":false}]},{"t":0,"p":[{"time":0,"aL":1602645229993,"freeze":false}]}]},"nt":2},"i":[]}},"2":{"s":3,"t":0.5,"S":{"m":{"s":"playing","t":500,"T":{"t":[0,1,2,3,4],"at":[{"t":0,"p":[{"time":0,"aL":1602645271394,"freeze":false}]},{"t":0,"p":[{"time":0,"aL":1602645272390,"freeze":false},{"time":1,"aL":1602645291376,"freeze":false}]},{"t":0,"p":[{"time":0,"aL":1602645291377,"freeze":false},{"time":999,"aL":1602645298957,"freeze":false}]},{"t":0,"p":[{"time":0,"aL":1602645298957,"freeze":false},{"time":2000,"aL":1602645304422,"freeze":false}]},{"t":0,"p":[{"time":0,"aL":1602645304422,"freeze":false}]}]}},"i":[]}},"3":{"s":0,"t":0.0010000000474974513,"S":{"m":{"s":"completed","t":1.0000000474974513,"T":{"t":[0,1],"at":[{"t":0,"p":[{"time":0,"aL":1602645260520,"freeze":false}]},{"t":0,"p":[{"time":0,"aL":1602645261498,"freeze":false}]}]}},"i":[]}}}}
     */

    private String pptSync;
    private String key;
    private String value;

    public String getPptSync() {
        return pptSync;
    }

    public void setPptSync(String pptSync) {
        this.pptSync = pptSync;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
