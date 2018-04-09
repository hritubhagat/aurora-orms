/**
 * 
 */
package com.sapient.publicis.vo;

import java.io.Serializable;

import com.mgmresorts.aurora.common.RoomReservation;
import com.mgmresorts.aurora.messages.MessageHeader;

/**
 * @author tkar1
 *
 */
public class GetModifiedRoomReservationResponse implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -1180316153957547812L;

    private final long[] _xFieldBitmask_ = new long[1];
    private MessageHeader header;
    private RoomReservation modifiedReservation;
    private short xRogType;

    public MessageHeader getHeader() {
        return header;
    }

    public void setHeader(MessageHeader header) {
        this.header = header;
    }

    public RoomReservation getModifiedReservation() {
        return modifiedReservation;
    }

    public void setModifiedReservation(RoomReservation modifiedReservation) {
        this.modifiedReservation = modifiedReservation;
    }

    public short getxRogType() {
        return xRogType;
    }

    public void setxRogType(short xRogType) {
        this.xRogType = xRogType;
    }

    public long[] get_xFieldBitmask_() {
        return _xFieldBitmask_;
    }

}
