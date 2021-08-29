package com.ifce.assember;

import com.ifce.assember.assemblerHandler.AssemblerHandlerService;
import com.ifce.service.AssemblerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AssemblerServiceImpl implements AssemblerService {
    private final AssemblerHandlerService assemblerHandlerService;

    @Override
    public void assemble() {
        assemblerHandlerService.handler();
    }
}