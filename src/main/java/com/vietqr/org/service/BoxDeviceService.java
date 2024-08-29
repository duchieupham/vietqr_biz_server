package com.vietqr.org.service;

import com.vietqr.org.dto.boxdevice.BoxDeviceFindMidDTO;
import com.vietqr.org.dto.boxdevice.BoxDeviceInsertDTO;
import com.vietqr.org.dto.common.ResponseMessageDTO;
import org.springframework.stereotype.Service;

@Service
public interface BoxDeviceService {
    ResponseMessageDTO insertBoxDevice(BoxDeviceInsertDTO dto);
    Object findBoxDeviceByMid(BoxDeviceFindMidDTO dto);
}
