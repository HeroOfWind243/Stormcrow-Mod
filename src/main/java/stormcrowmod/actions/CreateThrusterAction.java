package stormcrowmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import stormcrowmod.cards.created.Thruster;

public class CreateThrusterAction extends AbstractGameAction {
    private final int amount;
    private final boolean upgraded;

    public CreateThrusterAction(int amount) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.amount = amount;
        upgraded = false;
    }

    public CreateThrusterAction(int amount, boolean upgraded) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.amount = amount;
        this.upgraded = upgraded;
    }

    public void update() {
        AbstractCard thruster = new Thruster();
        if (this.upgraded) {
            thruster.upgrade();
        }

        addToTop(new MakeTempCardInDrawPileAction(thruster, this.amount, true, true, false));
        this.isDone = true;
    }

}
