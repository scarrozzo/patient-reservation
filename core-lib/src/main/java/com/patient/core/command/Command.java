package com.patient.core.command;

@FunctionalInterface
public interface Command<T> {
    T execute();
}
