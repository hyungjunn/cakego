package io.github.hyungjun.cakego.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class CakeTemplate {
    private CakeSize size;
    private CakeOrderType cakeType;
    private Money basePrice;
    private boolean isAvailable;
    private List<OptionGroup> optionGroups;

    public CakeTemplate(
            CakeSize size,
            CakeOrderType cakeType,
            Money basePrice,
            boolean isAvailable,
            List<OptionGroup> optionGroups
    ) {
        this.size = size;
        this.cakeType = cakeType;
        this.basePrice = basePrice;
        this.isAvailable = isAvailable;
        this.optionGroups = optionGroups;
    }

    public boolean isHidden() {
        return !isAvailable;
    }

    public List<String> findInvalidOptionNames(List<Option> selectedOptions) {
        return selectedOptions.stream()
                .filter(selectedOpt -> !selectedOpt.isValidGroupInTemplate(this))
                .map(Option::getName)
                .toList();
    }

    public boolean contains(OptionGroup optionGroup) {
        return this.optionGroups.contains(optionGroup);
    }
}
