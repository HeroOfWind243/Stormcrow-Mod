package stormcrowmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import stormcrowmod.cards.created.Invert;
import stormcrowmod.cards.created.Thruster;
import stormcrowmod.util.PilotTags;

public class PulseWidthModAction extends AbstractGameAction {
    private final int amount;
    AbstractCreature owner;

    public PulseWidthModAction(AbstractCreature owner, int amount) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.owner = owner;
        this.amount = amount;
    }

    public void update() {
        addToTop(new MakeTempCardInHandAction(new Invert(), amount));
        this.isDone = true;
    }

}
