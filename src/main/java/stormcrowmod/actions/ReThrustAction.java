package stormcrowmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import stormcrowmod.util.PilotTags;

import java.util.ArrayList;

public class ReThrustAction extends AbstractGameAction {
    AbstractCard card;
    AbstractPlayer owner;

    public ReThrustAction(AbstractPlayer owner) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.owner = owner;
    }

    public void update() {
        ArrayList<AbstractCard> thrusters = new ArrayList<AbstractCard>();
        for (AbstractCard c : owner.discardPile.group) {
            if (c.hasTag(PilotTags.THRUSTER)) {
                thrusters.add(c);
            }
        }
        //Break them up so you don't change what you are iterating over in the first loop
        for (AbstractCard c :thrusters) {
            owner.discardPile.moveToDeck(c, true);
        }
        this.isDone = true;
    }

}
