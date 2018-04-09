package com.sapient.publicis.vo;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author tkar1
 *
 */
@JsonInclude(Include.NON_EMPTY)
public class EditReservationRequest implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -4206275013068095021L;

    @NotNull(
            message = "invalid.reservationid", groups = { ModifyReservationValidation.class,
                    EditReservationValidation.class, ConfirmModifiedReservation.class })
    private String reservationId;

    private String confirmationNumber;

    @JsonFormat(
            pattern = "MM/dd/yyyy")
    private Date checkInDate;

    @JsonFormat(
            pattern = "MM/dd/yyyy")
    private Date checkOutDate;

    private String componentIds;

    @NotNull(
            message = "invalid.propertyid", groups = { ModifyReservationValidation.class,
                    EditReservationValidation.class, ConfirmModifiedReservation.class })
    private String propertyId;

    @DecimalMin(
            value = "1", inclusive = true, message = "invalid.tripduration",
            groups = { ModifyReservationValidation.class })
    private int maxTripDuration;

    private String cardCVV;
    
    /**
     * @return confirmationNumber: confirmationNumber
     */
    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    /**
     * @param confirmationNumber:
     *            confirmationNumber
     */
    public void setConfirmationNumber(String confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }

    /**
     * @return checkInDate: checkInDate
     */
    public Date getCheckInDate() {
        return checkInDate;
    }

    /**
     * @param checkInDate:
     *            checkInDate
     */
    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    /**
     * @return checkOutDate: checkOutDate
     */
    public Date getCheckOutDate() {
        return checkOutDate;
    }

    /**
     * @param checkOutDate:
     *            checkOutDate
     */
    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    /**
     * @return componentIds: componentIds
     */
    public String getComponentIds() {
        return componentIds;
    }

    /**
     * @param componentIds:
     *            componentIds
     */
    public void setComponentIds(String componentIds) {
        this.componentIds = componentIds;
    }

    /**
     * @return reservationId: reservationId
     */
    public String getReservationId() {
        return reservationId;
    }

    /**
     * @param reservationId:
     *            reservationId
     */
    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    /**
     * @return propertyId: propertyId
     */
    public String getPropertyId() {
        return propertyId;
    }

    /**
     * @param propertyId:
     *            propertyId
     */
    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    /**
     * @return maxTripDuration: maxTripDuration
     */
    public int getMaxTripDuration() {
        return maxTripDuration;
    }

    /**
     * @param maxTripDuration:
     *            maxTripDuration
     */
    public void setMaxTripDuration(int maxTripDuration) {
        this.maxTripDuration = maxTripDuration;
    }

    /**
     * @return cardCVV: cardCVV
     */
    public String getCardCVV() {
        return cardCVV;
    }

    /**
     * @param cardCVV:
     *            cardCVV
     */
    public void setCardCVV(String cardCVV) {
        this.cardCVV = cardCVV;
    }

    /**
     * Validation as reservation id should not be null if its present in
     * request.
     * 
     * @return
     */
    @JsonIgnore
    @AssertTrue(
            message = "invalid.reservationid",
            groups = { ModifyReservationValidation.class, EditReservationValidation.class })
    public boolean isValidRoomReservationIdNotEmpty() {
        boolean validId = false;
        final String bReservationId = this.getReservationId();
        if (StringUtils.isNotBlank(bReservationId)) {
            validId = true;
        }
        return validId;
    }

    /**
     * Validation as reservation id should not be null if its present in
     * request.
     * 
     * @return
     */
    @JsonIgnore
    @AssertTrue(
            message = "invalid.propertyid", groups = { EditReservationValidation.class })
    public boolean isValidPropertyIdNotEmpty() {
        boolean validPropertyId = true;
        final String bPropertyId = this.getPropertyId();
        if (StringUtils.isBlank(bPropertyId)) {
            validPropertyId = false;
        }
        return validPropertyId;
    }

    /**
     * For Edit Room Call Validation
     *
     */
    public interface EditReservationValidation extends Default {
    }

    /**
     * For Edit Room Modify Call Validation
     *
     */
    public interface ModifyReservationValidation extends Default {
    }

    /**
     * For Edit Room Confirm Call Validation
     *
     */
    public interface ConfirmModifiedReservation extends Default {
    }

}
