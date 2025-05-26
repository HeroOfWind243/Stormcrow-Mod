package stormcrowmod.cards.skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import stormcrowmod.actions.PulseAction;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.character.PilotCharacter;
import stormcrowmod.util.CardStats;
import stormcrowmod.util.PilotTags;

public class DragFlaps extends BaseCard {
    public static final String ID = makeID(DragFlaps.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            PilotCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            0 //Can use -1 for X, or -2 for unplayable
    );


    private static final int MAGIC = 8;
    private static final int UPG_MAGIC = -2;

    public DragFlaps() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);

        tags.add(PilotTags.PULSE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.getPower(makeID("Momentum")).amount == this.magicNumber) {
            addToBot(new RemoveSpecificPowerAction(p, p, makeID("Momentum")));
        } else {
            addToBot(new ReducePowerAction(p, p, makeID("Momentum"), this.magicNumber));
        }

        addToBot(new GainEnergyAction(1));
        addToBot(new PulseAction(new GainEnergyAction(1)));

    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse)
            return false;
        if (!p.hasPower(makeID("Momentum")) || (p.hasPower(makeID("Momentum")) && p.getPower(makeID("Momentum")).amount < this.magicNumber)) {
            this.cantUseMessage = "I don't have NL enough #rMomentum.";
            return false;
        }
        return canUse;
    }

    @Override
    public AbstractCard makeCopy() {
        return new DragFlaps();
    }
}
