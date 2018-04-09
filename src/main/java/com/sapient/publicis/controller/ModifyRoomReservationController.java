package com.sapient.publicis.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.sapient.publicis.helper.JsonReaderService;
import com.sapient.publicis.vo.GetModifiedRoomReservationResponse;

@Controller
@RequestMapping(
        value = "/rooms")
public class ModifyRoomReservationController {

    private static Logger LOG = LoggerFactory.getLogger(ModifyRoomReservationController.class);

    @RequestMapping(
            value = "/modify", method= RequestMethod.POST , produces = "*/*")
    @ResponseBody
    @ResponseStatus(
            value = HttpStatus.OK)
    public GetModifiedRoomReservationResponse modifyRoomReservation(HttpServletRequest request) {
        LOG.info("modifyRoomReservation method started");
        InputStream inputStream = null;
        try {
            inputStream = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("getModifiedReservation.json");
            return JsonReaderService.mockResponse(inputStream, GetModifiedRoomReservationResponse.class);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    LOG.error("Error occured closing inputstream", e);

                }
            }
        }
    }

}
