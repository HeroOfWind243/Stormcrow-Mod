package stormcrowmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import stormcrowmod.util.PilotTags;

public class TwinThrustersAction extends AbstractGameAction {
    private final AbstractPlayer p;

    public TwinThrustersAction(AbstractPlayer player) {
        this.p = player;
        this.actionType = ActionType.CARD_MANIPULATION;
    }


    public void update() {
        int thrustersInHand = 0;
        for (AbstractCard c : p.hand.group) {
            if (c.hasTag(PilotTags.THRUSTER)) {
                thrustersInHand++;
            }
        }
        if (thrustersInHand > 0) {
            addToTop(new CreateThrusterAction(thrustersInHand));
        }
        this.isDone = true;

    }

}
