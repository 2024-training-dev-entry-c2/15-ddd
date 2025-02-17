package com.theGameOfLife.trajectory.domain.player.values;
import com.theGameOfLife.shared.domain.generic.IValueObject;
import com.theGameOfLife.shared.domain.utils.ValidateUtils;
import com.theGameOfLife.trajectory.domain.player.values.StatusLevelEnum;

public class EducationalLevel implements IValueObject {
    private final StatusLevelEnum level;

    private EducationalLevel(String level){
        this.level = level;
        validate();
    }

    public static EducationalLevel of(StatusLevelEnum level){
        return new EducationalLevel(level);
    }

    @Override
    public void validate() {
        ValidateUtils.validateIsNotBlank(level, "el nivel de educacion no puede estar vacio");
        ValidateUtils.validateIsNotEmpty(level, "el nivel de educacion no puede ser null");
    }

    public StatusLevelEnum getLevel() {
        return level;
    }
}