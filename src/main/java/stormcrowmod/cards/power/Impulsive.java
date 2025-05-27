package stormcrowmod.cards.power;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import stormcrowmod.cards.BaseCard;
import stormcrowmod.cards.created.Impact;
import stormcrowmod.character.PilotCharacter;
import stormcrowmod.powers.ImpulsivePower;
import stormcrowmod.util.CardStats;

public class Impulsive extends BaseCard {
    public static final String ID = makeID(Impulsive.class.getSimpleName()); //makeID ensures this is unique to this mod
    private static final CardStats info = new CardStats(
            PilotCharacter.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1 //Can use -1 for X, or -2 for unplayable
    );

    public Impulsive() {
        super(ID, info);
        setInnate(false, true);
        cardsToPreview = new Impact();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new ImpulsivePower(p)));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Impulsive();
    }
}
