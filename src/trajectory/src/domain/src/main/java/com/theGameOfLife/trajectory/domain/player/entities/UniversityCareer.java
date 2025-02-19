package com.theGameOfLife.trajectory.domain.player.entities;
import com.theGameOfLife.trajectory.domain.player.values.EducationalLevel;
import com.theGameOfLife.trajectory.domain.player.values.Name;
import com.theGameOfLife.trajectory.domain.player.values.PlayerId;
import com.theGameOfLife.trajectory.domain.player.values.Salary;
import com.theGameOfLife.trajectory.domain.player.values.StatusLevelEnum;
import com.theGameOfLife.shared.domain.generic.Identity;

public class UniversityCareer extends Entity<PlayerId>{
    private Name name;
    private EducationalLevel educationalLevel;
    private Salary salary;

    public UniversityCareer(Name name, EducationalLevel educationalLevel, Salary salary){
        super(new PlayerId());
        this.name = name;
        this.educationalLevel = educationalLevel;
        this.salary = salary;
    }

    public UniversityCareer(Identity id, Name name, EducationalLevel educationalLevel, Salary salary){
        super(id);
        this.name = name;
        this.educationalLevel = educationalLevel;
        this.salary = salary;
    }

    public Name getName() {
        return name;
    }

    public EducationalLevel getEducationalLevel() {
        return educationalLevel;
    }

    public Salary getSalary() {
        Salary.getSalary().getAmount() == 0 ?
                system.out.println("No tienes fondos") :
                system.out.println("Tienes fondos suficientes");
        return salary;
    }


    public changeEducationalLevel(EducationalLevel educationalLevel){
        if(educationalLevel.getLevel() == StatusLevelEnum.UNIVERSITARY){
            return educationalLevel.setLevel(StatusLevelEnum.MAGISTER);
        }else{
            return educationalLevel.setLevel(StatusLevelEnum.PRIMARY);
        }
    }

    public changeAumentoLevel(Salary salary){
        if(eduacationalLevel.getLevel() == StatusLevelEnum.MAGISTER){
            return salary.setAmount(salary.getAmount() * 2);
        }else{
            return salary.setAmount(salary.getAmount() * 1.5);
        }
    }
}