package org.app.apilogs.interfaces;


import org.app.apilogs.dtos.LogDTORequest;
import org.app.apilogs.dtos.LogDTOResponse;

import java.util.List;

public interface LogInterface {
    List<LogDTOResponse> getAllLogs();
    LogDTOResponse saveLog(LogDTORequest logDTO);
}
