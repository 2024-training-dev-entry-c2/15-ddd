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
import com.theGameOfLife.shared.domain.generic.DomainEvent.subscribe;
import com.theGameOfLife.shared.domain.generic.AggregateRoot;

public class Player extends AggregateRoot<PlayerId>{

    private UniversityCareer career;
    private List<Family> family = new ArrayList<>();
    private List<Properties> properties = new ArrayList<>();
    private Name name;
    private Diner money;
    private StateHealth stateHealth;
    private BoardPosition boardPosition;
    private State state;

    public Player(){
        super(new PlayerId());
        subscribe(new PlayerHandler(this));
        apply(new AcquiredProperty());
        apply(new LoseMoney(money));
        apply(new MakeyMoney(money));
        apply(new PropertySold(String id, Address address, Diner value, TypeProperty type))
        apply(new StateChange(state));
    }

    private Player(PlayerId Identity){
        super(Identity);
        subscribe(new PlayerHandler(this));
    }

    public UniversityCareer getCareer() {
        return career;
    }

    public void setCareer(UniversityCareer career) {
        this.career = career;
    }

    public List<Family> getFamily() {
        return family;
    }

    public void setFamily(Family familia) {
        this.family.add(familia)
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Diner getMoney() {
        return money;
    }

    public void setMoney(Diner money) {
        this.money = money;
    }

    public StateHealth getStateHealth() {
        return stateHealth;
    }

    public void setStateHealth(StateHealth stateHealth) {
        this.stateHealth = stateHealth;
    }

    public BoardPosition getBoardPosition() {
        return boardPosition;
    }

    public void setBoardPosition(BoardPosition boardPosition) {
        this.boardPosition = boardPosition;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public List<Properties> getProperties(){
        return properties;
    }

    public void setProperty(Properties property) {
        this.properties.add(property);
    }

    public setDiner(Diner money) {

            this.money = money;
        }
    }

    public void disminurDinero(Diner amount) {
        if (this.money.getAmount() < 0) {
            throw new IllegalArgumentException("Fondos insuficientes");
        }
    }

    public void validateAmount(Diner amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("No se puede agregar un monto negativo.");
        }
    }

    public encontrarPropiedad(String id) {
    Properties propertyToRemove = getProperties().stream()
            .filter(prop -> prop.getId().equals(event.getId()))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Propiedad no encontrada"));
    }

}