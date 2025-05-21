package stormcrowmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import stormcrowmod.util.PilotTags;

import static stormcrowmod.StormcrowMod.makeID;

public class ReactionForceFollowupAction extends AbstractGameAction {
    private final AbstractPlayer player;

    public ReactionForceFollowupAction(AbstractPlayer p) {
        this.player = p;
        this.actionType = ActionType.BLOCK;
    }


    public void update() {
        tickDuration();
        if (this.isDone && player.hasPower(makeID("Momentum"))) {
            // The +1 accounts for the amount of Momentum gained for playing the Impact
            // I'm lazy and don't want to figure out how to make the order of operations any cleaner
            addToTop(new GainBlockAction(player, player.getPower(makeID("Momentum")).amount + 1));
        }
    }

}
