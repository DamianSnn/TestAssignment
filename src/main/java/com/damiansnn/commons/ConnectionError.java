package com.damiansnn.commons;

import java.util.Objects;

public final class ConnectionError implements AppError {
    private final String message;

    public ConnectionError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConnectionError that = (ConnectionError) o;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public String toString() {
        return "ConnectionError{" + "message='" + message + '\'' + '}';
    }
}
