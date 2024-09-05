package com.vietqr.org.service;

import com.vietqr.org.dto.boxdevice.BoxDeviceStatusDTO;
import com.vietqr.org.dto.boxdevice.BoxDeviceInsertDTO;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import org.springframework.stereotype.Service;

@Service
public interface BoxDeviceService {
    ResponseMessageDTO insertBoxDevice(BoxDeviceInsertDTO dto);
    Object findBoxDeviceByMid(String mid);
    Object findBoxDeviceByTid(String tid);
    ResponseMessageDTO activeBoxDeviceById(BoxDeviceStatusDTO dto);
    ResponseMessageDTO inactiveBoxDeviceById(BoxDeviceStatusDTO dto);
    ResponseMessageDTO deleteBoxDeviceById(BoxDeviceStatusDTO dto);
}
