package com.dilaverdemirel.scwm.order.common;

/**
 * @author dilaverd
 */
public enum EvenType {
    CREATE("Created"),UPDATE("Updated"),DELETE("Deleted");
    private String label;

    EvenType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
