package com.sapient.publicis.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mgmresorts.aurora.messages.GetRoomPricingAndAvailabilityRequest;
import com.mgmresorts.aurora.messages.GetRoomPricingAndAvailabilityResponse;
import com.sapient.publicis.helper.JsonReaderService;

@Controller
@RequestMapping(
        value = "/room")
public class RoomPricingAndAvailabilityController {

    private static Logger LOG = LoggerFactory.getLogger(RoomPricingAndAvailabilityController.class);

    @RequestMapping(
            value = "/pricingAndAvailabilityEx", method = RequestMethod.GET, produces = "*/*")
    @ResponseStatus(
            value = HttpStatus.OK)
    @ResponseBody
    public GetRoomPricingAndAvailabilityResponse getRoomProgramPricingAndAvailability(HttpServletRequest request,
            @RequestParam MultiValueMap<String, String> params) {
        LOG.info("getRoomProgramPricingAndAvailability method started");
        InputStream inputStream = null;
        GetRoomPricingAndAvailabilityResponse availabilityResponse = null;
        StringBuilder filePath = new StringBuilder("getRoomPricingAndAvailabilityEx-");
        try {
            // date format should be in "yyyy-MM-dd'T'HH:mm:ss.SSSZ" format (e.g. 2011-05-02T19:50:42.868-04:00)
            
            GetRoomPricingAndAvailabilityRequest availabilityRequest = new ObjectMapper()
                    .readValue(params.get("request").get(0), GetRoomPricingAndAvailabilityRequest.class);
            
            final String programId = availabilityRequest.getProgramId();
            
            if ("CalendarPricing".equalsIgnoreCase(availabilityRequest.getPricingType().toString())) {
                filePath.append("CP");
            } else {
                filePath.append("TP");
            }

            if (programId != null) {
                filePath.append("-").append(programId);
            }

            if (availabilityRequest.getCustomerId() == -1) {
                filePath.append("-TU");
            } else {
                filePath.append("-LU");
            }
            filePath.append(availabilityRequest.getPropertyId() == null ? "" : "-" + availabilityRequest.getPropertyId()).append(".json");
            LOG.info("file name being read is::{}", filePath);
            /*inputStream = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream(filePath.toString());
            availabilityResponse = JsonReaderService.mockResponse(inputStream, GetRoomPricingAndAvailabilityResponse.class);*/
            
            inputStream = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("getRoomPricingAndAvailabilityEx-CP-TU-66964e2b-2550-4476-84c3-1a4c0c5c067f.json");
            availabilityResponse =  JsonReaderService.mockResponse(inputStream, GetRoomPricingAndAvailabilityResponse.class);
        } catch (IOException ee) {
            LOG.error("Error occured during json mapping::{}", ee);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    LOG.error("Error occured closing inputstream::{}", e);

                }
            }
        }
        
        return availabilityResponse;
//        return reservationResponse;
    }
    
    /**
     * Convert the given date into string format
     * 
     * @param format
     * @param dateInput
     * @return String
     * @since
     */
    public static String converDateToString(String format, Date dateInput) {
        if (null != dateInput) {
            final DateFormat dateFormat = new SimpleDateFormat(format, Locale.ENGLISH);
            return dateFormat.format(dateInput);
        }
        return null;
    }

}
