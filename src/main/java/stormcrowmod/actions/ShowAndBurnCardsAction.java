package stormcrowmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ExhaustCardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;

import java.util.ArrayList;
import java.util.List;

public class ShowAndBurnCardsAction extends AbstractGameAction {
    private List<AbstractCard> cards;

    private boolean burning = false;
    private boolean burnt = false;


    public ShowAndBurnCardsAction(List<AbstractCard> cards) {
        this.cards = new ArrayList<>(cards);

        this.actionType = ActionType.EXHAUST;

        this.duration = 2.0f;
    }

    public void update() {
        if (this.duration == 2.0f && !burning) {
            for (AbstractCard c : cards) {
                c.untip();
                c.lighten(true);
                AbstractDungeon.effectList.add(new ShowCardBrieflyEffect(c));

            }
            burning = true;
        }
        if (this.duration < 1.0f && !burnt) {
            for (AbstractCard c : cards) {
                AbstractDungeon.effectsQueue.add(new ExhaustCardEffect(c));
            }
            burnt = true;
        }

        tickDuration();
    }
}
