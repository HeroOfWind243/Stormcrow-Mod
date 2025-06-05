package stormcrowmod.cards.attack;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import stormcrowmod.actions.PulseAction;
import stormcrowmod.actions.ShattercrashAction;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.cards.created.Impact;
import stormcrowmod.cards.created.RealDodge;
import stormcrowmod.character.PilotCharacter;
import stormcrowmod.util.CardStats;
import stormcrowmod.util.PilotTags;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Shattercrash extends BaseCard {
    public static final String ID = makeID(Shattercrash.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            PilotCharacter.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.RARE,
            CardTarget.ENEMY,
            -1 //Can use -1 for X, or -2 for unplayable
    );


    public Shattercrash() {
        super(ID, info);

        this.cardsToPreview = new Impact();

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        Consumer<List<AbstractCard>> card = c -> {
            for (AbstractCard i : c) {
                addToTop(new ShattercrashAction(p, m, this.freeToPlayOnce, this.energyOnUse, this.upgraded, i));
            }
        };

        Predicate<AbstractCard> filter = i -> (i.hasTag(PilotTags.IMPACT));

        addToBot(new SelectCardsInHandAction(1, "Exhaust", false, false, filter, card));

//        if (p.hasPower(makeID("Momentum"))) {
//            int half_m_rounded_up = (int) Math.ceil(p.getPower(makeID("Momentum")).amount / 2.0);
//            addToBot(new ReducePowerAction(p, p, makeID("Momentum"), half_m_rounded_up));
//        }

    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse)
            return false;

        boolean hasImpact = false;

        for (AbstractCard c : p.hand.group) {
            if (c.hasTag(PilotTags.IMPACT)) {
                hasImpact = true;
            }
        }

        if (!hasImpact) {
            this.cantUseMessage = "I don't have an #rImpact ready.";
            return false;
        }
        return canUse;
    }

    @Override
    public AbstractCard makeCopy() {
        return new Shattercrash();
    }
}
