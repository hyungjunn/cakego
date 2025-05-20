package io.github.hyungjun.cakego.domain;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CakeSize {
    private CakeSizeName name;
    private CakeOrderType cakeType;
    private Money basePrice;
    private boolean isAvailable;
    private List<OptionGroup> optionGroups;

    public CakeSize(
            CakeSizeName name,
            CakeOrderType cakeType,
            Money basePrice,
            boolean isAvailable,
            List<OptionGroup> optionGroups
    ) {
        this.name = name;
        this.cakeType = cakeType;
        this.basePrice = basePrice;
        this.isAvailable = isAvailable;
        this.optionGroups = optionGroups;
    }

    public boolean isHidden() {
        return !isAvailable;
    }

    public List<String> findInvalidOptionNames(List<Option> selectedOptions) {
        Set<Option> validOptions = optionGroups.stream()
                .flatMap(g -> g.getOptions().stream())
                .collect(Collectors.toSet());

        return selectedOptions.stream()
                .filter(o -> !validOptions.contains(o))
                .map(Option::getName)
                .toList();
    }
}
