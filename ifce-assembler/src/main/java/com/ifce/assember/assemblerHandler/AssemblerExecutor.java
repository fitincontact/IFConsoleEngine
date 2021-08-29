package com.ifce.assember.assemblerHandler;

import com.ifce.assember.assemblerHandler.handlers.AssemblerHandler;

public class AssemblerExecutor {
    private AssemblerHandler currentHandler;

    public AssemblerExecutor(final AssemblerHandler handler) {
        currentHandler = handler;
    }

    public AssemblerExecutor() {
    }

    public AssemblerExecutor addHandler(final AssemblerHandler handler) {
        if (currentHandler != null) {
            currentHandler.exec();
        }
        return new AssemblerExecutor(
                handler
        );
    }

    public void exec() {
        currentHandler.exec();
    }
}