package io.github.hyungjun.cakego.domain;

import java.util.List;

public class CakeSize {
    private String name;
    private CakeOrderType cakeType;
    private int basePrice;
    private boolean isAvailable;
    private List<OptionGroup> optionGroups;

    public CakeSize(String name, CakeOrderType cakeType, int basePrice, boolean isAvailable, List<OptionGroup> optionGroups) {
        this.name = name;
        this.cakeType = cakeType;
        this.basePrice = basePrice;
        this.isAvailable = isAvailable;
        this.optionGroups = optionGroups;
    }
}
