package com.pb.ProjetoGrupo2.constants;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Status {

    OPEN("open"),
    WITHDRAWN("withdrawn"),
    NOT_WITHDRAWN("not_withdrawn");

    Status(String value) {
        this.value = value;
    }

    private String value;

    @JsonCreator
    public static Status fromValue(String value) {
        for (Status status : Status.values()) {
            if (status.value.equalsIgnoreCase(value)) {
                return status;
            }
        }
        return null;
    }
}
