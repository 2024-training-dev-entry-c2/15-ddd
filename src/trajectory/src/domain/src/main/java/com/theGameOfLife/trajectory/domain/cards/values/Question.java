package com.theGameOfLife.trajectory.domain.cards.values;
import com.theGameOfLife.shared.domain.generic.IValueObject;
import com.theGameOfLife.shared.domain.utils.ValidateUtils;

public class Question implements IValueObject{
    private final String question;

    private Question(String question){
        this.question = question;
        validate();
    }

    public static Question of(String question){
        return new Question(question);
    }

    @Override
    public void validate() {
        ValidateUtils.validateIsNotBlank(question, "la pregunta no puede estar vacio");
        ValidateUtils.validateIsNotEmpty(question, "la pregunta es requerida");
        ValidateUtils.validateIsString(question, "la pregunta no puede ser un numero");
        ValidateUtils.validateIsNotNull(question, "la pregunta no puede ser null");
    }

    public String getQuestion() {
        return question;
    }

}