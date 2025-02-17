package com.theGameOfLife.trajectory.domain.player;
import com.theGameOfLife.trajectory.domain.player.entities.Family;
import com.theGameOfLife.trajectory.domain.player.entities.Properties;
import com.theGameOfLife.trajectory.domain.player.events.AcquiredProperty;
import com.theGameOfLife.trajectory.domain.player.events.LoseMoney;
import com.theGameOfLife.trajectory.domain.player.events.MakeyMoney;
import com.theGameOfLife.trajectory.domain.player.events.PropertySold;
import com.theGameOfLife.trajectory.domain.player.events.StateChange;
import com.theGameOfLife.trajectory.domain.player.events.UpdateFamily;
import com.theGameOfLife.trajectory.domain.player.events.UpdateProfession;
import com.theGameOfLife.trajectory.domain.player.values.Address;
import com.theGameOfLife.trajectory.domain.player.values.BoardPosition;
import com.theGameOfLife.trajectory.domain.player.values.Children;
import com.theGameOfLife.shared.domain.generic.AggregateRoot;

public class Player extends AggregateRoot<PlayerId>{

    private UniversityCareer career;
    private Family family;
    private Properties propertys;
    private Name name;
    private Diner money;
    private StateHealth stateHealth;
    private BoardPosition boardPosition;
    private State state;

    public Player(){
        super(new PlayerId());
    }

    private Player(PlayerId Identity){
        super(Identity);
    }

    getName(){
        return name;
    }

    getDiner(){
        return money;
    }

    getStateHealth(){
        return stateHealth;
    }

    getBoardPosition(){
        return boardPosition;
    }

    getState(){
        return state;
    }

    setName(Name name){
        this.name = name;
    }

    setDiner(Diner money){
        this.money = money;
    }

    setStateHealth(StateHealth stateHealth){
        this.stateHealth = stateHealth;
    }

    setBoardPosition(BoardPosition boardPosition){
        this.boardPosition = boardPosition;
    }

    setState(State state){
        this.state = state;
    }

    //region actions

    public void acquireProperty(Address address, ValueProperty value, TypeProperty type){
        apply(new AcquiredProperty(address, value, type));
    }

    public void loseMoney(Diner amount){
        apply(new LoseMoney(amount));
    }

    public void makeMoney(Diner amount, Salary salary){
        apply(new MakeyMoney(amount, salary));
    }

    public void propertySold(String id, Address address, Diner value, TypeProperty type){
        apply(new PropertySold(id, address, value, type));
    }

    public void stateChange(StatePlayerEnum state){
        apply(new StateChange(state));
    }

    public void updateFamily(Children children, Coupe coupe){
        apply(new UpdateFamily(children, coupe));
    }

    public void updateProfession(String id, Name name, NameProfession nameProfession, EducationalLevel educationalLevel, Salary salary){
        apply(new UpdateProfession(id, name, nameProfession, educationalLevel, salary));
    }

    //endregion



}