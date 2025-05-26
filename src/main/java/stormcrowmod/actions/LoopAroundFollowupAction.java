package stormcrowmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import stormcrowmod.powers.MomentumPower;
import stormcrowmod.util.PilotTags;

public class LoopAroundFollowupAction extends AbstractGameAction {
    private final int mToGive;
    private final AbstractPlayer t;

    public LoopAroundFollowupAction(AbstractPlayer target, int mToGive) {
        this.mToGive = mToGive;
        this.t = target;
        this.actionType = ActionType.SPECIAL;
    }


    public void update() {
        tickDuration();
        if (this.isDone) {
            for (AbstractCard c : DrawCardAction.drawnCards) {
                if (c.type == AbstractCard.CardType.ATTACK) {
                    addToTop(new ApplyPowerAction(t, t, new MomentumPower(t, mToGive)));
                }
                if (c.type == AbstractCard.CardType.SKILL) {
                    t.hand.moveToDiscardPile(c);
                    c.triggerOnManualDiscard();
                    GameActionManager.incrementDiscard(false);
                }
            }
        }
    }

}
