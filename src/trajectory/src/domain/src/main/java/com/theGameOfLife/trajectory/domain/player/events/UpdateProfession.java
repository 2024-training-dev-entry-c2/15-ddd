package com.theGameOfLife.trajectory.domain.player.events;
import com.theGameOfLife.trajectory.domain.player.values.EducationalLevel;
import com.theGameOfLife.trajectory.domain.player.values.Name;
import com.theGameOfLife.trajectory.domain.player.values.NameProfession;
import com.theGameOfLife.trajectory.domain.player.values.Salary;
import com.theGameOfLife.shared.domain.generic.DomainEvent;

public class UpdateProfession extends DomainEvent{
    private final String id;
    private final Name name;
    private final NameProfession nameProfession;
    private final EducationalLevel educationalLevel;
    private final Salary salary;

    public UpdateProfession(String id, Name name, NameProfession nameProfession, EducationalLevel educationalLevel, Salary salary){
        super(this.EventsNameEnum.Update_Profession.name());
        this.id = id;
        this.name = name;
        this.nameProfession = nameProfession;
        this.educationalLevel = educationalLevel;
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public NameProfession getNameProfession() {
        return nameProfession;
    }

    public EducationalLevel getEducationalLevel() {
        return educationalLevel;
    }

    public Salary getSalary() {
        return salary;
    }
}