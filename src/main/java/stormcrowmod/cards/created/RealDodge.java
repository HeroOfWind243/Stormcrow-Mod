package stormcrowmod.cards.created;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import stormcrowmod.actions.PulseAction;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.util.CardStats;
import stormcrowmod.util.PilotTags;

public class RealDodge extends BaseCard {
    public static final String ID = makeID(RealDodge.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS,
            CardType.SKILL,
            CardRarity.SPECIAL,
            CardTarget.SELF,
            0 //Can use -1 for X, or -2 for unplayable
    );

    public RealDodge() {
        super(ID, info);

        setExhaust(true);
        setEthereal(true);
        tags.add(PilotTags.PULSE);
        this.cardsToPreview = new Perfection();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new PulseAction(new MakeTempCardInHandAction(new Perfection())));

    }

    @Override
    public AbstractCard makeCopy() {
        return new RealDodge();
    }
}
