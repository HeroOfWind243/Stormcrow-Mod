package stormcrowmod.cards.created;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import stormcrowmod.actions.PulseAction;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.util.CardStats;
import stormcrowmod.util.PilotTags;

public class Perfection extends BaseCard {
    public static final String ID = makeID(Perfection.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.SKILL,
            CardRarity.SPECIAL,
            CardTarget.SELF,
            0 //Can use -1 for X, or -2 for unplayable
    );

    public Perfection() {
        super(ID, info);

        setExhaust(true);
        setEthereal(true);
        tags.add(PilotTags.PULSE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p,1)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Perfection();
    }
}
