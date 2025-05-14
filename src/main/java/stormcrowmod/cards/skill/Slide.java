package stormcrowmod.cards.skill;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.character.PilotCharacter;
import stormcrowmod.util.CardStats;

public class Slide extends BaseCard {
    public static final String ID = makeID(Slide.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            PilotCharacter.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.COMMON,
            CardTarget.SELF,
            0 //Can use -1 for X, or -2 for unplayable
    );

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public Slide() {
        super(ID, info);

        setMagic(MAGIC, UPG_MAGIC);

        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainEnergyAction(1));
        addToBot(new ApplyPowerAction(p, p, new DrawCardNextTurnPower(p, this.magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Slide();
    }
}
