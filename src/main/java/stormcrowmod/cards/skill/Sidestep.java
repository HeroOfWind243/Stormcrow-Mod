package stormcrowmod.cards.skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;
import stormcrowmod.actions.PulseAction;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.character.PilotCharacter;
import stormcrowmod.util.CardStats;
import stormcrowmod.util.PilotTags;

public class Sidestep extends BaseCard {
    public static final String ID = makeID(Sidestep.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            PilotCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            0 //Can use -1 for X, or -2 for unplayable
    );

    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public Sidestep() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);

        tags.add(PilotTags.PULSE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, this.magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new LoseDexterityPower(p, this.magicNumber)));

        addToBot(new PulseAction(new ApplyPowerAction(p, p, new DexterityPower(p, this.magicNumber)),
                new ApplyPowerAction(p, p, new LoseDexterityPower(p, this.magicNumber))));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Sidestep();
    }
}
