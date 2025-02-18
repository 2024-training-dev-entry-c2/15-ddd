package com.theGameOfLife.trajectory.domain.player.entities;
import com.theGameOfLife.trajectory.domain.player.values.PlayerId;
import com.theGameOfLife.trajectory.domain.player.values.Couple;
import com.theGameOfLife.trajectory.domain.player.values.Children;
package com.theGameOfLife.shared.domain.generic;


public class Family extends Entity<PlayerId>{
    private Couple couple;
    private Children children;

    public Family( Couple couple, Children children){
        super(new PlayerId());
        this.couple = couple;
        this.children = children;
    }

    public Family(Identity id, Couple couple, Children children){
        super(id);
        this.couple = couple;
        this.children = children;
    }

    public Couple getCouple() {
        return couple;
    }

    public Children addChildrenToFamily() {
        children.getNumberOfChildren() == 0 ?
                children.setNumberOfChildren(1) :
                children.setNumberOfChildren(children.getNumberOfChildren() + 1);
    }

    public StateChange changeStateOfPlayer() {
        StateChange.getStateChange().getStatePlayer() == StatePlayerEnum.ACTIVE ?
                StateChange.getStateChange().setStatePlayer(StatePlayerEnum.RETIRED) :
                StateChange.getStateChange().setStatePlayer(StatePlayerEnum.ACTIVE);
    }

}