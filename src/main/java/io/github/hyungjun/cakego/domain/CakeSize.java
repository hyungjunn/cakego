package io.github.hyungjun.cakego.domain;

import java.util.List;

public class CakeSize {
    private CakeSizeName name;
    private CakeOrderType cakeType;
    private Money basePrice;
    private boolean isAvailable;
    private List<OptionGroup> optionGroups;

    public CakeSize(CakeSizeName name, CakeOrderType cakeType, Money basePrice, boolean isAvailable, List<OptionGroup> optionGroups) {
        this.name = name;
        this.cakeType = cakeType;
        this.basePrice = basePrice;
        this.isAvailable = isAvailable;
        this.optionGroups = optionGroups;
    }

    public boolean isHidden() {
        return !isAvailable;
    }
}
