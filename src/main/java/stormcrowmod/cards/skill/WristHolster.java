package stormcrowmod.cards.skill;

import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import stormcrowmod.actions.ExhaustCardAction;
import stormcrowmod.actions.WristHolsterAction;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.cards.created.Impact;
import stormcrowmod.character.PilotCharacter;
import stormcrowmod.powers.CraterPower;
import stormcrowmod.powers.MomentumPower;
import stormcrowmod.util.CardStats;

import java.util.List;
import java.util.function.Consumer;

public class WristHolster extends BaseCard {
    public static final String ID = makeID(WristHolster.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            PilotCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1 //Can use -1 for X, or -2 for unplayable
    );

    public WristHolster() {
        super(ID, info);

        setCostUpgrade(0);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Consumer<List<AbstractCard>> card = list -> {
            for (AbstractCard c : list) {
                addToTop(new WristHolsterAction(p, c));
            }

        };

        addToBot(new SelectCardsInHandAction(1,"put on top of your draw pile.",(c) -> c.type == CardType.ATTACK, card));
        addToBot(new PressEndTurnButtonAction());
    }

    @Override
    public AbstractCard makeCopy() {
        return new WristHolster();
    }
}
