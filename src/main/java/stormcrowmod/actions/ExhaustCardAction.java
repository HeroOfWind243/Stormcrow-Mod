package stormcrowmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ExhaustCardAction extends AbstractGameAction {
    private final AbstractCard c;

    public ExhaustCardAction(AbstractCard card) {
        this.c = card;
        this.actionType = ActionType.EXHAUST;
    }

    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        p.hand.moveToExhaustPile(this.c);
        this.isDone = true;
    }

}
