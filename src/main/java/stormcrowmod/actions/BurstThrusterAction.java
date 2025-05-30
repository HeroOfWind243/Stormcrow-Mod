package stormcrowmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import stormcrowmod.util.PilotTags;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class BurstThrusterAction extends AbstractGameAction {
    private final AbstractPlayer p;
    private final boolean upgraded;
    private final int strPerCard;

    public BurstThrusterAction(AbstractPlayer player, int strPerCard, boolean upgraded) {
        this.p = player;
        this.strPerCard = strPerCard;
        this.upgraded = upgraded;
        this.actionType = ActionType.POWER;
    }


    public void update() {
        int amount = 0;
        ArrayList<AbstractCard> thrusters = new ArrayList<>();

        for (AbstractCard c : p.hand.group) {
            if (c.hasTag(PilotTags.THRUSTER)) {
                thrusters.add(c);
            }
        }
        for (AbstractCard c : thrusters) {
            if (!upgraded) {
                p.hand.moveToExhaustPile(c);
            } else {
                c.applyPowers();
                AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(c, null, 0,true, true), true);
            }
            amount++;
        }

        for (int i = 0; i < amount; i++) {
            addToTop(new ApplyPowerAction(p, p, new LoseStrengthPower(p, strPerCard), strPerCard, true));
            addToTop(new ApplyPowerAction(p, p, new StrengthPower(p, strPerCard), strPerCard, true));
        }

        this.isDone = true;

    }

}
