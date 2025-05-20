package io.github.hyungjun.cakego.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class OptionGroup {
    private String name;
    private List<Option> options;

    public OptionGroup(String name, List<Option> options) {
        this.name = name;
        this.options = options;
    }
}
