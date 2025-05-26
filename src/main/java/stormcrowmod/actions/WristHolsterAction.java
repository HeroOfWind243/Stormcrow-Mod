package stormcrowmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import stormcrowmod.cards.created.Invert;

public class WristHolsterAction extends AbstractGameAction {
    AbstractCard card;
    AbstractPlayer owner;

    public WristHolsterAction(AbstractPlayer owner, AbstractCard card) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.owner = owner;
        this.card = card;
    }

    public void update() {
        if (card.cost > 0) {
            card.freeToPlayOnce = true;
        }
        this.owner.hand.moveToDeck(card, false);
        this.isDone = true;
    }

}
