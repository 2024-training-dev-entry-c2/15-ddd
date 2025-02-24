package com.dddimplement.exchange.domain.player.entities;

import com.dddimplement.exchange.domain.player.values.*;
import com.dddimplement.shared.domain.generic.Entity;

public class Territory extends Entity<TerritoryId> {
    private City city;
    private Path path;
    private Settlement settlement;

    public Territory( City city, Path path, Settlement settlement) {
        super(new TerritoryId());
        this.city = city;
        this.path = path;
        this.settlement = settlement;
    }

    public Territory(TerritoryId identity, City city, Path path, Settlement settlement) {
        super(identity);
        this.city = city;
        this.path = path;
        this.settlement = settlement;
    }


    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public Settlement getSettlement() {
        return settlement;
    }

    public void setSettlement(Settlement settlement) {
        this.settlement = settlement;
    }

    public void expand() {
        this.path = Path.of(path.getValue() + 1);
    }

    public void build(){
        this.settlement = Settlement.of(settlement.getValue() + 1);
    }

    public void upgrade(){
        if (settlement.getValue() < 1) {
            throw new IllegalStateException("No hay asentamientos suficientes para mejorar a ciudad.");
        }

        this.settlement = Settlement.of(settlement.getValue() - 1);
        this.city = City.of(city.getValue() + 1);
    }
}
