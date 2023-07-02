package com.ifce.util.cor;

/**
 * Chain of responsibility
 */
public class CoRExecutor {
    private final CoRHandler currentHandler;

    public CoRExecutor(final CoRHandler handler) {
        currentHandler = handler;
    }

    public CoRExecutor addHandler(final CoRHandler handler) {
        if (currentHandler != null) {
            currentHandler.exec();
        }
        return new CoRExecutor(
                handler
        );
    }

    public void exec() {
        currentHandler.exec();
    }
}
