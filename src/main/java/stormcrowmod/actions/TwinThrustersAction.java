package stormcrowmod.actions;

import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import stormcrowmod.util.PilotTags;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class TwinThrustersAction extends AbstractGameAction {
    private final AbstractPlayer p;

    public TwinThrustersAction(AbstractPlayer player) {
        this.p = player;
        this.actionType = ActionType.CARD_MANIPULATION;
    }


    public void update() {

        CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

        List<AbstractCard> ammo = new ArrayList<>();

        for (AbstractCard c : p.discardPile.group) {
            if (c.hasTag(PilotTags.THRUSTER)) {
                ammo.add(c);
            }
        }

        List<AbstractCard> eligible = new ArrayList<>();

        for (AbstractCard t : p.drawPile.group) {
            if (t.hasTag(PilotTags.THRUSTER) && !t.upgraded) {
                eligible.add(t);
            }
        }

        int amount = eligible.size();
        if (amount > ammo.size()) {
            amount = ammo.size();
        }

        if (amount > 0) {
            for (int i = 0; i < amount; i++) {
                int r = AbstractDungeon.cardRandomRng.random(eligible.size() - 1);
                eligible.get(r).upgrade();
                eligible.remove(r);

                int r2 = AbstractDungeon.cardRandomRng.random(ammo.size() - 1);
                tmp.addToTop(ammo.get(r2));
                p.discardPile.removeCard(ammo.get(r2));
                ammo.remove(r2);
            }

            addToBot(new ShowAndBurnCardsAction(tmp.group));
            addToBot(new MoveCardsAction(p.exhaustPile, tmp, 99));
        }

        this.isDone = true;
    }

}
