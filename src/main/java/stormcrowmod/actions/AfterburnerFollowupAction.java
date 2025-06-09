package stormcrowmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import stormcrowmod.util.PilotTags;

import java.util.ArrayList;
import java.util.List;

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
        AbstractCard exhaust = null;
        boolean thrusterDrawn = false;
        if (this.isDone) {
            for (AbstractCard c : DrawCardAction.drawnCards) {
                if (c.hasTag(PilotTags.THRUSTER)) {
                    thrusterDrawn = true;
                    exhaust = c;
                    break;
                }
            }

            if (thrusterDrawn) {
//                List<AbstractCard> toBurn = new ArrayList<>();
//                toBurn.add(exhaust);
//                addToBot(new ShowAndBurnCardsAction(toBurn));
                addToTop(new ExhaustSpecificCardAction(exhaust, AbstractDungeon.player.hand, true));
                addToTop(new DrawCardAction(t, amountToDraw));

            }
        }
    }

}
