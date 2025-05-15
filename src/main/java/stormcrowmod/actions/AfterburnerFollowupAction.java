package stormcrowmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import stormcrowmod.util.PilotTags;

public class AfterburnerFollowupAction extends AbstractGameAction {
    private final int amountToDraw;
    private final AbstractCreature t;

    public AfterburnerFollowupAction(AbstractCreature target, int amountToDraw) {
        this.amountToDraw = amountToDraw;
        this.t = target;
        this.actionType = ActionType.DRAW;
    }


    public void update() {
        tickDuration();
        boolean thrusterDrawn = false;
        if (this.isDone) {
            for (AbstractCard c : DrawCardAction.drawnCards) {
                if (c.hasTag(PilotTags.THRUSTER)) {
                    thrusterDrawn = true;
                    break;
                }
            }

            if (thrusterDrawn) {
                addToTop(new DrawCardAction(t, amountToDraw));
            }
        }
    }

}
