package stormcrowmod.cards.skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import stormcrowmod.actions.LoopAroundFollowupAction;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.character.PilotCharacter;
import stormcrowmod.util.CardStats;

public class KineticGenerator extends BaseCard {
    public static final String ID = makeID(KineticGenerator.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            PilotCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1 //Can use -1 for X, or -2 for unplayable
    );

    private static final int MAGIC_2 = 6;

    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public KineticGenerator() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);
        setCustomVar("reqM", MAGIC_2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.getPower(makeID("Momentum")).amount == customVar("reqM")) {
            addToBot(new RemoveSpecificPowerAction(p, p, makeID("Momentum")));
        } else {
            addToBot(new ReducePowerAction(p, p, makeID("Momentum"), customVar("reqM")));
        }

        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber)));

    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse)
            return false;
        if (!p.hasPower(makeID("Momentum")) || (p.hasPower(makeID("Momentum")) && p.getPower(makeID("Momentum")).amount < customVar("reqM"))) {
            this.cantUseMessage = "I don't have NL enough #rMomentum.";
            return false;
        }
        return canUse;
    }

    @Override
    public AbstractCard makeCopy() {
        return new KineticGenerator();
    }
}
