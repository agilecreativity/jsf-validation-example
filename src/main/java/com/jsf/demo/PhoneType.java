package com.jsf.demo;

public enum PhoneType {

	HOME("Home Phone"),
    WORK("Work Phone"),
    MOBILE("Mobile Phone");

    private String label;

    private PhoneType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}