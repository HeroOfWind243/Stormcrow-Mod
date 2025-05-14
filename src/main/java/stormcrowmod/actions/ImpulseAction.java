package stormcrowmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import stormcrowmod.cards.created.Impact;
import stormcrowmod.powers.MomentumPower;

public class ImpulseAction extends AbstractGameAction {
    private final int momentumToGrant;
    private final AbstractCreature target;

    public ImpulseAction(AbstractCreature t, int momentumToGrant) {
        this.momentumToGrant = momentumToGrant;
        this.target = t;
        this.actionType = ActionType.CARD_MANIPULATION;

    }

    public void update() {
        addToTop(new ApplyPowerAction(target, target, new MomentumPower(target, momentumToGrant)));
        // TODO: Add some fancy visual effect
        Impact impact = new Impact();
        addToTop(new MakeTempCardInHandAction(impact, 1));
        this.isDone = true;
    }

}
